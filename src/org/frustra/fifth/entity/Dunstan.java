package org.frustra.fifth.entity;

import org.frustra.fifth.FifthBusiness;

public class Dunstan extends Character {
	public boolean movable = true;
	
	public Dunstan() {
		super("Dunstable", 0, 14, 0xFF271010, 0xFF5C5E37);
	}
	
	public void tick() {
		super.tick();
		if (movable && (this.targetX == -1 || this.targetY == -1)) {
			int dx = FifthBusiness.input.left ? -1 : 0;
			if (FifthBusiness.input.right) dx++; 
			int dy = FifthBusiness.input.up ? -1 : 0;
			if (FifthBusiness.input.down) dy++; 
			if (dx != 0 || dy != 0) move(dx, dy, speed);
		}
	}
	
	public void reset() {
		super.reset();
		this.movable = true;
	}
	
	public int getLightRadius() {
		return showLight ? 7 * 8 : 0;
	}
}
