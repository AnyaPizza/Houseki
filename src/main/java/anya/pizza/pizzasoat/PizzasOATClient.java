package anya.pizza.pizzasoat;

import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.screen.ModScreenHandlers;
import anya.pizza.pizzasoat.screen.custom.CrusherScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;

public class PizzasOATClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);

        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALUMINUM_TRAPDOOR, BlockRenderLayer.CUTOUT);

    }
}
