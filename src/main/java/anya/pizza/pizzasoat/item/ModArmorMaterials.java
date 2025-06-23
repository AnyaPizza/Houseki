package anya.pizza.pizzasoat.item;

import anya.pizza.pizzasoat.PizzasOAT;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> PINKU_MATERIAL = registerArmorMaterial("pinku",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.HELMET, 6);
                        map.put(ArmorItem.Type.CHESTPLATE, 11);
                        map.put(ArmorItem.Type.LEGGINGS, 9);
                        map.put(ArmorItem.Type.BOOTS, 6);
                        map.put(ArmorItem.Type.BODY, 14);
            }), 16, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItems.PINKU),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "pinku"))), 5, 0.3f));

    public static final RegistryEntry<ArmorMaterial> RAINBOW_MATERIAL = registerArmorMaterial("rainbow_pyrite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(ModItems.RAINBOW_PYRITE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "rainbow_pyrite"))), 2.5f, 0.1f));

    public static final RegistryEntry<ArmorMaterial> TUNGSTEN_MATERIAL = registerArmorMaterial("tungsten",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.BODY, 12);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(ModItems.TUNGSTEN),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "tungsten"))), 4, 0.2f));

    public static final RegistryEntry<ArmorMaterial> ALUMINUM_MATERIAL = registerArmorMaterial("aluminum",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.BODY, 10);
            }), 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(ModItems.ALUMINUM),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "aluminum"))), 2.5f, 0.1f));

    public static final RegistryEntry<ArmorMaterial> SAPPHIRE_MATERIAL = registerArmorMaterial("sapphire",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.BODY, 13);
            }), 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItems.SAPPHIRE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "sapphire"))), 4.5f, 0.3f));

    public static final RegistryEntry<ArmorMaterial> JADE_MATERIAL = registerArmorMaterial("jade",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.CHESTPLATE,8);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItems.JADE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "jade"))), 3, 0.2f));

    public static final RegistryEntry<ArmorMaterial> PLATINUM_MATERIAL = registerArmorMaterial("platinum",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.BODY, 12);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.ofItems(ModItems.PLATINUM),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "platinum"))), 4, 0.2f));

    public static final RegistryEntry<ArmorMaterial> STEEL_MATERIAL = registerArmorMaterial("steel",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.BODY, 11);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(ModItems.STEEL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(PizzasOAT.MOD_ID, "steel"))), 3.5f, 0.2f));


    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(PizzasOAT.MOD_ID, name), material.get());
    }
}
