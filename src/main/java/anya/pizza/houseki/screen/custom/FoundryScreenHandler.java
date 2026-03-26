package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class FoundryScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final FoundryBlockEntity blockEntity;

    /**
     * Creates a FoundryScreenHandler for the given player inventory and foundry block position.
     *
     * Resolves the block entity at the provided position and attaches a new ArrayPropertyDelegate with nine properties.
     *
     * @param syncId the synchronization id for this screen handler
     * @param inventory the player's inventory
     * @param pos the position of the foundry block
     * @throws IllegalStateException if the block entity at the given position is not a FoundryBlockEntity
     */
    public FoundryScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(13));
    }

    /**
     * Create a Foundry screen handler and configure its inventory slots and GUI property synchronization.
     *
     * The provided BlockEntity must be a FoundryBlockEntity with an inventory size of 4; the handler attaches the given PropertyDelegate to synchronize melt, fuel, metal level, and cast properties.
     *
     * @param syncId                window sync id assigned by the client/server
     * @param playerInventory       the player's inventory used to populate player slots and hotbar
     * @param blockEntity           the backing block entity; must be a FoundryBlockEntity with an inventory size of 4
     * @param arrayPropertyDelegate the PropertyDelegate used to synchronize progress, fuel, metal level, and cast-related GUI properties
     * @throws IllegalStateException if {@code blockEntity} is not a FoundryBlockEntity
     */
    public FoundryScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, syncId);
        if (!(blockEntity instanceof FoundryBlockEntity foundryEntity)) {
            throw new IllegalStateException("Expected FoundryBlockEntity but got " + blockEntity.getClass().getName());
        }
        checkSize(foundryEntity, 5);
        this.inventory = foundryEntity;
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = foundryEntity;
        this.addSlot(new Slot(inventory, 0, 26, 18)); //Input Slot
        this.addSlot(new Slot(inventory, 1, 26, 53)); //Fuel Slot
        this.addSlot(new Slot(inventory, 2, 134, 18) { //Cast Slot
            @Override
            public boolean canTakeItems(PlayerEntity player) {
                return propertyDelegate.get(9) == 0;
            }
        });
        this.addSlot(new Slot(inventory, 3, 135, 53) { //Output Slot
            /**
             * Prevents manual insertion into this output slot.
             *
             * @param stack the item stack attempted to be inserted
             * @return `false` always — items may not be inserted into this slot
             */
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, 4, -1000, -1000) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return false;
            }
        });
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(arrayPropertyDelegate);
    }

    public int getMeltProgress() { return this.propertyDelegate.get(0); }
    public int getMaxMeltProgress() { return this.propertyDelegate.get(1); }
    public int getFuelTime() { return this.propertyDelegate.get(2); }
    public int getMaxFuelTime() { return this.propertyDelegate.get(3); }
    public int getSteelLevel() { return this.propertyDelegate.get(4); }
    public int getMaxMetalLevel() { return this.propertyDelegate.get(5); }
    public int getCastProgress() { return this.propertyDelegate.get(6); }
    public int getMaxCastTime() { return this.propertyDelegate.get(7); }
    public int getCoolingProgress() { return this.propertyDelegate.get(9); }
    public int getMaxCoolingProgress() { return this.propertyDelegate.get(10); }
    public int getMeteoricIronLevel() { return this.propertyDelegate.get(11); }
    public int getActiveMetalType() { return this.propertyDelegate.get(12); }
    public boolean isBurning() { return this.propertyDelegate.get(2) > 0; }
    public boolean isCrafting() { return propertyDelegate.get(8) > 0; }

    /**
     * Computes the horizontal melt progress for the UI arrow, scaled to a 24-pixel width.
     *
     * Uses the internal melt progress and max melt progress properties to calculate the pixel length.
     *
     * @return the number of pixels (0–24) representing current melt progress; 0 if progress is zero or max progress is zero
     */
    public int getScaledArrowProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int arrowPixelSize = 24;

        return maxProgress > 0 && progress > 0 ? (progress * arrowPixelSize) / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int fuelTime = propertyDelegate.get(2);
        int maxFuelTime = propertyDelegate.get(3);
        int progressPixelSize = 14;
        return maxFuelTime > 0 && fuelTime > 0 ? (fuelTime * progressPixelSize) / maxFuelTime : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            itemStack = originalStack.copy();

            if (invSlot < 5) {
                if (!this.insertItem(originalStack, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, itemStack);
            } else {
                // All cast items go to the cast slot
                if (originalStack.isOf(ModItems.PICKAXE_HEAD_CAST) || originalStack.isOf(ModItems.AXE_HEAD_CAST)
                        || originalStack.isOf(ModItems.SHOVEL_HEAD_CAST) || originalStack.isOf(ModItems.SWORD_HEAD_CAST)
                        || originalStack.isOf(ModItems.HOE_HEAD_CAST) || originalStack.isOf(ModItems.SPEAR_HEAD_CAST)) {
                    if (!this.insertItem(originalStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (originalStack.isOf(Items.COAL)) {
                    if (!this.insertItem(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (originalStack.isOf(ModItems.STEEL) || originalStack.isOf(ModItems.METEORIC_IRON_INGOT)) {
                    if (!this.insertItem(originalStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot < 32) {
                    if (!this.insertItem(originalStack, 32, 41, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (invSlot >= 32 && invSlot < 41) {
                    if (!this.insertItem(originalStack, 5, 32, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
        }
        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    // Handle button clicks from the client (metal type cycling)
    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 0) {
            blockEntity.cycleActiveMetalType();
            return true;
        }
        return false;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }
}