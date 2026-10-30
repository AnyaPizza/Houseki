package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

import java.util.Map;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlocks.PINKU_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_PINKU);
        blockStateModelGenerator.createTrivialCube(ModBlocks.WOLFRAMITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.NETHERRACK_WOLFRAMITE_ORE);
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
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_STEEL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_CAST_STEEL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.METEORIC_IRON);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLOCK_OF_METEORIC_IRON);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SUGILITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BISMUTH_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BISMUTH_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SUGILITE_ORE);

        blockStateModelGenerator.createFurnace(ModBlocks.CRUSHER, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockStateModelGenerator.createFurnace(ModBlocks.FOUNDRY, TexturedModel.ORIENTABLE_ONLY_TOP);

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

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PINKU_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WOLFRAMITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SCHEELITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRUSHED_BAUXITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_NUGGET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SULFUR_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRUDE_IRON, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NICKEL_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SUGILITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BISMUTH, ModelTemplates.FLAT_ITEM);

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
        itemModelGenerator.generateFlatItem(ModItems.PINKU_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SIMPLE_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENHANCED_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ADVANCED_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PREMIUM_TUNGSTEN_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SIMPLE_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENHANCED_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ADVANCED_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PREMIUM_DIAMOND_DRILL, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateTrimmableArmorSet(ModItems.PINKU_HELMET, ModItems.PINKU_CHESTPLATE, ModItems.PINKU_LEGGINGS, ModItems.PINKU_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.RAINBOW_PYRITE_HELMET, ModItems.RAINBOW_PYRITE_CHESTPLATE, ModItems.RAINBOW_PYRITE_LEGGINGS, ModItems.RAINBOW_PYRITE_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.TUNGSTEN_HELMET, ModItems.TUNGSTEN_CHESTPLATE, ModItems.TUNGSTEN_LEGGINGS, ModItems.TUNGSTEN_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.ALUMINUM_HELMET, ModItems.ALUMINUM_CHESTPLATE, ModItems.ALUMINUM_LEGGINGS, ModItems.ALUMINUM_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.NEPHRITE_HELMET, ModItems.NEPHRITE_CHESTPLATE, ModItems.NEPHRITE_LEGGINGS, ModItems.NEPHRITE_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.JADEITE_HELMET, ModItems.JADEITE_CHESTPLATE, ModItems.JADEITE_LEGGINGS, ModItems.JADEITE_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.PLATINUM_HELMET, ModItems.PLATINUM_CHESTPLATE, ModItems.PLATINUM_LEGGINGS, ModItems.PLATINUM_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.CAST_STEEL_HELMET, ModItems.CAST_STEEL_CHESTPLATE, ModItems.CAST_STEEL_LEGGINGS, ModItems.CAST_STEEL_BOOTS, false, Map.of());
        itemModelGenerator.generateTrimmableArmorSet(ModItems.METEORIC_IRON_HELMET, ModItems.METEORIC_IRON_CHESTPLATE, ModItems.METEORIC_IRON_LEGGINGS, ModItems.METEORIC_IRON_BOOTS, false, Map.of());

        itemModelGenerator.generateFlatItem(ModItems.PINKU_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PINKU_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ALUMINUM_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PLATINUM_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CAST_STEEL_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NEPHRITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JADEITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METEORIC_IRON_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.INGOT_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PICKAXE_HEAD_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AXE_HEAD_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SHOVEL_HEAD_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SWORD_HEAD_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.HOE_HEAD_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEAR_HEAD_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.HELMET_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHESTPLATE_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LEGGINGS_CAST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BOOTS_CAST, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CS_PICKAXE_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CS_AXE_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CS_SHOVEL_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CS_SWORD_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CS_HOE_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CS_SPEAR_HEAD, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.MI_PICKAXE_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MI_AXE_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MI_SHOVEL_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MI_SWORD_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MI_HOE_HEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MI_SPEAR_HEAD, ModelTemplates.FLAT_ITEM);
    }
}