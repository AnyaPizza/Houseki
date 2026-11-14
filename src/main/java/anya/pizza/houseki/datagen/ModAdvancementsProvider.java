package anya.pizza.houseki.datagen;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public void generateAdvancement(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
        var items = registries.lookupOrThrow(Registries.ITEM);
        var blocks = registries.lookupOrThrow(Registries.BLOCK);

        //GENERAL ROOT

        AdvancementHolder generalRoot = Advancement.Builder.advancement()
                .rootDisplay(
                        ModBlocks.RAINBOW_PYRITE_ORE.asItem(),
                        Component.translatable("advancements.general.root.title"),
                        Component.translatable("advancements.general.root.description"),
                        Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "gui/advancements/backgrounds/chiseled_slate"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("has_crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, Items.CRAFTING_TABLE)))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "general/root"));

        AdvancementHolder crusherCrafting = Advancement.Builder.advancement()
                .parent(generalRoot)
                .display(
                        ModBlocks.CRUSHER.asItem(),
                        Component.translatable("advancements.houseki.crusher_crafting.title"),
                        Component.translatable("advancements.houseki.crusher_crafting.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("crusher_crafted", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModBlocks.CRUSHER.asItem())))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "general/crusher_crafting"));

        AdvancementHolder drill = Advancement.Builder.advancement()
                .parent(generalRoot)
                .display(
                        ModItems.PREMIUM_TUNGSTEN_DRILL,
                        Component.translatable("advancements.houseki.drill.title"),
                        Component.translatable("advancements.houseki.drill.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("simple_t_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SIMPLE_TUNGSTEN_DRILL)))
                .addCriterion("enhanced_t_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ENHANCED_TUNGSTEN_DRILL)))
                .addCriterion("advanced_t_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ADVANCED_TUNGSTEN_DRILL)))
                .addCriterion("premium_t_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PREMIUM_TUNGSTEN_DRILL)))
                .addCriterion("simple_d_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SIMPLE_TUNGSTEN_DRILL)))
                .addCriterion("enhanced_d_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ENHANCED_TUNGSTEN_DRILL)))
                .addCriterion("advanced_d_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ADVANCED_TUNGSTEN_DRILL)))
                .addCriterion("premium_d_drill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PREMIUM_TUNGSTEN_DRILL)))
                .rewards(AdvancementRewards.Builder.experience(70))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "general/obtain_drill"));

        ResourceKey<Structure> structureKey = ResourceKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "meteorite"));
        AdvancementHolder foundMeteorite = Advancement.Builder.advancement()
                .parent(generalRoot)
                .display(
                        ModBlocks.METEORIC_IRON.asItem(),
                        Component.translatable("advancements.houseki.meteorite.title"),
                        Component.translatable("advancements.houseki.meteorite.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("found_meteorite", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(registries.lookupOrThrow(Registries.STRUCTURE).getOrThrow(structureKey))))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "general/found_meteorite"));

        AdvancementHolder foundryCrafting = Advancement.Builder.advancement()
                .parent(generalRoot)
                .display(
                        ModBlocks.FOUNDRY.asItem(),
                        Component.translatable("advancements.houseki.foundry_crafting.title"),
                        Component.translatable("advancements.houseki.foundry_crafting.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("foundry_crafted", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModBlocks.FOUNDRY.asItem())))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "general/foundry_crafting"));

        AdvancementHolder obtainPinku = Advancement.Builder.advancement()
                .parent(generalRoot)
                .display(
                        ModItems.PINKU,
                        Component.translatable("advancements.houseki.pinku.title"),
                        Component.translatable("advancements.houseki.pinku.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("obtainPinku", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PINKU)))
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "general/obtain_pinku"));
    }
}

