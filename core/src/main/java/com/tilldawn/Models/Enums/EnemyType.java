package com.tilldawn.Models.Enums;

public enum EnemyType {
    Tree(25, 3, 100),
    Tentacle(10, 4.5f, 100),
    Eyebat(20, 5.5f, 100),
    Elder(75, 5, 200);

    private final float damage;
    private final float speed;
    private float health;

    EnemyType(float damage, float speed, float health) {
        this.damage = damage;
        this.speed = speed;
        this.health = health;
    }

    // getter and setter

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

    public float getHealth() {
        return health;
    }
}
