package com.pimentoso.games.lib.box2d;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Scaling;
import com.pimentoso.games.lib.scene2d.StaticImage;

/**
 * Generic {@link StaticImage} actor linked to a Box2d body.
 * This actor automatically follows its Box2d body when act() is called.
 * @author Pimentoso
 */
public class ImageActor extends StaticImage implements Poolable, Disposable {

	public static final int STATE_READY = 0;
	public static final int STATE_ALIVE = 16;
	public static final int STATE_DEAD =  32;
	
	public boolean hit;
	public int coins;

	public int state;
	public float stateTime;
	
	public ImageActor() {
		// empty constructor
	}
	
	public void init(TextureRegion region, float x, float y, float width, float height) {
				
		if (getDrawable() == null) {
			setDrawable(new TextureRegionDrawable(region));
		}
		else {
			TextureRegionDrawable drawable = (TextureRegionDrawable) getDrawable();
			drawable.setRegion(region);
		}
		
		coins = 1;
		setState(STATE_READY);
		setPosition(x, y, Align.center); // TODO CONTROLLARE
		setSize(width, height);
		setScaling(Scaling.stretch);
		setAlign(Align.center);
		invalidate();
	}
	
	public void setState(int state) {
		this.state = state;
		this.stateTime = 0f;
	}

	@Override
	public void reset() {
		hit = false;
		clearActions();
		remove();
	}

	@Override
	public void dispose() {
		clearActions();
		remove();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
        stateTime += delta;
		setRotation(MathUtils.radiansToDegrees * getRotation()); // TODO controllare
		setPosition(getX()-getWidth()/2, getY()-getHeight()/2);
	}
	
	public boolean isOutOfScreen(float worldWidth, float worldHeight) {
		return getX() < 0 || getY() < 0 || getX() > worldWidth || getY() > worldHeight;
	}
	
	public void changeSprite(Sprite s) {
		SpriteDrawable drawable = (SpriteDrawable) getDrawable();
		drawable.setSprite(s);
		invalidate();
	}
}
