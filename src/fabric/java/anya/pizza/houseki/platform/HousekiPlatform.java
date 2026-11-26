package anya.pizza.houseki.platform;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.world.item.CreativeModeTab;

public final class HousekiPlatform {
    private HousekiPlatform() {}

    public static CreativeModeTab.Builder creativeTab() {
        return FabricCreativeModeTab.builder();
    }
}
