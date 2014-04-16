package com.pimentoso.games.lib.box2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Scaling;
import com.pimentoso.games.lib.scene2d.StaticImage;
import com.pimentoso.games.lib.utils.BodyEditorLoader;

/**
 * Generic {@link StaticImage} actor linked to a Box2d body.
 * This actor automatically follows its Box2d body when act() is called.
 * @author Pimentoso
 */
public class Box2dActor extends StaticImage implements Poolable, Disposable {

	public static final Filter FILTER = new Filter();
	
	public Body body;
	public boolean hit;
	public int coins;
	
	private World world;
	
	public Box2dActor(World world) {
		this.world = world;
	}
	
	public void init(TextureRegion region, float x, float y, float width, float height, float density) {
		
		PolygonShape poly = null;
		BodyDef bodyDef = null;
		
		poly = Pools.get(PolygonShape.class).obtain();
		poly.setAsBox(width/2, height/2);
		
		bodyDef = Pools.get(BodyDef.class).obtain();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		
		body = world.createBody(bodyDef);
		body.setUserData(this);
		Fixture fix = body.createFixture(poly, density);
		fix.setFilterData(FILTER);
		
		Pools.get(PolygonShape.class).free(poly);
		Pools.get(BodyDef.class).free(bodyDef);
		
		if (getDrawable() == null) {
			setDrawable(new TextureRegionDrawable(region));
		}
		else {
			TextureRegionDrawable drawable = (TextureRegionDrawable) getDrawable();
			drawable.setRegion(region);
		}
		
		coins = 1;
		setPosition(body.getPosition().x-(width/2), body.getPosition().y-(height/2));
		setSize(width, height);
		setScaling(Scaling.stretch);
		setAlign(Align.center);
		invalidate();
	}
	
	public void init(TextureRegion region, BodyEditorLoader loader, String name, float x, float y, float width, float height, float density) {
				
		BodyDef bodyDef = Pools.get(BodyDef.class).obtain();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
	 
	    FixtureDef fixtureDef = Pools.get(FixtureDef.class).obtain();
	    fixtureDef.density = density;
	    fixtureDef.friction = 0.5f;
	    fixtureDef.restitution = 0.5f;
	    fixtureDef.filter.categoryBits = FILTER.categoryBits;
	    fixtureDef.filter.maskBits = FILTER.maskBits;
	 
	    body = world.createBody(bodyDef);
	    body.setActive(true);
		body.setUserData(this);
	    loader.attachFixture(body, name, fixtureDef, width);
	    
	    Pools.get(FixtureDef.class).free(fixtureDef);
	    // Pools.get(BodyDef.class).free(bodyDef); XXX scommentare?
		
		if (getDrawable() == null) {
			setDrawable(new TextureRegionDrawable(region));
		}
		else {
			TextureRegionDrawable drawable = (TextureRegionDrawable) getDrawable();
			drawable.setRegion(region);
		}
		
		coins = 1;
		setPosition(body.getPosition().x, body.getPosition().y);
		setSize(width, height);
		setScaling(Scaling.fillX);
		setAlign(Align.center);
		invalidate();
	}

	@Override
	public void reset() {
		hit = false;
		body.setActive(false);
		clearActions();
		remove();
	}

	@Override
	public void dispose() {
		world.destroyBody(body);
		clearActions();
		remove();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		setRotation(MathUtils.radiansToDegrees * body.getAngle());
		setPosition(body.getPosition().x, body.getPosition().y);
	}
}
