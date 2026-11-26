package anya.pizza.houseki.datagen.recipebuilder;

import anya.pizza.houseki.recipe.FoundryCastingRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

import static anya.pizza.houseki.block.entity.custom.FoundryBlockEntity.METAL_STEEL;

public class FoundryCastingRecipeBuilder implements RecipeBuilder {
    private final Ingredient input;
    private final ItemLike output;
    private int activeMetalType = METAL_STEEL;
    private final int castTime;
    private final int coolingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    public String group;

    public FoundryCastingRecipeBuilder(Ingredient input, ItemLike output, int activeMetalType, int castTime, int coolingTime) {
        this.input = input;
        this.output = output;
        this.activeMetalType = activeMetalType;
        this.castTime = castTime;
        this.coolingTime = coolingTime;
    }

    public static FoundryCastingRecipeBuilder create(Ingredient input, ItemLike output, int activeMetalType, int castTime, int coolingTime) {
        return new FoundryCastingRecipeBuilder(input, output, activeMetalType, castTime, coolingTime);
    }

    /**
     * Exports the built foundry recipe together with its unlocking advancement to the provided exporter.
     *
     * The advancement includes a recipe-unlocked criterion named "has_the_recipe", grants the recipe as a reward,
     * merges criteria with an OR policy, and includes any additional criteria accumulated on this builder.
     *
     * @param exporter the recipient that will be given the recipe and its advancement
     * @param recipeKey the registry key used to identify the exported recipe and to build the advancement rewards
     */
    public void save(RecipeOutput exporter, ResourceKey<Recipe<?>> location) {
        Advancement.Builder advancement = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location))
                .rewards(AdvancementRewards.Builder.recipe(location))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        FoundryCastingRecipe recipe = new FoundryCastingRecipe(input, output.asItem(), activeMetalType, castTime, coolingTime);
        exporter.accept(location, recipe, advancement.build(location.identifier().withPrefix("recipes/")));
    }

    /**
     * Adds a named advancement criterion to this recipe builder.
     *
     * @param name a unique identifier for the criterion
     * @param criterion the advancement criterion to associate with the given name
     * @return this builder instance for method chaining
     */
    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return null;
    }

    /**
     * Retrieves the item produced by this recipe's output.
     *
     * @return the underlying Item from the recipe's output ItemStack
     */
    public Item getResult() {
        return output.asItem();
    }
}