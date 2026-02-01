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
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Consumer;

public class DrillTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.DARK_AQUA;
    private static final ChatFormatting TITLE_FORMATTING = ChatFormatting.BLUE;
    private static final Component DRILL_UPGRADE_TEXT = Component.translatable(Util.makeDescriptionId("upgrade", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"drill_upgrade"))).withStyle(TITLE_FORMATTING);
    private static final Component DRILL_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "smithing_template.drill_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING);
    private static final Component DRILL_UPGRADE_INGREDIENTS_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"smithing_template.drill_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING);
    private static final Component DRILL_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"smithing_template.drill_upgrade.base_slot_description")));
    private static final Component DRILL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Houseki.MOD_ID,"smithing_template.drill_upgrade.additions_slot_description")));

    /**
     * Creates a DrillTemplateItem configured with the text components, empty-slot textures, and item properties used by the drill upgrade template.
     *
     * @param appliesToText               component describing what the upgrade applies to
     * @param ingredientsText             component describing the upgrade's required ingredients
     * @param baseSlotDescriptionText     component describing the base slot
     * @param additionsSlotDescriptionText component describing the additions slot
     * @param emptyBaseSlotTextures       identifiers for the empty textures used by the base slot
     * @param emptyAdditionsSlotTextures  identifiers for the empty textures used by the additions slot
     * @param settings                    item properties for this template
     */
    public DrillTemplateItem(Component appliesToText, Component ingredientsText, Component baseSlotDescriptionText, Component additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, Properties settings) {
        super(appliesToText, ingredientsText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
    }

    private static final Identifier EMPTY_SLOT_DRILL_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/drill_slot");
    private static final Identifier EMPTY_SLOT_DRILLBIT_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/drillbit_slot");
    private static final Identifier EMPTY_SLOT_CSTEEL_BLOCK_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/csteel_block_slot");

    /**
     * Creates a smithing template item configured for the drill upgrade.
     *
     * @param settings properties to apply to the created smithing template item
     * @return a SmithingTemplateItem preconfigured with drill-upgrade text components and slot textures
     */
    public static SmithingTemplateItem createDrillUpgrade(Properties settings) {
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

    /**
     * Adds drill-upgrade-specific tooltip lines: a title, an empty line, an "applies to" description, and an ingredients description.
     *
     * @param stack the item stack being hovered
     * @param context the tooltip context
     * @param displayComponent tooltip display helper used for rendering decisions
     * @param textConsumer consumer that will receive the tooltip components
     * @param type flags controlling which tooltip lines are shown
     */
    @Override
    public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, @NonNull Consumer<Component> textConsumer, @NonNull TooltipFlag type) {
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        textConsumer.accept(DRILL_UPGRADE_TEXT);
        textConsumer.accept(CommonComponents.EMPTY);
        textConsumer.accept(DRILL_UPGRADE_APPLIES_TO_TEXT);
        textConsumer.accept(DRILL_UPGRADE_INGREDIENTS_TEXT);
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