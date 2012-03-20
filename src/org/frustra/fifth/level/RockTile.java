package org.frustra.fifth.level;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class RockTile extends Tile {
	private static ColorSet set = new ColorSet(FifthBusiness.entitySheet, ColorSet.DARK_GRAY, 0xFFc4c4c4, ColorSet.LIGHT_GRAY, 0xFF949494);
	
	public RockTile(int id) {
		super(false, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		boolean u = level.getTile(x, y - 1) != this;
		boolean d = level.getTile(x, y + 1) != this;
		boolean l = level.getTile(x - 1, y) != this;
		boolean r = level.getTile(x + 1, y) != this;
		
		boolean ul = level.getTile(x - 1, y - 1) != this;
		boolean dl = level.getTile(x - 1, y + 1) != this;
		boolean ur = level.getTile(x + 1, y - 1) != this;
		boolean dr = level.getTile(x + 1, y + 1) != this;

		if (!u && !l) {
			if (!ul)
				screen.render8(x * 16 + 0, y * 16 + 0, 0, 0, set);
			else screen.render8(x * 16 + 0, y * 16 + 0, 7 + 0 * 32, 3, ColorSet.nullSetEntities);
		} else screen.render8(x * 16 + 0, y * 16 + 0, (l ? 6 : 5) + (u ? 2 : 1) * 32, 3, ColorSet.nullSetEntities);
		
		if (!u && !r) {
			if (!ur)
				screen.render8(x * 16 + 8, y * 16 + 0, 1, 0, set);
			else screen.render8(x * 16 + 8, y * 16 + 0, 8 + 0 * 32, 3, ColorSet.nullSetEntities);
		} else screen.render8(x * 16 + 8, y * 16 + 0, (r ? 4 : 5) + (u ? 2 : 1) * 32, 3, ColorSet.nullSetEntities);
		
		if (!d && !l) {
			if (!dl)
				screen.render8(x * 16 + 0, y * 16 + 8, 2, 0, set);
			else screen.render8(x * 16 + 0, y * 16 + 8, 7 + 1 * 32, 3, ColorSet.nullSetEntities);
		} else screen.render8(x * 16 + 0, y * 16 + 8, (l ? 6 : 5) + (d ? 0 : 1) * 32, 3, ColorSet.nullSetEntities);
		if (!d && !r) {
			if (!dr)
				screen.render8(x * 16 + 8, y * 16 + 8, 3, 0, set);
			else screen.render8(x * 16 + 8, y * 16 + 8, 8 + 1 * 32, 3, ColorSet.nullSetEntities);
		} else screen.render8(x * 16 + 8, y * 16 + 8, (r ? 4 : 5) + (d ? 0 : 1) * 32, 3, ColorSet.nullSetEntities);
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
