package com.tilldawn.Models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimationActor extends Actor {
    private final Animation<TextureRegion> animation;
    private float stateTime = 0f;

    public AnimationActor(Animation<TextureRegion> animation, float width, float height) {
        this.animation = animation;
        setSize(width, height);
    }

    public AnimationActor(Animation<TextureRegion> animation) {
        this.animation = animation;
        TextureRegion firstFrame = animation.getKeyFrame(0);
        setSize(firstFrame.getRegionWidth(), firstFrame.getRegionHeight());
    }

    public AnimationActor(Animation<TextureRegion> animation, float scale) {
        this.animation = animation;
        TextureRegion firstFrame = animation.getKeyFrame(0);
        setSize(firstFrame.getRegionWidth() * scale, firstFrame.getRegionHeight() * scale);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        batch.draw(frame, getX(), getY(), getWidth(), getHeight());
    }
}
