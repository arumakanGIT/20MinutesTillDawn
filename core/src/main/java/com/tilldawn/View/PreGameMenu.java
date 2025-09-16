package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Controller.PreGameMenuController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.TillDawn;

public class PreGameMenu implements AppView {

    private final Texture background;
    private final Stage stage;
    private final TextButton backButton;
    private final Button startButton;
    private final Button avatarRight;
    private final Button avatarLeft;
    private final Button weaponRight;
    private final Button weaponLeft;
    private Texture avatar;
    private Texture weapon;
    private Texture num1;
    private Texture num2;
    private Texture num3;
    private Texture num4;
    private final Button num1Up;
    private final Button num2Up;
    private final Button num3Up;
    private final Button num4Up;
    private final Button num1Down;
    private final Button num2Down;
    private final Button num3Down;
    private final Button num4Down;

    public PreGameMenu() {
        Viewport viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        background = new Texture(Gdx.files.internal("PreGameMenu/pregameMenu.png"));
        Skin skin = AssetManager.getInstance().getSkin();

        backButton = new TextButton("Back", skin);
        startButton = new Button(skin, "start");
        avatarLeft = new Button(skin, "left");
        weaponLeft = new Button(skin, "left");
        avatarRight = new Button(skin, "right");
        weaponRight = new Button(skin, "right");
        num1Up = new Button(skin, "hidden");
        num2Up = new Button(skin, "hidden");
        num3Up = new Button(skin, "hidden");
        num4Up = new Button(skin, "hidden");
        num1Down = new Button(skin, "hidden");
        num2Down = new Button(skin, "hidden");
        num3Down = new Button(skin, "hidden");
        num4Down = new Button(skin, "hidden");



        stage.addActor(table);
        new PreGameMenuController(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(36f / 255f, 32f / 255f, 47f / 255f, 1f));
        TillDawn.getGame().getBatch().setProjectionMatrix(stage.getCamera().combined);
        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        num1.dispose();
        num2.dispose();
        num3.dispose();
        num4.dispose();
        avatar.dispose();
        weapon.dispose();
    }

    //

    public Button getStartButton() {
        return startButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Button getAvatarRight() {
        return avatarRight;
    }

    public Button getAvatarLeft() {
        return avatarLeft;
    }

    public Button getWeaponRight() {
        return weaponRight;
    }

    public Button getWeaponLeft() {
        return weaponLeft;
    }

    public Button getNum1Up() {
        return num1Up;
    }

    public Button getNum2Up() {
        return num2Up;
    }

    public Button getNum3Up() {
        return num3Up;
    }

    public Button getNum4Up() {
        return num4Up;
    }

    public Button getNum1Down() {
        return num1Down;
    }

    public Button getNum2Down() {
        return num2Down;
    }

    public Button getNum3Down() {
        return num3Down;
    }

    public Button getNum4Down() {
        return num4Down;
    }

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
