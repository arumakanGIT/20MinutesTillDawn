package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.tilldawn.Models.Result;
import com.tilldawn.Models.SFX;
import com.tilldawn.View.LoginMenu;

public class LoginMenuController {
    private final LoginMenu menu;

    public LoginMenuController(LoginMenu loginMenu) {
        this.menu = loginMenu;
        initialize();
    }

    private void initialize() {

        // Pre game

        menu.getPreGameButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
            }
        });

        // setting

        menu.getSettingButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
            }
        });

        // profile

        menu.getProfileButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
            }
        });

        // score board

        menu.getScoreBoardButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
            }
        });

        // talent

        menu.getTalentButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
            }
        });

        // Exit

        menu.getExitButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private Result checkUsername(String username) {
        username = username.trim();

        if (username.length() < 3 || username.length() > 20)
            return new Result(false, "username should be between 3 and 20 characters");

        return new Result(true, "");
    }

    private Result checkPassword(String password) {
        password = password.trim();
        if (password.length() < 8)
            return new Result(false, "password should be at least 8 characters");

        return new Result(true, "");
    }
}
