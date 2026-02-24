package anya.pizza.houseki.trim;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
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
            if (server.getTicks() % 40 == 0) {
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    applyEffects(player);
                }
            }
        });
    }

    private static void applyEffects(ServerPlayerEntity player) {
        int amethystCount = 0;
        int diamondCount = 0;

        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = player.getEquippedStack(slot);
            if (stack.isEmpty()) continue;

            ArmorTrim trim = stack.get(DataComponentTypes.TRIM);
            if (trim == null) continue;

            if (trim.material().matchesKey(ArmorTrimMaterials.AMETHYST)) amethystCount++;
            if (trim.material().matchesKey(ArmorTrimMaterials.DIAMOND)) diamondCount++;
        }

        handleAmethystBonus(player, amethystCount);
        handleDiamondBonus(player, diamondCount);
    }

    private static void handleAmethystBonus(ServerPlayerEntity player, int aCount) {
        if (aCount >= 4) {
            // Full Set
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 1, true, false, true));
        } else if (aCount > 0) {
            // Anything under a full set
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0, true, false, true));
        }
    }

    private static void handleDiamondBonus(ServerPlayerEntity player, int dCount) {
        if (dCount >= 4) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 80, 1, true, false, true));
        } else if (dCount > 0) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 80, 0, true, false, true));
        }
    }
}
