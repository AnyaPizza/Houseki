package anya.pizza.houseki.trim;

import anya.pizza.houseki.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.trim.ArmorTrim;
import net.minecraft.item.equipment.trim.ArmorTrimMaterials;
import net.minecraft.server.network.ServerPlayerEntity;

public class TrimEffectHandler {
    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    public static void registerTrimEffects() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            //Could cause lag for big servers. May need to rebalance in future if needed.
            if (server.getTicks() % 40 == 0) {
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    applyEffects(player);
                }
            }
        });
    }

    private static void applyEffects(ServerPlayerEntity player) {
        int sugiliteCount = 0;
        int bismuthCount = 0;

        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = player.getEquippedStack(slot);
            if (stack.isEmpty()) continue;

            ArmorTrim trim = stack.get(DataComponentTypes.TRIM);
            if (trim == null) continue;

            if (trim.material().matchesKey(ModTrimMaterials.SUGILITE)) sugiliteCount++;
            if (trim.material().matchesKey(ModTrimMaterials.BISMUTH)) bismuthCount++;
        }

        handleSugiliteBonus(player, sugiliteCount);
        handleBismuthBonus(player, bismuthCount);
    }

    private static void handleSugiliteBonus(ServerPlayerEntity player, int aCount) {
        if (aCount >= 4) {
            // Full Set
            player.addStatusEffect(new StatusEffectInstance(ModEffects.SUGILITE_PROTECTION, 50, 1, true, false, false));
        } else if (aCount > 0) {
            // Anything under a full set
            player.addStatusEffect(new StatusEffectInstance(ModEffects.SUGILITE_PROTECTION, 50, 0, true, false, false));
        }
    }

    private static void handleBismuthBonus(ServerPlayerEntity player, int dCount) {
        if (dCount >= 4) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 50, 1, true, false, false));
        } else if (dCount > 0) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 50, 0, true, false, false));
        }
    }
}