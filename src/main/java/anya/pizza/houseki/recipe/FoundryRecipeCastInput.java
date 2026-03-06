package anya.pizza.houseki.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record FoundryRecipeCastInput(ItemStack cast) implements RecipeInput {
    /**
     * Retrieve the ItemStack used as the cast for the recipe input slot.
     *
     * @param slot the slot index (ignored; this input always represents a single slot)
     * @return the stored cast ItemStack
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return cast;
    }

    /**
     * Indicates how many item slots this recipe input exposes.
     *
     * @return 1, indicating a single-slot input
     */
    @Override
    public int size() {
        return 1;
    }
}
