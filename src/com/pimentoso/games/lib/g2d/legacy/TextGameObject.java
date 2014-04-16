package com.pimentoso.games.lib.g2d.legacy;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A {@link TransitionGameObject} that contains a text string
 * instead of a sprite. Will be rendered using a {@link BitmapFont}.
 * 
 * @author IG07588
 *
 */
public class TextGameObject extends TransitionGameObject {
	
	public BitmapFont font;
	public String text;
	public HAlignment textAlign;
	public float textAlignWidth;
	private TextBounds _textBounds;

	public TextGameObject(BitmapFont font, String text, float x, float y) {
		super(null, x, y);
		this.font = font;
		this.text = text;
		_textBounds = font.getBounds(text);
	}
	
	public TextGameObject(BitmapFont font, String text, float x, float y, float alignWidth, HAlignment align) {
		super(null, x, y);
		this.font = font;
		this.text = text;
		this.textAlign = align;
		this.textAlignWidth = alignWidth;
		_textBounds = font.getBounds(text);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (state == STATE_DEAD)
			return;
		font.setScale(scale);
		font.setColor(1, 1, 1, alpha);
		if (textAlign == null) {
			font.draw(batch, text, position.x, position.y+(_textBounds.height/2));
		}
		else {
			font.drawMultiLine(batch, text, position.x-(textAlignWidth/2), position.y+(_textBounds.height/2), 
				textAlignWidth, textAlign);
		}
		font.setColor(1, 1, 1, 1);
		font.setScale(1);
	}

	/**
	 * A {@link TextGameObject} cannot have rotation transitions.
	 */
	@Override
	public TransitionGameObject addRotation(float speed, float time, float duration) {
		throw new IllegalStateException("A TextDynamicGameObject cannot have rotation transitions.");
	}
	
	/**
	 * A {@link TextGameObject} cannot have rotation transitions.
	 */
	@Override
	public TransitionGameObject addRotation(float speed, float time) {
		throw new IllegalStateException("A TextDynamicGameObject cannot have rotation transitions.");
	}
}
