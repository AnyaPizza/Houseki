package anya.pizza.houseki.trim;

import anya.pizza.houseki.Houseki;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> RAINBOW_PYRITE =
            RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(Houseki.MOD_ID, "rainbow_pyrite"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, RAINBOW_PYRITE, Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()));
    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> trimMaterialKey, Style style) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(ArmorTrimAssets.of(trimMaterialKey.getValue().getPath()),
                Text.translatable(Util.createTranslationKey("trim_material", trimMaterialKey.getValue())).fillStyle(style));

        registerable.register(trimMaterialKey, trimMaterial);
    }
}