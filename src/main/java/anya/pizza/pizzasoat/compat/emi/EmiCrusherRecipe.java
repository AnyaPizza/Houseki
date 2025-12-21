package anya.pizza.pizzasoat.compat.emi;

import anya.pizza.pizzasoat.recipe.CrusherRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class EmiCrusherRecipe implements EmiRecipe {
    private final Identifier id;
    private final EmiIngredient input;
    private final EmiStack output;
    private final int crushingTime;

    public EmiCrusherRecipe(Identifier id, CrusherRecipe crusherRecipe) {
        this.id = id;
        this.input = EmiIngredient.of(crusherRecipe.inputItem());
        this.output = EmiStack.of(crusherRecipe.output());
        this.crushingTime = crusherRecipe.crushingTime();
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EmiCrusherPlugin.CRUSHER_CATEGORY;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 82;
    }

    @Override
    public int getDisplayHeight() {
        return 38;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(input, 0, 4);
        widgets.addSlot(output, 56, 0).large(true).recipeContext(this);
        widgets.addFillingArrow(24, 5, 50 * crushingTime).tooltip((mx, my) ->
                List.of(TooltipComponent.of(Text.translatable("emi.cooking.time", crushingTime / 20f).asOrderedText())));
        widgets.addTexture(EmiTexture.EMPTY_FLAME, 1, 24);
        widgets.addAnimatedTexture(EmiTexture.FULL_FLAME, 1, 24, 4000, false, true, true);
    }
}

