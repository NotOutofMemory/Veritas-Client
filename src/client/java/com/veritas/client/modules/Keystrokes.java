package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class Keystrokes extends Module {
    private static final int BOX = 20; // Size of the box that is used for the keystroke
    private static final int GAP = 2; // Gap in between the boxes of the keystrokes
    private static final int MARGIN = 10; // Self-explanatory

    public Keystrokes() {
        super("Keystrokes"); // Pass module name to Module.java
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        if (!isEnabled("Keystrokes")) return; // Check if module is turned on from Module.java.

        Minecraft client = Minecraft.getInstance(); // Get client.
        if (client.player == null) return; // Check if player exists.

        int baseX = MARGIN; // Set baseX to MARGIN.
        int baseY = MARGIN; // Set baseY to MARGIN.

        // Link all actions to variables.
        boolean w = client.options.keyUp.isDown();
        boolean a = client.options.keyLeft.isDown();
        boolean s = client.options.keyDown.isDown();
        boolean d = client.options.keyRight.isDown();
        boolean space = client.options.keyJump.isDown();
        boolean leftClick = client.options.keyAttack.isDown();
        boolean rightClick = client.options.keyUse.isDown();

        // Draw boxes for keystrokes.
        drawKey(graphics, client, baseX + BOX + GAP, baseY, "W", w, BOX);
        drawKey(graphics, client, baseX, baseY + BOX + GAP, "A", a, BOX);
        drawKey(graphics, client, baseX + BOX + GAP, baseY + BOX + GAP, "S", s, BOX);
        drawKey(graphics, client, baseX + (BOX + GAP) * 2, baseY + BOX + GAP, "D", d, BOX);
        drawKey(graphics, client, baseX, baseY + (BOX + GAP) * 2, "SPACE", space, (BOX + GAP) * 3 - GAP);

        // Draw boxes for mouse actions.
        drawKey(graphics, client, baseX, baseY + ( 3 * (BOX + GAP)), "LMB", leftClick, 31);
        drawKey(graphics, client, baseX + 33, baseY + ( 3 * (BOX + GAP)), "RMB", rightClick, 31);
    }

    private static void drawKey(GuiGraphicsExtractor graphics, Minecraft client, int x, int y, String label, boolean active, int width) {
        // Set text color and background color.
        int bgColor = active ? 0x8000FFFF : 0x80000000;
        int textColor = active ? 0xFF000000 : 0xFFFFFFFF;

        graphics.fill(x, y, x + width, y + BOX, bgColor); // Make a box.

        int textWidth = client.font.width(label);
        int textX = x + (width - textWidth) / 2;
        int textY = y + (BOX - client.font.lineHeight) / 2;
        graphics.text(client.font, label, textX, textY, textColor, false);
    }
}