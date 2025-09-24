package com.tilldawn.Models;

import com.tilldawn.Models.Enums.EnemyType;

public class Enemy {
    private int id;
    private final EnemyType type;
    private int health;
    private float speed;
    private final CollisionRect rect;

    public Enemy(EnemyType type, CollisionRect rect) {
        this.type = type;
        this.rect = rect;
    }

    public EnemyType getType() {
        return type;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
