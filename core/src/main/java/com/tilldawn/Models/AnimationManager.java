package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {
    private static AnimationManager instance;
    private final Map<String, Animation<TextureRegion>> animations = new HashMap<>();

    /*
    blinkLeft
    blinkRight
    */

    private AnimationManager() {
        loadAnimations();
    }

    public static AnimationManager getInstance() {
        if (instance == null) {
            instance = new AnimationManager();
        }
        return instance;
    }

    private void loadAnimations() {
        // Blick Eye :
        Texture blinkSheetLeft = new Texture(Gdx.files.internal("BlickEyeLeft.png"));
        TextureRegion[][] blinkFramesLeft = TextureRegion.split(blinkSheetLeft, blinkSheetLeft.getWidth() / 11, blinkSheetLeft.getHeight());
        animations.put("blinkLeft", new Animation<>(0.03f, blinkFramesLeft[0]));

        Texture blinkSheetRight = new Texture(Gdx.files.internal("BlickEyeRight.png"));
        TextureRegion[][] blinkFramesRight = TextureRegion.split(blinkSheetRight, blinkSheetRight.getWidth() / 11, blinkSheetRight.getHeight());
        animations.put("blinkRight", new Animation<>(0.03f, blinkFramesRight[0]));

        Array<TextureRegion> frames = new Array<>();

        for (int i = 7; i >= 1; i--) {
            Texture tex = new Texture(Gdx.files.internal("LoginMenuAssets/shadow" + i + ".png"));
            frames.add(new TextureRegion(tex));
        }

        for (int i = 1; i <= 7; i++) {
            Texture tex = new Texture(Gdx.files.internal("LoginMenuAssets/shadow" + i + ".png"));
            frames.add(new TextureRegion(tex));
        }

        Animation<TextureRegion> swordShadow = new Animation<>(0.1f, frames);
        animations.put("swordShadow", swordShadow);
    }

    public Animation<TextureRegion> get(String name) {
        return animations.get(name);
    }
}
