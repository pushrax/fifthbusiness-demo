package org.frustra.fifth.level;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class WallTile extends Tile {
	public WallTile(int id) {
		super(false, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = level.getTile(x, y - 1) != this && y >= 2;
		boolean d = level.getTile(x, y + 1) != this && y < level.h - 2;
		boolean dd = level.getTile(x, y + 2) != this && y < level.h - 2;
		boolean l = level.getTile(x - 1, y) != this && x >= 2;
		boolean r = level.getTile(x + 1, y) != this && x < level.w - 2;
		
		boolean ul = level.getTile(x - 1, y - 1) != this && y >= 2 && x >= 2;
		boolean dl = level.getTile(x - 1, y + 1) != this && y < level.h - 2 && x >= 2;
		boolean ddl = level.getTile(x - 1, y + 2) != this && y < level.h - 2 && x >= 2;
		boolean ur = level.getTile(x + 1, y - 1) != this && y >= 2 && x < level.w - 2;
		boolean dr = level.getTile(x + 1, y + 1) != this && y < level.h - 2 && x < level.w - 2;
		boolean ddr = level.getTile(x + 1, y + 2) != this && y < level.h - 2 && x < level.w - 2;

		if (u) {
			if (l) {
				screen.render16(x * 16, y * 16, 32 * 34, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 2 + 32 * 34, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 1 + 32 * 34, 0, ColorSet.nullSetTiles);
			}
		} else if (d) {
			if (l) {
				screen.render16(x * 16, y * 16, 4 + 32 * 42, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 5 + 32 * 42, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, (x % 5 == 0 ? 2 : 0) + 32 * 38, 0, ColorSet.nullSetTiles);
			}
		} else if (dd) {
			if (dl) {
				screen.render16(x * 16, y * 16, 4 + 32 * 40, 0, ColorSet.nullSetTiles);
			} else if (dr) {
				screen.render16(x * 16, y * 16, 5 + 32 * 40, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, (x % 5 == 0 ? 2 : 0) + 32 * 36, 0, ColorSet.nullSetTiles);
			}
		} else if (l || dl) {
			screen.render16(x * 16, y * 16, 4 + 32 * 38, 0, ColorSet.nullSetTiles);
		} else if (r || dr) {
			screen.render16(x * 16, y * 16, 5 + 32 * 38, 0, ColorSet.nullSetTiles);
		} else if (!dr && ddr) {
			screen.render16(x * 16, y * 16, 5 + 32 * 36, 0, ColorSet.nullSetTiles);
		} else if (!dl && ddl) {
			screen.render16(x * 16, y * 16, 4 + 32 * 36, 0, ColorSet.nullSetTiles);
		} else if (ur) {
			screen.render16(x * 16, y * 16, 9 + 32 * 36, 0, ColorSet.nullSetTiles);
		} else if (ul) {
			screen.render16(x * 16, y * 16, 7 + 32 * 36, 0, ColorSet.nullSetTiles);
		} else {
			screen.render16(x * 16, y * 16, 8 + 32 * 36, 0, ColorSet.nullSetTiles);
		}
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
