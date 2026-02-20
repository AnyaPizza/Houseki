package anya.pizza.houseki.item;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HOUSEKI_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Houseki.MOD_ID, "houseki_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINKU))
                    .displayName(Text.translatable("itemgroup.houseki.housekiitems"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINKU_HELMET);
                        entries.add(ModItems.PINKU_CHESTPLATE);
                        entries.add(ModItems.PINKU_LEGGINGS);
                        entries.add(ModItems.PINKU_BOOTS);
                        entries.add(ModItems.PINKU_PICKAXE);
                        entries.add(ModItems.PINKU_AXE);
                        entries.add(ModItems.PINKU_SHOVEL);
                        entries.add(ModItems.PINKU_SWORD);
                        entries.add(ModItems.PINKU_HOE);

                        entries.add(ModItems.RAINBOW_PYRITE_HELMET);
                        entries.add(ModItems.RAINBOW_PYRITE_CHESTPLATE);
                        entries.add(ModItems.RAINBOW_PYRITE_LEGGINGS);
                        entries.add(ModItems.RAINBOW_PYRITE_BOOTS);
                        entries.add(ModItems.RAINBOW_PYRITE_PICKAXE);
                        entries.add(ModItems.RAINBOW_PYRITE_AXE);
                        entries.add(ModItems.RAINBOW_PYRITE_SHOVEL);
                        entries.add(ModItems.RAINBOW_PYRITE_SWORD);
                        entries.add(ModItems.RAINBOW_PYRITE_HOE);

                        entries.add(ModItems.TUNGSTEN_HELMET);
                        entries.add(ModItems.TUNGSTEN_CHESTPLATE);
                        entries.add(ModItems.TUNGSTEN_LEGGINGS);
                        entries.add(ModItems.TUNGSTEN_BOOTS);
                        entries.add(ModItems.TUNGSTEN_PICKAXE);
                        entries.add(ModItems.TUNGSTEN_AXE);
                        entries.add(ModItems.TUNGSTEN_SHOVEL);
                        entries.add(ModItems.TUNGSTEN_SWORD);
                        entries.add(ModItems.TUNGSTEN_HOE);

                        entries.add(ModItems.ALUMINUM_HELMET);
                        entries.add(ModItems.ALUMINUM_CHESTPLATE);
                        entries.add(ModItems.ALUMINUM_LEGGINGS);
                        entries.add(ModItems.ALUMINUM_BOOTS);
                        entries.add(ModItems.ALUMINUM_PICKAXE);
                        entries.add(ModItems.ALUMINUM_AXE);
                        entries.add(ModItems.ALUMINUM_SHOVEL);
                        entries.add(ModItems.ALUMINUM_SWORD);
                        entries.add(ModItems.ALUMINUM_HOE);

                        entries.add(ModItems.SAPPHIRE_HELMET);
                        entries.add(ModItems.SAPPHIRE_CHESTPLATE);
                        entries.add(ModItems.SAPPHIRE_LEGGINGS);
                        entries.add(ModItems.SAPPHIRE_BOOTS);
                        entries.add(ModItems.SAPPHIRE_PICKAXE);
                        entries.add(ModItems.SAPPHIRE_AXE);
                        entries.add(ModItems.SAPPHIRE_SHOVEL);
                        entries.add(ModItems.SAPPHIRE_SWORD);
                        entries.add(ModItems.SAPPHIRE_HOE);

                        entries.add(ModItems.NEPHRITE_HELMET);
                        entries.add(ModItems.NEPHRITE_CHESTPLATE);
                        entries.add(ModItems.NEPHRITE_LEGGINGS);
                        entries.add(ModItems.NEPHRITE_BOOTS);
                        entries.add(ModItems.NEPHRITE_PICKAXE);
                        entries.add(ModItems.NEPHRITE_AXE);
                        entries.add(ModItems.NEPHRITE_SHOVEL);
                        entries.add(ModItems.NEPHRITE_SWORD);
                        entries.add(ModItems.NEPHRITE_HOE);

                        entries.add(ModItems.JADEITE_HELMET);
                        entries.add(ModItems.JADEITE_CHESTPLATE);
                        entries.add(ModItems.JADEITE_LEGGINGS);
                        entries.add(ModItems.JADEITE_BOOTS);
                        entries.add(ModItems.JADEITE_PICKAXE);
                        entries.add(ModItems.JADEITE_AXE);
                        entries.add(ModItems.JADEITE_SHOVEL);
                        entries.add(ModItems.JADEITE_SWORD);
                        entries.add(ModItems.JADEITE_HOE);

                        entries.add(ModItems.PLATINUM_HELMET);
                        entries.add(ModItems.PLATINUM_CHESTPLATE);
                        entries.add(ModItems.PLATINUM_LEGGINGS);
                        entries.add(ModItems.PLATINUM_BOOTS);
                        entries.add(ModItems.PLATINUM_PICKAXE);
                        entries.add(ModItems.PLATINUM_AXE);
                        entries.add(ModItems.PLATINUM_SHOVEL);
                        entries.add(ModItems.PLATINUM_SWORD);
                        entries.add(ModItems.PLATINUM_HOE);

                        entries.add(ModItems.STEEL_HELMET);
                        entries.add(ModItems.STEEL_CHESTPLATE);
                        entries.add(ModItems.STEEL_LEGGINGS);
                        entries.add(ModItems.STEEL_BOOTS);
                        entries.add(ModItems.STEEL_PICKAXE);
                        entries.add(ModItems.STEEL_AXE);
                        entries.add(ModItems.STEEL_SHOVEL);
                        entries.add(ModItems.STEEL_SWORD);
                        entries.add(ModItems.STEEL_HOE);

                        entries.add(ModItems.CAST_STEEL_HELMET);
                        entries.add(ModItems.CAST_STEEL_CHESTPLATE);
                        entries.add(ModItems.CAST_STEEL_LEGGINGS);
                        entries.add(ModItems.CAST_STEEL_BOOTS);
                        entries.add(ModItems.CAST_STEEL_PICKAXE);
                        entries.add(ModItems.CAST_STEEL_AXE);
                        entries.add(ModItems.CAST_STEEL_SHOVEL);
                        entries.add(ModItems.CAST_STEEL_SWORD);
                        entries.add(ModItems.CAST_STEEL_HOE);

                        entries.add(ModItems.PINKU_HORSE_ARMOR);
                        entries.add(ModItems.RAINBOW_PYRITE_HORSE_ARMOR);
                        entries.add(ModItems.TUNGSTEN_HORSE_ARMOR);
                        entries.add(ModItems.ALUMINUM_HORSE_ARMOR);
                        entries.add(ModItems.SAPPHIRE_HORSE_ARMOR);
                        entries.add(ModItems.PLATINUM_HORSE_ARMOR);
                        entries.add(ModItems.STEEL_HORSE_ARMOR);
                        entries.add(ModItems.CAST_STEEL_HORSE_ARMOR);
                        entries.add(ModItems.NEPHRITE_HORSE_ARMOR);

                        entries.add(ModItems.JADEITE_HORSE_ARMOR);
                        entries.add(ModItems.PINKU_NAUTILUS_ARMOR);
                        entries.add(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR);
                        entries.add(ModItems.TUNGSTEN_NAUTILUS_ARMOR);
                        entries.add(ModItems.ALUMINUM_NAUTILUS_ARMOR);
                        entries.add(ModItems.SAPPHIRE_NAUTILUS_ARMOR);
                        entries.add(ModItems.PLATINUM_NAUTILUS_ARMOR);
                        entries.add(ModItems.STEEL_NAUTILUS_ARMOR);
                        entries.add(ModItems.CAST_STEEL_NAUTILUS_ARMOR);

                        entries.add(ModItems.NEPHRITE_NAUTILUS_ARMOR);
                        entries.add(ModItems.JADEITE_NAUTILUS_ARMOR);
                        entries.add(ModItems.PINKU);
                        entries.add(ModItems.PINKU_SHARD);
                        entries.add(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE);
                        entries.add(ModItems.RAINBOW_PYRITE);
                        entries.add(ModItems.WOLFRAMITE);
                        entries.add(ModItems.SCHEELITE);

                        entries.add(ModItems.TUNGSTEN);
                        entries.add(ModItems.CRUSHED_BAUXITE);
                        entries.add(ModItems.ALUMINUM);
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModItems.NEPHRITE);
                        entries.add(ModItems.JADEITE);
                        entries.add(ModItems.PLATINUM);
                        entries.add(ModItems.PLATINUM_NUGGET);
                        entries.add(ModItems.SULFUR);

                        entries.add(ModItems.CRUDE_IRON);
                        entries.add(ModItems.STEEL);
                        entries.add(ModItems.CAST_STEEL);
                        entries.add(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE);
                        entries.add(ModItems.TUNGSTEN_DRILL_BIT);
                        entries.add(ModItems.DIAMOND_DRILL_BIT);
                        entries.add(ModItems.SIMPLE_DRILL_HEAD);
                        entries.add(ModItems.ENHANCED_DRILL_HEAD);
                        entries.add(ModItems.ADVANCED_DRILL_HEAD);

                        entries.add(ModItems.PREMIUM_DRILL_HEAD);
                        entries.add(ModItems.SIMPLE_TUNGSTEN_DRILL);
                        entries.add(ModItems.ENHANCED_TUNGSTEN_DRILL);
                        entries.add(ModItems.ADVANCED_TUNGSTEN_DRILL);
                        entries.add(ModItems.PREMIUM_TUNGSTEN_DRILL);
                        entries.add(ModItems.SIMPLE_DIAMOND_DRILL);
                        entries.add(ModItems.ENHANCED_DIAMOND_DRILL);
                        entries.add(ModItems.ADVANCED_DIAMOND_DRILL);
                        entries.add(ModItems.PREMIUM_DIAMOND_DRILL);

                        entries.add(ModItems.PINKU_SPEAR);
                        entries.add(ModItems.RAINBOW_PYRITE_SPEAR);
                        entries.add(ModItems.TUNGSTEN_SPEAR);
                        entries.add(ModItems.ALUMINUM_SPEAR);
                        entries.add(ModItems.SAPPHIRE_SPEAR);
                        entries.add(ModItems.NEPHRITE_SPEAR);
                        entries.add(ModItems.JADEITE_SPEAR);
                        entries.add(ModItems.PLATINUM_SPEAR);
                        entries.add(ModItems.STEEL_SPEAR);

                        entries.add(ModItems.CAST_STEEL_SPEAR);

                    }).build());

    public static final ItemGroup HOUSEKI_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Houseki.MOD_ID, "houseki_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.BLOCK_OF_PINKU))
                    .displayName(Text.translatable("itemgroup.houseki.housekiblocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PINKU_ORE);
                        entries.add(ModBlocks.BLOCK_OF_PINKU);

                        entries.add(ModBlocks.RAINBOW_PYRITE_ORE);
                        entries.add(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE);
                        entries.add(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE);
                        entries.add(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);

                        entries.add(ModBlocks.WOLFRAMITE_ORE);

                        entries.add(ModBlocks.SCHEELITE_ORE);

                        entries.add(ModBlocks.BLOCK_OF_TUNGSTEN_B);

                        entries.add(ModBlocks.BAUXITE);

                        entries.add(ModBlocks.BLOCK_OF_ALUMINUM);
                        entries.add(ModBlocks.ALUMINUM_GLASS);
                        entries.add(ModBlocks.ALUMINUM_GLASS_PANE);
                        entries.add(ModBlocks.ALUMINUM_DOOR);
                        entries.add(ModBlocks.ALUMINUM_TRAPDOOR);

                        entries.add(ModBlocks.SAPPHIRE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                        entries.add(ModBlocks.BLOCK_OF_SAPPHIRE);

                        entries.add(ModBlocks.NEPHRITE_ORE);
                        entries.add(ModBlocks.BLOCK_OF_JADEITE);
                        entries.add(ModBlocks.JADEITE_ORE);

                        entries.add(ModBlocks.PLATINUM_ORE);
                        entries.add(ModBlocks.DEEPSLATE_PLATINUM_ORE);
                        entries.add(ModBlocks.BLOCK_OF_PLATINUM);

                        entries.add(ModBlocks.CRUSHER);

                        entries.add(ModBlocks.LIMESTONE);
                        entries.add(ModBlocks.LIMESTONE_STAIRS);
                        entries.add(ModBlocks.LIMESTONE_SLAB);
                        entries.add(ModBlocks.LIMESTONE_WALL);
                        entries.add(ModBlocks.LIMESTONE_BRICKS);
                        entries.add(ModBlocks.LIMESTONE_BRICK_STAIRS);
                        entries.add(ModBlocks.LIMESTONE_BRICK_SLAB);
                        entries.add(ModBlocks.LIMESTONE_BRICK_WALL);
                        entries.add(ModBlocks.POLISHED_LIMESTONE);
                        entries.add(ModBlocks.POLISHED_LIMESTONE_STAIRS);
                        entries.add(ModBlocks.POLISHED_LIMESTONE_SLAB);
                        entries.add(ModBlocks.POLISHED_LIMESTONE_WALL);
                        entries.add(ModBlocks.CHISELED_LIMESTONE);

                        entries.add(ModBlocks.SLATE);
                        entries.add(ModBlocks.SLATE_STAIRS);
                        entries.add(ModBlocks.SLATE_SLAB);
                        entries.add(ModBlocks.SLATE_WALL);
                        entries.add(ModBlocks.SLATE_TILES);
                        entries.add(ModBlocks.SLATE_TILE_STAIRS);
                        entries.add(ModBlocks.SLATE_TILE_SLAB);
                        entries.add(ModBlocks.SLATE_TILE_WALL);
                        entries.add(ModBlocks.POLISHED_SLATE);
                        entries.add(ModBlocks.POLISHED_SLATE_STAIRS);
                        entries.add(ModBlocks.POLISHED_SLATE_SLAB);
                        entries.add(ModBlocks.POLISHED_SLATE_WALL);
                        entries.add(ModBlocks.CHISELED_SLATE);

                        entries.add(ModBlocks.SULFUR_ORE);
                        entries.add(ModBlocks.BLACKSTONE_SULFUR_ORE);
                        entries.add(ModBlocks.BLOCK_OF_SULFUR);

                        entries.add(ModBlocks.BLOCK_OF_STEEL);
                        entries.add(ModBlocks.BLOCK_OF_CAST_STEEL_B);
                    }).build());


    public static void registerItemGroups() {
        Houseki.LOGGER.info("Registering ItemGroups for " + Houseki.MOD_ID);
    }
}
