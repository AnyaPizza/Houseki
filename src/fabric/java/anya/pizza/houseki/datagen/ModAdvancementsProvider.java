package anya.pizza.houseki.datagen;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.util.ModTags;
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
import net.minecraft.tags.TagKey;
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

        // ARMOR SETS ROOT

        AdvancementHolder armorSets = Advancement.Builder.advancement()
                .rootDisplay(
                        ModItems.PINKU_CHESTPLATE,
                        Component.translatable("advancements.armor_sets.root.title"),
                        Component.translatable("advancements.armor_sets.root.description"),
                        Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "gui/advancements/backgrounds/rainbow_pyrite"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("obtain_houseki_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModTags.Items.ARMOR)))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/root"));

        AdvancementHolder aluminumArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.ALUMINUM_CHESTPLATE,
                        Component.translatable("advancements.houseki.aluminum_armor_set.title"),
                        Component.translatable("advancements.houseki.aluminum_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("aluminum_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ALUMINUM_HELMET)))
                .addCriterion("aluminum_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ALUMINUM_CHESTPLATE)))
                .addCriterion("aluminum_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ALUMINUM_LEGGINGS)))
                .addCriterion("aluminum_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.ALUMINUM_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/aluminum_armor"));

        AdvancementHolder castSteelArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.CAST_STEEL_CHESTPLATE,
                        Component.translatable("advancements.houseki.cast_steel_armor_set.title"),
                        Component.translatable("advancements.houseki.cast_steel_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("cast_steel_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.CAST_STEEL_HELMET)))
                .addCriterion("cast_steel_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.CAST_STEEL_CHESTPLATE)))
                .addCriterion("cast_steel_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.CAST_STEEL_LEGGINGS)))
                .addCriterion("cast_steel_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.CAST_STEEL_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(70))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/cast_steel_armor"));

        AdvancementHolder jadeiteArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.JADEITE_CHESTPLATE,
                        Component.translatable("advancements.houseki.jadeite_armor_set.title"),
                        Component.translatable("advancements.houseki.jadeite_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("jadeite_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.JADEITE_HELMET)))
                .addCriterion("jadeite_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.JADEITE_CHESTPLATE)))
                .addCriterion("jadeite_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.JADEITE_LEGGINGS)))
                .addCriterion("jadeite_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.JADEITE_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/jadeite_armor"));

        AdvancementHolder meteoricIronArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.METEORIC_IRON_CHESTPLATE,
                        Component.translatable("advancements.houseki.meteoric_iron_armor_set.title"),
                        Component.translatable("advancements.houseki.meteoric_iron_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("meteoric_iron_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.METEORIC_IRON_HELMET)))
                .addCriterion("meteoric_iron_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.METEORIC_IRON_CHESTPLATE)))
                .addCriterion("meteoric_iron_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.METEORIC_IRON_LEGGINGS)))
                .addCriterion("meteoric_iron_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.METEORIC_IRON_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(80))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/meteoric_iron_armor"));

        AdvancementHolder nephriteArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.NEPHRITE_CHESTPLATE,
                        Component.translatable("advancements.houseki.nephrite_armor_set.title"),
                        Component.translatable("advancements.houseki.nephrite_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("nephrite_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.NEPHRITE_HELMET)))
                .addCriterion("nephrite_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.NEPHRITE_CHESTPLATE)))
                .addCriterion("nephrite_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.NEPHRITE_LEGGINGS)))
                .addCriterion("nephrite_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.NEPHRITE_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/nephrite_armor"));

        AdvancementHolder pinkuArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.PINKU_CHESTPLATE,
                        Component.translatable("advancements.houseki.pinku_armor_set.title"),
                        Component.translatable("advancements.houseki.pinku_armor_set.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("pinku_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PINKU_HELMET)))
                .addCriterion("pinku_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PINKU_CHESTPLATE)))
                .addCriterion("pinku_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PINKU_LEGGINGS)))
                .addCriterion("pinku_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PINKU_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/pinku_armor"));

        AdvancementHolder platinumArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.PLATINUM_CHESTPLATE,
                        Component.translatable("advancements.houseki.platinum_armor_set.title"),
                        Component.translatable("advancements.houseki.platinum_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("platinum_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PLATINUM_HELMET)))
                .addCriterion("platinum_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PLATINUM_CHESTPLATE)))
                .addCriterion("platinum_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PLATINUM_LEGGINGS)))
                .addCriterion("platinum_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.PLATINUM_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/platinum_armor"));

        AdvancementHolder rainbowPyriteArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.RAINBOW_PYRITE_CHESTPLATE,
                        Component.translatable("advancements.houseki.rainbow_pyrite_armor_set.title"),
                        Component.translatable("advancements.houseki.rainbow_pyrite_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("rainbow_pyrite_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.RAINBOW_PYRITE_HELMET)))
                .addCriterion("rainbow_pyrite_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.RAINBOW_PYRITE_CHESTPLATE)))
                .addCriterion("rainbow_pyrite_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.RAINBOW_PYRITE_LEGGINGS)))
                .addCriterion("rainbow_pyrite_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.RAINBOW_PYRITE_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/rainbow_pyrite_armor"));

        AdvancementHolder sapphireArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.SAPPHIRE_CHESTPLATE,
                        Component.translatable("advancements.houseki.sapphire_armor_set.title"),
                        Component.translatable("advancements.houseki.sapphire_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("sapphire_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SAPPHIRE_HELMET)))
                .addCriterion("sapphire_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SAPPHIRE_CHESTPLATE)))
                .addCriterion("sapphire_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SAPPHIRE_LEGGINGS)))
                .addCriterion("sapphire_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SAPPHIRE_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(80))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/sapphire_armor"));

        AdvancementHolder tungstenArmor = Advancement.Builder.advancement()
                .parent(armorSets)
                .display(
                        ModItems.TUNGSTEN_CHESTPLATE,
                        Component.translatable("advancements.houseki.tungsten_armor_set.title"),
                        Component.translatable("advancements.houseki.tungsten_armor_set.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("tungsten_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.TUNGSTEN_HELMET)))
                .addCriterion("tungsten_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.TUNGSTEN_CHESTPLATE)))
                .addCriterion("tungsten_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.TUNGSTEN_LEGGINGS)))
                .addCriterion("tungsten_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.TUNGSTEN_BOOTS)))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(output, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "armor_sets/tungsten_armor"));
    }
}

