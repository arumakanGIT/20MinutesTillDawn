package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.Models.Result;
import com.tilldawn.Models.SFX;
import com.tilldawn.View.MainMenu;

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
}
