package anya.pizza.houseki.compat.rei;

import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.plugin.client.displays.ClientsidedCookingDisplay;
import net.minecraft.recipe.NetworkRecipeId;
import net.minecraft.recipe.display.FurnaceRecipeDisplay;

import java.util.List;
import java.util.Optional;

public class FixedClientsidedSmokingDisplay extends ClientsidedCookingDisplay.Smoking {
    public static final DisplaySerializer<FixedClientsidedSmokingDisplay> SERIALIZER = serializer(FixedClientsidedSmokingDisplay::new);

    public FixedClientsidedSmokingDisplay(FurnaceRecipeDisplay recipe, Optional<NetworkRecipeId> id) {
        super(recipe, id);
    }

    public FixedClientsidedSmokingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<NetworkRecipeId> id) {
        super(inputs, outputs, id);
    }

    @Override
    public DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
