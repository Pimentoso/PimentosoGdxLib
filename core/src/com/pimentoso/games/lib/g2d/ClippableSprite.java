package com.pimentoso.games.lib.g2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * Sprite with capabilities of drawing only a portion of its region.
 * Drawn portion starts at the bottom left corner.
 */
public class ClippableSprite extends Sprite {

	private float vClipping;
	private float hClipping;
	
	private int clippedRegionWidth;
	private int clippedRegionHeight;
	
	public ClippableSprite() {
		super();
		setClipping(1f, 1f);
	}

	public ClippableSprite(Sprite sprite) {
		super(sprite);
		setClipping(1f, 1f);
	}

	public ClippableSprite(TextureRegion region) {
		super(region);
		setClipping(1f, 1f);
	}

	/**
	 * Set clipping.
	 * http://rotatingcanvas.com/drawing-part-of-image-in-libgdx/
	 * @param h percentage of region width to draw (between 0 and 1)
	 * @param v percentage of region height to draw (between 0 and 1)
	 */
	public void setClipping(float h, float v) {
		hClipping = MathUtils.clamp(h, 0, 1);
		clippedRegionWidth = (int) (getRegionWidth()*hClipping);
		hClipping = (float) clippedRegionWidth/(float) getRegionWidth();
		
		vClipping = MathUtils.clamp(v, 0, 1);
		clippedRegionHeight = (int) (getRegionHeight()*vClipping);
		vClipping = (float) clippedRegionHeight/(float) getRegionHeight();
	}
	
	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setBounds(x, y, width*hClipping, height*vClipping);
	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width*hClipping, height*vClipping);
	}

	@Override
	public void draw(Batch batch) {
		batch.draw(getTexture(), getX(), getY(), getWidth()*hClipping, getHeight()*vClipping, getRegionX(), getRegionY()+(getRegionHeight()-clippedRegionHeight), clippedRegionWidth, clippedRegionHeight, false, false);
	}
}
