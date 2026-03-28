package anya.pizza.houseki.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record FoundryRecipeInput(ItemStack meltInput) implements RecipeInput {
    public ItemStack getStackInSlot(int castSlot) {
        if (castSlot !=2) {
            return ItemStack.EMPTY;
        }
        return meltInput;
    }

    @Override
    public int size() {
        return 1;
    }
}
