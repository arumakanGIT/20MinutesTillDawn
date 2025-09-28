package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Models.Enums.EnemyType;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;

public class Enemy {
    private static int counter = 0;
    private final int id;
    private final EnemyType type;
    private final CollisionRect rect;
    private final User player;
    private float stateTime = 0f;
    private final GameView view;
    private float health = 0f;

    public Enemy(EnemyType type, CollisionRect rect, GameView view) {
        counter++;
        this.id = counter;
        this.type = type;
        this.rect = rect;
        this.view = view;
        this.player = view.getGame().getPlayer();
        this.health = type.getHealth();
    }

    public void update() {
        if (view.getGame().isGamePaused()) {
            stateTime += Gdx.graphics.getDeltaTime();
            float playerX = player.getRect().getX();
            float playerY = player.getRect().getY();

            if (rect.getX() != playerX || rect.getY() != playerY) {
                float dx = player.getRect().getX() - rect.getX();
                float dy = player.getRect().getY() - rect.getY();
                float len = (float) Math.sqrt(dx * dx + dy * dy);
                if (len != 0) {
                    dx /= len;
                    dy /= len;
                }
                float speed = type.getSpeed();
                float delta = Gdx.graphics.getDeltaTime();
//                rect.move(rect.getX() + dx * speed * delta,
//                    rect.getY() + dy * speed * delta);
                rect.move(rect.getX() + type.getSpeed() * dx,  rect.getY() + (type.getSpeed() * dy));
            }
        }

        TextureRegion enemyFrame;
        enemyFrame = AnimationManager.getInstance().get("tentacleMonsterAnim").getKeyFrame(stateTime, true);

        float scale = 2f;

        TillDawn.getGame().getBatch().draw(
            enemyFrame,
            rect.getX(),
            rect.getY(),
            rect.getWidth() ,
            rect.getHeight());
    }

    // getter and setter

    public float getSpeed() {
        return type.getSpeed();
    }

    public float getDamage() {
        return type.getDamage();
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public EnemyType getType() {
        return type;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public int getId() {
        return id;
    }
}
