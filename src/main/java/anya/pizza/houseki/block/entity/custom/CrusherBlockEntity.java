package anya.pizza.houseki.block.entity.custom;

import anya.pizza.houseki.block.custom.CrusherBlock;
import anya.pizza.houseki.block.entity.ImplementedInventory;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
import anya.pizza.houseki.recipe.ModTypes;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class CrusherBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int AUXILIARY_OUTPUT_SLOT = 3;
    protected final ContainerData propertyDelegate;
    private int progress = 0;
    private int maxProgress = CrusherRecipe.DEFAULT_CRUSHING_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private final int lastValidFuelTime = 0;
    private boolean isCrafting = false;
    private ItemStack lastInput = ItemStack.EMPTY; //Cache input to detect changes

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHER_BE, pos, state);
        this.propertyDelegate = new ContainerData() {
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
            public int getCount() {
                return 5;
            }
        };
    }

    public int getFuelTime(ItemStack fuel) {
        return fuel.is(Items.IRON_INGOT) ? 1600 : 0;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer serverPlayerEntity) {
        return this.worldPosition;
    }

    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("gui.houseki.crusher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, @NonNull Inventory playerInventory, @NonNull Player player) {
        return new CrusherScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public void preRemoveSideEffects(@NonNull BlockPos pos, @NonNull BlockState oldState) {
        assert level != null;
        if (level != null) {
            Containers.dropContents(level, pos, this);
        }
        super.preRemoveSideEffects(pos, oldState);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput view) {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, inventory);
        view.putInt("progress", progress);
        view.putInt("max_progress", maxProgress);
        view.putInt("fuel_time", fuelTime);
        view.putInt("max_fuel_time", maxFuelTime);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput view) {
        super.loadAdditional(view);
        ContainerHelper.loadAllItems(view, inventory);
        progress = view.getIntOr("progress", 0);
        maxProgress = view.getIntOr("max_progress", 0);
        fuelTime = view.getIntOr("fuel_time", 0);
        maxFuelTime = view.getIntOr("max_fuel_time", 0);
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) return;
        boolean dirty = false;
        ItemStack input = inventory.getFirst();
        if(!ItemStack.isSameItemSameComponents(input, lastInput)) {
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
                fuelStack.shrink(1);
                dirty = true;
            }
        }

        //Handles Crushing
        boolean canCraftNow = fuelTime > 0 && canCraft();
        isCrafting = canCraftNow || (fuelTime > 0 && progress > 0);
        world.setBlockAndUpdate(pos, state.setValue(CrusherBlock.LIT, fuelTime > 0));
        if (canCraftNow) {
            progress++;
            dirty = true;
            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }
        }
        if (dirty) setChanged(world, pos, state);
    }

    private void updateMaxProgress(Level world) {
        Optional<RecipeHolder<CrusherRecipe>> recipe = getCurrentRecipe();
        maxProgress = recipe.map(entry -> entry.value().crushingTime())
                .orElse(CrusherRecipe.DEFAULT_CRUSHING_TIME);
    }

    private boolean canCraft() {
        Optional<RecipeHolder<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        CrusherRecipe crusherRecipe = recipe.get().value();
        ItemStack output = crusherRecipe.getResult(null);
        ItemStack auxiliary = crusherRecipe.auxiliaryOutput()
                .map(ItemStack::new)      // Convert the Item to a Stack if present
                .orElse(ItemStack.EMPTY); // Otherwise return the empty stack

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
        int maxCount = slotStack.isEmpty() ? stack.getMaxStackSize() : slotStack.getMaxStackSize();
        return (slotStack.isEmpty() || ItemStack.isSameItemSameComponents(slotStack, stack))
            && slotStack.getCount() + stack.getCount() <= maxCount;
    }

    /**
     * Apply the currently matched crusher recipe, producing the recipe's outputs and consuming one input item.
     *
     * If no matching recipe is available, no changes are made. The recipe's main output is inserted into (or stacked
     * onto) the main output slot; the auxiliary output is inserted into the auxiliary output slot only if the recipe
     * provides one and its configured chance succeeds. One item is removed from the input slot when the recipe is applied.
     */
    private void craftItem() {
        Optional<RecipeHolder<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;
        CrusherRecipe crusherRecipe = recipe.get().value();
        insertOrIncrement(OUTPUT_SLOT, crusherRecipe.getResult(null), 1.0);
        // Handles Auxiliary Output
        crusherRecipe.auxiliaryOutput().ifPresent(item -> {
            // Convert the raw Item into a new ItemStack so we can use .copy() or set counts
            ItemStack auxStack = new ItemStack(item); 
            insertOrIncrement(AUXILIARY_OUTPUT_SLOT, auxStack, crusherRecipe.auxiliaryChance());
        });

        inventory.get(INPUT_SLOT).shrink(1);
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
            slotStack.grow(result.getCount());
        }
    }

    /**
     * Look up the crusher recipe that matches the current input stack on the server.
     *
     * @return An Optional containing the matching RecipeHolder<CrusherRecipe> when running on a server and a recipe exists; {@code Optional.empty()} otherwise.
     */
    private Optional<RecipeHolder<CrusherRecipe>> getCurrentRecipe() {
        Level level = this.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return Optional.empty();
        }
        return serverLevel.recipeAccess()
                .getRecipeFor(ModTypes.CRUSHER_TYPE, new CrusherRecipeInput(inventory.getFirst()), level);
    }

    /**
     * Get the inventory slot indices that are accessible from the given block face.
     *
     * @param side the block face from which access is attempted
     * @return an array of slot indices accessible from the specified face; if {@link Direction#DOWN} returns
     *         OUTPUT_SLOT and AUXILIARY_OUTPUT_SLOT, otherwise returns INPUT_SLOT and FUEL_SLOT
     */
    @Override
    public int @NonNull [] getSlotsForFace(@NonNull Direction side) {
        return side == Direction.DOWN ? new int[]{OUTPUT_SLOT, AUXILIARY_OUTPUT_SLOT} : new int[]{INPUT_SLOT, FUEL_SLOT};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, @NonNull ItemStack stack, @Nullable Direction side) {
        if (slot == FUEL_SLOT) return getFuelTime(stack) > 0;
        if (slot == INPUT_SLOT) {
            Level level = getLevel();
            if (level instanceof ServerLevel serverLevel) {
                return serverLevel.recipeAccess()
                        .getRecipeFor(ModTypes.CRUSHER_TYPE, new CrusherRecipeInput(stack), level)
                        .isPresent();
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, @NonNull ItemStack stack, @NonNull Direction side) {
        return slot == OUTPUT_SLOT || slot == AUXILIARY_OUTPUT_SLOT;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public boolean stillValid(@NonNull Player player) {
        return level != null
                && level.getBlockEntity(worldPosition) == this
                && player.distanceToSqr(
                        worldPosition.getX() + 0.5,
                        worldPosition.getY() + 0.5,
                        worldPosition.getZ() + 0.5
                ) <= 4.5 * 4.5;
    }

    @Override
    public void clearContent() {
        inventory.clear();
        setChanged();
    }
}