package anya.pizza.houseki;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import anya.pizza.houseki.screen.custom.FoundryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;

public class HousekiClient implements ClientModInitializer {
    /**
     * Performs client-side initialization for the mod.
     *
     * Registers screen handlers for the crusher and foundry screens and configures
     * render layers for aluminum glass, glass panes, doors, and trapdoors.
     */
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        HandledScreens.register(ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, FoundryScreen::new);

        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_TRAPDOOR, BlockRenderLayer.CUTOUT);

    }
}