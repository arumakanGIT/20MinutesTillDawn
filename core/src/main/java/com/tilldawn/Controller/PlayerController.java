package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Models.AnimationManager;
import com.tilldawn.Models.Enums.Move;
import com.tilldawn.Models.User;
import com.tilldawn.TillDawn;

public class PlayerController {
    private final User player;
    private float stateTime = 0f;

    public PlayerController(User player) {
        this.player = player;
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame;

        if (player.getMoveState() == Move.idle) {
            currentFrame = AnimationManager.getInstance().get("idle").getKeyFrame(stateTime, true);
        } else if (player.getMoveState() == Move.run) {
            currentFrame = AnimationManager.getInstance().get("run").getKeyFrame(stateTime, true);
        } else {
            currentFrame = AnimationManager.getInstance().get("walk").getKeyFrame(stateTime, true);
        }

        TillDawn.getGame().getBatch().draw(currentFrame, player.getPlayerX(), player.getPlayerY());

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
