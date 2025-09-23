package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.View.SettingMenu;

public class SettingMenuController {
    private SettingMenu menu;

    public SettingMenuController(SettingMenu menu) {
        this.menu = menu;
        initialize();
    }

    private void initialize() {
        menu.getMusicSlider().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameAudioManager.getInstance().getCurrentMusic().setVolume(menu.getMusicSlider().getValue() / 100);
            }
        });
    }

    private void load() {

    }

    private void save() {

    }

    private void back() {

    }
}
