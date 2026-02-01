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

    /**
     * Creates a ModGenRecipes that will save generated recipes to the given exporter.
     *
     * @param exporter the destination RecipeOutput for generated recipes
     */
    public ModGenRecipes(RecipeOutput exporter) {
        this.exporter = exporter;
    }


    /**
    * Creates and saves a smithing recipe that upgrades the given base item into the specified result using the PINKU smithing template and PINKU item as the upgrade ingredient.
    *
    * @param exporter the destination to save the generated recipe
    * @param input    the base item to be upgraded
    * @param category the recipe category
    * @param result   the resulting item produced by the smithing upgrade
    */
    public static void offerPinkuUpgradeRecipe(RecipeOutput exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(ModItems.PINKU), category, result)
                        .unlocks("has_pinku", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PINKU)).save(exporter, getItemName(result) + "_smithing");
   }

    /**
     * Registers a smithing recipe that upgrades a base drill into the specified result using the drill upgrade template and a block of cast steel.
     *
     * The recipe is unlocked by the criterion named "has_block_of_cast_steel", which requires possession of the PINKU item.
     *
     * @param input    the base drill item to be upgraded
     * @param category the recipe category for the resulting recipe
     * @param result   the upgraded drill item produced by the smithing recipe
     */
    public static void offerDrillUpgradeRecipe(RecipeOutput exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(ModItems.BLOCK_OF_CAST_STEEL), category, result)
                .unlocks("has_block_of_cast_steel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PINKU)).save(exporter, getItemName(result) + "_smithing");
    }


    /**
     * Registers a shaped pickaxe crafting recipe that produces the specified output using the provided material.
     *
     * The recipe uses the material as the pickaxe head and sticks as the handle, unlocks when the player has the material,
     * and is saved to the supplied recipe output.
     *
     * @param output the item produced by the recipe (the pickaxe)
     * @param input  the material used as the primary ingredient for the pickaxe head
     */
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

    /**
     * Adds a shaped combat recipe for an axe using the given material and writes it to the exporter.
     *
     * The recipe uses the provided input as the primary material and a stick as the handle,
     * unlocks when the player has the input item, and displays a notification when learned.
     *
     * @param output the resulting axe item
     * @param input  the material item used to craft the axe
     */
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

    /**
     * Adds a shovel crafting recipe for the specified material to the provided exporter.
     *
     * The recipe uses the input item as the shovel head material and a stick as the handle,
     * unlocks when the player has the input item, displays a recipe notification, and is saved to the exporter.
     *
     * @param output the resulting shovel item
     * @param input  the material used as the shovel head
     */
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

    /**
     * Creates and saves a shaped sword crafting recipe that uses the given material and a stick as the handle.
     *
     * The recipe pattern is:
     *  " # "
     *  " # "
     *  " S "
     * and it unlocks when the player has the input material.
     *
     * @param exporter the destination to save the generated recipe
     * @param output   the resulting sword item
     * @param input    the primary material used for the sword (also used as the unlock criterion)
     */
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

    /**
     * Adds a shaped crafting recipe for a hoe that uses the specified material and a stick.
     *
     * The recipe is placed in the Tools category, unlocks when the player has the material item,
     * shows an on-screen notification when unlocked, and is saved to the provided exporter.
     *
     * @param exporter destination to save the generated recipe
     * @param output   the resulting hoe item
     * @param input    the material item used to craft the hoe
     */
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

    /**
     * Adds a shaped helmet crafting recipe that produces the given output using the given input material.
     *
     * The recipe is categorized as combat, unlocks when the player has the input material, shows a crafting
     * notification, and is written to the provided exporter.
     *
     * @param output the helmet item produced by the recipe
     * @param input  the material required to craft the helmet
     */
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

    /**
     * Adds a shaped crafting recipe for a chestplate using the specified material.
     *
     * The recipe produces one instance of `output` in the COMBAT category using `input`
     * arranged in the standard chestplate pattern. It is unlocked when the player
     * has `input`, displays a recipe unlock notification, and is saved to `exporter`.
     *
     * @param output the chestplate item produced by the recipe
     * @param input  the material item used to craft the chestplate
     */
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

    /**
     * Adds a shaped crafting recipe that produces the specified leggings item from the given material.
     *
     * The recipe is categorized as combat, unlocks when the player has the input material, and will show
     * an on-screen notification when learned.
     *
     * @param output the leggings item produced by the recipe
     * @param input  the material used to craft the leggings
     */
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

    /**
     * Adds a shaped crafting recipe that produces the specified boots from the given material.
     *
     * The recipe uses a 3x3 pattern with an empty top row and two rows of material at the left and right
     * ("   ", "# #", "# #"), unlocks when the player has the material, and shows a crafting notification.
     *
     * @param output the boots item produced by the recipe
     * @param input  the material used to craft the boots
     */
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