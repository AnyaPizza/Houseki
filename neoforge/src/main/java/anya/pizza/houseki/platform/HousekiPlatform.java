package anya.pizza.houseki.platform;

import anya.pizza.houseki.recipe.FoundryCastingRecipe;
import anya.pizza.houseki.recipe.FoundryRecipeCastInput;
import anya.pizza.houseki.recipe.ModTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.stream.Stream;

public final class HousekiPlatform {
    private HousekiPlatform() {}

    public static CreativeModeTab.Builder creativeTab() {
        return CreativeModeTab.builder();
    }

    public static Stream<RecipeHolder<FoundryCastingRecipe>> castingRecipes(ServerLevel level, FoundryRecipeCastInput input) {
        return level.recipeAccess().recipeMap().getRecipesFor(ModTypes.CASTING_FOUNDRY_TYPE, input, level);
    }
}
