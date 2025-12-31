package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.block.entity.custom.CrusherBlockEntity;
import anya.pizza.houseki.recipe.CrusherRecipeInput;
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

public class CrusherScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final CrusherBlockEntity blockEntity;

    public CrusherScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.getEntityWorld().getBlockEntity(pos), new ArrayPropertyDelegate(5));
    }

    public CrusherScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 3);
        this.inventory = (Inventory) blockEntity;
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = (CrusherBlockEntity) blockEntity;
        this.addSlot(new Slot(inventory, 0, 35, -5));
        this.addSlot(new Slot(inventory, 1, 13, 41));
        this.addSlot(new Slot(inventory, 2, 116, 30) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false; //Makes output slot read-only
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(arrayPropertyDelegate);
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
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack movedStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            movedStack = originalStack.copy();
            if (invSlot < inventory.size()) {
                if (!insertItem(originalStack,
                        inventory.size(),
                        slots.size(),
                        true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (blockEntity.getFuelTime(originalStack) > 0) {
                    if (!insertItem(originalStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (blockEntity.getWorld() instanceof ServerWorld serverWorld) {
                    CrusherRecipeInput recipeInput = new CrusherRecipeInput(originalStack);
                    boolean hasCrusherRecipe = serverWorld.getRecipeManager()
                            .getFirstMatch(ModRecipes.CRUSHER_TYPE, recipeInput, serverWorld)
                            .isPresent();
                    if (hasCrusherRecipe) {
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
        return movedStack;
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
