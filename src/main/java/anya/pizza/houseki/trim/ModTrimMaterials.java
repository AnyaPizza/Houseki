package anya.pizza.houseki.trim;

import anya.pizza.houseki.Houseki;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;

import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> RAINBOW_PYRITE = registryKey("rainbow_pyrite");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, RAINBOW_PYRITE, Style.EMPTY.withColor(TextColor.parseColor("#b03fe0").getOrThrow()), MaterialAssetGroup.create("rainbow_pyrite"));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> registryKey, Style style, MaterialAssetGroup assets) {
        Component description = Component.translatable(Util.makeDescriptionId("trim_material", registryKey.identifier())).withStyle(style);
        context.register(registryKey, new TrimMaterial(assets, description));
    }

    private static ResourceKey<TrimMaterial> registryKey(final String trim) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, trim));
    }
}