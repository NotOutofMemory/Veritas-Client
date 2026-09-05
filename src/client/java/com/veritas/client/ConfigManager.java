package com.veritas.client;

import net.fabricmc.loader.api.FabricLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager{

    public static class ModConfigData {
        public boolean ArmourDurability = false;
        public boolean Coords = false;
        public boolean FpsDisplay = false;
        public boolean Keystrokes = false;
        public int FpsX = 100;
        public int FpsY = 100;
    }

    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "mymod.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static ModConfigData INSTANCE = load();

    private static ModConfigData load() {
        if (!FILE.exists()) {
            return new ModConfigData();
        }
        try (FileReader reader = new FileReader(FILE)) {
            ModConfigData data = GSON.fromJson(reader, ModConfigData.class);
            return data != null ? data : new ModConfigData();
        } catch (Exception e) {
            e.printStackTrace();
            return new ModConfigData();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (Exception e) {
            e.printStackTrace();
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
    public static int loadConfigI(String key) {
        return switch (key) {
            case "FpsX" -> INSTANCE.FpsX;
            case "FpsY" -> INSTANCE.FpsY;
            default -> 0;
        };
    }
    public static void saveConfig(String key, boolean value) {
        switch (key) {
            case "ArmourDurability" -> INSTANCE.ArmourDurability = value;
            case "Coords" -> INSTANCE.Coords = value;
            case "FpsDisplay" -> INSTANCE.FpsDisplay = value;
            case "Keystrokes" -> INSTANCE.Keystrokes = value;
            default -> System.out.println("Unknown config key: " + key);
        }
        saveConfig();
    }

    public static void saveConfig(String key, int value) {
        switch (key) {
            case "FpsX" -> INSTANCE.FpsX = value;
            case "FpsY" -> INSTANCE.FpsY = value;
            default -> System.out.println("Unknown config key: " + key);
        }
        saveConfig();
    }
}
