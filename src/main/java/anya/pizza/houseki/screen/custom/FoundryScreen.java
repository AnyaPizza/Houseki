package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.Houseki;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoundryScreen extends HandledScreen<FoundryScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_gui.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_arrow.png");
    private static final Identifier ARROW_TEXTURE2 = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_arrow2.png");
    private static final Identifier MELTING_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/melting_progress.png");
    private static final Identifier FLUID_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/foundry/foundry_metal.png");
    private static final Logger log = LoggerFactory.getLogger(FoundryScreen.class);

    /**
     * Creates a FoundryScreen for the given handler and player inventory and configures the GUI to its standard 176×176 size.
     *
     * @param handler the screen handler providing access to the foundry's server-side state and sync methods
     * @param inventory the player's inventory to be displayed and managed by the screen
     * @param title the screen title text displayed at the top of the GUI
     */
    public FoundryScreen(FoundryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 176;
    }

    /**
     * Initializes screen components and centers the title horizontally within the GUI background.
     *
     * Sets `titleX` so the title text is centered based on `backgroundWidth` and the title's rendered width.
     */
    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        //titleY = -4;
        //backgroundHeight = 196;
    }

    /**
     * Renders the foundry GUI background and its dynamic progress indicators.
     *
     * Draws the static GUI texture centered on screen, then overlays:
     * - a melting flame graphic when the foundry is burning (height based on metal level),
     * - the fluid (molten metal) fill gauge (vertical portion based on metal level),
     * - a melt progress arrow (horizontal width based on melt progress),
     * - a cast progress arrow (horizontal width based on cast progress).
     *
     * @param context the drawing context to issue texture draw calls
     * @param delta   partial tick time used for interpolated rendering updates
     * @param mouseX  current mouse x position (screen coordinates)
     * @param mouseY  current mouse y position (screen coordinates)
     */

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, 176, 176, 256, 256);

        if (handler.isBurning()) {
            int fireHeight = handler.getScaledFuelProgress();
            context.drawTexture(RenderPipelines.GUI_TEXTURED, MELTING_TEXTURE, x + 27, y + 36 + (14 - fireHeight), 0, 14 - fireHeight, 14, fireHeight, 14, 14);
        }

        if (handler.getMetalLevel() > 0) {
            int scaledFluidHeight = (int) ((float) handler.getMetalLevel() / handler.getMaxMetalLevel() * 43);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, FLUID_TEXTURE, x + 80, y + 22 + (43 - scaledFluidHeight), 0, 43 - scaledFluidHeight, 16, scaledFluidHeight, 16, 43);
        }

        if (handler.getMeltProgress() > 0) {
            int meltPixelWidth = (int) ((float) handler.getMeltProgress() / handler.getMaxMeltProgress() * 24);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 49, y + 35, 0, 0, meltPixelWidth, 16, 24, 16);
        }

        if (handler.getCastProgress() > 0) {
            int castPixelWidth = (int) ((float) handler.getCastProgress() / handler.getMaxCastTime() * 24);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE2, x + 102, y + 35, 0, 0, castPixelWidth, 16, 24, 16);
        }

        ItemStack coolingStack = handler.getSlot(4).getStack();
        if (!coolingStack.isEmpty()) {
            context.drawItem(coolingStack, x + 134, y + 18);

            float coolPercent = (float) handler.getCoolingProgress() / handler.getMaxCoolingProgress();
            int alpha  = (int) ((1.0f - coolPercent) * 100);
            int color = (alpha << 24) | 0xFFFFFF;
            context.fill(x + 134, y + 18, x + 134 + 16, y + 18 + 16, color);
        }

        if (handler.getCoolingProgress() > 0 && handler.getMaxCoolingProgress() > 0) {
            int barX = x + 131;
            int barY = y + 37;
            int barWidth = 22;
            int barHeight = 3;
            context.fill(barX, barY, barX + barWidth, barY + barHeight, 0xFF404040);
            float barPercent = (float) handler.getCoolingProgress() / handler.getMaxCoolingProgress();
            int fillWidth = (int) (barPercent * barWidth);
            context.fill(barX, barY, barX + fillWidth, barY + barHeight, 0xFF6BB5FF);
        }
    }

    /**
     * Renders the screen background, standard UI components, and hover tooltips; shows a "Molten Steel" tooltip when the mouse is over the fluid tank.
     *
     * When the cursor is inside the fluid-tank region, a tooltip of the form
     * "Molten Steel: <current> / <max> mB" is displayed.
     *
     * @param context the drawing context used for rendering
     * @param mouseX  the current mouse x-coordinate relative to the window
     * @param mouseY  the current mouse y-coordinate relative to the window
     * @param delta   frame delta time / partial tick used for smooth rendering updates
     */

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);

        //Tooltips for fluid tank
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        if (isPointWithinBounds(80, 21, 16, 45, mouseX, mouseY)) {
            context.drawTooltip(textRenderer, Text.literal("Molten Steel: " +
                    handler.getMetalLevel() + " / " + handler.getMaxMetalLevel() + " mB"), mouseX, mouseY);
        }

        if (handler.getCoolingProgress() > 0 && handler.getMaxCoolingProgress() > 0 &&
                isPointWithinBounds(130, 17, 24, 26, mouseX, mouseY)) {
            int percent = (int) ((float) handler.getCoolingProgress() / handler.getMaxCoolingProgress() * 100);
            context.drawTooltip(textRenderer, Text.literal("Cooling: " + percent + "%"), mouseX, mouseY);
        }
    }
}