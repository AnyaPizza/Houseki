package anya.pizza.pizzasoat.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class TungstenSwordItem extends SwordItem {
    public TungstenSwordItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.hasStatusEffect(StatusEffects.POISON)) {//checks if target has the effect already
            if (attacker.getRandom().nextFloat() < 0.4f) {//40% chance to give effect
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 500, 0, false, true), attacker);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
