package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.tilldawn.Models.*;
import com.tilldawn.Models.Enums.SFX;
import com.tilldawn.TillDawn;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Pattern;

public class RegisterMenuController {
    private final RegisterMenu menu;
    private final SecureRandom random = new SecureRandom();

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
                menu.dispose();
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
                Random random = new Random();
                String password = generatePassword(random.nextInt(8) + 8);
                menu.getPasswordField().setPasswordMode(false);
                menu.getPasswordField().setText(password);
                menu.getConfirmPasswordField().setText(password);
                menu.getPasswordCheckButton().setVisible(true);
                menu.getConfirmPasswordCheckButton().setVisible(true);
                menu.getPasswordCheckButton().setChecked(false);
                menu.getConfirmPasswordCheckButton().setChecked(false);
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
                    if (UserDAO.userExists(menu.getUsernameField().getText()).isSuccessful()) {
                        menu.showWarning("This username is already taken!");
                        menu.getUsernameCheckButton().setChecked(true);
                    }
            }
        });

        // password field
        menu.getPasswordField().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!menu.getPasswordField().isPasswordMode()) menu.getPasswordField().setPasswordMode(true);
                menu.getPasswordCheckButton().setVisible(!menu.getPasswordField().getText().isEmpty());
                menu.getPasswordCheckButton().setChecked(!Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$").matcher(menu.getPasswordField().getText()).find());
                menu.getConfirmPasswordCheckButton().setChecked(!menu.getPasswordField().getText().equals(menu.getConfirmPasswordField().getText()));
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
                Result result = check(menu.getUsernameField().getText(), menu.getPasswordField().getText(), menu.getConfirmPasswordField().getText());
                if (result.isSuccessful()) {
                    menu.getSecurityQuestionDialog().setListener((selectedIndex, answer) -> {
                        Result result1 = UserDAO.register(menu.getUsernameField().getText(), menu.getPasswordField().getText(), selectedIndex, answer);
                        if (result1.isSuccessful()) {
                            App.setCurrentUser(UserDAO.getUserByUsername(menu.getUsernameField().getText()));
                            TillDawn.getGame().setScreen(new MainMenu());
                            menu.dispose();
                        }
                        else
                            menu.showWarning(result1.message());
                    });
                    menu.getSecurityQuestionDialog().show(menu.getStage());
                } else
                    menu.showWarning(result.message());
            }
        });
    }

    private Result check(String username, String password, String confirmPassword) {
        username = username.trim();
        if (UserDAO.userExists(username).isSuccessful())
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
        if (!menu.getPasswordField().getText().equals(menu.getConfirmPasswordField().getText()))
            return new Result(false, "Passwords do not match");

        if (!UserDAO.userExists(username).isSuccessful())
            return new Result(true, "User registered successfully");
        else
            return new Result(false, "registration failed");
    }

    private String generatePassword(int length) {
        String SPECIALS = "@#$%&*()";
        String DIGITS = "0123456789";
        String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ALL = "abcdefghijklmnopqrstuvwxyz" + UPPERCASE + DIGITS + SPECIALS;

        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8");
        }

        StringBuilder password = new StringBuilder(length);

        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(SPECIALS.charAt(random.nextInt(SPECIALS.length())));

        for (int i = 3; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        return shuffleString(password.toString());
    }

    private String shuffleString(String input) {
        char[] a = input.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }
}
