package anya.pizza.houseki;

import anya.pizza.houseki.platform.ClientMachineRecipes;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.screen.custom.CrusherScreen;
import anya.pizza.houseki.screen.custom.FoundryScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(value = Houseki.MOD_ID, dist = Dist.CLIENT)
public final class HousekiNeoForgeClient {
    public HousekiNeoForgeClient(IEventBus modBus) {
        ClientMachineRecipes.register();
        modBus.addListener(HousekiNeoForgeClient::registerScreens);
    }

    private static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        event.register(ModScreenHandlers.FOUNDRY_SCREEN_HANDLER, FoundryScreen::new);
    }
}
