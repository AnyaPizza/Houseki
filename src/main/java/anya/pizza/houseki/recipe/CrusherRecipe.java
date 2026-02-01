package anya.pizza.houseki.recipe;

import java.util.List;
import java.util.Optional;

import anya.pizza.houseki.Houseki;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.crafting.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.Level;

public record CrusherRecipe(Ingredient inputItem, ItemStack output, int crushingTime, Optional<ItemStack> auxiliaryOutput, double auxiliaryChance) implements Recipe<CrusherRecipeInput> {
    public static final int DEFAULT_CRUSHING_TIME = 200;
    public static final double DEFAULT_AUXILIARY_CHANCE = 1.0; //1 = 100%

    public CrusherRecipe {
        if (auxiliaryOutput.isEmpty()) {
            auxiliaryOutput = Optional.empty();
        }
    }

    // Secondary Constructor (For DataGen/Old Recipes)
    // This allows you to call: new CrusherRecipe(input, output, time)
    /**
     * Creates a CrusherRecipe for the given input, primary output, and crushing time with no auxiliary output and an auxiliary chance of 1.0.
     *
     * @param inputItem    the ingredient consumed by the recipe
     * @param output       the primary result produced by the recipe
     * @param crushingTime the time required to perform the crushing, in ticks
     */
    public CrusherRecipe(Ingredient inputItem, ItemStack output, int crushingTime) {
        this(inputItem, output, crushingTime, Optional.empty(), DEFAULT_AUXILIARY_CHANCE);
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.createWithCapacity(1);
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(CrusherRecipeInput input, Level world) {
        if (world.isClientSide()) {
            return false;
        }

        return inputItem.test(input.getItem(0));
    }

    public static class Type implements RecipeType<CrusherRecipe> {
        public static final Type INSTANCE = new Type();

        private Type() {}

        @Override
        public String toString() {
            return Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "crushing").toString();
        }
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return group();
    }

    @Override
    public ItemStack assemble(CrusherRecipeInput input) {
        return output.copy();
    }

    public ItemStack getResult(HolderLookup.Provider ignoredRegistriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<? extends Recipe<CrusherRecipeInput>> getSerializer() {
        return ModSerializer.CRUSHER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<CrusherRecipeInput>> getType() {
        return ModTypes.CRUSHER_TYPE;
    }

    //No idea if this is right but it works?
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }


    public static class Serializer implements RecipeSerializer<CrusherRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<CrusherRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(CrusherRecipe::inputItem),
            ItemStack.CODEC.fieldOf("result").forGetter(CrusherRecipe::output),
            Codec.INT.optionalFieldOf("crushingTime", DEFAULT_CRUSHING_TIME).forGetter(CrusherRecipe::crushingTime),
            // Optional auxiliary output is now the 4th parameter
            ItemStack.CODEC.optionalFieldOf("auxiliary_result", ItemStack.EMPTY)
                .xmap(Optional::of, opt -> opt.orElse(ItemStack.EMPTY))
                .forGetter(CrusherRecipe::auxiliaryOutput),
            Codec.DOUBLE.optionalFieldOf("auxiliary_chance", DEFAULT_AUXILIARY_CHANCE).forGetter(CrusherRecipe::auxiliaryChance)
            ).apply(inst, CrusherRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> STREAM_CODEC =
            StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, CrusherRecipe::inputItem,
                ItemStack.STREAM_CODEC, CrusherRecipe::output,
                ByteBufCodecs.INT, CrusherRecipe::crushingTime,
                ByteBufCodecs.optional(ItemStack.OPTIONAL_STREAM_CODEC), CrusherRecipe::auxiliaryOutput,
                ByteBufCodecs.DOUBLE, CrusherRecipe::auxiliaryChance,
                CrusherRecipe::new);

        @Override
        public MapCodec<CrusherRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}