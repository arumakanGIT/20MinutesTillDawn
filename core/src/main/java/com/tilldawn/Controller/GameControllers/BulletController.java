package com.tilldawn.Controller.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Bullet;
import com.tilldawn.Models.Enemy;
import com.tilldawn.Models.Enums.Gun;
import com.tilldawn.Models.ScheduledBullet;
import com.tilldawn.TillDawn;
import com.tilldawn.View.GameView;

import java.util.ArrayList;
import java.util.Iterator;

public class BulletController {
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<ScheduledBullet> jobs = new ArrayList<>();
    private final Gun gun;
    private final Table ammoTable;
    private final Table killTable;
    private int MAX_amount;
    private int amount;
    private float lastShotTime = 0f;
    private final GameView view;
    private final Label ammo;
    private final Label kill;
    private float currentTime;

    public BulletController(Gun gun, GameView view) {
        this.view = view;
        this.gun = gun;
        MAX_amount = gun.getAmount();
        amount = MAX_amount;
        Skin skin = AssetManager.getInstance().getSkin();

        // Ammo

        ammoTable = new Table();
        ammoTable.align(Align.top);
        float scale = 1.5f;
        Texture ammoTexture = AssetManager.getInstance().getTexture("T_AmmoIcon.png");
        Image ammoImage = new Image(ammoTexture);
        ammoTable.add(ammoImage).size(ammoTexture.getWidth() * scale,
                ammoTexture.getHeight() * scale)
            .padRight(30);
        ammo = new Label(
            String.format("%02d", amount) + "/" + String.format("%02d", amount),
            skin,
            "chvyExprs_GREEN_24");
        ammoTable.add(ammo);
        ammoTable.setPosition(ammoTable.getPrefWidth() / 2 + 50, view.getStage().getHeight() - 75);
        view.getStage().addActor(ammoTable);

        // kill

        killTable = new Table();
        killTable.align(Align.topRight);
        kill = new Label(
            String.format("%d", view.getGame().getPlayer().getKill()),
            skin,
            "chvyExprs_GREEN_24");
        killTable.add(kill).padRight(30);
        Texture killTexture = AssetManager.getInstance().getTexture("Icon_DarkArts.png");
        Image killImage = new Image(killTexture);
        killTable.add(killImage).size(killTexture.getWidth() * scale + 1,
            killTexture.getHeight() * scale + 1);
        killTable.setPosition(view.getStage().getWidth() - 50, view.getStage().getHeight() - 75);
        killImage.setWidth(ammoTable.getPrefWidth());
        view.getStage().addActor(killTable);
    }

    public void shootBulletHandle() {


        float gunX = view.getController().getPlayerController().getGunX();
        float gunY = view.getController().getPlayerController().getGunY() + 20;

        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        view.getCamera().unproject(mousePos);

        float dx = mousePos.x + 16 - gunX;
        float dy = mousePos.y - 20 - gunY;

        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));


        float fireRate = gun.getFireRate();

        if (amount != 0 && currentTime - lastShotTime >= fireRate) {
            jobs.addAll(gun.getStrategy().shoot(gunX, gunY, angle, gun.getSpeed(), gun.getLifeTime(), currentTime));
            amount -= 1;
            lastShotTime = currentTime;
            updateAmmoLabel();
        }
    }

    public void update() {
        currentTime = TimeUtils.nanoTime() / 1_000_000_000f;

        for (int i = jobs.size() - 1; i >= 0; i--) {
            ScheduledBullet s = jobs.get(i);
            if (currentTime - s.createdAt >= s.delay) {
                bullets.add(new Bullet(
                    view.getController().getPlayerController().getGunX(),
                    view.getController().getPlayerController().getGunY() + 20,
                    s.angle, s.speed, s.lifeTime, 0));
                jobs.remove(i);
            }
        }

        if (view.getGame().isGamePaused())
            for (int i = bullets.size() - 1; i >= 0; i--) {
                bullets.get(i).update();
                if (!bullets.get(i).isActive())
                    bullets.remove(i);
            }

        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();

            for (Enemy e : view.getController().getEnemyController().getEnemies()) {
                if (b.getRect().collidesWith(e.getRect())) {
                    it.remove();
                    e.setHealth(e.getHealth() - gun.getDamage());
                    break;
                }
            }

            TillDawn.getGame().getBatch().draw(b.getTexture(), b.getRect().getX(), b.getRect().getY());
        }
    }

    public void upgradeMaxAmount(int increase) {
        MAX_amount += increase;
    }

    public void updateAmmoLabel() {
        ammo.setText(String.format("%02d", amount) + "/" + String.format("%02d", MAX_amount));
    }

    public void updateKillLabel() {
        kill.setText(view.getGame().getPlayer().getKill());
    }

    public void reload() {
        amount = MAX_amount;
    }

    public void setPauseMode(boolean state) {
        ammoTable.setVisible(!state);
        killTable.setVisible(!state);
    }
}
