package com.pimentoso.games.lib.g2d.legacy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

/**
 * A circular {@link TransitionGameObject}.
 * 
 * @author IG07588
 *
 */
public class CircleGameObject extends TransitionGameObject {
	public final Circle bounds;

	/**
	 * Creates a CircleDynamicGameObject. Its bounds diameter will be equal to its sprite's width.
	 * X and Y coordinates refer to its center point.
	 * @param sprite
	 * @param x
	 * @param y
	 */
	public CircleGameObject(Sprite sprite, float x, float y) {
		super(sprite, x, y);
		this.bounds = new Circle(x, y, sprite.getWidth() / 2);
	}

	/**
	 * Creates a CircleDynamicGameObject. Its bounds diameter will be radius*2, the sprite can be smaller or bigger.
	 * X and Y coordinates refer to its center point.
	 * @param sprite
	 * @param x
	 * @param y
	 * @param radius
	 */
	public CircleGameObject(Sprite sprite, float x, float y, float radius) {
		super(sprite, x, y);
		this.bounds = new Circle(x, y, radius);
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
		sprite.setPosition(position.x - (sprite.getWidth() / 2), position.y - (sprite.getHeight() / 2));
		sprite.draw(batch, alpha);
		sprite.setScale(1);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		bounds.set(position.x, position.y, bounds.radius);
	}

	@Override
	public void reset(float x, float y) {
		super.reset(x, y);
		bounds.set(position.x, position.y, bounds.radius);
	}
}
