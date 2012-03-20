package org.frustra.fifth.entity.particle;

import java.util.Random;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.ColorSet;
import org.frustra.fifth.gfx.Font;
import org.frustra.fifth.gfx.Screen;

public class TextParticle extends Entity {
	private String msg;
	private ColorSet set;
	private int time = 0;
	public double xa, ya, za;
	public double xx, yy, zz;
	
	public TextParticle(String msg, int x, int y, ColorSet set) {
		this.msg = msg;
		this.x = x;
		this.y = y;
		this.set = set;
		xx = x;
		yy = y;
		zz = 2;
		Random random = new Random();
		xa = random.nextGaussian() * 0.3;
		ya = random.nextGaussian() * 0.2;
		za = random.nextFloat() * 0.7 + 2;
	}
	
	public void tick() {
		time++;
		if (time > 60) {
			remove();
		}
		xx += xa;
		yy += ya;
		zz += za;
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.6;
			ya *= 0.6;
		}
		za -= 0.15;
		x = (int) xx;
		y = (int) yy;
	}
	
	public void render(Screen screen) {
		// Font.draw(msg, screen, x - msg.length() * 4, y, Color.get(-1, 0, 0, 0));
		Font.draw(msg, screen, (int) x - msg.length() * 4 + 1, (int) y - (int) (zz) + 1, set);
		Font.draw(msg, screen, (int) x - msg.length() * 4, (int) y - (int) (zz), set);
	}
	
}
