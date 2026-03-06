package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
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
     * Creates a FoundryScreenHandler for the Foundry block at the given world position.
     *
     * Locates the BlockEntity at {@code pos} in the player's world and delegates to the primary
     * constructor, attaching a new 5-slot ArrayPropertyDelegate for GUI state synchronization.
     *
     * @param syncId synchronization id for the screen handler
     * @param inventory the player's inventory used to build player slots
     * @param pos the block position of the Foundry block entity to bind to this handler
     */
    public FoundryScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(5));
    }

    /**
     * Creates a Foundry screen handler, sets up the Foundry and player inventories, and attaches the property delegate used to synchronize GUI state.
     *
     * Validates that the provided BlockEntity implements Inventory with size 4 and casts it to FoundryBlockEntity before creating slots:
     * input (index 0), fuel (index 1), output (index 2, read-only), and auxiliary (index 3, read-only).
     *
     * @param syncId                window sync id assigned by the client/server
     * @param playerInventory       the player's inventory used to populate player slots and hotbar
     * @param blockEntity           the backing BlockEntity; must implement Inventory of size 4 and is treated as a FoundryBlockEntity
     * @param arrayPropertyDelegate the PropertyDelegate that synchronizes progress, fuel time, and related GUI properties between server and client
     */
    public FoundryScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 4);
        this.inventory = (Inventory) blockEntity;
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = (FoundryBlockEntity) blockEntity;
        this.addSlot(new Slot(inventory, 0, 35, -5)); //Input Slot
        this.addSlot(new Slot(inventory, 1, 13, 41)); //Fuel Slot
        this.addSlot(new Slot(inventory, 2, 115, 30) { /**
             * Disallows inserting any item into this output slot.
             *
             * @return `false` always, preventing the provided `ItemStack` from being inserted
             */
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        this.addSlot(new Slot(inventory, 3, 137, 30) { /**
             * Prevents insertion into this slot, making it read-only.
             *
             * @param stack the item stack attempted to be inserted (ignored)
             * @return `false` always, indicating insertion is disallowed
             */
            @Override
            public boolean canInsert(ItemStack stack) {
                return false; //Makes output slot read-only
            }
        });
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(arrayPropertyDelegate);
    }

    /**
     * Indicates whether the foundry currently has remaining burn time.
     *
     * @return true if the fuel time property (property index 2) is greater than 0, false otherwise.
     */
    public boolean isBurning() {
        return propertyDelegate.get(2) > 0;
    }

    /**
     * Indicates whether the foundry is currently crafting.
     *
     * @return `true` if the craft progress property (index 4) is greater than 0, `false` otherwise.
     */
    public boolean isCrafting() {
        return propertyDelegate.get(4) > 0;
    }

    /**
     * Computes the arrow fill width for the GUI based on current crafting progress.
     *
     * @return the arrow width in pixels (0–24) representing crafting progress; returns 0 if there is no progress or the maximum progress is zero
     */
    public int getScaledArrowProgress() {
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        int arrowPixelSize = 24;

        return maxProgress > 0 && progress > 0 ? (progress * arrowPixelSize) / maxProgress : 0;
    }

    /**
     * Computes the fuel progress for the GUI fuel indicator scaled to 20 pixels.
     *
     * @return `0` if there is no fuel or the maximum fuel time is zero, otherwise the fuel progress scaled to 20 pixels.
     */
    public int getScaledFuelProgress() {
        int fuelTime = propertyDelegate.get(2);
        int maxFuelTime = propertyDelegate.get(3);
        int progressPixelSize = 20;
        return maxFuelTime > 0 && fuelTime > 0 ? (fuelTime * progressPixelSize) / maxFuelTime : 0;
    }

    /**
     * Handles shift-click (quick move) transfers between the player inventory and the Foundry inventory.
     *
     * Attempts to move the stack at the given slot index into the appropriate destination:
     * - from the Foundry inventory to the player inventory, or
     * - from the player inventory into the fuel or input slot when applicable.
     * Updates or clears the source slot after moving.
     *
     * @param player the player performing the quick-move action
     * @param invSlot the index of the clicked slot in this handler's slot list
     * @return the original stack that was moved, or `ItemStack.EMPTY` if no transfer occurred
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
                    if (!insertItem(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (blockEntity.getWorld() instanceof ServerWorld serverWorld) {
                    FoundryRecipeCastInput recipeCastInput = new FoundryRecipeCastInput(originalStack);
                    boolean hasFoundryRecipe = serverWorld.getRecipeManager()
                            .getFirstMatch(ModRecipes.FOUNDRY_TYPE, recipeCastInput, serverWorld)
                            .isPresent();
                    if (hasFoundryRecipe) {
                        if (!insertItem(originalStack, 0, 1, false)) {
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

    /**
     * Determines whether the given player is allowed to interact with this container.
     *
     * @param player the player attempting to use the container
     * @return true if the player may use the container, false otherwise
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    /**
     * Adds the standard 3x9 player inventory grid of slots to this screen handler.
     *
     * @param playerInventory the player's inventory whose 3x9 main inventory slots will be added
     */
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    /**
     * Adds the player's 9-slot hotbar to this screen handler.
     *
     * Adds nine Slot instances for player inventory indices 0–8 arranged horizontally
     * starting at x=8, y=142 with 18 pixels between slots.
     *
     * @param playerInventory the player's inventory supplying hotbar slots 0–8
     */
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    /**
     * Provides the PropertyDelegate that exposes this handler's synchronized GUI properties.
     *
     * @return the attached PropertyDelegate containing the handler's progress and fuel properties
     */
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }
}