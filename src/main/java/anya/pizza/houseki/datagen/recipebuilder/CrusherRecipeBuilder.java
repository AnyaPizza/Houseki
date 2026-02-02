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
     * Creates a new builder for a crusher recipe with the given input, primary output, and crushing time.
     *
     * @param input the ingredient required by the recipe
     * @param output the primary output item for the recipe
     * @param crushingTime the time required to crush the input (in ticks)
     */
    public CrusherRecipeBuilder(Ingredient input, ItemLike output, int crushingTime) {
        this.input = input;
        this.output = output;
        this.crushingTime = crushingTime;
    }

    /**
     * Create a CrusherRecipeBuilder for the given input, output, and crushing time.
     *
     * @param input the required input ingredient for the recipe
     * @param output the primary output item of the recipe
     * @param crushingTime the time required to crush the input (in ticks)
     * @return a CrusherRecipeBuilder configured with the specified input, output, and crushing time
     */
    public static CrusherRecipeBuilder create(Ingredient input, ItemLike output, int crushingTime) {
        return new CrusherRecipeBuilder(input, output, crushingTime);
    }

    /**
     * Sets an auxiliary output item produced alongside the primary output.
     *
     * @param stack the auxiliary output item to produce when the recipe yields an auxiliary result
     * @return this builder instance
     */
    public CrusherRecipeBuilder auxiliary(ItemLike stack) {
        this.auxiliaryOutput = Optional.of(stack);
        return this;
    }

    /**
     * Sets the probability of producing the auxiliary output.
     *
     * @param chance the probability that the auxiliary output will be produced (0.0 to 1.0)
     * @return this builder instance
     */
    public CrusherRecipeBuilder chance(double chance) {
        this.auxiliaryChance = chance;
        return this;
    }

    /**
     * Creates a CrusherRecipe with the builder's configured fields, constructs an associated advancement
     * from the builder's criteria and the recipe unlocking trigger, and writes both to the provided exporter.
     *
     * The advancement is configured with the recipe as its reward, uses OR requirements, and is placed
     * under the "recipes/" path. The created recipe includes the primary output, the optional auxiliary
     * output (if configured) and its production chance.
     *
     * @param exporter  destination that accepts the recipe and its advancement
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
     * Saves this builder's crusher recipe and its corresponding advancement via the provided exporter.
     *
     * The advancement is placed under the "recipes/" path and uses the builder's configured unlock criteria;
     * the recipe is created from the builder's input, primary output, optional auxiliary output (with chance),
     * and crushing time.
     *
     * @param exporter the target that accepts the serialized recipe and built advancement
     * @param recipeId the string identifier of the recipe (e.g., "namespace:path")
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
     * Add an unlocking criterion identified by the given name to this builder.
     *
     * @param name      the unique name for the criterion
     * @param criterion the criterion that must be satisfied to unlock the recipe
     * @return          this RecipeBuilder instance for method chaining
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
     * Indicates the builder has no default recipe key.
     *
     * @return null to indicate no default ResourceKey<Recipe<?>> is provided for this recipe
     */
    @Override
    public @Nullable ResourceKey<Recipe<?>> defaultId() {
        return null;
    }

    /**
     * Get the primary output item for this recipe.
     *
     * @return the Item produced by the recipe
     */
    public Item getResult() {
        return output.asItem();
    }
}