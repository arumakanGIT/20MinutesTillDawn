package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tilldawn.Controller.PreGameMenuController;
import com.tilldawn.TillDawn;

public class PreGameMenu implements AppView {

    private final Texture background;
    private final Stage stage;

    public PreGameMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("PreGameMenu/backGround.png"));
        new PreGameMenuController(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
    public void dispose() {

    }
}
