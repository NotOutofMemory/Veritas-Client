package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;

public class Coords extends Module {

    public Coords() {
        super("Coords");
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        if (!isEnabled("Coords")) return; // Check if module is enabled check Module.java for more info.

        Minecraft client = Minecraft.getInstance(); // Get minecraft instance.
        Player player = client.player; // Get player
        if (player == null) return; // If player doesn't exist avoid erroring and return.

        int x = (int) Math.floor(player.getX()); // Get X coordinate and use Math.floor to make it a nicer number/
        int y = (int) Math.floor(player.getY()); // Get Y coordinate and use Math.floor to make it a nicer number/
        int z = (int) Math.floor(player.getZ()); // Get Z coordinate and use Math.floor to make it a nicer number/

        String coordsText = String.format("XYZ: %d / %d / %d", x, y, z); // get the coords text.

        graphics.text(client.font, coordsText, 5, 5, 0xFFFFFFFF, true); // actually display it on the screen
    }
}