package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Controller.FontLoader;
import com.tilldawn.Main;
import org.w3c.dom.Text;

public class MainMenuView implements Screen {
    private boolean isFullscreen = false;
    Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
    private final Stage stage;
    private final Main game;
    private final BitmapFont ExpressFont;
    private final Label label_play;
    private final Texture background;

    public MainMenuView(Main game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        ExpressFont = FontLoader.loadFont("fonts/ChevyRay - Express.ttf", 60);
        Label.LabelStyle labelStyle = new Label.LabelStyle(ExpressFont, Color.CORAL);

        // play
        label_play = new Label("PLAY", labelStyle);
        label_play.setPosition((float) Gdx.graphics.getWidth() / 2 - (label_play.getWidth() / 2), (float) (Gdx.graphics.getHeight() / 2) + label_play.getHeight() * 3);
        label_play.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked on");
            }
        });
        stage.addActor(label_play);

        // background
        background = new Texture(Gdx.files.internal("background.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
