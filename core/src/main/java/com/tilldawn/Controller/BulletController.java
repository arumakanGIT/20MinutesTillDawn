package com.tilldawn.Controller;

import com.tilldawn.Models.Bullet;
import com.tilldawn.Models.Enums.Gun;
import com.tilldawn.TillDawn;

import java.util.ArrayList;

public class BulletController {
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final Gun gun;

    public BulletController(Gun gun) {
        this.gun = gun;
    }

    public void shootBulletHandle(float gunX, float gunY, float angle) {
        System.out.println(gunX + " - " + gunY + " - " + angle);
        bullets.add(new Bullet(gunX, gunY, angle, gun.getSpeed(), gun.getLifeTime(), gun.getAge()));
    }

    public void update() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            bullets.get(i).update();
            if (!bullets.get(i).isActive()) {
                bullets.remove(i);
            }
        }

        for (Bullet b : bullets) {
            TillDawn.getGame().getBatch().draw(b.getTexture(), b.getX(), b.getY());
        }
    }
}
