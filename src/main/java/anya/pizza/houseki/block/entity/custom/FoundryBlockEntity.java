package anya.pizza.houseki.block.entity.custom;

import anya.pizza.houseki.block.custom.CrusherBlock;
import anya.pizza.houseki.block.custom.FoundryBlock;
import anya.pizza.houseki.block.entity.ImplementedInventory;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.recipe.*;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import anya.pizza.houseki.screen.custom.FoundryScreenHandler;
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

public class FoundryBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = FoundryRecipe.DEFAULT_MELT_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private final int lastValidFuelTime = 0;
    private boolean isCrafting = false;
    private ItemStack lastInput = ItemStack.EMPTY; //Cache input to detect changes

    public FoundryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FOUNDRY_BE, pos, state);
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
        return fuel.isOf(Items.COAL) ? 1600 : 0;
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
        return Text.translatable("gui.houseki.foundry");
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FoundryScreenHandler(syncId, playerInventory, this, propertyDelegate);
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
                fuelStack.decrement(1);
                dirty = true;
            }
        }

        //Handles Crushing
        boolean canCraftNow = fuelTime > 0 && canCraft();
        isCrafting = canCraftNow || (fuelTime > 0 && progress > 0);
        world.setBlockState(pos, state.with(FoundryBlock.LIT, fuelTime > 0));
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
        Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        maxProgress = recipe.map(entry -> entry.value().meltTime())
                .orElse(FoundryRecipe.DEFAULT_MELT_TIME);
    }

    private boolean canCraft() {
        Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        FoundryRecipe foundryRecipe = recipe.get().value();
        ItemStack output = foundryRecipe.getResult(null);

        return canInsertIntoSlot(OUTPUT_SLOT, output);
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
        Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;
        FoundryRecipe foundryRecipe = recipe.get().value();
        // Handles Main Output
        insertOrIncrement(OUTPUT_SLOT, foundryRecipe.getResult(null).copy(), 1.0);

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

    private Optional<RecipeEntry<FoundryRecipe>> getCurrentRecipe() {
        return ((ServerWorld) this.getWorld()).getRecipeManager()
                .getFirstMatch(ModRecipes.FOUNDRY_TYPE, new FoundryRecipeCastInput(inventory.getFirst()), this.getWorld());

    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return side == Direction.DOWN ? new int[]{OUTPUT_SLOT} : new int[]{INPUT_SLOT, FUEL_SLOT};
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @org.jetbrains.annotations.Nullable Direction side) {
        if (slot == FUEL_SLOT) return getFuelTime(stack) > 0;
        if (slot == INPUT_SLOT) ((ServerWorld) this.getWorld()).getRecipeManager()
                .getFirstMatch(ModRecipes.FOUNDRY_TYPE, new FoundryRecipeCastInput(stack), world).isPresent();
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot == OUTPUT_SLOT;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(pos, 4.5);
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }
}

    /*
    private static final int INPUT_SLOT_INGOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int INPUT_SLOT_CAST = 2;
    private static final int OUTPUT_SLOT = 3;
    protected final PropertyDelegate propertyDelegate;
    private int meltProgress = 0;
    private int maxMeltProgress = FoundryRecipe.DEFAULT_MELTING_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private final int lastValidFuelTime = 0;
    private int metalLevel = 0;
    private int maxMetalLevel = 900; //90 = 1 ingot
    private int castProgress = 0;
    private int maxCastTime = 120;
    private boolean isCrafting = false;
    private ItemStack lastInput = ItemStack.EMPTY; //cache


    public FoundryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FOUNDRY_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> meltProgress;
                    case 1 -> maxMeltProgress;
                    case 2 -> fuelTime > 0 ? fuelTime : lastValidFuelTime;
                    case 3 -> maxFuelTime;
                    case 4 -> metalLevel;
                    case 5 -> maxMetalLevel;
                    case 6 -> castProgress;
                    case 7 -> maxCastTime;
                    case 8 -> isCrafting ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> meltProgress = value;
                    case 1 -> maxMeltProgress = value;
                    case 2 -> fuelTime = value;
                    case 3 -> maxFuelTime = value;
                    case 4 -> metalLevel = value;
                    case 5 -> maxMetalLevel = value;
                    case 6 -> castProgress = value;
                }
            }

            @Override
            public int size() {
                return 9;
            }
        };
    }

    public int getFuelTime(ItemStack fuel) {
        return fuel.isOf(Items.COAL) ? 1000 : 0;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.houseki.foundry");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FoundryScreenHandler(syncId, playerInventory, this, propertyDelegate);
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
        view.putInt("melt_progress", meltProgress);
        view.putInt("max_progress", maxMeltProgress);
        view.putInt("fuel_time", fuelTime);
        view.putInt("max_fuel_time", maxFuelTime);
        view.putInt("metal_level", metalLevel);
        view.putInt("max_metal_level", maxMetalLevel);
        view.putInt("cast_time", castProgress);
        view.putInt("max_cast_time", maxCastTime);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        meltProgress = view.getInt("melt_progress", 0);
        maxMeltProgress = view.getInt("max_progress", 0);
        fuelTime = view.getInt("fuel_time", 0);
        maxFuelTime = view.getInt("max_fuel_time", 0);
        metalLevel = view.getInt("metal_level", 0);
        maxMetalLevel = view.getInt("max_metal_level", 0);
        castProgress = view.getInt("cast_time", 0);
        maxCastTime = view.getInt("max_cast_time", 0);
    }

    public static void tick(World world, BlockPos pos, BlockState state, FoundryBlockEntity entity) {
        if (world.isClient()) return;

        boolean wasBurning = entity.fuelTime > 0;
        boolean dirty = false;

        if (entity.fuelTime > 0) {
            entity.fuelTime--;
        }

        if (entity.fuelTime <= 0 && (canMelt(entity) || canCast(entity))) {
            ItemStack fuel = entity.getStack(FUEL_SLOT);
            if (!fuel.isEmpty()) {
                int time = entity.getFuelTime(fuel);
                if (time > 0) {
                    entity.fuelTime = entity.maxFuelTime = time;
                    fuel.decrement(1);
                    dirty = true;
                }
            }
        }

        boolean isBurning = entity.fuelTime > 0;

        if (isBurning && canMelt(entity)) {
            entity.meltProgress++;
            if (entity.meltProgress >= entity.maxMeltProgress) {
                entity.getStack(INPUT_SLOT_INGOT).decrement(1);
                entity.metalLevel += 90;
                entity.meltProgress = 0;
            }
            dirty = true;
        } else {
            entity.castProgress = 0;
        }

        if (wasBurning != (entity.fuelTime > 0)) {
            world.setBlockState(pos, state.with(FoundryBlock.LIT, entity.fuelTime > 0), Block.NOTIFY_ALL);
            dirty = true;
        }

        if (dirty) {
            markDirty(world, pos, state);
        }
    }

    private void updateMaxProgress(World world) {
        Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        maxMeltProgress = recipe.map(entry -> entry.value().meltingTime())
                .orElse(FoundryRecipe.DEFAULT_MELTING_TIME);
    }

    private static boolean canMelt(FoundryBlockEntity entity) {
        ItemStack input = entity.getStack(INPUT_SLOT_INGOT);
        return input.isOf(ModItems.STEEL) && (entity.metalLevel + 90 <= entity.maxMetalLevel);
    }

    private static boolean canCast(FoundryBlockEntity entity) {
        ItemStack cast = entity.getStack(INPUT_SLOT_CAST);
        ItemStack outputSlot = entity.getStack(OUTPUT_SLOT);

        if (cast.isEmpty() || entity.metalLevel < 90) return false;

        ItemStack potentialResult = getResultFromCast(cast);

        return !potentialResult.isEmpty() &&
                (outputSlot.isEmpty() || (outputSlot.isOf(potentialResult.getItem()) && outputSlot.getCount() < outputSlot.getMaxCount()));
    }

    private static void craftOutput(FoundryBlockEntity entity) {
        Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        FoundryRecipe foundryRecipe = recipe.get().value();
        ItemStack cast = entity.getStack(INPUT_SLOT_CAST);
        ItemStack result = getResultFromCast(cast);

        entity.metalLevel -= 90;

        ItemStack currentOutput = entity.getStack(OUTPUT_SLOT);
        if (currentOutput.isEmpty()) {
            entity.setStack(OUTPUT_SLOT, result.copy());
        } else {
            currentOutput.increment(1);
        }
    }
    //placeholder till get recipe logic
    private static ItemStack getResultFromCast(ItemStack cast) {
        if (cast.isIn(ModTags.Items.CASTS)) return new ItemStack(ModItems.SPEAR_HEAD_CAST);
        return ItemStack.EMPTY;
    }


}
*/
