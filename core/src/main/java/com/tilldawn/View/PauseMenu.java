package com.tilldawn.View;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class PauseMenu extends Window {
    private final TextButton resume;
    private final TextButton cheatSheet;
    private final TextButton giveUp;
    private final TextButton save;

    public PauseMenu(Skin skin) {
        super("", skin, "win3");
        resume = new TextButton("Resume", skin);
        cheatSheet = new TextButton("Cheat Sheet", skin);
        giveUp = new TextButton("Give Up", skin);
        save = new TextButton("Save & Exit", skin);

        int padTop = 40;

        add(resume).padTop(padTop).row();
        add(cheatSheet).padTop(padTop).row();
        add(giveUp).padTop(padTop).row();
        add(save).padTop(padTop).padBottom(padTop).row();

        pack();

        setMovable(false);
    }

    public void show(Stage stage) {
        if (stage.getViewport() != null)
            this.setPosition((stage.getViewport().getWorldWidth() - this.getPrefWidth()) / 2,
                (stage.getViewport().getWorldHeight() - this.getPrefHeight()) / 2);
        else
            this.setPosition((stage.getWidth() - this.getPrefWidth()) / 2,
                (stage.getHeight() - this.getPrefHeight()) / 2);
        stage.addActor(this);
    }

    public void hide() {
        this.remove();
    }

    public TextButton getResume() {
        return resume;
    }

    public TextButton getCheatSheet() {
        return cheatSheet;
    }

    public TextButton getGiveUp() {
        return giveUp;
    }

    public TextButton getSave() {
        return save;
    }

}
