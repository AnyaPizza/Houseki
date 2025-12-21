package anya.pizza.pizzasoat.compat.rei;

import anya.pizza.pizzasoat.PizzasOAT;
import anya.pizza.pizzasoat.block.ModBlocks;
import anya.pizza.pizzasoat.recipe.CrusherRecipe;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CrusherCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE = Identifier.of(PizzasOAT.MOD_ID, "textures/gui/crusher/crusher_gui.png");
    public static final CategoryIdentifier<CrusherDisplay> CRUSHER = CategoryIdentifier.of(PizzasOAT.MOD_ID, "crusher");

    public CrusherCategory() {
    }

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CRUSHER;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.pizzasoat.crusher");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRUSHER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87,  bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 176, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 35, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 40)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 79, startPoint.y + 39)).animationDurationTicks(250));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
