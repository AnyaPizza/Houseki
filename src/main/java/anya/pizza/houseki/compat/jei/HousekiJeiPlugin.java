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
import anya.pizza.houseki.platform.ClientMachineRecipes;
import net.minecraft.resources.Identifier;

import java.util.List;

@JeiPlugin
public class HousekiJeiPlugin implements IModPlugin {
    // JEI discovers plugins before NeoForge registers recipe types.
    private static final class Types {
        private static final IRecipeHolderType<CrusherRecipe> CRUSHING = IRecipeHolderType.create(ModTypes.CRUSHER_TYPE);
        private static final IRecipeHolderType<FoundryMeltingRecipe> MELTING = IRecipeHolderType.create(ModTypes.MELTING_FOUNDRY_TYPE);
        private static final IRecipeHolderType<FoundryCastingRecipe> CASTING = IRecipeHolderType.create(ModTypes.CASTING_FOUNDRY_TYPE);
    }

    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new MachineRecipeCategory<>(Types.CRUSHING, "crushing", CrusherRecipe::inputItem, CrusherRecipe::output),
                new MachineRecipeCategory<>(Types.MELTING, "melting", FoundryMeltingRecipe::inputMeltingItem, FoundryMeltingRecipe::output),
                new MachineRecipeCategory<>(Types.CASTING, "casting", FoundryCastingRecipe::inputCastingItem, FoundryCastingRecipe::output));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(Types.CRUSHING, List.copyOf(ClientMachineRecipes.get(ModTypes.CRUSHER_TYPE)));
        registration.addRecipes(Types.MELTING, List.copyOf(ClientMachineRecipes.get(ModTypes.MELTING_FOUNDRY_TYPE)));
        registration.addRecipes(Types.CASTING, List.copyOf(ClientMachineRecipes.get(ModTypes.CASTING_FOUNDRY_TYPE)));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(Types.CRUSHING, ModBlocks.CRUSHER);
        registration.addCraftingStation(Types.MELTING, ModBlocks.FOUNDRY);
        registration.addCraftingStation(Types.CASTING, ModBlocks.FOUNDRY);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrusherScreen.class, 79, 29, 24, 16, Types.CRUSHING);
        registration.addRecipeClickArea(FoundryScreen.class, 49, 35, 24, 16, Types.MELTING);
        registration.addRecipeClickArea(FoundryScreen.class, 102, 35, 24, 16, Types.CASTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CrusherScreenHandler.class,
                ModScreenHandlers.CRUSHER_SCREEN_HANDLER, Types.CRUSHING, 0, 1, 4, 36);
        registration.addRecipeTransferHandler(FoundryScreenHandler.class,
                ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, Types.MELTING, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(FoundryScreenHandler.class,
                ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, Types.CASTING, 2, 1, 5, 36);
    }
}
