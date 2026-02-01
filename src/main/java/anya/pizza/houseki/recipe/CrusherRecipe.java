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

    /**
     * Lists the ingredients required by this recipe.
     *
     * @return a NonNullList containing the single input Ingredient for this recipe
     */
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.createWithCapacity(1);
        list.add(this.inputItem);
        return list;
    }

    /**
     * Checks whether this recipe matches the provided crusher input within the given world context.
     *
     * @param input the crusher input whose first slot item will be tested against this recipe's ingredient
     * @param world the level in which the match is being evaluated; client-side checks always fail
     * @return `true` if the recipe's ingredient matches the input's first item and the world is server-side, `false` otherwise
     */
    @Override
    public boolean matches(CrusherRecipeInput input, Level world) {
        if (world.isClientSide()) {
            return false;
        }

        return inputItem.test(input.getItem(0));
    }

    public static class Type implements RecipeType<CrusherRecipe> {
        public static final Type INSTANCE = new Type();

        /**
 * Prevents external instantiation to enforce the singleton nature of this type.
 */
private Type() {}

        /**
         * The fully-qualified recipe type identifier for the crusher.
         *
         * @return the identifier string in the format "houseki:crushing"
         */
        @Override
        public String toString() {
            return Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "crushing").toString();
        }
    }

    /**
     * Indicates whether this recipe should display a toast notification when executed.
     *
     * @return `true` if a notification toast should be shown for this recipe, `false` otherwise.
     */
    @Override
    public boolean showNotification() {
        return true;
    }

    /**
     * The recipe group identifier used to group similar recipes in the recipe book.
     *
     * @return the group identifier string, or an empty string if the recipe has no group
     */
    @Override
    public String group() {
        return group();
    }

    /**
     * Produces the recipe's primary result for the given input.
     *
     * @return a new ItemStack equal to the recipe's primary output
     */
    @Override
    public ItemStack assemble(CrusherRecipeInput input) {
        return output.copy();
    }

    /**
     * Provide the primary output of this recipe.
     *
     * @param ignoredRegistriesLookup unused registry provider retained for API compatibility
     * @return the primary resulting ItemStack
     */
    public ItemStack getResult(HolderLookup.Provider ignoredRegistriesLookup) {
        return output;
    }

    /**
     * Retrieves the serializer used to read and write crusher recipes.
     *
     * @return the registered RecipeSerializer for crusher recipes
     */
    @Override
    public RecipeSerializer<? extends Recipe<CrusherRecipeInput>> getSerializer() {
        return ModSerializer.CRUSHER_SERIALIZER;
    }

    /**
     * The recipe type for crusher recipes.
     *
     * @return the registered {@link RecipeType} instance representing crusher recipes
     */
    @Override
    public RecipeType<? extends Recipe<CrusherRecipeInput>> getType() {
        return ModTypes.CRUSHER_TYPE;
    }

    /**
     * Specifies that this recipe does not support placing its ingredient into a crafting slot.
     *
     * @return `PlacementInfo.NOT_PLACEABLE` indicating the ingredient cannot be placed into a slot
     */
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    /**
     * Indicates which recipe book category this recipe belongs to.
     *
     * @return the recipe book category to display this recipe under, or `null` if the recipe should not appear in the recipe book
     */
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

        /**
         * Provides the MapCodec used to serialize and deserialize CrusherRecipe instances.
         *
         * @return the MapCodec for encoding and decoding {@code CrusherRecipe}
         */
        @Override
        public MapCodec<CrusherRecipe> codec() {
            return CODEC;
        }

        /**
         * Provides the StreamCodec used for encoding and decoding CrusherRecipe instances to and from network buffers.
         *
         * @return the StreamCodec that encodes and decodes {@link CrusherRecipe} instances with {@link RegistryFriendlyByteBuf}
         */
        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CrusherRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}