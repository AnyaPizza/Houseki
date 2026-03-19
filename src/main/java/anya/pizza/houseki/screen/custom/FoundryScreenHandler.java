package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import anya.pizza.houseki.recipe.FoundryRecipeCastInput;
import anya.pizza.houseki.recipe.ModRecipes;
import anya.pizza.houseki.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
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
        this(syncId, inventory, inventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(12));
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
    public int getMetalLevel() { return this.propertyDelegate.get(4); }
    public int getMaxMetalLevel() { return this.propertyDelegate.get(5); }
    public int getCastProgress() { return this.propertyDelegate.get(6); }
    public int getMaxCastTime() { return this.propertyDelegate.get(7); }
    public int getCoolingProgress() { return this.propertyDelegate.get(8); }
    public int getMaxCoolingProgress() { return this.propertyDelegate.get(9); }
    public boolean isBurning() { return this.propertyDelegate.get(2) > 0; }
    public boolean isCrafting() { return propertyDelegate.get(4) > 0; }

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
        int progressPixelSize = 20;
        return maxFuelTime > 0 && fuelTime > 0 ? (fuelTime * progressPixelSize) / maxFuelTime : 0;
    }

    /**
     * Moves items between the player inventory and the foundry inventory for a shift-clicked slot.
     *
     * Attempts to transfer the stack from a foundry slot into the player inventory, or from the
     * player inventory into the appropriate foundry slot (fuel slot if the item is fuel, input
     * slots if a matching foundry recipe exists). If the transfer cannot be completed the method
     * leaves inventories unchanged for that operation.
     *
     * @param player  the player performing the quick-move action
     * @param invSlot the index of the clicked slot
     * @return a copy of the moved ItemStack, or ItemStack.EMPTY if no transfer occurred
     */
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < inventory.size()) {
                if (!insertItem(originalStack, inventory.size(), slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (blockEntity.getFuelTime(originalStack) > 0) {
                    if (!insertItem(originalStack, 1, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (blockEntity.getWorld() instanceof ServerWorld serverWorld) {
                    FoundryRecipeCastInput recipeCastInput = new FoundryRecipeCastInput(originalStack);
                    boolean hasFoundryRecipe = serverWorld.getRecipeManager()
                            .getFirstMatch(ModRecipes.FOUNDRY_TYPE, recipeCastInput, serverWorld)
                            .isPresent();
                    if (hasFoundryRecipe) {
                        if (!insertItem(originalStack, 0, 2, false)) {
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
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
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