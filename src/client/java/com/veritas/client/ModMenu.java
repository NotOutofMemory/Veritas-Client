package com.veritas.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class ModMenu extends Screen { // Extend Screen class for making a Screen like documented in the fabric docs.
    int ScreenWidth;

    public ModMenu(Component title) {
        super(title);
    }

    private void ToggleModule(String Module){
        ExampleModClient.moduleManager.toggleModule(Module); // Toggle the module using ExampleModClient.
    }

    // Create toggle for module.
    protected void CreateModuleToggle(String Text, int x, int y, Consumer<String> func, String ModuleName) {
        int ButtonWidth = 140;
        ScreenWidth = this.width;
        Button toggleButton = Button.builder( // Build the button widget.
                getToggleLabel(ModuleName, ExampleModClient.moduleManager.getModule(ModuleName).isEnabled(ModuleName)), // Set the text of the button.
                (btn) -> {
                    func.accept(ModuleName);
                    boolean nowEnabled = ExampleModClient.moduleManager.getModule(ModuleName).isEnabled(ModuleName); // Set nowEnabled to the value isEnabled returns in Module.java.
                    btn.setMessage(getToggleLabel(Text, nowEnabled)); // Gets the label from getToggleLabel.
                }
        ).bounds(x, y, 120, 20).build();

        this.addRenderableWidget(toggleButton); // Render the actual button.
    }

    @Override
    protected void init() { // Pass required args to CreateModuleToggle for buttons.
        CreateModuleToggle("Keystrokes", 40, 40, (func) -> { ToggleModule("Keystrokes"); }, "Keystrokes");
        CreateModuleToggle("Coordinates", (ScreenWidth > 280) ? 170 : 40, (ScreenWidth > 280) ? 40 : 70, (func) -> { ToggleModule("Coords"); }, "Coords");
        CreateModuleToggle("ArmourDurability", (ScreenWidth > 420) ? 300 : 170, (ScreenWidth > 420) ? 40 : 70, (func) -> { ToggleModule("ArmourDurability"); }, "ArmourDurability");
        CreateModuleToggle("Fps Display", (ScreenWidth > 560) ? 430 : 40, (ScreenWidth > 560) ? 40 : 70, (func) -> { ToggleModule("FpsDisplay"); }, "FpsDisplay");
    }
    private Component getToggleLabel(String Text, boolean Toggled) { // Generate the text for the buttons.
        System.out.println(ScreenWidth);
        String stateText = Toggled ? "On" : "Off"; // If toggled true set stateText to On else set it to Off.
        return Component.literal(Text+ ": " + stateText); // Return the text.
    }
}
