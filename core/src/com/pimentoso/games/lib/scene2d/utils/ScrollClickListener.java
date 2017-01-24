package com.pimentoso.games.lib.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * ClickListener that correctly avoids accidental clicks while inside a ScrollPane.
 *
 * @author Pimentoso
 */
public class ScrollClickListener extends InputListener {

	private boolean dragged = false;
	private float lastY = 0;

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		dragged = false;
		lastY = y;
		return true;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		if (y < lastY - 25 || y > lastY + 25) {
			dragged = true;
		}
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		if (!dragged) {
			clicked(event, x, y);
		}
	}

	public void clicked (InputEvent event, float x, float y) {
	}
}
