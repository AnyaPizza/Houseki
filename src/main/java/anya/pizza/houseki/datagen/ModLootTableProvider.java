package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModItems;
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
        //Block Drops itself
        addDrop(ModBlocks.BLOCK_OF_PINKU);
        addDrop(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
        addDrop(ModBlocks.PINKU_ORE);
        addDrop(ModBlocks.CRUSHER);
        addDrop(ModBlocks.BLOCK_OF_TUNGSTEN_B);
        addDrop(ModBlocks.BLOCK_OF_ALUMINUM);
        addDrop(ModBlocks.ALUMINUM_TRAPDOOR);
        addDrop(ModBlocks.ALUMINUM_DOOR, doorDrops(ModBlocks.ALUMINUM_DOOR));
        addDrop(ModBlocks.BLOCK_OF_SAPPHIRE);
        addDrop(ModBlocks.BLOCK_OF_JADEITE);
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
        addDrop(ModBlocks.BLOCK_OF_CAST_STEEL_B);
        addDrop(ModBlocks.BAUXITE);
        addDrop(ModBlocks.PLATINUM_ORE);
        addDrop(ModBlocks.DEEPSLATE_PLATINUM_ORE);

        //Block drops other stuff.
        addDrop(ModBlocks.WOLFRAMITE_ORE, LightOreDrops(ModBlocks.WOLFRAMITE_ORE, ModItems.WOLFRAMITE));

        addDrop(ModBlocks.RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));
        addDrop(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));
        addDrop(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));

        addDrop(ModBlocks.SCHEELITE_ORE, AverageOreDrops(ModBlocks.SCHEELITE_ORE, ModItems.SCHEELITE));

        addDropWithSilkTouch(ModBlocks.ALUMINUM_GLASS);
        addDropWithSilkTouch(ModBlocks.ALUMINUM_GLASS_PANE);

        addDrop(ModBlocks.SAPPHIRE_ORE, SingleItemOreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.SAPPHIRE));
        addDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, SingleItemOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.SAPPHIRE));

        addDrop(ModBlocks.NEPHRITE_ORE, LightOreDrops(ModBlocks.NEPHRITE_ORE, ModItems.NEPHRITE));
        addDrop(ModBlocks.JADEITE_ORE, LightOreDrops(ModBlocks.JADEITE_ORE, ModItems.JADEITE));

        addDrop(ModBlocks.SULFUR_ORE, AverageOreDrops(ModBlocks.SULFUR_ORE, ModItems.SULFUR));
        addDrop(ModBlocks.BLACKSTONE_SULFUR_ORE, AverageOreDrops(ModBlocks.BLACKSTONE_SULFUR_ORE, ModItems.SULFUR));
    }

    public LootTable.Builder AverageOreDrops(Block drop, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 5))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder SingleItemOreDrops(Block drop, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder LightOreDrops(Block drop, Item item) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}