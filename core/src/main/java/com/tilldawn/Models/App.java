package com.tilldawn.Models;

import java.util.ArrayList;

public class App {
    public static Menu currentMenu;

    private final ArrayList<User> users = new ArrayList<>();

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }
}
