package anya.pizza.pizzasoat.item;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.item.custom.ModArmorItem;
import anya.pizza.pizzasoat.item.custom.PinkuTemplateItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //Items
    public static final Item RAINBOW_PYRITE = registerItem("rainbow_pyrite", new Item(new Item.Settings()));
    public static final Item PINKU = registerItem("pinku", new Item(new Item.Settings()));
    public static final Item PINKU_SHARD = registerItem("pinku_shard", new Item(new Item.Settings()));
    public static final Item WOLFRAMITE = registerItem("wolframite", new Item( new Item.Settings()));
    public static final Item SCHEELITE = registerItem("scheelite", new Item( new Item.Settings()));
    public static final Item TRONA = registerItem("trona", new Item( new Item.Settings()));
    public static final Item SODIUM_CARBONATE = registerItem("sodium_carbonate", new Item( new Item.Settings()));
    public static final Item PURE_TUNGSTEN = registerItem("pure_tungsten", new Item( new Item.Settings()));
    public static final Item TUNGSTEN = registerItem("tungsten", new Item( new Item.Settings()));
    public static final Item BAUXITE = registerItem("bauxite", new Item( new Item.Settings()));
    public static final Item ALUMINUM = registerItem("aluminum", new Item( new Item.Settings()));
    public static final Item SAPPHIRE = registerItem("sapphire", new Item( new Item.Settings()));
    public static final Item NEPHRITE = registerItem("nephrite", new Item( new Item.Settings()));
    public static final Item JADEITE = registerItem("jadeite", new Item( new Item.Settings()));
    public static final Item JADE = registerItem("jade", new Item( new Item.Settings()));
    public static final Item PLATINUM = registerItem("platinum", new Item( new Item.Settings()));
    public static final Item SULFUR = registerItem("sulfur", new Item( new Item.Settings()));
    public static final Item PINKU_UPGRADE_SMITHING_TEMPLATE = registerItem("pinku_upgrade_smithing_template", PinkuTemplateItem.createPinkuUpgrade());

    //Tools
    public static final Item PINKU_PICKAXE = registerItem("pinku_pickaxe",
            new PickaxeItem(ModToolMaterials.PINKU, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINKU, 8, 1.5f))));
    public static final Item PINKU_AXE = registerItem("pinku_axe",
            new AxeItem(ModToolMaterials.PINKU, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PINKU, 12, 1.3f))));
    public static final Item PINKU_SHOVEL = registerItem("pinku_shovel",
            new ShovelItem(ModToolMaterials.PINKU, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PINKU,6, 1.3f))));
    public static final Item PINKU_SWORD = registerItem("pinku_sword",
            new SwordItem(ModToolMaterials.PINKU, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PINKU, 10, 1.9f))));
    public static final Item PINKU_HOE = registerItem("pinku_hoe",
            new HoeItem(ModToolMaterials.PINKU, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PINKU, 2, 4.5f))));

    public static final Item RAINBOW_PYRITE_PICKAXE = registerItem("rainbow_pyrite_pickaxe",
            new PickaxeItem(ModToolMaterials.RAINBOW_PYRITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.RAINBOW_PYRITE, 0, 1.3f))));
    public static final Item RAINBOW_PYRITE_AXE = registerItem("rainbow_pyrite_axe",
            new AxeItem(ModToolMaterials.RAINBOW_PYRITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.RAINBOW_PYRITE, 4, 1.0f))));
    public static final Item RAINBOW_PYRITE_SHOVEL = registerItem("rainbow_pyrite_shovel",
            new ShovelItem(ModToolMaterials.RAINBOW_PYRITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.RAINBOW_PYRITE,1, 1.0f))));
    public static final Item RAINBOW_PYRITE_SWORD = registerItem("rainbow_pyrite_sword",
            new SwordItem(ModToolMaterials.RAINBOW_PYRITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.RAINBOW_PYRITE, 2, 1.3f))));
    public static final Item RAINBOW_PYRITE_HOE = registerItem("rainbow_pyrite_hoe",
            new HoeItem(ModToolMaterials.RAINBOW_PYRITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.RAINBOW_PYRITE, -4, 4.0f))));

    public static final Item TUNGSTEN_PICKAXE = registerItem("tungsten_pickaxe",
            new PickaxeItem(ModToolMaterials.TUNGSTEN, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN, 1, 1.4f ))));
    public static final Item TUNGSTEN_AXE = registerItem("tungsten_axe",
            new AxeItem(ModToolMaterials.TUNGSTEN, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN, 4.5f, 1.1f))));
    public static final Item TUNGSTEN_SHOVEL = registerItem("tungsten_shovel",
            new ShovelItem(ModToolMaterials.TUNGSTEN, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN, 0.5f, 1.1f))));
    public static final Item TUNGSTEN_SWORD = registerItem("tungsten_sword",
            new SwordItem(ModToolMaterials.TUNGSTEN, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN, 2, 1.7f))));
    public static final Item TUNGSTEN_HOE = registerItem("tungsten_hoe",
            new HoeItem(ModToolMaterials.TUNGSTEN, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.TUNGSTEN, -4, 4.1f))));

    public static final Item ALUMINUM_PICKAXE = registerItem("aluminum_pickaxe",
            new PickaxeItem(ModToolMaterials.ALUMINUM, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ALUMINUM, 0, 1.3f ))));
    public static final Item ALUMINUM_AXE = registerItem("aluminum_axe",
            new AxeItem(ModToolMaterials.ALUMINUM, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ALUMINUM, 4, 1))));
    public static final Item ALUMINUM_SHOVEL = registerItem("aluminum_shovel",
            new ShovelItem(ModToolMaterials.ALUMINUM, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ALUMINUM, 0, 1))));
    public static final Item ALUMINUM_SWORD = registerItem("aluminum_sword",
            new SwordItem(ModToolMaterials.ALUMINUM, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ALUMINUM, 2, 1.3f))));
    public static final Item ALUMINUM_HOE = registerItem("aluminum_hoe",
            new HoeItem(ModToolMaterials.ALUMINUM, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ALUMINUM, -3, 4))));

    public static final Item SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe",
            new PickaxeItem(ModToolMaterials.SAPPHIRE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.SAPPHIRE, 1, 2.4f))));
    public static final Item SAPPHIRE_AXE = registerItem("sapphire_axe",
            new AxeItem(ModToolMaterials.SAPPHIRE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.SAPPHIRE, 5, 2.1f))));
    public static final Item SAPPHIRE_SHOVEL = registerItem("sapphire_shovel",
            new ShovelItem(ModToolMaterials.SAPPHIRE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.SAPPHIRE,2, 2.1f))));
    public static final Item SAPPHIRE_SWORD = registerItem("sapphire_sword",
            new SwordItem(ModToolMaterials.SAPPHIRE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SAPPHIRE, 3, 2.7f))));
    public static final Item SAPPHIRE_HOE = registerItem("sapphire_hoe",
            new HoeItem(ModToolMaterials.SAPPHIRE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.SAPPHIRE, -4, 5.1f))));

    public static final Item JADE_PICKAXE = registerItem("jade_pickaxe",
            new PickaxeItem(ModToolMaterials.JADE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.JADE, 0, 1.3f ))));
    public static final Item JADE_AXE = registerItem("jade_axe",
            new AxeItem(ModToolMaterials.JADE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.JADE, 4, 1))));
    public static final Item JADE_SHOVEL = registerItem("jade_shovel",
            new ShovelItem(ModToolMaterials.JADE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.JADE, 1, 1))));
    public static final Item JADE_SWORD = registerItem("jade_sword",
            new SwordItem(ModToolMaterials.JADE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.JADE, 2, 1.5f))));
    public static final Item JADE_HOE = registerItem("jade_hoe",
            new HoeItem(ModToolMaterials.JADE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.JADE, -4, 4))));

    public static final Item PLATINUM_PICKAXE = registerItem("platinum_pickaxe",
            new PickaxeItem(ModToolMaterials.PLATINUM, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PLATINUM, 1, 1.4f ))));
    public static final Item PLATINUM_AXE = registerItem("platinum_axe",
            new AxeItem(ModToolMaterials.PLATINUM, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PLATINUM, 4.5f, 1.1f))));
    public static final Item PLATINUM_SHOVEL = registerItem("platinum_shovel",
            new ShovelItem(ModToolMaterials.PLATINUM, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PLATINUM, 0.5f, 1.1f))));
    public static final Item PLATINUM_SWORD = registerItem("platinum_sword",
            new SwordItem(ModToolMaterials.PLATINUM, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PLATINUM, 2, 1.7f))));
    public static final Item PLATINUM_HOE = registerItem("platinum_hoe",
            new HoeItem(ModToolMaterials.PLATINUM, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PLATINUM, -4, 4.1f))));

    //Armor
    public static final Item PINKU_HELMET = registerItem("pinku_helmet",
            new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(40))));
    public static final Item PINKU_CHESTPLATE = registerItem("pinku_chestplate",
            new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(40))));
    public static final Item PINKU_LEGGINGS = registerItem("pinku_leggings",
            new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(40))));
    public static final Item PINKU_BOOTS = registerItem("pinku_boots",
            new ArmorItem(ModArmorMaterials.PINKU_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(40))));

    public static final Item RAINBOW_PYRITE_HELMET = registerItem("rainbow_pyrite_helmet",
            new ModArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33))));
    public static final Item RAINBOW_PYRITE_CHESTPLATE = registerItem("rainbow_pyrite_chestplate",
            new ArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(33))));
    public static final Item RAINBOW_PYRITE_LEGGINGS = registerItem("rainbow_pyrite_leggings",
            new ArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(33))));
    public static final Item RAINBOW_PYRITE_BOOTS = registerItem("rainbow_pyrite_boots",
            new ArmorItem(ModArmorMaterials.RAINBOW_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(33))));

    public static final Item TUNGSTEN_HELMET = registerItem("tungsten_helmet",
            new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(50))));
    public static final Item TUNGSTEN_CHESTPLATE = registerItem("tungsten_chestplate",
            new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(50))));
    public static final Item TUNGSTEN_LEGGINGS = registerItem("tungsten_leggings",
            new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(50))));
    public static final Item TUNGSTEN_BOOTS = registerItem("tungsten_boots",
            new ArmorItem(ModArmorMaterials.TUNGSTEN_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(50))));

    public static final Item ALUMINUM_HELMET = registerItem("aluminum_helmet",
            new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(30))));
    public static final Item ALUMINUM_CHESTPLATE = registerItem("aluminum_chestplate",
            new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(30))));
    public static final Item ALUMINUM_LEGGINGS = registerItem("aluminum_leggings",
            new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(30))));
    public static final Item ALUMINUM_BOOTS = registerItem("aluminum_boots",
            new ArmorItem(ModArmorMaterials.ALUMINUM_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(30))));

    public static final Item SAPPHIRE_HELMET = registerItem("sapphire_helmet",
            new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(50))));
    public static final Item SAPPHIRE_CHESTPLATE = registerItem("sapphire_chestplate",
            new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(50))));
    public static final Item SAPPHIRE_LEGGINGS = registerItem("sapphire_leggings",
            new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(50))));
    public static final Item SAPPHIRE_BOOTS = registerItem("sapphire_boots",
            new ArmorItem(ModArmorMaterials.SAPPHIRE_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(50))));

    public static final Item JADE_HELMET = registerItem("jade_helmet",
            new ArmorItem(ModArmorMaterials.JADE_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(34))));
    public static final Item JADE_CHESTPLATE = registerItem("jade_chestplate",
            new ArmorItem(ModArmorMaterials.JADE_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(34))));
    public static final Item JADE_LEGGINGS = registerItem("jade_leggings",
            new ArmorItem(ModArmorMaterials.JADE_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(34))));
    public static final Item JADE_BOOTS = registerItem("jade_boots",
            new ArmorItem(ModArmorMaterials.JADE_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(34))));

    public static final Item PLATINUM_HELMET = registerItem("platinum_helmet",
            new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(34))));
    public static final Item PLATINUM_CHESTPLATE = registerItem("platinum_chestplate",
            new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(34))));
    public static final Item PLATINUM_LEGGINGS = registerItem("platinum_leggings",
            new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(34))));
    public static final Item PLATINUM_BOOTS = registerItem("platinum_boots",
            new ArmorItem(ModArmorMaterials.PLATINUM_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(34))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PizzasOAT.MOD_ID, name), item);
    }

    private static void customIngredients(FabricItemGroupEntries entries) {
        entries.add(PINKU);
        entries.add(PINKU_SHARD);
        entries.add(RAINBOW_PYRITE);
        entries.add(WOLFRAMITE);
        entries.add(SCHEELITE);
        entries.add(TRONA);
        entries.add(SODIUM_CARBONATE);
        entries.add(PURE_TUNGSTEN);
        entries.add(TUNGSTEN);
        entries.add(BAUXITE);
        entries.add(ALUMINUM);
        entries.add(SAPPHIRE);
        entries.add(NEPHRITE);
        entries.add(JADEITE);
        entries.add(JADE);
        entries.add(SULFUR);
    }

    public static void registerModItems() {
        PizzasOAT.LOGGER.info("Registering ModItems for " + PizzasOAT.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
