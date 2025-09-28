package com.tilldawn.Models.Shooting;

import com.tilldawn.Models.ScheduledBullet;

import java.util.ArrayList;
import java.util.Random;

public class ShotgunStrategy implements ShootingStrategy {

    @Override
    public ArrayList<ScheduledBullet> shoot(float x, float y, float angle, float speed, float lifeTime, float age) {
        ArrayList<ScheduledBullet> bullets = new ArrayList<>();
        Random rand = new Random();
        int count = rand.nextInt(4) + 4;


        bullets.add(new ScheduledBullet(x, y, angle, speed, lifeTime, 0, age));
        bullets.add(new ScheduledBullet(x, y, angle + 10, speed, lifeTime, 0, age));
        bullets.add(new ScheduledBullet(x, y, angle - 10, speed, lifeTime, 0, age));
        for (int i = 0; i < count; i++) {
            int temp = rand.nextInt(20) - 10;
            float delay = rand.nextFloat(0.05f);
            bullets.add(new ScheduledBullet(x, y, angle + temp, speed, lifeTime, delay, age));
        }
        return bullets;
    }

}
