package anya.pizza.houseki;

import anya.pizza.houseki.datagen.*;
import anya.pizza.houseki.world.ModConfiguredFeatures;
import anya.pizza.houseki.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class HousekiDataGenerator implements DataGeneratorEntrypoint {
	/**
	 * Create a data pack from the given FabricDataGenerator and register the mod's data providers.
	 *
	 * The method creates a new pack and adds providers responsible for block and item tags,
	 * loot tables, models, recipes, and registry data so those assets are generated for the mod.
	 *
	 * @param fabricDataGenerator the FabricDataGenerator used to create the pack and register providers
	 */
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
	}

	/**
	 * Registers bootstrap suppliers for configured and placed world-generation features into the given registry builder.
	 *
	 * @param registryBuilder the registry set builder to receive the configured-feature and placed-feature bootstraps
	 */
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}