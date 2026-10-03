package com.veritas.client;

import com.veritas.client.modules.Keystrokes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import com.mojang.blaze3d.vertex.*;

public class HudEditor extends Screen {
    public HudEditor(Component title) {
        super(title);
    }

    private void checkCollisions() {
        Minecraft client = Minecraft.getInstance();
        double mouseX = client.mouseHandler.xpos() * (double) client.getWindow().getGuiScaledWidth() / (double) client.getWindow().getWidth();
        double mouseY = client.mouseHandler.xpos() * (double) client.getWindow().getGuiScaledHeight() / (double) client.getWindow().getHeight();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        renderPreview(graphics);
    }

    public void renderPreview(GuiGraphicsExtractor graphics) {
        Keystrokes.renderPreview(graphics);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        VeritasClient.LOGGER.info("HudEditor clicked");
        return true;
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double offsetX, double offsetY) {
        if (event.button() == 1) {
            VeritasClient.LOGGER.info("HudEditor dragged, offset {}, {}", offsetX, offsetY);
            checkCollisions();

            return true;
        }
        return super.mouseDragged(event, offsetX, offsetY);
    }

}
