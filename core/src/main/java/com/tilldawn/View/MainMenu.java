package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Main;

public class MainMenu implements Screen, AppView {
    private final Stage stage;
    private final Main game = Main.getGame();

    private final Texture background;

    public MainMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Skin skin1 = AssetManager.getInstance().getSkin1();
        TextButton play = new TextButton("Play", skin1, "withoutBackGround");
        play.setPosition((float) Gdx.graphics.getWidth() - play.getWidth() - play.getWidth() / 2, (float) Gdx.graphics.getHeight() - play.getHeight() * 3);
        stage.addActor(play);

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
