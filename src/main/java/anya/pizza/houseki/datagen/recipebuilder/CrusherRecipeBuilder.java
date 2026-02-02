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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
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

    public CrusherRecipeBuilder(Ingredient input, ItemLike output, int crushingTime) {
        this.input = input;
        this.output = output;
        this.crushingTime = crushingTime;
    }

    public static CrusherRecipeBuilder create(Ingredient input, ItemLike output, int crushingTime) {
        return new CrusherRecipeBuilder(input, output, crushingTime);
    }

    public CrusherRecipeBuilder auxiliary(ItemLike stack) {
        this.auxiliaryOutput = Optional.of(stack);
        return this;
    }

    public CrusherRecipeBuilder chance(double chance) {
        this.auxiliaryChance = chance;
        return this;
    }

    public void save(RecipeOutput exporter, ResourceKey<Recipe<?>> recipeKey) {
        Advancement.Builder advancement = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeKey))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeKey))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);

        // Create an instance of your recipe record
        CrusherRecipe recipe = new CrusherRecipe(
            this.input, 
            this.output.asItem(), 
            this.crushingTime, 
            this.auxiliaryOutput.map(ItemLike::asItem), 
            this.auxiliaryChance
        );

        // Export it using the built-in exporter
        exporter.accept(recipeKey, recipe, advancement.build(recipeKey.registry().withPrefix("recipes/")));
    }

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

    @Override
    public @Nullable ResourceKey<Recipe<?>> defaultId() {
        return null;
    }

    public Item getResult() {
        return output.asItem();
    }
}