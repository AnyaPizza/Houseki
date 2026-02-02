package anya.pizza.houseki.item;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ModItemGroups {
    public static final CreativeModeTab HOUSEKI_ITEMS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "houseki_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PINKU))
                    .title(Component.translatable("itemgroup.houseki.housekiitems"))
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.PINKU_HELMET);
                        entries.accept(ModItems.PINKU_CHESTPLATE);
                        entries.accept(ModItems.PINKU_LEGGINGS);
                        entries.accept(ModItems.PINKU_BOOTS);
                        entries.accept(ModItems.PINKU_PICKAXE);
                        entries.accept(ModItems.PINKU_AXE);
                        entries.accept(ModItems.PINKU_SHOVEL);
                        entries.accept(ModItems.PINKU_SWORD);
                        entries.accept(ModItems.PINKU_HOE);

                        entries.accept(ModItems.RAINBOW_PYRITE_HELMET);
                        entries.accept(ModItems.RAINBOW_PYRITE_CHESTPLATE);
                        entries.accept(ModItems.RAINBOW_PYRITE_LEGGINGS);
                        entries.accept(ModItems.RAINBOW_PYRITE_BOOTS);
                        entries.accept(ModItems.RAINBOW_PYRITE_PICKAXE);
                        entries.accept(ModItems.RAINBOW_PYRITE_AXE);
                        entries.accept(ModItems.RAINBOW_PYRITE_SHOVEL);
                        entries.accept(ModItems.RAINBOW_PYRITE_SWORD);
                        entries.accept(ModItems.RAINBOW_PYRITE_HOE);

                        entries.accept(ModItems.TUNGSTEN_HELMET);
                        entries.accept(ModItems.TUNGSTEN_CHESTPLATE);
                        entries.accept(ModItems.TUNGSTEN_LEGGINGS);
                        entries.accept(ModItems.TUNGSTEN_BOOTS);
                        entries.accept(ModItems.TUNGSTEN_PICKAXE);
                        entries.accept(ModItems.TUNGSTEN_AXE);
                        entries.accept(ModItems.TUNGSTEN_SHOVEL);
                        entries.accept(ModItems.TUNGSTEN_SWORD);
                        entries.accept(ModItems.TUNGSTEN_HOE);

                        entries.accept(ModItems.ALUMINUM_HELMET);
                        entries.accept(ModItems.ALUMINUM_CHESTPLATE);
                        entries.accept(ModItems.ALUMINUM_LEGGINGS);
                        entries.accept(ModItems.ALUMINUM_BOOTS);
                        entries.accept(ModItems.ALUMINUM_PICKAXE);
                        entries.accept(ModItems.ALUMINUM_AXE);
                        entries.accept(ModItems.ALUMINUM_SHOVEL);
                        entries.accept(ModItems.ALUMINUM_SWORD);
                        entries.accept(ModItems.ALUMINUM_HOE);

                        entries.accept(ModItems.SAPPHIRE_HELMET);
                        entries.accept(ModItems.SAPPHIRE_CHESTPLATE);
                        entries.accept(ModItems.SAPPHIRE_LEGGINGS);
                        entries.accept(ModItems.SAPPHIRE_BOOTS);
                        entries.accept(ModItems.SAPPHIRE_PICKAXE);
                        entries.accept(ModItems.SAPPHIRE_AXE);
                        entries.accept(ModItems.SAPPHIRE_SHOVEL);
                        entries.accept(ModItems.SAPPHIRE_SWORD);
                        entries.accept(ModItems.SAPPHIRE_HOE);

                        entries.accept(ModItems.NEPHRITE_HELMET);
                        entries.accept(ModItems.NEPHRITE_CHESTPLATE);
                        entries.accept(ModItems.NEPHRITE_LEGGINGS);
                        entries.accept(ModItems.NEPHRITE_BOOTS);
                        entries.accept(ModItems.NEPHRITE_PICKAXE);
                        entries.accept(ModItems.NEPHRITE_AXE);
                        entries.accept(ModItems.NEPHRITE_SHOVEL);
                        entries.accept(ModItems.NEPHRITE_SWORD);
                        entries.accept(ModItems.NEPHRITE_HOE);

                        entries.accept(ModItems.JADEITE_HELMET);
                        entries.accept(ModItems.JADEITE_CHESTPLATE);
                        entries.accept(ModItems.JADEITE_LEGGINGS);
                        entries.accept(ModItems.JADEITE_BOOTS);
                        entries.accept(ModItems.JADEITE_PICKAXE);
                        entries.accept(ModItems.JADEITE_AXE);
                        entries.accept(ModItems.JADEITE_SHOVEL);
                        entries.accept(ModItems.JADEITE_SWORD);
                        entries.accept(ModItems.JADEITE_HOE);

                        entries.accept(ModItems.PLATINUM_HELMET);
                        entries.accept(ModItems.PLATINUM_CHESTPLATE);
                        entries.accept(ModItems.PLATINUM_LEGGINGS);
                        entries.accept(ModItems.PLATINUM_BOOTS);
                        entries.accept(ModItems.PLATINUM_PICKAXE);
                        entries.accept(ModItems.PLATINUM_AXE);
                        entries.accept(ModItems.PLATINUM_SHOVEL);
                        entries.accept(ModItems.PLATINUM_SWORD);
                        entries.accept(ModItems.PLATINUM_HOE);

                        entries.accept(ModItems.STEEL_HELMET);
                        entries.accept(ModItems.STEEL_CHESTPLATE);
                        entries.accept(ModItems.STEEL_LEGGINGS);
                        entries.accept(ModItems.STEEL_BOOTS);
                        entries.accept(ModItems.STEEL_PICKAXE);
                        entries.accept(ModItems.STEEL_AXE);
                        entries.accept(ModItems.STEEL_SHOVEL);
                        entries.accept(ModItems.STEEL_SWORD);
                        entries.accept(ModItems.STEEL_HOE);

                        entries.accept(ModItems.CAST_STEEL_HELMET);
                        entries.accept(ModItems.CAST_STEEL_CHESTPLATE);
                        entries.accept(ModItems.CAST_STEEL_LEGGINGS);
                        entries.accept(ModItems.CAST_STEEL_BOOTS);
                        entries.accept(ModItems.CAST_STEEL_PICKAXE);
                        entries.accept(ModItems.CAST_STEEL_AXE);
                        entries.accept(ModItems.CAST_STEEL_SHOVEL);
                        entries.accept(ModItems.CAST_STEEL_SWORD);
                        entries.accept(ModItems.CAST_STEEL_HOE);

                        entries.accept(ModItems.PINKU_HORSE_ARMOR);
                        entries.accept(ModItems.RAINBOW_PYRITE_HORSE_ARMOR);
                        entries.accept(ModItems.TUNGSTEN_HORSE_ARMOR);
                        entries.accept(ModItems.ALUMINUM_HORSE_ARMOR);
                        entries.accept(ModItems.SAPPHIRE_HORSE_ARMOR);
                        entries.accept(ModItems.PLATINUM_HORSE_ARMOR);
                        entries.accept(ModItems.STEEL_HORSE_ARMOR);
                        entries.accept(ModItems.CAST_STEEL_HORSE_ARMOR);
                        entries.accept(ModItems.NEPHRITE_HORSE_ARMOR);

                        entries.accept(ModItems.JADEITE_HORSE_ARMOR);
                        entries.accept(ModItems.PINKU_NAUTILUS_ARMOR);
                        entries.accept(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR);
                        entries.accept(ModItems.TUNGSTEN_NAUTILUS_ARMOR);
                        entries.accept(ModItems.ALUMINUM_NAUTILUS_ARMOR);
                        entries.accept(ModItems.SAPPHIRE_NAUTILUS_ARMOR);
                        entries.accept(ModItems.PLATINUM_NAUTILUS_ARMOR);
                        entries.accept(ModItems.STEEL_NAUTILUS_ARMOR);
                        entries.accept(ModItems.CAST_STEEL_NAUTILUS_ARMOR);

                        entries.accept(ModItems.NEPHRITE_NAUTILUS_ARMOR);
                        entries.accept(ModItems.JADEITE_NAUTILUS_ARMOR);
                        entries.accept(ModItems.PINKU);
                        entries.accept(ModItems.PINKU_SHARD);
                        entries.accept(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE);
                        entries.accept(ModItems.RAINBOW_PYRITE);
                        entries.accept(ModItems.WOLFRAMITE);
                        entries.accept(ModItems.SCHEELITE);

                        entries.accept(ModItems.TUNGSTEN);
                        entries.accept(ModItems.CRUSHED_BAUXITE);
                        entries.accept(ModItems.ALUMINUM);
                        entries.accept(ModItems.SAPPHIRE);
                        entries.accept(ModItems.NEPHRITE);
                        entries.accept(ModItems.JADEITE);
                        entries.accept(ModItems.PLATINUM);
                        entries.accept(ModItems.PLATINUM_NUGGET);
                        entries.accept(ModItems.SULFUR);

                        entries.accept(ModItems.CRUDE_IRON);
                        entries.accept(ModItems.STEEL);
                        entries.accept(ModItems.CAST_STEEL);
                        entries.accept(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE);
                        entries.accept(ModItems.TUNGSTEN_DRILL_BIT);
                        entries.accept(ModItems.DIAMOND_DRILL_BIT);
                        entries.accept(ModItems.SIMPLE_DRILL_HEAD);
                        entries.accept(ModItems.ENHANCED_DRILL_HEAD);
                        entries.accept(ModItems.ADVANCED_DRILL_HEAD);

                        entries.accept(ModItems.PREMIUM_DRILL_HEAD);
                        entries.accept(ModItems.SIMPLE_TUNGSTEN_DRILL);
                        entries.accept(ModItems.ENHANCED_TUNGSTEN_DRILL);
                        entries.accept(ModItems.ADVANCED_TUNGSTEN_DRILL);
                        entries.accept(ModItems.PREMIUM_TUNGSTEN_DRILL);
                        entries.accept(ModItems.SIMPLE_DIAMOND_DRILL);
                        entries.accept(ModItems.ENHANCED_DIAMOND_DRILL);
                        entries.accept(ModItems.ADVANCED_DIAMOND_DRILL);
                        entries.accept(ModItems.PREMIUM_DIAMOND_DRILL);

                    }).build());

    public static final CreativeModeTab HOUSEKI_BLOCKS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "houseki_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BLOCK_OF_PINKU))
                    .title(Component.translatable("itemgroup.houseki.housekiblocks"))
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModBlocks.PINKU_ORE);
                        entries.accept(ModBlocks.BLOCK_OF_PINKU);

                        entries.accept(ModBlocks.RAINBOW_PYRITE_ORE);
                        entries.accept(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE);
                        entries.accept(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE);
                        entries.accept(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);

                        entries.accept(ModBlocks.WOLFRAMITE_ORE);

                        entries.accept(ModBlocks.SCHEELITE_ORE);

                        entries.accept(ModBlocks.BLOCK_OF_TUNGSTEN_B);

                        entries.accept(ModBlocks.BAUXITE);

                        entries.accept(ModBlocks.BLOCK_OF_ALUMINUM);
                        entries.accept(ModBlocks.ALUMINUM_GLASS);
                        entries.accept(ModBlocks.ALUMINUM_GLASS_PANE);
                        entries.accept(ModBlocks.ALUMINUM_DOOR);
                        entries.accept(ModBlocks.ALUMINUM_TRAPDOOR);

                        entries.accept(ModBlocks.SAPPHIRE_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                        entries.accept(ModBlocks.BLOCK_OF_SAPPHIRE);

                        entries.accept(ModBlocks.NEPHRITE_ORE);
                        entries.accept(ModBlocks.BLOCK_OF_JADEITE);
                        entries.accept(ModBlocks.JADEITE_ORE);

                        entries.accept(ModBlocks.PLATINUM_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_PLATINUM_ORE);
                        entries.accept(ModBlocks.BLOCK_OF_PLATINUM);

                        entries.accept(ModBlocks.CRUSHER);

                        entries.accept(ModBlocks.LIMESTONE);
                        entries.accept(ModBlocks.LIMESTONE_STAIRS);
                        entries.accept(ModBlocks.LIMESTONE_SLAB);
                        entries.accept(ModBlocks.LIMESTONE_WALL);
                        entries.accept(ModBlocks.LIMESTONE_BRICKS);
                        entries.accept(ModBlocks.LIMESTONE_BRICK_STAIRS);
                        entries.accept(ModBlocks.LIMESTONE_BRICK_SLAB);
                        entries.accept(ModBlocks.LIMESTONE_BRICK_WALL);
                        entries.accept(ModBlocks.POLISHED_LIMESTONE);
                        entries.accept(ModBlocks.POLISHED_LIMESTONE_STAIRS);
                        entries.accept(ModBlocks.POLISHED_LIMESTONE_SLAB);
                        entries.accept(ModBlocks.POLISHED_LIMESTONE_WALL);
                        entries.accept(ModBlocks.CHISELED_LIMESTONE);

                        entries.accept(ModBlocks.SLATE);
                        entries.accept(ModBlocks.SLATE_STAIRS);
                        entries.accept(ModBlocks.SLATE_SLAB);
                        entries.accept(ModBlocks.SLATE_WALL);
                        entries.accept(ModBlocks.SLATE_TILES);
                        entries.accept(ModBlocks.SLATE_TILE_STAIRS);
                        entries.accept(ModBlocks.SLATE_TILE_SLAB);
                        entries.accept(ModBlocks.SLATE_TILE_WALL);
                        entries.accept(ModBlocks.POLISHED_SLATE);
                        entries.accept(ModBlocks.POLISHED_SLATE_STAIRS);
                        entries.accept(ModBlocks.POLISHED_SLATE_SLAB);
                        entries.accept(ModBlocks.POLISHED_SLATE_WALL);
                        entries.accept(ModBlocks.CHISELED_SLATE);

                        entries.accept(ModBlocks.SULFUR_ORE);
                        entries.accept(ModBlocks.BLACKSTONE_SULFUR_ORE);
                        entries.accept(ModBlocks.BLOCK_OF_SULFUR);

                        entries.accept(ModBlocks.BLOCK_OF_STEEL);
                        entries.accept(ModBlocks.BLOCK_OF_CAST_STEEL);
                    }).build());


    public static void registerItemGroups() {
        Houseki.LOGGER.info("Registering ItemGroups for " + Houseki.MOD_ID);
    }
}
