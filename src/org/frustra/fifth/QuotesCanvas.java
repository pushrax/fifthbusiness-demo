package org.frustra.fifth;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class QuotesCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	public final int w, h;
	
	public ArrayList<String> quoteLines;
	
	public QuotesCanvas(int w, int h) {
		this.w = w;
		this.h = h;
		setSize(w, h);
		quoteLines = new ArrayList<String>();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		if (!this.isVisible()) return;
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int xo = getWidth() / 2;
		
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < quoteLines.size(); ++i) {
			String quote = quoteLines.get(i);
			int x2 = g.getFontMetrics().stringWidth(quote) / 2;
			g.drawString(quote, xo - x2, 12 + 14 * i);
		}
		g.dispose();
		bs.show();
	}
	
	public static class QuoteLine {
		public String line;
		public boolean clearCurrent;
		
		public QuoteLine(String line, boolean clearCurrent) {
			this.line = line;
			this.clearCurrent = clearCurrent;
		}
	}
}
