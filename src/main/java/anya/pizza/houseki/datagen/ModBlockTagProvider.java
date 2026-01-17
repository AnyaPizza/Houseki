package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.BLOCK_OF_RAINBOW_PYRITE)
                .add(ModBlocks.PINKU_ORE)
                .add(ModBlocks.RAINBOW_PYRITE_ORE)
                .add(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE)
                .add(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE)
                .add(ModBlocks.WOLFRAMITE_ORE)
                .add(ModBlocks.SCHEELITE_ORE)
                .add(ModBlocks.CRUSHER)
                .add(ModBlocks.BLOCK_OF_TUNGSTEN_B)
                .add(ModBlocks.BAUXITE)
                .add(ModBlocks.BLOCK_OF_ALUMINUM)
                .add(ModBlocks.ALUMINUM_GLASS)
                .add(ModBlocks.ALUMINUM_GLASS_PANE)
                .add(ModBlocks.ALUMINUM_DOOR)
                .add(ModBlocks.ALUMINUM_TRAPDOOR)
                .add(ModBlocks.BLOCK_OF_SAPPHIRE)
                .add(ModBlocks.SAPPHIRE_ORE)
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
                .add(ModBlocks.NEPHRITE_ORE)
                .add(ModBlocks.JADEITE_ORE)
                .add(ModBlocks.BLOCK_OF_JADEITE)
                .add(ModBlocks.BLOCK_OF_PLATINUM)
                .add(ModBlocks.PLATINUM_ORE)
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE)
                .add(ModBlocks.LIMESTONE)
                .add(ModBlocks.LIMESTONE_SLAB)
                .add(ModBlocks.LIMESTONE_STAIRS)
                .add(ModBlocks.LIMESTONE_WALL)
                .add(ModBlocks.LIMESTONE_BRICKS)
                .add(ModBlocks.LIMESTONE_BRICK_SLAB)
                .add(ModBlocks.LIMESTONE_BRICK_STAIRS)
                .add(ModBlocks.LIMESTONE_BRICK_WALL)
                .add(ModBlocks.POLISHED_LIMESTONE)
                .add(ModBlocks.POLISHED_LIMESTONE_SLAB)
                .add(ModBlocks.POLISHED_LIMESTONE_STAIRS)
                .add(ModBlocks.POLISHED_LIMESTONE_WALL)
                .add(ModBlocks.CHISELED_LIMESTONE)
                .add(ModBlocks.SLATE)
                .add(ModBlocks.SLATE_SLAB)
                .add(ModBlocks.SLATE_STAIRS)
                .add(ModBlocks.SLATE_WALL)
                .add(ModBlocks.SLATE_TILES)
                .add(ModBlocks.SLATE_TILE_SLAB)
                .add(ModBlocks.SLATE_TILE_STAIRS)
                .add(ModBlocks.SLATE_TILE_WALL)
                .add(ModBlocks.POLISHED_SLATE)
                .add(ModBlocks.POLISHED_SLATE_SLAB)
                .add(ModBlocks.POLISHED_SLATE_STAIRS)
                .add(ModBlocks.POLISHED_SLATE_WALL)
                .add(ModBlocks.CHISELED_SLATE)
                .add(ModBlocks.BLOCK_OF_SULFUR)
                .add(ModBlocks.BLACKSTONE_SULFUR_ORE)
                .add(ModBlocks.SULFUR_ORE)
                .add(ModBlocks.BLOCK_OF_STEEL)
                .add(ModBlocks.BLOCK_OF_CAST_STEEL);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BAUXITE)
                .add(ModBlocks.SULFUR_ORE)
                .add(ModBlocks.BLACKSTONE_SULFUR_ORE)
                .add(ModBlocks.BLOCK_OF_SULFUR)
                .add(ModBlocks.ALUMINUM_DOOR)
                .add(ModBlocks.ALUMINUM_TRAPDOOR)
                .add(ModBlocks.CRUSHER);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.BLOCK_OF_RAINBOW_PYRITE)
                .add(ModBlocks.BLOCK_OF_SAPPHIRE)
                .add(ModBlocks.WOLFRAMITE_ORE)
                .add(ModBlocks.RAINBOW_PYRITE_ORE)
                .add(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE)
                .add(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE)
                .add(ModBlocks.SCHEELITE_ORE)
                .add(ModBlocks.BLOCK_OF_TUNGSTEN_B)
                .add(ModBlocks.BLOCK_OF_ALUMINUM)
                .add(ModBlocks.ALUMINUM_GLASS)
                .add(ModBlocks.ALUMINUM_GLASS_PANE)
                .add(ModBlocks.NEPHRITE_ORE)
                .add(ModBlocks.JADEITE_ORE)
                .add(ModBlocks.BLOCK_OF_JADEITE)
                .add(ModBlocks.BLOCK_OF_PLATINUM)
                .add(ModBlocks.PLATINUM_ORE)
                .add(ModBlocks.DEEPSLATE_PLATINUM_ORE)
                .add(ModBlocks.BLOCK_OF_STEEL)
                .add(ModBlocks.BLOCK_OF_CAST_STEEL);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PINKU_ORE)
                .add(ModBlocks.SAPPHIRE_ORE)
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.BLOCK_OF_RAINBOW_PYRITE)
                .add(ModBlocks.BLOCK_OF_TUNGSTEN_B)
                .add(ModBlocks.BLOCK_OF_ALUMINUM)
                .add(ModBlocks.BLOCK_OF_SAPPHIRE)
                .add(ModBlocks.BLOCK_OF_JADEITE)
                .add(ModBlocks.BLOCK_OF_PLATINUM)
                .add(ModBlocks.BLOCK_OF_STEEL)
                .add(ModBlocks.BLOCK_OF_CAST_STEEL);

        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.BLOCK_OF_SAPPHIRE)
                .add(ModBlocks.ALUMINUM_GLASS)
                .add(ModBlocks.ALUMINUM_GLASS_PANE);

        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.ALUMINUM_GLASS)
                .add(ModBlocks.ALUMINUM_GLASS_PANE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.LIMESTONE_WALL)
                .add(ModBlocks.LIMESTONE_BRICK_WALL)
                .add(ModBlocks.POLISHED_LIMESTONE_WALL)
                .add(ModBlocks.SLATE_WALL)
                .add(ModBlocks.SLATE_TILE_WALL)
                .add(ModBlocks.POLISHED_SLATE_WALL);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.LIMESTONE_STAIRS)
                .add(ModBlocks.POLISHED_LIMESTONE_STAIRS)
                .add(ModBlocks.LIMESTONE_BRICK_STAIRS)
                .add(ModBlocks.SLATE_STAIRS)
                .add(ModBlocks.POLISHED_SLATE_STAIRS)
                .add(ModBlocks.SLATE_TILE_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.LIMESTONE_SLAB)
                .add(ModBlocks.POLISHED_LIMESTONE_SLAB)
                .add(ModBlocks.LIMESTONE_BRICK_SLAB)
                .add(ModBlocks.SLATE_SLAB)
                .add(ModBlocks.POLISHED_SLATE_SLAB)
                .add(ModBlocks.SLATE_TILE_SLAB);

        getOrCreateTagBuilder(ModTags.Blocks.PREMIUM_DRILL_MINEABLE)
                .addTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE)
                .forceAddTag(BlockTags.AXE_MINEABLE);

        getOrCreateTagBuilder(ModTags.Blocks.ENHANCED_DRILL_MINEABLE)
                .addTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE);
    }
}
