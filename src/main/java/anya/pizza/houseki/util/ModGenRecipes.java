package anya.pizza.houseki.util;

import anya.pizza.houseki.item.ModItems;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;

import static net.minecraft.data.recipe.RecipeGenerator.*;

public class ModGenRecipes {
    protected final RecipeExporter exporter;
    private static final RegistryEntryLookup<Item> itemLookup = null;

    public ModGenRecipes(RecipeExporter exporter) {
        this.exporter = exporter;
    }


    //Smithing Templates
    public static void offerPinkuUpgradeRecipe(RecipeExporter exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.PINKU), category, result)
                        .criterion("has_pinku", conditionsFromItemPredicates()).offerTo(exporter, getItemPath(result) + "_smithing");
   }

    public static void offerDrillUpgradeRecipe(RecipeExporter exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.BLOCK_OF_CAST_STEEL), category, result)
                .criterion("has_block_of_cast_steel", conditionsFromItemPredicates()).offerTo(exporter, getItemPath(result) + "_smithing");
    }


    //Tool Recipes
    public static void offerPickaxeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerAxeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerShovelRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerSwordRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerHoeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    //Armor
    public static void offerHelmetRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerChestplateRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerLeggingsRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerBootsRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .criterion(hasItem(input), conditionsFromItemPredicates())
                .showNotification(true)
                .offerTo(exporter);
    }

}
