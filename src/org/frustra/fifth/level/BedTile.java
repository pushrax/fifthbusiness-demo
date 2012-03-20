package org.frustra.fifth.level;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class BedTile extends Tile {
	public BedTile(int id) {
		super(false, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = level.getTile(x, y - 1) != this;
		boolean d = level.getTile(x, y + 1) != this;
		boolean l = level.getTile(x - 1, y) != this;
		boolean r = level.getTile(x + 1, y) != this;
		
		if (u) {
			if (l) {
				screen.render16(x * 16, y * 16, 28 + 32 * 28, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 30 + 32 * 28, 0, ColorSet.nullSetTiles);
			}
		} else if (d) {
			if (l) {
				screen.render16(x * 16, y * 16, 28 + 32 * 30, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 30 + 32 * 30, 0, ColorSet.nullSetTiles);
			}
		}
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
