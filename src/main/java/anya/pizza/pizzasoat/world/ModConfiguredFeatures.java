package anya.pizza.pizzasoat.world;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> PINKU_ORE_KEY = registerKey("pinku_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RAINBOW_PYRITE_ORE_KEY = registerKey("rainbow_pyrite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SANDSTONE_RAINBOW_PYRITE_ORE_KEY = registerKey("sandstone_rainbow_pyrite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WOLFRAMITE_ORE_KEY = registerKey("wolframite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SCHEELITE_ORE_KEY = registerKey("scheelite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BAUXITE_ORE_KEY = registerKey("bauxite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NEPHRITE_ORE_KEY = registerKey("nephrite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TRONA_ORE_KEY = registerKey("trona_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PLATINUM_ORE_KEY = registerKey("platinum_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SULFUR_ORE_KEY = registerKey("sulfur_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLACKSTONE_SULFUR_ORE_KEY = registerKey("blackstone_sulfur_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE_KEY = registerKey("ore_limestone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SLATE_KEY = registerKey("ore_slate");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);
        RuleTest graniteReplaceables = new BlockMatchRuleTest(Blocks.GRANITE);
        RuleTest coalOreReplaceables = new BlockMatchRuleTest(Blocks.COAL_ORE);
        RuleTest copperReplaceables = new BlockMatchRuleTest(Blocks.COPPER_ORE);
        RuleTest deepCopperReplaceables = new BlockMatchRuleTest(Blocks.DEEPSLATE_COPPER_ORE);
        RuleTest blackstoneReplaceables = new BlockMatchRuleTest(Blocks.BLACKSTONE);
        RuleTest sandstoneReplaceables = new BlockMatchRuleTest(Blocks.SANDSTONE);

        List<OreFeatureConfig.Target> endPinkuOres =
                List.of(OreFeatureConfig.createTarget(endReplaceables, ModBlocks.PINKU_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldRainbowPyriteOres =
                List.of(OreFeatureConfig.createTarget(coalOreReplaceables, ModBlocks.RAINBOW_PYRITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldSandstoneRainbowPyriteOres =
                List.of(OreFeatureConfig.createTarget(sandstoneReplaceables, ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldWolframiteOres =
                List.of(OreFeatureConfig.createTarget(graniteReplaceables, ModBlocks.WOLFRAMITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherScheeliteOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables, ModBlocks.SCHEELITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldBauxiteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.BAUXITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_BAUXITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldSapphireOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldNephriteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.NEPHRITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_NEPHRITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldTronaOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TRONA_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldPlatinumOres =
                List.of(OreFeatureConfig.createTarget(copperReplaceables, ModBlocks.PLATINUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepCopperReplaceables, ModBlocks.DEEPSLATE_PLATINUM_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherSulfurOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables,ModBlocks.SULFUR_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> blackstoneSulfurOres =
                List.of(OreFeatureConfig.createTarget(blackstoneReplaceables,ModBlocks.BLACKSTONE_SULFUR_ORE.getDefaultState()));


        List<OreFeatureConfig.Target> overworldLimestoneOre =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables,ModBlocks.LIMESTONE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables,ModBlocks.LIMESTONE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldSlateOre =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables,ModBlocks.SLATE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables,ModBlocks.SLATE.getDefaultState()));


        register(context, PINKU_ORE_KEY, Feature.ORE, new OreFeatureConfig(endPinkuOres, 3));
        register(context, RAINBOW_PYRITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRainbowPyriteOres, 20));
        register(context, SANDSTONE_RAINBOW_PYRITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSandstoneRainbowPyriteOres, 5));
        register(context, WOLFRAMITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldWolframiteOres, 10));
        register(context, SCHEELITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherScheeliteOres, 6));
        register(context, BAUXITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldBauxiteOres, 6));
        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSapphireOres, 3));
        register(context, NEPHRITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldNephriteOres, 6));
        register(context, TRONA_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTronaOres, 3));
        register(context, PLATINUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPlatinumOres, 3));
        register(context, SULFUR_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherSulfurOres, 2));
        register(context, BLACKSTONE_SULFUR_ORE_KEY, Feature.ORE, new OreFeatureConfig(blackstoneSulfurOres, 10));

        register(context, ORE_LIMESTONE_KEY, Feature.ORE, new OreFeatureConfig(overworldLimestoneOre, 64));
        register(context, ORE_SLATE_KEY, Feature.ORE, new OreFeatureConfig(overworldSlateOre, 64));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(PizzasOAT.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
