package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    /**
     * Creates a ModBlockTagProvider for generating this mod's block tag data.
     *
     * @param output the FabricPackOutput used to write generated data files
     * @param registriesFuture a CompletableFuture that supplies the registry lookup provider needed when constructing tag values
     */
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Populates the mod's block tag assignments (mineability, tool requirements, beacon bases, immunities, and shape variants).
     *
     * This method registers which mod blocks belong to standard tags such as MINEABLE_WITH_PICKAXE, NEEDS_STONE_TOOL,
     * NEEDS_IRON_TOOL, NEEDS_DIAMOND_TOOL, BEACON_BASE_BLOCKS, WITHER_IMMUNE, DRAGON_IMMUNE, WALLS, STAIRS, SLABS, and the
     * mod-specific PREMIUM_DRILL_MINEABLE and ENHANCED_DRILL_MINEABLE groups.
     *
     * @param wrapperLookup provider for registry/holder lookups used when building tag value sets
     */
    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
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
                .add(ModBlocks.BLOCK_OF_CAST_STEEL_B);

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BAUXITE)
                .add(ModBlocks.SULFUR_ORE)
                .add(ModBlocks.BLACKSTONE_SULFUR_ORE)
                .add(ModBlocks.BLOCK_OF_SULFUR)
                .add(ModBlocks.ALUMINUM_DOOR)
                .add(ModBlocks.ALUMINUM_TRAPDOOR)
                .add(ModBlocks.CRUSHER);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
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
                .add(ModBlocks.BLOCK_OF_CAST_STEEL_B);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PINKU_ORE)
                .add(ModBlocks.SAPPHIRE_ORE)
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);

        valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.BLOCK_OF_RAINBOW_PYRITE)
                .add(ModBlocks.BLOCK_OF_TUNGSTEN_B)
                .add(ModBlocks.BLOCK_OF_ALUMINUM)
                .add(ModBlocks.BLOCK_OF_SAPPHIRE)
                .add(ModBlocks.BLOCK_OF_JADEITE)
                .add(ModBlocks.BLOCK_OF_PLATINUM)
                .add(ModBlocks.BLOCK_OF_STEEL)
                .add(ModBlocks.BLOCK_OF_CAST_STEEL_B);

        valueLookupBuilder(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.BLOCK_OF_SAPPHIRE)
                .add(ModBlocks.ALUMINUM_GLASS)
                .add(ModBlocks.ALUMINUM_GLASS_PANE);

        valueLookupBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.BLOCK_OF_PINKU)
                .add(ModBlocks.ALUMINUM_GLASS)
                .add(ModBlocks.ALUMINUM_GLASS_PANE);

        valueLookupBuilder(BlockTags.WALLS)
                .add(ModBlocks.LIMESTONE_WALL)
                .add(ModBlocks.LIMESTONE_BRICK_WALL)
                .add(ModBlocks.POLISHED_LIMESTONE_WALL)
                .add(ModBlocks.SLATE_WALL)
                .add(ModBlocks.SLATE_TILE_WALL)
                .add(ModBlocks.POLISHED_SLATE_WALL);

        valueLookupBuilder(BlockTags.STAIRS)
                .add(ModBlocks.LIMESTONE_STAIRS)
                .add(ModBlocks.POLISHED_LIMESTONE_STAIRS)
                .add(ModBlocks.LIMESTONE_BRICK_STAIRS)
                .add(ModBlocks.SLATE_STAIRS)
                .add(ModBlocks.POLISHED_SLATE_STAIRS)
                .add(ModBlocks.SLATE_TILE_STAIRS);

        valueLookupBuilder(BlockTags.SLABS)
                .add(ModBlocks.LIMESTONE_SLAB)
                .add(ModBlocks.POLISHED_LIMESTONE_SLAB)
                .add(ModBlocks.LIMESTONE_BRICK_SLAB)
                .add(ModBlocks.SLATE_SLAB)
                .add(ModBlocks.POLISHED_SLATE_SLAB)
                .add(ModBlocks.SLATE_TILE_SLAB);

        valueLookupBuilder(ModTags.Blocks.PREMIUM_DRILL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_SHOVEL)
                .addOptionalTag(BlockTags.MINEABLE_WITH_AXE);

        valueLookupBuilder(ModTags.Blocks.ENHANCED_DRILL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}