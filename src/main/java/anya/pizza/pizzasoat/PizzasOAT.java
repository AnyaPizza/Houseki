package anya.pizza.pizzasoat;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.block.entity.ModBlockEntities;
import anya.pizza.pizzasoat.item.ModItemGroups;
import anya.pizza.pizzasoat.item.ModItems;
import anya.pizza.pizzasoat.screen.ModScreenHandlers;
import anya.pizza.pizzasoat.world.gen.ModWorldGeneration;
import anya.pizza.pizzasoat.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzasOAT implements ModInitializer {
	public static final String MOD_ID = "pizzasoat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();

		ModWorldGeneration.generateModWorldGeneration();

		FuelRegistry.INSTANCE.add(ModItems.SULFUR, 1600);
		FuelRegistry.INSTANCE.add(ModBlocks.BLOCK_OF_SULFUR, 16000);
	}
}