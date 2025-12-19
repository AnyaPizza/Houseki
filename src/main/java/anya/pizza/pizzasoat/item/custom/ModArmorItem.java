package anya.pizza.pizzasoat.item.custom;

import anya.pizza.pizzasoat.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;


public class ModArmorItem extends Item {
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.RAINBOW_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.LUCK, 20, 1, false, false, true)))
                    .put(ModArmorMaterials.NEPHRITE_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.REGENERATION, 20, 0, false, false, true)))
                    .put(ModArmorMaterials.JADEITE_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.REGENERATION, 20, 1, false, false, true)))
                    .put(ModArmorMaterials.SAPPHIRE_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.RESISTANCE, 20, 4, false, false, true)))
                    .put(ModArmorMaterials.TUNGSTEN_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.FIRE_RESISTANCE, 20, 4, false, false, true)))
                    .put(ModArmorMaterials.CAST_STEEL_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.STRENGTH, 20, 1, false, false, true)))
                    .put(ModArmorMaterials.PLATINUM_MATERIAL, List.of(new StatusEffectInstance(
                            StatusEffects.ABSORPTION, 20, 1, false, false, true)))
                    .build();



    public ModArmorItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, List<StatusEffectInstance> mapStatusEffect) {
        boolean hasPlayerEffect = mapStatusEffect.stream().anyMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            for (StatusEffectInstance instance : mapStatusEffect) {
                player.addStatusEffect(new StatusEffectInstance(instance.getEffectType(),
                        instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles()));
            }
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        EquippableComponent equippableComponentBoots = player.getEquippedStack(EquipmentSlot.FEET).getItem().getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentLeggings = player.getEquippedStack(EquipmentSlot.LEGS).getItem().getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentBreastplate = player.getEquippedStack(EquipmentSlot.CHEST).getItem().getComponents().get(DataComponentTypes.EQUIPPABLE);
        EquippableComponent equippableComponentHelmet = player.getEquippedStack(EquipmentSlot.HEAD).getItem().getComponents().get(DataComponentTypes.EQUIPPABLE);

        return equippableComponentBoots.assetId().get().equals(material.assetId()) && equippableComponentLeggings.assetId().get().equals(material.assetId()) &&
                equippableComponentBreastplate.assetId().get().equals(material.assetId()) && equippableComponentHelmet.assetId().get().equals(material.assetId());
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }
}
