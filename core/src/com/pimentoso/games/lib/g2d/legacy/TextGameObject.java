package com.pimentoso.games.lib.g2d.legacy;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
	public int textAlign;
	public float textAlignWidth;
	private GlyphLayout _textBounds;

	public TextGameObject(BitmapFont font, String text, float x, float y) {
		super(null, x, y);
		this.font = font;
		this.text = text;
		_textBounds = new GlyphLayout();
        _textBounds.setText(font, text);
	}
	
	public TextGameObject(BitmapFont font, String text, float x, float y, float alignWidth, int align) {
		super(null, x, y);
		this.font = font;
		this.text = text;
		this.textAlign = align;
		this.textAlignWidth = alignWidth;
        _textBounds = new GlyphLayout();
        _textBounds.setText(font, text);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (state == STATE_DEAD)
			return;
		font.getData().setScale(scale);
		font.setColor(1, 1, 1, alpha);
		if (textAlign == 0) {
			font.draw(batch, text, position.x, position.y+(_textBounds.height/2));
        }
        else {
            font.draw(batch, text, position.x - (textAlignWidth/2), position.y+(_textBounds.height/2),
				textAlignWidth, textAlign, true);
		}
		font.setColor(1, 1, 1, 1);
        font.getData().setScale(1);
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
