package com.tilldawn.Controller.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Models.*;
import com.tilldawn.Models.Enums.Gun;
import com.tilldawn.Models.Enums.Move;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;

public class PlayerController {
    private final User player;
    private final Gun gun;
    private float stateTime = 0f;
    private int facingRight = 1;
    private final static float scale = 2.5f;
    private boolean isReloading = false;
    private final GameView view;
    private float gunX;
    private float gunY;
    private final GameInputSetting gameInputSetting;
    private final CollisionRect rect;

    public PlayerController(User player, GameView gameView) {
        this.player = player;
        gun = player.getWeapon();
        this.view = gameView;
        gameInputSetting = player.getGameInputSetting();
        rect = player.getRect();

        int padding = 10;
        float base = (view.getStage().getWidth() / 2)
            - ((new AnimationActor(AnimationManager.getInstance().get("HeartAnim")).getWidth() + padding) * 2);
        AnimationActor[] hearts = new AnimationActor[5];
        for (int i = 0; i < hearts.length; i++) {
            hearts[i] = new AnimationActor(AnimationManager.getInstance().get("HeartAnim"));
            hearts[i].setPosition(base + ((hearts[0].getWidth() + padding) * i), view.getStage().getHeight() - hearts[0].getHeight() - 75);
            view.getStage().addActor(hearts[i]);
        }
    }

    public void update() {
        if (view.getGame().isGamePaused()) {
            handlePlayerInput();
            stateTime += Gdx.graphics.getDeltaTime();
        }
        TextureRegion playerFrame;

        if (player.getMoveState() == Move.idle) {
            playerFrame = AnimationManager.getInstance().get("idle").getKeyFrame(stateTime, true);
        } else if (player.getMoveState() == Move.run) {
            playerFrame = AnimationManager.getInstance().get("run").getKeyFrame(stateTime, true);
        } else {
            playerFrame = AnimationManager.getInstance().get("walk").getKeyFrame(stateTime, true);
        }

        float drawW = playerFrame.getRegionWidth() * scale, drawH = playerFrame.getRegionHeight() * scale;

        // draw player

        TextureRegion gunFrame;
        if (isReloading) {
            Animation<TextureRegion> reload = AnimationManager.getInstance().get(gun.name() + "Reload");
            gunFrame = reload.getKeyFrame(stateTime, false);
            System.out.println("in reloading animation" + stateTime);
            if (reload.isAnimationFinished(stateTime)) {
                isReloading = false;
            }
        } else gunFrame = AnimationManager.getInstance().get(gun.name() + "Reload").getKeyFrame(0);

        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        view.getCamera().unproject(mousePos);
        float dx = mousePos.x - rect.getX();
        float dy = mousePos.y - rect.getY();

        float rawAngle = (float) Math.toDegrees(Math.atan2(dy, dx));
        float angleDraw = rawAngle;

        boolean flipGun = false;
        if (rawAngle > 90 || rawAngle < -90) {
            angleDraw += 180;
            flipGun = true;
        }

        float distanceFromPlayer = 20f;

        float playerCenterX = rect.getX();
        float playerCenterY = rect.getY();
        float offsetX = (float) Math.cos(Math.toRadians(rawAngle)) * distanceFromPlayer + 15;
        float offsetY = (float) Math.sin(Math.toRadians(rawAngle)) * distanceFromPlayer + 30;

        gunX = playerCenterX + offsetX;
        gunY = playerCenterY + offsetY;

        TillDawn.getGame().getBatch().draw(
            playerFrame,
            rect.getX(),
            rect.getY(),
            drawW / 2,
            drawH / 2,
            drawW,
            drawH,
            facingRight, 1f, 0f);

        TillDawn.getGame().getBatch().draw(
            gunFrame,
            gunX,
            gunY,
            gunFrame.getRegionWidth() / 2f,
            gunFrame.getRegionHeight() / 2f,
            gunFrame.getRegionWidth() * scale,
            gunFrame.getRegionHeight() * scale,
            flipGun ? -1f : 1f, 1f, angleDraw
        );

    }

    private void handlePlayerInput() {
        boolean moving = false;

        int speed = (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
            || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))
            ? Move.run.getSpeed()
            : Move.walk.getSpeed();

        if (Gdx.input.isKeyPressed(gameInputSetting.getUp())) {
            rect.move(rect.getX(), rect.getY() - speed);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(gameInputSetting.getDown())) {
            rect.move(rect.getX(), rect.getY() + speed);
            moving = true;
        }
        if (Gdx.input.isKeyPressed(gameInputSetting.getLeft())) {
            rect.move(rect.getX() - speed, rect.getY());
            facingRight = -1;
            moving = true;
        }
        if (Gdx.input.isKeyPressed(gameInputSetting.getRight())) {
            rect.move(rect.getX() + speed, rect.getY());
            facingRight = 1;
            moving = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            isReloading = true;
            stateTime = 0f;
            view.getController().getBulletController().reload();
            view.getController().getBulletController().updateAmmoLabel();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            view.getController().getEnemyController().clear();
            view.getController().getEnemyController().spawnEnemies();
        }

        if (moving)
            player.setMoveState(speed == Move.run.getSpeed() ? Move.run : Move.walk);
        else
            player.setMoveState(Move.idle);
    }

    public float getGunX() {
        return gunX;
    }

    public float getGunY() {
        return gunY;
    }

    public boolean isReloading() {
        return isReloading;
    }
}
