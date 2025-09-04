package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.LoginMenuController;
import com.tilldawn.Main;
import com.tilldawn.Models.AnimationManager;
import com.tilldawn.Models.AssetManager;

public class LoginMenu implements AppView {

    // fields
    private final Stage stage;
    private final Texture background;
    private final Animation<TextureRegion> shadow = AnimationManager.getInstance().get("swordShadow");

    private final Button exitButton;
    private final TextButton loginButton;
    private final TextButton forgetButton;
    private final TextButton registerButton;
    private final TextField usernameField;
    private final TextField passwordField;

    public LoginMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.left().top();
        background = new Texture(Gdx.files.internal("LoginMenuAssets/LoginMenuBackground.png"));
        Skin skin = AssetManager.getInstance().getSkin();

        exitButton = new Button(skin, "exit2");
        loginButton = new TextButton("Login", skin, "chvy_PINK_24_ui");
        forgetButton = new TextButton("Forget Password?", skin, "chvy_PINK_16");
        registerButton = new TextButton("Register", skin);
        Label welcomeLabel = new Label("WELCOME!", skin, "chvyExprs_RED_85");
        usernameField = new TextField("", skin, "default2");
        usernameField.setMessageText("Username");
        usernameField.setAlignment(Align.center);
        passwordField = new TextField("", skin, "default2");
        passwordField.setMessageText("Password");
        passwordField.setAlignment(Align.center);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        Label usernameLabel = new Label("Enter your Username :", skin);
        Label passwordLabel = new Label("Enter your password :", skin);

        Table menuTable = new Table();

        menuTable.add(usernameLabel).pad(25).padTop(285).row();
        menuTable.add(usernameField).width(600).height(50).pad(10).padBottom(60).row();
        menuTable.add(passwordLabel).pad(25).row();
        menuTable.add(passwordField).width(600).height(50).pad(10).row();
        menuTable.add(forgetButton).pad(10).row();
        menuTable.add(loginButton).pad(10).padTop(80).width(300).row();
        menuTable.add(registerButton).pad(10).row();

        table.add(menuTable).padLeft(220);
        stage.addActor(table);
        exitButton.setPosition(Gdx.graphics.getWidth() - 65, Gdx.graphics.getHeight() - 65);
        stage.addActor(exitButton);
        new LoginMenuController(this);
    }

    private float blinkingStateTime = 0f;
    private boolean blinking = false;
    private float blinkTimer = 0f;

    @Override
    public void render(float delta) {

        TextureRegion currentFrame;

        if (blinking) {
            blinkingStateTime += delta;
            currentFrame = shadow.getKeyFrame(blinkingStateTime, false);

            if (shadow.isAnimationFinished(blinkingStateTime)) {
                blinking = false;
                blinkTimer = 0;
                blinkingStateTime = 0;
                currentFrame = shadow.getKeyFrame(0);
            }
        } else {
            blinkTimer += delta;

            currentFrame = shadow.getKeyFrame(0);

            if (blinkTimer >= 1.4f) {
                blinking = true;
                blinkingStateTime = 0;
            }
        }

        ScreenUtils.clear(Color.WHITE);
        Main.getGame().getBatch().begin();
        Main.getGame().getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getGame().getBatch().draw(currentFrame, Gdx.graphics.getWidth() - 300, 448);
        Main.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {

    }

    //

    public Button getExitButton() {
        return exitButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getForgetButton() {
        return forgetButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getUsernameField() {
        return usernameField;
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
