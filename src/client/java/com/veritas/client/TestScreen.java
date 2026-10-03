package com.veritas.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TestScreen extends Screen {
    public TestScreen(MutableComponent testMenu) {
        super(Component.literal("Test"));
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        VeritasClient.LOGGER.info("TEST SCREEN CLICKED");
        return true;
    }
}