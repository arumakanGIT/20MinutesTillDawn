package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.LoginMenuController;
import com.tilldawn.Main;
import com.tilldawn.Models.AssetManager;

public class LoginMenu implements Screen, AppView {
    private final Main game = Main.getGame();

    // fields :
    private final Stage stage;
    private final TextField username;
    private final TextField password;
    private final TextField passwordConfirm;
    private final TextButton loginButton;
    private final TextButton registerButton;
    private final Texture background;
    private final Skin skin;
    private final TextButton exitButton;

    // init :
    public LoginMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = AssetManager.getInstance().getSkin1();
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        background = new Texture(Gdx.files.internal("background_loginmenu.jpg"));

        username = new TextField("", skin);
        password = new TextField("", skin);
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        passwordConfirm = new TextField("", skin);
        passwordConfirm.setPasswordCharacter('*');
        passwordConfirm.setPasswordMode(true);
        loginButton = new TextButton("  Login  ", skin, "default-16");
        registerButton = new TextButton("Register", skin, "without-16");
        Label usernameLabel = new Label("Username:", skin, "default-16");
        Label passwordLabel = new Label("Password:", skin, "default-16");
        Label passwordConfirmLabel = new Label("Confirm Password:", skin, "default-16");
        Label weolcomeLabel = new Label("Welcome", skin, "pink-45");
        exitButton = new TextButton("Exit", skin, "without-16");

        table.add(weolcomeLabel).padBottom(100);
        table.row();
        table.add(usernameLabel);
        table.row();
        table.add(username).width(300).padTop(10);
        table.row();
        table.add(passwordLabel).padTop(30);
        table.row();
        table.add(password).width(300).padTop(10);
        table.row();
        table.add(passwordConfirmLabel).padTop(30);
        table.row();
        table.add(passwordConfirm).width(300).padTop(10);
        table.row();
        table.add(loginButton).width(300).padTop(50);
        table.row();
        table.add(registerButton).padTop(30);
        table.row();
        table.add(exitButton).padTop(30);

        stage.addActor(table);
        new LoginMenuController(this);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    // getter :

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getPasswordConfirm() {
        return passwordConfirm;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public Stage getStage() {
        return stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    //

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
    public void show() {

    }
}
