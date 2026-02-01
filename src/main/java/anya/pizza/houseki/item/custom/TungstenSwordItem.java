package anya.pizza.houseki.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class TungstenSwordItem extends Item {
    public TungstenSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.sword(material, attackDamage, attackSpeed));
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.hasEffect(MobEffects.POISON)) {//checks if target has the effect already
            if (attacker.getRandom().nextFloat() < 0.4f) {//40% chance to give effect
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 500, 0, false, true), attacker);
            }
        }
    }
}
