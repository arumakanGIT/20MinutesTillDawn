package com.tilldawn.Models;

import com.badlogic.gdx.Input;

public class GameInputSetting {
    private int up;
    private int down;
    private int left;
    private int right;
    private int reload;
    private int speed;
    private int autoAim;

    public GameInputSetting() {
        // default inputs
        up = Input.Keys.W;
        down = Input.Keys.S;
        left = Input.Keys.A;
        right = Input.Keys.D;
        reload = Input.Keys.R;
        speed = Input.Keys.SHIFT_LEFT;
        autoAim = Input.Keys.SPACE;
    }

    //

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getReload() {
        return reload;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAutoAim() {
        return autoAim;
    }

    public void setAutoAim(int autoAim) {
        this.autoAim = autoAim;
    }
}
