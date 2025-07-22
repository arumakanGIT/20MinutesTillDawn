package com.tilldawn.Models;

import com.badlogic.gdx.Screen;
import com.tilldawn.View.*;

public enum Menu {
    MainMenu(new MainMenu()),
    LoginMenu(new LoginMenu()),
    RegisterMenu(new RegisterMenu());

    private final Screen screen;

    Menu(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
