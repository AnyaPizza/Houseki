package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.Houseki;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class CrusherScreen extends AbstractContainerScreen<CrusherScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "textures/gui/crusher/crusher_gui.png");
    private static final Identifier ARROW_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "textures/gui/crusher/crush_progress.png");
    private static final Identifier CRUSHING_TEXTURE = Identifier.fromNamespaceAndPath(Houseki.MOD_ID, "textures/gui/crusher/crushing_progress.png");

    public CrusherScreen(CrusherScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, 176, 176);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 114;
        titleLabelY = -4;
        inventoryLabelY = imageHeight - 104;
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - 196) / 2;

        context.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, 176, 176, 256, 256);
        renderProgressArrow(context, x, y);
        renderProgressCrushing(context, x, y);
    }

    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if(menu.getPropertyDelegate().get(0) > 0 && menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 79, y + 39, 0, 0,
                    menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    private void renderProgressCrushing(GuiGraphics context, int x, int y) {
        if (menu.isBurning()) {
            int progress = menu.getScaledFuelProgress();
            context.blit(RenderPipelines.GUI_TEXTURED, CRUSHING_TEXTURE, x + 5, y + 69 - progress, 0,
                    20 - progress, 6, progress, 6, 20);
        }
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        renderTooltip(context, mouseX, mouseY);
    }
}