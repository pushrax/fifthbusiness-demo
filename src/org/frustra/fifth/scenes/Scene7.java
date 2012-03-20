package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene7 extends Scene {
	
	public Scene7() {
		super(7, "/res/7.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = true;
		FifthBusiness.screenTop.enabled = true;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"'(The Lord) worked through that woman,", false),
			new QuoteLine("and she is a blessed saint, for what she did for me", false),
			new QuoteLine("-- I mean it as I say it -- was a miracle.'\"", false),
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
