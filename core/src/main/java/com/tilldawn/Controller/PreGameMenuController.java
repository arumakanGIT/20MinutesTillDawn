package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.TillDawn;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.PreGameMenu;

public class PreGameMenuController {
    private final PreGameMenu menu;

    public PreGameMenuController(PreGameMenu menu) {
        this.menu = menu;
        initialize();
    }

    private void initialize() {

        // avatar left

        menu.getAvatarLeft().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setIndex((menu.getIndex() + menu.getAvatars().size() - 1) % menu.getAvatars().size());
//                float oldWidth = menu.getAvatar().getPrefWidth();
//                float oldHeight = menu.getAvatar().getPrefHeight();
                menu.update();
//                menu.getAvatar().setSize(menu.getAvatar().getPrefWidth(), menu.getAvatar().getPrefHeight());
//                menu.getAvatar().setPosition(menu.getAvatar().getX() - ((menu.getAvatar().getPrefWidth() - oldWidth) / 2), menu.getAvatar().getY() - ((menu.getAvatar().getPrefHeight() - oldHeight) / 2));
            }
        });

        // avatar right

        menu.getAvatarRight().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setIndex((menu.getIndex() + menu.getAvatars().size() + 1) % menu.getAvatars().size());
                menu.update();
            }
        });

        // weapon left

        menu.getWeaponLeft().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setWeaponIndex((menu.getWeaponIndex() + menu.getWeapons().size() - 1) % menu.getWeapons().size());
                menu.update();
                menu.getWeapon().setSize(menu.getWeapon().getPrefWidth(), menu.getWeapon().getPrefHeight());
                menu.getWeapon().setPosition(PreGameMenu.weaponX - menu.getWeapon().getPrefWidth() / 2, PreGameMenu.weaponY - menu.getWeapon().getPrefHeight() / 2);
                menu.getWeaponText().setSize(menu.getWeaponText().getPrefWidth(), menu.getWeaponText().getPrefHeight());
            }
        });

        // weapon right

        menu.getWeaponRight().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setWeaponIndex((menu.getWeaponIndex() + menu.getWeapons().size() + 1) % menu.getWeapons().size());
                menu.update();
                menu.getWeapon().setSize(menu.getWeapon().getPrefWidth(), menu.getWeapon().getPrefHeight());
                menu.getWeapon().setPosition(PreGameMenu.weaponX - menu.getWeapon().getPrefWidth() / 2, PreGameMenu.weaponY - menu.getWeapon().getPrefHeight() / 2);
                menu.getWeaponText().setSize(menu.getWeaponText().getPrefWidth(), menu.getWeaponText().getPrefHeight());
            }
        });

        // back

        menu.getBackButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                TillDawn.getGame().setScreen(new MainMenu());
                menu.dispose();
            }
        });
    }
}
