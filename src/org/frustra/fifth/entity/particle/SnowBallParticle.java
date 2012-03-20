package org.frustra.fifth.entity.particle;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class SnowBallParticle extends Entity {
	private int dx, dy;
	
	public SnowBallParticle(int x, int y, int dx, int dy, int speed) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.xr = 1;
		this.yr = 3;
		this.speed = speed;
	}
	
	public void tick() {
		if (!move(dx, dy, speed)) {
			remove();
		}
	}
	
	public void render(Screen screen) {
		screen.render8((int) x - 4, (int) y - 8, 5 + 12 * 32, 0, ColorSet.nullSetEntities);
	}
}
