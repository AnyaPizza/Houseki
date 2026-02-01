package anya.pizza.houseki.world.gen;

import anya.pizza.houseki.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.RAINBOW_PYRITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SANDSTONE_RAINBOW_PYRITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BAUXITE_RAINBOW_PYRITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.PINKU_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.WOLFRAMITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SCHEELITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.JUNGLE,
                        Biomes.SAVANNA, Biomes.WARM_OCEAN, Biomes.FOREST, Biomes.SWAMP, Biomes.MANGROVE_SWAMP), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_BAUXITE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SAPPHIRE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER,
                        Biomes.DEEP_FROZEN_OCEAN, Biomes.SNOWY_BEACH, Biomes.ICE_SPIKES, Biomes.JAGGED_PEAKS, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN,
                        Biomes.DRIPSTONE_CAVES, Biomes.SNOWY_PLAINS, Biomes.LUKEWARM_OCEAN, Biomes.MANGROVE_SWAMP, Biomes.OCEAN, Biomes.RIVER, Biomes.STONY_SHORE, Biomes.STONY_PEAKS,
                        Biomes.SWAMP, Biomes.WARM_OCEAN, Biomes.LUSH_CAVES, Biomes.SNOWY_SLOPES, Biomes.SNOWY_TAIGA),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.NEPHRITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.JADEITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.PLATINUM_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER,
                        Biomes.DEEP_FROZEN_OCEAN, Biomes.SNOWY_BEACH, Biomes.ICE_SPIKES, Biomes.JAGGED_PEAKS, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN,
                        Biomes.DRIPSTONE_CAVES, Biomes.SNOWY_PLAINS, Biomes.LUKEWARM_OCEAN, Biomes.MANGROVE_SWAMP, Biomes.OCEAN, Biomes.RIVER, Biomes.STONY_SHORE, Biomes.STONY_PEAKS,
                        Biomes.SWAMP, Biomes.WARM_OCEAN, Biomes.LUSH_CAVES, Biomes.SNOWY_SLOPES, Biomes.SNOWY_TAIGA),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SLATE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACKSTONE_SULFUR_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.NETHER_WASTES, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.SOUL_SAND_VALLEY), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SULFUR_ORE_PLACED_KEY);
    }
}
