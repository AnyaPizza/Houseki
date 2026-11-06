package anya.pizza.houseki.compat.jei;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.recipe.*;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.util.List;

@JeiPlugin
public class HousekiJeiPlugin implements IModPlugin {
    public static final IRecipeHolderType<CrusherRecipe> CRUSHING = IRecipeHolderType.create(ModTypes.CRUSHER_TYPE);
    public static final IRecipeHolderType<FoundryMeltingRecipe> MELTING = IRecipeHolderType.create(ModTypes.MELTING_FOUNDRY_TYPE);
    public static final IRecipeHolderType<FoundryCastingRecipe> CASTING = IRecipeHolderType.create(ModTypes.CASTING_FOUNDRY_TYPE);

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new MachineRecipeCategory<>(CRUSHING, "crushing", CrusherRecipe::inputItem, CrusherRecipe::output),
                new MachineRecipeCategory<>(MELTING, "melting", FoundryMeltingRecipe::inputMeltingItem, FoundryMeltingRecipe::output),
                new MachineRecipeCategory<>(CASTING, "casting", FoundryCastingRecipe::inputCastingItem, FoundryCastingRecipe::output));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var level = Minecraft.getInstance().level;
        if (level == null) return;
        var recipes = level.recipeAccess().getSynchronizedRecipes();
        registration.addRecipes(CRUSHING, List.copyOf(recipes.getAllOfType(ModTypes.CRUSHER_TYPE)));
        registration.addRecipes(MELTING, List.copyOf(recipes.getAllOfType(ModTypes.MELTING_FOUNDRY_TYPE)));
        registration.addRecipes(CASTING, List.copyOf(recipes.getAllOfType(ModTypes.CASTING_FOUNDRY_TYPE)));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(CRUSHING, ModBlocks.CRUSHER);
        registration.addCraftingStation(MELTING, ModBlocks.FOUNDRY);
        registration.addCraftingStation(CASTING, ModBlocks.FOUNDRY);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrusherScreen.class, 79, 29, 24, 16, CRUSHING);
        registration.addRecipeClickArea(FoundryScreen.class, 49, 35, 24, 16, MELTING);
        registration.addRecipeClickArea(FoundryScreen.class, 102, 35, 24, 16, CASTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CrusherScreenHandler.class,
                ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CRUSHING, 0, 1, 4, 36);
        registration.addRecipeTransferHandler(FoundryScreenHandler.class,
                ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, MELTING, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(FoundryScreenHandler.class,
                ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, CASTING, 2, 1, 5, 36);
    }
}
