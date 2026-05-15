package anya.pizza.houseki.compat.rei;

import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.plugin.common.displays.cooking.DefaultBlastingDisplay;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class FixedBlastingDisplay extends DefaultBlastingDisplay {
    public static final DisplaySerializer<FixedBlastingDisplay> SERIALIZER = serializer(FixedBlastingDisplay::new);

    public FixedBlastingDisplay(RecipeEntry<BlastingRecipe> recipe) {
        super(recipe);
    }

    public FixedBlastingDisplay(List<EntryIngredient> input, List<EntryIngredient> output, Optional<Identifier> id, float xp, double cookTime) {
        super(input, output, id, xp, cookTime);
    }

    @Override
    public DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
