package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.*;
import com.tilldawn.TillDawn;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

import java.util.Objects;
import java.util.UUID;

public class LoginMenuController {

    private final LoginMenu menu;

    public LoginMenuController(LoginMenu loginMenu) {
        this.menu = loginMenu;
        initialize();
    }

    private void initialize() {
        // Exit
        menu.getExitButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Login
        menu.getLoginButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                Result result = check(menu.getUsernameField().getText(), menu.getPasswordField().getText());
                if (result.isSuccessful()) {
                    if (menu.getStayLoggedInCheckBox().isChecked()) {
                        System.out.println("stayLoggedInCheckBox is checked");
                        String token = generateToken();
                        Preferences prefs = Gdx.app.getPreferences("StayLoggedIn");
                        prefs.putString("rememberToken", token);
                        prefs.flush();
                        UserDAO.saveRememberToken(menu.getUsernameField().getText(), token);
                    }
                    App.setCurrentUser(UserDAO.getUserByUsername(menu.getUsernameField().getText()));
                    TillDawn.getGame().setScreen(new MainMenu());
                    menu.dispose();
                } else menu.showWarning(result.message());
            }
        });

        // Forget Pass
        menu.getForgetButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                if (!menu.getUsernameField().getText().isEmpty() && UserDAO.userExists(menu.getUsernameField().getText()).isSuccessful()) {
                    menu.getSecurityQuestionDialog().setForgetPasswordMode(UserDAO.getSecurityQuestionID(menu.getUsernameField().getText()));
                    menu.getSecurityQuestionDialog().setListener((selectedIndex, answer) -> {
                        if (Objects.requireNonNull(UserDAO.getAnswer(menu.getUsernameField().getText()))
                            .equals(SHA_256.hashPassword(answer, UserDAO.getSalt(menu.getUsernameField().getText())))) {
                            menu.getSecurityQuestionDialog().hide();
                            menu.getSetPasswordDialog().show(menu.getStage());
                        } else menu.showWarning("Wrong Answer!");
                    });
                    menu.getSecurityQuestionDialog().show(menu.getStage());
                } else
                    menu.showWarning("Please Enter Username!");
            }
        });

        // Register
        menu.getRegisterButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                TillDawn.getGame().setScreen(new RegisterMenu());
                menu.dispose();
            }
        });
    }

    private Result check(String username, String password) {
        username = username.trim();
        password = password.trim();


        if (UserDAO.login(username, password).isSuccessful())
            return new Result(true, "User logged in");
        else
            return new Result(false, "Incorrect username or password");
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
