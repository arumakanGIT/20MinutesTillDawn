package com.tilldawn.Models.Enums;

public enum Avatar {
    Abby(0, 0),
    Dasher(0, 0),
    Diamond(0, 0),
    Hastur(0, 0),
    Hina(0, 0),
    Lilith(0, 0),
    Luna(0, 0),
    Raven(0, 0),
    Scarlett(0, 0),
    Shana(0, 0),
    Yuki(0, 0);

    private int health;
    private int speed;

    Avatar(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
