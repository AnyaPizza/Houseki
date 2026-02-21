package anya.pizza.houseki.recipe;

import anya.pizza.houseki.Houseki;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

public class ModTypes {
    public static final RecipeType<CrusherRecipe> CRUSHER_TYPE = register("crushing", CrusherRecipe.Type.INSTANCE);

    public static <T extends Recipe<? extends RecipeInput>> RecipeType<T> register(String name, RecipeType<T> type) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name), type);
    }

    public static void registerRecipeTypes() {
        Houseki.LOGGER.info("Registering RecipeTypes for " + Houseki.MOD_ID);
    }
}