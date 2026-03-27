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
     * Create a FoundryScreenHandler for the player's inventory bound to the foundry at the given block position.
     *
     * Attaches a new ArrayPropertyDelegate of size 12 and resolves the block entity at the provided position to pass
     * to the primary constructor.
     *
     * @param syncId the synchronization id for this screen handler
     * @param inventory the player's inventory
     * @param pos the world position of the foundry block
     * @throws IllegalStateException if the block entity at the given position is not a FoundryBlockEntity
     */
    public FoundryScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(13));
    }

    /**
     * Construct a FoundryScreenHandler, configure its inventory slots (input, fuel, cast, output, and a disabled slot),
     * register player inventory/hotbar slots, and attach the provided PropertyDelegate for GUI synchronization.
     *
     * The provided {@code blockEntity} must be a {@link FoundryBlockEntity} whose inventory size is 5.
     *
     * @param syncId                window sync id assigned by the client/server
     * @param playerInventory       the player's inventory used to populate player slots and hotbar
     * @param blockEntity           the backing block entity; must be a {@code FoundryBlockEntity} with an inventory size of 5
     * @param arrayPropertyDelegate the PropertyDelegate used to synchronize melt, fuel, metal level, cast, and cooling GUI properties
     * @throws IllegalStateException if {@code blockEntity} is not a {@code FoundryBlockEntity}
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
                // Locked while casting or cooling
                return propertyDelegate.get(6) == 0 && propertyDelegate.get(9) == 0;
            }
        });
        this.addSlot(new Slot(inventory, 3, 135, 53) { //Output Slot
            /**
             * Prevents manual insertion into this output slot.
             *
             * @param player the player attempting to take items from the slot
             * @return `true` if cooling is not active and the player may take items, `false` otherwise
             */
            @Override
            public boolean canTakeItems(PlayerEntity player) {
                return propertyDelegate.get(9) == 0;
            }
        });
        this.addSlot(new Slot(inventory, 3, 135, 53) { //Output Slot
            /**
             * Prevents any ItemStack from being inserted into this slot.
             *
             * @param stack the ItemStack being offered for insertion (ignored)
             * @return `false` always, indicating insertion is disallowed
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

    /**
     * Compute the fuel progress scaled to a 14-pixel width for rendering the fuel bar in the GUI.
     *
     * @return `0` if the maximum fuel time is less than or equal to zero or the current fuel time is less than or equal to zero; otherwise the current fuel time scaled to fit a 14-pixel-wide progress bar.
     */
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

    /**
     * Checks whether the given player may interact with this container.
     *
     * @param player the player attempting to use the container
     * @return `true` if the player is allowed to use the underlying inventory, `false` otherwise
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    // Handle button clicks from the client (metal type cycling)
    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 0) {
            // Block switching while casting or cooling is in progress
            if (propertyDelegate.get(6) > 0 || propertyDelegate.get(9) > 0) return false;
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