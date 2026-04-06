package anya.pizza.houseki.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SugiliteProtectionStatusEffect extends MobEffect {
    protected SugiliteProtectionStatusEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 50, 1, true, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 50, 1, true, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 50, 1, true, false, false));
        return super.applyEffectTick(serverLevel, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
