package anya.pizza.houseki;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ChunkSectionLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class HousekiClient implements ClientModInitializer {
    /**
     * Initializes client-side resources by registering screens and configuring block render layers.
     *
     * Registers the Crusher GUI for its screen handler and maps render layers:
     * - ALUMINUM_GLASS and ALUMINUM_GLASS_PANE -> translucent
     * - ALUMINUM_DOOR and ALUMINUM_TRAPDOOR -> cutout
     */
    @Override
    public void onInitializeClient() {

        MenuScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);

        ChunkSectionLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS, ChunkSectionLayer.TRANSLUCENT);
        ChunkSectionLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS_PANE, ChunkSectionLayer.TRANSLUCENT);
        ChunkSectionLayerMap.putBlock(ModBlocks.ALUMINUM_DOOR, ChunkSectionLayer.CUTOUT);
        ChunkSectionLayerMap.putBlock(ModBlocks.ALUMINUM_TRAPDOOR, ChunkSectionLayer.CUTOUT);

    }
}