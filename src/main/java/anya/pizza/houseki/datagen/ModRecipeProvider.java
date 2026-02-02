package anya.pizza.houseki.datagen;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.datagen.recipebuilder.CrusherRecipeBuilder;
import anya.pizza.houseki.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static anya.pizza.houseki.util.ModGenRecipes.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Create a RecipeProvider that defines and registers all Houseki mod recipes used for data generation.
     *
     * @param wrapperLookup a registry lookup provider used by the generated recipes
     * @param recipeExporter the output sink that will receive generated recipe data
     * @return a RecipeProvider instance whose buildRecipes method emits the mod's recipe definitions
     */
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {
            @Override
            public void buildRecipes() {
                List<ItemLike> PINKU_SMELTABLES = List.of(ModBlocks.PINKU_ORE);
                List<ItemLike> RAINBOW_PYRITE_SMELTABLES = List.of(ModBlocks.RAINBOW_PYRITE_ORE, ModBlocks.SANDSTONE_RAINBOW_PYRITE_ORE, ModBlocks.BAUXITE_RAINBOW_PYRITE_ORE);
                List<ItemLike> ALUMINUM_SMELTABLES = List.of(ModItems.CRUSHED_BAUXITE);
                List<ItemLike> SCHEELITE_SMELTABLES = List.of(ModBlocks.SCHEELITE_ORE);
                List<ItemLike> WOLFRAMITE_SMELTABLES = List.of(ModBlocks.WOLFRAMITE_ORE);
                List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                List<ItemLike> NEPHRITE_SMELTABLES = List.of(ModBlocks.NEPHRITE_ORE);
                List<ItemLike> JADEITE_SMELTABLES = List.of(ModBlocks.JADEITE_ORE);
                List<ItemLike> PLATINUM_SMELTABLES = List.of(ModBlocks.PLATINUM_ORE, ModBlocks.DEEPSLATE_PLATINUM_ORE);
                List<ItemLike> SULFUR_SMELTABLES = List.of(ModBlocks.SULFUR_ORE, ModBlocks.BLACKSTONE_SULFUR_ORE);
                List<ItemLike> CRUDE_IRON_SMELTABLES = List.of(Items.IRON_INGOT);
                List<ItemLike> STEEL_SMELTABLES = List.of(ModItems.CRUDE_IRON);
                List<ItemLike> CAST_STEEL_SMELTABLES = List.of(ModItems.STEEL);

                //Crushing recipes with auxiliary output
                CrusherRecipeBuilder.create(Ingredient.of(ModBlocks.BAUXITE), ModItems.CRUSHED_BAUXITE, 250)
                        .chance(0.5)
                        .auxiliary(Items.CLAY)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "bauxite_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(Items.SANDSTONE), Items.SAND, 100)
                        .chance(0.2)
                        .auxiliary(Items.CALCITE)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "sandstone_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(ModItems.WOLFRAMITE), ModItems.TUNGSTEN, 250)
                        .chance(0.5)
                        .auxiliary(Items.QUARTZ)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "wolframite_crushing"))
                        );

                // Crushing recipes WITHOUT an auxiliary output
                CrusherRecipeBuilder.create(Ingredient.of(Items.COBBLESTONE), Items.GRAVEL, 100)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "cobblestone_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(Items.STONE), Items.GRAVEL, 100)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "stone_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(ModBlocks.PLATINUM_ORE), ModItems.PLATINUM, 250)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "platinum_ore_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(ModBlocks.DEEPSLATE_PLATINUM_ORE), ModItems.PLATINUM, 250)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "deepslate_platinum_ore_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(Items.COPPER_INGOT), ModItems.PLATINUM_NUGGET, 250)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "copper_ingot_crushing"))
                        );

                CrusherRecipeBuilder.create(Ingredient.of(ModItems.SCHEELITE), ModItems.TUNGSTEN, 250)
                        .save(
                                output, 
                                ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath("houseki", "scheelite_crushing"))
                        );

                //Smithing Upgrades
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.PINKU_HELMET);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.PINKU_CHESTPLATE);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.PINKU_LEGGINGS);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.PINKU_BOOTS);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.PINKU_PICKAXE);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_AXE, RecipeCategory.TOOLS, ModItems.PINKU_AXE);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.PINKU_SHOVEL);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.PINKU_SWORD);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.PINKU_HOE);
                offerPinkuUpgradeRecipe(output, Items.NETHERITE_NAUTILUS_ARMOR, RecipeCategory.COMBAT, ModItems.PINKU_NAUTILUS_ARMOR);
                offerDrillUpgradeRecipe(output, ModItems.SIMPLE_DRILL_HEAD, RecipeCategory.TOOLS, ModItems.ENHANCED_DRILL_HEAD);
                offerDrillUpgradeRecipe(output, ModItems.SIMPLE_TUNGSTEN_DRILL, RecipeCategory.TOOLS, ModItems.ENHANCED_TUNGSTEN_DRILL);
                offerDrillUpgradeRecipe(output, ModItems.ENHANCED_DRILL_HEAD, RecipeCategory.TOOLS, ModItems.ADVANCED_DRILL_HEAD);
                offerDrillUpgradeRecipe(output, ModItems.ENHANCED_TUNGSTEN_DRILL, RecipeCategory.TOOLS, ModItems.ADVANCED_TUNGSTEN_DRILL);
                offerDrillUpgradeRecipe(output, ModItems.ADVANCED_DRILL_HEAD, RecipeCategory.TOOLS, ModItems.PREMIUM_DRILL_HEAD);
                offerDrillUpgradeRecipe(output, ModItems.ADVANCED_TUNGSTEN_DRILL, RecipeCategory.TOOLS, ModItems.PREMIUM_TUNGSTEN_DRILL);
                offerDrillUpgradeRecipe(output, ModItems.SIMPLE_DIAMOND_DRILL, RecipeCategory.TOOLS, ModItems.ENHANCED_DIAMOND_DRILL);
                offerDrillUpgradeRecipe(output, ModItems.ENHANCED_DIAMOND_DRILL, RecipeCategory.TOOLS, ModItems.ADVANCED_DIAMOND_DRILL);
                offerDrillUpgradeRecipe(output, ModItems.ADVANCED_DIAMOND_DRILL, RecipeCategory.TOOLS, ModItems.PREMIUM_DIAMOND_DRILL);


                //Block Reversing Recipes
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PINKU, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_PINKU);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAINBOW_PYRITE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_RAINBOW_PYRITE);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.TUNGSTEN, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_TUNGSTEN_B);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.ALUMINUM, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_ALUMINUM);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SAPPHIRE);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.JADEITE, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_JADEITE);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PLATINUM, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_PLATINUM);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.SULFUR, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SULFUR);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_STEEL);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.CAST_STEEL, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_CAST_STEEL);


                //Smelting Recipes
                oreSmelting(ALUMINUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ALUMINUM, 0.5f, 200, "aluminum");
                oreSmelting(PINKU_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.PINKU_SHARD, 0.5f, 250, "pinku");
                oreSmelting(RAINBOW_PYRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.RAINBOW_PYRITE, 0.5f, 200, "rainbow_pyrite");
                oreSmelting(SCHEELITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SCHEELITE, 0.5f, 200, "scheelite");
                oreSmelting(WOLFRAMITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.WOLFRAMITE, 0.5f, 200, "wolframite");
                oreSmelting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SAPPHIRE, 0.5f, 250, "sapphire");
                oreSmelting(NEPHRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.NEPHRITE, 0.5f, 200, "nephrite");
                oreSmelting(JADEITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.JADEITE, 0.5f, 250, "jadeite");
                oreSmelting(PLATINUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.PLATINUM, 0.5f, 200, "platinum");
                oreSmelting(SULFUR_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SULFUR, 0.5f, 200, "sulfur");

                //Blasting Recipes
                oreBlasting(ALUMINUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ALUMINUM, 0.5f, 100, "aluminum");
                oreBlasting(PINKU_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.PINKU_SHARD, 0.5f, 150, "pinku");
                oreBlasting(RAINBOW_PYRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.RAINBOW_PYRITE, 0.5f, 100, "rainbow_pyrite");
                oreBlasting(SCHEELITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SCHEELITE, 0.5f, 100, "scheelite");
                oreBlasting(WOLFRAMITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.WOLFRAMITE, 0.5f, 100, "wolframite");
                oreBlasting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SAPPHIRE, 0.5f, 150, "sapphire");
                oreBlasting(NEPHRITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.NEPHRITE, 0.5f, 100, "nephrite");
                oreBlasting(JADEITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.JADEITE, 0.5f, 150, "jadeite");
                oreBlasting(PLATINUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.PLATINUM, 0.5f, 100, "platinum");
                oreBlasting(SULFUR_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SULFUR, 0.5f, 100, "sulfur");
                oreBlasting(CRUDE_IRON_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.CRUDE_IRON, 0.5f, 100, "crude_iron");
                oreBlasting(STEEL_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.STEEL, 0.5f, 120, "steel");
                oreBlasting(CAST_STEEL_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.CAST_STEEL, 0.5f, 120, "cast_steel");

                //Pickaxe Recipes
                offerPickaxeRecipe(output, ModItems.RAINBOW_PYRITE_PICKAXE, ModItems.RAINBOW_PYRITE);
                offerPickaxeRecipe(output, ModItems.ALUMINUM_PICKAXE, ModItems.ALUMINUM);
                offerPickaxeRecipe(output, ModItems.CAST_STEEL_PICKAXE, ModItems.CAST_STEEL);
                offerPickaxeRecipe(output, ModItems.JADEITE_PICKAXE, ModItems.JADEITE);
                offerPickaxeRecipe(output, ModItems.NEPHRITE_PICKAXE, ModItems.NEPHRITE);
                offerPickaxeRecipe(output, ModItems.PLATINUM_PICKAXE, ModItems.PLATINUM);
                offerPickaxeRecipe(output, ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE);
                offerPickaxeRecipe(output, ModItems.STEEL_PICKAXE, ModItems.STEEL);
                offerPickaxeRecipe(output, ModItems.TUNGSTEN_PICKAXE, ModItems.TUNGSTEN);

                //Axe Recipes
                offerAxeRecipe(output, ModItems.RAINBOW_PYRITE_AXE, ModItems.RAINBOW_PYRITE);
                offerAxeRecipe(output, ModItems.ALUMINUM_AXE, ModItems.ALUMINUM);
                offerAxeRecipe(output, ModItems.CAST_STEEL_AXE, ModItems.CAST_STEEL);
                offerAxeRecipe(output, ModItems.JADEITE_AXE, ModItems.JADEITE);
                offerAxeRecipe(output, ModItems.NEPHRITE_AXE, ModItems.NEPHRITE);
                offerAxeRecipe(output, ModItems.PLATINUM_AXE, ModItems.PLATINUM);
                offerAxeRecipe(output, ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE);
                offerAxeRecipe(output, ModItems.STEEL_AXE, ModItems.STEEL);
                offerAxeRecipe(output, ModItems.TUNGSTEN_AXE, ModItems.TUNGSTEN);

                //Shovel Recipes
                offerShovelRecipe(output, ModItems.RAINBOW_PYRITE_SHOVEL, ModItems.RAINBOW_PYRITE);
                offerShovelRecipe(output, ModItems.ALUMINUM_SHOVEL, ModItems.ALUMINUM);
                offerShovelRecipe(output, ModItems.CAST_STEEL_SHOVEL, ModItems.CAST_STEEL);
                offerShovelRecipe(output, ModItems.JADEITE_SHOVEL, ModItems.JADEITE);
                offerShovelRecipe(output, ModItems.NEPHRITE_SHOVEL, ModItems.NEPHRITE);
                offerShovelRecipe(output, ModItems.PLATINUM_SHOVEL, ModItems.PLATINUM);
                offerShovelRecipe(output, ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE);
                offerShovelRecipe(output, ModItems.STEEL_SHOVEL, ModItems.STEEL);
                offerShovelRecipe(output, ModItems.TUNGSTEN_SHOVEL, ModItems.TUNGSTEN);

                //Sword Recipes
                offerSwordRecipe(output, ModItems.RAINBOW_PYRITE_SWORD, ModItems.RAINBOW_PYRITE);
                offerSwordRecipe(output, ModItems.ALUMINUM_SWORD, ModItems.ALUMINUM);
                offerSwordRecipe(output, ModItems.CAST_STEEL_SWORD, ModItems.CAST_STEEL);
                offerSwordRecipe(output, ModItems.JADEITE_SWORD, ModItems.JADEITE);
                offerSwordRecipe(output, ModItems.NEPHRITE_SWORD, ModItems.NEPHRITE);
                offerSwordRecipe(output, ModItems.PLATINUM_SWORD, ModItems.PLATINUM);
                offerSwordRecipe(output, ModItems.SAPPHIRE_SWORD, ModItems.SAPPHIRE);
                offerSwordRecipe(output, ModItems.STEEL_SWORD, ModItems.STEEL);
                offerSwordRecipe(output, ModItems.TUNGSTEN_SWORD, ModItems.TUNGSTEN);

                //Hoe Recipes
                offerHoeRecipe(output, ModItems.RAINBOW_PYRITE_HOE, ModItems.RAINBOW_PYRITE);
                offerHoeRecipe(output, ModItems.ALUMINUM_HOE, ModItems.ALUMINUM);
                offerHoeRecipe(output, ModItems.CAST_STEEL_HOE, ModItems.CAST_STEEL);
                offerHoeRecipe(output, ModItems.JADEITE_HOE, ModItems.JADEITE);
                offerHoeRecipe(output, ModItems.NEPHRITE_HOE, ModItems.NEPHRITE);
                offerHoeRecipe(output, ModItems.PLATINUM_HOE, ModItems.PLATINUM);
                offerHoeRecipe(output, ModItems.SAPPHIRE_HOE, ModItems.SAPPHIRE);
                offerHoeRecipe(output, ModItems.STEEL_HOE, ModItems.STEEL);
                offerHoeRecipe(output, ModItems.TUNGSTEN_HOE, ModItems.TUNGSTEN);

                //Armor
                offerHelmetRecipe(output, ModItems.RAINBOW_PYRITE_HELMET, ModItems.RAINBOW_PYRITE);
                offerChestplateRecipe(output, ModItems.RAINBOW_PYRITE_CHESTPLATE, ModItems.RAINBOW_PYRITE);
                offerLeggingsRecipe(output, ModItems.RAINBOW_PYRITE_LEGGINGS, ModItems.RAINBOW_PYRITE);
                offerBootsRecipe(output, ModItems.RAINBOW_PYRITE_BOOTS, ModItems.RAINBOW_PYRITE);
                offerHelmetRecipe(output, ModItems.ALUMINUM_HELMET, ModItems.ALUMINUM);
                offerChestplateRecipe(output, ModItems.ALUMINUM_CHESTPLATE, ModItems.ALUMINUM);
                offerLeggingsRecipe(output, ModItems.ALUMINUM_LEGGINGS, ModItems.ALUMINUM);
                offerBootsRecipe(output, ModItems.ALUMINUM_BOOTS, ModItems.ALUMINUM);
                offerHelmetRecipe(output, ModItems.CAST_STEEL_HELMET, ModItems.CAST_STEEL);
                offerChestplateRecipe(output, ModItems.CAST_STEEL_CHESTPLATE, ModItems.CAST_STEEL);
                offerLeggingsRecipe(output, ModItems.CAST_STEEL_LEGGINGS, ModItems.CAST_STEEL);
                offerBootsRecipe(output, ModItems.CAST_STEEL_BOOTS, ModItems.CAST_STEEL);
                offerHelmetRecipe(output, ModItems.JADEITE_HELMET, ModItems.JADEITE);
                offerChestplateRecipe(output, ModItems.JADEITE_CHESTPLATE, ModItems.JADEITE);
                offerLeggingsRecipe(output, ModItems.JADEITE_LEGGINGS, ModItems.JADEITE);
                offerBootsRecipe(output, ModItems.JADEITE_BOOTS, ModItems.JADEITE);
                offerHelmetRecipe(output, ModItems.NEPHRITE_HELMET, ModItems.NEPHRITE);
                offerChestplateRecipe(output, ModItems.NEPHRITE_CHESTPLATE, ModItems.NEPHRITE);
                offerLeggingsRecipe(output, ModItems.NEPHRITE_LEGGINGS, ModItems.NEPHRITE);
                offerBootsRecipe(output, ModItems.NEPHRITE_BOOTS, ModItems.NEPHRITE);
                offerHelmetRecipe(output, ModItems.PLATINUM_HELMET, ModItems.PLATINUM);
                offerChestplateRecipe(output, ModItems.PLATINUM_CHESTPLATE, ModItems.PLATINUM);
                offerLeggingsRecipe(output, ModItems.PLATINUM_LEGGINGS, ModItems.PLATINUM);
                offerBootsRecipe(output, ModItems.PLATINUM_BOOTS, ModItems.PLATINUM);
                offerHelmetRecipe(output, ModItems.SAPPHIRE_HELMET, ModItems.SAPPHIRE);
                offerChestplateRecipe(output, ModItems.SAPPHIRE_CHESTPLATE, ModItems.SAPPHIRE);
                offerLeggingsRecipe(output, ModItems.SAPPHIRE_LEGGINGS, ModItems.SAPPHIRE);
                offerBootsRecipe(output, ModItems.SAPPHIRE_BOOTS, ModItems.SAPPHIRE);
                offerHelmetRecipe(output, ModItems.STEEL_HELMET, ModItems.STEEL);
                offerChestplateRecipe(output, ModItems.STEEL_CHESTPLATE, ModItems.STEEL);
                offerLeggingsRecipe(output, ModItems.STEEL_LEGGINGS, ModItems.STEEL);
                offerBootsRecipe(output, ModItems.STEEL_BOOTS, ModItems.STEEL);
                offerHelmetRecipe(output, ModItems.TUNGSTEN_HELMET, ModItems.TUNGSTEN);
                offerChestplateRecipe(output, ModItems.TUNGSTEN_CHESTPLATE, ModItems.TUNGSTEN);
                offerLeggingsRecipe(output, ModItems.TUNGSTEN_LEGGINGS, ModItems.TUNGSTEN);
                offerBootsRecipe(output, ModItems.TUNGSTEN_BOOTS, ModItems.TUNGSTEN);

                //Stonecutting Recipes
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LIMESTONE, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_STAIRS, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_SLAB, ModBlocks.LIMESTONE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_WALL, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_STAIRS, ModBlocks.LIMESTONE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE, 1);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SLATE, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILES, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_STAIRS, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_SLAB, ModBlocks.SLATE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_WALL, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_STAIRS, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.SLATE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_STAIRS, ModBlocks.SLATE, 1);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, ModBlocks.SLATE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, ModBlocks.SLATE, 1);

                //Crafting Recipes
                chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LIMESTONE, ModBlocks.LIMESTONE_SLAB);
                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, ModBlocks.LIMESTONE);
                polished(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE_BRICKS);
                chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SLATE, ModBlocks.SLATE_SLAB);
                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILES, ModBlocks.SLATE);
                polished(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE, ModBlocks.SLATE_TILES);
                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BAUXITE, ModItems.CRUSHED_BAUXITE);
                stainedGlassPaneFromStainedGlass(ModBlocks.ALUMINUM_GLASS_PANE, ModBlocks.ALUMINUM_GLASS);

                //Slab Recipes
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_SLAB, ModBlocks.LIMESTONE_BRICKS);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.POLISHED_LIMESTONE);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_SLAB, ModBlocks.SLATE);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_SLAB, ModBlocks.SLATE_TILES);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_SLAB, ModBlocks.POLISHED_SLATE);

                //Wall Recipes
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICK_WALL, ModBlocks.LIMESTONE_BRICKS);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.POLISHED_LIMESTONE);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_WALL, ModBlocks.SLATE);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLATE_TILE_WALL, ModBlocks.SLATE_TILES);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SLATE_WALL, ModBlocks.POLISHED_SLATE);

                //Stair Recipes
                stairBuilder(ModBlocks.LIMESTONE_STAIRS, Ingredient.of(ModBlocks.LIMESTONE)).unlockedBy(getHasName(ModBlocks.LIMESTONE), has(ModBlocks.LIMESTONE)).save(output);
                stairBuilder(ModBlocks.LIMESTONE_BRICK_STAIRS, Ingredient.of(ModBlocks.LIMESTONE_BRICKS)).unlockedBy(getHasName(ModBlocks.LIMESTONE_BRICKS), has(ModBlocks.LIMESTONE_BRICKS)).save(output);
                stairBuilder(ModBlocks.POLISHED_LIMESTONE_STAIRS, Ingredient.of(ModBlocks.POLISHED_LIMESTONE)).unlockedBy(getHasName(ModBlocks.POLISHED_LIMESTONE), has(ModBlocks.POLISHED_LIMESTONE)).save(output);
                stairBuilder(ModBlocks.SLATE_STAIRS, Ingredient.of(ModBlocks.SLATE)).unlockedBy(getHasName(ModBlocks.SLATE), has(ModBlocks.SLATE)).save(output);
                stairBuilder(ModBlocks.SLATE_TILE_STAIRS, Ingredient.of(ModBlocks.SLATE_TILES)).unlockedBy(getHasName(ModBlocks.SLATE_TILES), has(ModBlocks.SLATE_TILES)).save(output);
                stairBuilder(ModBlocks.POLISHED_SLATE_STAIRS, Ingredient.of(ModBlocks.POLISHED_SLATE)).unlockedBy(getHasName(ModBlocks.POLISHED_SLATE), has(ModBlocks.POLISHED_SLATE)).save(output);

                //Door Recipes
                doorBuilder(ModBlocks.ALUMINUM_DOOR, Ingredient.of(ModItems.ALUMINUM)).unlockedBy(getHasName(ModItems.ALUMINUM), has(ModItems.ALUMINUM)).save(output);
                trapdoorBuilder(ModBlocks.ALUMINUM_TRAPDOOR, Ingredient.of(ModItems.ALUMINUM)).unlockedBy(getHasName(ModItems.ALUMINUM), has(ModItems.ALUMINUM)).save(output);

               //Drills
                shapeless(RecipeCategory.TOOLS, ModItems.SIMPLE_TUNGSTEN_DRILL, 1)
                        .requires(ModItems.TUNGSTEN_DRILL_BIT)
                        .requires(ModItems.SIMPLE_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.TUNGSTEN_DRILL_BIT), has(ModItems.TUNGSTEN_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.SIMPLE_DRILL_HEAD), has(ModItems.SIMPLE_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.ENHANCED_TUNGSTEN_DRILL, 1)
                        .requires(ModItems.TUNGSTEN_DRILL_BIT)
                        .requires(ModItems.ENHANCED_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.TUNGSTEN_DRILL_BIT), has(ModItems.TUNGSTEN_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.ENHANCED_DRILL_HEAD), has(ModItems.ENHANCED_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.ADVANCED_TUNGSTEN_DRILL, 1)
                        .requires(ModItems.TUNGSTEN_DRILL_BIT)
                        .requires(ModItems.ADVANCED_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.TUNGSTEN_DRILL_BIT), has(ModItems.TUNGSTEN_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.ADVANCED_DRILL_HEAD), has(ModItems.ADVANCED_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.PREMIUM_TUNGSTEN_DRILL, 1)
                        .requires(ModItems.TUNGSTEN_DRILL_BIT)
                        .requires(ModItems.PREMIUM_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.TUNGSTEN_DRILL_BIT), has(ModItems.TUNGSTEN_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.PREMIUM_DRILL_HEAD), has(ModItems.PREMIUM_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.SIMPLE_DIAMOND_DRILL, 1)
                        .requires(ModItems.DIAMOND_DRILL_BIT)
                        .requires(ModItems.SIMPLE_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.DIAMOND_DRILL_BIT), has(ModItems.DIAMOND_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.SIMPLE_DRILL_HEAD), has(ModItems.SIMPLE_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.ENHANCED_DIAMOND_DRILL, 1)
                        .requires(ModItems.DIAMOND_DRILL_BIT)
                        .requires(ModItems.ENHANCED_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.DIAMOND_DRILL_BIT), has(ModItems.DIAMOND_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.ENHANCED_DRILL_HEAD), has(ModItems.ENHANCED_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.ADVANCED_DIAMOND_DRILL, 1)
                        .requires(ModItems.DIAMOND_DRILL_BIT)
                        .requires(ModItems.ADVANCED_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.DIAMOND_DRILL_BIT), has(ModItems.DIAMOND_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.ADVANCED_DRILL_HEAD), has(ModItems.ADVANCED_DRILL_HEAD))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.PREMIUM_DIAMOND_DRILL, 1)
                        .requires(ModItems.DIAMOND_DRILL_BIT)
                        .requires(ModItems.PREMIUM_DRILL_HEAD)
                        .unlockedBy(getHasName(ModItems.DIAMOND_DRILL_BIT), has(ModItems.DIAMOND_DRILL_BIT))
                        .unlockedBy(getHasName(ModItems.PREMIUM_DRILL_HEAD), has(ModItems.PREMIUM_DRILL_HEAD))
                        .save(output);


                //Random
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALUMINUM_GLASS,1)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .define('#', ModItems.ALUMINUM).define('X', Items.GLASS).unlockedBy(getHasName(ModItems.ALUMINUM), has(ModItems.ALUMINUM))
                        .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);

                shaped(RecipeCategory.MISC, ModItems.PINKU,1)
                        .pattern(" # ")
                        .pattern("#X#")
                        .pattern(" # ")
                        .define('#', ModItems.PINKU_SHARD).define('X', Items.AMETHYST_SHARD)
                        .unlockedBy(getHasName(ModItems.PINKU_SHARD), has(ModItems.PINKU_SHARD)).unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                        .save(output, ResourceKey.create(Registries.RECIPE, Identifier.parse("pinku_from_pinku_shards")));

                shaped(RecipeCategory.MISC, Blocks.TNT,1)
                        .pattern("X#X")
                        .pattern("#X#")
                        .pattern("X#X")
                        .define('#', Items.SAND).define('X', ModItems.SULFUR)
                        .unlockedBy(getHasName(ModItems.SULFUR), has(ModItems.SULFUR)).unlockedBy(getHasName(Items.SAND), has(Items.SAND)).save(output);

                shaped(RecipeCategory.MISC, Items.TORCH, 4)
                        .pattern("X")
                        .pattern("#")
                        .define('X', ModItems.SULFUR).define('#', Items.STICK)
                        .unlockedBy(getHasName(ModItems.SULFUR), has(ModItems.SULFUR)).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(output);

                shaped(RecipeCategory.MISC, ModBlocks.CRUSHER, 1)
                        .pattern("###")
                        .pattern("X X")
                        .pattern("###")
                        .define('#', ModItems.CAST_STEEL).define('X', Items.IRON_INGOT)
                        .unlockedBy(getHasName(ModItems.CAST_STEEL), has(ModItems.CAST_STEEL)).unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(output);

                shaped(RecipeCategory.MISC, ModItems.TUNGSTEN_DRILL_BIT, 1)
                        .pattern(" # ")
                        .pattern("#X#")
                        .define('#', ModItems.TUNGSTEN).define('X', ModBlocks.BLOCK_OF_TUNGSTEN_B)
                        .unlockedBy(getHasName(ModItems.TUNGSTEN), has(ModItems.TUNGSTEN)).unlockedBy(getHasName(ModBlocks.BLOCK_OF_TUNGSTEN_B), has(ModBlocks.BLOCK_OF_TUNGSTEN_B)).save(output);

                shaped(RecipeCategory.MISC, ModItems.SIMPLE_DRILL_HEAD, 1)
                        .pattern(" X ")
                        .pattern("#R#")
                        .pattern("###")
                        .define('X', ModItems.CAST_STEEL).define('#', ModBlocks.BLOCK_OF_CAST_STEEL).define('R', Items.REDSTONE).unlockedBy(getHasName(ModItems.CAST_STEEL), has(ModItems.CAST_STEEL))
                        .unlockedBy(getHasName(ModBlocks.BLOCK_OF_CAST_STEEL), has(ModBlocks.BLOCK_OF_CAST_STEEL)).unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE)).save(output);

                shaped(RecipeCategory.MISC, ModItems.DIAMOND_DRILL_BIT, 1)
                        .pattern(" # ")
                        .pattern("###")
                        .define('#', Blocks.DIAMOND_BLOCK).unlockedBy(getHasName(Blocks.DIAMOND_BLOCK), has(Blocks.DIAMOND_BLOCK)).save(output);

                shapeless(RecipeCategory.MISC, ModItems.PLATINUM_NUGGET, 9)
                        .requires(ModItems.PLATINUM)
                        .unlockedBy(getHasName(ModItems.PLATINUM), has(ModItems.PLATINUM))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.PLATINUM, 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ModItems.PLATINUM_NUGGET).unlockedBy(getHasName(ModItems.PLATINUM_NUGGET), has(ModItems.PLATINUM_NUGGET)).save(output, String.valueOf(Identifier.fromNamespaceAndPath("houseki", "platinum_from_platinum_nuggets")));

                //Smithing Template Dupe Recipe
                shaped(RecipeCategory.MISC, ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("#X#")
                        .pattern("#U#")
                        .pattern("###")
                        .define('#', ModItems.CAST_STEEL).define('X', ModBlocks.BLOCK_OF_CAST_STEEL).define('U', ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE)
                        .unlockedBy(getHasName(ModItems.CAST_STEEL), has(ModItems.CAST_STEEL)).unlockedBy(getHasName(ModBlocks.BLOCK_OF_CAST_STEEL), has(ModBlocks.BLOCK_OF_CAST_STEEL)).unlockedBy(getHasName(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE), has(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Houseki Recipes";
    }
}