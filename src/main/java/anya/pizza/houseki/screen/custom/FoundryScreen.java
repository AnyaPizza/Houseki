package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.Houseki;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FoundryScreen extends HandledScreen<FoundryScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_gui.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_arrow.png");
    private static final Identifier ARROW_TEXTURE2 = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_arrow2.png");
    private static final Identifier MELTING_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/melting_progress.png");
    private static final Identifier FLUID_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_metal.png");

    public FoundryScreen(FoundryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 176;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        //titleY = -4;
        //backgroundHeight = 196;
    }

    //private void renderProgressArrow(DrawContext context, int x, int y) {
    //    if(handler.getPropertyDelegate().get(0) > 0 && handler.isCrafting()) {
    //        context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 30, y + 39, 0, 0,
    //                handler.getScaledArrowProgress(), 16, 24, 16);
    //    }
    //}

    //private void renderProgressArrow2(DrawContext context, int x, int y) {
    //    if(handler.getPropertyDelegate().get(0) > 0 && handler.isCrafting()) {
    //        context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE2, x + 79, y + 39, 0, 0,
    //                handler.getScaledArrowProgress(), 16, 24, 16);
    //    }
    //}

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, 176, 176, 256, 256);

        if (handler.isBurning()) {
            int fireHeight = (int) ((float) handler.getMetalLevel() / handler.getMaxMetalLevel() * 50);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, MELTING_TEXTURE, x + 27, y + 40 + (14 - fireHeight), 176, 14 - fireHeight, 14, fireHeight, 256, 256);
        }

        if (handler.getMetalLevel() > 0) {
            int scaledFluidHeight = (int) ((float) handler.getMetalLevel() / handler.getMaxMetalLevel() * 50);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, FLUID_TEXTURE, x + 80, y + 20 + (50 - scaledFluidHeight), 0, 50 - scaledFluidHeight, 50, scaledFluidHeight, 16, 43);
        }

        if (handler.getMeltProgress() > 0) {
            int meltPixelWidth = (int) ((float) handler.getMeltProgress() / handler.getMaxMeltProgress() * 24);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 50, y + 35, 176, 14, meltPixelWidth, 16, 256, 256);
        }

        if (handler.getCastProgress() > 0) {
            int castPixelWidth = (int) ((float) handler.getCastProgress() / handler.getMaxCastTime() * 24);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE2, x + 105, y + 35, 176, 30, castPixelWidth, 16, 256, 256);
        }
        //renderProgressArrow(context, x, y);
        //renderProgressArrow2(context, x, y);
        //renderProgressMelting(context, x, y);
    }

    //private void renderProgressMelting(DrawContext context, int x, int y) {
    //    if (handler.isBurning()) {
    //        int progress = handler.getScaledFuelProgress();
    //        context.drawTexture(RenderPipelines.GUI_TEXTURED, MELTING_TEXTURE, x + 5, y + 69 - progress, 0,
    //                20 - progress, 6, progress, 6, 20);
    //    }
    //}

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        //Tooltips for fluid tank
        int x = (width - backgroundWidth) / 2;
        int y = (width - backgroundHeight) / 2;
        if (isPointWithinBounds(80, 20, 16, 50, mouseX, mouseY)) {
            context.drawTooltip(textRenderer, Text.literal("Molten Steel: " + handler.getMetalLevel() + " / " + handler.getMaxMetalLevel() + " mB"), mouseX, mouseY);
        }
    }
}