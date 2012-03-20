package org.frustra.fifth.gfx;

import java.util.Arrays;

import org.frustra.fifth.FifthBusiness;

public class ColorSet {
	public static final int TRANSPARENT_MASK = 0xFF000000;
	public static final int TRANSPARENT = 0;
	public static final int WHITE = 0xFFFFFFFF;
	public static final int BLACK = 0xFF000000;
	public static final int LIGHT_GRAY = 0xFFADADAD;
	public static final int MID_GRAY = 0xFF888888;
	public static final int DARK_GRAY = 0xFF515151;
	public static final int FRAME_BACKGROUND = 0xFF554499;
	
	public int[] pixels;
	public int width;
	
	public static ColorSet nullSetEntities = new ColorSet(FifthBusiness.entitySheet);
	public static ColorSet nullSetTiles = new ColorSet(FifthBusiness.tileSheet);
	
	private ColorSet(SpriteSheet sheet) {
		this.width = sheet.width;
		this.pixels = Arrays.copyOf(sheet.pixels, sheet.pixels.length);
	}
	
	public ColorSet(SpriteSheet sheet, int... colors) {
		this(sheet);
		if (colors.length % 2 != 0) throw new IllegalArgumentException("Must be an even number of colors.");
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < colors.length; j += 2) {
				if (pixels[i] == colors[j]) {
					pixels[i] = colors[j + 1];
					break;
				}
			}
		}
	}
}
