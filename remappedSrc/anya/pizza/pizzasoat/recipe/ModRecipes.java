package anya.pizza.pizzasoat.recipe;

import anya.pizza.pizzasoat.PizzasOAT;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<CrusherRecipe> CRUSHER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(PizzasOAT.MOD_ID, "crushing"), new CrusherRecipe.Serializer());
    public static final RecipeType<CrusherRecipe> CRUSHER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(PizzasOAT.MOD_ID, "crushing"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "crushing";
                }
            });

    public static void registerRecipes() {
        PizzasOAT.LOGGER.info("Registering Recipes for " + PizzasOAT.MOD_ID);
    }
}
