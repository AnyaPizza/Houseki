package anya.pizza.houseki.compat.rei;

import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.CrusherRecipe;
import anya.pizza.houseki.recipe.FoundryCastingRecipe;
import anya.pizza.houseki.recipe.FoundryMeltingRecipe;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

final class MachineCategory implements DisplayCategory<MachineDisplay> {
    private final CategoryIdentifier<MachineDisplay> category;
    private final String name;
    private final ItemLike workstation;

    MachineCategory(CategoryIdentifier<MachineDisplay> category, String name, ItemLike workstation) {
        this.category = category;
        this.name = name;
        this.workstation = workstation;
    }

    @Override
    public CategoryIdentifier<MachineDisplay> getCategoryIdentifier() {
        return category;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.houseki." + name);
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(workstation);
    }

    @Override
    public int getDisplayHeight() {
        return 92;
    }

    @Override
    public int getDisplayWidth(MachineDisplay display) {
        return 190;
    }

    @Override
    public List<Widget> setupDisplay(MachineDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        int x = bounds.x + 8;
        int y = bounds.y + 8;
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createSlot(new Point(x, y)).entries(display.getInputEntries().getFirst()).markInput());
        widgets.add(Widgets.createArrow(new Point(x + 74, y)));
        for (int i = 0; i < display.getOutputEntries().size(); i++) {
            widgets.add(Widgets.createSlot(new Point(x + 112 + i * 26, y))
                    .entries(display.getOutputEntries().get(i)).markOutput());
        }

        var recipe = display.recipe();
        int ticks = recipe instanceof CrusherRecipe r ? r.crushingTime()
                : recipe instanceof FoundryMeltingRecipe r ? r.meltTime()
                : ((FoundryCastingRecipe) recipe).castTime();
        addLabel(widgets, x, y + 28, Component.translatable("jei.houseki.time", ticks / 20.0));
        if (recipe instanceof CrusherRecipe crushing && crushing.auxiliaryOutput().isPresent()) {
            addLabel(widgets, x, y + 42, Component.translatable("jei.houseki.chance",
                    Math.round(crushing.auxiliaryChance() * 100)));
        } else if (recipe instanceof FoundryMeltingRecipe) {
            addLabel(widgets, x, y + 42, Component.translatable("jei.houseki.molten", 100));
        } else if (recipe instanceof FoundryCastingRecipe casting) {
            widgets.add(Widgets.createSlot(new Point(x + 40, y)).entry(EntryStacks.of(
                    casting.activeMetalType() == FoundryBlockEntity.METAL_STEEL
                            ? ModItems.STEEL : ModItems.METEORIC_IRON_INGOT)));
            addLabel(widgets, x, y + 42, Component.translatable("jei.houseki.cooling", casting.coolingTime() / 20.0));
            addLabel(widgets, x, y + 56, Component.translatable("jei.houseki.tank", 100));
            addLabel(widgets, x, y + 68, Component.translatable("jei.houseki.select_metal"));
        }
        return widgets;
    }

    private static void addLabel(List<Widget> widgets, int x, int y, Component text) {
        widgets.add(Widgets.createLabel(new Point(x, y), text).leftAligned().noShadow()
                .color(0xFF404040, 0xFFBBBBBB));
    }
}
