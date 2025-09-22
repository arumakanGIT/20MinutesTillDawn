package com.tilldawn.Models;

public class Game {
    private int minutes;
    private int seconds;
    private final User player;
    private boolean gameFinished = false;
    private boolean gamePaused = false;

    public Game(int minutes, int seconds, User player) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.player = player;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public boolean isGameFinished() {
        return gameFinished;
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

    public void decreaseMinutes() {
        this.minutes -= 1;
    }

    public void decreaseSeconds() {
        this.seconds -= 1;
        if (seconds == 0)
            if (minutes == 0)
                gameFinished = true;
            else {
                seconds = 60;
                decreaseMinutes();
            }
    }

    public void decreaseSeconds(int seconds) {
        this.seconds -= seconds;
    }

    public void decreaseMinutes(int minutes) {
        this.minutes -= minutes;
    }
}
