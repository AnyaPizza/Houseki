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

    public DrillTemplateItem(Component appliesToText, Component ingredientsText, Component baseSlotDescriptionText, Component additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, Properties settings) {
        super(appliesToText, ingredientsText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, settings);
    }

    private static final Identifier EMPTY_SLOT_DRILL_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/drill_slot");
    private static final Identifier EMPTY_SLOT_DRILLBIT_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/drillbit_slot");
    private static final Identifier EMPTY_SLOT_CSTEEL_BLOCK_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "container/slot/csteel_block_slot");

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