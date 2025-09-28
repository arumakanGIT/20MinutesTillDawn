package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Models.Enums.Gun;

import java.util.HashMap;
import java.util.Map;

public class AnimationManager {
    private static AnimationManager instance;
    private final Map<String, Animation<TextureRegion>> animations = new HashMap<>();

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
        // blinkLeft :
        Texture blinkSheetLeft = new Texture(Gdx.files.internal("BlickEyeLeft.png"));
        TextureRegion[][] blinkFramesLeft = TextureRegion.split(blinkSheetLeft, blinkSheetLeft.getWidth() / 11, blinkSheetLeft.getHeight());
        animations.put("blinkLeft", new Animation<>(0.03f, blinkFramesLeft[0]));

        // blinkRight
        Texture blinkSheetRight = new Texture(Gdx.files.internal("BlickEyeRight.png"));
        TextureRegion[][] blinkFramesRight = TextureRegion.split(blinkSheetRight, blinkSheetRight.getWidth() / 11, blinkSheetRight.getHeight());
        animations.put("blinkRight", new Animation<>(0.03f, blinkFramesRight[0]));

        // swordShadow
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

        // winged monster

        Array<TextureRegion> wingedMonsterFrames = new Array<>();
        for (int i = 0; i <= 4; i++) {
            Texture tex = AssetManager.getInstance().getTexture("WingedMonster_" + i + ".png");
            wingedMonsterFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> wingedMonsterAnim = new Animation<>(0.1f, wingedMonsterFrames);
        animations.put("wingedMonsterAnim", wingedMonsterAnim);

        // Witcher Monster

        Array<TextureRegion> witcherMonsterFrames = new Array<>();
        for (int i = 0; i <= 5; i++) {
            Texture tex = AssetManager.getInstance().getTexture("T_WitchMonster_" + i + ".png");
            witcherMonsterFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> witcherMonsterAnim = new Animation<>(0.1f, witcherMonsterFrames);
        animations.put("witcherMonsterAnim", witcherMonsterAnim);

        // Tentacle

        Array<TextureRegion> TentacleMonsterFrames = new Array<>();
        for (int i = 0; i <= 3; i++) {
            Texture tex = AssetManager.getInstance().getTexture("BrainMonster_" + i + ".png");
            TentacleMonsterFrames.add(new TextureRegion(tex));
        }
        for (int i = 3; i >= 0; i--) {
            Texture tex = AssetManager.getInstance().getTexture("BrainMonster_" + i + ".png");
            TentacleMonsterFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> tentacleMonsterAnim = new Animation<>(0.3f, TentacleMonsterFrames);
        animations.put("tentacleMonsterAnim", tentacleMonsterAnim);

        // Crow

        Array<TextureRegion> crowFrames = new Array<>();
        for (int i = 0; i <= 1; i++) {
            Texture tex = AssetManager.getInstance().getTexture("T_Crow_" + i + ".png");
            crowFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> crowAnim = new Animation<>(0.1f, crowFrames);
        animations.put("CrowAnim", crowAnim);

        // Boomer

        Array<TextureRegion> boomerFrames = new Array<>();
        for (int i = 0; i <= 2; i++) {
            Texture tex = AssetManager.getInstance().getTexture("Boomer_" + i + ".png");
            boomerFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> BoomerAnim = new Animation<>(0.1f, boomerFrames);
        animations.put("BoomerAnim", BoomerAnim);

        // Heart

        Array<TextureRegion> heartsFrames = new Array<>();
        for (int i = 0; i <= 2; i++) {
            Texture tex = AssetManager.getInstance().getTexture("HeartAnimation_" + i + ".png");
            heartsFrames.add(new TextureRegion(tex));
        }
        for (int i = 2; i >= 0; i--) {
            Texture tex = AssetManager.getInstance().getTexture("HeartAnimation_" + i + ".png");
            heartsFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> HeartAnim = new Animation<>(0.1f, heartsFrames);
        animations.put("HeartAnim", HeartAnim);

        // Soul Heart

        Array<TextureRegion> soulHeartsFrames = new Array<>();
        for (int i = 0; i <= 2; i++) {
            Texture tex = AssetManager.getInstance().getTexture("T_SoulHeartAnimation_" + i + ".png");
            soulHeartsFrames.add(new TextureRegion(tex));
        }
        for (int i = 2; i >= 0; i--) {
            Texture tex = AssetManager.getInstance().getTexture("T_SoulHeartAnimation_" + i + ".png");
            soulHeartsFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> SoulHeartAnim = new Animation<>(0.1f, soulHeartsFrames);
        animations.put("SoulHeartAnim", SoulHeartAnim);
    }

    public void loadAvatarAnimations(String avatarName) {
        avatarName = avatarName.substring(0, avatarName.lastIndexOf('.'));

        // walk

        Array<TextureRegion> walkFrames = new Array<>();
        for (int i = 0; i <= 7; i++) {
            Texture tex = AssetManager.getInstance().getTexture(avatarName + "_Walk_" + i + ".png");
            walkFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> walkAnim = new Animation<>(0.1f, walkFrames, Animation.PlayMode.LOOP);
        animations.put("walk", walkAnim);

        // run

        Array<TextureRegion> runFrames = new Array<>();
        for (int i = 0; i <= 3; i++) {
            Texture tex = AssetManager.getInstance().getTexture(avatarName + "_Run_" + i + ".png");
            runFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> runAnim = new Animation<>(0.1f, runFrames, Animation.PlayMode.LOOP);
        animations.put("run", runAnim);

        // Idle

        Array<TextureRegion> idleFrames = new Array<>();
        for (int i = 0; i <= 5; i++) {
            Texture tex = AssetManager.getInstance().getTexture(avatarName + "_Idle_" + i + ".png");
            idleFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> idleAnim = new Animation<>(0.1f, idleFrames, Animation.PlayMode.LOOP);
        animations.put("idle", idleAnim);
    }

    public void loadWeaponsAnimations(Gun gun) {
        int max = switch (gun) {
            case SMG -> 4;
            case Shotgun -> 2;
            default -> 3;
        };
        Array<TextureRegion> reloadFrames = new Array<>();
        for (int i = 0; i <= max; i++) {
            Texture tex = AssetManager.getInstance().getTexture(gun.name() + "_Reload_" + i + ".png");
            reloadFrames.add(new TextureRegion(tex));
        }
        Animation<TextureRegion> reloadAnim = new Animation<>(0.1f, reloadFrames, Animation.PlayMode.LOOP);
        animations.put(gun.name() + "Reload", reloadAnim);
    }

    public Animation<TextureRegion> get(String name) {
        return animations.get(name);
    }
}
