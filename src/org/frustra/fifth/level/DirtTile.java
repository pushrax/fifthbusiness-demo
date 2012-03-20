package org.frustra.fifth.level;

import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class DirtTile extends Tile {
	public DirtTile(int id) {
		super(true, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = level.getBack(x, y - 1) != this && y >= 2;
		boolean d = level.getBack(x, y + 1) != this && y < level.h - 2;
		boolean l = level.getBack(x - 1, y) != this && x >= 2;
		boolean r = level.getBack(x + 1, y) != this && x < level.w - 2;
		
		boolean ul = level.getBack(x - 1, y - 1) != this && y >= 2 && x >= 2;
		boolean dl = level.getBack(x - 1, y + 1) != this && y < level.h - 2 && x >= 2;
		boolean ur = level.getBack(x + 1, y - 1) != this && y >= 2 && x < level.w - 2;
		boolean dr = level.getBack(x + 1, y + 1) != this && y < level.h - 2 && x < level.w - 2;

		if (u) {
			if (l) {
				screen.render16(x * 16, y * 16, 6, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 10, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 8, 0, ColorSet.nullSetTiles);
			}
		} else if (d) {
			if (l) {
				screen.render16(x * 16, y * 16, 6 + 32 * 4, 0, ColorSet.nullSetTiles);
			} else if (r) {
				screen.render16(x * 16, y * 16, 10 + 32 * 4, 0, ColorSet.nullSetTiles);
			} else {
				screen.render16(x * 16, y * 16, 8 + 32 * 4, 0, ColorSet.nullSetTiles);
			}
		} else if (l) {
			screen.render16(x * 16, y * 16, 6 + 32 * 2, 0, ColorSet.nullSetTiles);
		} else if (r) {
			screen.render16(x * 16, y * 16, 10 + 32 * 2, 0, ColorSet.nullSetTiles);
		} else if (dr) {
			screen.render16(x * 16, y * 16, 28 + 32 * 12, 0, ColorSet.nullSetTiles);
		} else if (dl) {
			screen.render16(x * 16, y * 16, 30 + 32 * 12, 0, ColorSet.nullSetTiles);
		} else if (ur) {
			screen.render16(x * 16, y * 16, 28 + 32 * 14, 0, ColorSet.nullSetTiles);
		} else if (ul) {
			screen.render16(x * 16, y * 16, 30 + 32 * 14, 0, ColorSet.nullSetTiles);
		} else {
			screen.render16(x * 16, y * 16, 8 + 32 * 2, 0, ColorSet.nullSetTiles);
		}
	}
}
