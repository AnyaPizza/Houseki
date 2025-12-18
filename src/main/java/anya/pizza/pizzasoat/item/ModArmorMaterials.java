package anya.pizza.pizzasoat.item;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentModel;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;


public class ModArmorMaterials {
    public static EquipmentModel PINKU = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "pinku")).build();
    public static EquipmentModel RAINBOW = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "rainbow")).build();
    public static EquipmentModel TUNGSTEN = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "tungsten")).build();
    public static EquipmentModel ALUMINUM = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "aluminum")).build();
    public static EquipmentModel SAPPHIRE = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "sapphire")).build();
    public static EquipmentModel NEPHRITE = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "nephrite")).build();
    public static EquipmentModel JADEITE = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "jadeite")).build();
    public static EquipmentModel PLATINUM = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "platinum")).build();
    public static EquipmentModel STEEL = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "steel")).build();
    public static EquipmentModel CAST_STEEL = EquipmentModel.builder().addHumanoidLayers(Identifier.of(PizzasOAT.MOD_ID, "cast_steel")).build();

    public static final ArmorMaterial PINKU_MATERIAL = new ArmorMaterial(2580, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 6);
        map.put(EquipmentType.CHESTPLATE, 11);
        map.put(EquipmentType.LEGGINGS, 9);
        map.put(EquipmentType.BOOTS, 6);
        map.put(EquipmentType.BODY, 14);
    }), 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5, 0.3f, ModTags.Items.PINKU_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "pinku"));

    public static final ArmorMaterial RAINBOW_MATERIAL = new ArmorMaterial(2000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.BODY, 11);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5f, 0.1f, ModTags.Items.RAINBOW_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "rainbow_pyrite"));

    public static final ArmorMaterial TUNGSTEN_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 12);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4, 0.2f, ModTags.Items.TUNGSTEN_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "tungsten"));

    public static final ArmorMaterial ALUMINUM_MATERIAL = new ArmorMaterial(1500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.BODY, 10);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5f, 0.1f, ModTags.Items.ALUMINUM_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "aluminum"));

    public static final ArmorMaterial SAPPHIRE_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 5);
        map.put(EquipmentType.CHESTPLATE, 10);
        map.put(EquipmentType.LEGGINGS, 8);
        map.put(EquipmentType.BOOTS, 5);
        map.put(EquipmentType.BODY, 13);
    }), 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.5f, 0.3f, ModTags.Items.SAPPHIRE_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "sapphire"));

    public static final ArmorMaterial NEPHRITE_MATERIAL = new ArmorMaterial(2000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 5);
        map.put(EquipmentType.BODY, 11);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5f, 0.3f, ModTags.Items.NEPHRITE_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "nephrite"));

    public static final ArmorMaterial JADEITE_MATERIAL = new ArmorMaterial(2500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 10);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 6);
        map.put(EquipmentType.BODY, 12);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4, 0.4f, ModTags.Items.JADEITE_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "jadeite"));

    public static final ArmorMaterial PLATINUM_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 12);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 4, 0.2f, ModTags.Items.PLATINUM_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "platinum"));

    public static final ArmorMaterial STEEL_MATERIAL = new ArmorMaterial(2500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 11);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5f, 0.2f, ModTags.Items.STEEL_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "steel"));

    public static final ArmorMaterial CAST_STEEL_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 12);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.5f, 0.3f, ModTags.Items.CAST_STEEL_REPAIR,
            Identifier.of(PizzasOAT.MOD_ID, "cast_steel"));

}
