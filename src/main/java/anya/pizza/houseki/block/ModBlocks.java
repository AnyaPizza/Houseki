package anya.pizza.houseki.block;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.custom.CrusherBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.function.Function;

import static net.minecraft.block.Blocks.createLightLevelFromLitBlockState;

public class ModBlocks {
    //Adds Block
    public static final Block BLOCK_OF_PINKU = registerBlock("block_of_pinku",
            properties -> new Block(properties.mapColor(MapColor.PINK).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5, 1000).sounds(BlockSoundGroup.METAL)));

    public static final Block BLOCK_OF_RAINBOW_PYRITE = registerBlock("block_of_rainbow_pyrite",
            properties -> new Block(properties.mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5, 6).sounds(BlockSoundGroup.METAL)));

    public static final Block BLOCK_OF_TUNGSTEN_B = registerBlock("block_of_tungsten_b",
            properties -> new Block(properties.mapColor(MapColor.LIGHT_BLUE_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(10, 1000).sounds(BlockSoundGroup.METAL)));

    public static final Block BLOCK_OF_ALUMINUM = registerBlock("block_of_aluminum",
            properties -> new Block(properties.mapColor(MapColor.WHITE).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresTool().strength(8, 500).sounds(BlockSoundGroup.METAL)));

    public static final Block ALUMINUM_GLASS = registerBlock("aluminum_glass",
            properties -> new TransparentBlock(properties
                    .instrument(NoteBlockInstrument.HAT).strength(2, 1000).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never)) {
                @Override
                public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return super.getCameraCollisionShape(state, world, pos, context);
                }
            });

    public static final Block ALUMINUM_GLASS_PANE = registerBlock("aluminum_glass_pane",
            properties -> new PaneBlock(properties.instrument(NoteBlockInstrument.HAT).strength(2, 1000).sounds(BlockSoundGroup.GLASS).nonOpaque()) {
                @Override
                public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return super.getCameraCollisionShape(state, world, pos, context);
                }
            });

    public static final Block BLOCK_OF_SAPPHIRE = registerBlock("block_of_sapphire",
            properties -> new Block(properties.mapColor(MapColor.BLUE).requiresTool().strength(9, 500).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block BLOCK_OF_JADEITE = registerBlock("block_of_jadeite",
            properties -> new Block(properties.mapColor(MapColor.PALE_GREEN).requiresTool().strength(5, 6).sounds(BlockSoundGroup.METAL)));

    public static final Block BLOCK_OF_PLATINUM = registerBlock("block_of_platinum",
            properties -> new Block(properties.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(5, 6).sounds(BlockSoundGroup.METAL)));

    public static final Block LIMESTONE = registerBlock("limestone",
            properties -> new Block(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            properties -> new Block(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block POLISHED_LIMESTONE = registerBlock("polished_limestone",
            properties -> new Block(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block CHISELED_LIMESTONE = registerBlock("chiseled_limestone",
            properties -> new Block(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block BLOCK_OF_SULFUR = registerBlock("block_of_sulfur",
            properties -> new Block(properties.mapColor(MapColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(5, 6).luminance(s -> 7)));

    public static final Block SLATE = registerBlock("slate",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block SLATE_TILES = registerBlock("slate_tiles",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block POLISHED_SLATE = registerBlock("polished_slate",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block CHISELED_SLATE = registerBlock("chiseled_slate",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6)));

    public static final Block BLOCK_OF_STEEL = registerBlock("block_of_steel",
            properties -> new Block(properties.mapColor(MapColor.LIGHT_BLUE_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(4.5F, 5.5F).sounds(BlockSoundGroup.METAL)));

    public static final Block BLOCK_OF_CAST_STEEL_B = registerBlock("block_of_cast_steel_b",
            properties -> new Block(properties.mapColor(MapColor.LIGHT_BLUE_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.5F, 6.5F).sounds(BlockSoundGroup.METAL)));

    public static final Block BAUXITE = registerBlock("bauxite",
            properties -> new Block(properties.mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.25F, 4.2F)));


    //Adds Ore
    public static final Block PINKU_ORE = registerBlock("pinku_ore",
            properties -> new Block(properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().sounds(BlockSoundGroup.NETHERITE).strength(15, 9).luminance(s -> 1))); //10 Moh Scale

    public static final Block RAINBOW_PYRITE_ORE = registerBlock("rainbow_pyrite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(4, 7))); //6 Moh Scale

    public static final Block SANDSTONE_RAINBOW_PYRITE_ORE = registerBlock("sandstone_rainbow_pyrite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), properties.mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(4.8F, 7.2F)));

    public static final Block BAUXITE_RAINBOW_PYRITE_ORE = registerBlock("bauxite_rainbow_pyrite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(4.8F, 7.2F)));

    public static final Block WOLFRAMITE_ORE = registerBlock("wolframite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3, 4))); //4.5 Moh Scale

    public static final Block SCHEELITE_ORE = registerBlock("scheelite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.DARK_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3, 4).sounds(BlockSoundGroup.NETHERRACK))); //4.5 Moh Scale

    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 8), properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(10, 8).sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 8), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(9, 7))); //9 Moh Scale

    public static final Block NEPHRITE_ORE = registerBlock("nephrite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(4.5F, 6.5F))); //6.5 Moh Scale

    public static final Block JADEITE_ORE = registerBlock("jadeite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(5, 7))); //7 Moh Scale

    public static final Block PLATINUM_ORE = registerBlock("platinum_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3, 4))); //4.5 Moh Scale

    public static final Block DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3, 4).sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block SULFUR_ORE = registerBlock("sulfur_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.DARK_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1, 2).sounds(BlockSoundGroup.NETHERRACK))); //Moh scale 1.5

    public static final Block BLACKSTONE_SULFUR_ORE = registerBlock("blackstone_sulfur_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), properties.mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 2.5F)));

    //Stairs
    public static final Block LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            properties -> new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block POLISHED_LIMESTONE_STAIRS = registerBlock("polished_limestone_stairs",
            properties -> new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs",
            properties -> new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block SLATE_STAIRS = registerBlock("slate_stairs",
            properties -> new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block POLISHED_SLATE_STAIRS = registerBlock("polished_slate_stairs",
            properties -> new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block SLATE_TILE_STAIRS = registerBlock("slate_tile_stairs",
            properties -> new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));

    //Slabs
    public static final Block LIMESTONE_SLAB = registerBlock("limestone_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block POLISHED_LIMESTONE_SLAB = registerBlock("polished_limestone_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block SLATE_SLAB = registerBlock("slate_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block POLISHED_SLATE_SLAB = registerBlock("polished_slate_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));
    public static final Block SLATE_TILE_SLAB = registerBlock("slate_tile_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6)));

    //Walls
    public static final Block LIMESTONE_WALL = registerBlock("limestone_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6).solid()));
    public static final Block POLISHED_LIMESTONE_WALL = registerBlock("polished_limestone_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6).solid()));
    public static final Block LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.OFF_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6).solid()));
    public static final Block SLATE_WALL = registerBlock("slate_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6).solid()));
    public static final Block POLISHED_SLATE_WALL = registerBlock("polished_slate_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6).solid()));
    public static final Block SLATE_TILE_WALL = registerBlock("slate_tile_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(2, 6).solid()));

    //Doors
    public static final Block ALUMINUM_DOOR = registerBlock("aluminum_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.mapColor(MapColor.WHITE).requiresTool().strength(5).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block ALUMINUM_TRAPDOOR = registerBlock("aluminum_trapdoor",
            properties -> new TrapdoorBlock(BlockSetType.IRON, properties.mapColor(MapColor.WHITE).requiresTool().strength(5).nonOpaque().allowsSpawning(Blocks::never)));

    //Entity Blocks
    public static final Block CRUSHER = registerBlock("crusher",
            properties -> new CrusherBlock(properties.mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.5F).luminance(createLightLevelFromLitBlockState(13))));



    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Houseki.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(Houseki.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Houseki.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Houseki.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        Houseki.LOGGER.info("Registering ModBlocks for " + Houseki.MOD_ID);
    }
}
