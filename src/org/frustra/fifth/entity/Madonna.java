package org.frustra.fifth.entity;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class Madonna extends Entity {
	public void render(Screen screen) {
		screen.render16(x - 8, y - 12, 4 + 32 * 34, 0, ColorSet.nullSetTiles);
	}
}
