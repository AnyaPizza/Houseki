package anya.pizza.houseki.util;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.item.ModToolMaterials;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModLootTableModifiers {
    private static final Identifier ABANDONED_MINESHAFT_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier ANCIENT_CITY_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/ancient_city");
    private static final Identifier BASTION_TREASURE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/bastion_treasure");
    private static final Identifier BURIED_TREASURE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/buried_treasure");
    private static final Identifier CAMEL_HUSK_ID = Identifier.fromNamespaceAndPath("minecraft", "entities/camel_husk");
    private static final Identifier DESERT_PYRAMID_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/desert/pyramid");
    private static final Identifier END_CITY_TREASURE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/end_city_treasure");
    private static final Identifier HUSK_ID = Identifier.fromNamespaceAndPath("minecraft", "entities/husk");
    private static final Identifier JUNGLE_TEMPLE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/jungle_temple");
    private static final Identifier OCEAN_RUINS_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/underwater_run_big");
    private static final Identifier PILLAGER_OUTPOST_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/pillager_outpost");
    private static final Identifier SHIPWRECK_TREASURE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/shipwreck_treasure");
    private static final Identifier STRONGHOLD_CORRIDOR_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor");
    private static final Identifier TRIAL_REWARD_COMMON_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_common");
    private static final Identifier TRIAL_REWARD_OMINOUS_COMMON_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_ominous_common");
    private static final Identifier TRIAL_REWARD_RARE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_rare");
    private static final Identifier TRIAL_REWARD_OMINOUS_RARE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_ominous_rare");
    private static final Identifier TRIAL_REWARD_UNIQUE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_unique");
    private static final Identifier TRIAL_REWARD_OMINOUS_UNIQUE_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_unique");
    private static final Identifier VILLAGE_MASON_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/village/village_mason");
    private static final Identifier VILLAGE_TOOLSMITH_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/village/village_toolsmith");
    private static final Identifier VILLAGE_WEAPONSMITH_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/village/village_weaponsmith");
    private static final Identifier WOODLAND_MANSION_ID = Identifier.fromNamespaceAndPath("minecraft", "chests/woodland_mansion");
    private static final Identifier WARDEN_ID = Identifier.fromNamespaceAndPath("minecraft", "entities/warden");
    private static final Identifier ZOMBIE_ID = Identifier.fromNamespaceAndPath("minecraft", "entities/zombie");
    private static final Identifier ZOMBIE_HORSE_ID = Identifier.fromNamespaceAndPath("minecraft", "entities/zombie_horse");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, sources, registry) -> {
            if (ABANDONED_MINESHAFT_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 4))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.SULFUR_POWDER).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 8))))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_PICKAXE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_PICKAXE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_POWDER).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CRUDE_IRON).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 5))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(75))
                        .add(LootItem.lootTableItem(ModItems.SULFUR_POWDER).setWeight(7)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(5, 10))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(3)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_LEGGINGS).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_LEGGINGS).setWeight(2)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HOE).setWeight(2)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_LEGGINGS).setWeight(2)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HOE).setWeight(2)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_LEGGINGS).setWeight(2)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_HOE).setWeight(2)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HORSE_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HORSE_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HORSE_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_HORSE_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.STEEL).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.NICKEL_POWDER).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CAST_STEEL).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.PINKU_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ENHANCED_DRILL_HEAD).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DIAMOND_DRILL_BIT).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.PICKAXE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.AXE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SHOVEL_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SWORD_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.HOE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.HELMET_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CHESTPLATE_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.LEGGINGS_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.BOOTS_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.INGOT_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.BISMUTH).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SUGILITE).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
                LootPool.Builder rarePoolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD))
                        .add(LootItem.lootTableItem(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE))
                        .add(LootItem.lootTableItem(ModItems.SIMPLE_DRILL_HEAD))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)).build());
                tableBuilder.withPool(rarePoolBuilder);
            }

            if (BASTION_TREASURE_ID.equals(key.identifier())) {
                LootPool.Builder rarePoolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)).build());
                tableBuilder.withPool(rarePoolBuilder);

                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 4))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(11))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_SWORD).setWeight(6)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_SWORD).setWeight(6)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SWORD).setWeight(6)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_SWORD).setWeight(6)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(30, 50))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HELMET).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HELMET).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_CHESTPLATE).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HELMET).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_HELMET).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE_HELMET).setWeight(5)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.CRUDE_IRON).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6))))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.JADEITE).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.SULFUR_POWDER).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(6, 10))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
                        .add(LootItem.lootTableItem(ModItems.NICKEL_POWDER).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_POWDER).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SUGILITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.BISMUTH).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ENHANCED_DRILL_HEAD).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DIAMOND_DRILL_BIT).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR).setWeight(1)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR).setWeight(1)
                                .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.8f, 1)))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (BURIED_TREASURE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(1, 4))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(20))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(20)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SWORD).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SWORD).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_NAUTILUS_ARMOR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_NAUTILUS_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_NAUTILUS_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(5))
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)));
                tableBuilder.pool(poolBuilder.build());
            }

            if (CAMEL_HUSK_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if (DESERT_PYRAMID_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 4))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(15)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(14)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModBlocks.CHISELED_LIMESTONE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HORSE_ARMOR).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HORSE_ARMOR).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.PICKAXE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.AXE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SHOVEL_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SWORD_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.HOE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.HELMET_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CHESTPLATE_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.LEGGINGS_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.BOOTS_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.INGOT_CAST).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (END_CITY_TREASURE_ID.equals(key.identifier())) {
                LootPool.Builder rarePoolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE))
                        .add(LootItem.lootTableItem(ModItems.SIMPLE_DRILL_HEAD))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)).build());
                tableBuilder.withPool(rarePoolBuilder);
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 5))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(14))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_NUGGET).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.JADEITE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_PICKAXE).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_SHOVEL).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_BOOTS).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_PICKAXE).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_SHOVEL).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_BOOTS).setWeight(3)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.SUGILITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.BISMUTH).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.PINKU_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.NICKEL_POWDER).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.STEEL).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CAST_STEEL).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ENHANCED_DRILL_HEAD).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DIAMOND_DRILL_BIT).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR).setWeight(1)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR).setWeight(1)
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(20, 39))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (HUSK_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if (JUNGLE_TEMPLE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 6))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(30))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModBlocks.CHISELED_LIMESTONE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (OCEAN_RUINS_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(1, 2))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_NAUTILUS_ARMOR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.CAST_STEEL_NAUTILUS_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_NAUTILUS_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_NAUTILUS_ARMOR).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (PILLAGER_OUTPOST_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(1, 3))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(30))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(3)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(2)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (SHIPWRECK_TREASURE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(2, 5))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(90))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(90))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(90))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(90)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(70)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_NUGGET).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE_NAUTILUS_ARMOR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_NAUTILUS_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.JADEITE_NAUTILUS_ARMOR).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CORRIDOR_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(1, 4))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.SULFUR_POWDER).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 8))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_PICKAXE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_PICKAXE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_COMMON_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(3)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_COMMON_ID.equals(key.identifier())) {
                HolderSet<Item> castTagSet = registry.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.CASTS);

                LootPool.Builder poolBuilder = LootPool.lootPool().add(TagEntry.expandTag(castTagSet).setWeight(1));

                tableBuilder.pool(poolBuilder.build());
                }

            if (TRIAL_REWARD_OMINOUS_COMMON_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_NUGGET).setWeight(2)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(2)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_OMINOUS_COMMON_ID.equals(key.identifier())) {
                HolderSet<Item> castTagSet = registry.lookupOrThrow(Registries.ITEM).getOrThrow(ModTags.Items.CASTS);

                LootPool.Builder poolBuilder = LootPool.lootPool().add(TagEntry.expandTag(castTagSet).setWeight(1));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_RARE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_AXE).setWeight(2))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(0, 10)))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_CHESTPLATE).setWeight(2))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(0, 10)))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_CHESTPLATE).setWeight(1))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(5, 15)))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_AXE).setWeight(1))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(5, 15)))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))));
                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_OMINOUS_RARE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(ModBlocks.BLOCK_OF_RAINBOW_PYRITE).setWeight(4))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_AXE).setWeight(3))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(10, 20)))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_CHESTPLATE).setWeight(3))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(10, 20)))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_CHESTPLATE).setWeight(3))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(10, 20)))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_AXE).setWeight(3))
                                .apply(new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(10, 20)))
                        .add(LootItem.lootTableItem(ModBlocks.BLOCK_OF_SAPPHIRE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DIAMOND_DRILL_BIT).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_UNIQUE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(30))
                        .add(LootItem.lootTableItem(ModItems.SUGILITE).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.BISMUTH).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.STEEL).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CAST_STEEL).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ENHANCED_DRILL_HEAD).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DIAMOND_DRILL_BIT).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_OMINOUS_UNIQUE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(25))
                        .add(LootItem.lootTableItem(ModItems.SUGILITE).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.BISMUTH).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.STEEL).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CAST_STEEL).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ENHANCED_DRILL_HEAD).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_MASON_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(1, 5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(1))
                        .add(LootItem.lootTableItem(ModBlocks.LIMESTONE).setWeight(2))
                        .add(LootItem.lootTableItem(ModBlocks.LIMESTONE_BRICKS).setWeight(2))
                        .add(LootItem.lootTableItem(ModBlocks.SLATE).setWeight(2))
                        .add(LootItem.lootTableItem(ModBlocks.SLATE_TILES).setWeight(2));
                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_TOOLSMITH_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(3, 8))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_PICKAXE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SHOVEL).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SHOVEL).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_PICKAXE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                        .add(LootItem.lootTableItem(ModItems.SULFUR_POWDER).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.PICKAXE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.AXE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SHOVEL_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.SWORD_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.HOE_HEAD_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.HELMET_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.CHESTPLATE_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.LEGGINGS_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.BOOTS_CAST).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.INGOT_CAST).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_WEAPONSMITH_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(3, 8))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_PICKAXE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_HELMET).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_BOOTS).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.NEPHRITE).setWeight(5)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SWORD).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SWORD).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_CHESTPLATE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_LEGGINGS).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_PICKAXE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR).setWeight(2)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_HORSE_ARMOR).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HORSE_ARMOR).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HORSE_ARMOR).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (WOODLAND_MANSION_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.between(1, 3))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_HOE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_HOE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.SAPPHIRE_HOE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_HOE).setWeight(15))
                        .add(LootItem.lootTableItem(ModItems.SULFUR_POWDER).setWeight(15))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4))))
                        .add(LootItem.lootTableItem(ModItems.WOLFRAMITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.SCHEELITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.JADEITE).setWeight(10))
                        .add(LootItem.lootTableItem(ModItems.CRUSHED_BAUXITE).setWeight(10)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3))))
                        .add(LootItem.lootTableItem(ModItems.TUNGSTEN_CHESTPLATE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_CHESTPLATE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.PLATINUM_CHESTPLATE).setWeight(5))
                        .add(LootItem.lootTableItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .add(LootItem.lootTableItem(ModItems.DIAMOND_DRILL_BIT).setWeight(1));
                tableBuilder.pool(poolBuilder.build());
            }

            if (WOODLAND_MANSION_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)).build());
                tableBuilder.withPool(poolBuilder);
            }

            if (WARDEN_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.4f))
                        .add(LootItem.lootTableItem(ModBlocks.METEORIC_IRON))
                        .add(LootItem.lootTableItem(ModItems.PINKU_SHARD))
                        .add(LootItem.lootTableItem(ModItems.PINKU).when(LootItemRandomChanceCondition.randomChance(0.1f)))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if (ZOMBIE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if (ZOMBIE_HORSE_ID.equals(key.identifier())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.5f))
                        .add(LootItem.lootTableItem(ModItems.RAINBOW_PYRITE_SPEAR))
                        .add(LootItem.lootTableItem(ModItems.ALUMINUM_SPEAR))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}