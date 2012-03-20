package org.frustra.fifth.gfx;

import org.frustra.fifth.FifthBusiness;




public class Font {
	private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " +
										"0123456789.,!?'\"-+=/\\%()<>:;    ";

	public static void draw(String msg, Screen screen, int x, int y, ColorSet set) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			if (ix >= 0) {
				screen.render8(x + i * 8, y, ix + 30 * 32, 0, set);
			}
		}
	}
	
	public static void drawSmall(String msg, Screen screen, int x, int y, ColorSet set) {
		msg = msg.toUpperCase().replaceAll("[^A-Z ]", "");
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			if (ix >= 0) {
				screen.renderTextContrast(x + i * 5, y, ix + 29 * 32, 5, 0, set);
			}
		}
	}
	
	public static ColorSet frameSet = new ColorSet(FifthBusiness.entitySheet, ColorSet.LIGHT_GRAY, ColorSet.FRAME_BACKGROUND, ColorSet.BLACK, ColorSet.TRANSPARENT);
	public static ColorSet frameTextSet = new ColorSet(FifthBusiness.entitySheet, ColorSet.BLACK, ColorSet.FRAME_BACKGROUND);
	
	public static void renderFrame(Screen screen, String title, int x0, int y0, int x1, int y1) {
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				if (x == x0 && y == y0)
					screen.render8(x * 8, y * 8, 0 + 13 * 32, 0, frameSet);
				else if (x == x1 && y == y0)
					screen.render8(x * 8, y * 8, 0 + 13 * 32, 1, frameSet);
				else if (x == x0 && y == y1)
					screen.render8(x * 8, y * 8, 0 + 13 * 32, 2, frameSet);
				else if (x == x1 && y == y1)
					screen.render8(x * 8, y * 8, 0 + 13 * 32, 3, frameSet);
				else if (y == y0)
					screen.render8(x * 8, y * 8, 1 + 13 * 32, 0, frameSet);
				else if (y == y1)
					screen.render8(x * 8, y * 8, 1 + 13 * 32, 2, frameSet);
				else if (x == x0)
					screen.render8(x * 8, y * 8, 2 + 13 * 32, 0, frameSet);
				else if (x == x1)
					screen.render8(x * 8, y * 8, 2 + 13 * 32, 1, frameSet);
				else screen.render8(x * 8, y * 8, 2 + 13 * 32, 1, frameSet);
			}
		}
		
		draw(title, screen, x0 * 8 + 8, y0 * 8, frameTextSet);
		
	}
}
