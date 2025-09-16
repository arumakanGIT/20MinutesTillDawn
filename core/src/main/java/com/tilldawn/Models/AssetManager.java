package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private static AssetManager instance;
    private final Map<String, String> assetMap = new HashMap<>();

    private AssetManager() {
        scanAssets("assets");
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private void scanAssets(String path) {
        FileHandle folder = Gdx.files.internal(path);
        if (!folder.exists()) return;

        for (FileHandle file : folder.list()) {
            if (file.isDirectory()) {
                scanAssets(file.path());
            } else {
                String name = file.name();
                assetMap.put(name, file.path());
            }
        }
    }

    public Texture getTexture(String name) {
        String path = assetMap.get(name);
        if (path != null) {
            return new Texture(Gdx.files.internal(path));
        }
        throw new RuntimeException("Texture not found: " + name);
    }

    public Skin getSkin() {
        return new Skin(Gdx.files.internal("skin/20MinTillDawn.json"));
    }
}
