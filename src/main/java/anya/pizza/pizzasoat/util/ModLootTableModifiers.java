package anya.pizza.pizzasoat.util;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier ANCIENT_CITY_ID =
            Identifier.of("minecraft", "chests/ancient_city");
    private static final Identifier BASTION_TREASURE_ID =
            Identifier.of("minecraft", "chests/bastion_treasure");
    private static final Identifier END_CITY_TREASURE_ID =
            Identifier.of("minecraft", "chests/end_city_treasure");
    private static final Identifier WOODLAND_MANSION_ID =
            Identifier.of("minecraft", "chests/woodland_mansion");
    private static final Identifier WARDEN_ID =
            Identifier.of("minecraft", "entities/warden");
    private static final Identifier TREASURE_ID =
            Identifier.of("minecraft", "gameplay/fishing/treasure");
    private static final Identifier VILLAGE_TOOLSMITH_ID =
            Identifier.of("minecraft", "chests/village/village_toolsmith");
    private static final Identifier VILLAGE_WEAPONSMITH_ID =
            Identifier.of("minecraft", "chests/village/village_weaponsmith");
    private static final Identifier ABANDONED_MINECRAFT_ID =
            Identifier.of("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier JUNGLE_TEMPLE_ID =
            Identifier.of("minecraft", "chests/jungle_temple");
    private static final Identifier SHIPWRECK_TREASURE_ID =
            Identifier.of("minecraft", "chests/shipwreck_treasure");
    private static final Identifier STRONGHOLD_CORRIDOR_ID =
            Identifier.of("minecraft", "chests/stronghold_corridor");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, sources, registry) -> {
            if (ANCIENT_CITY_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.2f)) //1 = 100% chance item will be in loot table
                        .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.1f, 0.9f))) // 10%-90% Durability
                        .apply(EnchantRandomlyLootFunction.create())
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_LEGGINGS))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HOE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_LEGGINGS))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HOE))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE))
                        .with(ItemEntry.builder(ModItems.PLATINUM_LEGGINGS))
                        .with(ItemEntry.builder(ModItems.PLATINUM_HOE))
                        .with(ItemEntry.builder(ModItems.SULFUR))
                        .with(ItemEntry.builder(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.PINKU_HORSE_ARMOR))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HORSE_ARMOR))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_HORSE_ARMOR))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HORSE_ARMOR))
                        .with(ItemEntry.builder(ModItems.PLATINUM_HORSE_ARMOR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Sets the min and max amounts of the items

                tableBuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.01f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (BASTION_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.01f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (END_CITY_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.01f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }

            if (WOODLAND_MANSION_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.01f))
                        .with(ItemEntry.builder(ModItems.PINKU_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }


            if (BASTION_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HELMET))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_PICKAXE))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_AXE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_HELMET))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_PICKAXE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_AXE))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HELMET))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE_PICKAXE))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE))
                        .with(ItemEntry.builder(ModItems.NEPHRITE))
                        .with(ItemEntry.builder(ModItems.PLATINUM_HELMET))
                        .with(ItemEntry.builder(ModItems.PLATINUM_AXE))
                        .with(ItemEntry.builder(ModItems.PLATINUM))
                        .with(ItemEntry.builder(ModItems.SULFUR))
                        .with(ItemEntry.builder(ModBlocks.BLOCK_OF_SULFUR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());

            }

            if (END_CITY_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE_HOE))
                        .with(ItemEntry.builder(ModItems.NEPHRITE))
                        .with(ItemEntry.builder(ModItems.PLATINUM_HOE))
                        .with(ItemEntry.builder(ModItems.PLATINUM))
                        .with(ItemEntry.builder(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (WOODLAND_MANSION_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HELMET))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_CHESTPLATE))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_LEGGINGS))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_BOOTS))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_AXE))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_SHOVEL))
                        .with(ItemEntry.builder(ModItems.TUNGSTEN_HOE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_HELMET))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_CHESTPLATE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_LEGGINGS))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_BOOTS))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_AXE))
                        .with(ItemEntry.builder(ModItems.ALUMINUM_HOE))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE_AXE))
                        .with(ItemEntry.builder(ModItems.SAPPHIRE))
                        .with(ItemEntry.builder(ModItems.PLATINUM_HELMET))
                        .with(ItemEntry.builder(ModItems.PLATINUM))
                        .with(ItemEntry.builder(ModItems.PLATINUM_AXE))
                        .with(ItemEntry.builder(ModItems.SULFUR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());

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

            if (TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.NEPHRITE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_TOOLSMITH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_AXE))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_PICKAXE))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_SHOVEL))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_HOE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_WEAPONSMITH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (ABANDONED_MINECRAFT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.WOLFRAMITE))
                        .with(ItemEntry.builder(ModItems.SCHEELITE))
                        .with(ItemEntry.builder(ModItems.BAUXITE))
                        .with(ItemEntry.builder(ModBlocks.LIMESTONE))
                        .with(ItemEntry.builder(ModItems.SULFUR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (JUNGLE_TEMPLE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.WOLFRAMITE))
                        .with(ItemEntry.builder(ModItems.SCHEELITE))
                        .with(ItemEntry.builder(ModItems.BAUXITE))
                        .with(ItemEntry.builder(ModItems.NEPHRITE))
                        .with(ItemEntry.builder(ModBlocks.CHISELED_LIMESTONE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (SHIPWRECK_TREASURE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.WOLFRAMITE))
                        .with(ItemEntry.builder(ModItems.SCHEELITE))
                        .with(ItemEntry.builder(ModItems.NEPHRITE))
                        .with(ItemEntry.builder(ModBlocks.LIMESTONE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CORRIDOR_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RAINBOW_PYRITE))
                        .with(ItemEntry.builder(ModItems.WOLFRAMITE))
                        .with(ItemEntry.builder(ModItems.SCHEELITE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
