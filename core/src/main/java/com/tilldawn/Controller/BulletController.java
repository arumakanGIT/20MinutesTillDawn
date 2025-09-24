package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Bullet;
import com.tilldawn.Models.Enums.Gun;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;

import java.util.ArrayList;

public class BulletController {
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final Gun gun;
    private int MAX_amount;
    private int amount;
    private float lastShotTime = 0f;
    private final GameView view;
    private final Label remind;

    public BulletController(Gun gun, GameView view) {
        this.view = view;
        this.gun = gun;
        MAX_amount = gun.getAmount();
        amount = MAX_amount;
        Skin skin = AssetManager.getInstance().getSkin();
        Table table = new Table();
        table.align(Align.top);
        float scale = 1.5f;
        Texture texture = AssetManager.getInstance().getTexture("T_AmmoIcon.png");
        Image image = new Image(texture);
        table.add(image).size(texture.getWidth() * scale, texture.getHeight() * scale).padRight(30);
        remind = new Label(String.format("%02d", amount) + "/" + String.format("%02d", amount),
            skin, "chvyExprs_GREEN_24");
        table.add(remind);
        table.setPosition(table.getPrefWidth() / 2 + 50, view.getStage().getHeight() - 100);
        view.getStage().addActor(table);
    }

    public void shootBulletHandle(float gunX, float gunY, float angle) {
        float currentTime = TimeUtils.nanoTime() / 1_000_000_000f;
        float fireRate = gun.getFireRate();
        if (amount != 0 && currentTime - lastShotTime >= fireRate) {
            bullets.add(new Bullet(gunX, gunY, angle, gun.getSpeed(), gun.getLifeTime(), gun.getAge()));
            amount -= 1;
            lastShotTime = currentTime;
            updateLabel();
        }
    }

    public void update() {
        if (!view.getGame().isGamePaused())
            for (int i = bullets.size() - 1; i >= 0; i--) {
                bullets.get(i).update();
                if (!bullets.get(i).isActive())
                    bullets.remove(i);
            }

        for (Bullet b : bullets) {
            TillDawn.getGame().getBatch().draw(b.getTexture(), b.getX(), b.getY());
        }
    }

    public void upgradeMaxAmount(int increase) {
        MAX_amount += increase;
    }

    public void updateLabel() {
        remind.setText(String.format("%02d", amount) + "/" + String.format("%02d", MAX_amount));
    }

    public void reload() {
        amount = MAX_amount;
    }
}
