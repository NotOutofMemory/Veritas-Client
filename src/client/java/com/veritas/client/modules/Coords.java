package com.veritas.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;

public class Coords extends Module {


    private static final String SAMPLE = "XYZ: -30000000 / 320 / -30000000";

    public Coords() {
        super("Coords");
        setDefaultPosition(120, 10);
    }

    private String text(boolean preview) {
        if (preview) return "XYZ: 123 / 64 / -456";

        Player player = Minecraft.getInstance().player;
        if (player == null) return null;

        return String.format("XYZ: %d / %d / %d",
                (int) Math.floor(player.getX()),
                (int) Math.floor(player.getY()),
                (int) Math.floor(player.getZ()));
    }

    @Override
    public int getWidth() {
        return Minecraft.getInstance().font.width(text(true));
    }

    @Override
    public int getHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    protected void draw(GuiGraphicsExtractor g, int x, int y, boolean preview) {
        String s = text(preview);
        if (s == null) return;

        g.text(Minecraft.getInstance().font, s, x, y, 0xFFFFFFFF, true);
    }
}