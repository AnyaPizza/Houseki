package anya.pizza.houseki.recipe;

import anya.pizza.houseki.Houseki;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class ModSerializer {
    public static final RecipeSerializer<CrusherRecipe> CRUSHER_SERIALIZER = register("crusher", CrusherRecipe.Serializer.INSTANCE);


    public static <T extends Recipe<?>> RecipeSerializer<T> register(String name, RecipeSerializer<T> serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name), serializer);
    }

    public static void registerRecipes() {
        Houseki.LOGGER.info("Registering Recipes for " + Houseki.MOD_ID);
    }
}
