package anya.pizza.houseki.util;

import anya.pizza.houseki.item.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.core.HolderGetter;

import static net.minecraft.data.recipes.RecipeProvider.*;

public class ModGenRecipes {
    protected final RecipeOutput exporter;
    private static final HolderGetter<Item> itemLookup = null;

    public ModGenRecipes(RecipeOutput exporter) {
        this.exporter = exporter;
    }


    //Smithing Templates
    public static void offerPinkuUpgradeRecipe(RecipeOutput exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(ModItems.PINKU), category, result)
                        .unlocks("has_pinku", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PINKU)).save(exporter, getItemName(result) + "_smithing");
   }

    public static void offerDrillUpgradeRecipe(RecipeOutput exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(ModItems.BLOCK_OF_CAST_STEEL), category, result)
                .unlocks("has_block_of_cast_steel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PINKU)).save(exporter, getItemName(result) + "_smithing");
    }


    //Tool Recipes
    public static void offerPickaxeRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, output, 1)
                .define('#', input)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerAxeRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output, 1)
                .define('#', input)
                .define('S', Items.STICK)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerShovelRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, output, 1)
                .define('#', input)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerSwordRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output, 1)
                .define('#', input)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" S ")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerHoeRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.TOOLS, output, 1)
                .define('#', input)
                .define('S', Items.STICK)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    //Armor
    public static void offerHelmetRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output, 1)
                .define('#', input)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerChestplateRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output, 1)
                .define('#', input)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerLeggingsRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output, 1)
                .define('#', input)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

    public static void offerBootsRecipe(RecipeOutput exporter, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(itemLookup, RecipeCategory.COMBAT, output, 1)
                .define('#', input)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(input), inventoryTrigger((ItemPredicate.Builder) input))
                .showNotification(true)
                .save(exporter);
    }

}
