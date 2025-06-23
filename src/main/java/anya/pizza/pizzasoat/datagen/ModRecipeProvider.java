package anya.pizza.pizzasoat.datagen;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.item.ModItems;
import anya.pizza.pizzasoat.util.ModGenRecipes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static anya.pizza.pizzasoat.util.ModGenRecipes.offerPinkuUpgradeRecipe;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> PINKU_SMELTABLES = List.of(ModBlocks.PINKU_ORE);
        List<ItemConvertible> RAINBOW_PYRITE_SMELTABLES = List.of(ModBlocks.RAINBOW_PYRITE_ORE);
        List<ItemConvertible> ALUMINUM_SMELTABLES = List.of(ModItems.BAUXITE);
        List<ItemConvertible> BAUXITE_SMELTABLES = List.of(ModBlocks.BAUXITE_ORE, ModBlocks.DEEPSLATE_BAUXITE_ORE);
        List<ItemConvertible> SCHEELITE_SMELTABLES = List.of(ModBlocks.SCHEELITE_ORE);
        List<ItemConvertible> SODIUM_CARBONATE_SMELTABLES = List.of(ModItems.TRONA);
        List<ItemConvertible> TRONA_SMELTABLES = List.of(ModBlocks.TRONA_ORE);
        List<ItemConvertible> TUNGSTEN_SMELTABLES = List.of(ModItems.PURE_TUNGSTEN);
        List<ItemConvertible> WOLFRAMITE_SMELTABLES = List.of(ModBlocks.WOLFRAMITE_ORE);
        List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        List<ItemConvertible> NEPHRITE_SMELTABLES = List.of(ModBlocks.NEPHRITE_ORE, ModBlocks.DEEPSLATE_NEPHRITE_ORE);
        List<ItemConvertible> PLATINUM_SMELTABLES = List.of(ModBlocks.PLATINUM_ORE, ModBlocks.DEEPSLATE_PLATINUM_ORE);
        List<ItemConvertible> SULFUR_SMELTABLES = List.of(ModBlocks.SULFUR_ORE, ModBlocks.BLACKSTONE_SULFUR_ORE);
        List<ItemConvertible> CRUDE_IRON_SMELTABLES = List.of(Items.IRON_INGOT);
        List<ItemConvertible> STEEL_SMELTABLES = List.of(ModItems.CRUDE_IRON);

        //Creates recipes for blocks to make a block of something and back to 9 items
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINKU, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_PINKU);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAINBOW_PYRITE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TUNGSTEN, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_TUNGSTEN);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ALUMINUM, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_ALUMINUM);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SAPPHIRE);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.JADE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_JADE);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PLATINUM, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_PLATINUM);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SULFUR, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SULFUR);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_STEEL);


        //Smelting Recipes
        offerSmelting(exporter, ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM,
                0.5f, 200, "aluminum");
        offerSmelting(exporter, BAUXITE_SMELTABLES, RecipeCategory.MISC, ModItems.BAUXITE,
                0.5f, 200, "bauxite");
        offerSmelting(exporter, PINKU_SMELTABLES, RecipeCategory.MISC, ModItems.PINKU_SHARD,
                0.5f, 250, "pinku");
        offerSmelting(exporter, RAINBOW_PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAINBOW_PYRITE,
                0.5f, 200, "rainbow_pyrite");
        offerSmelting(exporter, SCHEELITE_SMELTABLES, RecipeCategory.MISC, ModItems.SCHEELITE,
                0.5f, 200, "scheelite");
        offerSmelting(exporter, SODIUM_CARBONATE_SMELTABLES, RecipeCategory.MISC, ModItems.SODIUM_CARBONATE,
                0.5f, 200, "sodium_carbonate");
        offerSmelting(exporter, TRONA_SMELTABLES, RecipeCategory.MISC, ModItems.TRONA,
                0.5f, 200, "trona");
        offerSmelting(exporter, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN,
                0.5f, 200, "tungsten");
        offerSmelting(exporter, WOLFRAMITE_SMELTABLES, RecipeCategory.MISC, ModItems.WOLFRAMITE,
                0.5f, 200, "wolframite");
        offerSmelting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE,
                0.5f, 250, "sapphire");
        offerSmelting(exporter, NEPHRITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEPHRITE,
                0.5f, 200, "nephrite");
        offerSmelting(exporter, PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM,
                0.5f, 200, "platinum");
        offerSmelting(exporter, SULFUR_SMELTABLES, RecipeCategory.MISC, ModItems.SULFUR,
                0.5f, 200, "sulfur");


        //Blasting Recipes
        offerBlasting(exporter, ALUMINUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM,
                0.5f, 100, "aluminum");
        offerBlasting(exporter, BAUXITE_SMELTABLES, RecipeCategory.MISC, ModItems.BAUXITE,
                0.5f, 100, "bauxite");
        offerBlasting(exporter, PINKU_SMELTABLES, RecipeCategory.MISC, ModItems.PINKU_SHARD,
                0.5f, 150, "pinku");
        offerBlasting(exporter, RAINBOW_PYRITE_SMELTABLES, RecipeCategory.MISC, ModItems.RAINBOW_PYRITE,
                0.5f, 100, "rainbow_pyrite");
        offerBlasting(exporter, SCHEELITE_SMELTABLES, RecipeCategory.MISC, ModItems.SCHEELITE,
                0.5f, 100, "scheelite");
        offerBlasting(exporter, SODIUM_CARBONATE_SMELTABLES, RecipeCategory.MISC, ModItems.SODIUM_CARBONATE,
                0.5f, 100, "sodium_carbonate");
        offerBlasting(exporter, TRONA_SMELTABLES, RecipeCategory.MISC, ModItems.TRONA,
                0.5f, 100, "trona");
        offerBlasting(exporter, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN,
                0.5f, 100, "tungsten");
        offerBlasting(exporter, WOLFRAMITE_SMELTABLES, RecipeCategory.MISC, ModItems.WOLFRAMITE,
                0.5f, 100, "wolframite");
        offerBlasting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE,
                0.5f, 150, "sapphire");
        offerBlasting(exporter, NEPHRITE_SMELTABLES, RecipeCategory.MISC, ModItems.NEPHRITE,
                0.5f, 100, "nephrite");
        offerBlasting(exporter, PLATINUM_SMELTABLES, RecipeCategory.MISC, ModItems.PLATINUM,
                0.5f, 100, "platinum");
        offerBlasting(exporter, SULFUR_SMELTABLES, RecipeCategory.MISC, ModItems.SULFUR,
                0.5f, 100, "sulfur");
        offerBlasting(exporter, CRUDE_IRON_SMELTABLES, RecipeCategory.MISC, ModItems.CRUDE_IRON,
                0.5f, 100, "crude_iron");
        offerBlasting(exporter, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL,
                0.5f, 120, "steel");

        //Stonecutting Recipes
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LIMESTONE, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_STAIRS, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_SLAB, ModBlocks.LIMESTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_WALL, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_STAIRS, ModBlocks.LIMESTONE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE, 1);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SLATE, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILES, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_STAIRS, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_SLAB, ModBlocks.SLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_WALL, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_STAIRS, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.SLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_STAIRS, ModBlocks.SLATE, 1);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, ModBlocks.SLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, ModBlocks.SLATE, 1);

        //Crafting Recipes
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LIMESTONE, ModBlocks.LIMESTONE_SLAB);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE_BRICKS);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SLATE, ModBlocks.SLATE_SLAB);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILES, ModBlocks.SLATE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE_TILES);

        //Slab Recipes
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_SLAB, ModBlocks.LIMESTONE_BRICKS);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.POLISHED_LIMESTONE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, ModBlocks.SLATE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_SLAB, ModBlocks.SLATE_TILES);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.POLISHED_SLATE);

        //Wall Recipes
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_WALL, ModBlocks.LIMESTONE_BRICKS);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.POLISHED_LIMESTONE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, ModBlocks.SLATE);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_WALL, ModBlocks.SLATE_TILES);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, ModBlocks.POLISHED_SLATE);

        //Stair Recipes
        ModGenRecipes.StairsRecipe(ModBlocks.LIMESTONE_STAIRS, (ModBlocks.LIMESTONE)).offerTo(exporter);
        ModGenRecipes.StairsRecipe(ModBlocks.LIMESTONE_BRICK_STAIRS, (ModBlocks.LIMESTONE_BRICKS)).offerTo(exporter);
        ModGenRecipes.StairsRecipe(ModBlocks.POLISHED_LIMESTONE_STAIRS, (ModBlocks.POLISHED_LIMESTONE)).offerTo(exporter);
        ModGenRecipes.StairsRecipe(ModBlocks.SLATE_STAIRS, (ModBlocks.SLATE)).offerTo(exporter);
        ModGenRecipes.StairsRecipe(ModBlocks.SLATE_TILE_STAIRS, (ModBlocks.SLATE_TILES)).offerTo(exporter);
        ModGenRecipes.StairsRecipe(ModBlocks.POLISHED_SLATE_STAIRS, (ModBlocks.POLISHED_SLATE)).offerTo(exporter);

        //Door Recipes
        ModGenRecipes.DoorRecipe(ModBlocks.ALUMINUM_DOOR, (ModItems.ALUMINUM)).offerTo(exporter);
        ModGenRecipes.TrapdoorRecipe(ModBlocks.ALUMINUM_TRAPDOOR, (ModItems.ALUMINUM)).offerTo(exporter);

        //Tool Recipes
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.PINKU_PICKAXE);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_AXE, RecipeCategory.COMBAT, ModItems.PINKU_AXE);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.PINKU_SHOVEL);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.PINKU_SWORD);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.PINKU_HOE);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.RAINBOW_PYRITE_PICKAXE, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.RAINBOW_PYRITE_AXE, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.RAINBOW_PYRITE_SHOVEL, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.RAINBOW_PYRITE_SWORD, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.RAINBOW_PYRITE_HOE, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.TUNGSTEN_PICKAXE, ModItems.TUNGSTEN);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.TUNGSTEN_AXE, ModItems.TUNGSTEN);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.TUNGSTEN_SHOVEL, ModItems.TUNGSTEN);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.TUNGSTEN_SWORD, ModItems.TUNGSTEN);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.TUNGSTEN_HOE, ModItems.TUNGSTEN);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.ALUMINUM_PICKAXE, ModItems.ALUMINUM);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.ALUMINUM_AXE, ModItems.ALUMINUM);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.ALUMINUM_SHOVEL, ModItems.ALUMINUM);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.ALUMINUM_SWORD, ModItems.ALUMINUM);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.ALUMINUM_HOE, ModItems.ALUMINUM);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.SAPPHIRE_SWORD, ModItems.SAPPHIRE);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.SAPPHIRE_HOE, ModItems.SAPPHIRE);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.JADE_PICKAXE, ModItems.JADE);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.JADE_AXE, ModItems.JADE);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.JADE_SHOVEL, ModItems.JADE);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.JADE_SWORD, ModItems.JADE);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.JADE_HOE, ModItems.JADE);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.PLATINUM_PICKAXE, ModItems.PLATINUM);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.PLATINUM_AXE, ModItems.PLATINUM);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.PLATINUM_SHOVEL, ModItems.PLATINUM);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.PLATINUM_SWORD, ModItems.PLATINUM);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.PLATINUM_HOE, ModItems.PLATINUM);
        ModGenRecipes.offerPickaxeRecipe(exporter, ModItems.STEEL_PICKAXE, ModItems.STEEL);
        ModGenRecipes.offerAxeRecipe(exporter, ModItems.STEEL_AXE, ModItems.STEEL);
        ModGenRecipes.offerShovelRecipe(exporter, ModItems.STEEL_SHOVEL, ModItems.STEEL);
        ModGenRecipes.offerSwordRecipe(exporter, ModItems.STEEL_SWORD, ModItems.STEEL);
        ModGenRecipes.offerHoeRecipe(exporter, ModItems.STEEL_HOE, ModItems.STEEL);


        //Armor Recipes
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.PINKU_HELMET);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.PINKU_CHESTPLATE);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.PINKU_LEGGINGS);
        offerPinkuUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.PINKU_BOOTS);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.RAINBOW_PYRITE_HELMET, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.RAINBOW_PYRITE_CHESTPLATE, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.RAINBOW_PYRITE_LEGGINGS, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.RAINBOW_PYRITE_BOOTS, ModItems.RAINBOW_PYRITE);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.TUNGSTEN_HELMET, ModItems.TUNGSTEN);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.TUNGSTEN_CHESTPLATE, ModItems.TUNGSTEN);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.TUNGSTEN_LEGGINGS, ModItems.TUNGSTEN);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.TUNGSTEN_BOOTS, ModItems.TUNGSTEN);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.ALUMINUM_HELMET, ModItems.ALUMINUM);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.ALUMINUM_CHESTPLATE, ModItems.ALUMINUM);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.ALUMINUM_LEGGINGS, ModItems.ALUMINUM);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.ALUMINUM_BOOTS, ModItems.ALUMINUM);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.SAPPHIRE_BOOTS, ModItems.SAPPHIRE);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.JADE_HELMET, ModItems.JADE);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.JADE_CHESTPLATE, ModItems.JADE);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.JADE_LEGGINGS, ModItems.JADE);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.JADE_BOOTS, ModItems.JADE);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.PLATINUM_HELMET, ModItems.PLATINUM);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.PLATINUM_CHESTPLATE, ModItems.PLATINUM);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.PLATINUM_LEGGINGS, ModItems.PLATINUM);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.PLATINUM_BOOTS, ModItems.PLATINUM);
        ModGenRecipes.offerHelmetRecipe(exporter, ModItems.STEEL_HELMET, ModItems.STEEL);
        ModGenRecipes.offerChestplateRecipe(exporter, ModItems.STEEL_CHESTPLATE, ModItems.STEEL);
        ModGenRecipes.offerLeggingsRecipe(exporter, ModItems.STEEL_LEGGINGS, ModItems.STEEL);
        ModGenRecipes.offerBootsRecipe(exporter, ModItems.STEEL_BOOTS, ModItems.STEEL);



        //Random
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALUMINUM_GLASS,1)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .input('#', ModItems.ALUMINUM)
                .input('X', Items.GLASS)
                .criterion(hasItem(ModItems.ALUMINUM), conditionsFromItem(ModItems.ALUMINUM))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINKU,1)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .input('#', ModItems.PINKU_SHARD)
                .input('X', Items.AMETHYST_SHARD)
                .criterion(hasItem(ModItems.PINKU_SHARD), conditionsFromItem(ModItems.PINKU_SHARD))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, Identifier.of("pinku_from_pinku_shards"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Blocks.TNT,1)
                .pattern("X#X")
                .pattern("#X#")
                .pattern("X#X")
                .input('#', Items.SAND)
                .input('X', ModItems.SULFUR)
                .criterion(hasItem(ModItems.SULFUR), conditionsFromItem(ModItems.SULFUR))
                .criterion(hasItem(Items.SAND), conditionsFromItem(Items.SAND))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.TORCH, 4)
                .pattern("X")
                .pattern("#")
                .input('X', ModItems.SULFUR)
                .input('#', Items.STICK)
                .criterion(hasItem(ModItems.SULFUR), conditionsFromItem(ModItems.SULFUR))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        /*ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRUSHER, 1)
                .pattern("###")
                .pattern("X X")
                .pattern("###")
                .input('#', Items.CAST_STEEL)
                .input('X', Items.IRON_INGOT)
                .criterion(hasItem(Items.CAST_STEEL), conditionsFromItem(Items.CAST_STEEL))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.STEEL_RAIL, 16)
                .pattern("X X")
                .pattern("X#X")
                .pattern("X X")
                .input('X', ModItems.STEEL)
                .input('#', Items.STICK)
                .criterion(hasItem(ModItems.STEEL), conditionsFromItem(ModItems.STEEL))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.STEEL_POWERED_RAIL, 6)
                .pattern("X X")
                .pattern("G#G")
                .pattern("XRX")
                .input('X', ModItems.STEEL)
                .input('#', Items.STICK)
                .input('G', Items.GOLD_INGOT)
                .input('R', Items.REDSTONE)
                .criterion(hasItem(ModItems.STEEL), conditionsFromItem(ModItems.STEEL))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.STEEL_DETECTOR_RAIL, 6)
                .pattern("X X")
                .pattern("X#X")
                .pattern("XRX")
                .input('X', ModItems.STEEL)
                .input('#', Items.STONE_PRESSURE_PLATE)
                .input('R', Items.REDSTONE)
                .criterion(hasItem(ModItems.STEEL), conditionsFromItem(ModItems.STEEL))
                .criterion(hasItem(Items.STONE_PRESSURE_PLATE), conditionsFromItem(Items.STONE_PRESSURE_PLATE))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.STEEL_ACTIVATOR_RAIL, 6)
                .pattern("X#X")
                .pattern("XRX")
                .pattern("X#X")
                .input('X', ModItems.STEEL)
                .input('#', Items.STICK)
                .input('R', Items.REDSTONE_TORCH)
                .criterion(hasItem(ModItems.STEEL), conditionsFromItem(ModItems.STEEL))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Items.REDSTONE_TORCH), conditionsFromItem(Items.REDSTONE_TORCH))
                .offerTo(exporter);

         */
    }
}
