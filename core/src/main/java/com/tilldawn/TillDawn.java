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
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;

public class TillDawn extends Game {
    private static SpriteBatch batch;
    private static TillDawn game;

    @Override
    public void create() {
        setCursor("Mouse.png");
        UserTable.createTable();
        GameAudioManager.getInstance().playMusic("Songs\\Pretty Dungeon LOOP.wav", true, GameAudioManager.musicVolume);
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
}
