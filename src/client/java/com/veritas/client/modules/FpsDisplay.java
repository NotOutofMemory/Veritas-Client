package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Font;
import com.veritas.client.ConfigManager;

public class FpsDisplay extends Module{
    public FpsDisplay() {
        super("FpsDisplay");
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTime) {
        if (!isEnabled("FpsDisplay")) return;


        Minecraft client = Minecraft.getInstance();
        int fps = client.getFps();
        Font font = client.font;

        int width = client.getWindow().getGuiScaledWidth();
        int x = ConfigManager.loadConfigI("FpsX");
        int y = ConfigManager.loadConfigI("FpsY");

        graphics.text(font, Integer.toString(fps), x, y, 0xFFFFFFFF);
    }


}
