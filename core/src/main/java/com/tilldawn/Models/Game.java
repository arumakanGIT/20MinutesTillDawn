package com.tilldawn.Models;

public class Game {
    private int minutes;
    private int seconds;
    private User player;

    public Game(int minutes, int seconds, User player) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.player = player;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public User getPlayer() {
        return player;
    }
}
