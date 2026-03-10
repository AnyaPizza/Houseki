package anya.pizza.houseki.block.entity.custom;

import anya.pizza.houseki.block.custom.FoundryBlock;
import anya.pizza.houseki.block.entity.ImplementedInventory;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.FoundryRecipe;
import anya.pizza.houseki.recipe.FoundryRecipeCastInput;
import anya.pizza.houseki.recipe.ModRecipes;
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
    private static final int CAST_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;
    protected final PropertyDelegate propertyDelegate;
    private int meltProgress = 0;
    private int maxMeltProgress = FoundryRecipe.DEFAULT_MELT_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private int metalLevel = 0;
    private int maxMetalLevel = 1000; //100 = 1 ingot, holds 10 ingots total
    private int castProgress = 0;
    private int maxCastProgress = FoundryRecipe.DEFAULT_CAST_TIME;
    private int lastValidFuelTime = 0;
    private boolean isCrafting = false;
    private ItemStack lastInput = ItemStack.EMPTY; //Cache input to detect changes

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
                    case 7 -> maxCastProgress;
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
                    case 7 -> maxCastProgress = value;
                }
            }

            @Override
            public int size() {
                return 9;
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
        view.putInt("progress", meltProgress);
        view.putInt("max_progress", maxMeltProgress);
        view.putInt("fuel_time", fuelTime);
        view.putInt("max_fuel_time", maxFuelTime);
        view.putInt("metal_level", metalLevel);
        view.putInt("cast_time", castProgress);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        meltProgress = view.getInt("melt_progress", 0);
        maxMeltProgress = view.getInt("max_melt_progress", 0);
        fuelTime = view.getInt("fuel_time", 0);
        maxFuelTime = view.getInt("max_fuel_time", 0);
        metalLevel = view.getInt("metal_level", 0);
        castProgress = view.getInt("cast_time", 0);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;

        boolean wasBurning = fuelTime > 0;
        boolean dirty = false;

        //Burn Fuel
        if (fuelTime > 0) {
            fuelTime--;
        }

        //Consume new fuel
        if (fuelTime <= 0 && (canMelt() || canCast())) {
            ItemStack fuelStack = getStack(FUEL_SLOT);
            if (!fuelStack.isEmpty()) {
                int burnTime = getFuelTime(fuelStack);
                if (burnTime > 0) {
                    fuelTime = burnTime;
                    maxFuelTime = fuelTime;
                    fuelStack.decrement(1);
                    dirty = true;
                }
            }
        }

        boolean isBurning = fuelTime > 0;

        //Melting
        if (isBurning && canMelt()) {
            meltProgress++;
            if (meltProgress >= maxMeltProgress) {
                getStack(INPUT_SLOT).decrement(1);
                metalLevel += 90;
                meltProgress = 0;
            }
            dirty = true;
        } else {
            if (meltProgress > 0) {
                meltProgress = Math.max(0, meltProgress - 2); //Cool down
                dirty = true;
            }
        }

        if (isBurning && canCast()) {
            castProgress++;
            if (castProgress >= maxCastProgress) {
                craftItem();
                castProgress = 0;
            }
            dirty = true;
        } else {
            if (castProgress > 0) {
                castProgress = Math.max(0, castProgress - 2);
                dirty = true;
            }
        }

        if (wasBurning != (fuelTime > 0)) {
            world.setBlockState(pos, state.with(FoundryBlock.LIT, fuelTime > 0), 3);
            dirty = true;
        }

        if (dirty) {
            markDirty(world, pos, state);
        }
    }
        /*if (world.isClient()) return;
        boolean dirty = false;
        ItemStack input = inventory.getFirst();
        if(!ItemStack.areItemsAndComponentsEqual(input, lastInput)) {
            lastInput = input.copy();
            updateMaxProgress(world);
            if (meltProgress > 0 && !canCraft()) {
                meltProgress = 0;
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

        //Handles Casting
        boolean canCraftNow = fuelTime > 0 && canCraft();
        isCrafting = canCraftNow || (fuelTime > 0 && meltProgress > 0);
        world.setBlockState(pos, state.with(FoundryBlock.LIT, fuelTime > 0));
        if (canCraftNow) {
            meltProgress++;
            dirty = true;
            if (meltProgress >= maxMeltProgress) {
                craftItem();
                meltProgress = 0;
            }
        }
        if (dirty) markDirty(world, pos, state);
    }*/

    //private void updateMaxProgress(World world) {
    //    Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
    //    maxProgress = recipe.map(entry -> entry.value().meltTime())
    //            .orElse(FoundryRecipe.DEFAULT_MELT_TIME);
    //}

    private boolean canMelt() {
        ItemStack input = getStack(INPUT_SLOT);
        boolean hasValidInput = input.isOf(ModItems.STEEL);
        boolean hasMeltedMetal = metalLevel + 90 <= maxMetalLevel;
        return hasValidInput && hasMeltedMetal;
    }

    private boolean canCast() {
        ItemStack cast = getStack(CAST_SLOT);
        ItemStack output = getStack(OUTPUT_SLOT);
        if (cast.isEmpty() || metalLevel < 90) return false;
        ItemStack expectedOutput = getResultFromCast(cast);
        if (expectedOutput.isEmpty()) return false;

        return output.isEmpty() || (output.isOf(expectedOutput.getItem()) &&
                output.getCount() + expectedOutput.getCount() <= output.getMaxCount());
        //Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        //if (recipe.isEmpty()) return false;
        //FoundryRecipe foundryRecipe = recipe.get().value();
        //ItemStack output = foundryRecipe.getResult(null);
        //return canInsertIntoSlot(OUTPUT_SLOT, output);
    }

    /**
     * Determine whether the given ItemStack can be placed into the specified inventory slot
     * without violating item compatibility or stack size limits.
     *
     * @param slot  index of the target slot in the block entity's inventory
     * @param stack the ItemStack intended for insertion; an empty stack is considered insertable
     * @return      `true` if the slot can accept the stack (slot empty or same item/component and total count does not exceed the slot's max), `false` otherwise
     */
    //private boolean canInsertIntoSlot(int slot, ItemStack stack) {
    //    if (stack.isEmpty()) return true;
    //    ItemStack slotStack = inventory.get(slot);
    //    int maxCount = slotStack.isEmpty() ? stack.getMaxCount() : slotStack.getMaxCount();
    //    return (slotStack.isEmpty() || ItemStack.areItemsAndComponentsEqual(slotStack, stack))
    //            && slotStack.getCount() + stack.getCount() <= maxCount;
    //}

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
        ItemStack cast = getStack(CAST_SLOT);
        ItemStack output = getResultFromCast(cast);
        metalLevel -= 90;
        ItemStack currentOutput = getStack(OUTPUT_SLOT);
        if (currentOutput.isEmpty()) {
            setStack(OUTPUT_SLOT, output.copy());
        } else {
            currentOutput.increment(output.getCount());
        }
        //Optional<RecipeEntry<FoundryRecipe>> recipe = getCurrentRecipe();
        //if (recipe.isEmpty()) return;
        //FoundryRecipe foundryRecipe = recipe.get().value();
        // Handles Main Output
        //insertOrIncrement(OUTPUT_SLOT, foundryRecipe.getResult(null).copy(), 1.0);
        //inventory.get(INPUT_SLOT).decrement(1);
    }

    private ItemStack getResultFromCast(ItemStack cast) {
        if (cast.isOf(ModItems.PICKAXE_HEAD_CAST)) {
            return new ItemStack(ModItems.CS_PICKAXE_HEAD);
        }
        return ItemStack.EMPTY;
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
    //private void insertOrIncrement(int slot, ItemStack result, double chance) {
    //    if (result.isEmpty() || Math.random() > chance) return;
    //    ItemStack slotStack = inventory.get(slot);
    //    if (slotStack.isEmpty()) {
    //        inventory.set(slot, result);
    //    } else {
    //        slotStack.increment(result.getCount());
    //    }
    //}

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
        if (slot == INPUT_SLOT) {
            return ((ServerWorld) this.getWorld()).getRecipeManager()
                    .getFirstMatch(ModRecipes.FOUNDRY_TYPE, new FoundryRecipeCastInput(stack), world).isPresent();
        }
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
        return player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64;
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }
}