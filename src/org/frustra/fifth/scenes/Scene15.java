package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene15 extends Scene {
	
	public Scene15() {
		super(15, "/res/15.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = true;
		FifthBusiness.screenTop.enabled = true;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"'He was killed by the usual cabal: by himself,", false),
			new QuoteLine("first of all; by the woman he knew; by the woman he", false),
			new QuoteLine("did not know; by the man who granted his inmost wish;", false),
			new QuoteLine("and by the inevitable fifth, who was keeper", false),
			new QuoteLine("of his conscience and keeper of the stone.'\"", false),
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
