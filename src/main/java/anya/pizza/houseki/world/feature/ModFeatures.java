package anya.pizza.houseki.world.feature;

import anya.pizza.houseki.Houseki;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModFeatures {
    public static final Feature<DefaultFeatureConfig> METEORITE = Registry.register(
            Registries.FEATURE,
            Identifier.of(Houseki.MOD_ID, "meteorite"),
            new MeteoriteFeature()
    );

    public static void registerFeatures() {
        Houseki.LOGGER.info("Registering Features for " + Houseki.MOD_ID);
    }
}
