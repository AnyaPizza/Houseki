package anya.pizza.houseki.datagen.recipebuilder;

import anya.pizza.houseki.recipe.CrusherRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class CrusherRecipeBuilder implements RecipeBuilder {
    private final Ingredient input;
    private final ItemLike output;
    private final int crushingTime;
    private Optional<ItemLike> auxiliaryOutput = Optional.empty();
    private double auxiliaryChance = 1;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    public String group;

    /**
     * Creates a new CrusherRecipeBuilder for building a crusher recipe.
     *
     * @param input the ingredient(s) consumed by the recipe
     * @param output the resulting item produced by the recipe
     * @param crushingTime the crushing duration in ticks
     */
    public CrusherRecipeBuilder(Ingredient input, ItemLike output, int crushingTime) {
        this.input = input;
        this.output = output;
        this.crushingTime = crushingTime;
    }

    /**
     * Create a builder for a crusher recipe.
     *
     * @param input        the ingredient consumed by the recipe
     * @param output       the item produced by the recipe
     * @param crushingTime the crushing duration for the recipe
     * @return             a CrusherRecipeBuilder configured with the provided input, output, and crushing time
     */
    public static CrusherRecipeBuilder create(Ingredient input, ItemLike output, int crushingTime) {
        return new CrusherRecipeBuilder(input, output, crushingTime);
    }

    /**
     * Sets an optional auxiliary output item for the crusher recipe.
     *
     * @param stack the auxiliary output item to produce in addition to the primary output
     * @return this builder instance
     */
    public CrusherRecipeBuilder auxiliary(ItemLike stack) {
        this.auxiliaryOutput = Optional.of(stack);
        return this;
    }

    /**
     * Set the probability that the auxiliary output is produced.
     *
     * @param chance the probability (0.0 to 1.0) that the auxiliary output is produced
     * @return the current CrusherRecipeBuilder instance
     */
    public CrusherRecipeBuilder chance(double chance) {
        this.auxiliaryChance = chance;
        return this;
    }

    /**
     * Builds an advancement that unlocks the given recipe and exports the constructed
     * CrusherRecipe together with that advancement.
     *
     * The advancement will include a `"has_the_recipe"` criterion for the provided
     * recipe key, any additional criteria configured on this builder, and will reward
     * the recipe. The built advancement is placed under the `"recipes/"` advancement
     * path when exported.
     *
     * @param exporter  target consumer that accepts the recipe and its advancement
     * @param recipeKey resource key identifying the recipe to export
     */
    public void save(RecipeOutput exporter, ResourceKey<Recipe<?>> recipeKey) {
        // 1. Build the advancement
        // We use recipeKey.getValue() to get the Identifier (e.g., "modid:item_crushing")
        Advancement.Builder advancement = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeKey))
                .requirements(AdvancementRequirements.Strategy.OR);
        
        this.criteria.forEach(advancement::addCriterion);

        // 2. Create the recipe record
        CrusherRecipe recipe = new CrusherRecipe(
            this.input, 
            this.output.asItem(), 
            this.crushingTime, 
            this.auxiliaryOutput.map(ItemLike::asItem), 
            this.auxiliaryChance
        );

        // 3. Export
        exporter.accept(
            recipeKey, 
            recipe, 
            advancement.build(recipeKey.identifier().withPrefix("recipes/"))
        );
    }

    /**
     * Builds a CrusherRecipe and its unlocking advancement, then registers them with the provided exporter under the "recipes/..." advancement path.
     *
     * The method parses the given recipeId into an Identifier and ResourceKey, adds a "has_the_recipe" criterion and any configured criteria to the advancement,
     * sets the recipe as the advancement reward, constructs a CrusherRecipe using this builder's input, output, crushing time, optional auxiliary output, and auxiliary chance,
     * and finally passes the recipe, its resource key, and the built advancement (placed under "recipes/<id>") to the exporter.
     *
     * @param exporter  the RecipeOutput consumer that receives the recipe ResourceKey, the built CrusherRecipe, and its corresponding Advancement
     * @param recipeId  the string identifier for the recipe (parsed into an Identifier and used to derive the advancement path)
     */
    public void save(RecipeOutput exporter, String recipeId) {
        // 1. Convert the String ID into an Identifier and a ResourceKey
        Identifier id = Identifier.parse(recipeId);
        ResourceKey<Recipe<?>> recipeKey = ResourceKey.create(Registries.RECIPE, id);

        // 2. Build the Advancement
        Advancement.Builder advancement = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeKey))
                .requirements(AdvancementRequirements.Strategy.OR);
        
        this.criteria.forEach(advancement::addCriterion);

        // 3. Create the Recipe Instance
        CrusherRecipe recipe = new CrusherRecipe(
            this.input, 
            this.output.asItem(), 
            this.crushingTime, 
            this.auxiliaryOutput.map(ItemLike::asItem), 
            this.auxiliaryChance
        );

        // 4. Export with the prefixed advancement path
        // Using id.withPrefix ensures the advancement file is correctly placed in "advancements/recipes/..."
        exporter.accept(recipeKey, recipe, advancement.build(id.withPrefix("recipes/")));
    }

    /**
     * Adds an advancement criterion that will be required to unlock the resulting recipe.
     *
     * @param name      a unique identifier for the criterion within the recipe's advancement
     * @param criterion the criterion describing the unlocking condition
     * @return          this builder instance for method chaining
     */
    @Override
    public @NonNull RecipeBuilder unlockedBy(@NonNull String name, @NonNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NonNull RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    /**
     * Indicates this builder does not supply a default recipe identifier.
     *
     * @return {@code null} to indicate no default {@code ResourceKey<Recipe<?>>} is provided.
     */
    @Override
    public @Nullable ResourceKey<Recipe<?>> defaultId() {
        return null;
    }

    /**
     * Gets the Item representation of the configured recipe output.
     *
     * @return the Item corresponding to the builder's output
     */
    public Item getResult() {
        return output.asItem();
    }
}