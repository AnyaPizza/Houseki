package anya.pizza.houseki.item.custom;

import anya.pizza.houseki.Houseki;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Consumer;

public class PinkuTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.LIGHT_PURPLE;
    private static final ChatFormatting TITLE_FORMATTING = ChatFormatting.DARK_PURPLE;
    private static final Component PINKU_UPGRADE_TEXT = Component.translatable(Util.makeDescriptionId("upgrade", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"pinku_upgrade"))).withStyle(TITLE_FORMATTING);
    private static final Component PINKU_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "smithing_template.pinku_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING);
    private static final Component PINKU_UPGRADE_INGREDIENTS_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"smithing_template.pinku_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING);
    private static final Component PINKU_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"smithing_template.pinku_upgrade.base_slot_description")));
    private static final Component PINKU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"smithing_template.pinku_upgrade.additions_slot_description")));

    /**
     * Creates a PinkuTemplateItem configured with its tooltip text, permitted empty-slot textures, and item properties.
     *
     * @param appliesToText               component describing what the upgrade applies to (shown in the tooltip)
     * @param ingredientsText             component describing the upgrade ingredients (shown in the tooltip)
     * @param baseSlotDescriptionText     component describing the base slot (shown in the tooltip)
     * @param additionsSlotDescriptionText component describing the additions slot (shown in the tooltip)
     * @param emptyBaseSlotTextures       list of texture identifiers allowed for empty base slots
     * @param emptyAdditionsSlotTextures  list of texture identifiers allowed for empty additions slots
     * @param settings                    item properties used to create the template item
     */
    public PinkuTemplateItem(Component appliesToText, Component ingredientsText, Component baseSlotDescriptionText, Component additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, Properties settings) {
        super(appliesToText, ingredientsText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
    }

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.withDefaultNamespace("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.withDefaultNamespace("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.withDefaultNamespace("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_PINKU_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/pinku_slot");

    /**
     * Create a SmithingTemplateItem configured for the Pinku upgrade.
     *
     * @param settings item properties used to construct the template
     * @return the SmithingTemplateItem instance configured with Pinku texts and empty slot textures
     */
    public static SmithingTemplateItem createPinkuUpgrade(Properties settings) {
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

    /**
     * Appends the Pinku upgrade title and descriptive lines to the item's tooltip after the base tooltip.
     *
     * @param textConsumer consumer that accepts tooltip components to be displayed
     */
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        textConsumer.accept(PINKU_UPGRADE_TEXT);
        textConsumer.accept(CommonComponents.EMPTY);
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