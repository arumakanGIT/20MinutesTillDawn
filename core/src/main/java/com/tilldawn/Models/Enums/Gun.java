package com.tilldawn.Models.Enums;

public enum Gun {
    SMG(22f, 2.5f, 0f, 12f),
    Shotgun(16f, 1.8f, 0f, 30f),
    Revolver(20f, 2.2f, 0f, 22f),
    ;

    private final float speed;
    private final float lifeTime;
    private final float age;
    private final float damage;

    Gun(float speed, float lifeTime, float age, float damage) {
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.age = age;
        this.damage = damage;
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
}
