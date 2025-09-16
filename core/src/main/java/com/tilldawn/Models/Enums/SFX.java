package com.tilldawn.Models.Enums;

public enum SFX {
    click("assets\\SFX\\random\\click.ogg");

    private final String path;

    SFX(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
