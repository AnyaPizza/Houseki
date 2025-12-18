package anya.pizza.pizzasoat;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.block.entity.ModBlockEntities;
import anya.pizza.pizzasoat.item.ModItemGroups;
import anya.pizza.pizzasoat.item.ModItems;
import anya.pizza.pizzasoat.screen.ModScreenHandlers;
import anya.pizza.pizzasoat.util.ADUsageEvent;
import anya.pizza.pizzasoat.util.EDUsageEvent;
import anya.pizza.pizzasoat.util.PDUsageEvent;
import anya.pizza.pizzasoat.util.ModLootTableModifiers;
import anya.pizza.pizzasoat.world.gen.ModWorldGeneration;
import anya.pizza.pizzasoat.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
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
		ModLootTableModifiers.modifyLootTables();

		ModWorldGeneration.generateModWorldGeneration();

		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.SULFUR, 1600);
		});
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModBlocks.BLOCK_OF_SULFUR, 16000);
		});


		PlayerBlockBreakEvents.BEFORE.register(new EDUsageEvent());
		PlayerBlockBreakEvents.BEFORE.register(new ADUsageEvent());
		PlayerBlockBreakEvents.BEFORE.register(new PDUsageEvent());
	}
}