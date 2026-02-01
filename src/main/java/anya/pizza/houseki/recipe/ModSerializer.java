package anya.pizza.houseki.recipe;

import anya.pizza.houseki.Houseki;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class ModSerializer {
    public static final RecipeSerializer<CrusherRecipe> CRUSHER_SERIALIZER = register("crusher", CrusherRecipe.Serializer.INSTANCE);


    /**
     * Registers a recipe serializer under this mod's namespace and returns the registered serializer.
     *
     * @param name the path portion of the identifier to register the serializer under (namespace is Houseki.MOD_ID)
     * @param serializer the recipe serializer to register
     * @param <T> the recipe type handled by the serializer
     * @return the registered {@code RecipeSerializer<T>}
     */
    public static <T extends Recipe<?>> RecipeSerializer<T> register(String name, RecipeSerializer<T> serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name), serializer);
    }

    /**
     * Registers this mod's recipe serializers.
     *
     * <p>Ensures recipe serializer fields are initialized and emits an informational log entry
     * indicating that recipe registration for the mod is occurring.</p>
     */
    public static void registerRecipes() {
        Houseki.LOGGER.info("Registering Recipes for " + Houseki.MOD_ID);
    }
}