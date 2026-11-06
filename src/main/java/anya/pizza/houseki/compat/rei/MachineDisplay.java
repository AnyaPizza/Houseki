package anya.pizza.houseki.compat.rei;

import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.FoundryCastingRecipe;
import anya.pizza.houseki.recipe.FoundryMeltingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class MachineDisplay extends BasicDisplay {
    private final CategoryIdentifier<MachineDisplay> category;
    private final Recipe<?> recipe;

    MachineDisplay(CategoryIdentifier<MachineDisplay> category, RecipeHolder<?> holder,
                   Ingredient input, Item output) {
        super(List.of(EntryIngredients.ofIngredient(input)), outputs(holder.value(), output),
                Optional.of(holder.id().identifier()));
        this.category = category;
        this.recipe = holder.value();
    }

    static MachineDisplay crushing(RecipeHolder<CrusherRecipe> holder) {
        return new MachineDisplay(HousekiReiPlugin.CRUSHING, holder,
                holder.value().inputItem(), holder.value().output());
    }

    static MachineDisplay melting(RecipeHolder<FoundryMeltingRecipe> holder) {
        return new MachineDisplay(HousekiReiPlugin.MELTING, holder,
                holder.value().inputMeltingItem(), holder.value().output());
    }

    static MachineDisplay casting(RecipeHolder<FoundryCastingRecipe> holder) {
        // Molten metal is a tank requirement, not an inventory ingredient.
        return new MachineDisplay(HousekiReiPlugin.CASTING, holder,
                holder.value().inputCastingItem(), holder.value().output());
    }

    private static List<EntryIngredient> outputs(Recipe<?> recipe, Item output) {
        List<EntryIngredient> outputs = new ArrayList<>();
        outputs.add(EntryIngredients.of(output));
        if (recipe instanceof CrusherRecipe crushing) {
            crushing.auxiliaryOutput().ifPresent(item -> outputs.add(EntryIngredients.of(item)));
        }
        return List.copyOf(outputs);
    }

    Recipe<?> recipe() {
        return recipe;
    }

    @Override
    public CategoryIdentifier<MachineDisplay> getCategoryIdentifier() {
        return category;
    }

    @Override
    public DisplaySerializer<? extends MachineDisplay> getSerializer() {
        // Fabric synchronizes the recipes; REI constructs these displays on the client.
        return null;
    }
}
