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
    /**
     * Constructs a ModLootTableProvider configured for data generation with the given output target and registry lookup.
     *
     * @param dataOutput     the pack output target used to write generated loot tables
     * @param registryLookup a future providing access to game registries required for loot table construction
     */
    public ModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     * Registers loot table definitions for the mod's blocks used during data generation.
     *
     * Configures drop behavior for each block: many blocks drop themselves, specific
     * slabs and doors use slab/door item tables, ores use the helper factories
     * (AverageOreDrops, LightOreDrops, SingleItemOreDrops) to apply fortune, silk-touch
     * dispatch, and explosion decay, and selected glass blocks drop only when mined
     * with Silk Touch.
     */
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
        dropSelf(ModBlocks.BLOCK_OF_CAST_STEEL_B);
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

    /**
     * Create a loot table for an ore block that drops multiple items, honoring Silk Touch, explosion decay, and Fortune.
     *
     * The generated table dispatches to Silk Touch (dropping the block itself) and otherwise drops between 2 and 5 of the
     * specified item, with the drop count modified by the Fortune enchantment and reduced by explosion decay when applicable.
     *
     * @param drop the ore block this loot table applies to
     * @param item the item to drop when the ore is mined without Silk Touch
     * @return a LootTable.Builder configured for the ore's drops
     */
    public LootTable.Builder AverageOreDrops(Block drop, Item item) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    /**
     * Create a loot table for an ore that drops a single item while supporting Silk Touch, Fortune, and explosion decay.
     *
     * @param drop  the ore block whose loot table is being created
     * @param item  the item dropped by the ore when not Silk Touched
     * @return      a LootTable.Builder that drops exactly one of the specified item (subject to Fortune bonuses and explosion decay); when Silk Touch is applied the block itself is dropped
     */
    public LootTable.Builder SingleItemOreDrops(Block drop, Item item) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f))))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    /**
     * Create a loot table for an ore that drops a light quantity of the specified item.
     *
     * The table makes the block drop 1–2 of the given item, applies Fortune bonuses to the count,
     * respects Silk Touch (dropping the block itself when present), and applies explosion decay.
     *
     * @param drop the ore block that will use this loot table
     * @param item the item produced by breaking the ore
     * @return a LootTable.Builder representing the described drop behavior
     */
    public LootTable.Builder LightOreDrops(Block drop, Item item) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, ((net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder<?>)
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f))))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}