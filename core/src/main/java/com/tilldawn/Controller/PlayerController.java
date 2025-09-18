package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tilldawn.Models.User;
import com.tilldawn.TillDawn;

public class PlayerController {
    private final User player;

    public PlayerController(User player) {
        this.player = player;
    }

    public void update() {
        player.getPlayerSprite().draw(TillDawn.getGame().getBatch());
        if (player.isPlayerIdle()) {
            idAnimation();
        }
        handlePlayerInput();
    }

    private void handlePlayerInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setPlayerY(player.getPlayerY() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setPlayerY(player.getPlayerY() + player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setPlayerX(player.getPlayerX() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setPlayerX(player.getPlayerX() + player.getSpeed());
            player.getPlayerSprite().flip(true, false);
        }
    }

    public User getPlayer() {
        return player;
    }


}
