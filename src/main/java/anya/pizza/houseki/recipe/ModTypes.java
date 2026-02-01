package anya.pizza.houseki.recipe;

import anya.pizza.houseki.Houseki;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ModTypes {
    public static final RecipeType<CrusherRecipe> CRUSHER_TYPE = register("crushing", CrusherRecipe.Type.INSTANCE);

    /**
     * Registers the given recipe type in the built-in recipe registry under this mod's namespace and provided name.
     *
     * @param <T>  the concrete Recipe subtype for the recipe type being registered
     * @param name the registry path segment to use for the recipe type (e.g., "crushing")
     * @param type the RecipeType instance to register
     * @return the registered RecipeType
     */
    public static <T extends Recipe<?>> RecipeType<T> register(String name, RecipeType<T> type) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name), type);
    }
}