package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    }

    public Animation<TextureRegion> get(String name) {
        return animations.get(name);
    }
}
