package anya.pizza.houseki.world;

import anya.pizza.houseki.Houseki;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PINKU_ORE_PLACED_KEY = registerKey("pinku_ore_placed");
    public static final ResourceKey<PlacedFeature> RAINBOW_PYRITE_ORE_PLACED_KEY = registerKey("rainbow_pyrite_ore_placed");
    public static final ResourceKey<PlacedFeature> SANDSTONE_RAINBOW_PYRITE_ORE_PLACED_KEY = registerKey("sandstone_rainbow_pyrite_ore_placed");
    public static final ResourceKey<PlacedFeature> BAUXITE_RAINBOW_PYRITE_ORE_PLACED_KEY = registerKey("bauxite_rainbow_pyrite_ore_placed");
    public static final ResourceKey<PlacedFeature> WOLFRAMITE_ORE_PLACED_KEY = registerKey("wolframite_ore_placed");
    public static final ResourceKey<PlacedFeature> SCHEELITE_ORE_PLACED_KEY = registerKey("scheelite_ore_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> NEPHRITE_ORE_PLACED_KEY = registerKey("nephrite_ore_placed");
    public static final ResourceKey<PlacedFeature> JADEITE_ORE_PLACED_KEY = registerKey("jadeite_ore_placed");
    public static final ResourceKey<PlacedFeature> PLATINUM_ORE_PLACED_KEY = registerKey("platinum_ore_placed");
    public static final ResourceKey<PlacedFeature> SULFUR_ORE_PLACED_KEY = registerKey("sulfur_ore_placed");
    public static final ResourceKey<PlacedFeature> BLACKSTONE_SULFUR_ORE_PLACED_KEY = registerKey("blackstone_sulfur_ore_placed");

    public static final ResourceKey<PlacedFeature> ORE_LIMESTONE_PLACED_KEY = registerKey("ore_limestone_placed");
    public static final ResourceKey<PlacedFeature> ORE_SLATE_PLACED_KEY = registerKey("ore_slate_placed");
    public static final ResourceKey<PlacedFeature> ORE_BAUXITE_PLACED_KEY = registerKey("ore_bauxite_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PINKU_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PINKU_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3, /*Veins per Chunk*/HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top())));

        register(context, RAINBOW_PYRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RAINBOW_PYRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.top())));
        register(context, SANDSTONE_RAINBOW_PYRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SANDSTONE_RAINBOW_PYRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.top())));
        register(context, BAUXITE_RAINBOW_PYRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BAUXITE_RAINBOW_PYRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.top())));

        register(context, WOLFRAMITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WOLFRAMITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

        register(context, SCHEELITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SCHEELITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));

        register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(30))));

        register(context, NEPHRITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NEPHRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

        register(context, JADEITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.JADEITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(30, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

        register(context, PLATINUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PLATINUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

        register(context, SULFUR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SULFUR_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
        register(context, BLACKSTONE_SULFUR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLACKSTONE_SULFUR_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));


        register(context, ORE_LIMESTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
        register(context, ORE_SLATE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SLATE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
        register(context, ORE_BAUXITE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_BAUXITE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}