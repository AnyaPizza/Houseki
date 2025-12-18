package anya.pizza.pizzasoat.item;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.custom.*;
import anya.pizza.pizzasoat.item.custom.PinkuTemplateItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems {
    //Items
    public static final Item RAINBOW_PYRITE = registerItem("rainbow_pyrite", Item::new);
    public static final Item PINKU = registerItem("pinku", Item::new);
    public static final Item PINKU_SHARD = registerItem("pinku_shard", Item::new);
    public static final Item WOLFRAMITE = registerItem("wolframite", Item::new);
    public static final Item SCHEELITE = registerItem("scheelite", Item::new);
    public static final Item TUNGSTEN = registerItem("tungsten", setting -> new Item(setting.fireproof()));
    public static final Item CRUSHED_BAUXITE = registerItem("crushed_bauxite", Item::new);
    public static final Item ALUMINUM = registerItem("aluminum", Item::new);
    public static final Item SAPPHIRE = registerItem("sapphire", Item::new);
    public static final Item NEPHRITE = registerItem("nephrite", Item::new);
    public static final Item JADEITE = registerItem("jadeite", Item::new);
    public static final Item PLATINUM = registerItem("platinum", Item::new);
    public static final Item PLATINUM_NUGGET = registerItem("platinum_nugget", Item::new);
    public static final Item SULFUR = registerItem("sulfur", Item::new);
    public static final Item CRUDE_IRON = registerItem("crude_iron", Item::new);
    public static final Item STEEL = registerItem("steel", Item::new);
    public static final Item CAST_STEEL = registerItem("cast_steel", Item::new);
    public static final Item TUNGSTEN_DRILL_BIT = registerItem("tungsten_drill_bit", setting -> new Item(setting.fireproof()));
    public static final Item SIMPLE_DRILL_HEAD = registerItem("simple_drill_head", Item::new);
    public static final Item ENHANCED_DRILL_HEAD = registerItem("enhanced_drill_head", Item::new);
    public static final Item ADVANCED_DRILL_HEAD = registerItem("advanced_drill_head", Item::new);
    public static final Item PREMIUM_DRILL_HEAD = registerItem("premium_drill_head", Item::new);
    public static final Item DIAMOND_DRILL_BIT = registerItem("diamond_drill_bit", Item::new);
    public static final Item BLOCK_OF_TUNGSTEN_ITEM = registerItem("block_of_tungsten_item", setting -> new BlockItem(ModBlocks.BLOCK_OF_TUNGSTEN, setting.fireproof()));

    public static final Item PINKU_UPGRADE_SMITHING_TEMPLATE = registerItem("pinku_upgrade_smithing_template", PinkuTemplateItem::createPinkuUpgrade);
    public static final Item DRILL_UPGRADE_SMITHING_TEMPLATE = registerItem("drill_upgrade_smithing_template", DrillTemplateItem::createDrillUpgrade);

    //Tools
    public static final Item PINKU_PICKAXE = registerItem("pinku_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.PINKU, 8, 1.5f, setting));
    public static final Item PINKU_AXE = registerItem("pinku_axe",
            setting -> new AxeItem(ModToolMaterials.PINKU, 12, 1.3f, setting));
    public static final Item PINKU_SHOVEL = registerItem("pinku_shovel",
            setting -> new ShovelItem(ModToolMaterials.PINKU, 6, 1.3f, setting));
    public static final Item PINKU_SWORD = registerItem("pinku_sword",
            setting -> new SwordItem(ModToolMaterials.PINKU, 10, 1.9f, setting));
    public static final Item PINKU_HOE = registerItem("pinku_hoe",
            setting -> new HoeItem(ModToolMaterials.PINKU, 2, 4.5f, setting));

    public static final Item RAINBOW_PYRITE_PICKAXE = registerItem("rainbow_pyrite_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.RAINBOW_PYRITE, 0, 1.3f, setting));
    public static final Item RAINBOW_PYRITE_AXE = registerItem("rainbow_pyrite_axe",
            setting -> new AxeItem(ModToolMaterials.RAINBOW_PYRITE, 4, 1.0f, setting));
    public static final Item RAINBOW_PYRITE_SHOVEL = registerItem("rainbow_pyrite_shovel",
            setting -> new ShovelItem(ModToolMaterials.RAINBOW_PYRITE, 1, 1.0f, setting));
    public static final Item RAINBOW_PYRITE_SWORD = registerItem("rainbow_pyrite_sword",
            setting -> new SwordItem(ModToolMaterials.RAINBOW_PYRITE, 2, 1.3f, setting));
    public static final Item RAINBOW_PYRITE_HOE = registerItem("rainbow_pyrite_hoe",
            setting -> new HoeItem(ModToolMaterials.RAINBOW_PYRITE, -4, 4.0f, setting));

    public static final Item TUNGSTEN_PICKAXE = registerItem("tungsten_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.TUNGSTEN, 1, 1.4f, setting.fireproof()));
    public static final Item TUNGSTEN_AXE = registerItem("tungsten_axe",
            setting -> new AxeItem(ModToolMaterials.TUNGSTEN, 4.5f, 1.1f, setting.fireproof()));
    public static final Item TUNGSTEN_SHOVEL = registerItem("tungsten_shovel",
            setting -> new ShovelItem(ModToolMaterials.TUNGSTEN, 0.5f, 1.1f, setting.fireproof()));
    public static final Item TUNGSTEN_SWORD = registerItem("tungsten_sword",
            setting -> new TungstenSwordItem(ModToolMaterials.TUNGSTEN, 2, 1.7f, setting.fireproof()));
    public static final Item TUNGSTEN_HOE = registerItem("tungsten_hoe",
            setting -> new HoeItem(ModToolMaterials.TUNGSTEN, -4, 4.1f, setting));

    public static final Item SIMPLE_TUNGSTEN_DRILL = registerItem("simple_tungsten_drill",
            setting -> new PickaxeItem(ModToolMaterials.TUNGSTEN_DRILL, 2, 1.9f, setting.fireproof()));
    public static final Item ENHANCED_TUNGSTEN_DRILL = registerItem("enhanced_tungsten_drill",
            setting -> new EnhancedDrillItem(ModToolMaterials.ENHANCED_TUNGSTEN_DRILL, 2.5f, 2.3f, setting.fireproof()));
    public static final Item ADVANCED_TUNGSTEN_DRILL = registerItem("advanced_tungsten_drill",
            setting -> new AdvancedDrillItem(ModToolMaterials.ADVANCED_TUNGSTEN_DRILL, 3, 2.9f, setting.fireproof()));
    public static final Item PREMIUM_TUNGSTEN_DRILL = registerItem("premium_tungsten_drill",
            setting -> new PremiumDrillItem(ModToolMaterials.PREMIUM_TUNGSTEN_DRILL, 3.5f, 3.5f, setting.fireproof()));

    public static final Item ALUMINUM_PICKAXE = registerItem("aluminum_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.ALUMINUM, 0, 1.3f, setting));
    public static final Item ALUMINUM_AXE = registerItem("aluminum_axe",
            setting -> new AxeItem(ModToolMaterials.ALUMINUM, 4, 1, setting));
    public static final Item ALUMINUM_SHOVEL = registerItem("aluminum_shovel",
            setting -> new ShovelItem(ModToolMaterials.ALUMINUM, 0, 1, setting));
    public static final Item ALUMINUM_SWORD = registerItem("aluminum_sword",
            setting -> new SwordItem(ModToolMaterials.ALUMINUM, 2, 1.3f, setting));
    public static final Item ALUMINUM_HOE = registerItem("aluminum_hoe",
            setting -> new HoeItem(ModToolMaterials.ALUMINUM, -3, 4, setting));

    public static final Item SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.SAPPHIRE, 1, 2.4f, setting));
    public static final Item SAPPHIRE_AXE = registerItem("sapphire_axe",
            setting -> new AxeItem(ModToolMaterials.SAPPHIRE, 5, 2.1f, setting));
    public static final Item SAPPHIRE_SHOVEL = registerItem("sapphire_shovel",
            setting -> new ShovelItem(ModToolMaterials.SAPPHIRE, 2, 2.1f, setting));
    public static final Item SAPPHIRE_SWORD = registerItem("sapphire_sword",
            setting -> new SwordItem(ModToolMaterials.SAPPHIRE, 3, 2.7f, setting));
    public static final Item SAPPHIRE_HOE = registerItem("sapphire_hoe",
            setting -> new HoeItem(ModToolMaterials.SAPPHIRE, -4, 5.1f, setting));

    public static final Item NEPHRITE_PICKAXE = registerItem("nephrite_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.NEPHRITE, 0, 1.3f, setting));
    public static final Item NEPHRITE_AXE = registerItem("nephrite_axe",
            setting -> new AxeItem(ModToolMaterials.NEPHRITE, 4, 1, setting));
    public static final Item NEPHRITE_SHOVEL = registerItem("nephrite_shovel",
            setting -> new ShovelItem(ModToolMaterials.NEPHRITE, 1, 1, setting));
    public static final Item NEPHRITE_SWORD = registerItem("nephrite_sword",
            setting -> new SwordItem(ModToolMaterials.NEPHRITE, 2, 1.5f, setting));
    public static final Item NEPHRITE_HOE = registerItem("nephrite_hoe",
            setting -> new HoeItem(ModToolMaterials.NEPHRITE, -4, 4, setting));

    public static final Item JADEITE_PICKAXE = registerItem("jadeite_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.JADEITE, 0.5f, 1.3f, setting));
    public static final Item JADEITE_AXE = registerItem("jadeite_axe",
            setting -> new AxeItem(ModToolMaterials.JADEITE, 4.5f, 1, setting));
    public static final Item JADEITE_SHOVEL = registerItem("jadeite_shovel",
            setting -> new ShovelItem(ModToolMaterials.JADEITE, 1.5f, 1, setting));
    public static final Item JADEITE_SWORD = registerItem("jadeite_sword",
            setting -> new SwordItem(ModToolMaterials.JADEITE, 2, 1.5f, setting));
    public static final Item JADEITE_HOE = registerItem("jadeite_hoe",
            setting -> new HoeItem(ModToolMaterials.JADEITE, -3.5f, 4, setting));

    public static final Item PLATINUM_PICKAXE = registerItem("platinum_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.PLATINUM, 1, 1.4f, setting));
    public static final Item PLATINUM_AXE = registerItem("platinum_axe",
            setting -> new AxeItem(ModToolMaterials.PLATINUM, 4.5f, 1.1f, setting));
    public static final Item PLATINUM_SHOVEL = registerItem("platinum_shovel",
            setting -> new ShovelItem(ModToolMaterials.PLATINUM, 0.5f, 1.1f, setting));
    public static final Item PLATINUM_SWORD = registerItem("platinum_sword",
            setting -> new SwordItem(ModToolMaterials.PLATINUM, 2, 1.7f, setting));
    public static final Item PLATINUM_HOE = registerItem("platinum_hoe",
            setting -> new HoeItem(ModToolMaterials.PLATINUM, -4, 4.1f, setting));

    public static final Item STEEL_PICKAXE = registerItem("steel_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.STEEL, 0.5f, 1.0f, setting));
    public static final Item STEEL_AXE = registerItem("steel_axe",
            setting -> new AxeItem(ModToolMaterials.STEEL, 4.0f, 1.0f, setting));
    public static final Item STEEL_SHOVEL = registerItem("steel_shovel",
            setting -> new ShovelItem(ModToolMaterials.STEEL, 0.0f, 1.0f, setting));
    public static final Item STEEL_SWORD = registerItem("steel_sword",
            setting -> new SwordItem(ModToolMaterials.STEEL, 2, 1.0f, setting));
    public static final Item STEEL_HOE = registerItem("steel_hoe",
            setting -> new HoeItem(ModToolMaterials.STEEL, -5.5f, 4.0f, setting));

    public static final Item CAST_STEEL_PICKAXE = registerItem("cast_steel_pickaxe",
            setting -> new PickaxeItem(ModToolMaterials.CAST_STEEL, 1, 1.0f, setting));
    public static final Item CAST_STEEL_AXE = registerItem("cast_steel_axe",
            setting -> new AxeItem(ModToolMaterials.CAST_STEEL, 4.5f, 1.0f, setting));
    public static final Item CAST_STEEL_SHOVEL = registerItem("cast_steel_shovel",
            setting -> new ShovelItem(ModToolMaterials.CAST_STEEL, 0.3f, 1.0f, setting));
    public static final Item CAST_STEEL_SWORD = registerItem("cast_steel_sword",
            setting -> new SwordItem(ModToolMaterials.CAST_STEEL, 3, 1.0f, setting));
    public static final Item CAST_STEEL_HOE = registerItem("cast_steel_hoe",
            setting -> new HoeItem(ModToolMaterials.CAST_STEEL, -4.5f, 4.0f, setting));

    public static final Item SIMPLE_DIAMOND_DRILL = registerItem("simple_diamond_drill",
            setting -> new PickaxeItem(ModToolMaterials.DIAMOND_DRILL, 1, -2.8f, setting.fireproof()));
    public static final Item ENHANCED_DIAMOND_DRILL = registerItem("enhanced_diamond_drill",
            setting -> new EnhancedDrillItem(ModToolMaterials.ENHANCED_DIAMOND_DRILL, 1, -2.1f, setting.fireproof()));
    public static final Item ADVANCED_DIAMOND_DRILL = registerItem("advanced_diamond_drill",
            setting -> new AdvancedDrillItem(ModToolMaterials.ADVANCED_DIAMOND_DRILL, 1.3f, -2.0f, setting.fireproof()));
    public static final Item PREMIUM_DIAMOND_DRILL = registerItem("premium_diamond_drill",
            setting -> new PremiumDrillItem(ModToolMaterials.PREMIUM_DIAMOND_DRILL, 1.5f, -1.8f, setting.fireproof()));

    //Armor
    public static final Item PINKU_HELMET = registerItem("pinku_helmet",
            setting -> new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(40))));
    public static final Item PINKU_CHESTPLATE = registerItem("pinku_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(40))));
    public static final Item PINKU_LEGGINGS = registerItem("pinku_leggings",
            setting -> new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(40))));
    public static final Item PINKU_BOOTS = registerItem("pinku_boots",
            setting -> new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(40))));

    public static final Item RAINBOW_PYRITE_HELMET = registerItem("rainbow_pyrite_helmet",
            setting -> new ModArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(33))));
    public static final Item RAINBOW_PYRITE_CHESTPLATE = registerItem("rainbow_pyrite_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(33))));
    public static final Item RAINBOW_PYRITE_LEGGINGS = registerItem("rainbow_pyrite_leggings",
            setting -> new ArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(33))));
    public static final Item RAINBOW_PYRITE_BOOTS = registerItem("rainbow_pyrite_boots",
            setting -> new ArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(33))));

    public static final Item TUNGSTEN_HELMET = registerItem("tungsten_helmet",
            setting -> new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, EquipmentType.HELMET, setting.fireproof().maxDamage(EquipmentType.HELMET.getMaxDamage(50))));
    public static final Item TUNGSTEN_CHESTPLATE = registerItem("tungsten_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, EquipmentType.CHESTPLATE, setting.fireproof().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(50))));
    public static final Item TUNGSTEN_LEGGINGS = registerItem("tungsten_leggings",
            setting -> new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, EquipmentType.LEGGINGS, setting.fireproof().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(50))));
    public static final Item TUNGSTEN_BOOTS = registerItem("tungsten_boots",
            setting -> new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, EquipmentType.BOOTS, setting.fireproof().maxDamage(EquipmentType.BOOTS.getMaxDamage(50))));

    public static final Item ALUMINUM_HELMET = registerItem("aluminum_helmet",
            setting -> new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(30))));
    public static final Item ALUMINUM_CHESTPLATE = registerItem("aluminum_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(30))));
    public static final Item ALUMINUM_LEGGINGS = registerItem("aluminum_leggings",
            setting -> new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(30))));
    public static final Item ALUMINUM_BOOTS = registerItem("aluminum_boots",
            setting -> new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(30))));

    public static final Item SAPPHIRE_HELMET = registerItem("sapphire_helmet",
            setting -> new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(50))));
    public static final Item SAPPHIRE_CHESTPLATE = registerItem("sapphire_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(50))));
    public static final Item SAPPHIRE_LEGGINGS = registerItem("sapphire_leggings",
            setting -> new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(50))));
    public static final Item SAPPHIRE_BOOTS = registerItem("sapphire_boots",
            setting -> new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(50))));

    public static final Item NEPHRITE_HELMET = registerItem("nephrite_helmet",
            setting -> new ArmorItem(ModArmorMaterials.NEPHRITE_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(34))));
    public static final Item NEPHRITE_CHESTPLATE = registerItem("nephrite_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.NEPHRITE_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(34))));
    public static final Item NEPHRITE_LEGGINGS = registerItem("nephrite_leggings",
            setting -> new ArmorItem(ModArmorMaterials.NEPHRITE_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(34))));
    public static final Item NEPHRITE_BOOTS = registerItem("nephrite_boots",
            setting -> new ArmorItem(ModArmorMaterials.NEPHRITE_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(34))));

    public static final Item JADEITE_HELMET = registerItem("jadeite_helmet",
            setting -> new ArmorItem(ModArmorMaterials.JADEITE_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(43))));
    public static final Item JADEITE_CHESTPLATE = registerItem("jadeite_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.JADEITE_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(43))));
    public static final Item JADEITE_LEGGINGS = registerItem("jadeite_leggings",
            setting -> new ArmorItem(ModArmorMaterials.JADEITE_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(43))));
    public static final Item JADEITE_BOOTS = registerItem("jadeite_boots",
            setting -> new ArmorItem(ModArmorMaterials.JADEITE_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(43))));

    public static final Item PLATINUM_HELMET = registerItem("platinum_helmet",
            setting -> new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(34))));
    public static final Item PLATINUM_CHESTPLATE = registerItem("platinum_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(34))));
    public static final Item PLATINUM_LEGGINGS = registerItem("platinum_leggings",
            setting -> new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(34))));
    public static final Item PLATINUM_BOOTS = registerItem("platinum_boots",
            setting -> new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(34))));

    public static final Item STEEL_HELMET = registerItem("steel_helmet",
            setting -> new ArmorItem(ModArmorMaterials.STEEL_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(32))));
    public static final Item STEEL_CHESTPLATE = registerItem("steel_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.STEEL_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(32))));
    public static final Item STEEL_LEGGINGS = registerItem("steel_leggings",
            setting -> new ArmorItem(ModArmorMaterials.STEEL_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(32))));
    public static final Item STEEL_BOOTS = registerItem("steel_boots",
            setting -> new ArmorItem(ModArmorMaterials.STEEL_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(32))));

    public static final Item CAST_STEEL_HELMET = registerItem("cast_steel_helmet",
            setting -> new ArmorItem(ModArmorMaterials.CAST_STEEL_MATERIAL, EquipmentType.HELMET, setting.maxDamage(EquipmentType.HELMET.getMaxDamage(33))));
    public static final Item CAST_STEEL_CHESTPLATE = registerItem("cast_steel_chestplate",
            setting -> new ArmorItem(ModArmorMaterials.CAST_STEEL_MATERIAL, EquipmentType.CHESTPLATE, setting.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(33))));
    public static final Item CAST_STEEL_LEGGINGS = registerItem("cast_steel_leggings",
            setting -> new ArmorItem(ModArmorMaterials.CAST_STEEL_MATERIAL, EquipmentType.LEGGINGS, setting.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(33))));
    public static final Item CAST_STEEL_BOOTS = registerItem("cast_steel_boots",
            setting -> new ArmorItem(ModArmorMaterials.CAST_STEEL_MATERIAL, EquipmentType.BOOTS, setting.maxDamage(EquipmentType.BOOTS.getMaxDamage(33))));

    //Horse Armor
    public static final Item PINKU_HORSE_ARMOR = registerItem("pinku_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.PINKU_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item RAINBOW_PYRITE_HORSE_ARMOR = registerItem("rainbow_pyrite_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item TUNGSTEN_HORSE_ARMOR = registerItem("tungsten_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting.fireproof()));
    public static final Item ALUMINUM_HORSE_ARMOR = registerItem("aluminum_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item SAPPHIRE_HORSE_ARMOR = registerItem("sapphire_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item PLATINUM_HORSE_ARMOR = registerItem("platinum_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item STEEL_HORSE_ARMOR = registerItem("steel_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.STEEL_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item CAST_STEEL_HORSE_ARMOR = registerItem("cast_steel_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.CAST_STEEL_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item NEPHRITE_HORSE_ARMOR = registerItem("nephrite_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.NEPHRITE_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));
    public static final Item JADEITE_HORSE_ARMOR = registerItem("jadeite_horse_armor",
            setting -> new AnimalArmorItem(ModArmorMaterials.JADEITE_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, setting));


    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(PizzasOAT.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PizzasOAT.MOD_ID, name)))));
    }

    private static void customIngredients(FabricItemGroupEntries entries) {
        entries.add(PINKU);
        entries.add(PINKU_SHARD);
        entries.add(RAINBOW_PYRITE);
        entries.add(WOLFRAMITE);
        entries.add(SCHEELITE);
        entries.add(TUNGSTEN);
        entries.add(CRUSHED_BAUXITE);
        entries.add(ALUMINUM);
        entries.add(SAPPHIRE);
        entries.add(PLATINUM);
        entries.add(PLATINUM_NUGGET);
        entries.add(NEPHRITE);
        entries.add(JADEITE);
        entries.add(SULFUR);
        entries.add(CRUDE_IRON);
        entries.add(STEEL);
        entries.add(CAST_STEEL);
    }

    public static void registerModItems() {
        PizzasOAT.LOGGER.info("Registering ModItems for " + PizzasOAT.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
