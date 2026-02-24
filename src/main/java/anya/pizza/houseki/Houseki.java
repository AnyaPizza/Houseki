package anya.pizza.houseki;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.item.ModItemGroups;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.ModRecipes;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.trim.TrimEffectHandler;
import anya.pizza.houseki.util.*;
import anya.pizza.houseki.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Houseki implements ModInitializer {
	public static final String MOD_ID = "houseki";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	/**
	 * Initializes and registers the mod's content and runtime hooks during startup.
	 *
	 * <p>Registers items, blocks, item groups, block entities, screen handlers, recipes,
	 * loot table modifiers, trim effects, world generation, fuel entries for sulfur items/blocks,
	 * and player block-break event handlers.</p>
	 */
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();
		ModLootTableModifiers.modifyLootTables();

		TrimEffectHandler.registerTrimEffects();

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