package org.frustra.fifth.level;

import java.util.Random;

import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class FloorTile extends Tile {
	public FloorTile(int id) {
		super(true, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		Random random = new Random(x + y * level.w);
		screen.render16(x * 16, y * 16, 28 + random.nextInt(3) + 32 * 16, 0, ColorSet.nullSetTiles);
	}
}
