package anya.pizza.houseki;

import anya.pizza.houseki.block.ModBlocks;
import anya.pizza.houseki.block.entity.ModBlockEntities;
import anya.pizza.houseki.effect.ModEffects;
import anya.pizza.houseki.item.ModItemGroups;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.ModSerializer;
import anya.pizza.houseki.recipe.ModTypes;
import anya.pizza.houseki.screen.ModScreenHandlers;
import anya.pizza.houseki.trim.TrimEffectHandler;
import anya.pizza.houseki.util.ADUsageEvent;
import anya.pizza.houseki.util.EDUsageEvent;
import anya.pizza.houseki.util.PDUsageEvent;
import anya.pizza.houseki.util.ModLootTableModifiers;
import anya.pizza.houseki.world.structure.ModStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@Mod(Houseki.MOD_ID)
public final class HousekiNeoForge {
    public HousekiNeoForge(IEventBus modBus) {
        modBus.addListener(HousekiNeoForge::register);
        modBus.addListener(HousekiNeoForge::registerCapabilities);
        NeoForge.EVENT_BUS.addListener(HousekiNeoForge::addLoot);
        NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, HousekiNeoForge::breakBlock);
        NeoForge.EVENT_BUS.addListener((ServerTickEvent.Post event) -> TrimEffectHandler.tick(event.getServer()));
        NeoForge.EVENT_BUS.addListener((OnDatapackSyncEvent event) -> event.sendRecipes(
                ModTypes.CRUSHER_TYPE, ModTypes.MELTING_FOUNDRY_TYPE, ModTypes.CASTING_FOUNDRY_TYPE));
    }

    private static void register(RegisterEvent event) {
        var key = event.getRegistryKey();
        if (key.equals(Registries.BLOCK)) ModBlocks.registerModBlocks();
        if (key.equals(Registries.ITEM)) {
            ModItems.registerModItems();
            ModBlocks.registerBlockItems();
        }
        if (key.equals(Registries.CREATIVE_MODE_TAB)) ModItemGroups.registerItemGroups();
        if (key.equals(Registries.BLOCK_ENTITY_TYPE)) ModBlockEntities.registerBlockEntities();
        if (key.equals(Registries.MENU)) ModScreenHandlers.registerScreenHandlers();
        if (key.equals(Registries.RECIPE_SERIALIZER)) ModSerializer.registerSerializers();
        if (key.equals(Registries.RECIPE_TYPE)) ModTypes.registerRecipeTypes();
        if (key.equals(Registries.STRUCTURE_TYPE)) ModStructures.register();
        if (key.equals(Registries.STRUCTURE_PIECE)) ModStructures.registerPiece();
        if (key.equals(Registries.MOB_EFFECT)) ModEffects.registerEffects();
    }

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, ModBlockEntities.CRUSHER_BE, WorldlyContainerWrapper::new);
        event.registerBlockEntity(Capabilities.Item.BLOCK, ModBlockEntities.FOUNDRY_BE, WorldlyContainerWrapper::new);
    }

    private static void addLoot(LootTableLoadEvent event) {
        ModLootTableModifiers.addPools(event.getName(), event.getTable()::addPool, event.getRegistries());
    }

    private static void breakBlock(BreakBlockEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            EDUsageEvent.breakExtraBlocks(player.level(), player, event.getPos());
            ADUsageEvent.breakExtraBlocks(player.level(), player, event.getPos());
            PDUsageEvent.breakExtraBlocks(player.level(), player, event.getPos());
        }
    }
}
