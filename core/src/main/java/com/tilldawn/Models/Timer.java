package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;


public class Timer {
    private final Window timerWindow;
    private int minutes;
    private int seconds;
    private float accumulator = 0f;
    private boolean finished = false;
    private final GameView view;
    private final Texture[] digits;
    private final Image image1;
    private final Image image2;
    private final Image image3;
    private final Image image4;

    public Timer(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.view = TillDawn.getGameView();

        digits = new Texture[10];
        for (int i = 0; i <= 9; i++) {
            digits[i] = AssetManager.getInstance().getTexture(i + ".png");
        }

        int minTens = minutes / 10;
        int minOnes = minutes % 10;
        int secTens = seconds / 10;
        int secOnes = seconds % 10;
        int padding = 10;
        float scale = 0.2f;

        timerWindow = new Window("", AssetManager.getInstance().getSkin(), "win4");
        image1 = new Image(digits[minTens]);
        image2 = new Image(digits[minOnes]);
        image3 = new Image(digits[secTens]);
        image4 = new Image(digits[secOnes]);
        Table table = new Table();
        table.add(image1).size(digits[0].getWidth() * scale, digits[0].getHeight() * scale).pad(padding);
        table.add(image2).size(digits[0].getWidth() * scale, digits[0].getHeight() * scale).pad(padding);
        table.add(new Label(":", AssetManager.getInstance().getSkin())).pad(padding);
        table.add(image3).size(digits[0].getWidth() * scale, digits[0].getHeight() * scale).pad(padding);
        table.add(image4).size(digits[0].getWidth() * scale, digits[0].getHeight() * scale).pad(padding);
        timerWindow.add(table).pad(1);
        timerWindow.pack();
        timerWindow.setPosition((view.getStage().getWidth() - timerWindow.getWidth()) / 2, timerWindow.getPrefHeight() / 2 + 5);
        timerWindow.getColor().a = 0.5f;
        view.getStage().addActor(timerWindow);
    }

    public void update() {
        if (finished) return;

        accumulator += Gdx.graphics.getDeltaTime();
        if (accumulator >= 1f) {
            accumulator -= 1f;
            tickDown();
        }

        if (seconds <= 10 || seconds >= 50)
            timerWindow.getColor().a = 1f;
        else
            timerWindow.getColor().a = 0.2f;


        int minTens = minutes / 10;
        int minOnes = minutes % 10;
        int secTens = seconds / 10;
        int secOnes = seconds % 10;

        image1.setDrawable(new TextureRegionDrawable(new TextureRegion(digits[minTens])));
        image2.setDrawable(new TextureRegionDrawable(new TextureRegion(digits[minOnes])));
        image3.setDrawable(new TextureRegionDrawable(new TextureRegion(digits[secTens])));
        image4.setDrawable(new TextureRegionDrawable(new TextureRegion(digits[secOnes])));
    }

    private void tickDown() {
        if (seconds > 0) {
            seconds--;
        } else if (minutes > 0) {
            minutes--;
            seconds = 59;
        } else {
            finished = true;
            view.getGame().setGameFinished(true);
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getFormattedTime() {
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void reset(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.accumulator = 0f;
        this.finished = false;
    }
}
