package anya.pizza.houseki;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;

public class HousekiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);

        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_TRAPDOOR, BlockRenderLayer.CUTOUT);

    }
}
