package com.veritas.client.modules;

import com.veritas.client.ConfigManager;

public abstract class Module {
    private final String name;
    private boolean enabled;

    public Module(String name) {
        this(name, false);
    }

    public Module(String name, boolean enabledByDefault) {
        this.name = name;
        this.enabled = ConfigManager.loadConfigB(name);
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled(String ModuleName) {
        return ConfigManager.loadConfigB(ModuleName);
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean value) {
        enabled = value;
        ConfigManager.saveConfig(name, enabled);
        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
}