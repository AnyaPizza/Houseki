package anya.pizza.houseki;

import anya.pizza.houseki.datagen.*;
import anya.pizza.houseki.trim.ModTrimMaterials;
import anya.pizza.houseki.world.ModConfiguredFeatures;
import anya.pizza.houseki.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class HousekiDataGenerator implements DataGeneratorEntrypoint {
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
	 * Registers the mod's data registries and their bootstrap suppliers with the provided RegistryBuilder.
	 *
	 * Specifically registers CONFIGURED_FEATURE, PLACED_FEATURE, and TRIM_MATERIAL with their respective
	 * bootstrap suppliers.
	 *
	 * @param registryBuilder the RegistryBuilder to which the mod registries and bootstrap suppliers are added
	 */
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
	}
}