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

    /**
     * Creates a CrusherScreen bound to the given handler, player inventory, and title.
     *
     * @param handler   the CrusherScreenHandler that provides inventory slots and progress properties
     * @param inventory the player's inventory to display
     * @param title     the title component shown on the screen
     */
    public CrusherScreen(CrusherScreenHandler handler, Inventory inventory, Component title/*, int imageWidth, int imageHeight*/) {
        super(handler, inventory, title);
        //imageWidth = 176;
        //imageHeight = 176;
    }

    /**
     * Initializes the screen layout and positions the title label within the GUI.
     *
     * Sets the titleLabelX to 114 and titleLabelY to -4 and performs superclass initialization.
     */
    @Override
    protected void init() {
        super.init();
        titleLabelX = 114;
        titleLabelY = -4;
        //imageHeight = 196;
    }

    /**
     * Renders the crusher GUI background and its progress indicators.
     *
     * Centers the GUI on screen, draws the main background texture, and renders
     * the crush progress arrow and crushing progress overlays.
     *
     * @param context the graphics context used for drawing
     * @param delta   frame tick delta time (partial ticks)
     * @param mouseX  current mouse X coordinate relative to the window
     * @param mouseY  current mouse Y coordinate relative to the window
     */
    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        context.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, 176, 176, 256, 256);
        renderProgressArrow(context, x, y);
        renderProgressCrushing(context, x, y);
    }

    /**
     * Renders the crafting progress arrow when the menu reports positive progress and crafting is active.
     *
     * @param context the GUI rendering context
     * @param x the x coordinate of the GUI's top-left corner
     * @param y the y coordinate of the GUI's top-left corner
     */
    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        if(menu.getPropertyDelegate().get(0) > 0 && menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 79, y + 39, 0, 0,
                    menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    /**
     * Renders the vertical crushing progress indicator when the crusher is burning.
     *
     * Draws the crushing progress texture aligned to the GUI's top-left corner.
     *
     * @param context the GUI rendering context
     * @param x the x coordinate of the GUI's top-left corner
     * @param y the y coordinate of the GUI's top-left corner
     */
    private void renderProgressCrushing(GuiGraphics context, int x, int y) {
        if (menu.isBurning()) {
            int progress = menu.getScaledFuelProgress();
            context.blit(RenderPipelines.GUI_TEXTURED, CRUSHING_TEXTURE, x + 5, y + 69 - progress, 0,
                    20 - progress, 6, progress, 6, 20);
        }
    }

    /**
     * Draws the crusher GUI: background, all widgets and slots, and any context-sensitive tooltips at the cursor.
     */
    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        renderTooltip(context, mouseX, mouseY);
    }
}