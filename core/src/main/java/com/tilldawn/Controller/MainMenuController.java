package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.*;
import com.tilldawn.Models.Enums.SFX;
import com.tilldawn.TillDawn;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.PreGameMenu;

public class MainMenuController {
    private final MainMenu menu;

    public MainMenuController(MainMenu mainMenu) {
        this.menu = mainMenu;
        initialize();
    }

    private void initialize() {
        // Pre game
        menu.getPreGameButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                TillDawn.getGame().setScreen(new PreGameMenu());
                menu.dispose();
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

        // logout
        menu.getLogoutButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UserDAO.saveRememberToken(App.getCurrentUser().getUsername(), null);
                Preferences prefs = Gdx.app.getPreferences("StayLoggedIn");
                prefs.remove("rememberToken");
                prefs.flush();
                TillDawn.getGame().setScreen(new LoginMenu());
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

    public static class GameController {
    }
}
