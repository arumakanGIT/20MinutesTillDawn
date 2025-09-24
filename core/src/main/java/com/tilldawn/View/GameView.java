package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.GameController;
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
        stage = new Stage();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
        this.game = game;

        TillDawn.setCursor("CursorSprite.png");
        this.controller = new GameController();
        controller.setView(this);

        Random random = new Random();
        int mapIndex = random.nextInt(4);
        background = AssetManager.getInstance().getTexture("map" + mapIndex + ".png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        darkMaskEffect = AssetManager.getInstance().getTexture("darkMaskEffect.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

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
        if (game.isGamePaused() && !controller.getPlayerController().isReloading()) {
            float gunX = controller.getPlayerController().getGunX();
            float gunY = controller.getPlayerController().getGunY() + 20;

            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            getCamera().unproject(mousePos);

            float dx = mousePos.x + 16 - gunX;
            float dy = mousePos.y - 20 - gunY;

            float angle = (float) Math.toDegrees(Math.atan2(dy, dx));

            controller.getBulletController().shootBulletHandle(gunX, gunY, angle);
        }
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
