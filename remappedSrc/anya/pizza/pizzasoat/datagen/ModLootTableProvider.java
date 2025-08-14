package anya.pizza.pizzasoat.datagen;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //Block Drops Itself
        addDrop(ModBlocks.BLOCK_OF_PINKU);
        addDrop(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
        addDrop(ModBlocks.PINKU_ORE);
        addDrop(ModBlocks.CRUSHER);
        addDrop(ModBlocks.BLOCK_OF_TUNGSTEN);
        addDrop(ModBlocks.BLOCK_OF_ALUMINUM);
        addDrop(ModBlocks.ALUMINUM_TRAPDOOR);
        addDrop(ModBlocks.ALUMINUM_DOOR, doorDrops(ModBlocks.ALUMINUM_DOOR));
        addDrop(ModBlocks.BLOCK_OF_SAPPHIRE);
        addDrop(ModBlocks.BLOCK_OF_JADE);
        addDrop(ModBlocks.BLOCK_OF_PLATINUM);
        addDrop(ModBlocks.LIMESTONE);
        addDrop(ModBlocks.POLISHED_LIMESTONE);
        addDrop(ModBlocks.CHISELED_LIMESTONE);
        addDrop(ModBlocks.LIMESTONE_BRICKS);
        addDrop(ModBlocks.LIMESTONE_BRICK_STAIRS);
        addDrop(ModBlocks.LIMESTONE_STAIRS);
        addDrop(ModBlocks.POLISHED_LIMESTONE_STAIRS);
        addDrop(ModBlocks.POLISHED_LIMESTONE_WALL);
        addDrop(ModBlocks.LIMESTONE_WALL);
        addDrop(ModBlocks.LIMESTONE_BRICK_WALL);
        addDrop(ModBlocks.LIMESTONE_SLAB, slabDrops(ModBlocks.LIMESTONE_SLAB));
        addDrop(ModBlocks.POLISHED_LIMESTONE_SLAB, slabDrops(ModBlocks.POLISHED_LIMESTONE_SLAB));
        addDrop(ModBlocks.LIMESTONE_BRICK_SLAB, slabDrops(ModBlocks.LIMESTONE_BRICK_SLAB));
        addDrop(ModBlocks.SLATE);
        addDrop(ModBlocks.POLISHED_SLATE);
        addDrop(ModBlocks.CHISELED_SLATE);
        addDrop(ModBlocks.SLATE_TILES);
        addDrop(ModBlocks.SLATE_TILE_STAIRS);
        addDrop(ModBlocks.SLATE_STAIRS);
        addDrop(ModBlocks.POLISHED_SLATE_STAIRS);
        addDrop(ModBlocks.POLISHED_SLATE_WALL);
        addDrop(ModBlocks.SLATE_WALL);
        addDrop(ModBlocks.SLATE_TILE_WALL);
        addDrop(ModBlocks.SLATE_SLAB, slabDrops(ModBlocks.SLATE_SLAB));
        addDrop(ModBlocks.POLISHED_SLATE_SLAB, slabDrops(ModBlocks.POLISHED_SLATE_SLAB));
        addDrop(ModBlocks.SLATE_TILE_SLAB, slabDrops(ModBlocks.SLATE_TILE_SLAB));
        addDrop(ModBlocks.BLOCK_OF_SULFUR);
        addDrop(ModBlocks.BLOCK_OF_STEEL);
        addDrop(ModBlocks.BLOCK_OF_CAST_STEEL);

        //Block drops other stuff.
        addDrop(ModBlocks.WOLFRAMITE_ORE, LightOreDrops(ModBlocks.WOLFRAMITE_ORE, ModItems.WOLFRAMITE));

        addDrop(ModBlocks.RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));
        addDrop(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));

        addDrop(ModBlocks.SCHEELITE_ORE, AverageOreDrops(ModBlocks.SCHEELITE_ORE, ModItems.SCHEELITE));

        //addDrop(ModBlocks.TRONA_ORE, AverageOreDrops(ModBlocks.TRONA_ORE, ModItems.TRONA));

        addDrop(ModBlocks.BAUXITE_ORE, AverageOreDrops(ModBlocks.BAUXITE_ORE, ModItems.BAUXITE));
        addDrop(ModBlocks.DEEPSLATE_BAUXITE_ORE, AverageOreDrops(ModBlocks.BAUXITE_ORE, ModItems.BAUXITE));

        addDropWithSilkTouch(ModBlocks.ALUMINUM_GLASS);

        addDrop(ModBlocks.SAPPHIRE_ORE, SingleItemOreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.SAPPHIRE));
        addDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, SingleItemOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.SAPPHIRE));

        addDrop(ModBlocks.NEPHRITE_ORE, LightOreDrops(ModBlocks.NEPHRITE_ORE, ModItems.NEPHRITE));
        addDrop(ModBlocks.DEEPSLATE_NEPHRITE_ORE, LightOreDrops(ModBlocks.DEEPSLATE_NEPHRITE_ORE, ModItems.NEPHRITE));

        addDrop(ModBlocks.PLATINUM_ORE, SingleItemOreDrops(ModBlocks.PLATINUM_ORE, ModItems.PLATINUM));
        addDrop(ModBlocks.DEEPSLATE_PLATINUM_ORE, SingleItemOreDrops(ModBlocks.DEEPSLATE_PLATINUM_ORE, ModItems.PLATINUM));

        addDrop(ModBlocks.SULFUR_ORE, AverageOreDrops(ModBlocks.SULFUR_ORE, ModItems.SULFUR));
        addDrop(ModBlocks.BLACKSTONE_SULFUR_ORE, AverageOreDrops(ModBlocks.BLACKSTONE_SULFUR_ORE, ModItems.SULFUR));
    }

    public LootTable.Builder AverageOreDrops(Block drop, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 5))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder SingleItemOreDrops(Block drop, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder LightOreDrops(Block drop, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
