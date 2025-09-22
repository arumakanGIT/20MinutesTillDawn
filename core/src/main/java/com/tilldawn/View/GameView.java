package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Controller.GameController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Game;
import com.tilldawn.TillDawn;

public class GameView implements InputProcessor, Screen {
    private final Game game;
    private Stage stage;
    private final GameController controller;
    private final Texture background;
    private final OrthographicCamera camera;
    private final Texture darkMaskEffect;
    private float timer = 0f;

    public GameView(Game game) {
        this.game = game;
        this.controller = new GameController();
        controller.setView(this);

        background = AssetManager.getInstance().getTexture("map.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        darkMaskEffect = AssetManager.getInstance().getTexture("darkMaskEffect.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        if (!game.isGamePaused()) {
            timer += delta;
            if (timer >= 1f) {
                game.decreaseSeconds();
                timer -= 1f;
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);

        camera.position.set(
            game.getPlayer().getPlayerX(),
            game.getPlayer().getPlayerY(),
            0
        );
        camera.update();

        TillDawn.getGame().getBatch().setProjectionMatrix(camera.combined);

        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background,
            camera.position.x - Gdx.graphics.getWidth() / 2f,
            camera.position.y - Gdx.graphics.getHeight() / 2f,
            (int) camera.position.x,
            (int) camera.position.y,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        controller.updateGame();
        TillDawn.getGame().getBatch().draw(
            darkMaskEffect,
            camera.position.x - Gdx.graphics.getWidth() / 2f,
            camera.position.y - Gdx.graphics.getHeight() / 2f,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    //

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
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
