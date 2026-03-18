package anya.pizza.houseki.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record FoundryRecipeCastInput(ItemStack cast) implements RecipeInput {
    /**
     * Returns the ItemStack stored at the given slot index; slot 0 contains the cast.
     *
     * @param slot the slot index to query; only slot 0 is valid
     * @return the cast ItemStack when slot is 0, {@link ItemStack#EMPTY} otherwise
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot !=0) {
            return ItemStack.EMPTY;
        }
        return cast;
    }

    @Override
    public int size() {
        return 1;
    }
}
