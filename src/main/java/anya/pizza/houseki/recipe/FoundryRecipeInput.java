package anya.pizza.houseki.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record FoundryRecipeInput(ItemStack meltInput) implements RecipeInput {
    public ItemStack getItem(int meltSlot) {
        if (meltSlot !=0) {
            throw new IllegalArgumentException("No item at slot " + meltInput);
        }
        return meltInput;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
