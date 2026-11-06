package anya.pizza.houseki.compat.jei;

import anya.pizza.houseki.block.entity.custom.FoundryBlockEntity;
import anya.pizza.houseki.item.ModItems;
import anya.pizza.houseki.recipe.*;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.function.Function;

final class MachineRecipeCategory<R extends Recipe<?>> implements IRecipeCategory<RecipeHolder<R>> {
    private final IRecipeHolderType<R> type;
    private final String name;
    private final Function<R, Ingredient> input;
    private final Function<R, Item> output;

    MachineRecipeCategory(IRecipeHolderType<R> type, String name,
                          Function<R, Ingredient> input, Function<R, Item> output) {
        this.type = type;
        this.name = name;
        this.input = input;
        this.output = output;
    }

    @Override
    public IRecipeHolderType<R> getRecipeType() { return type; }

    @Override
    public Component getTitle() { return Component.translatable("jei.houseki." + name); }

    @Override
    public int getWidth() { return 160; }

    @Override
    public int getHeight() { return 82; }

    @Override
    public IDrawable getIcon() { return null; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<R> holder, IFocusGroup focuses) {
        R recipe = holder.value();
        builder.addSlot(RecipeIngredientRole.INPUT, 5, 5)
                .setStandardSlotBackground().add(input.apply(recipe));
        var result = builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 5)
                .setStandardSlotBackground().add(output.apply(recipe));
        if (recipe instanceof CrusherRecipe crushing) {
            crushing.auxiliaryOutput().ifPresent(item ->
                    builder.addSlot(RecipeIngredientRole.OUTPUT, 135, 5)
                            .setStandardSlotBackground().add(item)
                            .addRichTooltipCallback((view, tooltip) -> tooltip.add(
                                    Component.translatable("jei.houseki.chance", Math.round(crushing.auxiliaryChance() * 100)))));
        } else if (recipe instanceof FoundryMeltingRecipe) {
            result.addRichTooltipCallback((view, tooltip) ->
                    tooltip.add(Component.translatable("jei.houseki.molten", 100)));
        } else if (recipe instanceof FoundryCastingRecipe casting) {
            // The tank is filled by melting first. Only the cast goes into an inventory slot.
            builder.addSlot(RecipeIngredientRole.CATALYST, 40, 5).setStandardSlotBackground()
                    .add(casting.activeMetalType() == FoundryBlockEntity.METAL_STEEL
                            ? ModItems.STEEL : ModItems.METEORIC_IRON_INGOT)
                    .addRichTooltipCallback((view, tooltip) ->
                            tooltip.add(Component.translatable("jei.houseki.tank", 100)));
        }
    }

    @Override
    public void draw(RecipeHolder<R> holder, IRecipeSlotsView slots, GuiGraphicsExtractor graphics,
                     double mouseX, double mouseY) {
        R recipe = holder.value();
        var font = Minecraft.getInstance().font;
        graphics.text(font, ">", 78, 9, 0xFF404040, false);
        int ticks = recipe instanceof CrusherRecipe r ? r.crushingTime()
                : recipe instanceof FoundryMeltingRecipe r ? r.meltTime()
                : ((FoundryCastingRecipe) recipe).castTime();
        graphics.text(font, Component.translatable("jei.houseki.time", ticks / 20.0), 4, 32, 0xFF404040, false);
        if (recipe instanceof FoundryCastingRecipe casting) {
            graphics.text(font, Component.translatable("jei.houseki.cooling", casting.coolingTime() / 20.0),
                    4, 44, 0xFF404040, false);
            graphics.text(font, Component.translatable("jei.houseki.select_metal"), 4, 58, 0xFF404040, false);
        } else if (recipe instanceof FoundryMeltingRecipe) {
            graphics.text(font, Component.translatable("jei.houseki.molten", 100), 4, 46, 0xFF404040, false);
        }
    }
}
