package anya.pizza.houseki.item;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class ModArmorMaterials {
    static RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));
    public static final RegistryKey<EquipmentAsset> PINKU_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "pinku"));
    public static final RegistryKey<EquipmentAsset> RAINBOW_PYRITE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "rainbow_pyrite"));
    public static final RegistryKey<EquipmentAsset> TUNGSTEN_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "tungsten"));
    public static final RegistryKey<EquipmentAsset> ALUMINUM_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "aluminum"));
    public static final RegistryKey<EquipmentAsset> SAPPHIRE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "sapphire"));
    public static final RegistryKey<EquipmentAsset> NEPHRITE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "nephrite"));
    public static final RegistryKey<EquipmentAsset> JADEITE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "jadeite"));
    public static final RegistryKey<EquipmentAsset> PLATINUM_KEY =RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "platinum"));
    public static final RegistryKey<EquipmentAsset> STEEL_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "steel"));
    public static final RegistryKey<EquipmentAsset> CAST_STEEL_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(Houseki.MOD_ID, "cast_steel"));

    public static final ArmorMaterial PINKU_MATERIAL = new ArmorMaterial(2580, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 6);
        map.put(EquipmentType.CHESTPLATE, 11);
        map.put(EquipmentType.LEGGINGS, 9);
        map.put(EquipmentType.BOOTS, 6);
        map.put(EquipmentType.BODY, 14);
    }), 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5, 0.3f, ModTags.Items.PINKU_REPAIR, PINKU_KEY);

    public static final ArmorMaterial RAINBOW_MATERIAL = new ArmorMaterial(2000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.BODY, 11);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5f, 0.1f, ModTags.Items.RAINBOW_REPAIR, RAINBOW_PYRITE_KEY);

    public static final ArmorMaterial TUNGSTEN_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 12);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4, 0.2f, ModTags.Items.TUNGSTEN_REPAIR, TUNGSTEN_KEY);

    public static final ArmorMaterial ALUMINUM_MATERIAL = new ArmorMaterial(1500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.BODY, 10);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5f, 0.1f, ModTags.Items.ALUMINUM_REPAIR, ALUMINUM_KEY);

    public static final ArmorMaterial SAPPHIRE_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 5);
        map.put(EquipmentType.CHESTPLATE, 10);
        map.put(EquipmentType.LEGGINGS, 8);
        map.put(EquipmentType.BOOTS, 5);
        map.put(EquipmentType.BODY, 13);
    }), 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.5f, 0.3f, ModTags.Items.SAPPHIRE_REPAIR, SAPPHIRE_KEY);

    public static final ArmorMaterial NEPHRITE_MATERIAL = new ArmorMaterial(2000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 5);
        map.put(EquipmentType.BODY, 11);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5f, 0.3f, ModTags.Items.NEPHRITE_REPAIR, NEPHRITE_KEY);

    public static final ArmorMaterial JADEITE_MATERIAL = new ArmorMaterial(2500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 10);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 6);
        map.put(EquipmentType.BODY, 12);
    }), 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4, 0.4f, ModTags.Items.JADEITE_REPAIR, JADEITE_KEY);

    public static final ArmorMaterial PLATINUM_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 12);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 4, 0.2f, ModTags.Items.PLATINUM_REPAIR, PLATINUM_KEY);

    public static final ArmorMaterial STEEL_MATERIAL = new ArmorMaterial(2500, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 11);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5f, 0.2f, ModTags.Items.STEEL_REPAIR, STEEL_KEY);

    public static final ArmorMaterial CAST_STEEL_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 4);
        map.put(EquipmentType.CHESTPLATE, 9);
        map.put(EquipmentType.LEGGINGS, 7);
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.BODY, 12);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.5f, 0.3f, ModTags.Items.CAST_STEEL_REPAIR, CAST_STEEL_KEY);
}