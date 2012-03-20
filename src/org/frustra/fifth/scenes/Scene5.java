package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene5 extends Scene {
	
	public Scene5() {
		super(5, "/res/5.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = false;
		FifthBusiness.screenTop.enabled = false;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"The red-faced man was some sort of specialist", false),
			new QuoteLine("in shell-shock cases, and I was one of his successes,", false),
			new QuoteLine("though I rather think I cured myself,", false),
			new QuoteLine("or the little Madonna cured me\"", false),
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
