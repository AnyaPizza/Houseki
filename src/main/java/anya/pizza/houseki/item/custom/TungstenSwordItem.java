package anya.pizza.houseki.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class TungstenSwordItem extends Item {
    /**
     * Creates a Tungsten sword item configured with the given material, attack damage, attack speed, and item properties.
     *
     * @param material     the tool material of the sword
     * @param attackDamage the base attack damage modifier for the sword
     * @param attackSpeed  the base attack speed modifier for the sword
     * @param settings     item properties used to configure the resulting sword item
     */
    public TungstenSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.sword(material, attackDamage, attackSpeed));
    }

    /**
     * Applies Poison I to the struck entity when this sword hits, subject to conditions.
     *
     * If the target does not already have the Poison effect, there is a 40% chance to apply
     * Poison with a duration of 500 ticks and amplifier 0; the attacker is recorded as the effect source.
     *
     * @param stack    the ItemStack of this sword (unused by this method)
     * @param target   the entity being hit; may receive the Poison effect
     * @param attacker the entity that performed the hit and is recorded as the effect source
     */
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.hasEffect(MobEffects.POISON)) {//checks if target has the effect already
            if (attacker.getRandom().nextFloat() < 0.4f) {//40% chance to give effect
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 500, 0, false, true), attacker);
            }
        }
    }
}