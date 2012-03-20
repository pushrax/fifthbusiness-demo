package org.frustra.fifth;

import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.gfx.Screen;
import org.frustra.fifth.level.Level;

public class Scene {
	protected Level level = null;
	private int id;
	private int brightness;
	private String mapPath;
	protected int quoteIndex = -1;
	public QuoteLine[] quotes = new QuoteLine[] { };
	protected int scriptIndex = 0;
	
	public Scene(int id, String mapPath, int brightness) {
		this.id = id;
		this.mapPath = mapPath;
		this.brightness = brightness;
	}
	
	public void startScene() {
		level = new Level(id, Screen.class.getResourceAsStream(mapPath), brightness);
		FifthBusiness.dunstan.setTarget(-1, -1);
		FifthBusiness.boy.setTarget(-1, -1);
		this.onStart();
	}
	
	public void onStart() { }
	
	public void tick() {
		int xt = (int) FifthBusiness.dunstan.x >> 4;
		int yt = (int) FifthBusiness.dunstan.y >> 4;
		if (level != null && (xt <= 0 || xt >= level.w - 1 || yt <= 0 || yt >= level.h - 1)) {
			Timeline.advanceScene();
		} else {
			onTick(xt, yt);
		}
	}
	
	public void onTick(int dxt, int dyt) { }
	
	public void advanceQuote() {
		if (quoteIndex + 1 >= quotes.length) throw new ArrayIndexOutOfBoundsException("No more quotes!");
		++quoteIndex;
		if (quotes[quoteIndex].clearCurrent) FifthBusiness.quotesCanvas.quoteLines.clear();
		FifthBusiness.quotesCanvas.quoteLines.add(quotes[quoteIndex].line);
	}
	
	public void advanceScript() {
		++scriptIndex;
	}
}