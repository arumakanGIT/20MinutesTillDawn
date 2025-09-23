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
import com.tilldawn.Models.AnimationActor;
import com.tilldawn.Models.AnimationManager;
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
    private final Button songRightBut;
    private final Button songLeftBut;
    private final CheckBox autoreloadCheckBox;
    private final CheckBox bwCheckBox;

    public SettingMenu() {
        Viewport viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        Skin skin = AssetManager.getInstance().getSkin();

        Table page1 = new Table();
        page1.setSize(1280, 720 * 3);

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
        songs.setItems(songsList.toArray(new String[0]));
        songs.setDisabled(true);
        songs.setAlignment(Align.center);

        songRightBut = new Button(skin, "right");
        songLeftBut = new Button(skin, "left");

        autoreloadCheckBox = new CheckBox("", skin, "on-off");
        bwCheckBox = new CheckBox("", skin, "on-off2");

        AnimationActor tentacle = new AnimationActor(AnimationManager.getInstance().get("witcherMonsterAnim"));
        page1.add(tentacle).row();

        int padTop = 40;
        int sliderW = 300;
        int padRight = 50;

        page1.add(new Label("Game Audio settings", skin, "title_chvy_WHITE_24")).padBottom(padTop * 2).row();
        Table row2 = new Table();
        row2.add(songLeftBut).padRight(padRight);
        row2.add(songs).width(600);
        row2.add(songRightBut).padLeft(padRight);
        page1.add(row2).row();
        Table row1 = new Table();
        row1.add(musicLabel).padRight(padRight);
        row1.add(musicSlider).width(sliderW).row();
        row1.add(SFXLabel).padTop(padTop).padRight(padRight);
        row1.add(sfxSlider).width(sliderW).padTop(padTop).row();
        row1.add(ambientLabel).padTop(padTop).padRight(padRight);
        row1.add(ambientSlider).padTop(padTop).width(sliderW).row();
        row1.add(footLabel).padTop(padTop).padRight(padRight);
        row1.add(footSlider).padTop(padTop).width(sliderW).row();
        page1.add(row1).padTop(padTop).row();
        page1.add(new Label("Game View Settings", skin, "title_chvy_WHITE_24")).padTop(padTop * 2).padBottom(padTop * 2).row();
        Table row3 = new Table();
        row3.add(new Label("Auto Reload", skin)).padRight(padRight);
        row3.add(autoreloadCheckBox).row();
        row3.add(new Label("Black and White", skin)).padTop(padTop).padRight(padRight);
        row3.add(bwCheckBox).padTop(padTop).row();
        page1.add(row3).row();
        page1.add(new Label("Game Controller Settings", skin, "title_chvy_WHITE_24")).padTop(padTop * 2).padBottom(padTop * 2).padBottom(600).row();


        ScrollPane scrollPane = new ScrollPane(page1);

        table.add(scrollPane).width(1280).height(720);
        stage.addActor(table);
        new SettingMenuController(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(39f / 255f, 33f / 255f, 42f / 255f, 1f));
        TillDawn.getGame().getBatch().setProjectionMatrix(stage.getCamera().combined);
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
