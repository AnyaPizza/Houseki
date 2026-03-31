package anya.pizza.houseki.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record FoundryRecipeCastInput(ItemStack cast) implements RecipeInput {
    @Override
    public ItemStack getItem(int slot) {
        if (slot !=2) {
            throw new IllegalArgumentException("No item at slot " + slot);
        }
        return cast;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.cast.isEmpty();
    }
}
