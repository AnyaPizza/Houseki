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

    /**
     * Create an ItemStack containing the recipe's primary output.
     *
     * @param registries the registry provider (not used by this implementation)
     * @return an ItemStack containing one unit of the recipe's output
     */
    public ItemStack getResult(HolderLookup.Provider registries) {
        return new ItemStack(this.output);
    }

    /**
     * Determines whether this recipe matches the provided crusher input.
     *
     * @param input the crusher input whose first slot will be tested against the recipe's ingredient
     * @param level the current level/world context
     * @return true if the recipe's input ingredient matches the item in the first slot of {@code input}, false otherwise
     */
    @Override
    public boolean matches(CrusherRecipeInput input, Level level) {
        return this.inputItem.test(input.getItem(0));
    }

    /**
     * Create an ItemStack representing this recipe's primary output.
     *
     * @return an ItemStack containing the recipe's output item with a count of 1
     */
    @Override
    public ItemStack assemble(CrusherRecipeInput input) {
        return new ItemStack(this.output);
    }

    /**
     * Indicates whether using this recipe triggers a player notification.
     *
     * @return true if a notification should be shown when the recipe is used, false otherwise.
     */
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

    /**
     * Specifies the recipe book category for this recipe.
     *
     * @return the RecipeBookCategory under which this recipe appears (RecipeBookCategories.CRAFTING_MISC)
     */
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
    
    public static final RecipeSerializer<CrusherRecipe> SERIALIZER = new RecipeSerializer<>(
        RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(CrusherRecipe::inputItem),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("result").forGetter(CrusherRecipe::output),
            Codec.INT.optionalFieldOf("crushingTime", DEFAULT_CRUSHING_TIME).forGetter(CrusherRecipe::crushingTime),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("auxiliary_result").forGetter(CrusherRecipe::auxiliaryOutput),
            Codec.DOUBLE.optionalFieldOf("auxiliary_chance", DEFAULT_AUXILIARY_CHANCE).forGetter(CrusherRecipe::auxiliaryChance)
        ).apply(inst, CrusherRecipe::new)),
        
        StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CrusherRecipe::inputItem,
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

    /**
     * Identifies the recipe type for crusher recipes.
     *
     * @return the RecipeType instance that identifies crusher recipes
     */
    @Override
    public RecipeType<? extends Recipe<CrusherRecipeInput>> getType() {
        return ModTypes.CRUSHER_TYPE;
    }
}