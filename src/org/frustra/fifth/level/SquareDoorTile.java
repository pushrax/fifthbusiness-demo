package org.frustra.fifth.level;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class SquareDoorTile extends HouseTile {
	public SquareDoorTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean d = level.getTile(x, y + 1) != this;
		boolean dd = level.getTile(x, y + 2) != this;
		
		if (d) {
			screen.render16(x * 16, y * 16, 24 + 32 * 26, 0, ColorSet.nullSetTiles);
		} else if (dd) {
			screen.render16(x * 16, y * 16, 24 + 32 * 24, 0, ColorSet.nullSetTiles);
		}
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
