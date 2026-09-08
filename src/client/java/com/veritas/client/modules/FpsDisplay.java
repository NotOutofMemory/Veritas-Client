package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Font;
import com.veritas.client.ConfigManager;

import java.util.Objects;

public class FpsDisplay extends Module{
    public FpsDisplay() {
        super("FpsDisplay");
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTime) {
        if (!isEnabled("FpsDisplay")) return;


        Minecraft client = Minecraft.getInstance();
        int fps = client.getFps();
        Font font = client.font;
        
        int x;
        int y;
        int width = client.getWindow().getGuiScaledWidth();
        
        if (Objects.equals(ConfigManager.loadConfigS("FpsDisplayTypeY"), "centre")) {
            y = client.getWindow().getGuiScaledHeight() / 2;
        } else if (Objects.equals(ConfigManager.loadConfigS("FpsDisplayTypeY"), "coordinate")) {
            y = ConfigManager.loadConfigI("FpsY");
        } else {
            y = 0;
        }

        if (Objects.equals(ConfigManager.loadConfigS("FpsDisplayTypeX"), "centre")) {
            x = client.getWindow().getGuiScaledWidth() / 2;
        } else if (Objects.equals(ConfigManager.loadConfigS("FpsDisplayTypeX"), "coordinate")) {
            x = ConfigManager.loadConfigI("FpsY");
        } else {
            x = 0;
        }



        graphics.text(font, Integer.toString(fps), x, y, 0xFFFFFFFF);
    }
}
