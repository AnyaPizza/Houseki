package anya.pizza.houseki.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataGenerator extends FabricDynamicRegistryProvider {
    public ModRegistryDataGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Populates the given Entries with all entries from the TRIM_MATERIAL, CONFIGURED_FEATURE, and PLACED_FEATURE registries.
     *
     * @param registries lookup used to retrieve registry contents by RegistryKey
     * @param entries target collection to receive the registry entries
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.TRIM_MATERIAL));
        entries.addAll(registries.getOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getOrThrow(RegistryKeys.PLACED_FEATURE));
    }

    /**
     * Provide the display name for this data provider.
     *
     * @return the display name of this provider
     */
    @Override
    public String getName() {
        return "Datagen";
    }
}