package anya.pizza.houseki.util;


import anya.pizza.houseki.Houseki;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_PINKU_TOOL = createTag("needs_pinku_tool");
        public static final TagKey<Block> INCORRECT_FOR_PINKU_TOOL = createTag("incorrect_for_pinku_tool");
        public static final TagKey<Block> NEEDS_RAINBOW_PYRITE_TOOL = createTag("needs_rainbow_pyrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_RAINBOW_PYRITE_TOOL = createTag("incorrect_for_rainbow_pyrite_tool");
        public static final TagKey<Block> NEEDS_TUNGSTEN_TOOL = createTag("needs_tungsten_tool");
        public static final TagKey<Block> INCORRECT_FOR_TUNGSTEN_TOOL = createTag("incorrect_for_tungsten_tool");
        public static final TagKey<Block> NEEDS_DRILL_TOOL = createTag("needs_drill_tool");
        public static final TagKey<Block> INCORRECT_FOR_DRILL_TOOL = createTag("incorrect_for_drill_tool");
        public static final TagKey<Block> NEEDS_ENHANCED_DRILL_TOOL = createTag("needs_enhanced_drill_tool");
        public static final TagKey<Block> INCORRECT_FOR_ENHANCED_DRILL_TOOL = createTag("incorrect_for_enhanced_drill_tool");
        public static final TagKey<Block> NEEDS_ADVANCED_DRILL_TOOL = createTag("needs_advanced_drill_tool");
        public static final TagKey<Block> INCORRECT_FOR_ADVANCED_DRILL_TOOL = createTag("incorrect_for_advanced_drill_tool");
        public static final TagKey<Block> NEEDS_PREMIUM_DRILL_TOOL = createTag("needs_premium_drill_tool");
        public static final TagKey<Block> INCORRECT_FOR_PREMIUM_DRILL_TOOL = createTag("incorrect_for_premium_drill_tool");
        public static final TagKey<Block> NEEDS_ALUMINUM_TOOL = createTag("needs_aluminum_tool");
        public static final TagKey<Block> INCORRECT_FOR_ALUMINUM_TOOL = createTag("incorrect_for_aluminum_tool");
        public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = createTag("needs_sapphire_tool");
        public static final TagKey<Block> INCORRECT_FOR_SAPPHIRE_TOOL = createTag("incorrect_for_sapphire_tool");
        public static final TagKey<Block> NEEDS_NEPHRITE_TOOL = createTag("needs_nephrite_tool");
        public static final TagKey<Block> INCORRECT_FOR_NEPHRITE_TOOL = createTag("incorrect_for_nephrite_tool");
        public static final TagKey<Block> NEEDS_JADEITE_TOOL = createTag("needs_jadeite_tool");
        public static final TagKey<Block> INCORRECT_FOR_JADEITE_TOOL = createTag("incorrect_for_jadeite_tool");
        public static final TagKey<Block> NEEDS_PLATINUM_TOOL = createTag("needs_platinum_tool");
        public static final TagKey<Block> INCORRECT_FOR_PLATINUM_TOOL = createTag("incorrect_for_platinum_tool");
        public static final TagKey<Block> NEEDS_STEEL_TOOL = createTag("needs_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");
        public static final TagKey<Block> NEEDS_CAST_STEEL_TOOL = createTag("needs_cast_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_CAST_STEEL_TOOL = createTag("incorrect_for_cast_steel_tool");

        public static final TagKey<Block> PREMIUM_DRILL_MINEABLE = createTag("premium_mineable");
        public static final TagKey<Block> ENHANCED_DRILL_MINEABLE = createTag("enhanced_mineable");


        /**
         * Creates a block tag key using the mod's namespace and the provided name.
         *
         * @param name the tag name (path) to append to the mod's namespace
         * @return the TagKey for the block identified by the mod namespace and given name
         */
        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DRILLS = createTag("drills");
        public static final TagKey<Item> DRILL_BITS = createTag("drill_bits");
        public static final TagKey<Item> SMITHING_TEMPLATES = createTag("smithing_templates");
        public static final TagKey<Item> PINKU_REPAIR = createTag("pinku_repair");
        public static final TagKey<Item> RAINBOW_REPAIR = createTag("rainbow_repair");
        public static final TagKey<Item> TUNGSTEN_REPAIR = createTag("tungsten_repair");
        public static final TagKey<Item> ALUMINUM_REPAIR = createTag("aluminum_repair");
        public static final TagKey<Item> SAPPHIRE_REPAIR = createTag("sapphire_repair");
        public static final TagKey<Item> NEPHRITE_REPAIR = createTag("nephrite_repair");
        public static final TagKey<Item> JADEITE_REPAIR = createTag("jadeite_repair");
        public static final TagKey<Item> PLATINUM_REPAIR = createTag("platinum_repair");
        public static final TagKey<Item> STEEL_REPAIR = createTag("steel_repair");
        public static final TagKey<Item> CAST_STEEL_REPAIR = createTag("cast_steel_repair");
        public static final TagKey<Item> DIAMOND_REPAIR = createTag("diamond_repair");

        public static final TagKey<Item> ARMOR = createTag("armor");
        public static final TagKey<Item> MOB_ARMORS = createTag("mob_armors");

        /**
         * Creates an item tag key in this mod's namespace.
         *
         * @param name the tag path (identifier path) to use within the mod namespace
         * @return the TagKey<Item> for the namespaced item tag
         */
        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Houseki.MOD_ID, name));
        }
    }
}