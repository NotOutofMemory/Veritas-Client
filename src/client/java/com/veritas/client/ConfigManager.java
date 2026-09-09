package com.veritas.client;

import net.fabricmc.loader.api.FabricLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

// Import all this shit.

public class ConfigManager{

    // Set default variables and their values.
    public static class ModConfigData {
        public boolean ArmourDurability = false;
        public boolean Coords = false;
        public boolean FpsDisplay = false;
        public boolean Keystrokes = false;
        public int FpsX = 100;
        public int FpsY = 10;
        public int CoordsX = 10;
        public int CoordsY = 100;
        public String FpsDisplayTypeX = "centre";
        public String FpsDisplayTypeY = "coordinate";
        public String CoordsDisplayTypeX = "coordinate";
        public String CoordsDisplayTypeY = "centre";
    }

    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "veritas.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static ModConfigData INSTANCE = load();

    // Load the mod config data.
    private static ModConfigData load() {
        if (!FILE.exists()) {
            return new ModConfigData();
        }
        try (FileReader reader = new FileReader(FILE)) {
            ModConfigData data = GSON.fromJson(reader, ModConfigData.class);
            return data != null ? data : new ModConfigData();
        } catch (Exception e) {
            ExampleModClient.LOGGER.warn(e);
            return new ModConfigData();
        }
    }
    // Save the config.
    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (Exception e) {
            ExampleModClient.LOGGER.warn(e);
        }
    }

    // Load a config key (boolean).
    public static boolean loadConfigB(String key) {
        return switch (key) {
            case "ArmourDurability" -> INSTANCE.ArmourDurability;
            case "Coords" -> INSTANCE.Coords;
            case "FpsDisplay" -> INSTANCE.FpsDisplay;
            case "Keystrokes" -> INSTANCE.Keystrokes;
            default -> false;
        };
    }
    // Load a config key (int).
    public static int loadConfigI(String key) {
        return switch (key) {
            case "FpsX" -> INSTANCE.FpsX;
            case "FpsY" -> INSTANCE.FpsY;
            case "CoordsX" -> INSTANCE.CoordsX;
            case "CoordsY" -> INSTANCE.CoordsY;
            default -> 0;
        };
    }
    // Load a config key (String).
    public static String loadConfigS(String key) {
        return switch (key) {
            case "FpsDisplayTypeX" -> INSTANCE.FpsDisplayTypeX;
            case "FpsDisplayTypeY" -> INSTANCE.FpsDisplayTypeY;
            case "CoordsDisplayTypeX" -> INSTANCE.CoordsDisplayTypeX;
            case "CoordsDisplayTypeY" -> INSTANCE.CoordsDisplayTypeY;
            default -> "";
        };
    }
    // Save a key (boolean).
    public static void saveConfig(String key, boolean value) {
        switch (key) {
            case "ArmourDurability" -> INSTANCE.ArmourDurability = value;
            case "Coords" -> INSTANCE.Coords = value;
            case "FpsDisplay" -> INSTANCE.FpsDisplay = value;
            case "Keystrokes" -> INSTANCE.Keystrokes = value;
            default -> ExampleModClient.LOGGER.info("Unknown config key: " + key);
        }
        saveConfig();
    }
    // Save a key (int).
    public static void saveConfig(String key, int value) {
        switch (key) {
            case "FpsX" -> INSTANCE.FpsX = value;
            case "FpsY" -> INSTANCE.FpsY = value;
            case "CoordsX" -> INSTANCE.CoordsX = value;
            case "CoordsY" -> INSTANCE.CoordsY = value;
            default -> ExampleModClient.LOGGER.info("Unknown config key: " + key);
        }
        saveConfig();
    }

    public static void saveConfig(String key, String value) {
        switch (key) {
            case "FpsDisplayTypeX" -> INSTANCE.FpsDisplayTypeX = value;
            case "FpsDisplayTypeY" -> INSTANCE.FpsDisplayTypeY = value;
            case "CoordsDisplayTypeX" -> INSTANCE.CoordsDisplayTypeX = value;
            case "CoordsDisplayTypeY" -> INSTANCE.CoordsDisplayTypeY = value;
            default -> ExampleModClient.LOGGER.info("Unknow config key: " + key);
        }
    }
}
