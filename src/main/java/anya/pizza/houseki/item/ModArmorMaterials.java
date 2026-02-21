package anya.pizza.houseki.item;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.util.ModTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class ModArmorMaterials {
    static ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> PINKU_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "pinku"));
    public static final ResourceKey<EquipmentAsset> RAINBOW_PYRITE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "rainbow_pyrite"));
    public static final ResourceKey<EquipmentAsset> TUNGSTEN_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "tungsten"));
    public static final ResourceKey<EquipmentAsset> ALUMINUM_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "aluminum"));
    public static final ResourceKey<EquipmentAsset> SAPPHIRE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "sapphire"));
    public static final ResourceKey<EquipmentAsset> NEPHRITE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "nephrite"));
    public static final ResourceKey<EquipmentAsset> JADEITE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "jadeite"));
    public static final ResourceKey<EquipmentAsset> PLATINUM_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "platinum"));
    public static final ResourceKey<EquipmentAsset> STEEL_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "steel"));
    public static final ResourceKey<EquipmentAsset> CAST_STEEL_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "cast_steel"));

    public static final ArmorMaterial PINKU_MATERIAL = new ArmorMaterial(2580, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 6);
        map.put(ArmorType.CHESTPLATE, 11);
        map.put(ArmorType.LEGGINGS, 9);
        map.put(ArmorType.BOOTS, 6);
        map.put(ArmorType.BODY, 14);
    }), 16, SoundEvents.ARMOR_EQUIP_NETHERITE, 5, 0.3f, ModTags.Items.PINKU_REPAIR, PINKU_KEY);

    public static final ArmorMaterial RAINBOW_MATERIAL = new ArmorMaterial(2000, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 3);
        map.put(ArmorType.CHESTPLATE, 8);
        map.put(ArmorType.LEGGINGS, 6);
        map.put(ArmorType.BOOTS, 3);
        map.put(ArmorType.BODY, 11);
    }), 11, SoundEvents.ARMOR_EQUIP_IRON, 2.5f, 0.1f, ModTags.Items.RAINBOW_REPAIR, RAINBOW_PYRITE_KEY);

    public static final ArmorMaterial TUNGSTEN_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 4);
        map.put(ArmorType.CHESTPLATE, 9);
        map.put(ArmorType.LEGGINGS, 7);
        map.put(ArmorType.BOOTS, 4);
        map.put(ArmorType.BODY, 12);
    }), 15, SoundEvents.ARMOR_EQUIP_IRON, 4, 0.2f, ModTags.Items.TUNGSTEN_REPAIR, TUNGSTEN_KEY);

    public static final ArmorMaterial ALUMINUM_MATERIAL = new ArmorMaterial(1500, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 3);
        map.put(ArmorType.CHESTPLATE, 8);
        map.put(ArmorType.LEGGINGS, 6);
        map.put(ArmorType.BOOTS, 3);
        map.put(ArmorType.BODY, 10);
    }), 11, SoundEvents.ARMOR_EQUIP_IRON, 2.5f, 0.1f, ModTags.Items.ALUMINUM_REPAIR, ALUMINUM_KEY);

    public static final ArmorMaterial SAPPHIRE_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 5);
        map.put(ArmorType.CHESTPLATE, 10);
        map.put(ArmorType.LEGGINGS, 8);
        map.put(ArmorType.BOOTS, 5);
        map.put(ArmorType.BODY, 13);
    }), 12, SoundEvents.ARMOR_EQUIP_DIAMOND, 4.5f, 0.3f, ModTags.Items.SAPPHIRE_REPAIR, SAPPHIRE_KEY);

    public static final ArmorMaterial NEPHRITE_MATERIAL = new ArmorMaterial(2000, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 3);
        map.put(ArmorType.CHESTPLATE, 9);
        map.put(ArmorType.LEGGINGS, 6);
        map.put(ArmorType.BOOTS, 5);
        map.put(ArmorType.BODY, 11);
    }), 11, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.5f, 0.3f, ModTags.Items.NEPHRITE_REPAIR, NEPHRITE_KEY);

    public static final ArmorMaterial JADEITE_MATERIAL = new ArmorMaterial(2500, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 4);
        map.put(ArmorType.CHESTPLATE, 10);
        map.put(ArmorType.LEGGINGS, 7);
        map.put(ArmorType.BOOTS, 6);
        map.put(ArmorType.BODY, 12);
    }), 11, SoundEvents.ARMOR_EQUIP_DIAMOND, 4, 0.4f, ModTags.Items.JADEITE_REPAIR, JADEITE_KEY);

    public static final ArmorMaterial PLATINUM_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 4);
        map.put(ArmorType.CHESTPLATE, 9);
        map.put(ArmorType.LEGGINGS, 7);
        map.put(ArmorType.BOOTS, 4);
        map.put(ArmorType.BODY, 12);
    }), 15, SoundEvents.ARMOR_EQUIP_GOLD, 4, 0.2f, ModTags.Items.PLATINUM_REPAIR, PLATINUM_KEY);

    public static final ArmorMaterial STEEL_MATERIAL = new ArmorMaterial(2500, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 4);
        map.put(ArmorType.CHESTPLATE, 9);
        map.put(ArmorType.LEGGINGS, 7);
        map.put(ArmorType.BOOTS, 4);
        map.put(ArmorType.BODY, 11);
    }), 15, SoundEvents.ARMOR_EQUIP_IRON, 3.5f, 0.2f, ModTags.Items.STEEL_REPAIR, STEEL_KEY);

    public static final ArmorMaterial CAST_STEEL_MATERIAL = new ArmorMaterial(3000, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 4);
        map.put(ArmorType.CHESTPLATE, 9);
        map.put(ArmorType.LEGGINGS, 7);
        map.put(ArmorType.BOOTS, 4);
        map.put(ArmorType.BODY, 12);
    }), 15, SoundEvents.ARMOR_EQUIP_IRON, 4.5f, 0.3f, ModTags.Items.CAST_STEEL_REPAIR, CAST_STEEL_KEY);
}