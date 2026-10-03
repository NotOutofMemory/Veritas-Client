package com.veritas.client;

import com.veritas.client.modules.Module;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModMenu extends Screen {
    public ModMenu(Component title) {
        super(title);
    }

    protected void createModuleToggle(String text, int x, int y, String moduleName) {
        Module module = VeritasClient.moduleManager.getModule(moduleName);

        Button toggleButton = Button.builder(
                getToggleLabel(text, module.isEnabled()),
                (btn) -> {
                    VeritasClient.moduleManager.toggleModule(moduleName);
                    btn.setMessage(getToggleLabel(text, module.isEnabled()));
                }
        ).bounds(x, y, 120, 20).build();

        this.addRenderableWidget(toggleButton);
    }

    @Override
    protected void init() {
        int w = this.width;
        createModuleToggle("Keystrokes", 40, 40, "Keystrokes");
        createModuleToggle("Coordinates", (w > 280) ? 170 : 40, (w > 280) ? 40 : 70, "Coords");
        createModuleToggle("ArmourDurability", (w > 420) ? 300 : 170, (w > 420) ? 40 : 70, "ArmourDurability");
        createModuleToggle("Fps Display", (w > 560) ? 430 : 40, (w > 560) ? 40 : 70, "FpsDisplay");
    }

    private Component getToggleLabel(String text, boolean toggled) {
        return Component.literal(text + ": " + (toggled ? "On" : "Off"));
    }
}