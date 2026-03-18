package anya.pizza.houseki.block.entity.custom;

import anya.pizza.houseki.block.custom.CrusherBlock;
import anya.pizza.houseki.block.entity.ImplementedInventory;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
import anya.pizza.houseki.recipe.ModRecipes;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class CrusherBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int AUXILIARY_OUTPUT_SLOT = 3;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = CrusherRecipe.DEFAULT_CRUSHING_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private int lastValidFuelTime = 0;
    private boolean isCrafting = false;
    private ItemStack lastInput = ItemStack.EMPTY; //Cache input to detect changes

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHER_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> fuelTime > 0 ? fuelTime : lastValidFuelTime;
                    case 3 -> maxFuelTime;
                    case 4 -> isCrafting ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = value;
                    case 2 -> fuelTime = value;
                    case 3 -> maxFuelTime = value;
                }
            }

            @Override
            public int size() {
                return 5;
            }
        };
    }

    public int getFuelTime(ItemStack fuel) {
        return fuel.isOf(Items.IRON_INGOT) ? 1600 : 0;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(@NonNull ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.houseki.crusher");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrusherScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        ItemScatterer.spawn(world, pos, (this));
        super.onBlockReplaced(pos, oldState);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        view.putInt("progress", progress);
        view.putInt("max_progress", maxProgress);
        view.putInt("fuel_time", fuelTime);
        view.putInt("max_fuel_time", maxFuelTime);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        progress = view.getInt("progress", 0);
        maxProgress = view.getInt("max_progress", 0);
        fuelTime = view.getInt("fuel_time", 0);
        maxFuelTime = view.getInt("max_fuel_time", 0);
    }

    /**
     * Performs the per-tick server-side update for the crusher: manages fuel consumption, advances
     * or resets crushing progress, executes completed crush recipes, updates the block's lit state,
     * and marks the block entity dirty when its stored state or inventory changes.
     *
     * @param world the world containing this block entity (server-side expected)
     * @param pos   the block position of this crusher
     * @param state the current block state at {@code pos}; the method may update the {@code LIT} property
     */
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;
        boolean dirty = false;
        ItemStack input = inventory.getFirst();
        if(!ItemStack.areItemsAndComponentsEqual(input, lastInput)) {
            lastInput = input.copy();
            updateMaxProgress(world);
            if (progress > 0 && !canCraft()) {
                progress = 0;
                dirty = true;
            }
        }

        //Handles Fuel
        if (fuelTime > 0) {
            fuelTime--;
            dirty = true;
        } else if (canCraft()) {
            ItemStack fuelStack = inventory.get(FUEL_SLOT);
            int fuelVal = getFuelTime(fuelStack);
            if (fuelVal > 0) {
                fuelTime = maxFuelTime = fuelVal;
                lastValidFuelTime = fuelVal;
                fuelStack.decrement(1);
                dirty = true;
            }
        }

        //Handles Crushing
        boolean canCraftNow = fuelTime > 0 && canCraft();
        isCrafting = canCraftNow || (fuelTime > 0 && progress > 0);
        world.setBlockState(pos, state.with(CrusherBlock.LIT, fuelTime > 0));
        if (canCraftNow) {
            progress++;
            dirty = true;
            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }
        }
        if (dirty) markDirty(world, pos, state);
    }

    private void updateMaxProgress(World world) {
        Optional<RecipeEntry<CrusherRecipe>> recipe = getCurrentRecipe();
        maxProgress = recipe.map(entry -> entry.value().crushingTime())
                .orElse(CrusherRecipe.DEFAULT_CRUSHING_TIME);
    }

    private boolean canCraft() {
        Optional<RecipeEntry<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        CrusherRecipe crusherRecipe = recipe.get().value();
        ItemStack output = crusherRecipe.getResult(null);
        ItemStack auxiliary = crusherRecipe.auxiliaryOutput().orElse(ItemStack.EMPTY);

        return canInsertIntoSlot(OUTPUT_SLOT, output) && canInsertIntoSlot(AUXILIARY_OUTPUT_SLOT, auxiliary);
    }

    /**
     * Determine whether the given ItemStack can be placed into the specified inventory slot
     * without violating item compatibility or stack size limits.
     *
     * @param slot  index of the target slot in the block entity's inventory
     * @param stack the ItemStack intended for insertion; an empty stack is considered insertable
     * @return      `true` if the slot can accept the stack (slot empty or same item/component and total count does not exceed the slot's max), `false` otherwise
     */
    private boolean canInsertIntoSlot(int slot, ItemStack stack) {
        if (stack.isEmpty()) return true;
        ItemStack slotStack = inventory.get(slot);
        int maxCount = slotStack.isEmpty() ? stack.getMaxCount() : slotStack.getMaxCount();
        return (slotStack.isEmpty() || ItemStack.areItemsAndComponentsEqual(slotStack, stack))
            && slotStack.getCount() + stack.getCount() <= maxCount;
    }

    /**
     * Apply the currently matched crusher recipe: produce the recipe's main output, optionally produce
     * the auxiliary output based on its chance, and consume one input item.
     *
     * If no matching recipe is available, the method makes no changes. The main output is always
     * inserted (or stacked) into the main output slot; the auxiliary output is inserted only if the
     * recipe provides one and its configured chance succeeds. One item is removed from the input slot
     * when a recipe is applied.
     */
    private void craftItem() {
        Optional<RecipeEntry<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;
        CrusherRecipe crusherRecipe = recipe.get().value();
        // Handles Main Output
        insertOrIncrement(OUTPUT_SLOT, crusherRecipe.getResult(null).copy(), 1.0);
        // Handles Auxiliary Output
        crusherRecipe.auxiliaryOutput().ifPresent(stack -> {
            insertOrIncrement(AUXILIARY_OUTPUT_SLOT, stack.copy(), crusherRecipe.auxiliaryChance());
        });

        inventory.get(INPUT_SLOT).decrement(1);
    }

    /**
         * Conditionally insert or merge an ItemStack into the given inventory slot.
         *
         * If `result` is empty nothing is changed. When the probabilistic check against `chance` succeeds,
         * the method places `result` into the slot if it is empty, or increases the existing stack's count by
         * `result.getCount()` if the slot already contains the same item.
         *
         * @param slot   the index of the target inventory slot
         * @param result the ItemStack to insert or merge into the slot
         * @param chance a probability in the range [0, 1] that the insertion will occur
         */
    private void insertOrIncrement(int slot, ItemStack result, double chance) {
        if (result.isEmpty() || Math.random() > chance) return;
        ItemStack slotStack = inventory.get(slot);
        if (slotStack.isEmpty()) {
            inventory.set(slot, result);
        } else {
            slotStack.increment(result.getCount());
        }
    }

    /**
     * Finds the first crusher recipe that matches the block entity's current input item.
     *
     * @return an Optional containing the matching RecipeEntry&lt;CrusherRecipe&gt; if present, otherwise an empty Optional
     */
    private Optional<RecipeEntry<CrusherRecipe>> getCurrentRecipe() {
        return ((ServerWorld) this.getWorld()).getRecipeManager()
                .getFirstMatch(ModRecipes.CRUSHER_TYPE, new CrusherRecipeInput(inventory.getFirst()), this.getWorld());
    }

    /**
     * Determine which inventory slot indices are accessible from a given block face.
     *
     * @param side the block face being accessed
     * @return an array of slot indices accessible from {@code side}; if {@code side} is {@link Direction#DOWN}
     *         the array contains the output and auxiliary output slots, otherwise it contains the input and fuel slots
     */
    @Override
    public int[] getAvailableSlots(Direction side) {
        return side == Direction.DOWN ? new int[]{OUTPUT_SLOT, AUXILIARY_OUTPUT_SLOT} : new int[]{INPUT_SLOT, FUEL_SLOT};
    }

    /**
     * Determines whether the given item stack may be inserted into the specified slot from the given side.
     *
     * @param slot  the target slot index (e.g., input or fuel)
     * @param stack the item stack to insert
     * @param side  the side of the block from which insertion is attempted; may be null and is not used by this implementation
     * @return      `true` if the stack may be inserted into the slot: for the fuel slot if it provides fuel time, for the input slot if it matches a crusher recipe in this world, `false` otherwise
     */
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (slot == FUEL_SLOT) return getFuelTime(stack) > 0;
        if (slot == INPUT_SLOT) {
            return ((ServerWorld) this.getWorld()).getRecipeManager()
                    .getFirstMatch(ModRecipes.CRUSHER_TYPE, new CrusherRecipeInput(stack), world).isPresent();
        }
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot == OUTPUT_SLOT || slot == AUXILIARY_OUTPUT_SLOT;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    /**
     * Determines whether the given player may interact with this block entity based on distance.
     *
     * @param player the player attempting to use the block entity
     * @return `true` if the player is within 8 blocks (squared distance ≤ 64) of the block entity's center, `false` otherwise
     */
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64;
    }

    /**
     * Clears the block entity's inventory and marks the entity as modified.
     *
     * This empties all item slots and flags the block entity so changes are persisted
     * and inventories are updated for viewers.
     */
    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }
}