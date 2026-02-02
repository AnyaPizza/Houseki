package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
import anya.pizza.houseki.recipe.ModTypes;
import anya.pizza.houseki.screen.ModScreenHandlers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class CrusherScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    private final ContainerData propertyDelegate;
    public final CrusherBlockEntity blockEntity;

    public CrusherScreenHandler(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(5));
    }

    /**
     * Creates a Crusher screen handler, initializes the crusher and player inventories, and attaches the provided property delegate for GUI state syncing.
     *
     * @param syncId               window sync id assigned by the client/server
     * @param playerInventory      the player's inventory to populate player slots and hotbar
     * @param entity          the block entity whose inventory backs this handler; must be an Inventory of size 4 and is used as a CrusherBlockEntity
     * @param data the PropertyDelegate used to synchronize progress, fuel, and related GUI properties
     */
    public CrusherScreenHandler(int syncId, Inventory playerInventory, BlockEntity entity, ContainerData data) {
        super(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, syncId);
        this.blockEntity = (entity instanceof CrusherBlockEntity) ? (CrusherBlockEntity) entity : null;
        this.inventory = (entity instanceof Container container) ? container : new SimpleContainer(4);
        checkContainerSize(this.inventory, 4);
        this.propertyDelegate = data;

        this.addSlot(new Slot(inventory, 0, 35, -5)); //Input Slot
        this.addSlot(new Slot(inventory, 1, 13, 41)); //Fuel Slot
        this.addSlot(new Slot(inventory, 2, 115, 30) { //Output Slot
            @Override public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, 3, 137, 30) { //Auxiliary Slot
            @Override public boolean mayPlace(ItemStack stack) {
                return false; //Makes output slot read-only
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addDataSlots(data);
    }

    public boolean isBurning() {
        return propertyDelegate.get(2) > 0;
    }

    public boolean isCrafting() {
        return propertyDelegate.get(4) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int arrowPixelSize = 24;

        return maxProgress > 0 && progress > 0 ? (progress * arrowPixelSize) / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int fuelTime = propertyDelegate.get(2);
        int maxFuelTime = propertyDelegate.get(3);
        int crushingPixelSize = 20;
        return maxFuelTime > 0 && fuelTime > 0 ? (fuelTime * crushingPixelSize) / maxFuelTime : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (invSlot < inventory.getContainerSize()) {
                if (!moveItemStackTo(originalStack, inventory.getContainerSize(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (blockEntity != null && blockEntity.getFuelTime(originalStack) > 0) {
                    if (!moveItemStackTo(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (player.level() instanceof ServerLevel serverWorld) {
                    CrusherRecipeInput recipeInput = new CrusherRecipeInput(originalStack);
                    boolean hasCrusherRecipe = serverWorld.recipeAccess()
                            .getRecipeFor(ModTypes.CRUSHER_TYPE, recipeInput, serverWorld)
                            .isPresent();
                    if (hasCrusherRecipe) {
                        if (!moveItemStackTo(originalStack, 0, 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            }
            if (originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public ContainerData getPropertyDelegate() {
        return propertyDelegate;
    }
}