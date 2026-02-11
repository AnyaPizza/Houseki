package anya.pizza.houseki;

import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class HousekiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        MenuScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);

    }
}
