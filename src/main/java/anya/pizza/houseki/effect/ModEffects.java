package anya.pizza.houseki.effect;

import anya.pizza.houseki.Houseki;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ModEffects {
    public static final Holder<MobEffect> SUGILITE_PROTECTION = registerMobEffect("sugilite_protection",
            new SugiliteProtectionStatusEffect(MobEffectCategory.BENEFICIAL, 0x6AC7FF)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
                            Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "sugilite_protection"), 2.0F,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect mobEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name), mobEffect);
    }

    public static void registerEffects() {
        Houseki.LOGGER.info("Registering Effects for " + Houseki.MOD_ID);
    }
}
