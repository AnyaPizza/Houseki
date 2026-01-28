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
    private int auxiliaryChance = 1;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    public CrusherRecipeBuilder(Ingredient input, ItemStack output, int crushingTime) {
        this.input = input;
        this.output = output;
        this.crushingTime = crushingTime;
    }

    public static CrusherRecipeBuilder create(Ingredient input, ItemStack output, int crushingTime) {
        return new CrusherRecipeBuilder(input, output, crushingTime);
    }

    /**
     * Set the optional auxiliary output produced by the crusher.
     *
     * @param stack the ItemStack to use as the auxiliary (secondary) output
     * @return      this builder instance for method chaining
     */
    public CrusherRecipeBuilder auxiliary(ItemStack stack) {
        this.auxiliaryOutput = Optional.of(stack);
        return this;
    }

    /**
     * Set the chance for producing the auxiliary output in the built recipe.
     *
     * @param chance the chance value used to determine auxiliary output occurrence
     * @return this builder instance
     */
    public CrusherRecipeBuilder chance(int chance) {
        this.auxiliaryChance = chance;
        return this;
    }

    /**
     * Builds the recipe's advancement (including stored criteria and rewards), constructs a
     * CrusherRecipe with the configured input, output, crushing time, optional auxiliary output,
     * and auxiliary chance, and submits both to the provided RecipeExporter under the given key.
     *
     * @param exporter  the exporter that will receive the recipe and associated advancement
     * @param recipeKey the registry key under which the recipe will be exported
     */
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(advancement::criterion);

        // Create an instance of your recipe record
        CrusherRecipe recipe = new CrusherRecipe(input, output, crushingTime, auxiliaryOutput, auxiliaryChance);

        // Export it using the built-in exporter
        exporter.accept(recipeKey, recipe, null);
    }

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