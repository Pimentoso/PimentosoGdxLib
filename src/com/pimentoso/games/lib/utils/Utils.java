package com.pimentoso.games.lib.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;

public class Utils {
	
	public static final StringBuilder sb = new StringBuilder();
	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Interpola un valore compreso fra un massimo e un minimo secondo una {@link Interpolation}.
	 * @param i l'interpolazione da usare
	 * @param min valore minimo
	 * @param value valore da interpolare (0,1)
	 * @param max valore massimo
	 * @return valore interpolato
	 */
	public static float interpolate(Interpolation i, float min, float value, float max) {
		return (i.apply(value) * (max-min)) + min;
	}
	
	public static CharSequence prettyTime(float seconds) {

		int input = (int)seconds;
		
		int sec = input%60;
		int min = input/60%60;
		int hr = input/3600%24;
		int day = input/86400;
		
		sb.setLength(0);
		if (day > 0) sb.append(day).append("day ");
		if (hr > 0 || day > 0) sb.append(hr).append("hour ");
		if (min > 0 || day > 0 || hr > 0) sb.append(min).append("min ");
		sb.append(sec).append("sec");
		
		return sb;
	}
	
	public static String getTodayDate() {
		return format.format(new Date());
	}
	
	/**
	 * Copied from StringUtils.isEmpty()
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	public static boolean isNotEmpty(final CharSequence cs) {
		return !Utils.isEmpty(cs);
	}
	
	/**
	 * Create a single pixel texture from a color.
	 */
	public static Texture createTexturePixel(Color c) {
		Pixmap map = new Pixmap(2, 2, Format.RGBA8888);
		map.setColor(c);
		map.fillRectangle(0, 0, 2, 2);
		Texture retorno = new Texture(map);
		retorno.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		map.dispose();
		return retorno;
	}
	
	public static TextureRegion createTextureRegionPixel(Color c) {
		return new TextureRegion(createTexturePixel(c), 2, 2);
	}

	
	public static void main(String... args) {
		Interpolation intrp = Interpolation.circleIn;
		final int steps = 50;
		final float step = 1/(float) steps;
		float val = 0;
		
		for (int i = 0; i < steps*2; i++) {
			float result = intrp.apply(val);
			val += step;
			
			for (int j = 0; j < result*100; j++) {
				System.out.print("0");
			}
			System.out.println();
		}
	}
}
