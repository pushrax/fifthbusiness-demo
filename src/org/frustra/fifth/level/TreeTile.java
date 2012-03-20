package org.frustra.fifth.level;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class TreeTile extends Tile {
	public TreeTile(int id) {
		super(false, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = level.getTile(x, y - 1) != this;
		boolean d = level.getTile(x, y + 1) != this;
		
		int offset = (level.getBack(x, y) instanceof SnowTile) ? 4 : 0;
		
		if (u && d) {
			screen.render16(x * 16, y * 16, 26 + 32 * 8, 0, ColorSet.nullSetTiles);
		} else if (d) {
			screen.render16(x * 16, y * 16, 22 + 32 * 30 + offset, 0, ColorSet.nullSetTiles);
		} else if (u) {
			screen.render16(x * 16, y * 16, 22 + 32 * 28 + offset, 0, ColorSet.nullSetTiles);
		} else {
			screen.render16(x * 16, y * 16, 20 + 32 * 30 + offset, 0, ColorSet.nullSetTiles);
		}
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
