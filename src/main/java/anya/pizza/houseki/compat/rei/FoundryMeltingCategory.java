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
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class FoundryMeltingCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_gui.png");
    public static final CategoryIdentifier<FoundryMeltingDisplay> FOUNDRY_MELTING =
            CategoryIdentifier.of(Houseki.MOD_ID, "foundry_melting");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return FOUNDRY_MELTING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("category.houseki.foundry_melting");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.FOUNDRY.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 41);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 176, 84)));

        // Input slot (melt input position)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 26, startPoint.y + 18))
                .entries(display.getInputEntries().get(0)).markInput());
        // Fuel slot - Coal (position 26, 53 from screen handler)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 26, startPoint.y + 53))
                .entries(EntryIngredient.of(EntryStacks.of(Items.COAL))).markInput());
        // Melt arrow
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 49, startPoint.y + 35)).animationDurationTicks(200));
        // Output shown in the output slot area
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 135, startPoint.y + 53))
                .entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 92;
    }
}
