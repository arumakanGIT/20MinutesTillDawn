package com.tilldawn.Models;

import com.badlogic.gdx.graphics.Texture;

public class Bullet {
    private final float angle;
    private final float speed;
    private final float lifeTime;
    private float age;
    private final Texture texture;
    private boolean active = true;
    private final CollisionRect rect;

    public Bullet(float x, float y, float angle, float speed, float lifeTime, float age) {
        this.angle = angle;
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.age = age;
        texture = AssetManager.getInstance().getTexture("Bullet.png");
        this.rect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update() {
        if (!active) return;

        if (age >= 0)
            rect.move(rect.getX() + (float) (Math.cos(Math.toRadians(angle)) * speed),
                rect.getY() + (float) (Math.sin(Math.toRadians(angle)) * speed));

        age += 1f / 60f;

        if (age >= lifeTime)
            active = false;
    }

    //

    public boolean isActive() {
        return active;
    }

    public Texture getTexture() {
        return texture;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
