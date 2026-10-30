package anya.pizza.houseki.world;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModFeatures {
    public static final ResourceKey<Feature> PINKU_ORE_KEY = registerKey("pinku_ore");
    public static final ResourceKey<Feature> RAINBOW_PYRITE_ORE_KEY = registerKey("rainbow_pyrite_ore");
    public static final ResourceKey<Feature> SANDSTONE_RAINBOW_PYRITE_ORE_KEY = registerKey("sandstone_rainbow_pyrite_ore");
    public static final ResourceKey<Feature> BAUXITE_RAINBOW_PYRITE_ORE_KEY = registerKey("bauxite_rainbow_pyrite_ore");
    public static final ResourceKey<Feature> WOLFRAMITE_ORE_KEY = registerKey("wolframite_ore");
    public static final ResourceKey<Feature> NETHERRACK_WOLFRAMITE_ORE_KEY = registerKey("netherrack_wolframite_ore");
    public static final ResourceKey<Feature> SCHEELITE_ORE_KEY = registerKey("scheelite_ore");
    public static final ResourceKey<Feature> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<Feature> NEPHRITE_ORE_KEY = registerKey("nephrite_ore");
    public static final ResourceKey<Feature> JADEITE_ORE_KEY = registerKey("jadeite_ore");
    public static final ResourceKey<Feature> PLATINUM_ORE_KEY = registerKey("platinum_ore");
    public static final ResourceKey<Feature> SUGILITE_ORE_KEY = registerKey("sugilite_ore");
    public static final ResourceKey<Feature> BISMUTH_ORE_KEY = registerKey("bismuth_ore");

    public static final ResourceKey<Feature> ORE_LIMESTONE_KEY = registerKey("ore_limestone");
    public static final ResourceKey<Feature> ORE_SLATE_KEY = registerKey("ore_slate");
    public static final ResourceKey<Feature> ORE_BAUXITE_KEY = registerKey("ore_bauxite");

    public static void bootstrap(BootstrapContext<Feature> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest graniteReplaceables = new BlockMatchTest(Blocks.GRANITE);
        RuleTest coalOreReplaceables = new BlockMatchTest(Blocks.COAL_ORE);
        RuleTest copperReplaceables = new BlockMatchTest(Blocks.COPPER_ORE);
        RuleTest deepCopperReplaceables = new BlockMatchTest(Blocks.DEEPSLATE_COPPER_ORE);
        RuleTest sandstoneReplaceables = new BlockMatchTest(Blocks.SANDSTONE);
        RuleTest bauxiteReplaceables = new BlockMatchTest(ModBlocks.BAUXITE);
        RuleTest limestoneReplaceables = new BlockMatchTest(ModBlocks.LIMESTONE);
        RuleTest slateReplaceables = new BlockMatchTest(ModBlocks.SLATE);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest quartzReplaceables = new BlockMatchTest(Blocks.NETHER_QUARTZ_ORE);

        List<BlockReplacement> endPinkuOres = List.of(BlockReplacement.replace(endReplaceables, ModBlocks.PINKU_ORE.defaultBlockState()));

        List<BlockReplacement> overworldRainbowPyriteOres = List.of(BlockReplacement.replace(coalOreReplaceables, ModBlocks.RAINBOW_PYRITE_ORE.defaultBlockState()));
        List<BlockReplacement> overworldSandstoneRainbowPyriteOres = List.of(BlockReplacement.replace(sandstoneReplaceables, ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE.defaultBlockState()));
        List<BlockReplacement> overworldBauxiteRainbowPyriteOres = List.of(BlockReplacement.replace(bauxiteReplaceables, ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE.defaultBlockState()));

        List<BlockReplacement> overworldWolframiteOres = List.of(BlockReplacement.replace(graniteReplaceables, ModBlocks.WOLFRAMITE_ORE.defaultBlockState()));
        List<BlockReplacement> netherWolframiteOres = List.of(BlockReplacement.replace(quartzReplaceables, ModBlocks.NETHERRACK_WOLFRAMITE_ORE.defaultBlockState()));

        List<BlockReplacement> netherScheeliteOres = List.of(BlockReplacement.replace(netherrackReplaceables, ModBlocks.SCHEELITE_ORE.defaultBlockState()));

        List<BlockReplacement> overworldBauxiteOre = List.of(BlockReplacement.replace(stoneReplaceables, ModBlocks.BAUXITE.defaultBlockState()));

        List<BlockReplacement> overworldSapphireOres = List.of(BlockReplacement.replace(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.defaultBlockState()),
                        BlockReplacement.replace(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.defaultBlockState()));

        List<BlockReplacement> overworldNephriteOres = List.of(BlockReplacement.replace(limestoneReplaceables, ModBlocks.NEPHRITE_ORE.defaultBlockState()));

        List<BlockReplacement> overworldJadeiteOres = List.of(BlockReplacement.replace(slateReplaceables, ModBlocks.JADEITE_ORE.defaultBlockState()));

        List<BlockReplacement> overworldPlatinumOres = List.of(BlockReplacement.replace(copperReplaceables, ModBlocks.PLATINUM_ORE.defaultBlockState()),
                        BlockReplacement.replace(deepCopperReplaceables, ModBlocks.DEEPSLATE_PLATINUM_ORE.defaultBlockState()));

        List<BlockReplacement> overworldLimestoneOre = List.of(BlockReplacement.replace(stoneReplaceables,ModBlocks.LIMESTONE.defaultBlockState()),
                        BlockReplacement.replace(deepslateReplaceables,ModBlocks.LIMESTONE.defaultBlockState()));

        List<BlockReplacement> overworldSlateOre = List.of(BlockReplacement.replace(stoneReplaceables,ModBlocks.SLATE.defaultBlockState()),
                        BlockReplacement.replace(deepslateReplaceables,ModBlocks.SLATE.defaultBlockState()));

        List<BlockReplacement> netherSugiliteOre = List.of(BlockReplacement.replace(netherrackReplaceables, ModBlocks.SUGILITE_ORE.defaultBlockState()));
        List<BlockReplacement> overworldBismuthOre = List.of(BlockReplacement.replace(stoneReplaceables, ModBlocks.BISMUTH_ORE.defaultBlockState()));

        context.register(PINKU_ORE_KEY, new OreFeature(endPinkuOres, 3));
        context.register(RAINBOW_PYRITE_ORE_KEY, new OreFeature(overworldRainbowPyriteOres, 10));
        context.register(SANDSTONE_RAINBOW_PYRITE_ORE_KEY, new OreFeature(overworldSandstoneRainbowPyriteOres, 5));
        context.register(BAUXITE_RAINBOW_PYRITE_ORE_KEY, new OreFeature(overworldBauxiteRainbowPyriteOres, 8));
        context.register(WOLFRAMITE_ORE_KEY, new OreFeature(overworldWolframiteOres, 4));
        context.register(NETHERRACK_WOLFRAMITE_ORE_KEY, new OreFeature(netherWolframiteOres, 8));
        context.register(SCHEELITE_ORE_KEY, new OreFeature(netherScheeliteOres, 6));
        context.register(SAPPHIRE_ORE_KEY, new OreFeature(overworldSapphireOres, 3));
        context.register(NEPHRITE_ORE_KEY, new OreFeature(overworldNephriteOres, 8));
        context.register(JADEITE_ORE_KEY, new OreFeature(overworldJadeiteOres, 8));
        context.register(PLATINUM_ORE_KEY, new OreFeature(overworldPlatinumOres, 3));
        context.register(SUGILITE_ORE_KEY, new OreFeature(netherSugiliteOre, 4));
        context.register(BISMUTH_ORE_KEY, new OreFeature(overworldBismuthOre, 4));

        context.register(ORE_LIMESTONE_KEY, new OreFeature(overworldLimestoneOre, 64));
        context.register(ORE_SLATE_KEY, new OreFeature(overworldSlateOre, 64));
        context.register(ORE_BAUXITE_KEY, new OreFeature(overworldBauxiteOre, 64));
    }

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name));
    }
}