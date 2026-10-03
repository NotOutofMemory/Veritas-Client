package com.veritas.client;

import com.veritas.client.modules.Module;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class HudEditor extends Screen {
    private Module dragging;
    private double grabX, grabY;
    private static final int SNAP = 5;

    public HudEditor(Component title) {
        super(title);
    }

    private List<Module> modules() {
        return VeritasClient.moduleManager.getModules();
    }

    private Module getAt(double mx, double my) {
        List<Module> list = modules();
        for (int i = list.size() - 1; i >= 0; i--) {
            Module m = list.get(i);
            if (m.isEnabled() && m.isHovered(mx, my)) return m;
        }
        return null;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(g, mouseX, mouseY, partialTick);
        Module hovered = getAt(mouseX, mouseY);

        for (Module m : modules()) {
            if (!m.isEnabled()) continue;
            m.renderPreview(g);
            int c = (m == dragging || m == hovered) ? 0xFFFFFFFF : 0x80FFFFFF;
            outline(g, m.getX() - 1, m.getY() - 1, m.getWidth() + 2, m.getHeight() + 2, c);
        }
    }

    private void outline(GuiGraphicsExtractor g, int x, int y, int w, int h, int c) {
        g.fill(x, y, x + w, y + 1, c);
        g.fill(x, y + h - 1, x + w, y + h, c);
        g.fill(x, y, x + 1, y + h, c);
        g.fill(x + w - 1, y, x + w, y + h, c);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent e, boolean doubleClick) {
        Module hit = getAt(e.x(), e.y());
        if (hit != null) {
            dragging = hit;
            grabX = e.x() - hit.getX();
            grabY = e.y() - hit.getY();
            modules().remove(hit); // bring to front
            modules().add(hit);
            return true;
        }
        return super.mouseClicked(e, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent e, double dx, double dy) {
        if (dragging != null) {
            int w = dragging.getWidth(), h = dragging.getHeight();
            int nx = Math.max(0, Math.min((int) (e.x() - grabX), width - w));
            int ny = Math.max(0, Math.min((int) (e.y() - grabY), height - h));

            nx = snap(nx, 0, (width - w) / 2, width - w);
            ny = snap(ny, 0, (height - h) / 2, height - h);

            dragging.setAbsolutePosition(nx, ny);
            return true;
        }
        return super.mouseDragged(e, dx, dy);
    }

    private int snap(int v, int... targets) {
        for (int t : targets) if (Math.abs(v - t) <= SNAP) return t;
        return v;
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent e) {
        if (dragging != null) {
            dragging.savePosition();
            dragging = null;
            return true;
        }
        return super.mouseReleased(e);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}