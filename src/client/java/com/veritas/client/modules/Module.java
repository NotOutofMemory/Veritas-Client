package com.veritas.client.modules;

import com.veritas.client.ConfigManager;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public abstract class Module {
    public enum Anchor { START, CENTER, END }

    private final String name;
    private boolean enabled;


    public Anchor anchorX = Anchor.START, anchorY = Anchor.START;
    public int offsetX, offsetY;


    private boolean hasSavedPosition;

    public Module(String name) {
        this(name, false);
    }

    public Module(String name, boolean enabledByDefault) {
        this.name = name;
        this.enabled = ConfigManager.loadConfigB(name);
        loadPosition();
    }

    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }

    public void toggle() { setEnabled(!enabled); }

    public void setEnabled(boolean value) {
        enabled = value;
        ConfigManager.saveConfig(name, enabled);
        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}


    public abstract int getWidth();
    public abstract int getHeight();

    protected abstract void draw(GuiGraphicsExtractor g, int x, int y, boolean preview);

    public final void render(GuiGraphicsExtractor g, DeltaTracker dt) {
        if (!enabled) return;
        draw(g, getX(), getY(), false);
    }

    public final void renderPreview(GuiGraphicsExtractor g) {
        draw(g, getX(), getY(), true);
    }


    private static int screenW() { return Minecraft.getInstance().getWindow().getGuiScaledWidth(); }
    private static int screenH() { return Minecraft.getInstance().getWindow().getGuiScaledHeight(); }

    private static int base(Anchor a, int screen, int size) {
        return switch (a) {
            case START -> 0;
            case CENTER -> (screen - size) / 2;
            case END -> screen - size;
        };
    }

    public int getX() { return base(anchorX, screenW(), getWidth()) + offsetX; }
    public int getY() { return base(anchorY, screenH(), getHeight()) + offsetY; }

    public boolean isHovered(double mx, double my) {
        int x = getX(), y = getY();
        return mx >= x && mx <= x + getWidth() && my >= y && my <= y + getHeight();
    }

    public void setAbsolutePosition(int x, int y) {
        int sw = screenW(), sh = screenH(), w = getWidth(), h = getHeight();

        double cx = x + w / 2.0, cy = y + h / 2.0;
        anchorX = cx < sw / 3.0 ? Anchor.START : cx > sw * 2 / 3.0 ? Anchor.END : Anchor.CENTER;
        anchorY = cy < sh / 3.0 ? Anchor.START : cy > sh * 2 / 3.0 ? Anchor.END : Anchor.CENTER;

        offsetX = x - base(anchorX, sw, w);
        offsetY = y - base(anchorY, sh, h);
    }

    public void savePosition() {
        ConfigManager.ModulePos p = new ConfigManager.ModulePos();
        p.anchorX = anchorX.name();
        p.anchorY = anchorY.name();
        p.offsetX = offsetX;
        p.offsetY = offsetY;
        ConfigManager.savePosition(name, p);
        hasSavedPosition = true;
    }

    private void loadPosition() {
        ConfigManager.ModulePos p = ConfigManager.loadPosition(name);
        if (p == null) return;
        try {
            anchorX = Anchor.valueOf(p.anchorX);
            anchorY = Anchor.valueOf(p.anchorY);
            offsetX = p.offsetX;
            offsetY = p.offsetY;
            hasSavedPosition = true;
        } catch (IllegalArgumentException ignored) {}
    }

    protected void setDefaultPosition(int x, int y) {
        if (hasSavedPosition) return;
        offsetX = x;
        offsetY = y;
    }
}