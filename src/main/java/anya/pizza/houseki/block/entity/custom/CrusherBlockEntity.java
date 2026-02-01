package anya.pizza.houseki.block.entity.custom;

import anya.pizza.houseki.block.custom.CrusherBlock;
import anya.pizza.houseki.block.entity.ImplementedInventory;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
import anya.pizza.houseki.recipe.ModSerializer;
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
    private ItemStack lastInput = ItemStack.EMPTY; /**
     * Constructs a CrusherBlockEntity at the given position and block state and installs its ContainerData property delegate.
     *
     * The property delegate exposes five indices for UI synchronization and saving: index 0 = progress, 1 = maxProgress, 2 = fuel display
     * (returns `fuelTime` when > 0 or `lastValidFuelTime` when `fuelTime` is 0), 3 = maxFuelTime, and 4 = `isCrafting` (1 if crafting, 0 otherwise).
     *
     * @param pos the block position
     * @param state the block state
     */

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHER_BE, pos, state);
        this.propertyDelegate = new ContainerData() {
            /**
             * Accesses a container property by index for UI synchronization.
             *
             * @param index the property index:
             *              0 = current progress,
             *              1 = maximum progress,
             *              2 = fuel time (or last valid fuel time if current fuel time is 0),
             *              3 = maximum fuel time,
             *              4 = crafting flag (1 if crafting, 0 otherwise)
             * @return the integer value of the requested property, or 0 for unknown indices
             */
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

            /**
             * Sets one of the container data fields by index.
             *
             * Index mapping:
             * 0 -> progress
             * 1 -> maxProgress
             * 2 -> fuelTime
             * 3 -> maxFuelTime
             *
             * @param index the data index to set
             * @param value the new value for the specified data index
             */
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = value;
                    case 2 -> fuelTime = value;
                    case 3 -> maxFuelTime = value;
                }
            }

            /**
             * Number of data properties exposed by this block entity's ContainerData.
             *
             * @return the number of data fields (5)
             */
            @Override
            public int getCount() {
                return 5;
            }
        };
    }

    /**
     * Determines burn time (in ticks) provided by the specified fuel item.
     *
     * @param fuel the item stack to test as fuel
     * @return the number of ticks of burn time the fuel supplies; 1600 for an iron ingot, 0 for any other item
     */
    public int getFuelTime(ItemStack fuel) {
        return fuel.is(Items.IRON_INGOT) ? 1600 : 0;
    }

    /**
     * Accesses the block entity's internal inventory list.
     *
     * @return the live NonNullList of ItemStack representing all inventory slots; changes to this list
     *         directly modify the block entity's inventory
     */
    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    /**
     * Provide the block position used to attach the crusher's screen for the given player.
     *
     * @return the block position of this block entity to use when opening the screen
     */
    @Override
    public BlockPos getScreenOpeningData(@NonNull ServerPlayer serverPlayerEntity) {
        return this.worldPosition;
    }

    /**
     * The translated display name for the crusher UI.
     *
     * @return the translated Component for the key "gui.houseki.crusher"
     */
    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("gui.houseki.crusher");
    }

    /**
     * Creates a CrusherScreenHandler bound to this block entity and its property delegate.
     *
     * @param syncId the window synchronization id supplied by the client
     * @param playerInventory the opening player's inventory
     * @param player the player opening the menu
     * @return the created menu instance for the player and this block entity
     */
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, @NonNull Inventory playerInventory, @NonNull Player player) {
        return new CrusherScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    /**
     * Drops this block entity's inventory at the given position and invokes superclass removal side effects.
     *
     * @param pos the block position being removed
     * @param oldState the block state that was replaced
     */
    @Override
    public void preRemoveSideEffects(@NonNull BlockPos pos, @NonNull BlockState oldState) {
        assert level != null;
        Containers.dropContents(level, pos, (this));
        super.preRemoveSideEffects(pos, oldState);
    }

    /**
     * Writes the block entity's persistent data (inventory and crafting/fuel state) into the provided value output.
     *
     * @param view the output to which inventory contents and state fields ("progress", "max_progress", "fuel_time", "max_fuel_time") are saved
     */
    @Override
    protected void saveAdditional(@NonNull ValueOutput view) {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, inventory);
        view.putInt("progress", progress);
        view.putInt("max_progress", maxProgress);
        view.putInt("fuel_time", fuelTime);
        view.putInt("max_fuel_time", maxFuelTime);
    }

    /**
     * Restores the block entity's persisted inventory and operational state from the given input.
     *
     * Loads inventory contents and numeric state fields used for crushing progress and fuel:
     * `progress`, `max_progress`, `fuel_time`, and `max_fuel_time`.
     *
     * @param view the data source containing the saved state to load
     */
    @Override
    protected void loadAdditional(@NonNull ValueInput view) {
        super.loadAdditional(view);
        ContainerHelper.loadAllItems(view, inventory);
        progress = view.getIntOr("progress", 0);
        maxProgress = view.getIntOr("max_progress", 0);
        fuelTime = view.getIntOr("fuel_time", 0);
        maxFuelTime = view.getIntOr("max_fuel_time", 0);
    }

    /**
     * Performs a single server-side tick update for the crusher, advancing crafting progress,
     * consuming fuel, producing output when a recipe completes, and updating the block's lit state.
     *
     * <p>Detects input changes to refresh required processing time, consumes one fuel item when needed,
     * increments progress while fuel is available and a valid recipe exists, invokes crafting when
     * progress reaches the required threshold, and marks the block entity changed when state is modified.
     *
     * @param world the level containing the crusher
     * @param pos   the position of the crusher block
     * @param state the current block state of the crusher
     */
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

        //Handle fuel
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

        //Handle Crushing
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

    /**
     * Updates this block entity's `maxProgress` to the crushing time of the current recipe, or to the recipe default when no recipe is available.
     */
    private void updateMaxProgress(Level world) {
        Optional<RecipeHolder<CrusherRecipe>> recipe = getCurrentRecipe();
        maxProgress = recipe.map(entry -> entry.value().crushingTime())
                .orElse(CrusherRecipe.DEFAULT_CRUSHING_TIME);
    }

    /**
     * Determines whether the crusher can perform the currently matched recipe given inventory space.
     *
     * @return `true` if a matching crusher recipe exists for the current input and both the main and auxiliary outputs can be inserted into their respective output slots, `false` otherwise.
     */
    private boolean canCraft() {
        Optional<RecipeHolder<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        CrusherRecipe crusherRecipe = recipe.get().value();
        ItemStack output = crusherRecipe.getResult(null);
        ItemStack auxiliary = crusherRecipe.auxiliaryOutput().orElse(ItemStack.EMPTY);

        return canInsertIntoSlot(OUTPUT_SLOT, output) && canInsertIntoSlot(AUXILIARY_OUTPUT_SLOT, auxiliary);
    }

    /**
     * Check whether an ItemStack can be placed into the specified inventory slot without
     * exceeding the slot's max stack size or changing the stored item's identity/components.
     *
     * @param slot  index of the target slot in the block entity's inventory
     * @param stack the ItemStack to insert; an empty stack is considered insertable
     * @return      true if the slot can accept the stack (slot empty or same item/components and combined count ≤ slot max), false otherwise
     */
    private boolean canInsertIntoSlot(int slot, ItemStack stack) {
        if (stack.isEmpty()) return true;
        ItemStack slotStack = inventory.get(slot);
        int maxCount = slotStack.isEmpty() ? stack.getMaxStackSize() : slotStack.getMaxStackSize();
        return (slotStack.isEmpty() || ItemStack.isSameItemSameComponents(slotStack, stack))
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
        Optional<RecipeHolder<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        CrusherRecipe crusherRecipe = recipe.get().value();

        // Handle Main Output
        insertOrIncrement(OUTPUT_SLOT, crusherRecipe.getResult(null).copy(), 1.0);

        // Handle Auxiliary Output
        crusherRecipe.auxiliaryOutput().ifPresent(stack -> {
            insertOrIncrement(AUXILIARY_OUTPUT_SLOT, stack.copy(), crusherRecipe.auxiliaryChance());
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
     * Locate the crusher recipe that matches the current input item.
     *
     * @return an Optional containing the matching `RecipeHolder<CrusherRecipe>` if a recipe exists for the current input, otherwise an empty Optional
     */
    private Optional<RecipeHolder<CrusherRecipe>> getCurrentRecipe() {
        assert this.getLevel() != null;
        return ((ServerLevel) this.getLevel()).recipeAccess()
                .getRecipeFor(ModTypes.CRUSHER_TYPE, new CrusherRecipeInput(inventory.getFirst()), this.getLevel());

    }

    /**
     * Provide the inventory slot indices that can be accessed from the given face.
     *
     * @param side the block face used for automation access
     * @return an array of slot indices accessible from that face — for `Direction.DOWN` the `OUTPUT_SLOT` and `AUXILIARY_OUTPUT_SLOT`, otherwise the `INPUT_SLOT` and `FUEL_SLOT`
     */
    @Override
    public int @NonNull [] getSlotsForFace(@NonNull Direction side) {
        return side == Direction.DOWN ? new int[]{OUTPUT_SLOT, AUXILIARY_OUTPUT_SLOT} : new int[]{INPUT_SLOT, FUEL_SLOT};
    }

    /**
     * Determines whether the given item stack is allowed to be inserted into the specified inventory slot from the given face.
     *
     * @param slot  the target inventory slot index (e.g., INPUT_SLOT, FUEL_SLOT, OUTPUT_SLOT)
     * @param stack the item stack proposed for insertion
     * @param side  the face from which the insertion would occur, or null if not from a specific face
     * @return      `true` if insertion is permitted (fuel items into the fuel slot; items with a matching crusher recipe into the input slot), `false` otherwise
     */
    @Override
    public boolean canPlaceItemThroughFace(int slot, @NonNull ItemStack stack, @Nullable Direction side) {
        if (slot == FUEL_SLOT) return getFuelTime(stack) > 0;
        if (slot == INPUT_SLOT) {
            assert this.getLevel() != null;
            assert level != null;
            ((ServerLevel) this.getLevel()).recipeAccess()
                    .getRecipeFor(ModTypes.CRUSHER_TYPE, new CrusherRecipeInput(stack), level).isPresent();
        }
        return false;
    }

    /**
     * Determines whether an item may be taken from the specified slot when accessed from the given face.
     *
     * @param slot  the inventory slot index being accessed
     * @param stack the item stack currently in the slot
     * @param side  the face through which extraction is attempted
     * @return `true` if the slot is the primary output or the auxiliary output slot, `false` otherwise
     */
    @Override
    public boolean canTakeItemThroughFace(int slot, @NonNull ItemStack stack, @NonNull Direction side) {
        return slot == OUTPUT_SLOT || slot == AUXILIARY_OUTPUT_SLOT;
    }

    /**
     * Create a block-entity data packet to synchronize this block entity's state with clients.
     *
     * @return the packet to send to clients to update this block entity's data, or {@code null} if no update is necessary
     */
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    /**
     * Checks whether the given player is within interaction range of this block entity.
     *
     * @return `true` if the player is within 4.5 blocks of the block entity's position, `false` otherwise.
     */
    @Override
    public boolean stillValid(@NonNull Player player) {
        return worldPosition.closerThan(worldPosition, 4.5);
    }

    /**
     * Clears all item stacks from this block entity's internal inventory and marks it as changed.
     *
     * Marking the block entity as changed ensures the new empty inventory state will be persisted and synchronized.
     */
    @Override
    public void clearContent() {
        inventory.clear();
        setChanged();
    }
}