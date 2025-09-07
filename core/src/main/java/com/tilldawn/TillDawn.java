package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.Models.UserTable;
import com.tilldawn.View.LoginMenu;

public class TillDawn extends Game {
    private static SpriteBatch batch;
    private static TillDawn game;

    @Override
    public void create() {
        UserTable.createTable();
        GameAudioManager.getInstance().playMusic("Pretty Dungeon LOOP.wav", false, GameAudioManager.musicVolume);
        batch = new SpriteBatch();
        game = this;
        setScreen(new LoginMenu());
    }

    public static TillDawn getGame() {
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
