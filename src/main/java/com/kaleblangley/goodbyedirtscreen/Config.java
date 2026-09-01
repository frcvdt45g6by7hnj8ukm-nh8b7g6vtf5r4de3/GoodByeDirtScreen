package com.kaleblangley.goodbyedirtscreen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.telemetry.TelemetryInfoScreen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("goodbye_dirt_screen.json");
    
    private static Config instance;
    
    public boolean darkening = true;
    public boolean blur = true;
    public List<String> allowScreenList = List.of(
            GenericDirtMessageScreen.class.getName(),
            LevelLoadingScreen.class.getName(),
            TelemetryInfoScreen.class.getName()
    );
    public List<String> excludeScreenList = List.of();
    
    public static Config getInstance() {
        if (instance == null) {
            load();
        }
        return instance;
    }
    
    public static void load() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String json = Files.readString(CONFIG_PATH);
                instance = GSON.fromJson(json, Config.class);
                if (instance == null) instance = new Config();
                if (instance.allowScreenList == null) instance.allowScreenList = new Config().allowScreenList;
                if (instance.excludeScreenList == null) instance.excludeScreenList = List.of();
            } else {
                instance = new Config();
                save();
            }
        } catch (IOException | JsonParseException e) {
            GoodByeDirtScreen.LOGGER.error("Failed to load config", e);
            instance = new Config();
        }
    }
    
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            String json = GSON.toJson(instance);
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            GoodByeDirtScreen.LOGGER.error("Failed to save config", e);
        }
    }
}
