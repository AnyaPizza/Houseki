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
    public static final ResourceKey<TrimMaterial> PINKU = registryKey("pinku");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = registryKey("sapphire");
    public static final ResourceKey<TrimMaterial> NEPHRITE = registryKey("nephrite");
    public static final ResourceKey<TrimMaterial> JADEITE = registryKey("jadeite");
    public static final ResourceKey<TrimMaterial> CAST_STEEL = registryKey("cast_steel");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        // Trims with no effects
        register(context, RAINBOW_PYRITE, Style.EMPTY.withColor(TextColor.parseColor("#b03fe0").getOrThrow()), MaterialAssetGroup.create("rainbow_pyrite"));
        register(context, PINKU, Style.EMPTY.withColor(TextColor.parseColor("#f10af7").getOrThrow()), MaterialAssetGroup.create("pinku"));
        register(context, SAPPHIRE, Style.EMPTY.withColor(TextColor.parseColor("#0f52ba").getOrThrow()), MaterialAssetGroup.create("sapphire"));
        register(context, NEPHRITE, Style.EMPTY.withColor(TextColor.parseColor("#60A472").getOrThrow()), MaterialAssetGroup.create("nephrite"));
        register(context, JADEITE, Style.EMPTY.withColor(TextColor.parseColor("#246542").getOrThrow()), MaterialAssetGroup.create("jadeite"));
        register(context, CAST_STEEL, Style.EMPTY.withColor(TextColor.parseColor("#8B929B").getOrThrow()), MaterialAssetGroup.create("cast_steel"));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> registryKey, Style style, MaterialAssetGroup assets) {
        Component description = Component.translatable(Util.makeDescriptionId("trim_material", registryKey.identifier())).withStyle(style);
        context.register(registryKey, new TrimMaterial(assets, description));
    }

    private static ResourceKey<TrimMaterial> registryKey(final String trim) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, trim));
    }
}