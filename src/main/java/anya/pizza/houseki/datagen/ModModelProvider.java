package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModArmorMaterials;
import anya.pizza.houseki.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    /**
     * Creates a ModModelProvider backed by the given pack output.
     *
     * @param output the FabricPackOutput to write generated model and asset files to
     */
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    /**
     * Generates block state and model definitions for this mod's blocks.
     *
     * Registers cube models for ores and storage blocks, glass panes, doors, trapdoors,
     * an orientable furnace (crusher), and family-derived stairs, slabs, and walls
     * for limestone and slate block families.
     *
     * @param blockStateModelGenerator the model generator used to create block models and families
     */
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlocks.PINKU_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_PINKU);
        blockStateModelGenerator.createTrivialCube(ModBlocks.WOLFRAMITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAINBOW_PYRITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SCHEELITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_TUNGSTEN_B);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BAUXITE);
        blockStateModelGenerator.createGlassBlocks(ModBlocks.ALUMINUM_GLASS, ModBlocks.ALUMINUM_GLASS_PANE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_ALUMINUM);
        blockStateModelGenerator.createDoor(ModBlocks.ALUMINUM_DOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.ALUMINUM_TRAPDOOR);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_SAPPHIRE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.NEPHRITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.JADEITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_JADEITE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_PLATINUM);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_PLATINUM_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.PLATINUM_ORE);
        BlockModelGenerators.BlockFamilyProvider limestoneBrickPool = blockStateModelGenerator.family(ModBlocks.LIMESTONE_BRICKS);
        BlockModelGenerators.BlockFamilyProvider limestonePool = blockStateModelGenerator.family(ModBlocks.LIMESTONE);
        BlockModelGenerators.BlockFamilyProvider polishedLimestonePool = blockStateModelGenerator.family(ModBlocks.POLISHED_LIMESTONE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_LIMESTONE);
        BlockModelGenerators.BlockFamilyProvider slateTilesPool = blockStateModelGenerator.family(ModBlocks.SLATE_TILES);
        BlockModelGenerators.BlockFamilyProvider slatePool = blockStateModelGenerator.family(ModBlocks.SLATE);
        BlockModelGenerators.BlockFamilyProvider polishedSlatePool = blockStateModelGenerator.family(ModBlocks.POLISHED_SLATE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_SLATE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLACKSTONE_SULFUR_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SULFUR_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_SULFUR);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_STEEL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_CAST_STEEL_B);

        blockStateModelGenerator.createFurnace(ModBlocks.CRUSHER, TexturedModel.ORIENTABLE_ONLY_TOP);

        limestonePool.stairs(ModBlocks.LIMESTONE_STAIRS);
        limestoneBrickPool.stairs(ModBlocks.LIMESTONE_BRICK_STAIRS);
        polishedLimestonePool.stairs(ModBlocks.POLISHED_LIMESTONE_STAIRS);
        limestonePool.slab(ModBlocks.LIMESTONE_SLAB);
        limestoneBrickPool.slab(ModBlocks.LIMESTONE_BRICK_SLAB);
        polishedLimestonePool.slab(ModBlocks.POLISHED_LIMESTONE_SLAB);
        limestonePool.wall(ModBlocks.LIMESTONE_WALL);
        limestoneBrickPool.wall(ModBlocks.LIMESTONE_BRICK_WALL);
        polishedLimestonePool.wall(ModBlocks.POLISHED_LIMESTONE_WALL);
        slatePool.stairs(ModBlocks.SLATE_STAIRS);
        slateTilesPool.stairs(ModBlocks.SLATE_TILE_STAIRS);
        polishedSlatePool.stairs(ModBlocks.POLISHED_SLATE_STAIRS);
        slatePool.slab(ModBlocks.SLATE_SLAB);
        slateTilesPool.slab(ModBlocks.SLATE_TILE_SLAB);
        polishedSlatePool.slab(ModBlocks.POLISHED_SLATE_SLAB);
        slatePool.wall(ModBlocks.SLATE_WALL);
        slateTilesPool.wall(ModBlocks.SLATE_TILE_WALL);
        polishedSlatePool.wall(ModBlocks.POLISHED_SLATE_WALL);
    }

    /**
     * Registers item model templates for all mod items (flat, handheld, trimmable, horse, and nautilus variants).
     *
     * @param itemModelGenerator generator used to create item models and trim variants
     */
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WOLFRAMITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SCHEELITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRUSHED_BAUXITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_NUGGET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SULFUR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRUDE_IRON, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_DRILL_BIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SIMPLE_DRILL_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENHANCED_DRILL_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ADVANCED_DRILL_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PREMIUM_DRILL_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_DRILL_BIT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PINKU_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SIMPLE_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENHANCED_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ADVANCED_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PREMIUM_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SIMPLE_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENHANCED_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ADVANCED_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PREMIUM_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateTrimmableItem(ModItems.PINKU_HELMET, ModArmorMaterials.PINKU_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PINKU_CHESTPLATE, ModArmorMaterials.PINKU_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PINKU_LEGGINGS, ModArmorMaterials.PINKU_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PINKU_BOOTS, ModArmorMaterials.PINKU_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.RAINBOW_PYRITE_HELMET, ModArmorMaterials.RAINBOW_PYRITE_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.RAINBOW_PYRITE_CHESTPLATE, ModArmorMaterials.RAINBOW_PYRITE_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.RAINBOW_PYRITE_LEGGINGS,  ModArmorMaterials.RAINBOW_PYRITE_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.RAINBOW_PYRITE_BOOTS, ModArmorMaterials.RAINBOW_PYRITE_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_HELMET, ModArmorMaterials.TUNGSTEN_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_CHESTPLATE, ModArmorMaterials.TUNGSTEN_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_LEGGINGS,  ModArmorMaterials.TUNGSTEN_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_BOOTS, ModArmorMaterials.TUNGSTEN_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.ALUMINUM_HELMET, ModArmorMaterials.ALUMINUM_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.ALUMINUM_CHESTPLATE, ModArmorMaterials.ALUMINUM_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.ALUMINUM_LEGGINGS,  ModArmorMaterials.ALUMINUM_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.ALUMINUM_BOOTS, ModArmorMaterials.ALUMINUM_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_HELMET, ModArmorMaterials.SAPPHIRE_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_CHESTPLATE, ModArmorMaterials.SAPPHIRE_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_LEGGINGS,  ModArmorMaterials.SAPPHIRE_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.SAPPHIRE_BOOTS, ModArmorMaterials.SAPPHIRE_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.NEPHRITE_HELMET, ModArmorMaterials.NEPHRITE_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.NEPHRITE_CHESTPLATE, ModArmorMaterials.NEPHRITE_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.NEPHRITE_LEGGINGS,  ModArmorMaterials.NEPHRITE_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.NEPHRITE_BOOTS, ModArmorMaterials.NEPHRITE_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.JADEITE_HELMET, ModArmorMaterials.JADEITE_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.JADEITE_CHESTPLATE, ModArmorMaterials.JADEITE_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.JADEITE_LEGGINGS,  ModArmorMaterials.JADEITE_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.JADEITE_BOOTS, ModArmorMaterials.JADEITE_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PLATINUM_HELMET, ModArmorMaterials.PLATINUM_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PLATINUM_CHESTPLATE, ModArmorMaterials.PLATINUM_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PLATINUM_LEGGINGS,  ModArmorMaterials.PLATINUM_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.PLATINUM_BOOTS, ModArmorMaterials.PLATINUM_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.STEEL_HELMET, ModArmorMaterials.STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.STEEL_CHESTPLATE, ModArmorMaterials.STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.STEEL_LEGGINGS,  ModArmorMaterials.STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.STEEL_BOOTS, ModArmorMaterials.STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.CAST_STEEL_HELMET, ModArmorMaterials.CAST_STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.CAST_STEEL_CHESTPLATE, ModArmorMaterials.CAST_STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.CAST_STEEL_LEGGINGS,  ModArmorMaterials.CAST_STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.CAST_STEEL_BOOTS, ModArmorMaterials.CAST_STEEL_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerator.generateFlatItem(ModItems.PINKU_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PINKU_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
    }
}