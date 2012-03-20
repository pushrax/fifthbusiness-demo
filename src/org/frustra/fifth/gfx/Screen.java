package org.frustra.fifth.gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import org.frustra.fifth.FifthBusiness;


public class Screen extends Canvas {
	private static final long serialVersionUID = 1L;
	
	public int xOffset;
	public int yOffset;
	
	public static final int BIT_MIRROR_X = 0x01;
	public static final int BIT_MIRROR_Y = 0x02;
	
	public final int w, h;
	public final boolean top;
	public boolean enabled = true;
	private BufferedImage image;
	private int[] pixels;
	private int[] lightPixels;
	
	public Screen(int w, int h, boolean top) {
		this.w = w;
		this.h = h;
		this.top = top;
		setSize(w * 2, h * 2 + 10);
		
		this.image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		this.lightPixels = new int[this.pixels.length];
	}
	
	public void clearLight(int brightness) {
		for (int i = 0; i < lightPixels.length; i++) {
			lightPixels[i] = brightness;
		}
	}
	
	public boolean fadeOut = false;
	public boolean doneFadeOut = false;
	public int fadeStartTickTime = -1;
	public void reset() {
		fadeOut = false;
		doneFadeOut = false;
		fadeStartTickTime = -1;
	}
	
	public void fadeOut() {
		fadeOut = true;
		fadeStartTickTime = FifthBusiness.level.tickTime * 2;
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		synchronized (FifthBusiness.level) {
			int xScroll = (int) (top ? FifthBusiness.boy : FifthBusiness.dunstan).x - w / 2;
			int yScroll = (int) (top ? FifthBusiness.boy : FifthBusiness.dunstan).y - (h - 8) / 2;
			if (xScroll < 16) xScroll = 16;
			if (yScroll < 16) yScroll = 16;
			if (xScroll > FifthBusiness.level.w * 16 - w - 16) xScroll = FifthBusiness.level.w * 16 - w - 16;
			if (yScroll > FifthBusiness.level.h * 16 - h - 16) yScroll = FifthBusiness.level.h * 16 - h - 16;
		
			FifthBusiness.level.renderLevel(this, xScroll, yScroll);
			FifthBusiness.level.renderSprites(this, xScroll, yScroll);
			
			int fadeAmount = fadeOut ? ((FifthBusiness.level.tickTime * 2) - fadeStartTickTime) : Math.max(0, 255 - FifthBusiness.level.tickTime * 2);
			// FifthBusiness.level.brightness - FifthBusiness.level.tickTime * 2
			if (fadeOut && fadeAmount >= 255) doneFadeOut = true;
			clearLight(FifthBusiness.level.brightness);
			FifthBusiness.level.renderLight(this, xScroll, yScroll);
			for (int i = 0; i < this.lightPixels.length; i++) {
				int brightness = 255 - this.lightPixels[i] + fadeAmount;
				if (brightness > 255) brightness = 255;
				for (int off = 0; off < 4; off++) {
					int tmp = (this.pixels[i] >> (off * 8)) & 0xFF;
					this.pixels[i] ^= (tmp << (off * 8));
					tmp -= brightness;
					if (tmp < 0) tmp = 0;
					tmp <<= (off * 8);
					this.pixels[i] |= tmp;
				}
			}
		}
		
		if (!top && !hasFocus() && !FifthBusiness.quotesCanvas.hasFocus() && !FifthBusiness.screenTop.hasFocus()) renderFocusNagger();

		if (!this.isVisible()) return;
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int ww = w * 2;
		int hh = h * 2;
		int xo = (getWidth() - ww) / 2;
		if (enabled) {
			g.drawImage(image, xo, 5, ww, hh, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(xo, 5, ww, hh);
		}
		g.dispose();
		bs.show();
	}
	
	private void renderFocusNagger() {
		String msg = "Click to play!";
		int xx = (w - msg.length() * 8) / 2;
		int yy = (h - 8) / 2;
		int w = msg.length();
		int h = 1;

		render8(xx - 8, yy - 8, 416, 0, Font.frameSet);
		render8(xx + w * 8, yy - 8, 416, 1, Font.frameSet);
		render8(xx - 8, yy + 8, 416, 2, Font.frameSet);
		render8(xx + w * 8, yy + 8, 416, 3, Font.frameSet);
		for (int x = 0; x < w; x++) {
			render8(xx + x * 8, yy - 8, 417, 0, Font.frameSet);
			render8(xx + x * 8, yy + 8, 417, 2, Font.frameSet);
		}
		for (int y = 0; y < h; y++) {
			render8(xx - 8, yy + y * 8, 418, 0, Font.frameSet);
			render8(xx + w * 8, yy + y * 8, 418, 1, Font.frameSet);
		}
		
		Font.draw(msg, this, xx, yy, Font.frameTextSet);
	}
	
	public void render8(int xp, int yp, int tile, int bits, ColorSet set) {
		render(xp, yp, tile, 8, bits, set);
	}
	
	public void render16(int xp, int yp, int tile, int bits, ColorSet set) {
		render(xp, yp, tile, 16, bits, set);
	}
	
	public void render(int xp, int yp, int tile, int dimensions, int bits, ColorSet set) {
		xp -= xOffset;
		yp -= yOffset;
		boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
		boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;
		
		int xTile = tile % 32;
		int yTile = tile / 32;
		int toffs = xTile * 8 + yTile * 8 * set.width;
		
		for (int y = 0; y < dimensions; y++) {
			int ys = y;
			if (mirrorY) ys = dimensions - 1 - y;
			if (y + yp < 0 || y + yp >= h) continue;
			for (int x = 0; x < dimensions; x++) {
				if (x + xp < 0 || x + xp >= w) continue;
				
				int xs = x;
				if (mirrorX) xs = dimensions - 1 - x;
				int col = set.pixels[xs + ys * set.width + toffs];
				if ((col & ColorSet.TRANSPARENT_MASK) != 0) {
					pixels[(x + xp) + (y + yp) * w] = col;
				}
			}
		}
	}
	
	public void renderTextContrast(int xp, int yp, int tile, int dimensions, int bits, ColorSet set) {
		xp -= xOffset;
		yp -= yOffset;
		boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
		boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;
		
		int xTile = tile % 32;
		int yTile = tile / 32;
		int toffs = xTile * 8 + yTile * 8 * set.width;
		
		int color = 0xFFFFFFFF - pixels[Math.min(w - 1, Math.max(0, (dimensions / 2) + xp)) + Math.min(h - 1, Math.max(0, (dimensions / 2) + yp)) * w];
		
		for (int y = 0; y < dimensions; y++) {
			int ys = y;
			if (mirrorY) ys = dimensions - 1 - y;
			if (y + yp < 0 || y + yp >= h) continue;
			for (int x = 0; x < dimensions; x++) {
				if (x + xp < 0 || x + xp >= w) continue;
				
				int xs = x;
				if (mirrorX) xs = dimensions - 1 - x;
				int col = set.pixels[xs + ys * set.width + toffs];
				if ((col & ColorSet.TRANSPARENT_MASK) != 0) {
					pixels[(x + xp) + (y + yp) * w] = color;
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void renderLight(int x, int y, int r) {
		x -= xOffset;
		y -= yOffset;
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;
		
		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > w) x1 = w;
		if (y1 > h) y1 = h;
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int xd = xx - x;
				int dist = xd * xd + yd;
				if (dist <= r * r) {
					int br = 255 - dist * 255 / (r * r);
					lightPixels[xx + yy * w] += br / 1.2;
					lightPixels[xx + yy * w] = Math.min(255, lightPixels[xx + yy * w]); // Comment this line for fun =D
				}
			}
		}
	}
}
