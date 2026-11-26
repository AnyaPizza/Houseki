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
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class HousekiFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlocks.registerBlockItems();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModSerializer.registerSerializers();
		RecipeSynchronization.synchronizeRecipeSerializer(ModSerializer.CRUSHER_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModSerializer.FOUNDRY_MELTING_SERIALIZER);
		RecipeSynchronization.synchronizeRecipeSerializer(ModSerializer.FOUNDRY_CASTING_SERIALIZER);
		ModTypes.registerRecipeTypes();
		ModStructures.register();
		ModStructures.registerPiece();
		LootTableEvents.MODIFY.register((key, table, source, registries) ->
				ModLootTableModifiers.addPools(key.identifier(), table::pool));

		ServerTickEvents.END_SERVER_TICK.register(TrimEffectHandler::tick);

		ModWorldGeneration.generateModWorldGeneration();

		ModEffects.registerEffects();

		FuelValueEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.SULFUR_POWDER, 1600);
		});

		PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, entity) -> {
			EDUsageEvent.breakExtraBlocks(level, player, pos);
			ADUsageEvent.breakExtraBlocks(level, player, pos);
			PDUsageEvent.breakExtraBlocks(level, player, pos);
			return true;
		});
	}
}