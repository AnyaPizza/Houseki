package anya.pizza.pizzasoat.item.custom;

import anya.pizza.pizzasoat.PizzasOAT;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class PinkuTemplateItem extends SmithingTemplateItem {
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.LIGHT_PURPLE;
    private static final Formatting TITLE_FORMATTING = Formatting.DARK_PURPLE;
    private static final Text PINKU_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(PizzasOAT.MOD_ID,"pinku_upgrade")))
            .formatted(TITLE_FORMATTING);
    private static final Text PINKU_UPGRADE_APPLIES_TO_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID, "smithing_template.pinku_upgrade.applies_to"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text PINKU_UPGRADE_INGREDIENTS_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID,"smithing_template.pinku_upgrade.ingredients"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text PINKU_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID,"smithing_template.pinku_upgrade.base_slot_description"))
    );
    private static final Text PINKU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(PizzasOAT.MOD_ID,"smithing_template.pinku_upgrade.additions_slot_description"))
    );

    public PinkuTemplateItem(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, FeatureFlag... requiredFeatures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, requiredFeatures);
    }

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.ofVanilla("item/empty_slot_ingot");

    public static SmithingTemplateItem createPinkuUpgrade() {
        return new SmithingTemplateItem(
                PINKU_UPGRADE_APPLIES_TO_TEXT,
                PINKU_UPGRADE_INGREDIENTS_TEXT,
                PINKU_UPGRADE_TEXT,
                PINKU_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                PINKU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getNetheriteUpgradeEmptyBaseSlotTextures(),
                getNetheriteUpgradeEmptyAdditionsSlotTextures()
        );
    }

    private static List<Identifier> getNetheriteUpgradeEmptyBaseSlotTextures() {
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

    private static List<Identifier> getNetheriteUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }
}
