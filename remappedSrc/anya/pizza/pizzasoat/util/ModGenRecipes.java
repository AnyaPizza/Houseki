package anya.pizza.houseki.util;

import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.ModRecipes;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;

import static net.minecraft.data.server.recipe.RecipeProvider.*;

public class ModGenRecipes {

    //Pinku Smithing Template
    public static void offerPinkuUpgradeRecipe(RecipeExporter exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.PINKU), category, result)
                .criterion("has_pinku", conditionsFromItem(ModItems.PINKU))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    //Tool Recipes
    public static void offerPickaxeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerAxeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerShovelRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerSwordRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerHoeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output, 1)
                .input('#', input)
                .input('S', Items.STICK)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    //Armor
    public static void offerHelmetRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerChestplateRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerLeggingsRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    public static void offerBootsRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('#', input)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true)
                .offerTo(exporter);
    }

    //Misc
    public static CraftingRecipeJsonBuilder DoorRecipe(ItemConvertible output, ItemConvertible input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 3)
                .input('#', input)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true);
    }
    public static CraftingRecipeJsonBuilder TrapdoorRecipe(ItemConvertible output, ItemConvertible input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 2)
                .input('#', input)
                .pattern("###")
                .pattern("###")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true);
    }
    public static CraftingRecipeJsonBuilder StairsRecipe(ItemConvertible output, ItemConvertible input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .input('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .criterion(hasItem(input), conditionsFromItem(input))
                .showNotification(true);
    }
}
