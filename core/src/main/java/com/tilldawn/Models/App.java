package com.tilldawn.Models;

import com.badlogic.gdx.Screen;

public class App {
    public static Menu currentMenu = Menu.LoginMenu;

    public static Screen getCurrentMenu() {
        return currentMenu.getScreen();
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }
}
