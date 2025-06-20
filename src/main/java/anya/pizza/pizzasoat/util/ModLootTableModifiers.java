package anya.pizza.pizzasoat.util;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier ABANDONED_MINESHAFT_ID =
            Identifier.of("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier ANCIENT_CITY_ID =
            Identifier.of("minecraft", "chests/ancient_city");
    private static final Identifier BASTION_TREASURE_ID =
            Identifier.of("minecraft", "chests/bastion_treasure");
    private static final Identifier BURIED_TREASURE_ID =
            Identifier.of("minecraft", "chest/buried_treasure");
    private static final Identifier DESERT_PYRAMID_ID =
            Identifier.of("minecraft", "chests/desert/pyramid");
    private static final Identifier END_CITY_TREASURE_ID =
            Identifier.of("minecraft", "chests/end_city_treasure");
    private static final Identifier JUNGLE_TEMPLE_ID =
            Identifier.of("minecraft", "chests/jungle_temple");
    private static final Identifier PILLAGER_OUTPOST_ID =
            Identifier.of("minecraft", "chests/pillager_outpost");
    private static final Identifier SHIPWRECK_TREASURE_ID =
            Identifier.of("minecraft", "chests/shipwreck_treasure");
    private static final Identifier STRONGHOLD_CORRIDOR_ID =
            Identifier.of("minecraft", "chests/stronghold_corridor");
    private static final Identifier TRIAL_REWARD_COMMON_ID =
            Identifier.of("minecraft", "chests/trial_chambers/reward_common");
    private static final Identifier TRIAL_REWARD_OMINOUS_COMMON_ID =
            Identifier.of("minecraft", "chests/trial_chambers/reward_ominous_common");
    private static final Identifier TRIAL_REWARD_RARE_ID =
            Identifier.of("minecraft", "chests/trial_chambers/reward_rare");
    private static final Identifier TRIAL_REWARD_OMINOUS_RARE_ID =
            Identifier.of("minecraft", "chests/trial_chambers/reward_ominous_rare");
    private static final Identifier TRIAL_REWARD_UNIQUE_ID =
            Identifier.of("minecraft", "chests/trial_chambers/reward_unique");
    private static final Identifier TRIAL_REWARD_OMINOUS_UNIQUE_ID =
            Identifier.of("minecraft", "chests/trial_chambers/reward_unique");
    private static final Identifier VILLAGE_TOOLSMITH_ID =
            Identifier.of("minecraft", "chests/village/village_toolsmith");
    private static final Identifier VILLAGE_WEAPONSMITH_ID =
            Identifier.of("minecraft", "chests/village/village_weaponsmith");
    private static final Identifier WOODLAND_MANSION_ID =
            Identifier.of("minecraft", "chests/woodland_mansion");
    private static final Identifier WARDEN_ID =
            Identifier.of("minecraft", "entities/warden");



    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, sources, registry) -> {
            if (ABANDONED_MINESHAFT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 4f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SULFUR).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 8.0f))))

                        .with(ItemEntry.builder(Items.AIR).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_PICKAXE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_PICKAXE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 5f))
                        //.conditionally(RandomChanceLootCondition.builder(0.2f)) //1 = 100% chance item will be in loot table
                        //.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1f, 0.9f))) // 10%-90% Durability
                        //.apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                        .with(ItemEntry.builder(Items.AIR).weight(75)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SULFUR).weight(7)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0f, 10.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_LEGGINGS).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_LEGGINGS).weight(2)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HOE).weight(2)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_LEGGINGS).weight(2)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HOE).weight(2)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_LEGGINGS).weight(2)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_HOE).weight(2)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HORSE_ARMOR).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HORSE_ARMOR).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HORSE_ARMOR).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_HORSE_ARMOR).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .with(ItemEntry.builder(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (BASTION_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (BASTION_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 4f))
                        .with(ItemEntry.builder(Items.AIR).weight(11)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_SWORD).weight(6)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_SWORD).weight(6)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_SWORD).weight(6)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_SWORD).weight(6)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(30.0F, 50.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HELMET).weight(5)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HELMET).weight(5)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_CHESTPLATE).weight(5)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HELMET).weight(5)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_HELMET).weight(5)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8f, 1.0f)))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModBlocks.BLOCK_OF_SULFUR).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.SULFUR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0f, 10.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))));

                tableBuilder.pool(poolBuilder.build());

            }

            if (BURIED_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1f, 4f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(20)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(20)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_SWORD).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_SWORD).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_SHARD).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (DESERT_PYRAMID_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 4f))
                        .with(ItemEntry.builder(Items.AIR).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(14)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModBlocks.CHISELED_LIMESTONE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HORSE_ARMOR).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HORSE_ARMOR).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (END_CITY_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (END_CITY_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 5f))
                        .with(ItemEntry.builder(Items.AIR).weight(14)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_PICKAXE).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_SHOVEL).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_BOOTS).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_PICKAXE).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_SHOVEL).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_BOOTS).weight(3)
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20.0F, 39.0F)))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (JUNGLE_TEMPLE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 6f))
                        .with(ItemEntry.builder(Items.AIR).weight(30)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModBlocks.CHISELED_LIMESTONE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (PILLAGER_OUTPOST_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1f, 3f))
                        .with(ItemEntry.builder(Items.AIR).weight(30)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (SHIPWRECK_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2f, 5f))
                        .with(ItemEntry.builder(Items.AIR).weight(90)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(90)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(90)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(90)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(70)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CORRIDOR_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1f, 4f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SULFUR).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 8.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_PICKAXE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_PICKAXE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_COMMON_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_OMINOUS_COMMON_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.PLATINUM).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_RARE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_AXE).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(0.0F, 10.0F)))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_CHESTPLATE).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(0.0F, 10.0F)))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_CHESTPLATE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(5.0F, 15.0F)))

                        .with(ItemEntry.builder(ModItems.PLATINUM_AXE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(5.0F, 15.0F)))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_OMINOUS_RARE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModBlocks.BLOCK_OF_RAINBOW_PYRITE).weight(4)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_AXE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(10.0F, 20.0F)))

                        .with(ItemEntry.builder(ModItems.PLATINUM_CHESTPLATE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(10.0F, 20.0F)))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_CHESTPLATE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(10.0F, 20.0F)))

                        .with(ItemEntry.builder(ModItems.PLATINUM_AXE).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                                .apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(10.0F, 20.0F)))

                        .with(ItemEntry.builder(ModBlocks.BLOCK_OF_SAPPHIRE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_UNIQUE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.AIR).weight(30)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_SHARD).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (TRIAL_REWARD_OMINOUS_UNIQUE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.AIR).weight(25)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PINKU_SHARD).weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_TOOLSMITH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3f, 8f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_PICKAXE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_SHOVEL).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_SHOVEL).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_PICKAXE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.SULFUR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_WEAPONSMITH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3f, 8f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_PICKAXE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_HELMET).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_BOOTS).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.NEPHRITE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_SWORD).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_SWORD).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_CHESTPLATE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_LEGGINGS).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_PICKAXE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN).weight(3)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HORSE_ARMOR).weight(1)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());
            }

            if (WOODLAND_MANSION_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1f, 3f))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HOE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_HOE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HOE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_HOE).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.SULFUR).weight(15)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(Items.AIR).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))))

                        .with(ItemEntry.builder(ModItems.WOLFRAMITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.SCHEELITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.BAUXITE).weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))

                        .with(ItemEntry.builder(ModItems.TUNGSTEN_CHESTPLATE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.ALUMINUM_CHESTPLATE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))

                        .with(ItemEntry.builder(ModItems.PLATINUM_CHESTPLATE).weight(5)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));

                tableBuilder.pool(poolBuilder.build());

            }

            if (WOODLAND_MANSION_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.4f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .with(ItemEntry.builder(ModItems.PINKU).conditionally(RandomChanceLootCondition.builder(0.1f)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

        });
    }
}
