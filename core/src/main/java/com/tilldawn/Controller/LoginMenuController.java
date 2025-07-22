package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.tilldawn.View.LoginMenu;

public class LoginMenuController {
    private final LoginMenu menu;

    public LoginMenuController(LoginMenu loginMenu) {
        this.menu = loginMenu;
        initialize();
    }

    private void initialize() {
        menu.getLoginButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
            }
        });

        menu.getUsername().addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusEvent event, Actor actor,boolean focused) {
                if (!focused) {
                    System.out.println("keyboardFocusChanged");
                }
            }
        });
    }
}
