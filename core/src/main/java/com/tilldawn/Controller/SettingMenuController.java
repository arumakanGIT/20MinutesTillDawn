package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.TillDawn;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.SettingMenu;

public class SettingMenuController {
    private SettingMenu menu;

    public SettingMenuController(SettingMenu menu) {
        this.menu = menu;
        initialize();
    }

    private void initialize() {
        // music slider

        menu.getMusicSlider().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameAudioManager.getInstance().getCurrentMusic().setVolume(menu.getMusicSlider().getValue() / 100);
            }
        });

        // back

        menu.getCancelButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back();
                TillDawn.getGame().setScreen(new MainMenu());
                menu.dispose();
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
