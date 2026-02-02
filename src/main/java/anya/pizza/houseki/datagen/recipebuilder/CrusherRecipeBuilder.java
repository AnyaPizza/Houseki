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