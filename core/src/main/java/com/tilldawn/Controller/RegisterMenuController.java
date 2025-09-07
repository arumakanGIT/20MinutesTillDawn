package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.Models.Result;
import com.tilldawn.Models.SFX;
import com.tilldawn.Models.UserDAO;
import com.tilldawn.TillDawn;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

import java.util.regex.Pattern;

public class RegisterMenuController {
    private final RegisterMenu menu;

    public RegisterMenuController(RegisterMenu menu) {
        this.menu = menu;
        initialize();
    }

    private void initialize() {
        // login button
        menu.getLoginButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                TillDawn.getGame().setScreen(new LoginMenu());
            }
        });

        // exit button
        menu.getExitButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                Gdx.app.exit();
            }
        });

        // random password
        menu.getRandomPasswordButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
            }
        });

        // username field
        menu.getUsernameField().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menu.getUsernameCheckButton().setVisible(!menu.getUsernameField().getText().isEmpty());
                menu.getUsernameCheckButton().setChecked(!Pattern.compile("^[a-zA-Z0-9_!.*&^%$#@]{4,32}$").matcher(menu.getUsernameField().getText()).find());
            }

        });

        menu.getUsernameField().addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused)
                    if (UserDAO.userExists(menu.getUsernameField().getText())) {
                        menu.showWarning("This username is already taken!");
                        menu.getUsernameCheckButton().setChecked(true);
                    }
            }
        });

        // password field
        menu.getPasswordField().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menu.getPasswordCheckButton().setVisible(!menu.getPasswordField().getText().isEmpty());
                menu.getPasswordCheckButton().setChecked(!Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$").matcher(menu.getPasswordField().getText()).find());
            }
        });

        // confirm password field
        menu.getConfirmPasswordField().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menu.getConfirmPasswordCheckButton().setVisible(!menu.getConfirmPasswordField().getText().isEmpty());
                menu.getConfirmPasswordCheckButton().setChecked(!menu.getPasswordField().getText().equals(menu.getConfirmPasswordField().getText()));
            }
        });

        menu.getRegisterButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = check(menu.getUsernameField().getText(), menu.getPasswordField().getText());
                if (result.isSuccessful())
                    TillDawn.getGame().setScreen(new MainMenu());
                else
                    menu.showWarning(result.message());
            }
        });
    }


    private Result check(String username, String password) {
        username = username.trim();
        if (UserDAO.userExists(username))
            return new Result(false, "Username already exists");
        if (username.length() < 4 || username.length() > 32)
            return new Result(false, "username should be between 4 and 32 characters");
        if (!Pattern.compile("^[a-zA-Z0-9_!.*&^%$#@]{4,32}$").matcher(username).find())
            return new Result(false, "Invalid username");
        password = password.trim();
        if (password.length() < 8)
            return new Result(false, "password should be at least 8 characters");
        if (!Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$").matcher(password).find())
            return new Result(false, "Invalid password");

        if (UserDAO.register(username, password))
            return new Result(true, "User registered successfully");
        else
            return new Result(false, "registration failed");
    }
}
