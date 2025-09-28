package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.GameControllers.GameController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Game;
import com.tilldawn.TillDawn;

import java.util.Random;

public class GameView implements InputProcessor, Screen {
    private final Game game;
    private final Stage stage;
    private final GameController controller;
    private final Texture background;
    private final OrthographicCamera camera;
    private final Texture darkMaskEffect;

    public GameView(Game game) {

        // initialize stage
        stage = new Stage();

        // multiplexer
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);

        //inputs
        Gdx.input.setInputProcessor(multiplexer);

        // set game
        this.game = game;

        // set cursor
        TillDawn.setCursor("CursorSprite.png");

        // game controller
        this.controller = new GameController(stage);
        controller.setView(this);

        // game background and effects
        Random random = new Random();
        int mapIndex = random.nextInt(4);
        background = AssetManager.getInstance().getTexture("map" + mapIndex + ".png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        darkMaskEffect = AssetManager.getInstance().getTexture("darkMaskEffect.png");

        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        controller.getEnemyController().setTimer();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (game.isGamePaused()) {
            camera.position.set(
                game.getPlayer().getRect().getX(),
                game.getPlayer().getRect().getY(),
                0
            );
            camera.update();
        }

        if (game.isGameFinished()) {
            TillDawn.getGame().setScreen(new MainMenu());
            dispose();
        }

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (game.isGamePaused() && !controller.getPlayerController().isReloading())
            controller.getBulletController().shootBulletHandle();
        return false;
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

    //

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Stage getStage() {
        return stage;
    }

    public Game getGame() {
        return game;
    }

    public GameController getController() {
        return controller;
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
