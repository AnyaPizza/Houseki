package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PINKU))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_RAINBOW_PYRITE))
                .add(ModBlocks.getRK(ModBlocks.PINKU_ORE))
                .add(ModBlocks.getRK(ModBlocks.RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.WOLFRAMITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.NETHERRACK_WOLFRAMITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SCHEELITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.CRUSHER))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_TUNGSTEN_B))
                .add(ModBlocks.getRK(ModBlocks.BAUXITE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_ALUMINUM))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS_PANE))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_DOOR))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_SAPPHIRE))
                .add(ModBlocks.getRK(ModBlocks.SAPPHIRE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_SAPPHIRE_ORE))
                .add(ModBlocks.getRK(ModBlocks.NEPHRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.JADEITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_JADEITE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PLATINUM))
                .add(ModBlocks.getRK(ModBlocks.PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICKS))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICK_SLAB))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICK_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICK_WALL))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.CHISELED_LIMESTONE))
                .add(ModBlocks.getRK(ModBlocks.SLATE))
                .add(ModBlocks.getRK(ModBlocks.SLATE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.SLATE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.SLATE_WALL))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILES))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILE_WALL))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE_WALL))
                .add(ModBlocks.getRK(ModBlocks.CHISELED_SLATE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_STEEL))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_CAST_STEEL))
                .add(ModBlocks.getRK(ModBlocks.METEORIC_IRON))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_METEORIC_IRON))
                .add(ModBlocks.getRK(ModBlocks.FOUNDRY))
                .add(ModBlocks.getRK(ModBlocks.SUGILITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.SUGILITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BISMUTH_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.BISMUTH_ORE));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.getRK(ModBlocks.BAUXITE))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_DOOR))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.CRUSHER))
                .add(ModBlocks.getRK(ModBlocks.FOUNDRY));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PINKU))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_RAINBOW_PYRITE))
                .add(ModBlocks.getRK(ModBlocks.WOLFRAMITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.NETHERRACK_WOLFRAMITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SCHEELITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_TUNGSTEN_B))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_ALUMINUM))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS_PANE))
                .add(ModBlocks.getRK(ModBlocks.NEPHRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.JADEITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_JADEITE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PLATINUM))
                .add(ModBlocks.getRK(ModBlocks.PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_STEEL))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_CAST_STEEL))
                .add(ModBlocks.getRK(ModBlocks.BISMUTH_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.BISMUTH_ORE))
                .add(ModBlocks.getRK(ModBlocks.SUGILITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.SUGILITE_ORE));

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.PINKU_ORE))
                .add(ModBlocks.getRK(ModBlocks.SAPPHIRE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_SAPPHIRE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_SAPPHIRE))
                .add(ModBlocks.getRK(ModBlocks.METEORIC_IRON))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_METEORIC_IRON));

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PINKU))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_RAINBOW_PYRITE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_TUNGSTEN_B))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_ALUMINUM))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_SAPPHIRE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_JADEITE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PLATINUM))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_STEEL))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_CAST_STEEL))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_METEORIC_IRON))
                .add(ModBlocks.getRK(ModBlocks.SUGILITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.BISMUTH_BLOCK));

        tag(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PINKU))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_SAPPHIRE))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS_PANE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_METEORIC_IRON))
                .add(ModBlocks.getRK(ModBlocks.METEORIC_IRON));

        tag(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_PINKU))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS))
                .add(ModBlocks.getRK(ModBlocks.ALUMINUM_GLASS_PANE))
                .add(ModBlocks.getRK(ModBlocks.BLOCK_OF_METEORIC_IRON))
                .add(ModBlocks.getRK(ModBlocks.METEORIC_IRON));

        tag(BlockTags.WALLS)
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICK_WALL))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.SLATE_WALL))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILE_WALL))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE_WALL));

        tag(BlockTags.STAIRS)
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICK_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.SLATE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILE_STAIRS));

        tag(BlockTags.SLABS)
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_LIMESTONE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.LIMESTONE_BRICK_SLAB))
                .add(ModBlocks.getRK(ModBlocks.SLATE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_SLATE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.SLATE_TILE_SLAB));

        tag(ModTags.Blocks.PREMIUM_DRILL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_SHOVEL)
                .addOptionalTag(BlockTags.MINEABLE_WITH_AXE);

        tag(ModTags.Blocks.ENHANCED_DRILL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_SHOVEL);

        tag(ModTags.Blocks.COAL_ORES)
                .add(BlockItemIds.COAL_ORE.block())
                .add(BlockItemIds.DEEPSLATE_COAL_ORE.block());

        tag(ModTags.Blocks.METEOR_WONT_REPLACE)
                .forceAddTag(ModTags.Blocks.COAL_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .add(ModBlocks.getRK(ModBlocks.PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_PLATINUM_ORE))
                .add(BlockItemIds.COBBLESTONE.block())
                .add(BlockItemIds.COBBLED_DEEPSLATE.block());

        tag(ModTags.Blocks.HOUSEKI_ORES)
                .add(ModBlocks.getRK(ModBlocks.PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.WOLFRAMITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.NETHERRACK_WOLFRAMITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SCHEELITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.SAPPHIRE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_SAPPHIRE_ORE))
                .add(ModBlocks.getRK(ModBlocks.NEPHRITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.JADEITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.DEEPSLATE_PLATINUM_ORE))
                .add(ModBlocks.getRK(ModBlocks.BISMUTH_ORE))
                .add(ModBlocks.getRK(ModBlocks.SUGILITE_ORE));
    }
}