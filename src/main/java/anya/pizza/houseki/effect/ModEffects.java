package anya.pizza.houseki.effect;

import anya.pizza.houseki.Houseki;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SUGILITE_PROTECTION = registerStatusEffect("sugilite_protection",
            new SugiliteProtectionStatusEffect(StatusEffectCategory.BENEFICIAL, 0x6AC7FF)
                    .addAttributeModifier(EntityAttributes.ARMOR_TOUGHNESS,
                            Identifier.of(Houseki.MOD_ID, "sugilite_protection"), 2.0F,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Houseki.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Houseki.LOGGER.info("Registering Effects for " + Houseki.MOD_ID);
    }
}
