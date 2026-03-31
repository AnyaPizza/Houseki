package anya.pizza.houseki;

import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import anya.pizza.houseki.screen.custom.FoundryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class HousekiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        MenuScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        HandledScreens.register(ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, FoundryScreen::new);

        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_TRAPDOOR, BlockRenderLayer.CUTOUT);

    }
}