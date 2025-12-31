package anya.pizza.houseki.world.gen;

import anya.pizza.houseki.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.RAINBOW_PYRITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SANDSTONE_RAINBOW_PYRITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BAUXITE_RAINBOW_PYRITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.PINKU_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.WOLFRAMITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SCHEELITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BEACH, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.JUNGLE,
                        BiomeKeys.SAVANNA, BiomeKeys.WARM_OCEAN, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_BAUXITE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SAPPHIRE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BEACH, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER,
                        BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.SNOWY_BEACH, BiomeKeys.ICE_SPIKES, BiomeKeys.JAGGED_PEAKS, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN,
                        BiomeKeys.DRIPSTONE_CAVES, BiomeKeys.SNOWY_PLAINS, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.OCEAN, BiomeKeys.RIVER, BiomeKeys.STONY_SHORE, BiomeKeys.STONY_PEAKS,
                        BiomeKeys.SWAMP, BiomeKeys.WARM_OCEAN, BiomeKeys.LUSH_CAVES, BiomeKeys.SNOWY_SLOPES, BiomeKeys.SNOWY_TAIGA),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.NEPHRITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.JADEITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.PLATINUM_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BEACH, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER,
                        BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.SNOWY_BEACH, BiomeKeys.ICE_SPIKES, BiomeKeys.JAGGED_PEAKS, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN,
                        BiomeKeys.DRIPSTONE_CAVES, BiomeKeys.SNOWY_PLAINS, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.OCEAN, BiomeKeys.RIVER, BiomeKeys.STONY_SHORE, BiomeKeys.STONY_PEAKS,
                        BiomeKeys.SWAMP, BiomeKeys.WARM_OCEAN, BiomeKeys.LUSH_CAVES, BiomeKeys.SNOWY_SLOPES, BiomeKeys.SNOWY_TAIGA),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SLATE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BASALT_DELTAS), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BLACKSTONE_SULFUR_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES, BiomeKeys.CRIMSON_FOREST, BiomeKeys.WARPED_FOREST, BiomeKeys.SOUL_SAND_VALLEY), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SULFUR_ORE_PLACED_KEY);
    }
}
