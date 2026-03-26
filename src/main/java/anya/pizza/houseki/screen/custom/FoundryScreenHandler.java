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
        this(syncId, inventory, inventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(12));
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
        this.addSlot(new Slot(inventory, 2, 134, 18) { /**
             * Allows a player to take items from the cast slot only when the foundry reports no active cooling.
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
            /**
             * Determine whether the specified player is allowed to take items from this slot.
             *
             * @param playerEntity the player attempting to take items
             * @return `true` if the player may take items from this slot, `false` otherwise
             */
            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return false;
            }
        });
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(arrayPropertyDelegate);
    }

    /**
 * Retrieves the current melt progress used by the GUI.
 *
 * @return the current melt progress value
 */
public int getMeltProgress() { return this.propertyDelegate.get(0); }
    /**
 * Maximum melt progress required to complete a melt operation.
 *
 * @return the maximum melt progress value used to determine when melting is complete
 */
public int getMaxMeltProgress() { return this.propertyDelegate.get(1); }
    /**
 * Gets the current fuel time value for the foundry.
 *
 * @return the current fuel time value from the handler's property delegate
 */
public int getFuelTime() { return this.propertyDelegate.get(2); }
    /**
 * Gets the maximum fuel time for the foundry's current fuel.
 *
 * @return the maximum fuel time in ticks
 */
public int getMaxFuelTime() { return this.propertyDelegate.get(3); }
    /**
 * Gets the current metal level in the foundry.
 *
 * @return the current metal level
 */
public int getMetalLevel() { return this.propertyDelegate.get(4); }
    /**
 * Gets the maximum metal level the foundry can hold.
 *
 * @return the maximum metal level as an integer
 */
public int getMaxMetalLevel() { return this.propertyDelegate.get(5); }
    /**
 * Gets the current cast progress for the foundry UI.
 *
 * @return the current cast progress value.
 */
public int getCastProgress() { return this.propertyDelegate.get(6); }
    /**
 * Gets the maximum cast time for the current casting operation.
 *
 * @return the maximum cast time in ticks for casting
 */
public int getMaxCastTime() { return this.propertyDelegate.get(7); }
    /**
 * Gets the current cooling progress for the foundry GUI.
 *
 * @return the current cooling progress value used by the GUI
 */
public int getCoolingProgress() { return this.propertyDelegate.get(8); }
    /**
 * Provide the maximum cooling progress used by the cooling progress indicator.
 *
 * @return the maximum cooling progress value
 */
public int getMaxCoolingProgress() { return this.propertyDelegate.get(9); }
    /**
 * Determines whether the foundry is currently burning fuel.
 *
 * @return `true` if the foundry has remaining fuel time (> 0), `false` otherwise.
 */
public boolean isBurning() { return this.propertyDelegate.get(2) > 0; }
    /**
 * Determines whether the foundry is currently crafting.
 *
 * @return `true` if the foundry is currently crafting, `false` otherwise.
 */
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