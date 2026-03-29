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


public record FoundryMeltingRecipe(Ingredient inputMeltingItem, ItemStack output, int meltTime) implements Recipe<FoundryRecipeInput> {
    public static final int DEFAULT_MELT_TIME = 200;

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputMeltingItem);
        return list;
    }

    @Override
    public boolean matches(FoundryRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputMeltingItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(FoundryRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    public ItemStack getResult(RegistryWrapper.WrapperLookup ignoredRegistriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<? extends Recipe<FoundryRecipeInput>> getSerializer() {
        return ModRecipes.FOUNDRY_MELTING_SERIALIZER;
    }

    /**
     * Exposes the recipe category used for foundry recipes.
     *
     * @return `ModRecipes.FOUNDRY_TYPE`, the recipe type used for foundry recipes.
     */
    @Override
    public RecipeType<? extends Recipe<FoundryRecipeInput>> getType() {
        return ModRecipes.FOUNDRY_MELTING_TYPE;
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

    public static class Serializer implements RecipeSerializer<FoundryMeltingRecipe> {
        public static final MapCodec<FoundryMeltingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(FoundryMeltingRecipe::inputMeltingItem),
                ItemStack.CODEC.fieldOf("result").forGetter(FoundryMeltingRecipe::output),
                Codec.INT.optionalFieldOf("meltTime", DEFAULT_MELT_TIME).forGetter(FoundryMeltingRecipe::meltTime)
        ).apply(inst, FoundryMeltingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, FoundryMeltingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, FoundryMeltingRecipe::inputMeltingItem,
                        ItemStack.PACKET_CODEC, FoundryMeltingRecipe::output,
                        PacketCodecs.INTEGER, FoundryMeltingRecipe::meltTime,
                        FoundryMeltingRecipe::new);

        /**
         * Provide the MapCodec used to serialize and deserialize FoundryRecipe instances.
         *
         * @return the MapCodec that encodes and decodes FoundryRecipe objects
         */
        @Override
        public MapCodec<FoundryMeltingRecipe> codec() {
            return CODEC;
        }

        /**
         * Provide the codec used to encode and decode FoundryRecipe instances for network packets.
         *
         * @return the packet codec that reads and writes a FoundryRecipe to a RegistryByteBuf
         */
        @Override
        public PacketCodec<RegistryByteBuf, FoundryMeltingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}