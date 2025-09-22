package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Controller.SettingMenuController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.TillDawn;

import java.util.ArrayList;

public class SettingMenu implements AppView {

    private final Stage stage;

    private final Slider musicSlider;
    private final Slider ambientSlider;
    private final Slider footSlider;
    private final Slider sfxSlider;

    public SettingMenu() {
        Viewport viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        Skin skin = AssetManager.getInstance().getSkin();

        Table page1 = new Table();
        page1.setSize(1280, 720 * 3);
        page1.setFillParent(true);

        Label musicLabel = new Label("Music", skin);
        musicSlider = new Slider(0, 100, 1, false, skin);
        musicSlider.setValue(GameAudioManager.musicVolume * 100);

        Label SFXLabel = new Label("SFX", skin);
        sfxSlider = new Slider(0, 100, 1, false, skin);
        sfxSlider.setValue(GameAudioManager.sfxVolume * 100);

        Label footLabel = new Label("Foot", skin);
        footSlider = new Slider(0, 100, 1, false, skin);
        footSlider.setValue(GameAudioManager.footStepVolume * 100);

        Label ambientLabel = new Label("Ambient", skin);
        ambientSlider = new Slider(0, 100, 1, false, skin);
        ambientSlider.setValue(GameAudioManager.ambientVolume * 100);

        SelectBox<String> songs = new SelectBox<>(skin);
        ArrayList<String> songsList = new ArrayList<>();
        songsList.add("test1");
        songsList.add("test2");
        songsList.add("test3");
        songsList.add("test4");
        songs.setItems(String.valueOf(songsList));
        songs.setDisabled(true);
        songs.setAlignment(Align.center);

        int padTop = 25;
        int sliderW = 300;
        int padRight = 50;
        page1.add(musicLabel).padRight(padRight);
        page1.add(musicSlider).width(sliderW).row();
        page1.add(SFXLabel).padTop(padTop).padRight(padRight);
        page1.add(sfxSlider).width(sliderW).padTop(padTop).row();
        page1.add(ambientLabel).padTop(padTop).padRight(padRight);
        page1.add(ambientSlider).padTop(padTop).width(sliderW).row();
        page1.add(footLabel).padTop(padTop).padRight(padRight);
        page1.add(footSlider).padTop(padTop).width(sliderW).row();
        page1.add(songs).width(600).row();

        ScrollPane scrollPane = new ScrollPane(page1);

        table.add(scrollPane).width(1280).height(720);
        stage.addActor(table);
        new SettingMenuController(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(39f / 255f, 33f / 255f, 42f / 255f, 1f));
        TillDawn.getGame().getBatch().setProjectionMatrix(stage.getCamera().combined);
        TillDawn.getGame().getBatch().begin();

        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    //

    public Slider getAmbientSlider() {
        return ambientSlider;
    }

    public Slider getFootSlider() {
        return footSlider;
    }

    public Slider getSfxSlider() {
        return sfxSlider;
    }

    public Slider getMusicSlider() {
        return musicSlider;
    }

    //

    @Override
    public void show() {

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

    @Override
    public void dispose() {

    }
}
