package com.veritas.client.widgets;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class Button extends AbstractWidget {

    public Button(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        // We'll just draw a simple rectangle for now.
        // x1, y1, x2, y2, startColor, endColor
        int startColor = 0xFF00FF00; // Green
        int endColor = 0xFF0000FF; // Blue

        int buttonColor = 0xFF000000;

        if (isHovered()) {
            buttonColor = 0xFFFFFFFF;
        }


        graphics.fill(getX(), getY(), getX() + this.width, getY() + this.height, buttonColor);
        graphics.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, endColor);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput builder) {
        // For brevity, we'll just skip this for now - if you want to add narration to your widget, you can do so here.
        return;
    }

}
