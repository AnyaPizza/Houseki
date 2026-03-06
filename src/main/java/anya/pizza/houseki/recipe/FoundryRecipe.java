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

    //public FoundryRecipe(Ingredient inputItem, ItemStack output, int meltTime) {
    //    this(inputCastItem, output, meltTime);
    /**
     * Provides the recipe's ingredient list containing the required cast item.
     *
     * @return a DefaultedList of ingredients containing the single input cast item
     */

    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(1);
        list.add(this.inputCastItem);
        return list;
    }

    /**
     * Determines whether the provided input satisfies this recipe in the given world.
     *
     * @param input the foundry input to test against this recipe
     * @param world the world context in which matching is evaluated
     * @return `true` if the input matches this recipe and the world is server-side, `false` otherwise
     */
    @Override
    public boolean matches(FoundryRecipeCastInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputCastItem.test(input.getStackInSlot(0));
    }

    /**
     * Produce the recipe's output ItemStack for the provided input.
     *
     * @param input the crafting input for this foundry recipe
     * @param lookup registry lookup available during crafting (not used by this recipe)
     * @return the recipe result as an ItemStack (a copy of the configured output)
     */
    @Override
    public ItemStack craft(FoundryRecipeCastInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    /**
     * Retrieves the recipe's configured output item stack.
     *
     * @return the recipe's output {@code ItemStack}; this returns the configured instance (not a defensive copy)
     */
    public ItemStack getResult(RegistryWrapper.WrapperLookup ignoredRegistriesLookup) {
        return output;
    }

    /**
     * Provides the serializer associated with this foundry recipe type.
     *
     * @return the RecipeSerializer used to serialize and deserialize FoundryRecipe instances
     */
    @Override
    public RecipeSerializer<? extends Recipe<FoundryRecipeCastInput>> getSerializer() {
        return ModRecipes.FOUNDRY_SERIALIZER;
    }

    /**
     * Retrieves the recipe type used to categorize foundry recipes.
     *
     * @return the recipe type instance for foundry recipes
     */
    @Override
    public RecipeType<? extends Recipe<FoundryRecipeCastInput>> getType() {
        return ModRecipes.FOUNDRY_TYPE;
    }

    /**
     * Specifies the placement of this recipe's ingredient(s) across input slots for UI display and validation.
     *
     * @return an IngredientPlacement representing multiple-slot placement with no specific slot indices (empty placement)
     */
    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(List.of());
    }

    /**
     * Indicates the recipe has no associated recipe book category.
     *
     * @return `null` to indicate there is no recipe book category for this recipe
     */
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

        /**
         * Provides the map-based codec used for serializing and deserializing this recipe.
         *
         * @return the MapCodec that serializes and deserializes FoundryRecipe instances
         */
        @Override
        public MapCodec<FoundryRecipe> codec() {
            return CODEC;
        }

        /**
         * Provides the packet codec used to serialize and deserialize FoundryRecipe instances to and from network buffers.
         *
         * @return the PacketCodec that encodes/decodes a FoundryRecipe to/from a RegistryByteBuf
         */
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