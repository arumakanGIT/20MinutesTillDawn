package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.PauseMenu;

public class GameController {
    private final ProgressBar xpBar;
    private GameView view;
    private PlayerController playerController;
    private BulletController bulletController;

    public GameController(Stage stage) {
        xpBar = new ProgressBar(0, 100, 1, false, AssetManager.getInstance().getSkin());
        xpBar.setSize(stage.getWidth() - 70, 100);
        xpBar.setPosition((stage.getWidth() - xpBar.getWidth()) / 2, stage.getHeight() - xpBar.getHeight());
        stage.addActor(xpBar);
    }

    public void updateGame() {
        if (view != null) {
            inputHandler();
            playerController.update();
            bulletController.update();
            if (view.getGame().isGamePaused())
                view.getGame().getTimer().update();
        }
    }

    private void inputHandler() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && view.getGame().isGamePaused()) {
            bulletController.setPauseMode(true);
            view.getGame().getTimer().setPauseMode(true);
            setPauseMode(true);


            view.getGame().setGamePaused(true);
            TillDawn.setCursor("Mouse.png");
            PauseMenu pauseMenu = new PauseMenu(AssetManager.getInstance().getSkin());

            pauseMenu.getResume().addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    pauseMenu.hide();
                    view.getGame().setGamePaused(false);
                    TillDawn.setCursor("CursorSprite.png");
                    bulletController.setPauseMode(false);
                    view.getGame().getTimer().setPauseMode(false);
                    setPauseMode(false);
                }
            });

            pauseMenu.getCheatSheet().addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                }
            });

            pauseMenu.getGiveUp().addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    view.dispose();
                    TillDawn.getGame().setScreen(new MainMenu());
                }
            });

            pauseMenu.getSave().addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    view.dispose();
                    Gdx.app.exit();
                }
            });

            pauseMenu.show(view.getStage());
        }
    }

    private void setPauseMode(boolean state) {
        xpBar.setVisible(!state);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(App.getCurrentUser(), view);
        bulletController = new BulletController(view.getGame().getPlayer().getWeapon(), view);
    }
}
