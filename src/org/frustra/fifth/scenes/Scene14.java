package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;

public class Scene14 extends Scene {
	
	public Scene14() {
		super(14, "/res/14.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.boy.shown = true;
		FifthBusiness.screenTop.enabled = true;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"'Would this jog your memory?' I asked, ...", false),
			new QuoteLine("'It is the stone you put in the", false),
			new QuoteLine("snowball you threw at Mrs. Dempster.'\"", false),
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
