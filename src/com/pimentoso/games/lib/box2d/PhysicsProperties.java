package com.pimentoso.games.lib.box2d;

/**
 * Container for various properties to apply to the box2d body. 
 * @author Pimentoso
 */
public class PhysicsProperties {
	
	/**
	 * Box2d collision filter category. Available values:
	 * 0x0001		0x0002		0x0004		0x0008
	 * 0x0010		0x0020		0x0040		0x0080
	 * 0x0100		0x0200		0x0400		0x0800
	 * 0x1000		0x2000		0x4000		0x8000
	 */
	public short filterCategory;
	
	/**
	 * Box2d collision filter mask. 
	 * Value shoud be all categories which the object should collide with, separated by OR.
	 * Example: 0x0002 | 0x0004
	 */
	public short filterMask;
	
	/**
	 * Box2d fixture density. 
	 * Expressed in kg per m^2
	 */
	public float density;
	
	/**
	 * Box2d fixture friction value. 
	 * 0: no friction, 1: high friction 
	 */
	public float friction;
	
	/**
	 * Box2d fixture restitution value.
	 * 0: no bounce, 1: perfect bounce 
	 */
	public float restitution;
	
	public PhysicsProperties(short filterCategory, short filterMask, float density, float friction, float restitution) {
		this.filterCategory = filterCategory;
		this.filterMask = filterMask;
		this.density = density;
		this.friction = friction;
		this.restitution = restitution;
	}
}
