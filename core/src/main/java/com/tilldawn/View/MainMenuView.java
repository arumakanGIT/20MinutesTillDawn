package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Main;

public class MainMenuView implements Screen {
    private boolean isFullscreen = false;
    Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
    private final Stage stage;
    private final Main game;
    private final BitmapFont font;
    private final Label label;
    private Button button;
    private ImageButton textButton;

    public MainMenuView(Main game) {

        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        label = new Label("press F11 to fullscreen!", labelStyle);
        label.setPosition((float) Gdx.graphics.getWidth() / 2 - (label.getWidth() / 2), label.getHeight());

        Texture buttonTexture = new Texture("button.png");
        TextureRegionDrawable up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = up;
        style.down = up;

        Button button = new Button(style);

        stage.addActor(button);
        stage.addActor(label);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            label.setVisible(false);
            if (isFullscreen) {
                Gdx.graphics.setWindowedMode((int) (currentMode.width * 0.8), (int) (currentMode.height * 0.8));
                isFullscreen = false;
            } else {
                Gdx.graphics.setFullscreenMode(currentMode);
                isFullscreen = true;
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(v);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
