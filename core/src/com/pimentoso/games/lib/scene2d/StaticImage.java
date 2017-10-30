package com.pimentoso.games.lib.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

/**
 * Static image scene2d actor. Similar to {@link Image}.
 * Supports {@link TextureRegionDrawable} and {@link SpriteDrawable}.
 * Will apply rotation, origin and scale even when a {@link SpriteDrawable} is used.
 * @author Pimentoso 
 */
public class StaticImage extends Widget {
	
	private Scaling scaling;
	private int align = Align.center;
	private float imageX, imageY, imageWidth, imageHeight;
	private Drawable drawable;

	public StaticImage () {
		this((Drawable)null);
	}

	public StaticImage (TextureRegion region) {
		this(new TextureRegionDrawable(region), Scaling.stretch, Align.center);
	}

	public StaticImage (Sprite sprite) {
		this(new SpriteDrawable(sprite), Scaling.stretch, Align.center);
	}

	public StaticImage (Drawable drawable) {
		this(drawable, Scaling.stretch, Align.center);
	}

	public StaticImage (Drawable drawable, Scaling scaling, int align) {
		setDrawable(drawable);
		this.scaling = scaling;
		this.align = align;
		setWidth(getPrefWidth());
		setHeight(getPrefHeight());
	}

	@Override
	public void layout () {
		float regionWidth, regionHeight;
		if (drawable != null) {
			regionWidth = drawable.getMinWidth();
			regionHeight = drawable.getMinHeight();
		} else
			return;

		float width = getWidth();
		float height = getHeight();

		Vector2 size = scaling.apply(regionWidth, regionHeight, width, height);
		imageWidth = size.x;
		imageHeight = size.y;

		if ((align & Align.left) != 0)
			imageX = 0;
		else if ((align & Align.right) != 0)
			imageX = width - imageWidth;
		else
			imageX = width / 2 - imageWidth / 2;

		if ((align & Align.top) != 0)
			imageY = height - imageHeight;
		else if ((align & Align.bottom) != 0)
			imageY = 0;
		else
			imageY = height / 2 - imageHeight / 2;
	}

	@Override
	public void draw (Batch batch, float parentAlpha) {
		validate();

		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

		float x = getX();
		float y = getY();
		float scaleX = getScaleX();
		float scaleY = getScaleY();

		if (drawable != null) {
			if (drawable.getClass() == TextureRegionDrawable.class) {
				TextureRegion region = ((TextureRegionDrawable)drawable).getRegion();
				float rotation = getRotation();
				if (scaleX == 1 && scaleY == 1 && rotation == 0)
					batch.draw(region, x + imageX, y + imageY, imageWidth, imageHeight);
				else {
					batch.draw(region, x + imageX, y + imageY, getOriginX() - imageX, getOriginY() - imageY, imageWidth, imageHeight,
						scaleX, scaleY, rotation);
				}
			}
			else if (drawable.getClass() == SpriteDrawable.class) {
				Sprite sprite = ((SpriteDrawable)drawable).getSprite();
				float rotation = getRotation();
				if (scaleX != 1 || scaleY != 1 || rotation != 0) {
					sprite.setOrigin(getOriginX(), getOriginY());
					sprite.setRotation(rotation);
					sprite.setScale(scaleX, scaleY);
				}
				sprite.setBounds(x + imageX, y + imageY, imageWidth, imageHeight);
				sprite.draw(batch);
			}
			else {
				drawable.draw(batch, x + imageX, y + imageY, imageWidth * scaleX, imageHeight * scaleY);
			}
		}
	}

	public void setDrawable (Drawable drawable) {
		if (drawable != null) {
			if (this.drawable == drawable) return;
			if (getPrefWidth() != drawable.getMinWidth() || getPrefHeight() != drawable.getMinHeight()) invalidateHierarchy();
		} else {
			if (getPrefWidth() != 0 || getPrefHeight() != 0) invalidateHierarchy();
		}
		this.drawable = drawable;
	}

	public Drawable getDrawable () {
		return drawable;
	}

	public void setScaling (Scaling scaling) {
		if (scaling == null) throw new IllegalArgumentException("scaling cannot be null.");
		this.scaling = scaling;
	}

	public void setAlign (int align) {
		this.align = align;
	}

	public float getMinWidth () {
		return 0;
	}

	public float getMinHeight () {
		return 0;
	}

	public float getPrefWidth () {
		if (drawable != null) return drawable.getMinWidth();
		return 0;
	}

	public float getPrefHeight () {
		if (drawable != null) return drawable.getMinHeight();
		return 0;
	}

	public float getImageX () {
		return imageX;
	}

	public float getImageY () {
		return imageY;
	}

	public float getImageWidth () {
		return imageWidth;
	}

	public float getImageHeight () {
		return imageHeight;
	}
}
