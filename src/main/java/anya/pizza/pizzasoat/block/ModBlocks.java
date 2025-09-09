package anya.pizza.pizzasoat.block;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.block.custom.CrusherBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ModBlocks {
    //Adds Block
    public static final Block BLOCK_OF_PINKU = registerBlock("block_of_pinku",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).resistance(1000).requiresTool()));

    public static final Block BLOCK_OF_RAINBOW_PYRITE = registerBlock("block_of_rainbow_pyrite",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool()));

    public static final Block BLOCK_OF_TUNGSTEN = registerBlock("block_of_tungsten",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool().strength(40, 1000)));

    public static final Block BLOCK_OF_ALUMINUM = registerBlock("block_of_aluminum",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool().strength(30, 500)));

    public static final Block ALUMINUM_GLASS = registerBlock("aluminum_glass",
            new TranslucentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).requiresTool().strength(2, 1000).nonOpaque()) {
                @Override
                public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return super.getCameraCollisionShape(state, world, pos, context);
                }
            });

    public static final Block BLOCK_OF_SAPPHIRE = registerBlock("block_of_sapphire",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).requiresTool().strength(45, 500)));

    public static final Block BLOCK_OF_JADE = registerBlock("block_of_jade",
            new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).requiresTool()));

    public static final Block BLOCK_OF_PLATINUM = registerBlock("block_of_platinum",
            new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).requiresTool()));

    public static final Block LIMESTONE = registerBlock("limestone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));

    public static final Block LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool()));

    public static final Block POLISHED_LIMESTONE = registerBlock("polished_limestone",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE).requiresTool()));

    public static final Block CHISELED_LIMESTONE = registerBlock("chiseled_limestone",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS).requiresTool()));

    public static final Block BLOCK_OF_SULFUR = registerBlock("block_of_sulfur",
            new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK).requiresTool().luminance(s -> 7)));

    public static final Block SLATE = registerBlock("slate",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));

    public static final Block SLATE_TILES = registerBlock("slate_tiles",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool()));

    public static final Block POLISHED_SLATE = registerBlock("polished_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE).requiresTool()));

    public static final Block CHISELED_SLATE = registerBlock("chiseled_slate",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS).requiresTool()));

    public static final Block BLOCK_OF_STEEL = registerBlock("block_of_steel",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).strength(4.5f, 5.5f).requiresTool()));

    public static final Block BLOCK_OF_CAST_STEEL = registerBlock("block_of_cast_steel",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).strength(5.5f, 6.5f).requiresTool()));

    public static final Block BAUXITE = registerBlock("bauxite",
            new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).requiresTool()));


    //Adds Ore
    public static final Block PINKU_ORE = registerBlock("pinku_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE).sounds(BlockSoundGroup.NETHERITE).strength(15, 9).luminance(s -> 1).requiresTool())); //10 Moh Scale

    public static final Block RAINBOW_PYRITE_ORE = registerBlock("rainbow_pyrite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.STONE).strength(4.8f, 7.2f).requiresTool())); //6 Moh Scale

    public static final Block SANDSTONE_RAINBOW_PYRITE_ORE = registerBlock("sandstone_rainbow_pyrite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.SANDSTONE).strength(4.8f, 7.2f).requiresTool()));

    public static final Block WOLFRAMITE_ORE = registerBlock("wolframite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.GRANITE).strength(3, 4).requiresTool())); //4.5 Moh Scale

    public static final Block SCHEELITE_ORE = registerBlock("scheelite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.NETHERRACK).strength(3, 4).requiresTool())); //4.5 Moh Scale

    //public static final Block TRONA_ORE = registerBlock("trona_ore",
    //        new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.STONE).strength(2, 3).requiresTool())); //2.5 Moh Scale

    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(5, 8), AbstractBlock.Settings.copy(Blocks.DEEPSLATE).strength(10, 8).requiresTool()));

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(5, 8), AbstractBlock.Settings.copy(Blocks.STONE).strength(9, 7).requiresTool())); //9 Moh Scale

    public static final Block NEPHRITE_ORE = registerBlock("nephrite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.STONE).strength(4, 6).requiresTool())); //6.5 Moh Scale

    public static final Block DEEPSLATE_NEPHRITE_ORE = registerBlock("deepslate_nephrite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.DEEPSLATE).strength(4.5f, 6.5f).requiresTool()));

    public static final Block PLATINUM_ORE = registerBlock("platinum_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.STONE).strength(3, 4).requiresTool())); //4.5 Moh Scale

    public static final Block DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.DEEPSLATE).strength(4, 5).requiresTool()));

    public static final Block SULFUR_ORE = registerBlock("sulfur_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.NETHERRACK).strength(1, 2).requiresTool())); //Moh scale 1.5

    public static final Block BLACKSTONE_SULFUR_ORE = registerBlock("blackstone_sulfur_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.copy(Blocks.BLACKSTONE).strength(1.5f, 2.5f).requiresTool()));

    //Stairs
    public static final Block LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.STONE_STAIRS).requiresTool()));
    public static final Block POLISHED_LIMESTONE_STAIRS = registerBlock("polished_limestone_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_STAIRS).requiresTool()));
    public static final Block LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.BRICK_STAIRS).requiresTool()));
    public static final Block SLATE_STAIRS = registerBlock("slate_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.STONE_STAIRS).requiresTool()));
    public static final Block POLISHED_SLATE_STAIRS = registerBlock("polished_slate_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_STAIRS).requiresTool()));
    public static final Block SLATE_TILE_STAIRS = registerBlock("slate_tile_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.BRICK_STAIRS).requiresTool()));

    //Slabs
    public static final Block LIMESTONE_SLAB = registerBlock("limestone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_SLAB).requiresTool()));
    public static final Block POLISHED_LIMESTONE_SLAB = registerBlock("polished_limestone_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_SLAB).requiresTool()));
    public static final Block LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.BRICK_SLAB).requiresTool()));
    public static final Block SLATE_SLAB = registerBlock("slate_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_SLAB).requiresTool()));
    public static final Block POLISHED_SLATE_SLAB = registerBlock("polished_slate_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_SLAB).requiresTool()));
    public static final Block SLATE_TILE_SLAB = registerBlock("slate_tile_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.BRICK_SLAB).requiresTool()));

    //Walls
    public static final Block LIMESTONE_WALL = registerBlock("limestone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE_WALL).requiresTool()));
    public static final Block POLISHED_LIMESTONE_WALL = registerBlock("polished_limestone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_WALL).requiresTool()));
    public static final Block LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL).requiresTool()));
    public static final Block SLATE_WALL = registerBlock("slate_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE_WALL).requiresTool()));
    public static final Block POLISHED_SLATE_WALL = registerBlock("polished_slate_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_WALL).requiresTool()));
    public static final Block SLATE_TILE_WALL = registerBlock("slate_tile_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_WALL).requiresTool()));

    //Doors
    public static final Block ALUMINUM_DOOR = registerBlock("aluminum_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.IRON_DOOR).requiresTool().nonOpaque()));

    public static final Block ALUMINUM_TRAPDOOR = registerBlock("aluminum_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR).requiresTool().nonOpaque()));

    //Entity Blocks
    public static final Block CRUSHER = registerBlock("crusher",
            new CrusherBlock(AbstractBlock.Settings.copy(Blocks.BLAST_FURNACE).nonOpaque().requiresTool()));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PizzasOAT.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PizzasOAT.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PizzasOAT.LOGGER.info("Registering ModBlocks for " + PizzasOAT.MOD_ID);
    }
}
