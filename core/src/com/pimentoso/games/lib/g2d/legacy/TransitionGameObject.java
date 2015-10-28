package com.pimentoso.games.lib.g2d.legacy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * A {@link DynamicGameObject} that allows timed transitions.
 * 
 * @author IG07588
 *
 */
public abstract class TransitionGameObject extends DynamicGameObject {

	public float aliveTime;
	public float scale;
	public float alpha;
	public Array<Transition> transitions;
	private Transition _transition;
	
	public TransitionGameObject(Sprite sprite, float x, float y) {
		super(sprite, x, y);
		this.aliveTime = 0;
		this.scale = 1;
		this.alpha = 1;
		this.transitions = new Array<Transition>(false, 10);
	}
	
	/**
	 * Adds a fadein transition.
	 * @param time
	 * @param duration
	 */
	public TransitionGameObject addFadeIn(float time, float duration) {
		_transition = new Transition(Transition.TYPE_FADEIN);
		_transition.startTime = time;
		_transition.duration = duration;
		transitions.add(_transition);
		return this;
	}
	
	/**
	 * Adds a fadein transition that starts immediately.
	 * @param time
	 * @param duration
	 * @return
	 */
	public TransitionGameObject addFadeIn(float duration) {
		return addFadeIn(aliveTime, duration);
	}
	
	/** 
	 * Adds a fadeout transition.
	 * @param time
	 * @param duration
	 */
	public TransitionGameObject addFadeOut(float time, float duration) {
		_transition = new Transition(Transition.TYPE_FADEOUT);
		_transition.startTime = time;
		_transition.duration = duration;
		transitions.add(_transition);		
		return this;
	}
	
	/**
	 * Adds a fadeout transition that starts immediately.
	 * @param duration
	 * @return
	 */
	public TransitionGameObject addFadeOut(float duration) {
		return addFadeOut(aliveTime, duration);
	}
	
	/**
	 * Adds a rotation transition.
	 * @param speed rotation speed (positive: CCW, negative: CW)
	 * @param time
	 * @param duration
	 */
	public TransitionGameObject addRotation(float speed, float time, float duration) {
		_transition = new Transition(Transition.TYPE_ROTATION);
		_transition.startTime = time;
		_transition.speed = speed;
		_transition.duration = duration;
		transitions.add(_transition);
		return this;
	}
	
	/**
	 * Adds a rotation transition that starts immediately.
	 * @param speed rotation speed (positive: CCW, negative: CW)
	 * @param time
	 * @return
	 */
	public TransitionGameObject addRotation(float speed, float duration) {
		return addRotation(speed, aliveTime, duration);
	}
	
	/**
	 * Kills the element at the specified time.
	 * @param time
	 */
	public TransitionGameObject addDeath(float time) {
		_transition = new Transition(Transition.TYPE_DEATH);
		_transition.startTime = time;
		transitions.add(_transition);
		return this;
	}
	
	/**
	 * Adds a linear movement transition.
	 * @param destX movement destination X point, relative to the start point
	 * @param destY movement destination Y point, relative to the start point
	 * @param time
	 * @param duration
	 */
	public TransitionGameObject addMove(float destX, float destY, float time, float duration) {
		_transition = new Transition(Transition.TYPE_MOVE);
		_transition.startTime = time;
		_transition.duration = duration;
		_transition.toX = destX;
		_transition.toY = destY;
		transitions.add(_transition);
		return this;
	}
	
	/**
	 * Adds a linear movement transition that starts immediately.
	 * @param destX movement destination X point, relative to the start point
	 * @param destY movement destination Y point, relative to the start point
	 * @param duration
	 * @return
	 */
	public TransitionGameObject addMove(float destX, float destY, float duration) {
		return addMove(destX, destY, aliveTime, duration);
	}
	
	/**
	 * Instantly moves the object to a point.
	 * @param destX teleport destination X point, relative to the start point
	 * @param destY teleport destination Y point, relative to the start point
	 * @param time
	 */
	public TransitionGameObject addTeleport(float destX, float destY, float time) {
		_transition = new Transition(Transition.TYPE_TELEPORT);
		_transition.startTime = time;
		_transition.duration = 0;
		_transition.toX = destX;
		_transition.toY = destY;
		transitions.add(_transition);
		return this;
	}
	
