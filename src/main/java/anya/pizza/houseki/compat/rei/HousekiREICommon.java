package anya.pizza.houseki.compat.rei;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.ModRecipes;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class HousekiREICommon implements REICommonPlugin {
    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(CrusherRecipe.class)
                .filterType(ModRecipes.CRUSHER_TYPE)
                .fill(entry -> new CrusherDisplay(entry));

        registerCastingDisplays(registry);
    }

    private void registerCastingDisplays(ServerDisplayRegistry registry) {
        // Steel casting recipes
        addCasting(registry, ModItems.STEEL, ModItems.INGOT_CAST, ModItems.CAST_STEEL);
        addCasting(registry, ModItems.STEEL, ModItems.PICKAXE_HEAD_CAST, ModItems.CS_PICKAXE_HEAD);
        addCasting(registry, ModItems.STEEL, ModItems.AXE_HEAD_CAST, ModItems.CS_AXE_HEAD);
        addCasting(registry, ModItems.STEEL, ModItems.SHOVEL_HEAD_CAST, ModItems.CS_SHOVEL_HEAD);
        addCasting(registry, ModItems.STEEL, ModItems.SWORD_HEAD_CAST, ModItems.CS_SWORD_HEAD);
        addCasting(registry, ModItems.STEEL, ModItems.HOE_HEAD_CAST, ModItems.CS_HOE_HEAD);
        addCasting(registry, ModItems.STEEL, ModItems.SPEAR_HEAD_CAST, ModItems.CS_SPEAR_HEAD);
        addCasting(registry, ModItems.STEEL, ModItems.HELMET_CAST, ModItems.CAST_STEEL_HELMET);
        addCasting(registry, ModItems.STEEL, ModItems.CHESTPLATE_CAST, ModItems.CAST_STEEL_CHESTPLATE);
        addCasting(registry, ModItems.STEEL, ModItems.LEGGINGS_CAST, ModItems.CAST_STEEL_LEGGINGS);
        addCasting(registry, ModItems.STEEL, ModItems.BOOTS_CAST, ModItems.CAST_STEEL_BOOTS);

        // Meteoric Iron casting recipes
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.INGOT_CAST, ModItems.METEORIC_IRON_INGOT);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.PICKAXE_HEAD_CAST, ModItems.MI_PICKAXE_HEAD);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.AXE_HEAD_CAST, ModItems.MI_AXE_HEAD);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.SHOVEL_HEAD_CAST, ModItems.MI_SHOVEL_HEAD);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.SWORD_HEAD_CAST, ModItems.MI_SWORD_HEAD);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.HOE_HEAD_CAST, ModItems.MI_HOE_HEAD);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.SPEAR_HEAD_CAST, ModItems.MI_SPEAR_HEAD);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.HELMET_CAST, ModItems.METEORIC_IRON_HELMET);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.CHESTPLATE_CAST, ModItems.METEORIC_IRON_CHESTPLATE);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.LEGGINGS_CAST, ModItems.METEORIC_IRON_LEGGINGS);
        addCasting(registry, ModItems.METEORIC_IRON_INGOT, ModItems.BOOTS_CAST, ModItems.METEORIC_IRON_BOOTS);
    }

    private void addCasting(ServerDisplayRegistry registry, Item metal, Item cast, Item result) {
        registry.add(new FoundryCastingDisplay(
                List.of(EntryIngredient.of(EntryStacks.of(metal)), EntryIngredient.of(EntryStacks.of(cast))),
                List.of(EntryIngredient.of(EntryStacks.of(result))),
                Optional.empty()));
    }

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(Identifier.of(Houseki.MOD_ID, "crusher"), CrusherDisplay.SERIALIZER);
        registry.register(Identifier.of(Houseki.MOD_ID, "foundry_melting"), FoundryMeltingDisplay.SERIALIZER);
        registry.register(Identifier.of(Houseki.MOD_ID, "foundry_casting"), FoundryCastingDisplay.SERIALIZER);
    }
}
