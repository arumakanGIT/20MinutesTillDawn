package com.tilldawn.Models.Enums;

import com.tilldawn.Models.Shooting.NormalStrategy;
import com.tilldawn.Models.Shooting.ShootingStrategy;
import com.tilldawn.Models.Shooting.ShotgunStrategy;

public enum Gun {
    SMG(22f, 1f, 0f, 8f, 30, 0.1f, new NormalStrategy()),
    Shotgun(15f, 0.3f, 0f, 10f, 2, 0.6f, new ShotgunStrategy()),
    Revolver(20f, 1f, 0f, 20f, 7, 0.4f, new NormalStrategy()),
    ;

    private final float speed;
    private final float lifeTime;
    private final float age;
    private final float damage;
    private final int amount;
    private final float fireRate;
    private final ShootingStrategy strategy;

    Gun(float speed, float lifeTime, float age, float damage, int amount, float fireRate, ShootingStrategy strategy) {
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.age = age;
        this.damage = damage;
        this.amount = amount;
        this.fireRate = fireRate;
        this.strategy = strategy;
    }

    public float getFireRate() {
        return fireRate;
    }

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public float getAge() {
        return age;
    }

    public int getAmount() {
        return amount;
    }

    public ShootingStrategy getStrategy() {
        return strategy;
    }
}
