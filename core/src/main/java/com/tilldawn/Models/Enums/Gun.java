package com.tilldawn.Models.Enums;

public enum Gun {
    SMG(22f, 2.5f, 0f, 12f, 30, 0.1f),
    Shotgun(16f, 1.8f, 0f, 30f, 2, 0.6f),
    Revolver(20f, 2.2f, 0f, 22f, 7, 0.4f),
    ;

    private final float speed;
    private final float lifeTime;
    private final float age;
    private final float damage;
    private final int amount;
    private final float fireRate;

    Gun(float speed, float lifeTime, float age, float damage, int amount, float fireRate) {
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.age = age;
        this.damage = damage;
        this.amount = amount;
        this.fireRate = fireRate;
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
}
