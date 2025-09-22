package com.tilldawn.Models.Enums;

public enum Move {
    run(5),
    idle(0),
    walk(2);

    private final int speed;

    Move(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
