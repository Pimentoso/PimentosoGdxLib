package com.pimentoso.games.lib.scene2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

/**
 * Created by sako on 22/09/2017.
 */

public class AnimatedDrawable extends BaseDrawable {

    private Animation<TextureAtlas.AtlasRegion> animation;
    private float stateTime;
    private boolean looping;

    public AnimatedDrawable (Animation animation, boolean looping) {
        setAnimation(animation, looping);
    }

    public AnimatedDrawable (AnimatedDrawable drawable) {
        super(drawable);
        setAnimation(drawable.animation, drawable.looping);
    }

    @Override
    public void draw (Batch batch, float x, float y, float width, float height) {
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(stateTime, this.looping), x, y, width, height);
    }

    public void setAnimation (Animation<TextureAtlas.AtlasRegion> animation, boolean looping) {
        this.animation = animation;
        this.looping = looping;
        setMinWidth(Math.abs(animation.getKeyFrame(0).getRegionWidth()));
        setMinHeight(Math.abs(animation.getKeyFrame(0).getRegionHeight()));
    }

    public Animation getAnimation () {
        return animation;
    }
}