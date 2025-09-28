package com.tilldawn.Models;

public class ScheduledBullet {
    public float delay;
    public float x, y, angle, speed, lifeTime;
    public float createdAt;

    public ScheduledBullet(float x, float y, float angle, float speed, float lifeTime, float delay, float createdAt) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.delay = delay;
        this.createdAt = createdAt;
    }
}
