package com.tilldawn.Models;

import com.badlogic.gdx.graphics.Texture;

public class Bullet {
    private float x, y;
    private final float angle;
    private final float speed;
    private final float lifeTime;
    private float age;
    private final Texture texture;
    private boolean active = true;

    public Bullet(float x, float y, float angle, float speed, float lifeTime, float age) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.age = age;
        texture = AssetManager.getInstance().getTexture("Bullet.png");
    }

    public void update() {
        if (!active) return;

        x += (float) (Math.cos(Math.toRadians(angle)) * speed);
        y += (float) (Math.sin(Math.toRadians(angle)) * speed);

        age += 1f / 60f;

        if (age >= lifeTime)
            active = false;

        //TODO برخورد با مانع
    }

    //

    public boolean isActive() {
        return active;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
