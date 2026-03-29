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


public record FoundryCastingRecipe(Ingredient inputCastingItem, ItemStack output, int activeMetalType, int castTime, int coolingTime) implements Recipe<FoundryRecipeCastInput> {
    public static final int DEFAULT_CAST_TIME = 200;
    public static final int DEFAULT_COOLING_TIME = 200;

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.set(0, this.inputCastingItem);
        return list;
    }

    @Override
    public boolean matches(FoundryRecipeCastInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputCastingItem.test(input.getStackInSlot(0));
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
        return ModRecipes.FOUNDRY_CASTING_SERIALIZER;
    }

    /**
     * Exposes the recipe category used for foundry recipes.
     *
     * @return `ModRecipes.FOUNDRY_TYPE`, the recipe type used for foundry recipes.
     */
    @Override
    public RecipeType<? extends Recipe<FoundryRecipeCastInput>> getType() {
        return ModRecipes.FOUNDRY_CASTING_TYPE;
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

    public static class Serializer implements RecipeSerializer<FoundryCastingRecipe> {
        public static final MapCodec<FoundryCastingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(FoundryCastingRecipe::inputCastingItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FoundryCastingRecipe::output),
                Codec.INT.fieldOf("activeMetalType").forGetter(FoundryCastingRecipe::activeMetalType),
                Codec.INT.optionalFieldOf("castTime", DEFAULT_CAST_TIME).forGetter(FoundryCastingRecipe::castTime),
                Codec.INT.optionalFieldOf("coolingTime", DEFAULT_COOLING_TIME).forGetter(FoundryCastingRecipe::coolingTime)
        ).apply(inst, FoundryCastingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, FoundryCastingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, FoundryCastingRecipe::inputCastingItem,
                        ItemStack.PACKET_CODEC, FoundryCastingRecipe::output,
                        PacketCodecs.INTEGER, FoundryCastingRecipe::activeMetalType,
                        PacketCodecs.INTEGER, FoundryCastingRecipe::castTime,
                        PacketCodecs.INTEGER, FoundryCastingRecipe::coolingTime,
                        FoundryCastingRecipe::new);

        /**
         * Provide the MapCodec used to serialize and deserialize FoundryRecipe instances.
         *
         * @return the MapCodec that encodes and decodes FoundryRecipe objects
         */
        @Override
        public MapCodec<FoundryCastingRecipe> codec() {
            return CODEC;
        }

        /**
         * Provide the codec used to encode and decode FoundryRecipe instances for network packets.
         *
         * @return the packet codec that reads and writes a FoundryRecipe to a RegistryByteBuf
         */
        @Override
        public PacketCodec<RegistryByteBuf, FoundryCastingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}