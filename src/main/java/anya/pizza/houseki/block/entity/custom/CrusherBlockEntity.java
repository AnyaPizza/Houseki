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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrusherBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int AUXILARY_OUTPUT_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = CrusherRecipe.DEFAULT_CRUSHING_TIME;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private final int lastValidFuelTime = 0;
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
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
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
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("progress", progress);
        nbt.putInt("max_progress", maxProgress);
        nbt.putInt("fuel_time", fuelTime);
        nbt.putInt("max_fuel_time", maxFuelTime);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("progress");
        maxProgress = nbt.getInt("max_progress");
        fuelTime = nbt.getInt("fuel_time");
        maxFuelTime = nbt.getInt("max_fuel_time");
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;

        boolean dirty = false;
        ItemStack input = inventory.get(INPUT_SLOT);

        if(!ItemStack.areItemsAndComponentsEqual(input, lastInput)) {
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
                fuelStack.decrement(1);
                dirty = true;
            }
        }

        //Handle Crushing
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
        ItemStack auxiliary = crusherRecipe.getAuxiliaryOutput().orElse(ItemStack.EMPTY);

        return canInsertIntoSlot(OUTPUT_SLOT, output) && canInsertIntoSlot(AUXILARY_OUTPUT_SLOT, auxiliary);
    }

    private boolean canInsertIntoSlot(int slot, ItemStack stack) {
        if (stack.isEmpty()) return true;
        ItemStack slotStack = inventory.get(slot);
        int maxCount = slotStack.isEmpty() ? stack.getMaxCount() : slotStack.getMaxCount();
        return (slotStack.isEmpty() || ItemStack.areItemsAndComponentsEqual(slotStack, stack))
            && slotStack.getCount() + stack.getCount() <= maxCount;
    }

    private void craftItem() {
        Optional<RecipeEntry<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        CrusherRecipe crusherRecipe = recipe.get().value();

        // Handle Main Output
        insertOrIncrement(OUTPUT_SLOT, crusherRecipe.output().copy());
        
        // Handle Auxiliary Output
        crusherRecipe.getAuxiliaryOutput().ifPresent(stack -> {
            insertOrIncrement(AUXILARY_OUTPUT_SLOT, stack.copy());
        });

        inventory.get(INPUT_SLOT).decrement(1);
    }

    private void insertOrIncrement(int slot, ItemStack result) {
        if (result.isEmpty()) return;
        ItemStack slotStack = inventory.get(slot);
        if (slotStack.isEmpty()) {
            inventory.set(slot, result);
        } else {
            slotStack.increment(result.getCount());
        }
    }

    private Optional<RecipeEntry<CrusherRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager().getFirstMatch(ModRecipes.CRUSHER_TYPE, new CrusherRecipeInput(inventory.get(INPUT_SLOT)), this.getWorld());

    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return side == Direction.DOWN ? new int[]{OUTPUT_SLOT} : new int[]{INPUT_SLOT, FUEL_SLOT};
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (slot == FUEL_SLOT) return getFuelTime(stack) > 0;
        if (slot == INPUT_SLOT) return world.getRecipeManager().getFirstMatch(
                ModRecipes.CRUSHER_TYPE, new CrusherRecipeInput(stack), world).isPresent();
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return slot == OUTPUT_SLOT || slot == AUXILARY_OUTPUT_SLOT;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(player.getPos(), 4.5);
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }
}
