package nanami.pizza.pizzasoat.screen.custom;

import nanami.pizza.pizzasoat.PizzasOAT;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CrusherScreen extends HandledScreen<CrusherScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(PizzasOAT.MOD_ID, "textures/gui/crusher/crusher_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(PizzasOAT.MOD_ID, "textures/gui/crusher/crush_progress.png");
    private static final Identifier CRUSHING_TEXTURE =
            Identifier.of(PizzasOAT.MOD_ID, "textures/gui/crusher/crushing_progress.png");

    public CrusherScreen(CrusherScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        titleX = 67;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(context, x, y);
        renderProgressCrushing(context, x, y);

    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.getPropertyDelegate().get(0) > 0 && handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 79, y + 35, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    private void renderProgressCrushing(DrawContext context, int x, int y) {
        if (handler.isBurning()) {
            int progress = handler.getScaledFuelProgress();
            context.drawTexture(CRUSHING_TEXTURE, x + 56, y + 53 - progress, 0,
                    20 - progress, 16, progress, 16, 20);
        }
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
