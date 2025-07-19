package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Controller.GameAudioController;
import com.tilldawn.View.MainMenuView;

public class Main extends Game {
    private static SpriteBatch batch;
    private static Main game;

    @Override
    public void create() {
        GameAudioController.getInstance().playMusic("Pretty Dungeon LOOP.wav", false, 0.1f);
        batch = new SpriteBatch();
        game = this;
        setScreen(new MainMenuView(game));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
