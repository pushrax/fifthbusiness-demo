package org.frustra.fifth.level;

import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class RoadTile extends Tile {
	public RoadTile(int id) {
		super(false, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = level.getTile(x, y - 1) != this && y >= 2;
		boolean d = level.getTile(x, y + 1) != this && y < level.h - 2;
		boolean l = level.getTile(x - 1, y) != this && x >= 2;
		boolean r = level.getTile(x + 1, y) != this && x < level.w - 2;
		
		boolean ul = level.getTile(x - 1, y - 1) != this && y >= 2 && x >= 2;
		boolean dl = level.getTile(x - 1, y + 1) != this && y < level.h - 2 && x >= 2;
		boolean ur = level.getTile(x + 1, y - 1) != this && y >= 2 && x < level.w - 2;
		boolean dr = level.getTile(x + 1, y + 1) != this && y < level.h - 2 && x < level.w - 2;

		if (u) {
			if (l) {
				screen.render16(x * 16, y * 16, 6 + 32 * 12, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 10 + 32 * 12, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 8 + 32 * 12, 0, ColorSet.nullSetTiles);
			}
		} else if (d) {
			if (l) {
				screen.render16(x * 16, y * 16, 6 + 32 * 16, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 10 + 32 * 16, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 8 + 32 * 16, 0, ColorSet.nullSetTiles);
			}
		} else if (l) {
			screen.render16(x * 16, y * 16, 6 + 32 * 14, 0, ColorSet.nullSetTiles);
		} else if (r) {
			screen.render16(x * 16, y * 16, 10 + 32 * 14, 0, ColorSet.nullSetTiles);
		} else if (dr) {
			screen.render16(x * 16, y * 16, 28 + 32 * 4, 0, ColorSet.nullSetTiles);
		} else if (dl) {
			screen.render16(x * 16, y * 16, 30 + 32 * 4, 0, ColorSet.nullSetTiles);
		} else if (ur) {
			screen.render16(x * 16, y * 16, 28 + 32 * 6, 0, ColorSet.nullSetTiles);
		} else if (ul) {
			screen.render16(x * 16, y * 16, 30 + 32 * 6, 0, ColorSet.nullSetTiles);
		} else {
			screen.render16(x * 16, y * 16, 8 + 32 * 14, 0, ColorSet.nullSetTiles);
		}
	}
}
