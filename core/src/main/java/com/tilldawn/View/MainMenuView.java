package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.FontLoader;
import com.tilldawn.Main;

public class MainMenuView implements Screen {
    private boolean isFullscreen = false;
    Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
    private final Stage stage;
    private final Main game;

    private final Texture background;
    private final TextButton play;
    private final Skin skin1 = AssetManager.getInstance().getSkin1();

    public MainMenuView(Main game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        play = new TextButton("  Play  ", skin1, "square_checked");
        play.setPosition((float) Gdx.graphics.getWidth() / 2 - play.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - play.getHeight() / 2);
        stage.addActor(play);
        // background
        background = new Texture(Gdx.files.internal("background.png"));
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

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
