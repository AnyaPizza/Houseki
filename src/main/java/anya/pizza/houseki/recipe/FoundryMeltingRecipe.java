package anya.pizza.houseki.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;


public record FoundryMeltingRecipe(Ingredient inputMeltingItem, Item output, int meltTime) implements Recipe<FoundryRecipeInput> {
    public static final int DEFAULT_MELT_TIME = 200;

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.inputMeltingItem);
        return list;
    }

    public static class Type implements RecipeType<FoundryMeltingRecipe> {
        public static final FoundryMeltingRecipe.Type INSTANCE = new FoundryMeltingRecipe.Type();

        private Type() {}

        @Override
        public String toString() {
            return "melting";
        }
    }

    @Override
    public boolean matches(FoundryRecipeInput input, Level level) {
        return this.inputMeltingItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(FoundryRecipeInput input) {
        return new ItemStack(this.output);
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    public ItemStack getResult(HolderLookup.Provider registries) {
        return new ItemStack(this.output);
    }

    @Override
    public RecipeSerializer<? extends Recipe<FoundryRecipeInput>> getSerializer() {
        return ModSerializer.FOUNDRY_MELTING_SERIALIZER;
    }

    /**
     * Exposes the recipe category used for foundry recipes.
     *
     * @return `ModRecipes.FOUNDRY_TYPE`, the recipe type used for foundry recipes.
     */
    @Override
    public RecipeType<? extends Recipe<FoundryRecipeInput>> getType() {
        return ModTypes.MELTING_FOUNDRY_TYPE;
    }

    /**
     * Specifies how ingredients are positioned for this recipe.
     *
     * <p>Indicates that the recipe's ingredient(s) may occupy multiple inventory slots without fixed indices.</p>
     *
     * @return an IngredientPlacement configured for multiple slots (no fixed slot indices)
     */
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of());
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final RecipeSerializer<FoundryMeltingRecipe> SERIALIZER = new RecipeSerializer<>(
        RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(FoundryMeltingRecipe::inputMeltingItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FoundryMeltingRecipe::output),
                Codec.INT.optionalFieldOf("meltTime", DEFAULT_MELT_TIME).forGetter(FoundryMeltingRecipe::meltTime)
        ).apply(inst, FoundryMeltingRecipe::new)),

            StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, FoundryMeltingRecipe::inputMeltingItem,
                ByteBufCodecs.registry(Registries.ITEM), FoundryMeltingRecipe::output,
                ByteBufCodecs.VAR_INT.<RegistryFriendlyByteBuf>cast(), FoundryMeltingRecipe::meltTime,
                FoundryMeltingRecipe::new
            )
    );
}
