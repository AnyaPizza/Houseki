package anya.pizza.houseki.screen;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import anya.pizza.houseki.screen.custom.FoundryScreenHandler;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.Identifier;

public class ModScreenHandlers {
    public static final MenuType<CrusherScreenHandler> CRUSHER_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "crusher"),
                 new ExtendedMenuType<>(CrusherScreenHandler::new, BlockPos.STREAM_CODEC));

    public static final MenuType<FoundryScreenHandler> FOUNDRY_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "foundry_screen_handler"),
                    new ExtendedMenuType<>(FoundryScreenHandler::new, BlockPos.STREAM_CODEC));

    public static void registerScreenHandlers() {
        Houseki.LOGGER.info("Registering Screen Handlers for " + Houseki.MOD_ID);
    }
}