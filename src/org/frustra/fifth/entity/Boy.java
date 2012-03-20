package org.frustra.fifth.entity;

import org.frustra.fifth.FifthBusiness;

public class Boy extends Character {
	public boolean following = false;
	
	public Boy() {
		super("Percy", 0, 14, 0xFF271010, 0xFF6565BA);
	}
	
	public void tick() {
		super.tick();
		if (following) {
			if (Math.hypot(x - FifthBusiness.dunstan.x, y - FifthBusiness.dunstan.y) > 64) {
				this.setTarget((FifthBusiness.dunstan.x & 0xFFFFFFF0) + 8, (FifthBusiness.dunstan.y & 0xFFFFFFF0) + 10);
			} else this.setTarget(-1, -1);
		}
	}
	
	public void reset() {
		super.reset();
		this.following = false;
	}
	
	public int getLightRadius() {
		return showLight ? 5 * 8 : 0;
	}
	
	public boolean move(int xa, int ya, int speed) {
		boolean ret = super.move(xa, ya, speed);
		int xt = (int) x >> 4;
		int yt = (int) y >> 4;
		if (xt <= 0 || xt >= level.w - 1 || yt <= 0 || yt >= level.h - 1) {
			shown = false;
		}
		return ret;
	}
}
