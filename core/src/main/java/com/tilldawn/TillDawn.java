package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Models.*;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;

public class TillDawn extends Game {
    private static SpriteBatch batch;
    private static TillDawn game;

    @Override
    public void create() {
        UserTable.createTable();
        GameAudioManager.getInstance().playMusic("Pretty Dungeon LOOP.wav", true, GameAudioManager.musicVolume / 2f);
        batch = new SpriteBatch();
        game = this;

        Preferences prefs = Gdx.app.getPreferences("StayLoggedIn");
        String token = prefs.getString("rememberToken", null);
        if (token != null) {
            String username = UserDAO.getUserByToken(token);
            if (username != null) {
                App.setCurrentUser(UserDAO.getUserByUsername(username));
                setScreen(new MainMenu());
            } else
                setScreen(new LoginMenu());
        } else
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
