package anya.pizza.houseki.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record CrusherRecipeInput(ItemStack input) implements RecipeInput {
    /**
     * Provide the recipe input for a requested slot.
     *
     * @param slot index of the requested input slot; ignored because this input contains exactly one item
     * @return the stored input ItemStack
     */
    @Override
    public ItemStack getItem(int slot) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}