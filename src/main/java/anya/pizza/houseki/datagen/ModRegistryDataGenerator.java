package anya.pizza.houseki.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataGenerator extends FabricDynamicRegistryProvider {
    /**
     * Creates a ModRegistryDataGenerator that provides dynamic registry data for world generation.
     *
     * @param output         destination for generated pack data
     * @param registriesFuture a future that supplies registry lookups required during data generation
     */
    public ModRegistryDataGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Adds all configured and placed world-generation features from the provided registries into the given Entries collection.
     *
     * @param registries provider used to lookup the CONFIGURED_FEATURE and PLACED_FEATURE registries
     * @param entries    destination collection to receive all entries from those registries
     */
    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    /**
     * Provides the human-readable name for this data provider.
     *
     * @return the provider name "World Gen"
     */
    @Override
    public @NonNull String getName() {
        return "World Gen";
    }
}