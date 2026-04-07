package anya.pizza.houseki.trim;

import anya.pizza.houseki.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;


public class TrimEffectHandler {
    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    public static void registerTrimEffects() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            //Could cause lag for big servers. May need to rebalance in future if needed.
            if (server.getTickCount() % 40 == 0) {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    applyEffects(player);
                }
            }
        });
    }

    private static void applyEffects(ServerPlayer player) {
        int sugiliteCount = 0;
        int bismuthCount = 0;

        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.isEmpty()) continue;

            ArmorTrim trim = stack.get(DataComponents.TRIM);
            if (trim == null) continue;

            if (trim.material().is(ModTrimMaterials.SUGILITE)) sugiliteCount++;
            if (trim.material().is(ModTrimMaterials.BISMUTH)) bismuthCount++;
        }

        handleSugiliteBonus(player, sugiliteCount);
        handleBismuthBonus(player, bismuthCount);
    }

    private static void handleSugiliteBonus(ServerPlayer player, int aCount) {
        if (aCount >= 4) {
            // Full Set
            player.addEffect(new MobEffectInstance(ModEffects.SUGILITE_PROTECTION, 50, 1, true, false, false));
        } else if (aCount > 0) {
            // Anything under a full set
            player.addEffect(new MobEffectInstance(ModEffects.SUGILITE_PROTECTION, 50, 0, true, false, false));
        }
    }

    private static void handleBismuthBonus(ServerPlayer player, int dCount) {
        if (dCount >= 4) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, 1, true, false, false));
        } else if (dCount > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, 0, true, false, false));
        }
    }
}