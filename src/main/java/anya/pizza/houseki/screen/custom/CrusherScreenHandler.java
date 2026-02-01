package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
import anya.pizza.houseki.recipe.ModTypes;
import anya.pizza.houseki.screen.ModScreenHandlers;
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

    /**
     * Creates a CrusherScreenHandler for the crusher block at the given world position using the provided player inventory.
     *
     * @param syncId synchronization id for the container
     * @param inventory the player's inventory used to populate player slots
     * @param pos the block position of the crusher block entity to bind to this handler
     */
    public CrusherScreenHandler(int syncId, Inventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.level().getBlockEntity(pos), new SimpleContainerData(5));
    }

    /**
     * Initializes a crusher screen handler with the crusher's inventory, the player's inventory slots, and a property delegate for GUI synchronization.
     *
     * @param syncId the window sync id assigned by the client/server
     * @param playerInventory the player's inventory used to populate player slots and hotbar
     * @param blockEntity the block entity backing this handler; must implement Container with size 4 and is treated as a CrusherBlockEntity
     * @param arrayPropertyDelegate the ContainerData used to synchronize progress, fuel, and related GUI properties
     */
    public CrusherScreenHandler(int syncId, Inventory playerInventory, BlockEntity blockEntity, ContainerData arrayPropertyDelegate) {
        super(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, syncId);
        checkContainerSize((Container) blockEntity, 4);
        this.inventory = (Container) blockEntity;
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = (CrusherBlockEntity) blockEntity;
        this.addSlot(new Slot(inventory, 0, 35, -5)); //Input Slot
        this.addSlot(new Slot(inventory, 1, 13, 41)); //Fuel Slot
        this.addSlot(new Slot(inventory, 2, 115, 30) { /**
             * Prevents any item from being placed into this slot.
             *
             * @param stack the item attempting to be placed into the slot
             * @return false always, indicating insertion is not allowed
             */
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, 3, 137, 30) { /**
             * Prevents any item from being placed into this slot.
             *
             * @param stack the item stack attempted to be placed
             * @return `false` always; this slot is read-only and does not accept items
             */
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false; //Makes output slot read-only
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addDataSlots(arrayPropertyDelegate);
    }

    /**
     * Determines whether the crusher is currently burning fuel.
     *
     * @return `true` if the crusher's remaining burn time is greater than zero, `false` otherwise.
     */
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

    /**
     * Calculates the current fuel progress scaled to a 20-pixel indicator.
     *
     * @return an integer representing fuel progress in pixels (0–20); `0` if there is no fuel or the maximum fuel time is not greater than zero
     */
    public int getScaledFuelProgress() {
        int fuelTime = propertyDelegate.get(2);
        int maxFuelTime = propertyDelegate.get(3);
        int crushingPixelSize = 20;
        return maxFuelTime > 0 && fuelTime > 0 ? (fuelTime * crushingPixelSize) / maxFuelTime : 0;
    }

    /**
     * Transfers an item stack between the crusher inventory and the player's inventory in response to a quick-move (shift-click).
     *
     * Attempts to move a stack from the crusher to the player's inventory when the clicked slot is within the crusher's container range.
     * When the clicked slot is in the player inventory, attempts to move the stack into the fuel slot if it is valid fuel, or into the input slot if a crusher recipe exists for it; fails and returns empty if neither destination is valid or the server recipe lookup is unavailable.
     *
     * @param invSlot the index of the clicked slot within this menu's slot list
     * @return the stack that was moved, or `ItemStack.EMPTY` if no move occurred
     */
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
                if (blockEntity.getFuelTime(originalStack) > 0) {
                    if (!moveItemStackTo(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (blockEntity.getLevel() instanceof ServerLevel serverWorld) {
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

    /**
     * Checks whether the player can still interact with this container.
     *
     * @param player the player to validate
     * @return `true` if the container remains usable by the player, `false` otherwise
     */
    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    /**
     * Adds the player's main inventory (3 rows of 9 slots) to this container at the standard GUI coordinates.
     *
     * @param playerInventory the player's inventory whose slots will be added
     */
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    /**
     * Adds the player's hotbar slots to this container's slot list.
     *
     * @param playerInventory the player's inventory from which hotbar slots are added
     */
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    /**
     * Provides the ContainerData that exposes the screen's synchronized properties (progress, fuel, etc.).
     *
     * @return the ContainerData backing this handler's GUI state
     */
    public ContainerData getPropertyDelegate() {
        return propertyDelegate;
    }
}