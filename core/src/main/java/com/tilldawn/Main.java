package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Controller.GameAudioManager;
import com.tilldawn.Models.App;

public class Main extends Game {
    private static SpriteBatch batch;
    private static Main game;

    @Override
    public void create() {
        GameAudioManager.getInstance().playMusic("Pretty Dungeon LOOP.wav", false, GameAudioManager.musicVolume);
        batch = new SpriteBatch();
        game = this;
        setScreen(App.getCurrentMenu());
    }

    public static Main getGame() {
        return game;
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
