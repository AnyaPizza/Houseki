package anya.pizza.houseki.datagen.recipebuilder;

import anya.pizza.houseki.recipe.FoundryRecipe;
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

import java.util.LinkedHashMap;
import java.util.Map;

public class FoundryRecipeBuilder implements CraftingRecipeJsonBuilder {
    private final Ingredient input;
    private final ItemStack output;
    private final int meltTime;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    public String group;

    /**
     * Creates a FoundryRecipeBuilder configured with the given input, output, and melt time.
     *
     * @param input    the Ingredient that will be consumed by the foundry recipe
     * @param output   the ItemStack produced by the foundry when the recipe completes
     * @param meltTime the melting duration (in ticks) required to produce the output
     */
    public FoundryRecipeBuilder(Ingredient input, ItemStack output, int meltTime) {
        this.input = input;
        this.output = output;
        this.meltTime = meltTime;
    }

    /**
     * Create a FoundryRecipeBuilder configured with the specified input, output, and melt time.
     *
     * @param input the ingredient consumed by the foundry recipe
     * @param output the resulting item stack produced by the recipe
     * @param meltTime the melt time for the recipe
     * @return a new FoundryRecipeBuilder initialized with the given parameters
     */
    public static FoundryRecipeBuilder create(Ingredient input, ItemStack output, int meltTime) {
        return new FoundryRecipeBuilder(input, output, meltTime);
    }

    /**
     * Builds the advancement for this foundry recipe and submits the constructed recipe to the exporter.
     *
     * The method registers a "has_the_recipe" criterion and rewards that grant the recipe, applies any
     * additional criteria collected on the builder, creates a FoundryRecipe from the stored input,
     * output and melt time, and passes the recipe to the provided exporter.
     *
     * @param exporter the export target that will receive the recipe (and is used to build the advancement)
     * @param recipeKey the registry key under which the recipe will be exported and referenced by the advancement
     */
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(advancement::criterion);
        FoundryRecipe recipe = new FoundryRecipe(input, output, meltTime);
        exporter.accept(recipeKey, recipe, null);
    }

    /**
     * Adds an advancement criterion to the recipe builder under the given name.
     *
     * @param name the identifier for the criterion as it will appear in the advancement
     * @param criterion the advancement criterion to add
     * @return this builder instance
     */
    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    /**
     * Sets the recipe group identifier to include in the generated recipe JSON.
     *
     * @param group an optional group name; may be null to omit grouping
     * @return this builder
     */
    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    /**
     * Indicates this builder does not expose a standard output item for the recipe JSON.
     *
     * @return Items.AIR, a placeholder value meaning no output item is provided in the usual recipe output position.
     */
    @Override
    public Item getOutputItem() {
        return Items.AIR;
    }
}