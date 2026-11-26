package anya.pizza.houseki.recipe;

import anya.pizza.houseki.item.ModItems;
import net.minecraft.world.item.Item;

import java.util.Map;

public final class FoundryRecipes {
    public record CastResult(Item steel, Item meteoricIron) {}

    public static final Map<Item, CastResult> CASTS = Map.ofEntries(
            Map.entry(ModItems.INGOT_CAST, new CastResult(ModItems.CAST_STEEL, ModItems.METEORIC_IRON_INGOT)),
            Map.entry(ModItems.PICKAXE_HEAD_CAST, new CastResult(ModItems.CS_PICKAXE_HEAD, ModItems.MI_PICKAXE_HEAD)),
            Map.entry(ModItems.AXE_HEAD_CAST, new CastResult(ModItems.CS_AXE_HEAD, ModItems.MI_AXE_HEAD)),
            Map.entry(ModItems.SHOVEL_HEAD_CAST, new CastResult(ModItems.CS_SHOVEL_HEAD, ModItems.MI_SHOVEL_HEAD)),
            Map.entry(ModItems.SWORD_HEAD_CAST, new CastResult(ModItems.CS_SWORD_HEAD, ModItems.MI_SWORD_HEAD)),
            Map.entry(ModItems.HOE_HEAD_CAST, new CastResult(ModItems.CS_HOE_HEAD, ModItems.MI_HOE_HEAD)),
            Map.entry(ModItems.SPEAR_HEAD_CAST, new CastResult(ModItems.CS_SPEAR_HEAD, ModItems.MI_SPEAR_HEAD)),
            Map.entry(ModItems.HELMET_CAST, new CastResult(ModItems.CAST_STEEL_HELMET, ModItems.METEORIC_IRON_HELMET)),
            Map.entry(ModItems.CHESTPLATE_CAST, new CastResult(ModItems.CAST_STEEL_CHESTPLATE, ModItems.METEORIC_IRON_CHESTPLATE)),
            Map.entry(ModItems.LEGGINGS_CAST, new CastResult(ModItems.CAST_STEEL_LEGGINGS, ModItems.METEORIC_IRON_LEGGINGS)),
            Map.entry(ModItems.BOOTS_CAST, new CastResult(ModItems.CAST_STEEL_BOOTS, ModItems.METEORIC_IRON_BOOTS))
    );

    private FoundryRecipes() {}
}
