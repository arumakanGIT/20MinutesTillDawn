package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.*;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;
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
                App.getCurrentUser().setAvatar(menu.getAvatar());
                menu.update();
            }
        });

        // avatar right

        menu.getAvatarRight().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setIndex((menu.getIndex() + menu.getAvatars().size() + 1) % menu.getAvatars().size());
                App.getCurrentUser().setAvatar(menu.getAvatar());
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
                App.getCurrentUser().setWeapon(menu.getWeapons().get(menu.getWeaponIndex()));
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
                App.getCurrentUser().setWeapon(menu.getWeapons().get(menu.getWeaponIndex()));
            }
        });

        // num1

        menu.getNum1Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[0] = (menu.getTime()[0] + 7) % 6;
                menu.update();
            }
        });

        menu.getNum1Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[0] = (menu.getTime()[0] + 5) % 6;
                menu.update();
            }
        });

        // num2

        menu.getNum2Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[1] = (menu.getTime()[1] + 11) % 10;
                menu.update();
            }
        });

        menu.getNum2Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[1] = (menu.getTime()[1] + 9) % 10;
                menu.update();
            }
        });

        // num3

        menu.getNum3Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[2] = ((menu.getTime()[2] + 7) % 6);
                menu.update();
            }
        });

        menu.getNum3Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[2] = ((menu.getTime()[2] + 5) % 6);
                menu.update();
            }
        });

        // num4

        menu.getNum4Up().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[3] = ((menu.getTime()[3] + 11) % 10);
                menu.update();
            }
        });

        menu.getNum4Down().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.getTime()[3] = ((menu.getTime()[3] + 9) % 10);
                menu.update();
            }
        });

        // back

        menu.getBackButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                TillDawn.getGame().setScreen(new MainMenu());
                UserDAO.updateUser((menu.getTime()[0]) * 10 + menu.getTime()[1],
                    (menu.getTime()[2]) * 10 + menu.getTime()[3]);
                menu.dispose();
            }
        });

        // start

        menu.getStartButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                UserDAO.updateUser((menu.getTime()[0]) * 10 + menu.getTime()[1],
                    (menu.getTime()[2]) * 10 + menu.getTime()[3]);
                App.getCurrentUser().setAvatar(menu.getAvatar());
                App.getCurrentUser().setWeapon(menu.getGun());
                Game game = new Game(App.getCurrentUser());
                AnimationManager.getInstance().loadAvatarAnimations(menu.getAvatar());
                AnimationManager.getInstance().loadWeaponsAnimations(menu.getGun());
                GameView view = new GameView(game);
                TillDawn.setGameView(view);
                game.setTimer(new Timer(
                    (menu.getTime()[0]) * 10 + menu.getTime()[1],
                    (menu.getTime()[2]) * 10 + menu.getTime()[3]));

                TillDawn.getGame().setScreen(view);
                menu.dispose();
            }
        });
    }
}
