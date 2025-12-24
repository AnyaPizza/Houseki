package anya.pizza.pizzasoat.item.custom;

import anya.pizza.pizzasoat.PizzasOAT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class DrillTemplateItem extends SmithingTemplateItem {
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.RED;
    private static final Formatting TITLE_FORMATTING = Formatting.BLUE;
    private static final Text DRILL_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(PizzasOAT.MOD_ID,"drill_upgrade"))).formatted(TITLE_FORMATTING);
    private static final Text DRILL_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID, "smithing_template.drill_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text DRILL_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID,"smithing_template.drill_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text DRILL_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID,"smithing_template.drill_upgrade.base_slot_description")));
    private static final Text DRILL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID,"smithing_template.drill_upgrade.additions_slot_description")));

    public DrillTemplateItem(Text appliesToText, Text ingredientsText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, Settings settings) {
        super(appliesToText, ingredientsText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
    }

    private static final Identifier EMPTY_SLOT_DRILL_TEXTURE = Identifier.of(PizzasOAT.MOD_ID, "container/slot/drill_slot");
    private static final Identifier EMPTY_SLOT_DRILLBIT_TEXTURE = Identifier.of(PizzasOAT.MOD_ID, "container/slot/drillbit_slot");
    private static final Identifier EMPTY_SLOT_CSTEEL_BLOCK_TEXTURE = Identifier.of(PizzasOAT.MOD_ID, "container/slot/csteel_block_slot");

    public static SmithingTemplateItem createDrillUpgrade(Settings settings) {
        return new SmithingTemplateItem(
                DRILL_UPGRADE_APPLIES_TO_TEXT,
                DRILL_UPGRADE_INGREDIENTS_TEXT,
                DRILL_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                DRILL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getDrillUpgradeEmptyBaseSlotTextures(),
                getDrillUpgradeEmptyAdditionsSlotTextures(),
                settings
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(DRILL_UPGRADE_TEXT);
        tooltip.add(ScreenTexts.EMPTY);
        tooltip.add(DRILL_UPGRADE_APPLIES_TO_TEXT);
        tooltip.add(DRILL_UPGRADE_INGREDIENTS_TEXT);
    }

    private static List<Identifier> getDrillUpgradeEmptyBaseSlotTextures() {
        return List.of(
                EMPTY_SLOT_DRILL_TEXTURE,
                EMPTY_SLOT_DRILLBIT_TEXTURE
        );
    }

    private static List<Identifier> getDrillUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_CSTEEL_BLOCK_TEXTURE);
    }
}