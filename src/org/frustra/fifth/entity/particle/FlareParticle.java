package org.frustra.fifth.entity.particle;

import java.util.Random;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.Timeline;
import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;
import org.frustra.fifth.scenes.Scene4;;

public class FlareParticle extends Entity {
	private int sx, sy;
	private int tx, ty;
	private boolean stopped;
	private int lr = 8 * 8, qr = 8 * 8, tickC = 0;
	
	public FlareParticle(int x, int y, int tx, int ty, int speed) {
		this.x = x;
		this.y = y;
		this.sx = x;
		this.sy = y;
		this.tx = tx;
		this.ty = ty;
		this.xr = 1;
		this.yr = 3;
		this.speed = speed;
		this.collides = false;
	}
	
	private boolean shouldStop() {
		boolean stopX = false, stopY = false;
		if (sx > tx && x < tx) stopX = true;
		else if (sx < tx && x > tx) stopX = true;
		if (sy > ty && y < ty) stopX = true;
		else if (sy < ty && y > ty) stopY = true;
		return stopX || stopY;
	}

	Random r = new Random();
	
	public void tick() {
		if (Timeline.currentScene() instanceof Scene4) {
			if (!stopped) {
				ty = FifthBusiness.dunstan.y;
				tx = FifthBusiness.dunstan.x - 8;
				int dx = sx < tx ? 1 : -1;
				int dy = (sy < ty ? 1 : -1) * (int) Math.round((double) (ty - y) / (double) (tx - x));
				move((int) dx, (int) dy, speed);
				if (shouldStop()) {
					stopped = true;
					y = FifthBusiness.dunstan.y + 4;
					x = FifthBusiness.dunstan.x - 8;
					Timeline.advanceScript();
				}
			} else if (lr > 0) {
				++tickC;
				if (tickC > 3) {
					lr -= 1;
					tickC = 0;
				}
				qr = lr + r.nextInt(4);
			} else {
				Timeline.advanceScript();
			}
		}
	}
	
	public void render(Screen screen) {
		screen.render8((int) x - 4, (int) y - 8, 5 + 12 * 32, 0, ColorSet.nullSetEntities);
	}
	
	public int getLightRadius() {
		return qr;
	}
}
