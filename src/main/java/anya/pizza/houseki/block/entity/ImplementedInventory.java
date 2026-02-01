package anya.pizza.houseki.block.entity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Container;
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
    /**
 * Provide the inventory's backing list of item stacks.
 *
 * Implementations must return the same NonNullList instance on every call.
 *
 * @return the backing NonNullList of ItemStack instances (the same list instance on every call)
 */
    NonNullList<ItemStack> getItems();

    /**
     * Creates an ImplementedInventory backed by the given item list.
     *
     * The returned inventory's getItems() will return the same supplied NonNullList instance on every call.
     *
     * @param items the backing list of ItemStack instances used as the inventory storage
     * @return an ImplementedInventory backed by the provided item list
     */
    static ImplementedInventory of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    /**
     * Create an ImplementedInventory with the given number of slots.
     *
     * @param size the number of slots in the inventory
     * @return an ImplementedInventory backed by a NonNullList of the given size, with each slot initialized to ItemStack.EMPTY
     */
    static ImplementedInventory ofSize(int size) {
        return of(NonNullList.withSize(size, ItemStack.EMPTY));
    }

    // SidedInventory

    /**
         * Provides the indices of slots accessible from the given side.
         *
         * <p>Default implementation exposes all slots.
         *
         * @param side the side to query for accessible slots
         * @return an array containing every slot index from 0 to (inventory size - 1) that is accessible from the specified side
         */
    @Override
    default int @NonNull [] getSlotsForFace(@NonNull Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    /**
         * Determine whether the given item stack may be inserted into the specified slot from the given side.
         *
         * @param slot the slot index
         * @param stack the item stack to insert (must not be null)
         * @param side the side from which insertion is attempted; may be null
         * @return `true` if the stack can be inserted, `false` otherwise
         */
    @Override
    default boolean canPlaceItemThroughFace(int slot,@NonNull ItemStack stack, @Nullable Direction side) {
        return true;
    }

    /**
     * Determines whether an item stack can be extracted from the specified slot from the given side.
     *
     * @param slot  the slot index to check
     * @param stack the item stack being extracted
     * @param side  the side from which extraction is attempted
     * @return `true` if the stack can be extracted from the slot via the given side, `false` otherwise
     */
    @Override
    default boolean canTakeItemThroughFace(int slot, @NonNull ItemStack stack, @NonNull Direction side) {
        return true;
    }

    // Inventory

    /**
     * Get the number of slots in the inventory.
     *
     * <p>Default implementation returns the size of {@link #getItems()}.
     *
     * @return the number of slots in the inventory
     */
    @Override
    default int getContainerSize() {
        return getItems().size();
    }

    /**
         * Checks whether every slot in the inventory is empty.
         *
         * @return `true` if all slots are empty, `false` otherwise.
         */
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

    /**
         * Retrieves the item stack at the specified inventory slot.
         *
         * @param slot the zero-based slot index
         * @return the `ItemStack` contained in the specified slot; never `null`
         */
    @Override
    default @NonNull ItemStack getItem(int slot) {
        return getItems().get(slot);
    }

    /**
         * Removes up to {@code count} items from the specified slot and returns them as an ItemStack.
         *
         * <p>If the slot contains fewer than {@code count} items, removes and returns all items in that slot.
         *
         * @param slot  the slot index to remove items from
         * @param count the maximum number of items to remove
         * @return the removed ItemStack, or {@link ItemStack#EMPTY} if the slot was empty
         *
         * Calls {@link #setChanged()} if any items were removed.
         */
    @Override
    default @NonNull ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(getItems(), slot, count);
        if (!result.isEmpty()) {
            setChanged();
        }

        return result;
    }

    /**
     * Remove and return the ItemStack at the specified slot without notifying the inventory of a change.
     *
     * @param slot the slot index
     * @return the removed ItemStack, or ItemStack.EMPTY if the slot was empty
     */
    @Override
    default @NonNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     *
     * <p>If the stack is too big for this inventory ({@link Container#getMaxStackSize()}),
     * it gets resized to this inventory's maximum amount.
     *
     * @param slot the slot
     * @param stack the stack
     */
    @Override
    default void setItem(int slot, @NonNull ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
    }

    /**
     * Removes all items from the inventory.
     */
    @Override
    default void clearContent() {
        getItems().clear();
    }

    /**
     * Called to notify that the inventory's contents have changed.
     *
     * Default implementation does nothing; override to perform update actions (for example, mark the container dirty or synchronize state).
     */
    @Override
    default void setChanged() {
        // Override if you want behavior.
    }

    /**
     * Determines whether the given player may interact with this inventory.
     *
     * @param player the player to check access for
     * @return `true` if the player may use the inventory, `false` otherwise
     */
    @Override
    default boolean stillValid(@NonNull Player player) {
        return true;
    }
}