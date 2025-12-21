package anya.pizza.pizzasoat.compat.emi;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.recipe.CrusherRecipe;
import anya.pizza.pizzasoat.recipe.ModRecipes;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import anya.pizza.pizzasoat.block.ModBlocks;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

@EmiEntrypoint
public class EmiCrusherPlugin implements EmiPlugin {
    public static final EmiStack CRUSHEREMI = EmiStack.of(ModBlocks.CRUSHER);
    public static final EmiRecipeCategory CRUSHER_CATEGORY = new EmiRecipeCategory(
             Identifier.of(PizzasOAT.MOD_ID, "crusher"), EmiStack.of(ModBlocks.CRUSHER));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(CRUSHER_CATEGORY);
        registry.addWorkstation(CRUSHER_CATEGORY, CRUSHEREMI);

        RecipeManager recipeManager = registry.getRecipeManager();

        recipeManager.listAllOfType(ModRecipes.CRUSHER_TYPE).forEach(entry -> {
            CrusherRecipe recipe = entry.value();
            Identifier id = entry.id();
            registry.addRecipe(new EmiCrusherRecipe(id, recipe));
        });
    }
}
