package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene13 extends Scene {
	
	public Scene13() {
		super(13, "/res/13.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = true;
		FifthBusiness.screenTop.enabled = true;
		
		quotes = new QuoteLine[] {
				new QuoteLine("\"'I've done just about everything I've ever planned to do, ...", false),
				new QuoteLine("But sometimes I wish I could get into a", false),
				new QuoteLine("car and drive away from the whole damned thing.'\"", false),
				new QuoteLine("", false),
				new QuoteLine("Press space to continue...", false),
				new QuoteLine("\"Boy Staunton had reached a point in life where he no", false),
				new QuoteLine("longer tried to conceal his naked wish to dominate everybody.\"", false),
				new QuoteLine("", false),
				new QuoteLine("", false),
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
