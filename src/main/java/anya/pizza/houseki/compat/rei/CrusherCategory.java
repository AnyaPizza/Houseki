package anya.pizza.houseki.compat.rei;

import anya.pizza.houseki.Houseki;
import anya.pizza.houseki.block.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CrusherCategory implements DisplayCategory<CrusherDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/crusher/crusher_gui.png");
    public static final CategoryIdentifier<CrusherDisplay> CRUSHER = CategoryIdentifier.of(Houseki.MOD_ID, "crusher");

    @Override
    public CategoryIdentifier<CrusherDisplay> getCategoryIdentifier() {
        return CRUSHER;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.houseki.crusher");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRUSHER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(CrusherDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 41);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 176, 84)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 35, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        // Fuel slot - Iron Ingot (position 13, 41 from screen handler)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 13, startPoint.y + 41))
                .entries(EntryIngredient.of(EntryStacks.of(Items.IRON_INGOT))).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 40)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 79, startPoint.y + 39)).animationDurationTicks(250));

        if (display.getOutputEntries().size() > 1) {
            widgets.add(Widgets.createSlot(new Point(startPoint.x + 138, startPoint.y + 40)).entries(display.getOutputEntries().get(1)).disableBackground().markOutput());
            int chancePercent = (int) Math.round(display.getAuxiliaryChance() * 100);
            widgets.add(Widgets.createLabel(new Point(startPoint.x + 147, startPoint.y + 62), Text.literal(chancePercent + "%"))
                    .centered().noShadow().color(0xFF404040, 0xFFBBBBBB));
        }

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 92;
    }
}
