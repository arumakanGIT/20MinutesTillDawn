package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.LoginMenuController;
import com.tilldawn.Main;
import com.tilldawn.Models.AnimationManager;
import com.tilldawn.Models.AssetManager;

public class LoginMenu implements AppView {

    // fields
    private final Stage stage;
    private final Texture background;
    private final Animation<TextureRegion> shadow = AnimationManager.getInstance().get("swordShadow");

    public LoginMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        background = new Texture(Gdx.files.internal("LoginMenuAssets/LoginMenuBackground.png"));
        Skin skin = AssetManager.getInstance().getSkin();
        stage.addActor(table);
        new LoginMenuController(this);
    }

    private float blinkingStateTime = 0f;
    private boolean blinking = false;
    private float blinkTimer = 0f;


    @Override
    public void render(float delta) {

        TextureRegion currentFrame;

        if (blinking) {
            blinkingStateTime += delta;
            currentFrame = shadow.getKeyFrame(blinkingStateTime, false);

            if (shadow.isAnimationFinished(blinkingStateTime)) {
                blinking = false;
                blinkTimer = 0;
                blinkingStateTime = 0;
                currentFrame = shadow.getKeyFrame(0);
            }
        } else {
            blinkTimer += delta;

            currentFrame = shadow.getKeyFrame(0);

            if (blinkTimer >= 1.4f) {
                blinking = true;
                blinkingStateTime = 0;
            }
        }

        ScreenUtils.clear(Color.WHITE);
        Main.getGame().getBatch().begin();
        Main.getGame().getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getGame().getBatch().draw(currentFrame, Gdx.graphics.getWidth() - 410, 340);
        Main.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {

    }

    //


    //

    @Override
    public void show() {

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
}
