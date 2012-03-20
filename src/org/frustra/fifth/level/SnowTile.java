package org.frustra.fifth.level;

import java.util.Random;

import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class SnowTile extends Tile {
	public SnowTile(int id) {
		super(true, id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		Random random = new Random(x + y * level.w);
		screen.render16(x * 16, y * 16, 26, random.nextInt(3), ColorSet.nullSetTiles);
	}
}
