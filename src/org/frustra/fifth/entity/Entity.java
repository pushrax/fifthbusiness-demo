package org.frustra.fifth.entity;

import java.util.List;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.gfx.Screen;
import org.frustra.fifth.level.Level;
import org.frustra.fifth.level.Tile;

public class Entity {
	public int speed = 10;
	public int x, y;
	public int xr = 6;
	public int yr = 6;
	public boolean shown = true;
	public boolean removed;
	public Level level;
	public boolean showLight = true;
	public boolean collides = true;
	
	public void render(Screen screen) {
	}
	
	public void tick() {
	}
	
	public void remove() {
		removed = true;
	}
	
	public final void init(Level level) {
		this.level = level;
	}
	
	public boolean intersects(int x0, int y0, int x1, int y1) {
		return !(x + xr < x0 || y + yr < y0 || x - xr > x1 || y - yr > y1);
	}
	
	public boolean blocks(Entity e) {
		return false;
	}
	
	public void hurt(Character mob, int dmg, int attackDir) {
	}
	
	public void hurt(Tile tile, int x, int y, int dmg) {
	}
	
	public boolean move(int xa, int ya) {
		return move(xa, ya, 1);
	}
	
	public boolean move(int xa, int ya, int speed) {
		if (speed <= 10 || (FifthBusiness.level.tickTime * 10) % speed == 0) {
			if (xa != 0 || ya != 0) {
				if (speed < 10 && speed > 0) {
					xa *= 10 / speed;
					ya *= 10 / speed;
				}
				boolean stopped = true;
				if (xa != 0 && move2(xa, 0)) stopped = false;
				if (ya != 0 && move2(0, ya)) stopped = false;
				return !stopped;
			}
			return true;
		} else return false;
	}
	
	protected boolean move2(int xa, int ya) {
		if (xa != 0 && ya != 0) throw new IllegalArgumentException("Move2 can only move along one axis at a time!");
		
		if (collides) {
			int xto0 = (x - xr) >> 4;
			int yto0 = (y - yr) >> 4;
			int xto1 = (x + xr) >> 4;
			int yto1 = (y + yr) >> 4;
			
			int xt0 = ((x + xa) - xr) >> 4;
			int yt0 = ((y + ya) - yr) >> 4;
			int xt1 = ((x + xa) + xr) >> 4;
			int yt1 = ((y + ya) + yr) >> 4;
			boolean blocked = false;
			for (int yt = yt0; yt <= yt1; yt++)
				for (int xt = xt0; xt <= xt1; xt++) {
					if (xt >= xto0 && xt <= xto1 && yt >= yto0 && yt <= yto1) continue;
					if (!level.getTile(xt, yt).mayPass(level, xt, yt, this)) {
						blocked = true;
						return false;
					}
				}
			if (blocked) return false;
			
			List<Entity> wasInside = level.getEntities(x - xr, y - yr, x + xr, y + yr);
			List<Entity> isInside = level.getEntities(x + xa - xr, y + ya - yr, x + xa + xr, y + ya + yr);
			for (int i = 0; i < isInside.size(); i++) {
				Entity e = isInside.get(i);
				if (e == this) continue;
				
				e.touchedBy(this);
			}
			isInside.removeAll(wasInside);
			for (int i = 0; i < isInside.size(); i++) {
				Entity e = isInside.get(i);
				if (e == this) continue;
				
				if (e.blocks(this)) {
					return false;
				}
			}
		}
		
		x += xa;
		y += ya;
		return true;
	}
	
	protected void touchedBy(Entity entity) {
	}
	
	public boolean isBlockableBy(Character mob) {
		return true;
	}
	
	public boolean canSwim() {
		return false;
	}
	
	public boolean use(Character player, int attackDir) {
		return false;
	}
	
	public int getLightRadius() {
		return 0;
	}
}
