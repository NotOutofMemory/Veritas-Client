package com.veritas.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.input.MouseButtonEvent;

public class HudEditor extends Screen {

    public HudEditor(Component title) {
        super(title);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (event.button() == 0) {
            Minecraft client = Minecraft.getInstance();

            double mouseX = client.mouseHandler.xpos() / client.getWindow().getGuiScale();
            double mouseY = client.mouseHandler.ypos() / client.getWindow().getGuiScale();

            VeritasClient.LOGGER.info("Left click detected in HUD Editor at X: {}, Y: {}", mouseX, mouseY);

            return true;
        }

        return super.mouseClicked(event, doubleClick);
    }


    public boolean mouseDragged(MouseButtonEvent event, double mouseX, double mouseY) {
        // Check if the button being dragged is the Left Click (typically button 0)
        if (event.button() == 0) {
            VeritasClient.LOGGER.info("Dragging in HUD Editor at X: {}, Y: {}", mouseX, mouseY);

            return true;
        }
        return super.mouseDragged(event, mouseX, mouseY);
    }


    public double getMouseX() {
        Minecraft client = Minecraft.getInstance();

        return client.mouseHandler.xpos();
    }

    public double getMouseY() {
        Minecraft client = Minecraft.getInstance();

        return client.mouseHandler.ypos();
    }
}
