package anya.pizza.houseki.screen.custom;

import anya.pizza.houseki.Houseki;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FoundryScreen extends HandledScreen<FoundryScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/crusher/crusher_gui.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/crusher/crush_progress.png");
    private static final Identifier CRUSHING_TEXTURE = Identifier.of(Houseki.MOD_ID, "textures/gui/crusher/crushing_progress.png");

    /**
     * Creates a FoundryScreen backed by the given screen handler and player inventory and displays the specified title.
     *
     * Initializes the screen and sets the default background size to 176×176 pixels.
     *
     * @param handler   the screen handler that provides sync state and logic for this screen
     * @param inventory the player's inventory to display and interact with
     * @param title     the title text shown on the screen
     */
    public FoundryScreen(FoundryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 176;
    }

    /**
     * Initializes the screen layout and overrides default title positioning and background height.
     *
     * Sets the title X/Y coordinates and adjusts the background height for this screen after superclass initialization.
     */
    @Override
    protected void init() {
        super.init();
        titleX = 114;
        titleY = -4;
        backgroundHeight = 196;
    }

    /**
     * Draws the foundry screen's background and its progress indicators.
     *
     * This centers and renders the main GUI texture, then renders the arrow and crushing progress overlays.
     *
     * @param context the draw context used for rendering
     * @param delta   frame delta time in seconds
     * @param mouseX  current mouse x position in screen coordinates
     * @param mouseY  current mouse y position in screen coordinates
     */
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, 176, 176, 256, 256);
        renderProgressArrow(context, x, y);
        renderProgressCrushing(context, x, y);
    }

    /**
     * Draws the crafting progress arrow on the GUI when crafting is in progress and the handler's
     * property delegate value at index 0 is greater than zero.
     *
     * @param context the draw context used to render textures
     * @param x the x coordinate of the GUI's top-left corner
     * @param y the y coordinate of the GUI's top-left corner
     */
    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.getPropertyDelegate().get(0) > 0 && handler.isCrafting()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 79, y + 39, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    /**
     * Draws the crushing (fuel) progress indicator on the GUI when the foundry is burning.
     *
     * The indicator's height reflects the current fuel progress and is rendered using the
     * CRUSHING_TEXTURE at a fixed offset from the GUI origin.
     *
     * @param context the drawing context to render textures
     * @param x       the x-coordinate of the GUI's top-left corner
     * @param y       the y-coordinate of the GUI's top-left corner
     */
    private void renderProgressCrushing(DrawContext context, int x, int y) {
        if (handler.isBurning()) {
            int progress = handler.getScaledFuelProgress();
            context.drawTexture(RenderPipelines.GUI_TEXTURED, CRUSHING_TEXTURE, x + 5, y + 69 - progress, 0,
                    20 - progress, 6, progress, 6, 20);
        }
    }

    /**
     * Renders the screen: draws the background, the screen contents, and any mouse-over tooltip.
     *
     * @param context the drawing context used for batched GUI rendering
     * @param mouseX  current mouse x coordinate relative to the window
     * @param mouseY  current mouse y coordinate relative to the window
     * @param delta   frame interpolation delta (partial ticks) for smooth animations
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}