package com.pimentoso.games.lib.g2d.legacy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * A rectangular {@link TransitionGameObject}.
 * 
 * @author IG07588
 *
 */
public class RectangleGameObject extends TransitionGameObject {
	public final Rectangle bounds;

	/**
	 * Creates a RectDynamicGameObject. Its bounds size will be equal to its sprite.
	 * X and Y coordinates refer to its center point.
	 * @param sprite
	 * @param x
	 * @param y
	 */
	public RectangleGameObject(Sprite sprite, float x, float y) {
		super(sprite, x, y);
		this.bounds = new Rectangle(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2), sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * Creates a RectDynamicGameObject. Its bounds size will be width*height, the sprite can be smaller or bigger.
	 * X and Y coordinates refer to its center point.
	 * @param sprite
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public RectangleGameObject(Sprite sprite, float x, float y, float width, float height) {
		super(sprite, x, y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (state == STATE_DEAD)
			return;
		if (rotation != 0) {
			sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
			sprite.setRotation(rotation);
		}
		if (scale != 1) sprite.setScale(scale);
		sprite.setBounds(position.x - (sprite.getWidth() / 2), position.y - (sprite.getHeight() / 2), sprite.getWidth(), sprite.getHeight());
		sprite.draw(batch, alpha);
		sprite.setScale(1);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		bounds.setX(position.x - (bounds.width / 2));
		bounds.setY(position.y - (bounds.height / 2));
	}

	@Override
	public void reset(float x, float y) {
		super.reset(x, y);
		bounds.setX(position.x - (bounds.width / 2));
		bounds.setY(position.y - (bounds.height / 2));
	}
}
