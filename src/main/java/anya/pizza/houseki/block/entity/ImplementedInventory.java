package anya.pizza.houseki.block.entity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;

/**
 * A simple {@code SidedInventory} implementation with only default methods + an item list getter.
 *
 * <h2>Reading and writing to tags</h2>
 * Use {@link ContainerHelper#saveAllItems(ValueOutput, NonNullList)} and
 * {@link ContainerHelper#loadAllItems(ValueInput, NonNullList)}
 * on {@linkplain #getItems() the item list}.
 *
 * License: <a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0</a>
 * @author Juuz
 */
@FunctionalInterface
public interface ImplementedInventory extends WorldlyContainer {

    NonNullList<ItemStack> getItems();

    static ImplementedInventory of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    static ImplementedInventory ofSize(int size) {
        return of(NonNullList.withSize(size, ItemStack.EMPTY));
    }

    // SidedInventory

    @Override
    default int @NonNull [] getSlotsForFace(@NonNull Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    default boolean canPlaceItemThroughFace(int slot,@NonNull ItemStack stack, @Nullable Direction side) {
        return true;
    }

    @Override
    default boolean canTakeItemThroughFace(int slot, @NonNull ItemStack stack, @NonNull Direction side) {
        return true;
    }

    // Inventory

    @Override
    default int getContainerSize() {
        return getItems().size();
    }

    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    default @NonNull ItemStack getItem(int slot) {
        return getItems().get(slot);
    }

    @Override
    default @NonNull ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(getItems(), slot, count);
        if (!result.isEmpty()) {
            setChanged();
        }

        return result;
    }

    @Override
    default @NonNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    @Override
    default void setItem(int slot, @NonNull ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
    }

    @Override
    default void clearContent() {
        getItems().clear();
    }

    @Override
    default void setChanged() {
        // Override if you want behavior.
    }

    @Override
    default boolean stillValid(@NonNull Player player) {
        return true;
    }
}