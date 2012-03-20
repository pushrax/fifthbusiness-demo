package org.frustra.fifth.level;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class SquareHouseTile extends Tile {
	public SquareHouseTile(int id) {
		super(false, id);
		connectsToHouse = true;
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = !level.getTile(x, y - 1).connectsToHouse;
		boolean d = !level.getTile(x, y + 1).connectsToHouse;
		boolean dd = !level.getTile(x, y + 2).connectsToHouse;
		boolean ddd = !level.getTile(x, y + 3).connectsToHouse;
		boolean l = !level.getTile(x - 1, y).connectsToHouse;
		boolean r = !level.getTile(x + 1, y).connectsToHouse;
		
		if (u) {
			if (l) {
				screen.render16(x * 16, y * 16, 20 + 32 * 18, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 30 + 32 * 18, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 26 + 32 * 18, 0, ColorSet.nullSetTiles);
			}
		} else if (d) {
			if (l) {
				screen.render16(x * 16, y * 16, 20 + 32 * 26, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 30 + 32 * 26, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 28 + 32 * 26, 0, ColorSet.nullSetTiles);
			}
		} else if (dd) {
			if (l) {
				screen.render16(x * 16, y * 16, 20 + 32 * 24, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 30 + 32 * 24, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 28 + 32 * 24, 0, ColorSet.nullSetTiles);
			}
		} else if (!ddd) {
			if (l) {
				screen.render16(x * 16, y * 16, 20 + 32 * 20, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 30 + 32 * 20, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 26 + 32 * 20, 0, ColorSet.nullSetTiles);
			}
		} else if (l) {
			screen.render16(x * 16, y * 16, 20 + 32 * 22, 0, ColorSet.nullSetTiles);
		} else if (r) {
			screen.render16(x * 16, y * 16, 30 + 32 * 22, 0, ColorSet.nullSetTiles);
		} else {
			screen.render16(x * 16, y * 16, 26 + 32 * 22, 0, ColorSet.nullSetTiles);
		}
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
