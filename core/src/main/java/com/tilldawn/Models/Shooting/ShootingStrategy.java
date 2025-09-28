package com.tilldawn.Models.Shooting;

import com.tilldawn.Models.ScheduledBullet;

import java.util.ArrayList;

public interface ShootingStrategy {
    ArrayList<ScheduledBullet> shoot(float x, float y, float angle, float speed, float lifeTime, float age);
}
