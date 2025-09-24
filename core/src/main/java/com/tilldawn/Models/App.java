package com.tilldawn.Models;

import com.tilldawn.Models.Enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu;
    private static User currentUser;

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}
