package anya.pizza.houseki.item.custom;

import anya.pizza.houseki.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends Item {
    private static final Map<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.RAINBOW_MATERIAL, List.of(new MobEffectInstance(MobEffects.LUCK, 20, 1, false, false, true)))
                    .put(ModArmorMaterials.NEPHRITE_MATERIAL, List.of(new MobEffectInstance(MobEffects.REGENERATION, 20, 0, false, false, true)))
                    .put(ModArmorMaterials.JADEITE_MATERIAL, List.of(new MobEffectInstance(MobEffects.REGENERATION, 20, 1, false, false, true)))
                    .put(ModArmorMaterials.SAPPHIRE_MATERIAL, List.of(new MobEffectInstance(MobEffects.RESISTANCE, 20, 4, false, false, true)))
                    .put(ModArmorMaterials.TUNGSTEN_MATERIAL, List.of(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 4, false, false, true)))
                    .put(ModArmorMaterials.CAST_STEEL_MATERIAL, List.of(new MobEffectInstance(MobEffects.STRENGTH, 20, 1, false, false, true)))
                    .put(ModArmorMaterials.PLATINUM_MATERIAL, List.of(new MobEffectInstance(MobEffects.ABSORPTION, 20, 1, false, false, true)))
                    .build();

    public ModArmorItem(Properties settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        if (!world.isClientSide()) {
            if (entity instanceof Player player) {
                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, List<MobEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().anyMatch(statusEffectInstance -> player.hasEffect(statusEffectInstance.getEffect()));

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            for (MobEffectInstance instance : mapStatusEffect) {
                player.addEffect(new MobEffectInstance(instance.getEffect(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.isVisible()));
            }
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        Equippable equippableComponentBoots = player.getItemBySlot(EquipmentSlot.FEET).getItem().components().get(DataComponents.EQUIPPABLE);
        Equippable equippableComponentLeggings = player.getItemBySlot(EquipmentSlot.LEGS).getItem().components().get(DataComponents.EQUIPPABLE);
        Equippable equippableComponentBreastplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem().components().get(DataComponents.EQUIPPABLE);
        Equippable equippableComponentHelmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem().components().get(DataComponents.EQUIPPABLE);

        return equippableComponentBoots.assetId().get().equals(material.assetId()) && equippableComponentLeggings.assetId().get().equals(material.assetId()) &&
                equippableComponentBreastplate.assetId().get().equals(material.assetId()) && equippableComponentHelmet.assetId().get().equals(material.assetId());
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }
}
