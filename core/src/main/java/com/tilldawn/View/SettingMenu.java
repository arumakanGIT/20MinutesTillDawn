package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Controller.SettingController;
import com.tilldawn.TillDawn;

public class SettingMenu implements AppView {

    private final Stage stage;

    public SettingMenu() {
        Viewport viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);



        stage.addActor(table);
        new SettingController(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        TillDawn.getGame().getBatch().setProjectionMatrix(stage.getCamera().combined);
        TillDawn.getGame().getBatch().begin();

        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
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

    @Override
    public void dispose() {

    }
}
