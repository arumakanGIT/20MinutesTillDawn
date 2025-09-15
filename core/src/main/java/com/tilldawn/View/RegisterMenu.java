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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Controller.RegisterMenuController;
import com.tilldawn.Models.AnimationManager;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.TillDawn;

public class RegisterMenu implements AppView {

    private final Stage stage;
    private final Texture background;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField confirmPasswordField;
    private final TextButton registerButton;
    private final Button exitButton;
    private final TextButton loginButton;
    private final TextButton randomPasswordButton;
    private final Button usernameCheckButton;
    private final Button passwordCheckButton;
    private final Button confirmPasswordCheckButton;
    private final SecurityQuestionDialog securityQuestionDialog;
    private final Label warningLabel;

    public RegisterMenu() {
        Viewport viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.top().left();
        background = new Texture(Gdx.files.internal("LoginMenuAssets/LoginMenuBackground.png"));
        Skin skin = AssetManager.getInstance().getSkin();

        Table menuTable = new Table();
        loginButton = new TextButton("Login", skin, "chvy_PINK_16");
        exitButton = new Button(skin, "exit2");
        usernameField = new TextField("", skin, "default3");
        usernameField.setAlignment(Align.center);
        usernameField.setMessageText("Username");
        passwordField = new TextField("", skin, "default3");
        passwordField.setAlignment(Align.center);
        passwordField.setMessageText("Password");
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        confirmPasswordField = new TextField("", skin, "default3");
        confirmPasswordField.setAlignment(Align.center);
        confirmPasswordField.setMessageText("Confirm Password");
        confirmPasswordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        registerButton = new TextButton("Register", skin, "chvy_PINK_16_ui");
        randomPasswordButton = new TextButton("Random Password", skin, "chvy_PINK_16");
        Label usernameLabel = new Label("Enter your Username:", skin, "chvyExprs_WHITE_16");
        Label passwordLabel = new Label("Enter your Password:", skin, "chvyExprs_WHITE_16");
        Label confirmPasswordLabel = new Label("Confirm Password:", skin, "chvyExprs_WHITE_16");
        usernameCheckButton = new Button(skin, "check");
        passwordCheckButton = new Button(skin, "check");
        confirmPasswordCheckButton = new Button(skin, "check");
        usernameCheckButton.setDisabled(true);
        passwordCheckButton.setDisabled(true);
        randomPasswordButton.setDisabled(true);
        usernameCheckButton.setVisible(false);
        passwordCheckButton.setVisible(false);
        confirmPasswordCheckButton.setVisible(false);
        warningLabel = new Label("", skin, "war_OpSa_WHITE_16");
        warningLabel.setAlignment(Align.center);
        warningLabel.setVisible(false);

        securityQuestionDialog = new SecurityQuestionDialog(skin);

        menuTable.add(usernameLabel).padLeft(-40).padBottom(10).row();
        Table usernameTable = new Table();
        usernameTable.add(usernameField).width(400).height(50);
        usernameTable.add(usernameCheckButton).width(usernameCheckButton.getWidth() / 2)
            .height(usernameCheckButton.getHeight() / 2).padBottom(5).padLeft(40).row();
        menuTable.add(usernameTable).padBottom(5).row();
        menuTable.add(passwordLabel).pad(10).padLeft(-40).row();
        Table passwordTable = new Table();
        passwordTable.add(passwordField).width(400).padBottom(10).height(50);
        passwordTable.add(passwordCheckButton).width(passwordCheckButton.getWidth() / 2)
            .height(passwordCheckButton.getHeight() / 2).padBottom(15).padLeft(40).row();
        menuTable.add(passwordTable).row();
        menuTable.add(randomPasswordButton).padBottom(10).padLeft(-55
        ).row();
        menuTable.add(confirmPasswordLabel).pad(10).padLeft(-40).row();
        Table confirmPasswordTable = new Table();
        confirmPasswordTable.add(confirmPasswordField).width(400).height(50);
        confirmPasswordTable.add(confirmPasswordCheckButton).width(confirmPasswordCheckButton.getWidth() / 2)
            .height(confirmPasswordCheckButton.getHeight() / 2).padBottom(5).padLeft(40).row();
        menuTable.add(confirmPasswordTable).padBottom(20).row();
        menuTable.add(registerButton).width(150).height(40).padLeft(-50).row();
        menuTable.add(loginButton).padLeft(-50).padTop(10).row();

        table.add(menuTable).padLeft(160).padTop(180);
        exitButton.setSize(exitButton.getWidth() / 2, exitButton.getHeight() / 2);
        exitButton.setPosition(stage.getViewport().getWorldWidth() - 40, stage.getViewport().getWorldHeight() - 40);
        warningLabel.setPosition(stage.getViewport().getWorldWidth() / 2f, 40);
        stage.addActor(exitButton);
        stage.addActor(table);
        stage.addActor(warningLabel);
        new RegisterMenuController(this);
    }

    private float blinkingStateTime = 0f;
    private boolean blinking = false;
    private final Animation<TextureRegion> shadow = AnimationManager.getInstance().get("swordShadow");
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

        float small = 0.7f;

        ScreenUtils.clear(new Color(39f / 255f, 33f / 255f, 42f / 255f, 1f));
        TillDawn.getGame().getBatch().setProjectionMatrix(stage.getCamera().combined);
        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background,
            0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
        TillDawn.getGame().getBatch().draw(currentFrame, stage.getViewport().getWorldWidth() - 203, 297,
            currentFrame.getRegionWidth() * small,
            currentFrame.getRegionHeight() * small);
        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override

    public void dispose() {
        stage.dispose();
        background.dispose();
    }

    //

    public SecurityQuestionDialog getSecurityQuestionDialog() {
        return securityQuestionDialog;
    }

    public Stage getStage() {
        return stage;
    }

    public Button getConfirmPasswordCheckButton() {
        return confirmPasswordCheckButton;
    }

    public Button getUsernameCheckButton() {
        return usernameCheckButton;
    }

    public Button getPasswordCheckButton() {
        return passwordCheckButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getRandomPasswordButton() {
        return randomPasswordButton;
    }

    public void showWarning(String message) {
        LoginMenu.showWarningLabel(message, warningLabel);
    }

    //

    @Override
    public void show() {

    }

    @Override
    public void resize(int i, int i1) {

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
