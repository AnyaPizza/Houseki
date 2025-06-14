package anya.pizza.pizzasoat.datagen;

import anya.pizza.pizzasoat.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.PINKU)
                .add(ModItems.TUNGSTEN)
                .add(ModItems.ALUMINUM)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.JADE)
                .add(ModItems.PLATINUM);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PINKU_HELMET)
                .add(ModItems.PINKU_CHESTPLATE)
                .add(ModItems.PINKU_LEGGINGS)
                .add(ModItems.PINKU_BOOTS)
                .add(ModItems.RAINBOW_PYRITE_HELMET)
                .add(ModItems.RAINBOW_PYRITE_CHESTPLATE)
                .add(ModItems.RAINBOW_PYRITE_LEGGINGS)
                .add(ModItems.RAINBOW_PYRITE_BOOTS)
                .add(ModItems.TUNGSTEN_HELMET)
                .add(ModItems.TUNGSTEN_CHESTPLATE)
                .add(ModItems.TUNGSTEN_LEGGINGS)
                .add(ModItems.TUNGSTEN_BOOTS)
                .add(ModItems.ALUMINUM_HELMET)
                .add(ModItems.ALUMINUM_CHESTPLATE)
                .add(ModItems.ALUMINUM_LEGGINGS)
                .add(ModItems.ALUMINUM_BOOTS)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.SAPPHIRE_BOOTS)
                .add(ModItems.JADE_HELMET)
                .add(ModItems.JADE_CHESTPLATE)
                .add(ModItems.JADE_LEGGINGS)
                .add(ModItems.JADE_BOOTS)
                .add(ModItems.PLATINUM_HELMET)
                .add(ModItems.PLATINUM_CHESTPLATE)
                .add(ModItems.PLATINUM_LEGGINGS)
                .add(ModItems.PLATINUM_BOOTS);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.PINKU_AXE)
                .add(ModItems.RAINBOW_PYRITE_AXE)
                .add(ModItems.TUNGSTEN_AXE)
                .add(ModItems.ALUMINUM_AXE)
                .add(ModItems.SAPPHIRE_AXE)
                .add(ModItems.JADE_AXE)
                .add(ModItems.PLATINUM_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.PINKU_HOE)
                .add(ModItems.RAINBOW_PYRITE_HOE)
                .add(ModItems.TUNGSTEN_HOE)
                .add(ModItems.ALUMINUM_HOE)
                .add(ModItems.SAPPHIRE_HOE)
                .add(ModItems.JADE_HOE)
                .add(ModItems.PLATINUM_HOE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.PINKU_SHOVEL)
                .add(ModItems.RAINBOW_PYRITE_SHOVEL)
                .add(ModItems.TUNGSTEN_SHOVEL)
                .add(ModItems.ALUMINUM_SHOVEL)
                .add(ModItems.SAPPHIRE_SHOVEL)
                .add(ModItems.JADE_SHOVEL)
                .add(ModItems.PLATINUM_SHOVEL);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.PINKU_SWORD)
                .add(ModItems.RAINBOW_PYRITE_SWORD)
                .add(ModItems.TUNGSTEN_SWORD)
                .add(ModItems.ALUMINUM_SWORD)
                .add(ModItems.SAPPHIRE_SWORD)
                .add(ModItems.JADE_SWORD)
                .add(ModItems.PLATINUM_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.PINKU_PICKAXE)
                .add(ModItems.RAINBOW_PYRITE_PICKAXE)
                .add(ModItems.TUNGSTEN_PICKAXE)
                .add(ModItems.ALUMINUM_PICKAXE)
                .add(ModItems.SAPPHIRE_PICKAXE)
                .add(ModItems.JADE_PICKAXE)
                .add(ModItems.PLATINUM_PICKAXE);

    }
}
