package com.veritas.client.modules;

import com.veritas.client.ExampleModClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    public final Keystrokes keystrokes = new Keystrokes();
    public final Coords coords = new Coords();
    public final ArmourDurability armourDurability = new ArmourDurability();
    public final FpsDisplay fpsDisplay = new FpsDisplay();

    public ModuleManager() {
        modules.add(keystrokes);
        modules.add(coords);
        modules.add(armourDurability);
        modules.add(fpsDisplay);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    public void toggleModule(String name) {
        Module m = getModule(name);
        if (m != null) {
            m.toggle();
            ExampleModClient.LOGGER.info("{} toggled, enabled = {}", name, m.isEnabled(name));
        } else {
            ExampleModClient.LOGGER.info("No module found with name: {}", name);
        }
    }
}