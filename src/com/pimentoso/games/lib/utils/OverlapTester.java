package com.pimentoso.games.lib.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OverlapTester
{
	private static final Vector2 temp1 = new Vector2();
	private static final Vector2 temp2 = new Vector2();
	
	public static boolean overlapRectangles(Rectangle r1, Rectangle r2)
	{
		if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y)
			return true;
		else
			return false;
	}

	public static boolean pointInRectangle(Rectangle r, Vector2 p)
	{
		return r.x <= p.x && r.x + r.width >= p.x && r.y <= p.y && r.y + r.height >= p.y;
	}

	public static boolean pointInRectangle(Rectangle r, Vector3 p)
	{
		return r.x <= p.x && r.x + r.width >= p.x && r.y <= p.y && r.y + r.height >= p.y;
	}

	public static boolean pointInRectangle(Rectangle r, float x, float y)
	{
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	
	public static boolean overlapCircles(Circle c1, Circle c2) {

		temp1.set(c1.x, c1.y);
		temp2.set(c2.x, c2.y);
		
		float distance = temp1.dst2(temp2);
		float radiusSum = c1.radius + c2.radius;
		return distance <= radiusSum * radiusSum;
	}
	
	public static boolean overlapCircleRectangle(Circle c, Rectangle r) {

		temp1.set(c.x, c.y);
		
		float closestX = temp1.x;
		float closestY = temp1.y;

		if (temp1.x < r.x) {
			closestX = r.x;
		}
		else if (temp1.x > r.x + r.width) {
			closestX = r.x + r.width;
		}

		if (temp1.y < r.y) {
			closestY = r.y;
		}
		else if (temp1.y > r.y + r.height) {
			closestY = r.y + r.height;
		}
		
		temp2.set(closestX, closestY);

		return temp1.dst2(temp2) < c.radius * c.radius;
	}
	
	public static boolean pointInCircle(Circle c, Vector2 p) {

		return c.contains(p);
	}

	public static boolean pointInCircle(Circle c, float x, float y) {

		return c.contains(x, y);
	}
}