package anya.pizza.houseki.trim;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> RAINBOW_PYRITE =
            RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(Houseki.MOD_ID, "rainbow_pyrite"));

    /**
     * Initializes and registers the mod's armor trim materials.
     *
     * @param registerable the registry to add the mod's ArmorTrimMaterial entries to
     */
    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, RAINBOW_PYRITE, Registries.ITEM.getEntry(ModItems.RAINBOW_PYRITE),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()));
    }

    /**
     * Creates and registers an ArmorTrimMaterial under the provided registry key using the given display style.
     *
     * @param registerable the registry to add the trim material to
     * @param trimMaterialKey the registry key under which the trim material will be registered
     * @param item a registry entry for the item associated with the trim material (used to identify the material)
     * @param style the text style to apply to the trim material's display name
     */
    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> trimMaterialKey, RegistryEntry<Item> item, Style style) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(ArmorTrimAssets.of("rainbow_pyrite"),
                Text.translatable(Util.createTranslationKey("trim_material", trimMaterialKey.getValue())).fillStyle(style));

        registerable.register(trimMaterialKey, trimMaterial);
    }
}/*extends FabricDynamicRegistryProvider {
    public ModTrimMaterials(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
    ArmorTrimAssets.AssetId rainbowPyriteID = new ArmorTrimAssets.AssetId("rainbow_pyrite");
        entries.add(RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of("houseki", "rainbow_pyrite")),
                new ArmorTrimMaterial(new ArmorTrimAssets(rainbowPyriteID, Map.of()),
                        Text.translatable("trim_material.houseki.rainbow_pyrite")));
    }

    @Override
    public String getName() {
        return "Trim Materials";
    }
}*/
