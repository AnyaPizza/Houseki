package anya.pizza.houseki.datagen;

import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.references.ItemIds;
import net.minecraft.world.item.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.getRK(ModItems.PINKU))
                .add(ModItems.getRK(ModItems.TUNGSTEN))
                .add(ModItems.getRK(ModItems.ALUMINUM))
                .add(ModItems.getRK(ModItems.SAPPHIRE))
                .add(ModItems.getRK(ModItems.JADEITE))
                .add(ModItems.getRK(ModItems.PLATINUM))
                .add(ModItems.getRK(ModItems.STEEL))
                .add(ModItems.getRK(ModItems.CAST_STEEL))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_INGOT))
                .add(ModItems.getRK(ModItems.SUGILITE))
                .add(ModItems.getRK(ModItems.BISMUTH));

        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE))
                .add(ModItems.getRK(ModItems.PINKU))
                .add(ModItems.getRK(ModItems.SAPPHIRE))
                .add(ModItems.getRK(ModItems.NEPHRITE))
                .add(ModItems.getRK(ModItems.JADEITE))
                .add(ModItems.getRK(ModItems.CAST_STEEL))
                .add(ModItems.getRK(ModItems.SUGILITE))
                .add(ModItems.getRK(ModItems.BISMUTH));

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.getRK(ModItems.PINKU_HELMET))
                .add(ModItems.getRK(ModItems.PINKU_CHESTPLATE))
                .add(ModItems.getRK(ModItems.PINKU_LEGGINGS))
                .add(ModItems.getRK(ModItems.PINKU_BOOTS))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_HELMET))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_BOOTS))
                .add(ModItems.getRK(ModItems.TUNGSTEN_HELMET))
                .add(ModItems.getRK(ModItems.TUNGSTEN_CHESTPLATE))
                .add(ModItems.getRK(ModItems.TUNGSTEN_LEGGINGS))
                .add(ModItems.getRK(ModItems.TUNGSTEN_BOOTS))
                .add(ModItems.getRK(ModItems.ALUMINUM_HELMET))
                .add(ModItems.getRK(ModItems.ALUMINUM_CHESTPLATE))
                .add(ModItems.getRK(ModItems.ALUMINUM_LEGGINGS))
                .add(ModItems.getRK(ModItems.ALUMINUM_BOOTS))
                .add(ModItems.getRK(ModItems.SAPPHIRE_HELMET))
                .add(ModItems.getRK(ModItems.SAPPHIRE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.SAPPHIRE_LEGGINGS))
                .add(ModItems.getRK(ModItems.SAPPHIRE_BOOTS))
                .add(ModItems.getRK(ModItems.NEPHRITE_HELMET))
                .add(ModItems.getRK(ModItems.NEPHRITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.NEPHRITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.NEPHRITE_BOOTS))
                .add(ModItems.getRK(ModItems.JADEITE_HELMET))
                .add(ModItems.getRK(ModItems.JADEITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.JADEITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.JADEITE_BOOTS))
                .add(ModItems.getRK(ModItems.PLATINUM_HELMET))
                .add(ModItems.getRK(ModItems.PLATINUM_CHESTPLATE))
                .add(ModItems.getRK(ModItems.PLATINUM_LEGGINGS))
                .add(ModItems.getRK(ModItems.PLATINUM_BOOTS))
                .add(ModItems.getRK(ModItems.CAST_STEEL_HELMET))
                .add(ModItems.getRK(ModItems.CAST_STEEL_CHESTPLATE))
                .add(ModItems.getRK(ModItems.CAST_STEEL_LEGGINGS))
                .add(ModItems.getRK(ModItems.CAST_STEEL_BOOTS))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_HELMET))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_CHESTPLATE))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_LEGGINGS))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_BOOTS));

        tag(ItemTags.AXES)
                .add(ModItems.getRK(ModItems.PINKU_AXE))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_AXE))
                .add(ModItems.getRK(ModItems.TUNGSTEN_AXE))
                .add(ModItems.getRK(ModItems.ALUMINUM_AXE))
                .add(ModItems.getRK(ModItems.SAPPHIRE_AXE))
                .add(ModItems.getRK(ModItems.NEPHRITE_AXE))
                .add(ModItems.getRK(ModItems.JADEITE_AXE))
                .add(ModItems.getRK(ModItems.PLATINUM_AXE))
                .add(ModItems.getRK(ModItems.CAST_STEEL_AXE))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_AXE));

        tag(ItemTags.HOES)
                .add(ModItems.getRK(ModItems.PINKU_HOE))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_HOE))
                .add(ModItems.getRK(ModItems.TUNGSTEN_HOE))
                .add(ModItems.getRK(ModItems.ALUMINUM_HOE))
                .add(ModItems.getRK(ModItems.SAPPHIRE_HOE))
                .add(ModItems.getRK(ModItems.NEPHRITE_HOE))
                .add(ModItems.getRK(ModItems.JADEITE_HOE))
                .add(ModItems.getRK(ModItems.PLATINUM_HOE))
                .add(ModItems.getRK(ModItems.CAST_STEEL_HOE))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_HOE));

        tag(ItemTags.SHOVELS)
                .add(ModItems.getRK(ModItems.PINKU_SHOVEL))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_SHOVEL))
                .add(ModItems.getRK(ModItems.TUNGSTEN_SHOVEL))
                .add(ModItems.getRK(ModItems.ALUMINUM_SHOVEL))
                .add(ModItems.getRK(ModItems.SAPPHIRE_SHOVEL))
                .add(ModItems.getRK(ModItems.NEPHRITE_SHOVEL))
                .add(ModItems.getRK(ModItems.JADEITE_SHOVEL))
                .add(ModItems.getRK(ModItems.PLATINUM_SHOVEL))
                .add(ModItems.getRK(ModItems.CAST_STEEL_SHOVEL))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_SHOVEL));

        tag(ItemTags.SWORDS)
                .add(ModItems.getRK(ModItems.PINKU_SWORD))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_SWORD))
                .add(ModItems.getRK(ModItems.TUNGSTEN_SWORD))
                .add(ModItems.getRK(ModItems.ALUMINUM_SWORD))
                .add(ModItems.getRK(ModItems.SAPPHIRE_SWORD))
                .add(ModItems.getRK(ModItems.NEPHRITE_SWORD))
                .add(ModItems.getRK(ModItems.JADEITE_SWORD))
                .add(ModItems.getRK(ModItems.PLATINUM_SWORD))
                .add(ModItems.getRK(ModItems.CAST_STEEL_SWORD))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_SWORD));

        tag(ItemTags.PICKAXES)
                .add(ModItems.getRK(ModItems.PINKU_PICKAXE))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_PICKAXE))
                .add(ModItems.getRK(ModItems.TUNGSTEN_PICKAXE))
                .add(ModItems.getRK(ModItems.ALUMINUM_PICKAXE))
                .add(ModItems.getRK(ModItems.SAPPHIRE_PICKAXE))
                .add(ModItems.getRK(ModItems.NEPHRITE_PICKAXE))
                .add(ModItems.getRK(ModItems.JADEITE_PICKAXE))
                .add(ModItems.getRK(ModItems.PLATINUM_PICKAXE))
                .add(ModItems.getRK(ModItems.CAST_STEEL_PICKAXE))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_PICKAXE));

        tag(ModTags.Items.DRILLS)
                .add(ModItems.getRK(ModItems.SIMPLE_DRILL_HEAD))
                .add(ModItems.getRK(ModItems.SIMPLE_TUNGSTEN_DRILL))
                .add(ModItems.getRK(ModItems.ENHANCED_DRILL_HEAD))
                .add(ModItems.getRK(ModItems.ENHANCED_TUNGSTEN_DRILL))
                .add(ModItems.getRK(ModItems.ADVANCED_DRILL_HEAD))
                .add(ModItems.getRK(ModItems.ADVANCED_TUNGSTEN_DRILL))
                .add(ModItems.getRK(ModItems.PREMIUM_DRILL_HEAD))
                .add(ModItems.getRK(ModItems.PREMIUM_TUNGSTEN_DRILL))
                .add(ModItems.getRK(ModItems.SIMPLE_DIAMOND_DRILL))
                .add(ModItems.getRK(ModItems.ENHANCED_DIAMOND_DRILL))
                .add(ModItems.getRK(ModItems.ADVANCED_DIAMOND_DRILL))
                .add(ModItems.getRK(ModItems.PREMIUM_DIAMOND_DRILL));

        tag(ModTags.Items.DRILL_BITS)
                .add(ModItems.getRK(ModItems.TUNGSTEN_DRILL_BIT))
                .add(ModItems.getRK(ModItems.DIAMOND_DRILL_BIT));

        tag(ModTags.Items.SMITHING_TEMPLATES)
                .add(ModItems.getRK(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE))
                .add(ModItems.getRK(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE));

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.getRK(ModItems.PINKU_BOOTS))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_BOOTS))
                .add(ModItems.getRK(ModItems.TUNGSTEN_BOOTS))
                .add(ModItems.getRK(ModItems.ALUMINUM_BOOTS))
                .add(ModItems.getRK(ModItems.SAPPHIRE_BOOTS))
                .add(ModItems.getRK(ModItems.NEPHRITE_BOOTS))
                .add(ModItems.getRK(ModItems.JADEITE_BOOTS))
                .add(ModItems.getRK(ModItems.PLATINUM_BOOTS))
                .add(ModItems.getRK(ModItems.CAST_STEEL_BOOTS))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_BOOTS));

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.getRK(ModItems.PINKU_LEGGINGS))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.TUNGSTEN_LEGGINGS))
                .add(ModItems.getRK(ModItems.ALUMINUM_LEGGINGS))
                .add(ModItems.getRK(ModItems.SAPPHIRE_LEGGINGS))
                .add(ModItems.getRK(ModItems.NEPHRITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.JADEITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.PLATINUM_LEGGINGS))
                .add(ModItems.getRK(ModItems.CAST_STEEL_LEGGINGS))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_LEGGINGS));

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.getRK(ModItems.PINKU_CHESTPLATE))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.TUNGSTEN_CHESTPLATE))
                .add(ModItems.getRK(ModItems.ALUMINUM_CHESTPLATE))
                .add(ModItems.getRK(ModItems.SAPPHIRE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.NEPHRITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.JADEITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.PLATINUM_CHESTPLATE))
                .add(ModItems.getRK(ModItems.CAST_STEEL_CHESTPLATE))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_CHESTPLATE));

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.getRK(ModItems.PINKU_HELMET))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_HELMET))
                .add(ModItems.getRK(ModItems.TUNGSTEN_HELMET))
                .add(ModItems.getRK(ModItems.ALUMINUM_HELMET))
                .add(ModItems.getRK(ModItems.SAPPHIRE_HELMET))
                .add(ModItems.getRK(ModItems.NEPHRITE_HELMET))
                .add(ModItems.getRK(ModItems.JADEITE_HELMET))
                .add(ModItems.getRK(ModItems.PLATINUM_HELMET))
                .add(ModItems.getRK(ModItems.CAST_STEEL_HELMET))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_HELMET));

        tag(ModTags.Items.MOB_ARMORS)
                .add(ModItems.getRK(ModItems.PINKU_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.TUNGSTEN_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.ALUMINUM_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.SAPPHIRE_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.NEPHRITE_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.JADEITE_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.PLATINUM_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.CAST_STEEL_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.PINKU_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.TUNGSTEN_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.ALUMINUM_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.SAPPHIRE_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.NEPHRITE_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.JADEITE_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.PLATINUM_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.CAST_STEEL_NAUTILUS_ARMOR))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_NAUTILUS_ARMOR));

        tag(ModTags.Items.PINKU_REPAIR)
                .add(ModItems.getRK(ModItems.PINKU));
        tag(ModTags.Items.RAINBOW_REPAIR)
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE));
        tag(ModTags.Items.TUNGSTEN_REPAIR)
                .add(ModItems.getRK(ModItems.TUNGSTEN));
        tag(ModTags.Items.ALUMINUM_REPAIR)
                .add(ModItems.getRK(ModItems.ALUMINUM));
        tag(ModTags.Items.SAPPHIRE_REPAIR)
                .add(ModItems.getRK(ModItems.SAPPHIRE));
        tag(ModTags.Items.NEPHRITE_REPAIR)
                .add(ModItems.getRK(ModItems.NEPHRITE));
        tag(ModTags.Items.JADEITE_REPAIR)
                .add(ModItems.getRK(ModItems.JADEITE));
        tag(ModTags.Items.PLATINUM_REPAIR)
                .add(ModItems.getRK(ModItems.PLATINUM));
        tag(ModTags.Items.CAST_STEEL_REPAIR)
                .add(ModItems.getRK(ModItems.CAST_STEEL));
        tag(ModTags.Items.METEORIC_IRON_REPAIR)
                .add(ModItems.getRK(ModItems.METEORIC_IRON_INGOT));
        tag(ModTags.Items.DIAMOND_REPAIR)
                .add(ItemIds.DIAMOND);

        tag(ModTags.Items.ARMOR)
                .add(ModItems.getRK(ModItems.PINKU_HELMET))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_HELMET))
                .add(ModItems.getRK(ModItems.TUNGSTEN_HELMET))
                .add(ModItems.getRK(ModItems.ALUMINUM_HELMET))
                .add(ModItems.getRK(ModItems.SAPPHIRE_HELMET))
                .add(ModItems.getRK(ModItems.NEPHRITE_HELMET))
                .add(ModItems.getRK(ModItems.JADEITE_HELMET))
                .add(ModItems.getRK(ModItems.PLATINUM_HELMET))
                .add(ModItems.getRK(ModItems.CAST_STEEL_HELMET))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_HELMET))
                .add(ModItems.getRK(ModItems.PINKU_CHESTPLATE))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.TUNGSTEN_CHESTPLATE))
                .add(ModItems.getRK(ModItems.ALUMINUM_CHESTPLATE))
                .add(ModItems.getRK(ModItems.SAPPHIRE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.NEPHRITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.JADEITE_CHESTPLATE))
                .add(ModItems.getRK(ModItems.PLATINUM_CHESTPLATE))
                .add(ModItems.getRK(ModItems.CAST_STEEL_CHESTPLATE))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_CHESTPLATE))
                .add(ModItems.getRK(ModItems.PINKU_LEGGINGS))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.TUNGSTEN_LEGGINGS))
                .add(ModItems.getRK(ModItems.ALUMINUM_LEGGINGS))
                .add(ModItems.getRK(ModItems.SAPPHIRE_LEGGINGS))
                .add(ModItems.getRK(ModItems.NEPHRITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.JADEITE_LEGGINGS))
                .add(ModItems.getRK(ModItems.PLATINUM_LEGGINGS))
                .add(ModItems.getRK(ModItems.CAST_STEEL_LEGGINGS))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_LEGGINGS))
                .add(ModItems.getRK(ModItems.PINKU_BOOTS))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_BOOTS))
                .add(ModItems.getRK(ModItems.TUNGSTEN_BOOTS))
                .add(ModItems.getRK(ModItems.ALUMINUM_BOOTS))
                .add(ModItems.getRK(ModItems.SAPPHIRE_BOOTS))
                .add(ModItems.getRK(ModItems.NEPHRITE_BOOTS))
                .add(ModItems.getRK(ModItems.JADEITE_BOOTS))
                .add(ModItems.getRK(ModItems.PLATINUM_BOOTS))
                .add(ModItems.getRK(ModItems.CAST_STEEL_BOOTS))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_BOOTS));

        tag(ItemTags.SPEARS)
                .add(ModItems.getRK(ModItems.PINKU_SPEAR))
                .add(ModItems.getRK(ModItems.RAINBOW_PYRITE_SPEAR))
                .add(ModItems.getRK(ModItems.TUNGSTEN_SPEAR))
                .add(ModItems.getRK(ModItems.ALUMINUM_SPEAR))
                .add(ModItems.getRK(ModItems.SAPPHIRE_SPEAR))
                .add(ModItems.getRK(ModItems.NEPHRITE_SPEAR))
                .add(ModItems.getRK(ModItems.NEPHRITE_SPEAR))
                .add(ModItems.getRK(ModItems.JADEITE_SPEAR))
                .add(ModItems.getRK(ModItems.PLATINUM_SPEAR))
                .add(ModItems.getRK(ModItems.CAST_STEEL_SPEAR))
                .add(ModItems.getRK(ModItems.METEORIC_IRON_SPEAR));

        tag(ModTags.Items.CASTS)
                .add(ModItems.getRK(ModItems.INGOT_CAST))
                .add(ModItems.getRK(ModItems.PICKAXE_HEAD_CAST))
                .add(ModItems.getRK(ModItems.AXE_HEAD_CAST))
                .add(ModItems.getRK(ModItems.SHOVEL_HEAD_CAST))
                .add(ModItems.getRK(ModItems.SWORD_HEAD_CAST))
                .add(ModItems.getRK(ModItems.HOE_HEAD_CAST))
                .add(ModItems.getRK(ModItems.SPEAR_HEAD_CAST))
                .add(ModItems.getRK(ModItems.HELMET_CAST))
                .add(ModItems.getRK(ModItems.CHESTPLATE_CAST))
                .add(ModItems.getRK(ModItems.LEGGINGS_CAST))
                .add(ModItems.getRK(ModItems.BOOTS_CAST));
    }
}