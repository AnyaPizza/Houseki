package anya.pizza.houseki.block.entity.custom;

import anya.pizza.houseki.block.custom.FoundryBlock;
import anya.pizza.houseki.block.entity.ImplementedInventory;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.*;
import anya.pizza.houseki.screen.custom.FoundryScreenHandler;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class FoundryBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(5, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int CAST_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;
    private static final int COOLING_SLOT = 4;
    protected final ContainerData propertyDelegate;
    private int meltProgress = 0;
    private int maxMeltProgress = FoundryMeltingRecipe.DEFAULT_MELT_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    // Metal type constants
    public static final int METAL_STEEL = 0;
    public static final int METAL_METEORIC_IRON = 1;

    private int steelLevel = 0;
    private int meteoricIronLevel = 0;
    private int maxMetalLevel = 1000; // 100 = 1 ingot, holds 10 ingots per metal type
    private int activeMetalType = METAL_STEEL; // selected metal for casting
    private int castProgress = 0;
    private int maxCastProgress = FoundryCastingRecipe.DEFAULT_CAST_TIME;
    private int coolingProgress = 0;
    private int maxCoolingProgress = FoundryCastingRecipe.DEFAULT_COOLING_TIME;
    private int lastValidFuelTime = 0;
    private boolean isCrafting = false;
    private ItemStack lastInput = ItemStack.EMPTY;
    /**
     * Constructs a FoundryBlockEntity at the given position and block state and initializes its property delegate used
     * for UI synchronization.
     *
     * <p>The property delegate exposes 11 integer properties (indices 0–10) used by the screen handler:
     * <ul>
     *   <li>0 = current melt progress</li>
     *   <li>1 = maximum melt progress</li>
     *   <li>2 = current fuel time, or the last valid fuel time when not burning</li>
     *   <li>3 = maximum fuel time</li>
     *   <li>4 = current metal level</li>
     *   <li>5 = maximum metal level</li>
     *   <li>6 = current cast progress</li>
     *   <li>7 = maximum cast progress</li>
     *   <li>8 = `1` if crafting is active, `0` otherwise</li>
     *   <li>9 = cooling progress</li>
     *   <li>10 = maximum cooling progress</li>
     * </ul>
     *
     * <p>The delegate's size is 11. The delegate's get(index) returns the corresponding value (or `0` for invalid indices).
     * The delegate accepts set(index, value) for indices 0–9 to update the corresponding internal fields; index 10 (max cooling
     * progress) is exposed as read-only through get.
     *
     * @param pos   the block position of this block entity
     * @param state the block state at the given position
     */

    public FoundryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FOUNDRY_BE, pos, state);
        this.propertyDelegate = new ContainerData() {
            /**
             * Get the synchronization property value for the screen handler by index.
             *
             * The index selects which internal state value to expose to the UI:
             * 0 = current melt progress
             * 1 = maximum melt progress
             * 2 = current fuel time, or the last valid fuel time when not burning
             * 3 = maximum fuel time
             * 4 = current metal level
             * 5 = maximum metal level
             * 6 = current cast progress
             * 7 = maximum cast progress
             * 8 = `1` if crafting is active, `0` otherwise
             * 9 = current cooling progress
             * 10 = maximum cooling progress
             *
             * @param index the property index (0–10)
             * @return the value for the requested property, or `0` if the index is invalid
             */
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> meltProgress;
                    case 1 -> maxMeltProgress;
                    case 2 -> fuelTime > 0 ? fuelTime : lastValidFuelTime;
                    case 3 -> maxFuelTime;
                    case 4 -> steelLevel;
                    case 5 -> maxMetalLevel;
                    case 6 -> castProgress;
                    case 7 -> maxCastProgress;
                    case 8 -> isCrafting ? 1 : 0;
                    case 9 -> coolingProgress;
                    case 10 -> maxCoolingProgress;
                    case 11 -> meteoricIronLevel;
                    case 12 -> activeMetalType;
                    default -> 0;
                };
            }

            /**
             * Sets an internal integer property identified by index.
             *
             * Indices:
             * 0 = meltProgress, 1 = maxMeltProgress, 2 = fuelTime, 3 = maxFuelTime,
             * 4 = metalLevel, 5 = maxMetalLevel, 6 = castProgress, 7 = maxCastProgress,
             * 8 = coolingProgress, 9 = maxCoolingProgress.
             *
             * @param index the property index to set
             * @param value the value to assign to the selected property
             */
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> meltProgress = value;
                    case 1 -> maxMeltProgress = value;
                    case 2 -> fuelTime = value;
                    case 3 -> maxFuelTime = value;
                    case 4 -> steelLevel = value;
                    case 5 -> maxMetalLevel = value;
                    case 6 -> castProgress = value;
                    case 7 -> maxCastProgress = value;
                    case 8 -> isCrafting = (value != 0);
                    case 9 -> coolingProgress = value;
                    case 10 -> maxCoolingProgress = value;
                    case 11 -> meteoricIronLevel = value;
                    case 12 -> activeMetalType = value;
                }
            }

            /**
             * Number of properties exposed by this block entity's property delegate.
             *
             * @return the fixed size (11) of the property delegate
             */
            @Override
            public int getCount() {
                return 13;
            }
        };
    }

    public int getFuelTime(ItemStack fuel) {
        return fuel.is(Items.COAL) ? 1600 : 0;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(@NonNull ServerPlayer serverPlayerEntity) {
        return this.worldPosition;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.houseki.foundry");
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new FoundryScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState oldState) {
        assert level != null;
        if (level != null) {
            Containers.dropContents(level, pos, this);
        }
        super.preRemoveSideEffects(pos, oldState);
    }

    /**
     * Save this block entity's inventory and numeric runtime state into the given WriteView.
     *
     * Writes the inventory and the following integer keys: "progress" (current melt progress),
     * "max_progress" (maximum melt progress), "fuel_time", "max_fuel_time", "metal_level",
     * "max_metal_level", "cast_time", "max_cast_time", "cooling_time", and "max_cooling_time".
     *
     * @param view the WriteView that receives the saved data
     */
    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, inventory);
        view.putInt("melt_progress", meltProgress);
        view.putInt("max_melt_progress", maxMeltProgress);
        view.putInt("fuel_time", fuelTime);
        view.putInt("max_fuel_time", maxFuelTime);
        view.putInt("steel_level", steelLevel);
        view.putInt("meteoric_iron_level", meteoricIronLevel);
        view.putInt("max_metal_level", maxMetalLevel);
        view.putInt("active_metal_type", activeMetalType);
        view.putInt("cast_time", castProgress);
        view.putInt("max_cast_time", maxCastProgress);
        view.putInt("cooling_time", coolingProgress);
        view.putInt("max_cooling_time", maxCoolingProgress);
    }

    /**
         * Restores this block entity's inventory and persisted integer state from the provided view.
         *
         * Reads inventory data and integer keys from the view and assigns them to fields. Concretely the method:
         * - delegates to super.readData(view) and reads the inventory via Inventories.readData(view, inventory)
         * - sets meltProgress from "melt_progress" and maxMeltProgress from "max_melt_progress"
         * - sets fuelTime from "fuel_time" and maxFuelTime from "max_fuel_time"
         * - sets metalLevel from "metal_level" and then (overwrites) from "max_metal_level"
         * - sets castProgress from "cast_time" and then (overwrites) from "max_cast_time", "cooling_time", and "max_cooling_time"
         *
         * Note: several assignments overwrite previous values (e.g., max_metal_level overwrites metalLevel), so the final field values reflect the last read assignment for each field.
         *
         * @param view the ReadView containing persisted data for this block entity
         */
    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);
        ContainerHelper.loadAllItems(view, inventory);
        meltProgress = view.getIntOr("melt_progress", 0);
        maxMeltProgress = view.getIntOr("max_melt_progress", 0);
        fuelTime = view.getIntOr("fuel_time", 0);
        maxFuelTime = view.getIntOr("max_fuel_time", 0);
        steelLevel = view.getIntOr("steel_level", 0);
        meteoricIronLevel = view.getIntOr("meteoric_iron_level", 0);
        maxMetalLevel = view.getIntOr("max_metal_level", 0);
        activeMetalType = view.getIntOr("active_metal_type", 0);
        castProgress = view.getIntOr("cast_time", 0);
        maxCastProgress = view.getIntOr("max_cast_time", 0);
        coolingProgress = view.getIntOr("cooling_time", 0);
        maxCoolingProgress = view.getIntOr("max_cooling_time", 0);
    }

    /**
     * Run server-side per-tick processing for the foundry, managing fuel, melting, casting, cooling, and block-state synchronization.
     *
     * <p>This updates internal progress counters and inventory slots as the foundry consumes fuel, converts input into molten
     * metal, performs casting into the cooling slot, advances cooling until the finished item is moved to output, and updates
     * the block's LIT property when burning starts or stops. Marks the block entity dirty when state or inventory changes.
     *
     * @param world the world the block entity is in
     * @param pos the block position of the foundry
     * @param state the current block state of the foundry
     */
    public void tick(Level world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) return;

        boolean wasBurning = fuelTime > 0;
        boolean dirty = false;

        //Burn Fuel
        if (fuelTime > 0) {
            fuelTime--;
        }

        //Consume new fuel
        if (fuelTime <= 0 && (canMelt() || canCast())) {
            ItemStack fuelStack = inventory.get(FUEL_SLOT);
            if (!fuelStack.isEmpty()) {
                int burnTime = getFuelTime(fuelStack);
                if (burnTime > 0) {
                    fuelTime = burnTime;
                    maxFuelTime = fuelTime;
                    fuelStack.shrink(1);
                    dirty = true;
                }
            }
        }

        boolean isBurning = fuelTime > 0;

        //Melting
        if (isBurning && canMelt()) {
            meltProgress++;
            if (meltProgress >= maxMeltProgress) {
                ItemStack meltedInput = inventory.get(INPUT_SLOT);
                if (meltedInput.is(ModItems.STEEL)) {
                    steelLevel += 100;
                } else if (meltedInput.is(ModItems.METEORIC_IRON_INGOT)) {
                    meteoricIronLevel += 100;
                }
                meltedInput.shrink(1);
                meltProgress = 0;
            }
            dirty = true;
        } else {
            if (meltProgress > 0) {
                meltProgress = 0; // Reset fully so it restarts from scratch
                dirty = true;
            }
        }

        //Casting
        if (isBurning && canCast()) {
            castProgress++;
            if (castProgress >= maxCastProgress) {
                ItemStack result = getResultFromCast(inventory.get(CAST_SLOT));
                inventory.set(COOLING_SLOT, result.copy());
                // Drain from the active metal's tank
                if (activeMetalType == METAL_STEEL) {
                    steelLevel -= 100;
                } else {
                    meteoricIronLevel -= 100;
                }
                castProgress = 0;
                maxCoolingProgress = getCurrentCastingRecipe()
                        .map(r -> r.value().coolingTime())
                        .orElse(FoundryCastingRecipe.DEFAULT_COOLING_TIME);
                coolingProgress = 1;
            }
            dirty = true;
        } else {
            castProgress = 0;
        }

        //Cooling
        if (coolingProgress > 0) {
            if (coolingProgress < maxCoolingProgress) {
                coolingProgress++;
                dirty = true;
            }
            if (coolingProgress >= maxCoolingProgress) {
                if (canFinishCooling()) {
                    ItemStack coolingItem = inventory.get(COOLING_SLOT);
                    ItemStack output = inventory.get(OUTPUT_SLOT);

                    if (output.isEmpty()) {
                        inventory.set(OUTPUT_SLOT, coolingItem.copy());
                    } else {
                        output.grow(coolingItem.getCount());
                    }

                    inventory.set(COOLING_SLOT, ItemStack.EMPTY);
                    inventory.set(CAST_SLOT, ItemStack.EMPTY);
                    coolingProgress = 0;

                    dirty = true;
                    setChanged(world, pos, state);
                    //level.updateNeighborsAt(pos, state, state, Block.UPDATE_NEIGHBORS);
                    //dirty = true;
                }
            }
        }

        if (wasBurning != (fuelTime > 0)) {
            world.setBlock(pos, state.setValue(FoundryBlock.LIT, fuelTime > 0), 3);
            dirty = true;
        }

        if (dirty) setChanged(world, pos, state);
    }
    /**
     * Determines whether the foundry can start melting the current input stack.
     *
     * @return `true` if the input slot contains `ModItems.STEEL` and increasing `metalLevel` by 90 would not exceed `maxMetalLevel`, `false` otherwise.
     */

    private boolean canMelt() {
        ItemStack input = inventory.get(INPUT_SLOT);
        if (input.is(ModItems.STEEL)) {
            return steelLevel + 100 <= maxMetalLevel;
        }
        if (input.is(ModItems.METEORIC_IRON_INGOT)) {
            return meteoricIronLevel + 100 <= maxMetalLevel;
        }
        return false;
    }

    /**
     * Determines whether the foundry can start a casting operation with the current cast, metal level, and available output/cooling space.
     *
     * The check requires a non-empty cast, at least 90 metal units, a valid cast result, the cooling slot to be empty,
     * and the output slot to be empty or contain the same item with enough space for the produced stack.
     *
     * @return `true` if casting can proceed, `false` otherwise.
     */
    private boolean canCast() {
        ItemStack cast = inventory.get(CAST_SLOT);
        ItemStack output = inventory.get(OUTPUT_SLOT);
        if (cast.isEmpty()) return false;

        // Check if the active metal has enough for casting
        int activeLevel = activeMetalType == METAL_STEEL ? steelLevel : meteoricIronLevel;
        if (activeLevel < 100) return false;

        ItemStack expectedOutput = getResultFromCast(cast);
        if (expectedOutput.isEmpty()) return false;

        return inventory.get(COOLING_SLOT).isEmpty() && (output.isEmpty() || (output.is(expectedOutput.getItem())
        && output.getCount() + expectedOutput.getCount() <= output.getMaxStackSize()));
    }

    /**
     * Determines whether the cooled item can be transferred from the cooling slot into the output slot.
     *
     * @return `true` if the output slot is empty or already contains the same item and has enough remaining space to accommodate the cooled item's entire stack, `false` otherwise.
     */
    private boolean canFinishCooling() {
        ItemStack coolingItem = inventory.get(COOLING_SLOT);
        ItemStack output = inventory.get(OUTPUT_SLOT);
        return output.isEmpty() || (output.is(coolingItem.getItem()) && output.getCount() +
                coolingItem.getCount() <= output.getMaxStackSize());
    }

    /**
     * Determine the produced output for a given cast item used in the foundry.
     *
     * @param cast the cast ItemStack to evaluate
     * @return the resulting ItemStack for the cast (a pickaxe head) or `ItemStack.EMPTY` if no match
     */
    private ItemStack getResultFromCast(ItemStack cast) {
        // Map cast + active metal type to the correct output
        if (activeMetalType == METAL_STEEL) {
            // Tool heads
            if (cast.is(ModItems.PICKAXE_HEAD_CAST)) return new ItemStack(ModItems.CS_PICKAXE_HEAD);
            if (cast.is(ModItems.AXE_HEAD_CAST)) return new ItemStack(ModItems.CS_AXE_HEAD);
            if (cast.is(ModItems.SHOVEL_HEAD_CAST)) return new ItemStack(ModItems.CS_SHOVEL_HEAD);
            if (cast.is(ModItems.SWORD_HEAD_CAST)) return new ItemStack(ModItems.CS_SWORD_HEAD);
            if (cast.is(ModItems.HOE_HEAD_CAST)) return new ItemStack(ModItems.CS_HOE_HEAD);
            if (cast.is(ModItems.SPEAR_HEAD_CAST)) return new ItemStack(ModItems.CS_SPEAR_HEAD);
            // Armor
            if (cast.is(ModItems.HELMET_CAST)) return new ItemStack(ModItems.CAST_STEEL_HELMET);
            if (cast.is(ModItems.CHESTPLATE_CAST)) return new ItemStack(ModItems.CAST_STEEL_CHESTPLATE);
            if (cast.is(ModItems.LEGGINGS_CAST)) return new ItemStack(ModItems.CAST_STEEL_LEGGINGS);
            if (cast.is(ModItems.BOOTS_CAST)) return new ItemStack(ModItems.CAST_STEEL_BOOTS);
        } else if (activeMetalType == METAL_METEORIC_IRON) {
            // Tool heads
            if (cast.is(ModItems.PICKAXE_HEAD_CAST)) return new ItemStack(ModItems.MI_PICKAXE_HEAD);
            if (cast.is(ModItems.AXE_HEAD_CAST)) return new ItemStack(ModItems.MI_AXE_HEAD);
            if (cast.is(ModItems.SHOVEL_HEAD_CAST)) return new ItemStack(ModItems.MI_SHOVEL_HEAD);
            if (cast.is(ModItems.SWORD_HEAD_CAST)) return new ItemStack(ModItems.MI_SWORD_HEAD);
            if (cast.is(ModItems.HOE_HEAD_CAST)) return new ItemStack(ModItems.MI_HOE_HEAD);
            if (cast.is(ModItems.SPEAR_HEAD_CAST)) return new ItemStack(ModItems.MI_SPEAR_HEAD);
            // Armor
            if (cast.is(ModItems.HELMET_CAST)) return new ItemStack(ModItems.METEORIC_IRON_HELMET);
            if (cast.is(ModItems.CHESTPLATE_CAST)) return new ItemStack(ModItems.METEORIC_IRON_CHESTPLATE);
            if (cast.is(ModItems.LEGGINGS_CAST)) return new ItemStack(ModItems.METEORIC_IRON_LEGGINGS);
            if (cast.is(ModItems.BOOTS_CAST)) return new ItemStack(ModItems.METEORIC_IRON_BOOTS);
        }
        return ItemStack.EMPTY;
    }

    // Cycle through available metal types (called from screen handler on button click)
    public void cycleActiveMetalType() {
        activeMetalType = (activeMetalType + 1) % 2;
        setChanged();
    }

    /**
     * Finds the first foundry recipe that matches the block entity's current input.
     *
     * Uses the server world's recipe manager with a FoundryRecipeCastInput constructed from the inventory's first slot.
     *
     * @return an Optional containing the first matching RecipeEntry<FoundryRecipe>, or empty if no match is found
     */

    private Optional<RecipeHolder<FoundryMeltingRecipe>> getCurrentMeltingRecipe() {
        Level level = this.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return Optional.empty();
        }
        return serverLevel.recipeAccess()
                .getRecipeFor(ModTypes.MELTING_FOUNDRY_TYPE, new FoundryRecipeInput(inventory.getFirst()), level);

    }

    private Optional<RecipeHolder<FoundryCastingRecipe>> getCurrentCastingRecipe() {
        Level level = this.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return Optional.empty();
        }
        return serverLevel.recipeAccess()
                .getRecipeFor(ModTypes.CASTING_FOUNDRY_TYPE, new FoundryRecipeCastInput(inventory.getFirst()), level);

    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return side == Direction.DOWN ? new int[]{OUTPUT_SLOT} : new int[]{INPUT_SLOT, FUEL_SLOT};
    }

    /**
     * Determines whether the given item stack may be inserted into the specified slot from the given side.
     *
     * For the fuel slot, accepts items that provide fuel time (> 0). For the input slot, accepts items that match
     * a foundry recipe input. All other slots reject insertion.
     *
     * @param slot  the target slot index (e.g., INPUT_SLOT, FUEL_SLOT, etc.)
     * @param stack the item stack proposed for insertion
     * @param side  the direction from which insertion is attempted, or null if not side-specific
     * @return      `true` if the stack may be inserted into the slot from the specified side, `false` otherwise
     */
    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @org.jetbrains.annotations.Nullable Direction side) {
        if (slot == FUEL_SLOT) return getFuelTime(stack) > 0;
        if (slot == INPUT_SLOT) {
            Level level = getLevel();
            if (level instanceof ServerLevel serverLevel) {
                return serverLevel.recipeAccess()
                        .getRecipeFor(ModTypes.MELTING_FOUNDRY_TYPE, new FoundryRecipeInput(stack), level).isPresent();

            }
        }
        if (slot == CAST_SLOT) {
            Level level = getLevel();
            if (level instanceof ServerLevel serverLevel) {
                return serverLevel.recipeAccess()
                    .getRecipeFor(ModTypes.CASTING_FOUNDRY_TYPE, new FoundryRecipeCastInput(stack), level).isPresent();
            }
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return slot == OUTPUT_SLOT;
    }

    /**
     * Creates the network packet that synchronizes this block entity's state to clients.
     *
     * @return the BlockEntity update packet containing this entity's NBT, or `null` if no packet should be sent
     */
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    /**
     * Produce the block entity's initial NBT used when the chunk is sent to clients.
     *
     * @param registries a registry lookup used when constructing the NBT representation
     * @return an NbtCompound containing this block entity's initial state for chunk synchronization
     */
    //@Override
    //public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
    //    return createNbt(registries);
    //}

    /**
     * Determines whether the given player may interact with this block entity based on proximity.
     *
     * @param player the player to check
     * @return `true` if the player's squared distance to the block's center is less than or equal to 64, `false` otherwise
     */
    @Override
    public boolean stillValid(Player player) {
        return level != null
                && level.getBlockEntity(worldPosition) == this
                && player.distanceToSqr(
                worldPosition.getX() + 0.5,
                worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5
        ) <= 64;
    }

    /**
     * Removes all items from the block entity's inventory and marks the block entity as dirty so the change is persisted and synchronized.
     */
    @Override
    public void clearContent() {
        inventory.clear();
        setChanged();
    }
}