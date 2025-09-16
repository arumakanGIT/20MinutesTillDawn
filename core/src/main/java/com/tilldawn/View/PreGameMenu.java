package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Controller.PreGameMenuController;
import com.tilldawn.Models.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.UserDAO;
import com.tilldawn.TillDawn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PreGameMenu implements AppView {

    public static final int weaponX = 830;
    public static final int weaponY = 400;
    private final Texture background;
    private final Stage stage;
    private final TextButton backButton;
    private final Button startButton;
    private final Button avatarRight;
    private final Button avatarLeft;
    private final Button weaponRight;
    private final Button weaponLeft;
    private Image avatar;
    private Image avatarText;
    private Image weapon;
    private Image weaponText;
    private Image num1;
    private Image num2;
    private Image num3;
    private Image num4;
    private final Button num1Up;
    private final Button num2Up;
    private final Button num3Up;
    private final Button num4Up;
    private final Button num1Down;
    private final Button num2Down;
    private final Button num3Down;
    private final Button num4Down;
    private final ArrayList<String> avatars = new ArrayList<>();
    private int index;
    private int weaponIndex;
    private final ArrayList<String> weapons = new ArrayList<>(Arrays.asList("SMG", "Revolver", "ShotGun"));

    public PreGameMenu() {
        Viewport viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.top);
        background = new Texture(Gdx.files.internal("PreGameMenu/pregameMenu.png"));
        Skin skin = AssetManager.getInstance().getSkin();
        initAvatars();

        backButton = new TextButton("Back", skin);
        startButton = new Button(skin, "start");
        avatarLeft = new Button(skin, "left");
        weaponLeft = new Button(skin, "left");
        avatarRight = new Button(skin, "right");
        weaponRight = new Button(skin, "right");
        num1Up = new Button(skin, "hidden");
        num2Up = new Button(skin, "hidden");
        num3Up = new Button(skin, "hidden");
        num4Up = new Button(skin, "hidden");
        num1Down = new Button(skin, "hidden");
        num2Down = new Button(skin, "hidden");
        num3Down = new Button(skin, "hidden");
        num4Down = new Button(skin, "hidden");

        index = avatars.indexOf(UserDAO.getStringField(App.getCurrentUser().getUsername(), "avatar") + ".png");
        avatar = new Image(AssetManager.getInstance().getTexture(avatars.get(index)));
        avatarText = new Image(AssetManager.getInstance().getTexture(avatars.get(index).substring(0, avatars.get(index).length() - 4) + "_T.png"));
        weaponIndex = weapons.indexOf(UserDAO.getStringField(App.getCurrentUser().getUsername(), "weapon"));
        weapon = new Image(AssetManager.getInstance().getTexture(weapons.get(weaponIndex) + ".png"));
        weaponText = new Image(AssetManager.getInstance().getTexture(weapons.get(weaponIndex) + "Text.png"));
        num1 = new Image(AssetManager.getInstance().getTexture("2.png"));
        num2 = new Image(AssetManager.getInstance().getTexture("0.png"));
        num3 = new Image(AssetManager.getInstance().getTexture("0.png"));
        num4 = new Image(AssetManager.getInstance().getTexture("0.png"));

        avatar.setScaling(Scaling.fit);
        avatarText.setScaling(Scaling.fit);

        Table row1 = new Table();
        row1.add(avatarLeft).padRight(700);
        avatar.setPosition(400, 450);
        avatarText.setPosition(700, 550);
        table.addActor(avatar);
        table.addActor(avatarText);
        row1.add(avatarRight);

        weapon.setScaling(Scaling.fit);
        weaponText.setScaling(Scaling.fit);

        Table row2 = new Table();
        row2.add(weaponLeft).padRight(700);
        weapon.setPosition(730, 350);
        weaponText.setPosition(400, 360);
        table.addActor(weapon);
        table.addActor(weaponText);
        row2.add(weaponRight);

        Table row3 = new Table();
        Stack num1Stack = new Stack();
        Table num1Table = new Table();
        num1Table.add(num1Up).row();
        num1Table.add(num1Down);
        num1Stack.add(num1);
        num1Stack.add(num1Table);
        row3.add(num1Stack).width(num1Stack.getWidth() / 3).height(num1Stack.getHeight() / 2).padRight(20);
        Stack num2Stack = new Stack();
        Table num2Table = new Table();
        num2Table.add(num2Up).row();
        num2Table.add(num2Down);
        num2Stack.add(num2);
        num2Stack.add(num2Table);
        row3.add(num2Stack).width(num2Stack.getWidth() / 3).height(num2Stack.getHeight() / 2).padRight(40);
        Stack num3Stack = new Stack();
        Table num3Table = new Table();
        num3Table.add(num3Up).row();
        num3Table.add(num3Down);
        num3Stack.add(num3);
        num3Stack.add(num3Table);
        row3.add(num3Stack).width(num3Stack.getWidth() / 3).height(num3Stack.getHeight() / 2).padRight(20);
        Stack num4Stack = new Stack();
        Table num4Table = new Table();
        num4Table.add(num4Up).row();
        num4Table.add(num4Down);
        num4Stack.add(num4);
        num4Stack.add(num4Table);
        row3.add(num4Stack).width(num4Stack.getWidth() / 3).height(num4Stack.getHeight() / 2);

        Table row4 = new Table();
        row4.add(startButton).row();
        row4.add(backButton).padTop(20).row();

        table.add(row1).padTop(110).row();
        table.add(row2).padTop(175).row();
        table.add(row3).padTop(60).row();
        table.add(row4).padTop(85).row();

        addFloatAnimation(avatar);
        addFloatAnimation(weapon);

        stage.addActor(table);
        new PreGameMenuController(this);
    }

    private final Random random = new Random();

    public void addFloatAnimation(final Image image) {
        final float baseX = image.getX();
        final float baseY = image.getY();

        image.addAction(Actions.forever(
            Actions.sequence(
                Actions.moveTo(
                    baseX + (random.nextFloat() * 6 - 3),
                    baseY + (random.nextFloat() * 20 - 10),
                    0.5f + random.nextFloat() * 0.5f,
                    Interpolation.sine
                ),
                Actions.moveTo(
                    baseX + (random.nextFloat() * 20 - 10),
                    baseY + (random.nextFloat() * 10 - 5),
                    0.5f + random.nextFloat() * 0.5f,
                    Interpolation.sine
                ),
                Actions.moveTo(
                    baseX + (random.nextFloat() * 20 - 10),
                    baseY + (random.nextFloat() * 18 - 9),
                    0.5f + random.nextFloat() * 0.5f,
                    Interpolation.sine
                ),
                Actions.moveTo(baseX, baseY, 0.5f)
            )
        ));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(36f / 255f, 32f / 255f, 47f / 255f, 1f));
        TillDawn.getGame().getBatch().setProjectionMatrix(stage.getCamera().combined);
        TillDawn.getGame().getBatch().begin();
        TillDawn.getGame().getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
        TillDawn.getGame().getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }

    //

    public void update() {
        avatar.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(avatars.get(index)))));
        avatarText.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(avatars.get(index).substring(0, avatars.get(index).length() - 4) + "_T.png"))));
        weapon.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(weapons.get(weaponIndex) + ".png"))));
        weaponText.setDrawable(new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(weapons.get(weaponIndex) + "Text.png"))));
    }

    public ArrayList<String> getAvatars() {
        return avatars;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Button getStartButton() {
        return startButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Button getAvatarRight() {
        return avatarRight;
    }

    public Button getAvatarLeft() {
        return avatarLeft;
    }

    public Button getWeaponRight() {
        return weaponRight;
    }

    public Button getWeaponLeft() {
        return weaponLeft;
    }

    public Button getNum1Up() {
        return num1Up;
    }

    public Button getNum2Up() {
        return num2Up;
    }

    public Button getNum3Up() {
        return num3Up;
    }

    public Button getNum4Up() {
        return num4Up;
    }

    public Button getNum1Down() {
        return num1Down;
    }

    public Button getNum2Down() {
        return num2Down;
    }

    public Button getNum3Down() {
        return num3Down;
    }

    public Button getNum4Down() {
        return num4Down;
    }

    public void initAvatars() {
        FileHandle folder = Gdx.files.internal("assets/PreGameMenu/Portraits/");

        if (folder.exists() && folder.isDirectory()) {
            for (FileHandle file : folder.list()) {
                if (!file.isDirectory()) {
                    System.out.println(file.name());
                    avatars.add(file.name());
                }
            }
        } else {
            System.out.println("Path not found");
        }
    }

    public int getWeaponIndex() {
        return weaponIndex;
    }

    public void setWeaponIndex(int weaponIndex) {
        this.weaponIndex = weaponIndex;
    }

    public ArrayList<String> getWeapons() {
        return weapons;
    }

    public Image getWeapon() {
        return weapon;
    }

    public Image getWeaponText() {
        return weaponText;
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
}
