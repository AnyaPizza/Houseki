package anya.pizza.houseki.platform;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.Collection;

public final class ClientMachineRecipes {
    private static RecipeMap recipes = RecipeMap.EMPTY;

    private ClientMachineRecipes() {}

    public static void register() {
        NeoForge.EVENT_BUS.addListener((RecipesReceivedEvent event) -> recipes = event.getRecipeMap());
        NeoForge.EVENT_BUS.addListener((ClientPlayerNetworkEvent.LoggingOut event) -> recipes = RecipeMap.EMPTY);
    }

    public static <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> get(RecipeType<T> type) {
        return recipes.byType(type);
    }

    public static void onSync(Runnable callback) {
        NeoForge.EVENT_BUS.addListener((RecipesReceivedEvent event) -> callback.run());
    }
}
