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
    private final int coolingTime;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    public String group;

    public FoundryRecipeBuilder(Ingredient input, ItemStack output, int meltTime, int coolingTime) {
        this.input = input;
        this.output = output;
        this.meltTime = meltTime;
        this.coolingTime = coolingTime;
    }

    public static FoundryRecipeBuilder create(Ingredient input, ItemStack output, int meltTime, int coolingTime) {
        return new FoundryRecipeBuilder(input, output, meltTime, coolingTime);
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
    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(advancement::criterion);
        FoundryRecipe recipe = new FoundryRecipe(input, output, meltTime, coolingTime);
        exporter.accept(recipeKey, recipe, advancement.build(recipeKey.getValue()));
    }

    /**
     * Adds a named advancement criterion to this recipe builder.
     *
     * @param name a unique identifier for the criterion
     * @param criterion the advancement criterion to associate with the given name
     * @return this builder instance for method chaining
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

    /**
     * Retrieves the item produced by this recipe's output.
     *
     * @return the underlying Item from the recipe's output ItemStack
     */
    @Override
    public Item getOutputItem() {
        return output.getItem();
    }
}