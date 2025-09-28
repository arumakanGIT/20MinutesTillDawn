package com.tilldawn.Controller.GameControllers;

import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.CollisionRect;
import com.tilldawn.Models.Enemy;
import com.tilldawn.Models.Enums.EnemyType;
import com.tilldawn.Models.Timer;
import com.tilldawn.View.GameView;

import java.util.ArrayList;
import java.util.Random;

public class EnemyController {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final GameView view;
    private Timer timer;
    private final Random random = new Random();
    private int lastTime;

    public EnemyController(GameView view) {
        this.view = view;
        spawnEnemies();
    }

    public void setTimer() {
        timer = view.getGame().getTimer();
        lastTime = timer.timeAsSeconds();
    }

    public void spawnEnemies() {
        // Tree

        // Tentacle

        Texture tex = AssetManager.getInstance().getTexture("BrainMonster_0.png");
        float width = tex.getWidth();
        float height = tex.getHeight();
        for (int i = 0; i < 3; i++) {
            float playerX = view.getGame().getPlayer().getRect().getX();
            float playerY = view.getGame().getPlayer().getRect().getY();

            float offsetX = (view.getStage().getWidth() / 2)
                - random.nextFloat(view.getStage().getWidth() / 2) * (random.nextBoolean() ? 1 : -1);
            float offsetY = (view.getStage().getHeight() / 2)
                - random.nextFloat(view.getStage().getHeight() / 2) * (random.nextBoolean() ? 1 : -1);
            enemies.add(new Enemy(EnemyType.Tentacle, new CollisionRect(offsetX, offsetY, width * 2, height * 2), view));
        }

        // Eyebat

        // Elder
    }

    public void update() {
//        if (lastTime - timer.timeAsSeconds() > 3) {
//            lastTime = timer.timeAsSeconds();
//            spawnEnemies();
//        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            enemies.get(i).update();
            if (enemies.get(i).getHealth() <= 0)
                enemies.remove(i);
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // for debug

    public void clear() {
        enemies.clear();
    }
}
