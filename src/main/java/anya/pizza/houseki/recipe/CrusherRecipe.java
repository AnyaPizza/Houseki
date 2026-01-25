package anya.pizza.houseki.recipe;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record CrusherRecipe(Ingredient inputItem, ItemStack output, int crushingTime, Optional<ItemStack> auxiliaryOutput) implements Recipe<CrusherRecipeInput> {
    public static final int DEFAULT_CRUSHING_TIME = 200;

    public CrusherRecipe {
        if (auxiliaryOutput.isEmpty()) {
            auxiliaryOutput = Optional.empty();
        }
    }

    // 2. Secondary Constructor (For DataGen/Old Recipes)
    // This allows you to call: new CrusherRecipe(input, output, time)
    /**
     * Constructs a CrusherRecipe with the specified input, output, and crushing time, and with no auxiliary output.
     *
     * @param inputItem    the ingredient consumed by the recipe
     * @param output       the primary result produced by the recipe
     * @param crushingTime the time required to perform the crushing (in ticks)
     */
    public CrusherRecipe(Ingredient inputItem, ItemStack output, int crushingTime) {
        this(inputItem, output, crushingTime, Optional.empty());
    }

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

    public ItemStack getResult(RegistryWrapper.WrapperLookup ignoredRegistriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<? extends Recipe<CrusherRecipeInput>> getSerializer() {
        return ModRecipes.CRUSHER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<CrusherRecipeInput>> getType() {
        return ModRecipes.CRUSHER_TYPE;
    }

    //No idea if this is right but it works?
    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(List.of());
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<CrusherRecipe> {
        public static final MapCodec<CrusherRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(CrusherRecipe::inputItem),
            ItemStack.CODEC.fieldOf("result").forGetter(CrusherRecipe::output),
            Codec.INT.optionalFieldOf("crushingTime", DEFAULT_CRUSHING_TIME).forGetter(CrusherRecipe::crushingTime),
            // Optional auxiliary output is now the 4th parameter
            ItemStack.CODEC.optionalFieldOf("auxiliary_result", ItemStack.EMPTY)
                .xmap(Optional::of, opt -> opt.orElse(ItemStack.EMPTY))
                .forGetter(CrusherRecipe::auxiliaryOutput)
            ).apply(inst, CrusherRecipe::new));

        public static final PacketCodec<RegistryByteBuf, CrusherRecipe> STREAM_CODEC =
            PacketCodec.tuple(
                Ingredient.PACKET_CODEC, CrusherRecipe::inputItem,
                ItemStack.PACKET_CODEC, CrusherRecipe::output,
                PacketCodecs.INTEGER, CrusherRecipe::crushingTime,
                PacketCodecs.optional(ItemStack.OPTIONAL_PACKET_CODEC), CrusherRecipe::auxiliaryOutput,
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