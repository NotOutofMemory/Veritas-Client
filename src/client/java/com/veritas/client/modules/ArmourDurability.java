package com.veritas.client.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArmourDurability extends Module {

    private static final int ROW_HEIGHT = 15;
    private static final int ICON_SIZE = 16;
    private static final int TEXT_OFFSET_X = 15;
    private static final int TEXT_OFFSET_Y = 4;
    private static final int PIECES = 4;

    public ArmourDurability() {
        super("ArmourDurability");
        setDefaultPosition(200, 10);
    }

    private ItemStack[] getPieces(boolean preview) {
        if (preview) {
            return new ItemStack[] {
                    new ItemStack(Items.DIAMOND_HELMET),
                    new ItemStack(Items.DIAMOND_CHESTPLATE),
                    new ItemStack(Items.DIAMOND_LEGGINGS),
                    new ItemStack(Items.DIAMOND_BOOTS)
            };
        }

        Player player = Minecraft.getInstance().player;
        if (player == null) return null;

        return new ItemStack[] {
                player.getSlot(103).get(),
                player.getSlot(102).get(),
                player.getSlot(101).get(),
                player.getSlot(100).get()
        };
    }

    @Override
    public int getWidth() {
        return TEXT_OFFSET_X + Minecraft.getInstance().font.width("592");
    }

    @Override
    public int getHeight() {
        return ROW_HEIGHT * (PIECES - 1) + ICON_SIZE;
    }

    @Override
    protected void draw(GuiGraphicsExtractor g, int x, int y, boolean preview) {
        ItemStack[] pieces = getPieces(preview);
        if (pieces == null) return;

        Font font = Minecraft.getInstance().font;

        for (int i = 0; i < pieces.length; i++) {
            ItemStack piece = pieces[i];
            if (piece.isEmpty() || !piece.isDamageableItem()) continue;

            int remaining = piece.getMaxDamage() - piece.getDamageValue();
            int rowY = y + i * ROW_HEIGHT;

            g.item(piece, x, rowY);
            g.text(font, String.valueOf(remaining), x + TEXT_OFFSET_X, rowY + TEXT_OFFSET_Y, 0xFFFFFFFF);
        }
    }
}