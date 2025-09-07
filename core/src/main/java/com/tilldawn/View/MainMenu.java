package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.MainMenuController;
import com.tilldawn.TillDawn;
import com.tilldawn.Models.AnimationManager;
import com.tilldawn.Models.AssetManager;

public class MainMenu implements AppView {
    // fields :
    private final Texture background;
    private final Stage stage;
    private final Animation<TextureRegion> blickRight1 = AnimationManager.getInstance().get("blinkRight");
    private final Animation<TextureRegion> blickRight2 = AnimationManager.getInstance().get("blinkRight");
    private final Animation<TextureRegion> blickLeft1 = AnimationManager.getInstance().get("blinkLeft");
    private final Animation<TextureRegion> blickLeft2 = AnimationManager.getInstance().get("blinkLeft");

    private final TextButton preGameButton;
    private final TextButton settingButton;
    private final TextButton profileButton;
    private final TextButton scoreBoardButton;
    private final TextButton talentButton;
    private final TextButton logoutButton;
    private final TextButton exitButton;

    // init :
    public MainMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        background = new Texture(Gdx.files.internal("IntroMenuBackGround.png"));
        Skin skin = AssetManager.getInstance().getSkin();

        preGameButton = new TextButton("Pre Game", skin, "chvy_PINK_54");
        settingButton = new TextButton("Setting", skin, "chvy_PINK_36");
        profileButton = new TextButton("Profile", skin, "chvy_PINK_36");
        scoreBoardButton = new TextButton("Score Board", skin, "chvy_PINK_36");
        talentButton = new TextButton("Talent", skin, "chvy_PINK_36");
        logoutButton = new TextButton("Logout", skin, "chvy_PINK_36");
        exitButton = new TextButton("Exit", skin);


        int pad = 20;
        table.add(preGameButton).pad(pad).padTop(200).row();
        table.add(settingButton).pad(pad).row();
        table.add(profileButton).pad(pad).row();
        table.add(scoreBoardButton).pad(pad).row();
        table.add(talentButton).pad(pad).row();
        table.add(logoutButton).pad(pad).row();
        table.add(exitButton).pad(pad);

        stage.addActor(table);
        new MainMenuController(this);
    }

    private float blinkingStateTime = 0f;
    private boolean blinking = false;
    private float blinkTimer = 0f;

    @Override
    public void render(float v) {
        TextureRegion currentFrame1;
        TextureRegion currentFrame2;
        TextureRegion currentFrame3;
        TextureRegion currentFrame4;

        if (blinking) {
            blinkingStateTime += v;
            currentFrame1 = blickRight1.getKeyFrame(blinkingStateTime, false);
            currentFrame2 = blickRight2.getKeyFrame(blinkingStateTime, false);
            currentFrame3 = blickLeft1.getKeyFrame(blinkingStateTime, false);
            currentFrame4 = blickLeft2.getKeyFrame(blinkingStateTime, false);

            if (blickRight1.isAnimationFinished(blinkingStateTime)) {
                blinking = false;
                blinkTimer = 0;
                blinkingStateTime = 0;
                currentFrame1 = blickRight1.getKeyFrame(0);
                currentFrame2 = blickRight2.getKeyFrame(0);
                currentFrame3 = blickLeft1.getKeyFrame(0);
                currentFrame4 = blickLeft2.getKeyFrame(0);
            }
        } else {
            blinkTimer += v;

            currentFrame1 = blickRight1.getKeyFrame(0);
            currentFrame2 = blickRight2.getKeyFrame(0);
            currentFrame3 = blickLeft1.getKeyFrame(0);
            currentFrame4 = blickLeft2.getKeyFrame(0);

            if (blinkTimer >= 4f) {
                blinking = true;
                blinkingStateTime = 0;
            }
        }


        ScreenUtils.clear(Color.WHITE);
        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        TillDawn.getGame().getBatch().draw(currentFrame1, Gdx.graphics.getWidth() / 2f + 400, Gdx.graphics.getHeight() / 2f - 100 + 100);
        TillDawn.getGame().getBatch().draw(currentFrame2, Gdx.graphics.getWidth() / 2f + 350, Gdx.graphics.getHeight() / 2f - 100 - 100);
        TillDawn.getGame().getBatch().draw(currentFrame3, Gdx.graphics.getWidth() / 2f - 500, Gdx.graphics.getHeight() / 2f - 100 + 100);
        TillDawn.getGame().getBatch().draw(currentFrame4, Gdx.graphics.getWidth() / 2f - 450, Gdx.graphics.getHeight() / 2f - 100 - 100);
        TillDawn.getGame().getBatch().end();

        stage.act(v);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    // getter :

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getSettingButton() {
        return settingButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getScoreBoardButton() {
        return scoreBoardButton;
    }

    public TextButton getTalentButton() {
        return talentButton;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }
}
