package anya.pizza.houseki.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import java.util.Optional;

public record CrusherRecipe(
    Ingredient inputItem, 
    Item output, 
    int crushingTime, 
    Optional<Item> auxiliaryOutput, 
    double auxiliaryChance
) implements Recipe<CrusherRecipeInput> {
    public static final int DEFAULT_CRUSHING_TIME = 200;
    public static final double DEFAULT_AUXILIARY_CHANCE = 1.0; //1 = 100%

    public static class Type implements RecipeType<CrusherRecipe> {
        public static final Type INSTANCE = new Type();

        private Type() {}

        @Override
        public String toString() {
            return "crushing";
        }
    }

    public ItemStack getResult(HolderLookup.Provider registries) {
        return new ItemStack(this.output);
    }

    //public CrusherRecipe(Ingredient inputItem, ItemStack output, int crushingTime) {
    //    this(inputItem, output, crushingTime, Optional.empty(), DEFAULT_AUXILIARY_CHANCE);
    //}

    @Override
    public boolean matches(CrusherRecipeInput input, Level level) {
        return this.inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(CrusherRecipeInput input) {
        return new ItemStack(this.output);
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public PlacementInfo placementInfo() {
        // This handles how items are ghosted in the crafting slots
        return PlacementInfo.create(this.inputItem);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        // Use a standard category or your own
        return RecipeBookCategories.CRAFTING_MISC;
    }
    
    public static final RecipeSerializer<CrusherRecipe> SERIALIZER = new RecipeSerializer<>(
        RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(CrusherRecipe::inputItem),
            // Use the standard Item registry codec here
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("result").forGetter(CrusherRecipe::output),
            Codec.INT.optionalFieldOf("crushingTime", DEFAULT_CRUSHING_TIME).forGetter(CrusherRecipe::crushingTime),
            // Use the same item codec for the optional auxiliary result
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("auxiliary_result").forGetter(CrusherRecipe::auxiliaryOutput),
            Codec.DOUBLE.optionalFieldOf("auxiliary_chance", DEFAULT_AUXILIARY_CHANCE).forGetter(CrusherRecipe::auxiliaryChance)
        ).apply(inst, CrusherRecipe::new)),
        
        StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CrusherRecipe::inputItem,
            // Use Item reference for network
            ByteBufCodecs.registry(Registries.ITEM), CrusherRecipe::output,
            ByteBufCodecs.VAR_INT.<RegistryFriendlyByteBuf>cast(), CrusherRecipe::crushingTime,
            ByteBufCodecs.optional(ByteBufCodecs.registry(Registries.ITEM)), CrusherRecipe::auxiliaryOutput,
            ByteBufCodecs.DOUBLE.<RegistryFriendlyByteBuf>cast(), CrusherRecipe::auxiliaryChance,
            CrusherRecipe::new
        )
    );

    @Override
    public RecipeSerializer<? extends Recipe<CrusherRecipeInput>> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<CrusherRecipeInput>> getType() {
        return ModTypes.CRUSHER_TYPE;
    }

    /*public static class Serializer implements RecipeSerializer<CrusherRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final MapCodec<CrusherRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(CrusherRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CrusherRecipe::output),
                Codec.INT.optionalFieldOf("crushing_time", 200).forGetter(CrusherRecipe::crushingTime),
                ItemStack.CODEC.optionalFieldOf("auxiliary_result").forGetter(CrusherRecipe::auxiliaryOutput),
                Codec.DOUBLE.optionalFieldOf("auxiliary_chance", 1.0).forGetter(CrusherRecipe::auxiliaryChance)
        ).apply(inst, CrusherRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, CrusherRecipe::inputItem,
                ItemStack.STREAM_CODEC, CrusherRecipe::output,
                ByteBufCodecs.VAR_INT, CrusherRecipe::crushingTime,
                ByteBufCodecs.optional(ItemStack.STREAM_CODEC), CrusherRecipe::auxiliaryOutput,
                ByteBufCodecs.DOUBLE, CrusherRecipe::auxiliaryChance,
                CrusherRecipe::new
        );

        @Override
        public MapCodec<CrusherRecipe> codec() { return CODEC; }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> streamCodec() { return STREAM_CODEC; }
    }*/
}