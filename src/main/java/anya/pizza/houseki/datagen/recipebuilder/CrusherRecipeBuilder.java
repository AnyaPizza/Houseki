package anya.pizza.houseki.datagen.recipebuilder;

import anya.pizza.houseki.recipe.CrusherRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.resources.ResourceKey;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class CrusherRecipeBuilder implements RecipeBuilder {
    private final Ingredient input;
    private final ItemStack output;
    private final int crushingTime;
    private Optional<ItemStack> auxiliaryOutput = Optional.empty();
    private double auxiliaryChance = 1;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
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

    /**
     * Sets the probability that the auxiliary output is produced when the recipe is processed.
     *
     * @param chance the probability between 0.0 and 1.0 for producing the auxiliary output
     * @return this builder
     */
    public CrusherRecipeBuilder chance(double chance) {
        this.auxiliaryChance = chance;
        return this;
    }

    /**
     * Exports this builder's crusher recipe and its advancement to the given exporter under the provided recipe key.
     *
     * The advancement will include the stored criteria, a "has_the_recipe" criterion for the recipe key,
     * rewards that grant the recipe, and OR requirements strategy.
     *
     * @param exporter the RecipeOutput used to accept the recipe and register its advancement
     * @param recipeKey the resource key that identifies the exported recipe
     */
    public void save(RecipeOutput exporter, @NonNull ResourceKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeKey))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);

        // Create an instance of your recipe record
        CrusherRecipe recipe = new CrusherRecipe(input, output, crushingTime, auxiliaryOutput, auxiliaryChance);

        // Export it using the built-in exporter
        exporter.accept(recipeKey, recipe, null);
    }

    /**
     * Adds an advancement criterion under the given name to be used when unlocking the recipe.
     *
     * @param name the identifier for the criterion
     * @param criterion the criterion that will be associated with the given name
     * @return this builder instance for method chaining
     */
    @Override
    public @NonNull RecipeBuilder unlockedBy(@NonNull String name, @NonNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    /**
     * Sets the recipe group identifier used to group related recipes.
     *
     * @param group the group identifier, or {@code null} to clear the group
     * @return this builder instance
     */
    @Override
    public @NonNull RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    /**
     * Provide the default recipe resource key for this builder.
     *
     * <p>Indicates that this builder has no default recipe ResourceKey.</p>
     *
     * @return `null` to indicate no default ResourceKey<Recipe<?>> is provided
     */
    @Override
    public @Nullable ResourceKey<Recipe<?>> defaultId() {
        return null;
    }

    /**
     * Provides a representative result item for this recipe builder.
     *
     * @return `Items.AIR` — a placeholder result when the builder has no concrete output
     */
    public Item getResult() {
        return Items.AIR;
    }
}