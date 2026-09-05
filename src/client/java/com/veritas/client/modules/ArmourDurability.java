package com.veritas.client.modules;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class ArmourDurability extends Module {


    public ArmourDurability() {
        super("ArmourDurability"); // Send name to Module.java
    }

    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTime) {
        if (!isEnabled("ArmourDurability")) return; // Check if module is enabled.

        // Get screen width and height.
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        // calculate the right edge of the hotbar.
        int hotbarEdge = (screenWidth / 2) + 91;

        // Get the client and player and stop if player has not loaded in yet.
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        if (player == null) return; // No player loaded yet.


        // Get all the items from the armour slots.
        ItemStack helmet = player.getSlot(103).get();
        ItemStack chestplate = player.getSlot(102).get();
        ItemStack leggings = player.getSlot(101).get();
        ItemStack boots = player.getSlot(100).get();

        // Turn all the armour pieces into an array.
        ItemStack[] armorPieces = {helmet, chestplate, leggings, boots};

        // Get the font.
        Font font = client.font;

        // Loop trough all the armour pieces.
        for (int i = 0; i < armorPieces.length; i++) {
            if (!armorPieces[i].isEmpty() || armorPieces[i].isDamageableItem()) {

                // Get the current damage, max damage and subtract them from each other to get the current damage.
                int damageValue = armorPieces[i].getDamageValue();
                int maxDamage = armorPieces[i].getMaxDamage();
                int currentDamage = maxDamage - damageValue;

                // Set x and y variables empty.
                int x;
                int y;


                if (i == 0){
                    // Set x and y.
                    x = hotbarEdge + 5;
                    y = screenHeight - 65;

                    // Render the armor piece and the durability text.
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                } else if (i==1) {
                    // Set x and y.
                    x = hotbarEdge + 5;
                    y = screenHeight - 50;

                    // Render the armor piece and the durability text.
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                } else if (i==2) {
                    // Set x and y.
                    x = hotbarEdge + 5;
                    y = screenHeight - 35;

                    // Render the armor piece and the durability text.
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                } else {
                    // Set x and y.
                    x = hotbarEdge + 5;
                    y = screenHeight - 20;

                    // Render the armor piece and the durability text.
                    graphics.item(armorPieces[i], x, y);
                    graphics.text(font, String.valueOf(currentDamage), x+15, y+4, 0xFFFFFFFF);
                }
            }
        }
    }
}