package anya.pizza.pizzasoat.datagen;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static anya.pizza.pizzasoat.util.ModGenRecipes.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                List<ItemConvertible> PINKU_SMELTABLES = List.of(ModBlocks.PINKU_ORE);
                List<ItemConvertible> RAINBOW_PYRITE_SMELTABLES = List.of(ModBlocks.RAINBOW_PYRITE_ORE, ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE);
                List<ItemConvertible> ALUMINUM_SMELTABLES = List.of(ModItems.CRUSHED_BAUXITE);
                List<ItemConvertible> SCHEELITE_SMELTABLES = List.of(ModBlocks.SCHEELITE_ORE);
                List<ItemConvertible> WOLFRAMITE_SMELTABLES = List.of(ModBlocks.WOLFRAMITE_ORE);
                List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                List<ItemConvertible> NEPHRITE_SMELTABLES = List.of(ModBlocks.NEPHRITE_ORE);
                List<ItemConvertible> JADEITE_SMELTABLES = List.of(ModBlocks.JADEITE_ORE);
                List<ItemConvertible> PLATINUM_SMELTABLES = List.of(ModBlocks.PLATINUM_ORE, ModBlocks.DEEPSLATE_PLATINUM_ORE);
                List<ItemConvertible> SULFUR_SMELTABLES = List.of(ModBlocks.SULFUR_ORE, ModBlocks.BLACKSTONE_SULFUR_ORE);
                List<ItemConvertible> CRUDE_IRON_SMELTABLES = List.of(Items.IRON_INGOT);
                List<ItemConvertible> STEEL_SMELTABLES = List.of(ModItems.CRUDE_IRON);
                List<ItemConvertible> CAST_STEEL_SMELTABLES = List.of(ModItems.STEEL);

                //Smithing Upgrades
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.PINKU_HELMET);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.PINKU_CHESTPLATE);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.PINKU_LEGGINGS);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.PINKU_BOOTS);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.PINKU_PICKAXE);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_AXE, RecipeCategory.TOOLS, ModItems.PINKU_AXE);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.PINKU_SHOVEL);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.PINKU_SWORD);
                offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.PINKU_HOE);
                offerDrillUpgradeRecipe(exporter, ModItems.SIMPLE_DRILL_HEAD, RecipeCategory.TOOLS, ModItems.ENHANCED_DRILL_HEAD);
                offerDrillUpgradeRecipe(exporter, ModItems.SIMPLE_TUNGSTEN_DRILL, RecipeCategory.TOOLS, ModItems.ENHANCED_TUNGSTEN_DRILL);
                offerDrillUpgradeRecipe(exporter, ModItems.ENHANCED_DRILL_HEAD, RecipeCategory.TOOLS, ModItems.ADVANCED_DRILL_HEAD);
                offerDrillUpgradeRecipe(exporter, ModItems.ENHANCED_TUNGSTEN_DRILL, RecipeCategory.TOOLS, ModItems.ADVANCED_TUNGSTEN_DRILL);
                offerDrillUpgradeRecipe(exporter, ModItems.ADVANCED_DRILL_HEAD, RecipeCategory.TOOLS, ModItems.PREMIUM_DRILL_HEAD);
                offerDrillUpgradeRecipe(exporter, ModItems.ADVANCED_TUNGSTEN_DRILL, RecipeCategory.TOOLS, ModItems.PREMIUM_TUNGSTEN_DRILL);
                offerDrillUpgradeRecipe(exporter, ModItems.SIMPLE_DIAMOND_DRILL, RecipeCategory.TOOLS, ModItems.ENHANCED_DIAMOND_DRILL);
                offerDrillUpgradeRecipe(exporter, ModItems.ENHANCED_DIAMOND_DRILL, RecipeCategory.TOOLS, ModItems.ADVANCED_DIAMOND_DRILL);
                offerDrillUpgradeRecipe(exporter, ModItems.ADVANCED_DIAMOND_DRILL, RecipeCategory.TOOLS, ModItems.PREMIUM_DIAMOND_DRILL);


                //Block Reversing Recipes
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PINKU, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_PINKU);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAINBOW_PYRITE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.TUNGSTEN, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_TUNGSTEN_B);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.ALUMINUM, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_ALUMINUM);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SAPPHIRE);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.JADEITE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_JADEITE);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PLATINUM, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_PLATINUM);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.SULFUR, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SULFUR);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_STEEL);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.CAST_STEEL, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_CAST_STEEL_B);

                //Smelting Recipes
                offerSmelting(ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM, 0.5f, 200, "aluminum");
                offerSmelting(PINKU_SMELTABLES, RecipeCategory.MISC, ModItems.PINKU_SHARD, 0.5f, 250, "pinku");
                offerSmelting(RAINBOW_PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAINBOW_PYRITE, 0.5f, 200, "rainbow_pyrite");
                offerSmelting(SCHEELITE_SMELTABLES, RecipeCategory.MISC, ModItems.SCHEELITE, 0.5f, 200, "scheelite");
                offerSmelting(WOLFRAMITE_SMELTABLES, RecipeCategory.MISC, ModItems.WOLFRAMITE, 0.5f, 200, "wolframite");
                offerSmelting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.5f, 250, "sapphire");
                offerSmelting(NEPHRITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEPHRITE, 0.5f, 200, "nephrite");
                offerSmelting(JADEITE_SMELTABLES, RecipeCategory.MISC, ModItems.JADEITE, 0.5f, 250, "jadeite");
                offerSmelting(PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM, 0.5f, 200, "platinum");
                offerSmelting(SULFUR_SMELTABLES, RecipeCategory.MISC, ModItems.SULFUR, 0.5f, 200, "sulfur");

                //Blasting Recipes
                offerBlasting(ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM, 0.5f, 100, "aluminum");
                offerBlasting(PINKU_SMELTABLES, RecipeCategory.MISC, ModItems.PINKU_SHARD, 0.5f, 150, "pinku");
                offerBlasting(RAINBOW_PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAINBOW_PYRITE, 0.5f, 100, "rainbow_pyrite");
                offerBlasting(SCHEELITE_SMELTABLES, RecipeCategory.MISC, ModItems.SCHEELITE, 0.5f, 100, "scheelite");
                offerBlasting(WOLFRAMITE_SMELTABLES, RecipeCategory.MISC, ModItems.WOLFRAMITE, 0.5f, 100, "wolframite");
                offerBlasting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.5f, 150, "sapphire");
                offerBlasting(NEPHRITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEPHRITE, 0.5f, 100, "nephrite");
                offerBlasting(JADEITE_SMELTABLES, RecipeCategory.MISC, ModItems.JADEITE, 0.5f, 150, "jadeite");
                offerBlasting(PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM, 0.5f, 100, "platinum");
                offerBlasting(SULFUR_SMELTABLES, RecipeCategory.MISC, ModItems.SULFUR, 0.5f, 100, "sulfur");
                offerBlasting(CRUDE_IRON_SMELTABLES, RecipeCategory.MISC, ModItems.CRUDE_IRON, 0.5f, 100, "crude_iron");
                offerBlasting(STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL, 0.5f, 120, "steel");
                offerBlasting(CAST_STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.CAST_STEEL, 0.5f, 120, "cast_steel");

                //Stonecutting Recipes
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LIMESTONE, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_STAIRS, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_SLAB, ModBlocks.LIMESTONE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_WALL, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_STAIRS, ModBlocks.LIMESTONE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE, 1);

                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SLATE, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILES, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_STAIRS, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_SLAB, ModBlocks.SLATE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_WALL, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_STAIRS, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.SLATE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_STAIRS, ModBlocks.SLATE, 1);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, ModBlocks.SLATE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, ModBlocks.SLATE, 1);

                //Crafting Recipes
                offerChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LIMESTONE, ModBlocks.LIMESTONE_SLAB);
                offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE);
                offerPolishedStoneRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE_BRICKS);
                offerChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SLATE, ModBlocks.SLATE_SLAB);
                offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILES, ModBlocks.SLATE);
                offerPolishedStoneRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE_TILES);
                offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BAUXITE, ModItems.CRUSHED_BAUXITE);
                offerStainedGlassPaneRecipe(ModBlocks.ALUMINUM_GLASS_PANE, ModBlocks.ALUMINUM_GLASS);

                //Slab Recipes
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE);
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_SLAB, ModBlocks.LIMESTONE_BRICKS);
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.POLISHED_LIMESTONE);
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, ModBlocks.SLATE);
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_SLAB, ModBlocks.SLATE_TILES);
                offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.POLISHED_SLATE);

                //Wall Recipes
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE);
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_WALL, ModBlocks.LIMESTONE_BRICKS);
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.POLISHED_LIMESTONE);
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, ModBlocks.SLATE);
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_WALL, ModBlocks.SLATE_TILES);
                offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, ModBlocks.POLISHED_SLATE);

                //Stair Recipes
                createStairsRecipe(ModBlocks.LIMESTONE_STAIRS, Ingredient.ofItem(ModBlocks.LIMESTONE)).criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE)).offerTo(exporter);
                createStairsRecipe(ModBlocks.LIMESTONE_BRICK_STAIRS, Ingredient.ofItem(ModBlocks.LIMESTONE_BRICKS)).criterion(hasItem(ModBlocks.LIMESTONE_BRICKS), conditionsFromItem(ModBlocks.LIMESTONE_BRICKS)).offerTo(exporter);
                createStairsRecipe(ModBlocks.POLISHED_LIMESTONE_STAIRS, Ingredient.ofItem(ModBlocks.POLISHED_LIMESTONE)).criterion(hasItem(ModBlocks.POLISHED_LIMESTONE), conditionsFromItem(ModBlocks.POLISHED_LIMESTONE)).offerTo(exporter);;
                createStairsRecipe(ModBlocks.SLATE_STAIRS, Ingredient.ofItem(ModBlocks.SLATE)).criterion(hasItem(ModBlocks.SLATE), conditionsFromItem(ModBlocks.SLATE)).offerTo(exporter);
                createStairsRecipe(ModBlocks.SLATE_TILE_STAIRS, Ingredient.ofItem(ModBlocks.SLATE_TILES)).criterion(hasItem(ModBlocks.SLATE_TILES), conditionsFromItem(ModBlocks.SLATE_TILES)).offerTo(exporter);
                createStairsRecipe(ModBlocks.POLISHED_SLATE_STAIRS, Ingredient.ofItem(ModBlocks.POLISHED_SLATE)).criterion(hasItem(ModBlocks.POLISHED_SLATE), conditionsFromItem(ModBlocks.POLISHED_SLATE)).offerTo(exporter);

                //Door Recipes
                createDoorRecipe(ModBlocks.ALUMINUM_DOOR, Ingredient.ofItem(ModItems.ALUMINUM)).criterion(hasItem(ModItems.ALUMINUM), conditionsFromItem(ModItems.ALUMINUM)).offerTo(exporter);
                createTrapdoorRecipe(ModBlocks.ALUMINUM_TRAPDOOR, Ingredient.ofItem(ModItems.ALUMINUM)).criterion(hasItem(ModItems.ALUMINUM), conditionsFromItem(ModItems.ALUMINUM)).offerTo(exporter);


                //Tool Recipes
                offerPickaxeRecipe(exporter, ModItems.RAINBOW_PYRITE_PICKAXE, ModItems.RAINBOW_PYRITE);
                offerAxeRecipe(exporter, ModItems.RAINBOW_PYRITE_AXE, ModItems.RAINBOW_PYRITE);
                offerShovelRecipe(exporter, ModItems.RAINBOW_PYRITE_SHOVEL, ModItems.RAINBOW_PYRITE);
                offerSwordRecipe(exporter, ModItems.RAINBOW_PYRITE_SWORD, ModItems.RAINBOW_PYRITE);
                offerHoeRecipe(exporter, ModItems.RAINBOW_PYRITE_HOE, ModItems.RAINBOW_PYRITE);
                offerPickaxeRecipe(exporter, ModItems.TUNGSTEN_PICKAXE, ModItems.TUNGSTEN);
                offerAxeRecipe(exporter, ModItems.TUNGSTEN_AXE, ModItems.TUNGSTEN);
                offerShovelRecipe(exporter, ModItems.TUNGSTEN_SHOVEL, ModItems.TUNGSTEN);
                offerSwordRecipe(exporter, ModItems.TUNGSTEN_SWORD, ModItems.TUNGSTEN);
                offerHoeRecipe(exporter, ModItems.TUNGSTEN_HOE, ModItems.TUNGSTEN);
                offerPickaxeRecipe(exporter, ModItems.ALUMINUM_PICKAXE, ModItems.ALUMINUM);
                offerAxeRecipe(exporter, ModItems.ALUMINUM_AXE, ModItems.ALUMINUM);
                offerShovelRecipe(exporter, ModItems.ALUMINUM_SHOVEL, ModItems.ALUMINUM);
                offerSwordRecipe(exporter, ModItems.ALUMINUM_SWORD, ModItems.ALUMINUM);
                offerHoeRecipe(exporter, ModItems.ALUMINUM_HOE, ModItems.ALUMINUM);
                offerPickaxeRecipe(exporter, ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE);
                offerAxeRecipe(exporter, ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE);
                offerShovelRecipe(exporter, ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE);
                offerSwordRecipe(exporter, ModItems.SAPPHIRE_SWORD, ModItems.SAPPHIRE);
                offerHoeRecipe(exporter, ModItems.SAPPHIRE_HOE, ModItems.SAPPHIRE);
                offerPickaxeRecipe(exporter, ModItems.NEPHRITE_PICKAXE, ModItems.NEPHRITE);
                offerAxeRecipe(exporter, ModItems.NEPHRITE_AXE, ModItems.NEPHRITE);
                offerShovelRecipe(exporter, ModItems.NEPHRITE_SHOVEL, ModItems.NEPHRITE);
                offerSwordRecipe(exporter, ModItems.NEPHRITE_SWORD, ModItems.NEPHRITE);
                offerHoeRecipe(exporter, ModItems.NEPHRITE_HOE, ModItems.NEPHRITE);
                offerPickaxeRecipe(exporter, ModItems.JADEITE_PICKAXE, ModItems.JADEITE);
                offerAxeRecipe(exporter, ModItems.JADEITE_AXE, ModItems.JADEITE);
                offerShovelRecipe(exporter, ModItems.JADEITE_SHOVEL, ModItems.JADEITE);
                offerSwordRecipe(exporter, ModItems.JADEITE_SWORD, ModItems.JADEITE);
                offerHoeRecipe(exporter, ModItems.JADEITE_HOE, ModItems.JADEITE);
                offerPickaxeRecipe(exporter, ModItems.PLATINUM_PICKAXE, ModItems.PLATINUM);
                offerAxeRecipe(exporter, ModItems.PLATINUM_AXE, ModItems.PLATINUM);
                offerShovelRecipe(exporter, ModItems.PLATINUM_SHOVEL, ModItems.PLATINUM);
                offerSwordRecipe(exporter, ModItems.PLATINUM_SWORD, ModItems.PLATINUM);
                offerHoeRecipe(exporter, ModItems.PLATINUM_HOE, ModItems.PLATINUM);
                offerPickaxeRecipe(exporter, ModItems.STEEL_PICKAXE, ModItems.STEEL);
                offerAxeRecipe(exporter, ModItems.STEEL_AXE, ModItems.STEEL);
                offerShovelRecipe(exporter, ModItems.STEEL_SHOVEL, ModItems.STEEL);
                offerSwordRecipe(exporter, ModItems.STEEL_SWORD, ModItems.STEEL);
                offerHoeRecipe(exporter, ModItems.STEEL_HOE, ModItems.STEEL);
                offerPickaxeRecipe(exporter, ModItems.CAST_STEEL_PICKAXE, ModItems.CAST_STEEL);
                offerAxeRecipe(exporter, ModItems.CAST_STEEL_AXE, ModItems.CAST_STEEL);
                offerShovelRecipe(exporter, ModItems.CAST_STEEL_SHOVEL, ModItems.CAST_STEEL);
                offerSwordRecipe(exporter, ModItems.CAST_STEEL_SWORD, ModItems.CAST_STEEL);
                offerHoeRecipe(exporter, ModItems.CAST_STEEL_HOE, ModItems.CAST_STEEL);

                //Drills
                createShapeless(RecipeCategory.TOOLS, ModItems.SIMPLE_TUNGSTEN_DRILL, 1)
                        .input(ModItems.TUNGSTEN_DRILL_BIT)
                        .input(ModItems.SIMPLE_DRILL_HEAD)
                        .criterion(hasItem(ModItems.TUNGSTEN_DRILL_BIT), conditionsFromItem(ModItems.TUNGSTEN_DRILL_BIT))
                        .criterion(hasItem(ModItems.SIMPLE_DRILL_HEAD), conditionsFromItem(ModItems.SIMPLE_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.ENHANCED_TUNGSTEN_DRILL, 1)
                        .input(ModItems.TUNGSTEN_DRILL_BIT)
                        .input(ModItems.ENHANCED_DRILL_HEAD)
                        .criterion(hasItem(ModItems.TUNGSTEN_DRILL_BIT), conditionsFromItem(ModItems.TUNGSTEN_DRILL_BIT))
                        .criterion(hasItem(ModItems.ENHANCED_DRILL_HEAD), conditionsFromItem(ModItems.ENHANCED_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.ADVANCED_TUNGSTEN_DRILL, 1)
                        .input(ModItems.TUNGSTEN_DRILL_BIT)
                        .input(ModItems.ADVANCED_DRILL_HEAD)
                        .criterion(hasItem(ModItems.TUNGSTEN_DRILL_BIT), conditionsFromItem(ModItems.TUNGSTEN_DRILL_BIT))
                        .criterion(hasItem(ModItems.ADVANCED_DRILL_HEAD), conditionsFromItem(ModItems.ADVANCED_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.PREMIUM_TUNGSTEN_DRILL, 1)
                        .input(ModItems.TUNGSTEN_DRILL_BIT)
                        .input(ModItems.PREMIUM_DRILL_HEAD)
                        .criterion(hasItem(ModItems.TUNGSTEN_DRILL_BIT), conditionsFromItem(ModItems.TUNGSTEN_DRILL_BIT))
                        .criterion(hasItem(ModItems.PREMIUM_DRILL_HEAD), conditionsFromItem(ModItems.PREMIUM_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.SIMPLE_DIAMOND_DRILL, 1)
                        .input(ModItems.DIAMOND_DRILL_BIT)
                        .input(ModItems.SIMPLE_DRILL_HEAD)
                        .criterion(hasItem(ModItems.DIAMOND_DRILL_BIT), conditionsFromItem(ModItems.DIAMOND_DRILL_BIT))
                        .criterion(hasItem(ModItems.SIMPLE_DRILL_HEAD), conditionsFromItem(ModItems.SIMPLE_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.ENHANCED_DIAMOND_DRILL, 1)
                        .input(ModItems.DIAMOND_DRILL_BIT)
                        .input(ModItems.ENHANCED_DRILL_HEAD)
                        .criterion(hasItem(ModItems.DIAMOND_DRILL_BIT), conditionsFromItem(ModItems.DIAMOND_DRILL_BIT))
                        .criterion(hasItem(ModItems.ENHANCED_DRILL_HEAD), conditionsFromItem(ModItems.ENHANCED_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.ADVANCED_DIAMOND_DRILL, 1)
                        .input(ModItems.DIAMOND_DRILL_BIT)
                        .input(ModItems.ADVANCED_DRILL_HEAD)
                        .criterion(hasItem(ModItems.DIAMOND_DRILL_BIT), conditionsFromItem(ModItems.DIAMOND_DRILL_BIT))
                        .criterion(hasItem(ModItems.ADVANCED_DRILL_HEAD), conditionsFromItem(ModItems.ADVANCED_DRILL_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ModItems.PREMIUM_DIAMOND_DRILL, 1)
                        .input(ModItems.DIAMOND_DRILL_BIT)
                        .input(ModItems.PREMIUM_DRILL_HEAD)
                        .criterion(hasItem(ModItems.DIAMOND_DRILL_BIT), conditionsFromItem(ModItems.DIAMOND_DRILL_BIT))
                        .criterion(hasItem(ModItems.PREMIUM_DRILL_HEAD), conditionsFromItem(ModItems.PREMIUM_DRILL_HEAD))
                        .offerTo(exporter);


                //Armor Recipes
                offerHelmetRecipe(exporter, ModItems.RAINBOW_PYRITE_HELMET, ModItems.RAINBOW_PYRITE);
                offerChestplateRecipe(exporter, ModItems.RAINBOW_PYRITE_CHESTPLATE, ModItems.RAINBOW_PYRITE);
                offerLeggingsRecipe(exporter, ModItems.RAINBOW_PYRITE_LEGGINGS, ModItems.RAINBOW_PYRITE);
                offerBootsRecipe(exporter, ModItems.RAINBOW_PYRITE_BOOTS, ModItems.RAINBOW_PYRITE);
                offerHelmetRecipe(exporter, ModItems.TUNGSTEN_HELMET, ModItems.TUNGSTEN);
                offerChestplateRecipe(exporter, ModItems.TUNGSTEN_CHESTPLATE, ModItems.TUNGSTEN);
                offerLeggingsRecipe(exporter, ModItems.TUNGSTEN_LEGGINGS, ModItems.TUNGSTEN);
                offerBootsRecipe(exporter, ModItems.TUNGSTEN_BOOTS, ModItems.TUNGSTEN);
                offerHelmetRecipe(exporter, ModItems.ALUMINUM_HELMET, ModItems.ALUMINUM);
                offerChestplateRecipe(exporter, ModItems.ALUMINUM_CHESTPLATE, ModItems.ALUMINUM);
                offerLeggingsRecipe(exporter, ModItems.ALUMINUM_LEGGINGS, ModItems.ALUMINUM);
                offerBootsRecipe(exporter, ModItems.ALUMINUM_BOOTS, ModItems.ALUMINUM);
                offerHelmetRecipe(exporter, ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE);
                offerChestplateRecipe(exporter, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE);
                offerLeggingsRecipe(exporter, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE);
                offerBootsRecipe(exporter, ModItems.SAPPHIRE_BOOTS, ModItems.SAPPHIRE);
                offerHelmetRecipe(exporter, ModItems.NEPHRITE_HELMET, ModItems.NEPHRITE);
                offerChestplateRecipe(exporter, ModItems.NEPHRITE_CHESTPLATE, ModItems.NEPHRITE);
                offerLeggingsRecipe(exporter, ModItems.NEPHRITE_LEGGINGS, ModItems.NEPHRITE);
                offerBootsRecipe(exporter, ModItems.NEPHRITE_BOOTS, ModItems.NEPHRITE);
                offerHelmetRecipe(exporter, ModItems.JADEITE_HELMET, ModItems.JADEITE);
                offerChestplateRecipe(exporter, ModItems.JADEITE_CHESTPLATE, ModItems.JADEITE);
                offerLeggingsRecipe(exporter, ModItems.JADEITE_LEGGINGS, ModItems.JADEITE);
                offerBootsRecipe(exporter, ModItems.JADEITE_BOOTS, ModItems.JADEITE);
                offerHelmetRecipe(exporter, ModItems.PLATINUM_HELMET, ModItems.PLATINUM);
                offerChestplateRecipe(exporter, ModItems.PLATINUM_CHESTPLATE, ModItems.PLATINUM);
                offerLeggingsRecipe(exporter, ModItems.PLATINUM_LEGGINGS, ModItems.PLATINUM);
                offerBootsRecipe(exporter, ModItems.PLATINUM_BOOTS, ModItems.PLATINUM);
                offerHelmetRecipe(exporter, ModItems.STEEL_HELMET, ModItems.STEEL);
                offerChestplateRecipe(exporter, ModItems.STEEL_CHESTPLATE, ModItems.STEEL);
                offerLeggingsRecipe(exporter, ModItems.STEEL_LEGGINGS, ModItems.STEEL);
                offerBootsRecipe(exporter, ModItems.STEEL_BOOTS, ModItems.STEEL);
                offerHelmetRecipe(exporter, ModItems.CAST_STEEL_HELMET, ModItems.CAST_STEEL);
                offerChestplateRecipe(exporter, ModItems.CAST_STEEL_CHESTPLATE, ModItems.CAST_STEEL);
                offerLeggingsRecipe(exporter, ModItems.CAST_STEEL_LEGGINGS, ModItems.CAST_STEEL);
                offerBootsRecipe(exporter, ModItems.CAST_STEEL_BOOTS, ModItems.CAST_STEEL);


                //Random
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALUMINUM_GLASS,1)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .input('#', ModItems.ALUMINUM)
                        .input('X', Items.GLASS)
                        .criterion(hasItem(ModItems.ALUMINUM), conditionsFromItem(ModItems.ALUMINUM))
                        .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.PINKU,1)
                        .pattern(" # ")
                        .pattern("#X#")
                        .pattern(" # ")
                        .input('#', ModItems.PINKU_SHARD)
                        .input('X', Items.AMETHYST_SHARD)
                        .criterion(hasItem(ModItems.PINKU_SHARD), conditionsFromItem(ModItems.PINKU_SHARD))
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of("pinku_from_pinku_shards")));

                createShaped(RecipeCategory.MISC, Blocks.TNT,1)
                        .pattern("X#X")
                        .pattern("#X#")
                        .pattern("X#X")
                        .input('#', Items.SAND)
                        .input('X', ModItems.SULFUR)
                        .criterion(hasItem(ModItems.SULFUR), conditionsFromItem(ModItems.SULFUR))
                        .criterion(hasItem(Items.SAND), conditionsFromItem(Items.SAND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Items.TORCH, 4)
                        .pattern("X")
                        .pattern("#")
                        .input('X', ModItems.SULFUR)
                        .input('#', Items.STICK)
                        .criterion(hasItem(ModItems.SULFUR), conditionsFromItem(ModItems.SULFUR))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.CRUSHER, 1)
                        .pattern("###")
                        .pattern("X X")
                        .pattern("###")
                        .input('#', ModItems.CAST_STEEL)
                        .input('X', Items.IRON_INGOT)
                        .criterion(hasItem(ModItems.CAST_STEEL), conditionsFromItem(ModItems.CAST_STEEL))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.TUNGSTEN_DRILL_BIT, 1)
                        .pattern("   ")
                        .pattern(" # ")
                        .pattern("#X#")
                        .input('#', ModItems.TUNGSTEN)
                        .input('X', ModBlocks.BLOCK_OF_TUNGSTEN_B)
                        .criterion(hasItem(ModItems.TUNGSTEN), conditionsFromItem(ModItems.TUNGSTEN))
                        .criterion(hasItem(ModBlocks.BLOCK_OF_TUNGSTEN_B), conditionsFromItem(ModBlocks.BLOCK_OF_TUNGSTEN_B))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.SIMPLE_DRILL_HEAD, 1)
                        .pattern(" X ")
                        .pattern("#R#")
                        .pattern("###")
                        .input('X', ModItems.CAST_STEEL)
                        .input('#', ModBlocks.BLOCK_OF_CAST_STEEL_B)
                        .input('R', Items.REDSTONE)
                        .criterion(hasItem(ModItems.CAST_STEEL), conditionsFromItem(ModItems.CAST_STEEL))
                        .criterion(hasItem(ModBlocks.BLOCK_OF_CAST_STEEL_B), conditionsFromItem(ModBlocks.BLOCK_OF_CAST_STEEL_B))
                        .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.DIAMOND_DRILL_BIT, 1)
                        .pattern("   ")
                        .pattern(" # ")
                        .pattern("###")
                        .input('#', Blocks.DIAMOND_BLOCK)
                        .criterion(hasItem(Blocks.DIAMOND_BLOCK), conditionsFromItem(Blocks.DIAMOND_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("#X#")
                        .pattern("#U#")
                        .pattern("###")
                        .input('#', ModItems.CAST_STEEL)
                        .input('X', ModBlocks.BLOCK_OF_CAST_STEEL_B)
                        .input('U', ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE)
                        .criterion(hasItem(ModItems.CAST_STEEL), conditionsFromItem(ModItems.CAST_STEEL))
                        .criterion(hasItem(ModBlocks.BLOCK_OF_CAST_STEEL_B), conditionsFromItem(ModBlocks.BLOCK_OF_CAST_STEEL_B))
                        .criterion(hasItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Pizza's OAT Recipes";
    }
}
