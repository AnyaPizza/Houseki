package anya.pizza.houseki.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;


public record FoundryRecipe(Ingredient inputCastItem, ItemStack output, int meltTime) implements Recipe<FoundryRecipeCastInput> {
    public static final int DEFAULT_MELT_TIME = 200;
    public static final int DEFAULT_CAST_TIME = 200;

    //public FoundryRecipe(Ingredient inputItem, ItemStack output, int meltTime) {
    //    this(inputCastItem, output, meltTime);
    //}

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(1);
        list.add(this.inputCastItem);
        return list;
    }

    @Override
    public boolean matches(FoundryRecipeCastInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputCastItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(FoundryRecipeCastInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup ignoredRegistriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<? extends Recipe<FoundryRecipeCastInput>> getSerializer() {
        return ModRecipes.FOUNDRY_SERIALIZER;
    }

    /**
     * Exposes the recipe category used for foundry recipes.
     *
     * @return `ModRecipes.FOUNDRY_TYPE`, the recipe type used for foundry recipes.
     */
    @Override
    public RecipeType<? extends Recipe<FoundryRecipeCastInput>> getType() {
        return ModRecipes.FOUNDRY_TYPE;
    }

    /**
     * Specifies how ingredients are positioned for this recipe.
     *
     * <p>Indicates that the recipe's ingredient(s) may occupy multiple inventory slots without fixed indices.</p>
     *
     * @return an IngredientPlacement configured for multiple slots (no fixed slot indices)
     */
    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(List.of());
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<FoundryRecipe> {
        public static final MapCodec<FoundryRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(FoundryRecipe::inputCastItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FoundryRecipe::output),
                Codec.INT.optionalFieldOf("meltTime", DEFAULT_MELT_TIME).forGetter(FoundryRecipe::meltTime)
        ).apply(inst, FoundryRecipe::new));

        public static final PacketCodec<RegistryByteBuf, FoundryRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, FoundryRecipe::inputCastItem,
                        ItemStack.PACKET_CODEC, FoundryRecipe::output,
                        PacketCodecs.INTEGER, FoundryRecipe::meltTime,
                        FoundryRecipe::new);

        @Override
        public MapCodec<FoundryRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, FoundryRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
/*public record FoundryRecipe implements Recipe<FoundryRecipeCastInput> {
    private final ItemStack cast;
    private final ItemStack result;
    private final int metalCost;

    public FoundryRecipe(ItemStack cast, ItemStack result, int metalCost) {
        this.cast = cast;
        this.result = result;
        this.metalCost = metalCost;
    }

    @Override
    public boolean matches(FoundryRecipeCastInput input, World world) {
        return ItemStack.areItemsEqual(input.cast(), this.cast);
    }

    @Override
    public ItemStack craft(FoundryRecipeCastInput input, RegistryWrapper.WrapperLookup lookup) {
        return result.copy();
    }

    public boolean fits(int width, int height) {
        return true;
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return result;
    }

    public int getMetalCost() {
        return metalCost;
    }

    @Override
    public RecipeSerializer<? extends Recipe<FoundryRecipeCastInput>> getSerializer() {
        return ModRecipes.FOUNDRY_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<FoundryRecipeCastInput>> getType() {
        return ModRecipes.FOUNDRY_TYPE;
    }

    public static class Serializer implements RecipeSerializer<FoundryRecipe> {
        public static final MapCodec<FoundryRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                ItemStack.CODEC.fieldOf("cast").forGetter(r -> r.cast),
                ItemStack.CODEC.fieldOf("result").forGetter(r -> r.result),
                Codec.INT.fieldOf("metal_cost").orElse(90).forGetter(r -> r.metalCost)
        ).apply(inst, FoundryRecipe::new));

        @Override
        public PacketCodec<RegistryByteBuf, FoundryRecipe> packetCodec() {
            return null;
        }
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return null;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }
}
*/