package com.veritas.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    public static class ModulePos {
        public String anchorX = "START";
        public String anchorY = "START";
        public int offsetX = 0;
        public int offsetY = 0;
    }


    public static class ModConfigData {
        public boolean ArmourDurability = false;
        public boolean Coords = false;
        public boolean FpsDisplay = false;
        public boolean Keystrokes = false;


        public Map<String, ModulePos> positions = new HashMap<>();
    }

    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "veritas.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static ModConfigData INSTANCE = load();


    private static ModConfigData load() {
        if (!FILE.exists()) {
            return new ModConfigData();
        }
        try (FileReader reader = new FileReader(FILE)) {
            ModConfigData data = GSON.fromJson(reader, ModConfigData.class);
            if (data == null) return new ModConfigData();
            if (data.positions == null) data.positions = new HashMap<>(); // old config files
            return data;
        } catch (Exception e) {
            VeritasClient.LOGGER.warn("Failed to load config", e);
            return new ModConfigData();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (Exception e) {
            VeritasClient.LOGGER.warn("Failed to save config", e);
        }
    }



    public static boolean loadConfigB(String key) {
        return switch (key) {
            case "ArmourDurability" -> INSTANCE.ArmourDurability;
            case "Coords" -> INSTANCE.Coords;
            case "FpsDisplay" -> INSTANCE.FpsDisplay;
            case "Keystrokes" -> INSTANCE.Keystrokes;
            default -> false;
        };
    }

    public static void saveConfig(String key, boolean value) {
        switch (key) {
            case "ArmourDurability" -> INSTANCE.ArmourDurability = value;
            case "Coords" -> INSTANCE.Coords = value;
            case "FpsDisplay" -> INSTANCE.FpsDisplay = value;
            case "Keystrokes" -> INSTANCE.Keystrokes = value;
            default -> VeritasClient.LOGGER.info("Unknown config key: " + key);
        }
        saveConfig();
    }


    public static ModulePos loadPosition(String module) {
        if (INSTANCE.positions == null) return null;
        return INSTANCE.positions.get(module);
    }

    public static void savePosition(String module, ModulePos pos) {
        if (INSTANCE.positions == null) INSTANCE.positions = new HashMap<>();
        INSTANCE.positions.put(module, pos);
        saveConfig();
    }
}