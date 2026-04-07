package anya.pizza.houseki.datagen;

import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    /**
     * Populate item tag groups with mod and vanilla items used for data generation.
     *
     * Registers items into standard Minecraft and mod-specific item tags (armor, tools,
     * repair materials, drills, casts, trim materials, beacon payments, etc.) so the
     * generated data contains the correct tag memberships.
     *
     * @param wrapperLookup lookup of registries used when building tag values
     */
    @Override
    protected void configure(RegistryWrapper.@NonNull WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.PINKU)
                .add(ModItems.TUNGSTEN)
                .add(ModItems.ALUMINUM)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.JADEITE)
                .add(ModItems.PLATINUM)
                .add(ModItems.STEEL)
                .add(ModItems.CAST_STEEL)
                .add(ModItems.METEORIC_IRON_INGOT)
                .add(ModItems.SUGILITE)
                .add(ModItems.BISMUTH);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.RAINBOW_PYRITE)
                .add(ModItems.PINKU)
                .add(ModItems.SAPPHIRE)
                .add(ModItems.NEPHRITE)
                .add(ModItems.JADEITE)
                .add(ModItems.CAST_STEEL)
                .add(ModItems.SUGILITE)
                .add(ModItems.BISMUTH);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PINKU_HELMET)
                .add(ModItems.PINKU_CHESTPLATE)
                .add(ModItems.PINKU_LEGGINGS)
                .add(ModItems.PINKU_BOOTS)
                .add(ModItems.RAINBOW_PYRITE_HELMET)
                .add(ModItems.RAINBOW_PYRITE_CHESTPLATE)
                .add(ModItems.RAINBOW_PYRITE_LEGGINGS)
                .add(ModItems.RAINBOW_PYRITE_BOOTS)
                .add(ModItems.TUNGSTEN_HELMET)
                .add(ModItems.TUNGSTEN_CHESTPLATE)
                .add(ModItems.TUNGSTEN_LEGGINGS)
                .add(ModItems.TUNGSTEN_BOOTS)
                .add(ModItems.ALUMINUM_HELMET)
                .add(ModItems.ALUMINUM_CHESTPLATE)
                .add(ModItems.ALUMINUM_LEGGINGS)
                .add(ModItems.ALUMINUM_BOOTS)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.SAPPHIRE_BOOTS)
                .add(ModItems.NEPHRITE_HELMET)
                .add(ModItems.NEPHRITE_CHESTPLATE)
                .add(ModItems.NEPHRITE_LEGGINGS)
                .add(ModItems.NEPHRITE_BOOTS)
                .add(ModItems.JADEITE_HELMET)
                .add(ModItems.JADEITE_CHESTPLATE)
                .add(ModItems.JADEITE_LEGGINGS)
                .add(ModItems.JADEITE_BOOTS)
                .add(ModItems.PLATINUM_HELMET)
                .add(ModItems.PLATINUM_CHESTPLATE)
                .add(ModItems.PLATINUM_LEGGINGS)
                .add(ModItems.PLATINUM_BOOTS)
                .add(ModItems.CAST_STEEL_HELMET)
                .add(ModItems.CAST_STEEL_CHESTPLATE)
                .add(ModItems.CAST_STEEL_LEGGINGS)
                .add(ModItems.CAST_STEEL_BOOTS)
                .add(ModItems.METEORIC_IRON_HELMET)
                .add(ModItems.METEORIC_IRON_CHESTPLATE)
                .add(ModItems.METEORIC_IRON_LEGGINGS)
                .add(ModItems.METEORIC_IRON_BOOTS);

        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.PINKU_AXE)
                .add(ModItems.RAINBOW_PYRITE_AXE)
                .add(ModItems.TUNGSTEN_AXE)
                .add(ModItems.ALUMINUM_AXE)
                .add(ModItems.SAPPHIRE_AXE)
                .add(ModItems.NEPHRITE_AXE)
                .add(ModItems.JADEITE_AXE)
                .add(ModItems.PLATINUM_AXE)
                .add(ModItems.CAST_STEEL_AXE)
                .add(ModItems.METEORIC_IRON_AXE);

        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.PINKU_HOE)
                .add(ModItems.RAINBOW_PYRITE_HOE)
                .add(ModItems.TUNGSTEN_HOE)
                .add(ModItems.ALUMINUM_HOE)
                .add(ModItems.SAPPHIRE_HOE)
                .add(ModItems.NEPHRITE_HOE)
                .add(ModItems.JADEITE_HOE)
                .add(ModItems.PLATINUM_HOE)
                .add(ModItems.CAST_STEEL_HOE)
                .add(ModItems.METEORIC_IRON_HOE);

        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.PINKU_SHOVEL)
                .add(ModItems.RAINBOW_PYRITE_SHOVEL)
                .add(ModItems.TUNGSTEN_SHOVEL)
                .add(ModItems.ALUMINUM_SHOVEL)
                .add(ModItems.SAPPHIRE_SHOVEL)
                .add(ModItems.NEPHRITE_SHOVEL)
                .add(ModItems.JADEITE_SHOVEL)
                .add(ModItems.PLATINUM_SHOVEL)
                .add(ModItems.CAST_STEEL_SHOVEL)
                .add(ModItems.METEORIC_IRON_SHOVEL);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.PINKU_SWORD)
                .add(ModItems.RAINBOW_PYRITE_SWORD)
                .add(ModItems.TUNGSTEN_SWORD)
                .add(ModItems.ALUMINUM_SWORD)
                .add(ModItems.SAPPHIRE_SWORD)
                .add(ModItems.NEPHRITE_SWORD)
                .add(ModItems.JADEITE_SWORD)
                .add(ModItems.PLATINUM_SWORD)
                .add(ModItems.CAST_STEEL_SWORD)
                .add(ModItems.METEORIC_IRON_SWORD);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.PINKU_PICKAXE)
                .add(ModItems.RAINBOW_PYRITE_PICKAXE)
                .add(ModItems.TUNGSTEN_PICKAXE)
                .add(ModItems.ALUMINUM_PICKAXE)
                .add(ModItems.SAPPHIRE_PICKAXE)
                .add(ModItems.NEPHRITE_PICKAXE)
                .add(ModItems.JADEITE_PICKAXE)
                .add(ModItems.PLATINUM_PICKAXE)
                .add(ModItems.CAST_STEEL_PICKAXE)
                .add(ModItems.METEORIC_IRON_PICKAXE);

        valueLookupBuilder(ModTags.Items.DRILLS)
                .add(ModItems.SIMPLE_DRILL_HEAD)
                .add(ModItems.SIMPLE_TUNGSTEN_DRILL)
                .add(ModItems.ENHANCED_DRILL_HEAD)
                .add(ModItems.ENHANCED_TUNGSTEN_DRILL)
                .add(ModItems.ADVANCED_DRILL_HEAD)
                .add(ModItems.ADVANCED_TUNGSTEN_DRILL)
                .add(ModItems.PREMIUM_DRILL_HEAD)
                .add(ModItems.PREMIUM_TUNGSTEN_DRILL)
                .add(ModItems.SIMPLE_DIAMOND_DRILL)
                .add(ModItems.ENHANCED_DIAMOND_DRILL)
                .add(ModItems.ADVANCED_DIAMOND_DRILL)
                .add(ModItems.PREMIUM_DIAMOND_DRILL);

        valueLookupBuilder(ModTags.Items.DRILL_BITS)
                .add(ModItems.TUNGSTEN_DRILL_BIT)
                .add(ModItems.DIAMOND_DRILL_BIT);

        valueLookupBuilder(ModTags.Items.SMITHING_TEMPLATES)
                .add(ModItems.PINKU_UPGRADE_SMITHING_TEMPLATE)
                .add(ModItems.DRILL_UPGRADE_SMITHING_TEMPLATE);

        valueLookupBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.PINKU_BOOTS)
                .add(ModItems.RAINBOW_PYRITE_BOOTS)
                .add(ModItems.TUNGSTEN_BOOTS)
                .add(ModItems.ALUMINUM_BOOTS)
                .add(ModItems.SAPPHIRE_BOOTS)
                .add(ModItems.NEPHRITE_BOOTS)
                .add(ModItems.JADEITE_BOOTS)
                .add(ModItems.PLATINUM_BOOTS)
                .add(ModItems.CAST_STEEL_BOOTS)
                .add(ModItems.METEORIC_IRON_BOOTS);

        valueLookupBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.PINKU_LEGGINGS)
                .add(ModItems.RAINBOW_PYRITE_LEGGINGS)
                .add(ModItems.TUNGSTEN_LEGGINGS)
                .add(ModItems.ALUMINUM_LEGGINGS)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.NEPHRITE_LEGGINGS)
                .add(ModItems.JADEITE_LEGGINGS)
                .add(ModItems.PLATINUM_LEGGINGS)
                .add(ModItems.CAST_STEEL_LEGGINGS)
                .add(ModItems.METEORIC_IRON_LEGGINGS);

        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.PINKU_CHESTPLATE)
                .add(ModItems.RAINBOW_PYRITE_CHESTPLATE)
                .add(ModItems.TUNGSTEN_CHESTPLATE)
                .add(ModItems.ALUMINUM_CHESTPLATE)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.NEPHRITE_CHESTPLATE)
                .add(ModItems.JADEITE_CHESTPLATE)
                .add(ModItems.PLATINUM_CHESTPLATE)
                .add(ModItems.CAST_STEEL_CHESTPLATE)
                .add(ModItems.METEORIC_IRON_CHESTPLATE);

        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.PINKU_HELMET)
                .add(ModItems.RAINBOW_PYRITE_HELMET)
                .add(ModItems.TUNGSTEN_HELMET)
                .add(ModItems.ALUMINUM_HELMET)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.NEPHRITE_HELMET)
                .add(ModItems.JADEITE_HELMET)
                .add(ModItems.PLATINUM_HELMET)
                .add(ModItems.CAST_STEEL_HELMET)
                .add(ModItems.METEORIC_IRON_HELMET);

        valueLookupBuilder(ModTags.Items.MOB_ARMORS)
                .add(ModItems.PINKU_HORSE_ARMOR)
                .add(ModItems.RAINBOW_PYRITE_HORSE_ARMOR)
                .add(ModItems.TUNGSTEN_HORSE_ARMOR)
                .add(ModItems.ALUMINUM_HORSE_ARMOR)
                .add(ModItems.SAPPHIRE_HORSE_ARMOR)
                .add(ModItems.NEPHRITE_HORSE_ARMOR)
                .add(ModItems.JADEITE_HORSE_ARMOR)
                .add(ModItems.PLATINUM_HORSE_ARMOR)
                .add(ModItems.CAST_STEEL_HORSE_ARMOR)
                .add(ModItems.METEORIC_IRON_HORSE_ARMOR)
                .add(ModItems.PINKU_NAUTILUS_ARMOR)
                .add(ModItems.RAINBOW_PYRITE_NAUTILUS_ARMOR)
                .add(ModItems.TUNGSTEN_NAUTILUS_ARMOR)
                .add(ModItems.ALUMINUM_NAUTILUS_ARMOR)
                .add(ModItems.SAPPHIRE_NAUTILUS_ARMOR)
                .add(ModItems.NEPHRITE_NAUTILUS_ARMOR)
                .add(ModItems.JADEITE_NAUTILUS_ARMOR)
                .add(ModItems.PLATINUM_NAUTILUS_ARMOR)
                .add(ModItems.CAST_STEEL_NAUTILUS_ARMOR)
                .add(ModItems.METEORIC_IRON_NAUTILUS_ARMOR);

        valueLookupBuilder(ModTags.Items.PINKU_REPAIR)
                .add(ModItems.PINKU);
        valueLookupBuilder(ModTags.Items.RAINBOW_REPAIR)
                .add(ModItems.RAINBOW_PYRITE);
        valueLookupBuilder(ModTags.Items.TUNGSTEN_REPAIR)
                .add(ModItems.TUNGSTEN);
        valueLookupBuilder(ModTags.Items.ALUMINUM_REPAIR)
                .add(ModItems.ALUMINUM);
        valueLookupBuilder(ModTags.Items.SAPPHIRE_REPAIR)
                .add(ModItems.SAPPHIRE);
        valueLookupBuilder(ModTags.Items.NEPHRITE_REPAIR)
                .add(ModItems.NEPHRITE);
        valueLookupBuilder(ModTags.Items.JADEITE_REPAIR)
                .add(ModItems.JADEITE);
        valueLookupBuilder(ModTags.Items.PLATINUM_REPAIR)
                .add(ModItems.PLATINUM);
        valueLookupBuilder(ModTags.Items.CAST_STEEL_REPAIR)
                .add(ModItems.CAST_STEEL);
        valueLookupBuilder(ModTags.Items.METEORIC_IRON_REPAIR)
                .add(ModItems.METEORIC_IRON_INGOT);
        valueLookupBuilder(ModTags.Items.DIAMOND_REPAIR)
                .add(Items.DIAMOND);

        valueLookupBuilder(ModTags.Items.ARMOR)
                .add(ModItems.PINKU_HELMET)
                .add(ModItems.RAINBOW_PYRITE_HELMET)
                .add(ModItems.TUNGSTEN_HELMET)
                .add(ModItems.ALUMINUM_HELMET)
                .add(ModItems.SAPPHIRE_HELMET)
                .add(ModItems.NEPHRITE_HELMET)
                .add(ModItems.JADEITE_HELMET)
                .add(ModItems.PLATINUM_HELMET)
                .add(ModItems.CAST_STEEL_HELMET)
                .add(ModItems.METEORIC_IRON_HELMET)
                .add(ModItems.PINKU_CHESTPLATE)
                .add(ModItems.RAINBOW_PYRITE_CHESTPLATE)
                .add(ModItems.TUNGSTEN_CHESTPLATE)
                .add(ModItems.ALUMINUM_CHESTPLATE)
                .add(ModItems.SAPPHIRE_CHESTPLATE)
                .add(ModItems.NEPHRITE_CHESTPLATE)
                .add(ModItems.JADEITE_CHESTPLATE)
                .add(ModItems.PLATINUM_CHESTPLATE)
                .add(ModItems.CAST_STEEL_CHESTPLATE)
                .add(ModItems.METEORIC_IRON_CHESTPLATE)
                .add(ModItems.PINKU_LEGGINGS)
                .add(ModItems.RAINBOW_PYRITE_LEGGINGS)
                .add(ModItems.TUNGSTEN_LEGGINGS)
                .add(ModItems.ALUMINUM_LEGGINGS)
                .add(ModItems.SAPPHIRE_LEGGINGS)
                .add(ModItems.NEPHRITE_LEGGINGS)
                .add(ModItems.JADEITE_LEGGINGS)
                .add(ModItems.PLATINUM_LEGGINGS)
                .add(ModItems.CAST_STEEL_LEGGINGS)
                .add(ModItems.METEORIC_IRON_LEGGINGS)
                .add(ModItems.PINKU_BOOTS)
                .add(ModItems.RAINBOW_PYRITE_BOOTS)
                .add(ModItems.TUNGSTEN_BOOTS)
                .add(ModItems.ALUMINUM_BOOTS)
                .add(ModItems.SAPPHIRE_BOOTS)
                .add(ModItems.NEPHRITE_BOOTS)
                .add(ModItems.JADEITE_BOOTS)
                .add(ModItems.PLATINUM_BOOTS)
                .add(ModItems.CAST_STEEL_BOOTS)
                .add(ModItems.METEORIC_IRON_BOOTS);

        valueLookupBuilder(ItemTags.SPEARS)
                .add(ModItems.PINKU_SPEAR)
                .add(ModItems.RAINBOW_PYRITE_SPEAR)
                .add(ModItems.TUNGSTEN_SPEAR)
                .add(ModItems.ALUMINUM_SPEAR)
                .add(ModItems.SAPPHIRE_SPEAR)
                .add(ModItems.NEPHRITE_SPEAR)
                .add(ModItems.NEPHRITE_SPEAR)
                .add(ModItems.JADEITE_SPEAR)
                .add(ModItems.PLATINUM_SPEAR)
                .add(ModItems.CAST_STEEL_SPEAR)
                .add(ModItems.METEORIC_IRON_SPEAR);

        valueLookupBuilder(ModTags.Items.CASTS)
                .add(ModItems.INGOT_CAST)
                .add(ModItems.PICKAXE_HEAD_CAST)
                .add(ModItems.AXE_HEAD_CAST)
                .add(ModItems.SHOVEL_HEAD_CAST)
                .add(ModItems.SWORD_HEAD_CAST)
                .add(ModItems.HOE_HEAD_CAST)
                .add(ModItems.SPEAR_HEAD_CAST)
                .add(ModItems.HELMET_CAST)
                .add(ModItems.CHESTPLATE_CAST)
                .add(ModItems.LEGGINGS_CAST)
                .add(ModItems.BOOTS_CAST);
    }
}