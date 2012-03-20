package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene12 extends Scene {
	
	public Scene12() {
		super(12, "/res/12.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = true;
		FifthBusiness.screenTop.enabled = true;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"'So, although I was asked to dinner now and then,", false),
			new QuoteLine("when the other guests were people who had", false),
			new QuoteLine("to be worked off for some tiresome reason,", false),
			new QuoteLine("I was no longer an intimate of the household.'\"", false),
			new QuoteLine("Press space to continue...", false),
		};
	}
	
	boolean confronted = false;
	boolean faded = false;
	public void onTick(int dxt, int dyt) {
		if (scriptIndex == 0) {
			
		} else if (scriptIndex == 1) {
			if (!faded) {
				faded = true;
				FifthBusiness.screenBottom.fadeOut();
				FifthBusiness.screenTop.fadeOut();
			}
		}
	}
}
