package org.frustra.fifth.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	public int width, height;
	public int[] pixels;
	
	public SpriteSheet(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}
}
