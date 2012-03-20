package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene10 extends Scene {
	
	public Scene10() {
		super(10, "/res/10.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = true;
		FifthBusiness.screenTop.enabled = true;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"When Leola fell ill of pneumonia I informed Boy", false),
			new QuoteLine("and did all the obvious things and did not worry. ...", false),
			new QuoteLine("He asked me, by cable, to do what had to be done.\"", false),
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
