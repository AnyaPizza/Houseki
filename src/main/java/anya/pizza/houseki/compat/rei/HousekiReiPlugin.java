package anya.pizza.houseki.compat.rei;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.recipe.ModTypes;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import anya.pizza.houseki.screen.custom.FoundryScreen;
import anya.pizza.houseki.screen.custom.FoundryScreenHandler;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.client.Minecraft;

public final class HousekiReiPlugin implements REIClientPlugin {
    static final CategoryIdentifier<MachineDisplay> CRUSHING = CategoryIdentifier.of(Houseki.MOD_ID, "crushing");
    static final CategoryIdentifier<MachineDisplay> MELTING = CategoryIdentifier.of(Houseki.MOD_ID, "melting");
    static final CategoryIdentifier<MachineDisplay> CASTING = CategoryIdentifier.of(Houseki.MOD_ID, "casting");

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new MachineCategory(CRUSHING, "crushing", ModBlocks.CRUSHER));
        registry.add(new MachineCategory(MELTING, "melting", ModBlocks.FOUNDRY));
        registry.add(new MachineCategory(CASTING, "casting", ModBlocks.FOUNDRY));
        registry.addWorkstations(CRUSHING, EntryStacks.of(ModBlocks.CRUSHER));
        registry.addWorkstations(MELTING, EntryStacks.of(ModBlocks.FOUNDRY));
        registry.addWorkstations(CASTING, EntryStacks.of(ModBlocks.FOUNDRY));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        var level = Minecraft.getInstance().level;
        if (level == null) return;
        var recipes = level.recipeAccess().getSynchronizedRecipes();
        for (var holder : recipes.getAllOfType(ModTypes.CRUSHER_TYPE)) {
            registry.add(MachineDisplay.crushing(holder), holder);
        }
        for (var holder : recipes.getAllOfType(ModTypes.MELTING_FOUNDRY_TYPE)) {
            registry.add(MachineDisplay.melting(holder), holder);
        }
        for (var holder : recipes.getAllOfType(ModTypes.CASTING_FOUNDRY_TYPE)) {
            registry.add(MachineDisplay.casting(holder), holder);
        }
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle((screen.width - 176) / 2 + 79,
                (screen.height - 176) / 2 + 29, 24, 16), CrusherScreen.class, CRUSHING);
        registry.registerClickArea(screen -> new Rectangle((screen.width - 176) / 2 + 49,
                (screen.height - 176) / 2 + 35, 24, 16), FoundryScreen.class, MELTING);
        registry.registerClickArea(screen -> new Rectangle((screen.width - 176) / 2 + 102,
                (screen.height - 176) / 2 + 35, 24, 16), FoundryScreen.class, CASTING);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        registry.register(SimpleTransferHandler.create(CrusherScreenHandler.class, CRUSHING,
                new SimpleTransferHandler.IntRange(0, 1)));
        registry.register(SimpleTransferHandler.create(FoundryScreenHandler.class, MELTING,
                new SimpleTransferHandler.IntRange(0, 1)));
        registry.register(SimpleTransferHandler.create(FoundryScreenHandler.class, CASTING,
                new SimpleTransferHandler.IntRange(2, 3)));
    }
}
