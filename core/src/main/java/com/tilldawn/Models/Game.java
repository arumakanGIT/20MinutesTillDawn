package com.tilldawn.Models;

public class Game {
    private final User player;
    private boolean gameFinished = false;
    private boolean gamePaused = false;
    private Timer timer;

    public Game(User player) {
        this.player = player;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
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

    public User getPlayer() {
        return player;
    }

    public Timer getTimer() {
        return timer;
    }
}
