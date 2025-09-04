package com.tilldawn.Models;

import com.badlogic.gdx.Screen;
import com.tilldawn.Main;

public class App {
    public static Menu currentMenu;

    public static Screen getCurrentMenu() {
        return currentMenu.getScreen();
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static void setScreen(Menu currentMenu) {
        setCurrentMenu(currentMenu);
        Main.getGame().setScreen(currentMenu.getScreen());
    }
}
