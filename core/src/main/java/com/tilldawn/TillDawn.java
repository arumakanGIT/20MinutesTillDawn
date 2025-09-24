package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Models.*;
import com.tilldawn.View.GameView;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;

public class TillDawn extends Game {
    private static SpriteBatch batch;
    private static TillDawn game;

    private static GameView gameView;

    @Override
    public void create() {
        setCursor("Mouse.png");
        UserTable.createUserTable();
        UserTable.createSettingTable();
        UserTable.createGameInputTable();
        batch = new SpriteBatch();
        game = this;

        Preferences prefs = Gdx.app.getPreferences("StayLoggedIn");
        String token = prefs.getString("rememberToken", null);
        System.out.println("Remember token: " + token);
        if (token != null) {
            String username = UserDAO.getUserByToken(token);
            GameAudioManager.getInstance();

            if (username != null) {
                App.setCurrentUser(UserDAO.getUserByUsername(username));
                setScreen(new MainMenu());
            } else {
                setScreen(new LoginMenu());
            }

            System.out.println(username);
            System.out.println(UserDAO.getFloatFieldFromSetting(username,"music"));

            // TODO set song name
        } else
            setScreen(new LoginMenu());
        GameAudioManager.getInstance().playMusic("Songs\\Pretty Dungeon LOOP.wav", true, GameAudioManager.musicVolume);
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

    public static void setCursor(String cursorString) {
        Texture texture = AssetManager.getInstance().getTexture(cursorString);
        TextureData textureData = texture.getTextureData();
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        Pixmap pixmap = textureData.consumePixmap();
        Cursor cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(cursor);
        pixmap.dispose();
    }

    public static GameView getGameView() {
        return gameView;
    }

    public static void setGameView(GameView gameView) {
        TillDawn.gameView = gameView;
    }
}
