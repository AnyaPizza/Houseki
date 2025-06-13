package nanami.pizza.pizzasoat.block.entity.custom;

import nanami.pizza.pizzasoat.block.custom.CrusherBlock;
import nanami.pizza.pizzasoat.block.entity.ImplementedInventory;
import nanami.pizza.pizzasoat.block.entity.ModBlockEntities;
import nanami.pizza.pizzasoat.item.ModItems;
import nanami.pizza.pizzasoat.recipe.CrusherRecipe;
import nanami.pizza.pizzasoat.recipe.CrusherRecipeInput;
import nanami.pizza.pizzasoat.recipe.ModRecipes;
import nanami.pizza.pizzasoat.screen.custom.CrusherScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
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
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

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
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.pizzasoat.crusher");
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
            //world.setBlockState(pos, state.with(CrusherBlock.LIT, true));
            dirty = true;
            if (progress >= maxProgress) {
                craftItem();
                progress = 0;
            }
        } /*else {
            world.setBlockState(pos, state.with(CrusherBlock.LIT, false));
            if (fuelTime <= 0 && progress > 0) {
                progress = 0;
                dirty = true;
            }
        }*/

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

        ItemStack output = recipe.get().value().getResult(null);
        ItemStack outputSlot = inventory.get(OUTPUT_SLOT);
        return (outputSlot.isEmpty() || outputSlot.isOf(output.getItem()))
                && outputSlot.getCount() + output.getCount() <= outputSlot.getMaxCount();
    }

    private void craftItem() {
        Optional<RecipeEntry<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        inventory.get(INPUT_SLOT).decrement(1);
        ItemStack outputSlot = inventory.get(OUTPUT_SLOT);
        ItemStack result = recipe.get().value().output().copy();
        if (outputSlot.isEmpty()) {
            inventory.set(OUTPUT_SLOT, result);
        } else {
            outputSlot.increment(result.getCount());
        }
    }

    /*private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<CrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        this.removeStack(INPUT_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().output().getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + recipe.get().value().output().getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }*/

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
        return slot == OUTPUT_SLOT;
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
