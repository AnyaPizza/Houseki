package anya.pizza.houseki.screen;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.screen.custom.CrusherScreenHandler;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;

public class ModScreenHandlers {
    public static final MenuType<CrusherScreenHandler> CRUSHER_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "crusher_screen_handler"),
                    new ExtendedMenuProvider<>(CrusherScreenHandler::new, BlockPos.STREAM_CODEC));

    /**
     * Registers the mod's screen handler types.
     *
     * <p>Currently this method emits an informational log entry indicating registration for the mod ID.</p>
     */
    public static void registerScreenHandlers() {
        Houseki.LOGGER.info("Registering Screen Handlers for " + Houseki.MOD_ID);
    }
}