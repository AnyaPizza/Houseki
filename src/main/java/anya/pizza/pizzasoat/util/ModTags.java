package anya.pizza.pizzasoat.util;


import anya.pizza.pizzasoat.PizzasOAT;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

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


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(PizzasOAT.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DRILLS = createTag("drills");
        public static final TagKey<Item> DRILL_BITS = createTag("drill_bits");
        public static final TagKey<Item> SMITHING_TEMPLATES = createTag("smithing_templates");

        public static final TagKey<Item> ARMOR = createTag("armor");
        public static final TagKey<Item> MOB_ARMORS = createTag("mob_armors");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(PizzasOAT.MOD_ID, name));
        }
    }
}
