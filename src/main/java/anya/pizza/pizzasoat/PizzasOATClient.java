package anya.pizza.pizzasoat;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.screen.ModScreenHandlers;
import anya.pizza.pizzasoat.screen.custom.CrusherScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class PizzasOATClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALUMINUM_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALUMINUM_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALUMINUM_TRAPDOOR, RenderLayer.getCutout());
        /*
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STEEL_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STEEL_POWERED_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STEEL_ACTIVATOR_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STEEL_DETECTOR_RAIL, RenderLayer.getCutout());
        */
    }
}
