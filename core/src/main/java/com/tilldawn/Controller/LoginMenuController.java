package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.tilldawn.Models.Result;
import com.tilldawn.View.LoginMenu;

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
            public void clicked(InputEvent event, float x, float y) {
                String username = menu.getUsername().getText();
                String password = menu.getPassword().getText();
                String confirmPassword = menu.getPasswordConfirm().getText();

                if (!checkUsername(username).isSuccessful())
                    ;
//                if ()
            }
        });

        // Username ExtField

        menu.getUsername().addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) {
                    String text = menu.getUsername().getText();
                    if (text.length() > 20 || text.length() < 3) {

                    }
                }
            }
        });

        // Password TextField

        menu.getPassword().addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) {
                    String text = menu.getPassword().getText();

                }
            }
        });

        // Password Confirm TextField

        menu.getPasswordConfirm().addListener(new FocusListener() {
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) {
                    String text = menu.getPasswordConfirm().getText();

                }
            }
        });

        // Register

        menu.getRegisterButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

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
