package org.frustra.fifth.entity;

import java.util.ArrayList;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Font;
import org.frustra.fifth.gfx.Screen;


public class Character extends Entity {
	protected double walkDist = 0;
	public int dir = 1;
	protected int renderTileX;
	protected int renderTileY;

	protected int targetX = -1;
	protected int targetY = -1;
	
	public String name;
	public boolean showName = true;
	public boolean fallen = false;
	public boolean lying = false;
	
	private ColorSet set;
	
	public Character(String name, int renderTileX, int renderTileY, int hairColor, int shirtColor) {
		this.name = name;
		x = y = 64;
		xr = 4;
		yr = 3;
		this.renderTileX = renderTileX;
		this.renderTileY = renderTileY;
		this.set = new ColorSet(FifthBusiness.entitySheet, ColorSet.DARK_GRAY, hairColor, ColorSet.LIGHT_GRAY, shirtColor);
	}
	
	public void reset() {
		this.shown = true;
		this.fallen = false;
		this.showName = true;
		this.speed = 10;
	}
	
	public void tick() {
		int targX = this.targetX >> 4;
		int targY = this.targetY >> 4;
		if (this.targetX != -1 && this.targetY != -1) {
			int crumb = FifthBusiness.level.getCrumb();
			ArrayList<int[]> queue = new ArrayList<int[]>();
			int tx = (int) this.x >> 4;
			int ty = (int) this.y >> 4;
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, 1, 0, this)) queue.add(new int[] {tx + 1, ty, tx + 1, ty});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, 0, 1, this)) queue.add(new int[] {tx, ty + 1, tx, ty + 1});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, 1, 1, this)) queue.add(new int[] {tx + 1, ty + 1, tx + 1, ty + 1});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, 0, -1, this)) queue.add(new int[] {tx, ty - 1, tx, ty - 1});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, -1, 0, this)) queue.add(new int[] {tx - 1, ty, tx - 1, ty});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, -1, -1, this)) queue.add(new int[] {tx - 1, ty - 1, tx - 1, ty - 1});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, -1, 1, this)) queue.add(new int[] {tx - 1, ty + 1, tx - 1, ty + 1});
			if (FifthBusiness.level.canMoveTo(crumb, tx, ty, 1, -1, this)) queue.add(new int[] {tx + 1, ty - 1, tx + 1, ty - 1});
			while (queue.size() > 0) {
				int[] here = queue.get(0);
				queue.remove(0);
				if (here[2] == targX && here[3] == targY) {
					tx = here[0];
					ty = here[1];
					break;
				}
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], 1, 0, this)) queue.add(new int[] {here[0], here[1], here[2] + 1, here[3]});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], 0, 1, this)) queue.add(new int[] {here[0], here[1], here[2], here[3] + 1});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], 1, 1, this)) queue.add(new int[] {here[0], here[1], here[2] + 1, here[3] + 1});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], 0, -1, this)) queue.add(new int[] {here[0], here[1], here[2], here[3] - 1});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], -1, 0, this)) queue.add(new int[] {here[0], here[1], here[2] - 1, here[3]});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], -1, -1, this)) queue.add(new int[] {here[0], here[1], here[2] - 1, here[3] - 1});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], -1, 1, this)) queue.add(new int[] {here[0], here[1], here[2] - 1, here[3] + 1});
				if (FifthBusiness.level.canMoveTo(crumb, here[2], here[3], 1, -1, this)) queue.add(new int[] {here[0], here[1], here[2] + 1, here[3] - 1});
			}
			int dx = (tx << 4) - (int) this.x + (this.targetX & 0xF);
			int dy = (ty << 4) - (int) this.y + (this.targetY & 0xF);
			move(dx > 0 ? 1 : (dx < 0 ? -1 : 0), dy > 0 ? 1 : (dy < 0 ? -1 : 0), speed);
		}
	}
	
	ColorSet contrastTextSet = new ColorSet(FifthBusiness.entitySheet, ColorSet.BLACK, ColorSet.TRANSPARENT);
	
	public void render(Screen screen) {
		if (!shown) return;
		int xt = renderTileX;
		int yt = renderTileY;
		
		int flip1 = ((int) walkDist >> 3) & 1;
		int flip2 = ((int) walkDist >> 3) & 1;

		int xo = (int) x - 8;
		int yo = (int) y - 11;
		
		if (fallen || lying) {
			xt += 8;
			if ((dir & 1) == 0) {
				flip1 = flip2 = 1;
			} else {
				flip1 = flip2 = 0;
			}
			if (lying) xt += 2;
			screen.render16(xo, yo, xt + yt * 32, flip2, set);
			if (showName) Font.drawSmall(name, screen, xo - (int) (name.length() * 2.5) + 8, yo, contrastTextSet);
		} else {
			if (dir == 1) {
				xt += 2;
			}
			if (dir > 1) {
				if (dir == 2) {
					flip1 = flip2 = 1;
				} else {
					flip1 = flip2 = 0;
				}
				xt += 4 + (((int) walkDist >> 3) & 1) * 2;
			}
			
			screen.render8(xo + 8 * flip1, yo + 0, xt + yt * 32, flip1, set);
			screen.render8(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, flip1, set);
			screen.render8(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, flip2, set);
			screen.render8(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, flip2, set);
			if (showName) Font.drawSmall(name, screen, xo - (int) (name.length() * 2.5) + 8, yo - 5, contrastTextSet);
		}
	}
	
	public void setTarget(int x, int y) {
		this.targetX = x;
		this.targetY = y;
	}
	
	public boolean move(int xa, int ya, int speed) {
		if (xa != 0 || ya != 0) {
			if (speed != 0) {
				walkDist += 10.0 / speed;
			}
			if (xa < 0) dir = 2;
			if (xa > 0) dir = 3;
			if (ya < 0) dir = 1;
			if (ya > 0) dir = 0;
		}
		return super.move(xa, ya, speed);
	}
	
	public boolean blocks(Entity e) {
		return e.isBlockableBy(this);
	}
}
