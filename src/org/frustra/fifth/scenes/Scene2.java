package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;
import org.frustra.fifth.Timeline;

public class Scene2 extends Scene {
	
	public Scene2() {
		super(2, "/res/2.map", 255);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"I was perfectly sure, you see,", false),
			new QuoteLine(" that the birth of Paul Dempster, so small,", false),
			new QuoteLine("so feeble and troublesome, was my fault.\" (p 16)", false),
			new QuoteLine("", false),
			new QuoteLine("Press space to continue...", false),
			new QuoteLine("\"'I threw a snowball at you,' ...", true),
			new QuoteLine("'And it's what you'd better think too,", false),
			new QuoteLine("if you know what's good for you.'\" (p 17)", false),
			new QuoteLine("", false),
			new QuoteLine("Press space to continue...", false),
		};
	}

	boolean confronted = false;
	boolean confronted2 = false;
	boolean faded = false;
	public void onTick(int dxt, int dyt) {
		if (scriptIndex == 0) {
			if (dyt == 21) {
				if (!confronted) {
					confronted = true;
					Timeline.advanceQuote(5);
					Timeline.waitForUser(false);
				}
			} else if (dyt > 18) {
				FifthBusiness.dunstan.showName = false;
				FifthBusiness.boy.showName = false;
				FifthBusiness.dunstan.speed = 20;
				FifthBusiness.boy.speed = 20;
				FifthBusiness.dunstan.setTarget((10 << 4), (21 << 4));
				FifthBusiness.boy.setTarget((10 << 4), (22 << 4));
			} else if (dyt > 12) {
				int ty = (21 << 4) - FifthBusiness.dunstan.y + (21 << 4);
				if (ty < FifthBusiness.boy.y) FifthBusiness.boy.setTarget((9 << 4) + 8, ty);
			}
		} else if (scriptIndex == 1) {
			if (!confronted2) {
				confronted2 = true;
				Timeline.advanceQuote(5);
				Timeline.waitForUser(false);
			}
		} else if (scriptIndex == 2) {
			if (!faded) {
				FifthBusiness.dunstan.speed = 15;
				FifthBusiness.boy.speed = 15;
				FifthBusiness.dunstan.setTarget((15 << 4), (21 << 4));
				FifthBusiness.boy.setTarget(0, (22 << 4));

				faded = true;
				FifthBusiness.screenBottom.fadeOut();
				FifthBusiness.screenTop.fadeOut();
			}
		}
	}
}
