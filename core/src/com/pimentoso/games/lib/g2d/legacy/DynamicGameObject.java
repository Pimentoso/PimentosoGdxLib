package com.pimentoso.games.lib.g2d.legacy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class DynamicGameObject
{
	/**
	 * Normal/active state.
	 */
	public static final int STATE_NORMAL = 0;
	
	/**
	 * Dead state. Object should not be updated or rendered,
	 * and should be removed from the game.
	 */
	public static final int STATE_DEAD = 999;
	
	public Sprite sprite;
	public final Vector2 position;
	public final Vector2 velocity;
	public final Vector2 accel;
	public float rotation;

	public int state;
	public float stateTime;
	
	public DynamicGameObject(Sprite sprite, float x, float y) {
		this.sprite = sprite;
		this.position = new Vector2(x, y);
		this.velocity = new Vector2();
		this.accel = new Vector2();
		this.rotation = 0;
		setState(STATE_NORMAL);
	}
	
	/**
	 * Changes state of the object. Also resets the state timer.
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
		this.stateTime = 0;
	}
	
	/**
	 * Resets the object to its initial state.
	 */
	public void reset(float x, float y) {
		this.position.set(x, y);
		this.velocity.set(0, 0);
		this.accel.set(0, 0);
		this.rotation = 0;
		setState(STATE_NORMAL);
	}
	
	public abstract void update(float delta);
	
	public abstract void draw(SpriteBatch batch);
}
