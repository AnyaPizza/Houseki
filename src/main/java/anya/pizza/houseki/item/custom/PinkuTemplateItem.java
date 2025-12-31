package anya.pizza.houseki.item.custom;

import anya.pizza.houseki.Houseki;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Consumer;

public class PinkuTemplateItem extends SmithingTemplateItem {
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.LIGHT_PURPLE;
    private static final Formatting TITLE_FORMATTING = Formatting.DARK_PURPLE;
    private static final Text PINKU_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(Houseki.MOD_ID,"pinku_upgrade"))).formatted(TITLE_FORMATTING);
    private static final Text PINKU_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Houseki.MOD_ID, "smithing_template.pinku_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text PINKU_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Houseki.MOD_ID,"smithing_template.pinku_upgrade.ingredients"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text PINKU_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Houseki.MOD_ID,"smithing_template.pinku_upgrade.base_slot_description")));
    private static final Text PINKU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.of(Houseki.MOD_ID,"smithing_template.pinku_upgrade.additions_slot_description")));

    public PinkuTemplateItem(Text appliesToText, Text ingredientsText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, Settings settings) {
        super(appliesToText, ingredientsText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
    }

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("container/slot/helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("container/slot/chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("container/slot/leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_PINKU_TEXTURE = Identifier.of(Houseki.MOD_ID, "container/slot/pinku_slot");

    public static SmithingTemplateItem createPinkuUpgrade(Settings settings) {
        return new SmithingTemplateItem(
                PINKU_UPGRADE_APPLIES_TO_TEXT,
                PINKU_UPGRADE_INGREDIENTS_TEXT,
                PINKU_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                PINKU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getPinkuUpgradeEmptyBaseSlotTextures(),
                getPinkuUpgradeEmptyAdditionsSlotTextures(),
                settings
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        textConsumer.accept(PINKU_UPGRADE_TEXT);
        textConsumer.accept(ScreenTexts.EMPTY);
        textConsumer.accept(PINKU_UPGRADE_APPLIES_TO_TEXT);
        textConsumer.accept(PINKU_UPGRADE_INGREDIENTS_TEXT);
    }

    private static List<Identifier> getPinkuUpgradeEmptyBaseSlotTextures() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_SLOT_SWORD_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_SLOT_PICKAXE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_SLOT_AXE_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                EMPTY_SLOT_HOE_TEXTURE,
                EMPTY_SLOT_SHOVEL_TEXTURE
        );
    }

    private static List<Identifier> getPinkuUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_PINKU_TEXTURE);
    }
}
