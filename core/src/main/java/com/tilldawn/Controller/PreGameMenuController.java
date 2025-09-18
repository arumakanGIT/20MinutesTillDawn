package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.Models.App;
import com.tilldawn.Models.Game;
import com.tilldawn.TillDawn;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.PreGameMenu;

import java.util.Arrays;

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
                menu.update();
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

        // num1

        menu.getNum1Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[0] = (char) ((Character.getNumericValue(menu.getTime()[0]) + 7) % 6 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        menu.getNum1Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[0] = (char) ((Character.getNumericValue(menu.getTime()[0]) + 5) % 6 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        // num2

        menu.getNum2Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[1] = (char) ((Character.getNumericValue(menu.getTime()[1]) + 11) % 10 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        menu.getNum2Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[1] = (char) ((Character.getNumericValue(menu.getTime()[1]) + 9) % 10 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        // num3

        menu.getNum3Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[2] = (char) ((Character.getNumericValue(menu.getTime()[2]) + 7) % 6 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        menu.getNum3Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[2] = (char) ((Character.getNumericValue(menu.getTime()[2]) + 5) % 6 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        // num4

        menu.getNum4Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[3] = (char) ((Character.getNumericValue(menu.getTime()[3]) + 11) % 10 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        menu.getNum4Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[3] = (char) ((Character.getNumericValue(menu.getTime()[3]) + 9) % 10 + '0');
                System.out.println(Arrays.toString(menu.getTime()));
                menu.update();
            }
        });

        // start

        menu.getStartButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Game game = new Game((menu.getTime()[0] - '0') * 10 + menu.getTime()[1] - '0',
                    (menu.getTime()[2] - '0') * 10 + menu.getTime()[3] - '0'
                    , App.getCurrentUser());

            }
        });
    }
}
