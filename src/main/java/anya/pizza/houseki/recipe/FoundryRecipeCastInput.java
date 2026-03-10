package anya.pizza.houseki.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record FoundryRecipeCastInput(ItemStack cast) implements RecipeInput {
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
