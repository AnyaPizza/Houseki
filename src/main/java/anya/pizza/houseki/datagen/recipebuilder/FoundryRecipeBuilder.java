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
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    public String group;

    public FoundryRecipeBuilder(Ingredient input, ItemStack output, int meltTime) {
        this.input = input;
        this.output = output;
        this.meltTime = meltTime;
    }

    public static FoundryRecipeBuilder create(Ingredient input, ItemStack output, int meltTime) {
        return new FoundryRecipeBuilder(input, output, meltTime);
    }

    public void offerTo(RecipeExporter exporter, RegistryKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeKey))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        this.criteria.forEach(advancement::criterion);
        FoundryRecipe recipe = new FoundryRecipe(input, output, meltTime);
        exporter.accept(recipeKey, recipe, advancement.build(recipeKey.getValue()));
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
        return output.getItem();
    }
}