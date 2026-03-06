package anya.pizza.houseki.recipe;

import anya.pizza.houseki.Houseki;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<CrusherRecipe> CRUSHER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Houseki.MOD_ID, "crushing"), new CrusherRecipe.Serializer());
    public static final RecipeType<CrusherRecipe> CRUSHER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Houseki.MOD_ID, "crushing"), new RecipeType<>() {
                /**
                 * Provide a stable textual identifier for this recipe type.
                 *
                 * @return the string "crushing"
                 */
                @Override
                public String toString() {
                    return "crushing";
                }
            });

    public static final RecipeSerializer<FoundryRecipe> FOUNDRY_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Houseki.MOD_ID, "foundry"), new FoundryRecipe.Serializer());
    public static final RecipeType<FoundryRecipe> FOUNDRY_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Houseki.MOD_ID, "foundry"), new RecipeType<>() {
                /**
                 * Provide the string identifier for the foundry recipe type.
                 *
                 * @return {@code "foundry"} — the textual identifier of this recipe type.
                 */
                @Override
                public String toString() {
                    return "foundry";
                }
            });

    /**
     * Registers the mod's recipe serializers and recipe types.
     *
     * <p>Invoking this method records an informational log entry about recipe registration.
     */
    public static void registerRecipes() {
        Houseki.LOGGER.info("Registering Recipes for " + Houseki.MOD_ID);
    }
}
