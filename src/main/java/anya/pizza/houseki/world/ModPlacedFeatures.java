package anya.pizza.houseki.world;

import anya.pizza.houseki.Houseki;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> PINKU_ORE_PLACED_KEY = registerKey("pinku_ore_placed");
    public static final RegistryKey<PlacedFeature> RAINBOW_PYRITE_ORE_PLACED_KEY = registerKey("rainbow_pyrite_ore_placed");
    public static final RegistryKey<PlacedFeature> SANDSTONE_RAINBOW_PYRITE_ORE_PLACED_KEY = registerKey("sandstone_rainbow_pyrite_ore_placed");
    public static final RegistryKey<PlacedFeature> BAUXITE_RAINBOW_PYRITE_ORE_PLACED_KEY = registerKey("bauxite_rainbow_pyrite_ore_placed");
    public static final RegistryKey<PlacedFeature> WOLFRAMITE_ORE_PLACED_KEY = registerKey("wolframite_ore_placed");
    public static final RegistryKey<PlacedFeature> SCHEELITE_ORE_PLACED_KEY = registerKey("scheelite_ore_placed");
    public static final RegistryKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final RegistryKey<PlacedFeature> NEPHRITE_ORE_PLACED_KEY = registerKey("nephrite_ore_placed");
    public static final RegistryKey<PlacedFeature> JADEITE_ORE_PLACED_KEY = registerKey("jadeite_ore_placed");
    public static final RegistryKey<PlacedFeature> PLATINUM_ORE_PLACED_KEY = registerKey("platinum_ore_placed");
    public static final RegistryKey<PlacedFeature> SULFUR_ORE_PLACED_KEY = registerKey("sulfur_ore_placed");
    public static final RegistryKey<PlacedFeature> BLACKSTONE_SULFUR_ORE_PLACED_KEY = registerKey("blackstone_sulfur_ore_placed");

    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE_PLACED_KEY = registerKey("ore_limestone_placed");
    public static final RegistryKey<PlacedFeature> ORE_SLATE_PLACED_KEY = registerKey("ore_slate_placed");
    public static final RegistryKey<PlacedFeature> ORE_BAUXITE_PLACED_KEY = registerKey("ore_bauxite_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, PINKU_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PINKU_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3, /*Veins per Chunk*/HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop())));

        register(context, RAINBOW_PYRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RAINBOW_PYRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacementModifier.uniform(YOffset.fixed(10), YOffset.getTop())));
        register(context, SANDSTONE_RAINBOW_PYRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SANDSTONE_RAINBOW_PYRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.fixed(10), YOffset.getTop())));
        register(context, BAUXITE_RAINBOW_PYRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BAUXITE_RAINBOW_PYRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacementModifier.uniform(YOffset.fixed(10), YOffset.getTop())));

        register(context, WOLFRAMITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WOLFRAMITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));

        register(context, SCHEELITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SCHEELITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(6, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

        register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(30))));

        register(context, NEPHRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NEPHRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));

        register(context, JADEITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.JADEITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));

        register(context, PLATINUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PLATINUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));

        register(context, SULFUR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SULFUR_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));
        register(context, BLACKSTONE_SULFUR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLACKSTONE_SULFUR_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));


        register(context, ORE_LIMESTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));
        register(context, ORE_SLATE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SLATE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));
        register(context, ORE_BAUXITE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_BAUXITE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Houseki.MOD_ID, name));
    }

    public static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}