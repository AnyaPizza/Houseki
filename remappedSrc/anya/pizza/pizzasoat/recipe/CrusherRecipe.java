package anya.pizza.pizzasoat.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record CrusherRecipe(Ingredient inputItem, ItemStack output, int crushingTime) implements Recipe<CrusherRecipeInput> {
    public static final int DEFAULT_CRUSHING_TIME = 200;

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(CrusherRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(CrusherRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRUSHER_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CRUSHER_TYPE;
    }


    public static class Serializer implements RecipeSerializer<CrusherRecipe> {
        public static final MapCodec<CrusherRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(CrusherRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CrusherRecipe::output),
                Codec.INT.optionalFieldOf("crushingTime",DEFAULT_CRUSHING_TIME).forGetter(CrusherRecipe::crushingTime)
        ).apply(inst, CrusherRecipe::new));
        public static final PacketCodec<RegistryByteBuf, CrusherRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, CrusherRecipe::inputItem,
                        ItemStack.PACKET_CODEC, CrusherRecipe::output,
                        PacketCodecs.INTEGER, CrusherRecipe::crushingTime,
                        CrusherRecipe::new);

        @Override
        public MapCodec<CrusherRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CrusherRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }

}
