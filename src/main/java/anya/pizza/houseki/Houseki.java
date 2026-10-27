package anya.pizza.houseki;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.effect.ModEffects;
import anya.pizza.houseki.item.ModItemGroups;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.ModTypes;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.trim.TrimEffectHandler;
import anya.pizza.houseki.util.ADUsageEvent;
import anya.pizza.houseki.util.EDUsageEvent;
import anya.pizza.houseki.util.PDUsageEvent;
import anya.pizza.houseki.util.ModLootTableModifiers;
import anya.pizza.houseki.world.gen.ModWorldGeneration;
import anya.pizza.houseki.recipe.ModSerializer;
import anya.pizza.houseki.world.structure.ModStructures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Houseki implements ModInitializer {
	public static final String MOD_ID = "houseki";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModSerializer.registerSerializers();
		ModTypes.registerRecipeTypes();
		ModStructures.register();
		ModLootTableModifiers.modifyLootTables();

		TrimEffectHandler.registerTrimEffects();

		ModWorldGeneration.generateModWorldGeneration();

		ModEffects.registerEffects();

		PlayerBlockBreakEvents.BEFORE.register(new EDUsageEvent());
		PlayerBlockBreakEvents.BEFORE.register(new ADUsageEvent());
		PlayerBlockBreakEvents.BEFORE.register(new PDUsageEvent());
	}
}