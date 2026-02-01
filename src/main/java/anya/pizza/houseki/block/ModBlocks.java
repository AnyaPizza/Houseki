package anya.pizza.houseki.block;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.custom.CrusherBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.item.BlockItem;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

import static net.minecraft.world.level.block.Blocks.litBlockEmission;

public class ModBlocks {
    //Adds Block
    public static final Block BLOCK_OF_PINKU = registerBlock("block_of_pinku",
            properties -> new Block(properties.mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5, 1000).sound(SoundType.METAL)));

    public static final Block BLOCK_OF_RAINBOW_PYRITE = registerBlock("block_of_rainbow_pyrite",
            properties -> new Block(properties.mapColor(MapColor.COLOR_LIGHT_BLUE).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.METAL)));

    public static final Block BLOCK_OF_TUNGSTEN_B = registerBlock("block_of_tungsten_b",
            properties -> new Block(properties.mapColor(MapColor.CLAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(10, 1000).sound(SoundType.METAL)));

    public static final Block BLOCK_OF_ALUMINUM = registerBlock("block_of_aluminum",
            properties -> new Block(properties.mapColor(MapColor.SNOW).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(8, 500).sound(SoundType.METAL)));

    public static final Block ALUMINUM_GLASS = registerBlock("aluminum_glass",
            properties -> new TransparentBlock(properties.instrument(NoteBlockInstrument.HAT).strength(2, 1000).sound(SoundType.GLASS)
                    .noOcclusion().isValidSpawn(Blocks::never).isRedstoneConductor(Blocks::never).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)) {
                @Override
                public @NonNull VoxelShape getVisualShape(@NonNull BlockState state, @NonNull BlockGetter world, @NonNull BlockPos pos, @NonNull CollisionContext context) {
                    return super.getVisualShape(state, world, pos, context);
                }
            });

    public static final Block ALUMINUM_GLASS_PANE = registerBlock("aluminum_glass_pane",
            properties -> new IronBarsBlock(properties.instrument(NoteBlockInstrument.HAT).strength(2, 1000).sound(SoundType.GLASS).noOcclusion()) {
                @Override
                public @NonNull VoxelShape getVisualShape(@NonNull BlockState state, @NonNull BlockGetter world, @NonNull BlockPos pos, @NonNull CollisionContext context) {
                    return super.getVisualShape(state, world, pos, context);
                }
            });

    public static final Block BLOCK_OF_SAPPHIRE = registerBlock("block_of_sapphire",
            properties -> new Block(properties.mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(9, 500).sound(SoundType.NETHERITE_BLOCK)));

    public static final Block BLOCK_OF_JADEITE = registerBlock("block_of_jadeite",
            properties -> new Block(properties.mapColor(MapColor.GRASS).requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.METAL)));

    public static final Block BLOCK_OF_PLATINUM = registerBlock("block_of_platinum",
            properties -> new Block(properties.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops().strength(5, 6).sound(SoundType.METAL)));

    public static final Block LIMESTONE = registerBlock("limestone",
            properties -> new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            properties -> new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block POLISHED_LIMESTONE = registerBlock("polished_limestone",
            properties -> new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block CHISELED_LIMESTONE = registerBlock("chiseled_limestone",
            properties -> new Block(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block BLOCK_OF_SULFUR = registerBlock("block_of_sulfur",
            properties -> new Block(properties.mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5, 6).lightLevel(s -> 7)));

    public static final Block SLATE = registerBlock("slate",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block SLATE_TILES = registerBlock("slate_tiles",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block POLISHED_SLATE = registerBlock("polished_slate",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block CHISELED_SLATE = registerBlock("chiseled_slate",
            properties -> new Block(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6)));

    public static final Block BLOCK_OF_STEEL = registerBlock("block_of_steel",
            properties -> new Block(properties.mapColor(MapColor.CLAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(4.5F, 5.5F).sound(SoundType.METAL)));

    public static final Block BLOCK_OF_CAST_STEEL_B = registerBlock("block_of_cast_steel_b",
            properties -> new Block(properties.mapColor(MapColor.CLAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.5F, 6.5F).sound(SoundType.METAL)));

    public static final Block BAUXITE = registerBlock("bauxite",
            properties -> new Block(properties.mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));


    //Adds Ore
    public static final Block PINKU_ORE = registerBlock("pinku_ore",
            properties -> new Block(properties
                    .mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK).strength(15, 9).lightLevel(s -> 1)));
                        //10 Moh Scale
    public static final Block RAINBOW_PYRITE_ORE = registerBlock("rainbow_pyrite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4, 7)));
                        //6 Moh Scale
    public static final Block SANDSTONE_RAINBOW_PYRITE_ORE = registerBlock("sandstone_rainbow_pyrite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties
                    .mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.8F, 7.2F)));

    public static final Block BAUXITE_RAINBOW_PYRITE_ORE = registerBlock("bauxite_rainbow_pyrite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.8F, 7.2F)));

    public static final Block WOLFRAMITE_ORE = registerBlock("wolframite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3, 4)));
                        //4.5 Moh Scale
    public static final Block SCHEELITE_ORE = registerBlock("scheelite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3, 4).sound(SoundType.NETHERRACK)));
                        //4.5 Moh Scale
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            properties -> new DropExperienceBlock(UniformInt.of(5, 8), properties
                    .mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(10, 8).sound(SoundType.DEEPSLATE)));

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            properties -> new DropExperienceBlock(UniformInt.of(5, 8), properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(9, 7)));
                        //9 Moh Scale
    public static final Block NEPHRITE_ORE = registerBlock("nephrite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.5F, 6.5F)));
                        //6.5 Moh Scale
    public static final Block JADEITE_ORE = registerBlock("jadeite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5, 7)));
                        //7 Moh Scale
    public static final Block PLATINUM_ORE = registerBlock("platinum_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3, 4)));
                        //4.5 Moh Scale
    public static final Block DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3, 4).sound(SoundType.DEEPSLATE)));

    public static final Block SULFUR_ORE = registerBlock("sulfur_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1, 2).sound(SoundType.NETHERRACK)));
                        //Moh scale 1.5
    public static final Block BLACKSTONE_SULFUR_ORE = registerBlock("blackstone_sulfur_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 2.5F)));

    //Stairs
    public static final Block LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            properties -> new StairBlock(ModBlocks.LIMESTONE.defaultBlockState(),properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block POLISHED_LIMESTONE_STAIRS = registerBlock("polished_limestone_stairs",
            properties -> new StairBlock(ModBlocks.LIMESTONE.defaultBlockState(),properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs",
            properties -> new StairBlock(ModBlocks.LIMESTONE.defaultBlockState(),properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block SLATE_STAIRS = registerBlock("slate_stairs",
            properties -> new StairBlock(ModBlocks.LIMESTONE.defaultBlockState(),properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block POLISHED_SLATE_STAIRS = registerBlock("polished_slate_stairs",
            properties -> new StairBlock(ModBlocks.LIMESTONE.defaultBlockState(),properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block SLATE_TILE_STAIRS = registerBlock("slate_tile_stairs",
            properties -> new StairBlock(ModBlocks.LIMESTONE.defaultBlockState(),properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));

    //Slabs
    public static final Block LIMESTONE_SLAB = registerBlock("limestone_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block POLISHED_LIMESTONE_SLAB = registerBlock("polished_limestone_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block SLATE_SLAB = registerBlock("slate_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block POLISHED_SLATE_SLAB = registerBlock("polished_slate_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));
    public static final Block SLATE_TILE_SLAB = registerBlock("slate_tile_slab",
            properties -> new SlabBlock(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6)));

    //Walls
    public static final Block LIMESTONE_WALL = registerBlock("limestone_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6).forceSolidOn()));
    public static final Block POLISHED_LIMESTONE_WALL = registerBlock("polished_limestone_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6).forceSolidOn()));
    public static final Block LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6).forceSolidOn()));
    public static final Block SLATE_WALL = registerBlock("slate_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6).forceSolidOn()));
    public static final Block POLISHED_SLATE_WALL = registerBlock("polished_slate_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6).forceSolidOn()));
    public static final Block SLATE_TILE_WALL = registerBlock("slate_tile_wall",
            properties -> new WallBlock(properties.mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2, 6).forceSolidOn()));

    //Doors
    public static final Block ALUMINUM_DOOR = registerBlock("aluminum_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(5).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block ALUMINUM_TRAPDOOR = registerBlock("aluminum_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.IRON, properties.mapColor(MapColor.SNOW).requiresCorrectToolForDrops().strength(5).noOcclusion().isValidSpawn(Blocks::never)));

    //Entity Blocks
    public static final Block CRUSHER = registerBlock("crusher",
            properties -> new CrusherBlock(properties
                    .mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(litBlockEmission(13))));



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name),
                new BlockItem(block, new net.minecraft.world.item.Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        Houseki.LOGGER.info("Registering ModBlocks for " + Houseki.MOD_ID);
    }
}