	/**
	 * Adds a shrink or grow transition.
	 * Warning: does not modify the object bounds, it only resizes its sprite.
	 * @param fromScale starting scale of the sprite
	 * @param destScale final scale of the sprite
	 * @param time
	 * @param duration
	 * @return
	 */
	public TransitionGameObject addResize(float fromScale, float destScale, float time, float duration) {
		_transition = new Transition(Transition.TYPE_RESIZE);
		_transition.startTime = time;
		_transition.fromValue = fromScale;
		_transition.toValue = destScale;
		_transition.duration = duration;
		transitions.add(_transition);
		return this;
	}
	
	/**
	 * Adds a shrink or grow transition that starts immediately.
	 * Warning: does not modify the object bounds, it only resizes its sprite.
	 * @param fromScale starting scale of the sprite
	 * @param destScale final scale of the sprite
	 * @param duration
	 * @return
	 */
	public TransitionGameObject addResize(float fromScale, float destScale, float duration) {
		return addResize(fromScale, destScale, aliveTime, duration);
	}

	@Override
	public void reset(float x, float y) {
		super.reset(x, y);
		this.aliveTime = 0;
		this.scale = 1;
		this.alpha = 1;
		this.transitions.clear();
	}

	@Override
	public void update(float delta) {
		
		if (state == STATE_DEAD) 
			return;
		
		velocity.add(accel.x*delta*60, accel.y*delta*60);
		position.add(velocity.x*delta*60, velocity.y*delta*60);
		stateTime += delta;
		aliveTime += delta;
		
		// cicla tutte le transizioni e aumenta il loro timer. cambia lo stato se partono
		int len = transitions.size;
		for (int i = 0; i < len; i++) {
			
			_transition = transitions.get(i);
			
			// aggiorno solo le transizioni attive
			if (aliveTime >= _transition.startTime) {
				
				// faccio partire la transizione
				if (!_transition.running) {
					_transition.running = true;
					startTransition(_transition);
				}
				
				_transition.update(delta);
				
				// aggiorno quelle running
				if (_transition.running) {
					updateTransition(_transition, delta);
				}
			}
		}
	}
	
	protected void startTransition(Transition t) {
		
		switch (t.type) {
			
			case (Transition.TYPE_DEATH): {
				setState(STATE_DEAD);
				break;
			}
			case (Transition.TYPE_TELEPORT): {
				position.add(t.toX, t.toY);
				break;
			}
			case (Transition.TYPE_MOVE): {
				t.fromX = position.x;
				t.fromY = position.y;
				break;
			}
		}
	}
	
	protected void updateTransition(Transition t, float delta) {
		
		switch (t.type) {
			
			case (Transition.TYPE_FADEIN): {
				alpha = MathUtils.clamp(t.runningTime/t.duration, 0, 1);
				break;
			}
			case (Transition.TYPE_FADEOUT): {
				alpha = MathUtils.clamp(1-(t.runningTime/t.duration), 0, 1);
				break;
			}
			case (Transition.TYPE_ROTATION): {
				rotation += (t.speed*delta*60);
				break;
			}
			case (Transition.TYPE_MOVE): {
				float movX = (t.toX/t.duration)*t.runningTime;
				float movY = (t.toY/t.duration)*t.runningTime;
				position.set(t.fromX+movX, t.fromY+movY);
				break;
			}
			case (Transition.TYPE_RESIZE): {
				float dstScale = t.toValue-t.fromValue;
				float movScale = (dstScale/t.duration)*t.runningTime;
				scale = t.fromValue+movScale;
				break;
			}
		}
	}
	
	public class Transition {
		
		public int type;
		public boolean running;
		public float runningTime; // tempo dal quale la transazione � partita
		
		public float startTime; // istante di inizio dell'effetto
		public float duration; // durata dell'effetto
		public float speed;
		public float fromX;
		public float fromY;
		public float toX;
		public float toY;
		public float fromValue;
		public float toValue;
		
		public static final int TYPE_DEATH = 0;
		public static final int TYPE_FADEIN = 1;
		public static final int TYPE_FADEOUT = 2;
		public static final int TYPE_ROTATION = 3;
		public static final int TYPE_MOVE = 4;
		public static final int TYPE_TELEPORT = 5;
		public static final int TYPE_RESIZE = 6;
		
		public static final float DURATION_ENDLESS = -1;
		
		public Transition(int type) {
			this.type = type;
		}
		
		public void update(float delta) {
			
			if (running) {
				runningTime += delta;
			}
			
			if (duration > -1 && runningTime >= duration) {
				running = false;
			}
		}
	}
}
