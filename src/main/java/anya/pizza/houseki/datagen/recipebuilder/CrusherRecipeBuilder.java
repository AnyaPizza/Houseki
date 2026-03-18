package anya.pizza.houseki.datagen.recipebuilder;

import anya.pizza.houseki.recipe.CrusherRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class CrusherRecipeBuilder implements CraftingRecipeJsonBuilder {
    private final Ingredient input;
    private final ItemStack output;
    private final int crushingTime;
    private Optional<ItemStack> auxiliaryOutput = Optional.empty();
    private double auxiliaryChance = 1;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    public String group;

    public CrusherRecipeBuilder(Ingredient input, ItemStack output, int crushingTime) {
        this.input = input;
        this.output = output;
        this.crushingTime = crushingTime;
    }

    public static CrusherRecipeBuilder create(Ingredient input, ItemStack output, int crushingTime) {
        return new CrusherRecipeBuilder(input, output, crushingTime);
    }

    public CrusherRecipeBuilder auxiliary(ItemStack stack) {
        this.auxiliaryOutput = Optional.of(stack);
        return this;
    }

    public CrusherRecipeBuilder chance(double chance) {
        this.auxiliaryChance = chance;
        return this;
    }

    /**
     * Builds an advancement for unlocking the given recipe and exports the constructed crusher recipe.
     *
     * The method creates an advancement that uses an "has_the_recipe" criterion, sets the recipe as the advancement reward,
     * applies any additional criteria added to this builder, constructs a CrusherRecipe from the builder state, and passes
     * both recipe and advancement to the provided exporter.
     *
     * @param exporter  the RecipeExporter that will receive the finished recipe and its associated advancement
     * @param recipeKey the registry key that identifies the recipe to export; used in the advancement reward and as the recipe id
     */
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(advancement::criterion);
        CrusherRecipe recipe = new CrusherRecipe(input, output, crushingTime, auxiliaryOutput, auxiliaryChance);
        exporter.accept(recipeKey, recipe, advancement.build(recipeKey.getValue()));
    }

    /**
     * Adds a named advancement criterion to this recipe builder.
     *
     * @param name      the unique name for the criterion within the recipe's advancement
     * @param criterion the advancement criterion to add
     * @return          this builder instance for method chaining
     */
    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return Items.AIR;
    }
}