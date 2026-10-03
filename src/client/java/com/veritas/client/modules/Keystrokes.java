package com.veritas.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class Keystrokes extends Module {
    private static final int BOX = 20;  // Size of each key box
    private static final int GAP = 2;   // Gap between boxes
    private static final int STEP = BOX + GAP;
    private static final int MOUSE_W = 31;

    private static final int TOTAL_W = STEP * 3 - GAP;      // 64
    private static final int TOTAL_H = STEP * 3 + BOX;      // 86

    public Keystrokes() {
        super("Keystrokes");
        setDefaultPosition(10, 10);
    }

    @Override public int getWidth()  { return TOTAL_W; }
    @Override public int getHeight() { return TOTAL_H; }

    @Override
    protected void draw(GuiGraphicsExtractor g, int x, int y, boolean preview) {
        Minecraft client = Minecraft.getInstance();
        if (!preview && client.player == null) return;

        boolean w     = !preview && client.options.keyUp.isDown();
        boolean a     = !preview && client.options.keyLeft.isDown();
        boolean s     = !preview && client.options.keyDown.isDown();
        boolean d     = !preview && client.options.keyRight.isDown();
        boolean space = !preview && client.options.keyJump.isDown();
        boolean lmb   = !preview && client.options.keyAttack.isDown();
        boolean rmb   = !preview && client.options.keyUse.isDown();

        drawKey(g, client, x + STEP,     y,            "W", w, BOX);
        drawKey(g, client, x,            y + STEP,     "A", a, BOX);
        drawKey(g, client, x + STEP,     y + STEP,     "S", s, BOX);
        drawKey(g, client, x + STEP * 2, y + STEP,     "D", d, BOX);
        drawKey(g, client, x,            y + STEP * 2, "SPACE", space, TOTAL_W);

        drawKey(g, client, x,                   y + STEP * 3, "LMB", lmb, MOUSE_W);
        drawKey(g, client, x + MOUSE_W + GAP,   y + STEP * 3, "RMB", rmb, MOUSE_W);
    }

    private static void drawKey(GuiGraphicsExtractor g, Minecraft client, int x, int y,
                                String label, boolean active, int width) {
        int bgColor = active ? 0x8000FFFF : 0x80000000;
        int textColor = active ? 0xFF000000 : 0xFFFFFFFF;

        g.fill(x, y, x + width, y + BOX, bgColor);

        int textX = x + (width - client.font.width(label)) / 2;
        int textY = y + (BOX - client.font.lineHeight) / 2;
        g.text(client.font, label, textX, textY, textColor, false);
    }
}