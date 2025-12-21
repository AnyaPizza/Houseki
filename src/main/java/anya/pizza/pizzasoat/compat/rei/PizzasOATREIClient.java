package anya.pizza.pizzasoat.compat.rei;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.recipe.CrusherRecipe;
import anya.pizza.pizzasoat.recipe.ModRecipes;
import anya.pizza.pizzasoat.screen.custom.CrusherScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;


public class PizzasOATREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrusherCategory());

        registry.addWorkstations(CrusherCategory.CRUSHER, EntryStacks.of(ModBlocks.CRUSHER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrusherRecipe.class, ModRecipes.CRUSHER_TYPE, CrusherDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78, ((screen.height - 176) / 2) + 30, 20, 25),
                CrusherScreen.class, CrusherCategory.CRUSHER);
    }
}
