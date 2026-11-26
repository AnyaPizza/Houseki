package anya.pizza.houseki.platform;

import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collection;
import java.util.List;

public final class ClientMachineRecipes {
    private ClientMachineRecipes() {}

    public static <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> get(RecipeType<T> type) {
        var level = Minecraft.getInstance().level;
        return level == null ? List.of() : level.recipeAccess().getSynchronizedRecipes().getAllOfType(type);
    }

    public static void onSync(Runnable callback) {
        ClientRecipeSynchronizedEvent.EVENT.register((client, recipes) -> callback.run());
    }
}
