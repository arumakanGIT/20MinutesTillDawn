package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.Models.Result;
import com.tilldawn.Models.SFX;
import com.tilldawn.Models.UserDAO;
import com.tilldawn.TillDawn;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

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
                if (result.isSuccessful())
                    TillDawn.getGame().setScreen(new MainMenu());
                else ;
            }
        });

        // Forget Pass
        menu.getForgetButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);

            }
        });

        // Register
        menu.getRegisterButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                TillDawn.getGame().setScreen(new RegisterMenu());
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
}
