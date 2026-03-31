package anya.pizza.houseki.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.recipe.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;


public record FoundryCastingRecipe(Ingredient inputCastingItem, Item output, int activeMetalType, int castTime, int coolingTime) implements Recipe<FoundryRecipeCastInput> {
    public static final int DEFAULT_CAST_TIME = 200;
    public static final int DEFAULT_COOLING_TIME = 200;

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.set(0, this.inputCastingItem);
        return list;
    }

    public static class Type implements RecipeType<FoundryCastingRecipe> {
        public static final FoundryCastingRecipe.Type INSTANCE = new FoundryCastingRecipe.Type();

        private Type() {}

        @Override
        public String toString() {
            return "casting";
        }
    }

    @Override
    public boolean matches(FoundryRecipeCastInput input, Level level) {
        return this.inputCastingItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(FoundryRecipeCastInput input) {
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
    public RecipeSerializer<? extends Recipe<FoundryRecipeCastInput>> getSerializer() {
        return ModSerializer.FOUNDRY_CASTING_SERIALIZER;
    }

    /**
     * Exposes the recipe category used for foundry recipes.
     *
     * @return `ModRecipes.FOUNDRY_TYPE`, the recipe type used for foundry recipes.
     */
    @Override
    public RecipeType<? extends Recipe<FoundryRecipeCastInput>> getType() {
        return ModTypes.CASTING_FOUNDRY_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of());
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final RecipeSerializer<FoundryCastingRecipe> SERIALIZER = new RecipeSerializer<>(
        RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(FoundryCastingRecipe::inputCastingItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FoundryCastingRecipe::output),
                Codec.INT.fieldOf("activeMetalType").forGetter(FoundryCastingRecipe::activeMetalType),
                Codec.INT.optionalFieldOf("castTime", DEFAULT_CAST_TIME).forGetter(FoundryCastingRecipe::castTime),
                Codec.INT.optionalFieldOf("coolingTime", DEFAULT_COOLING_TIME).forGetter(FoundryCastingRecipe::coolingTime)
        ).apply(inst, FoundryCastingRecipe::new)),

        StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, FoundryCastingRecipe::inputCastingItem,
                ByteBufCodecs.registry(Registries.ITEM), FoundryCastingRecipe::output,
                ByteBufCodecs.VAR_INT.<RegistryFriendlyByteBuf>cast(), FoundryCastingRecipe::activeMetalType,
                ByteBufCodecs.VAR_INT.<RegistryFriendlyByteBuf>cast(), FoundryCastingRecipe::castTime,
                ByteBufCodecs.VAR_INT.<RegistryFriendlyByteBuf>cast(), FoundryCastingRecipe::coolingTime,
                FoundryCastingRecipe::new
        )
    );
}
