package com.tilldawn.Models.Shooting;

import com.tilldawn.Models.ScheduledBullet;

import java.util.ArrayList;
import java.util.List;

public class NormalStrategy implements ShootingStrategy {
    @Override
    public ArrayList<ScheduledBullet> shoot(float x, float y, float angle, float speed, float lifeTime, float age) {
        return new ArrayList<>(List.of(new ScheduledBullet(x, y, angle, speed, lifeTime, 0, age)));
    }
}
