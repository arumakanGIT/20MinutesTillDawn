package com.tilldawn.Controller;

import com.tilldawn.Models.App;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;

    public void updateGame() {
        if (view != null) {
            playerController.update();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(App.getCurrentUser(), view);
    }
}
