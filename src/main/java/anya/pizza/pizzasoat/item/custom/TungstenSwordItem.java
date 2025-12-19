package anya.pizza.pizzasoat.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class TungstenSwordItem extends Item {
    public TungstenSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.sword(material, attackDamage, attackSpeed));
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.hasStatusEffect(StatusEffects.POISON)) {//checks if target has the effect already
            if (attacker.getRandom().nextFloat() < 0.4f) {//40% chance to give effect
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0, false, true), attacker);
            }
        }
    }
}
