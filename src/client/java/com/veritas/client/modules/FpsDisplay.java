package com.veritas.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class FpsDisplay extends Module {
    public FpsDisplay() {
        super("FpsDisplay");
        setDefaultPosition(10, 110);
    }

    private String text(boolean preview) {
        return preview ? "120" : Integer.toString(Minecraft.getInstance().getFps());
    }

    @Override public int getWidth()  { return Minecraft.getInstance().font.width(text(true)); }
    @Override public int getHeight() { return Minecraft.getInstance().font.lineHeight; }

    @Override
    protected void draw(GuiGraphicsExtractor g, int x, int y, boolean preview) {
        g.text(Minecraft.getInstance().font, text(preview), x, y, 0xFFFFFFFF);
    }
}