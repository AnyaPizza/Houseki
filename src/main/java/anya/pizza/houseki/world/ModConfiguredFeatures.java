package anya.pizza.houseki.world;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.resources.Identifier;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINKU_ORE_KEY = registerKey("pinku_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_PYRITE_ORE_KEY = registerKey("rainbow_pyrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDSTONE_RAINBOW_PYRITE_ORE_KEY = registerKey("sandstone_rainbow_pyrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAUXITE_RAINBOW_PYRITE_ORE_KEY = registerKey("bauxite_rainbow_pyrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WOLFRAMITE_ORE_KEY = registerKey("wolframite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCHEELITE_ORE_KEY = registerKey("scheelite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NEPHRITE_ORE_KEY = registerKey("nephrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JADEITE_ORE_KEY = registerKey("jadeite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_KEY = registerKey("platinum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SULFUR_ORE_KEY = registerKey("sulfur_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_SULFUR_ORE_KEY = registerKey("blackstone_sulfur_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE_KEY = registerKey("ore_limestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SLATE_KEY = registerKey("ore_slate");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BAUXITE_KEY = registerKey("ore_bauxite");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest graniteReplaceables = new BlockMatchTest(Blocks.GRANITE);
        RuleTest coalOreReplaceables = new BlockMatchTest(Blocks.COAL_ORE);
        RuleTest copperReplaceables = new BlockMatchTest(Blocks.COPPER_ORE);
        RuleTest deepCopperReplaceables = new BlockMatchTest(Blocks.DEEPSLATE_COPPER_ORE);
        RuleTest blackstoneReplaceables = new BlockMatchTest(Blocks.BLACKSTONE);
        RuleTest sandstoneReplaceables = new BlockMatchTest(Blocks.SANDSTONE);
        RuleTest bauxiteReplaceables = new BlockMatchTest(ModBlocks.BAUXITE);
        RuleTest limestoneReplaceables = new BlockMatchTest(ModBlocks.LIMESTONE);
        RuleTest slateReplaceables = new BlockMatchTest(ModBlocks.SLATE);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> endPinkuOres = List.of(OreConfiguration.target(endReplaceables, ModBlocks.PINKU_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldRainbowPyriteOres = List.of(OreConfiguration.target(coalOreReplaceables, ModBlocks.RAINBOW_PYRITE_ORE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldSandstoneRainbowPyriteOres = List.of(OreConfiguration.target(sandstoneReplaceables, ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldBauxiteRainbowPyriteOres = List.of(OreConfiguration.target(bauxiteReplaceables, ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldWolframiteOres = List.of(OreConfiguration.target(graniteReplaceables, ModBlocks.WOLFRAMITE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherScheeliteOres = List.of(OreConfiguration.target(netherrackReplaceables, ModBlocks.SCHEELITE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldBauxiteOre = List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.BAUXITE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldNephriteOres = List.of(OreConfiguration.target(limestoneReplaceables, ModBlocks.NEPHRITE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldJadeiteOres = List.of(OreConfiguration.target(slateReplaceables, ModBlocks.JADEITE_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldPlatinumOres = List.of(OreConfiguration.target(copperReplaceables, ModBlocks.PLATINUM_ORE.defaultBlockState()),
                        OreConfiguration.target(deepCopperReplaceables, ModBlocks.DEEPSLATE_PLATINUM_ORE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherSulfurOres = List.of(OreConfiguration.target(netherrackReplaceables,ModBlocks.SULFUR_ORE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> blackstoneSulfurOres = List.of(OreConfiguration.target(blackstoneReplaceables,ModBlocks.BLACKSTONE_SULFUR_ORE.defaultBlockState()));


        List<OreConfiguration.TargetBlockState> overworldLimestoneOre = List.of(OreConfiguration.target(stoneReplaceables,ModBlocks.LIMESTONE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables,ModBlocks.LIMESTONE.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldSlateOre = List.of(OreConfiguration.target(stoneReplaceables,ModBlocks.SLATE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables,ModBlocks.SLATE.defaultBlockState()));


        register(context, PINKU_ORE_KEY, Feature.ORE, new OreConfiguration(endPinkuOres, 3));
        register(context, RAINBOW_PYRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRainbowPyriteOres, 10));
        register(context, SANDSTONE_RAINBOW_PYRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSandstoneRainbowPyriteOres, 5));
        register(context, BAUXITE_RAINBOW_PYRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBauxiteRainbowPyriteOres, 8));
        register(context, WOLFRAMITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldWolframiteOres, 4));
        register(context, SCHEELITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherScheeliteOres, 6));
        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 3));
        register(context, NEPHRITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldNephriteOres, 8));
        register(context, JADEITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldJadeiteOres, 8));
        register(context, PLATINUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPlatinumOres, 3));
        register(context, SULFUR_ORE_KEY, Feature.ORE, new OreConfiguration(netherSulfurOres, 2));
        register(context, BLACKSTONE_SULFUR_ORE_KEY, Feature.ORE, new OreConfiguration(blackstoneSulfurOres, 10));

        register(context, ORE_LIMESTONE_KEY, Feature.ORE, new OreConfiguration(overworldLimestoneOre, 64));
        register(context, ORE_SLATE_KEY, Feature.ORE, new OreConfiguration(overworldSlateOre, 64));
        register(context, ORE_BAUXITE_KEY, Feature.ORE, new OreConfiguration(overworldBauxiteOre, 64));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
