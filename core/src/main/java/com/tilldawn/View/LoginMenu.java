package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.LoginMenuController;
import com.tilldawn.Models.SHA_256;
import com.tilldawn.Models.UserDAO;
import com.tilldawn.TillDawn;
import com.tilldawn.Models.AnimationManager;
import com.tilldawn.Models.AssetManager;

import java.util.Objects;
import java.util.regex.Pattern;

public class LoginMenu implements AppView {

    // fields
    private final Stage stage;
    private final Texture background;
    private final Animation<TextureRegion> shadow = AnimationManager.getInstance().get("swordShadow");
    private final SecurityQuestionDialog securityQuestionDialog;
    private final Button exitButton;
    private final TextButton loginButton;
    private final TextButton forgetButton;
    private final TextButton registerButton;
    private final TextField usernameField;
    private final TextField passwordField;
    private final Label warningLabel;
    private final Dialog setPasswordDialog;

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
        securityQuestionDialog = new SecurityQuestionDialog(skin);
        Table menuTable = new Table();
        warningLabel = new Label("", skin, "war_chvy_WHITE_24");
        warningLabel.setAlignment(Align.center);
        warningLabel.setVisible(false);
        setPasswordDialog = new Dialog("", skin);
        setPasswordDialog.setModal(true);
        setPasswordDialog.setMovable(false);
        setPasswordDialog.setResizable(false);
        setPasswordDialog.getContentTable().add(new Label("Enter new Password :", skin)).padTop(50).padLeft(40);
        TextField newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText("Password");
        newPasswordField.setAlignment(Align.center);
        newPasswordField.setPasswordCharacter('*');
        newPasswordField.setPasswordMode(true);
        setPasswordDialog.getContentTable().add(newPasswordField).height(60).width(600).padTop(50).padRight(40).row();
        setPasswordDialog.getContentTable().add(new Label("Confirm Password :", skin)).padTop(25).padLeft(40);
        TextField confirmPasswordField = new TextField("", skin);
        confirmPasswordField.setMessageText("Confirm Password");
        confirmPasswordField.setAlignment(Align.center);
        confirmPasswordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        setPasswordDialog.getContentTable().add(confirmPasswordField).height(60).width(600).padTop(25).padRight(40).row();
        Button okButton = new Button(skin, "ok");
        Button cancelButton = new Button(skin, "cancel");
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
                    showWarning("Passwords do not match!");
                    return;
                }
                if (!Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$").matcher(newPasswordField.getText()).find()) {
                    showWarning("Invalid Password!");
                    return;
                }
                if (Objects.equals(UserDAO.getPassword(usernameField.getText()), SHA_256.hashPassword(newPasswordField.getText(), UserDAO.getSalt(usernameField.getText())))) {
                    showWarning("Please Enter a new Password");
                    return;
                }

                UserDAO.changePassword(usernameField.getText(), newPasswordField.getText());
                newPasswordField.setText("");
                confirmPasswordField.setText("");
                setPasswordDialog.hide();
            }
        });
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newPasswordField.setText("");
                confirmPasswordField.setText("");
                setPasswordDialog.hide();
            }
        });
        setPasswordDialog.getContentTable().add(cancelButton).padTop(50);
        setPasswordDialog.getContentTable().add(okButton).padTop(50);

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
        warningLabel.setPosition(Gdx.graphics.getWidth() / 2f, 40);
        stage.addActor(exitButton);
        stage.addActor(warningLabel);
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
        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        TillDawn.getGame().getBatch().draw(currentFrame, Gdx.graphics.getWidth() - 300, 448);
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
        background.dispose();
        stage.dispose();
    }

    //
    public Dialog getSetPasswordDialog() {
        return setPasswordDialog;
    }

    public Stage getStage() {
        return stage;
    }

    public SecurityQuestionDialog getSecurityQuestionDialog() {
        return securityQuestionDialog;
    }

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

    public void showWarning(String message) {
        showWarningLabel(message, warningLabel);
    }

    static void showWarningLabel(String message, Label warningLabel) {
        warningLabel.clearActions();
        warningLabel.setText(message);

        warningLabel.setWidth(warningLabel.getPrefWidth());
        if (warningLabel.getWidth() < 600)
            warningLabel.setWidth(600);
        warningLabel.setHeight(warningLabel.getPrefHeight());

        warningLabel.setPosition(Gdx.graphics.getWidth() / 2f - warningLabel.getWidth() / 2, 40);

        warningLabel.getColor().a = 0f;
        warningLabel.setVisible(true);

        warningLabel.addAction(
            Actions.sequence(
                Actions.fadeIn(0.5f),
                Actions.delay(3),
                Actions.fadeOut(0.5f),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        warningLabel.setVisible(false);
                        return true;
                    }
                }
            )
        );
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
