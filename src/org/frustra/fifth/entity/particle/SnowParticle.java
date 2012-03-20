package org.frustra.fifth.entity.particle;

import java.util.Random;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Screen;

public class SnowParticle extends Entity {
	private static Random random = new Random();
	private int time = 0;
	private int type;
	private int flip = 0;
	public int tempX;
	public int tempY;
	private static ColorSet set = new ColorSet(FifthBusiness.entitySheet, ColorSet.DARK_GRAY, ColorSet.TRANSPARENT, ColorSet.LIGHT_GRAY, ColorSet.WHITE);
	
	public SnowParticle(int x, int y) {
		this((int) y - FifthBusiness.HEIGHT / 4 - 4, x, y);
	}
	
	public SnowParticle(int sy, int x, int y) {
		this.x = x;
		this.y = y;
		this.tempX = 0;
		this.tempY = sy;
		this.type = random.nextInt(4);
		int flipT = random.nextInt(4);
		if ((flipT & 1) != 0) {
			this.flip |= Screen.BIT_MIRROR_X;
		}
		if ((flipT & 2) != 0) {
			this.flip |= Screen.BIT_MIRROR_Y;
		}
	}
	
	public void tick() {
		if (this.tempY < this.y) {
			this.tempY++;
			this.tempX = (int) (this.x + Math.sin((this.y - this.tempY) / Math.PI / 5) * 5);
		}
		time++;
		if (time > 200) {
			remove();
		}
	}
	
	public void render(Screen screen) {
		screen.render8(tempX - 4, tempY - 4, type, flip, set);
	}
}
