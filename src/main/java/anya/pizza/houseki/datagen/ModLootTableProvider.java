package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
    public ModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //Block Drops Itself
        dropSelf(ModBlocks.BLOCK_OF_PINKU);
        dropSelf(ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
        dropSelf(ModBlocks.PINKU_ORE);
        dropSelf(ModBlocks.CRUSHER);
        dropSelf(ModBlocks.BLOCK_OF_TUNGSTEN_B);
        dropSelf(ModBlocks.BLOCK_OF_ALUMINUM);
        dropSelf(ModBlocks.ALUMINUM_TRAPDOOR);
        add(ModBlocks.ALUMINUM_DOOR, createDoorTable(ModBlocks.ALUMINUM_DOOR));
        dropSelf(ModBlocks.BLOCK_OF_SAPPHIRE);
        dropSelf(ModBlocks.BLOCK_OF_JADEITE);
        dropSelf(ModBlocks.BLOCK_OF_PLATINUM);
        dropSelf(ModBlocks.LIMESTONE);
        dropSelf(ModBlocks.POLISHED_LIMESTONE);
        dropSelf(ModBlocks.CHISELED_LIMESTONE);
        dropSelf(ModBlocks.LIMESTONE_BRICKS);
        dropSelf(ModBlocks.LIMESTONE_BRICK_STAIRS);
        dropSelf(ModBlocks.LIMESTONE_STAIRS);
        dropSelf(ModBlocks.POLISHED_LIMESTONE_STAIRS);
        dropSelf(ModBlocks.POLISHED_LIMESTONE_WALL);
        dropSelf(ModBlocks.LIMESTONE_WALL);
        dropSelf(ModBlocks.LIMESTONE_BRICK_WALL);
        add(ModBlocks.LIMESTONE_SLAB, createSlabItemTable(ModBlocks.LIMESTONE_SLAB));
        add(ModBlocks.POLISHED_LIMESTONE_SLAB, createSlabItemTable(ModBlocks.POLISHED_LIMESTONE_SLAB));
        add(ModBlocks.LIMESTONE_BRICK_SLAB, createSlabItemTable(ModBlocks.LIMESTONE_BRICK_SLAB));
        dropSelf(ModBlocks.SLATE);
        dropSelf(ModBlocks.POLISHED_SLATE);
        dropSelf(ModBlocks.CHISELED_SLATE);
        dropSelf(ModBlocks.SLATE_TILES);
        dropSelf(ModBlocks.SLATE_TILE_STAIRS);
        dropSelf(ModBlocks.SLATE_STAIRS);
        dropSelf(ModBlocks.POLISHED_SLATE_STAIRS);
        dropSelf(ModBlocks.POLISHED_SLATE_WALL);
        dropSelf(ModBlocks.SLATE_WALL);
        dropSelf(ModBlocks.SLATE_TILE_WALL);
        add(ModBlocks.SLATE_SLAB, createSlabItemTable(ModBlocks.SLATE_SLAB));
        add(ModBlocks.POLISHED_SLATE_SLAB, createSlabItemTable(ModBlocks.POLISHED_SLATE_SLAB));
        add(ModBlocks.SLATE_TILE_SLAB, createSlabItemTable(ModBlocks.SLATE_TILE_SLAB));
        dropSelf(ModBlocks.BLOCK_OF_SULFUR);
        dropSelf(ModBlocks.BLOCK_OF_STEEL);
        dropSelf(ModBlocks.BLOCK_OF_CAST_STEEL);
        dropSelf(ModBlocks.BAUXITE);
        dropSelf(ModBlocks.PLATINUM_ORE);
        dropSelf(ModBlocks.DEEPSLATE_PLATINUM_ORE);

        //Block drops other stuff.
        add(ModBlocks.WOLFRAMITE_ORE, LightOreDrops(ModBlocks.WOLFRAMITE_ORE, ModItems.WOLFRAMITE));

        add(ModBlocks.RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));
        add(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));
        add(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE, SingleItemOreDrops(ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE, ModItems.RAINBOW_PYRITE));

        add(ModBlocks.SCHEELITE_ORE, AverageOreDrops(ModBlocks.SCHEELITE_ORE, ModItems.SCHEELITE));

        dropWhenSilkTouch(ModBlocks.ALUMINUM_GLASS);
        dropWhenSilkTouch(ModBlocks.ALUMINUM_GLASS_PANE);

        add(ModBlocks.SAPPHIRE_ORE, SingleItemOreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.SAPPHIRE));
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, SingleItemOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.SAPPHIRE));

        add(ModBlocks.NEPHRITE_ORE, LightOreDrops(ModBlocks.NEPHRITE_ORE, ModItems.NEPHRITE));
        add(ModBlocks.JADEITE_ORE, LightOreDrops(ModBlocks.JADEITE_ORE, ModItems.JADEITE));

        add(ModBlocks.SULFUR_ORE, AverageOreDrops(ModBlocks.SULFUR_ORE, ModItems.SULFUR));
        add(ModBlocks.BLACKSTONE_SULFUR_ORE, AverageOreDrops(ModBlocks.BLACKSTONE_SULFUR_ORE, ModItems.SULFUR));
    }

    public LootTable.Builder AverageOreDrops(Block drop, Item item) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder SingleItemOreDrops(Block drop, Item item) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f))))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    public LootTable.Builder LightOreDrops(Block drop, Item item) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f))))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
