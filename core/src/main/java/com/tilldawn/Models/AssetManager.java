package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private static AssetManager instance;

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public Skin getSkin() {
        return new Skin(Gdx.files.internal("skin/20MinTillDawn.json"));
    }
}
