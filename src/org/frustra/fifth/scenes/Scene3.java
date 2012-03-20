package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.entity.Character;
import org.frustra.fifth.Scene;
import org.frustra.fifth.Timeline;

public class Scene3 extends Scene {
	
	Character willie;
	
	public Scene3() {
		super(2, "/res/3.map", 255);
	}
	
	public void onStart() {
		willie = new Character(" Willie", 0, 14, 0xFFFABC73, 0xFF5C5E37);
		willie.lying = true;
		willie.x = (3 << 4);
		willie.y = (3 << 4) + 4;
		willie.dir = 1;
		level.add(willie);
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		level.add(FifthBusiness.mary);

		FifthBusiness.mary.x = (7 << 4) + 8;
		FifthBusiness.mary.y = (10 << 4) + 12;
		FifthBusiness.mary.dir = 1;
		
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.dunstan.showName = false;
		FifthBusiness.boy.shown = false;
		FifthBusiness.screenTop.enabled = false;
		
		quotes = new QuoteLine[] {
				new QuoteLine("\"Willie could not be dead. It must not be.", false),
				new QuoteLine("I would not have it. And, without giving a thought", false),
				new QuoteLine("to calling the doctor, I set out on the run", false),
				new QuoteLine("to fetch Mrs. Dempster.\"", false),
				new QuoteLine("Press space to continue...", false),
				new QuoteLine("\"I hoped till I ached. She shook his hands gently,", true),
				new QuoteLine("as if rousing a sleeper. 'Willie.'\"", false),
				new QuoteLine("", false),
				new QuoteLine("", false),
				new QuoteLine("Press space to continue...", false),
		};
	}

	boolean confronted = false;
	boolean confronted2 = false;
	boolean hasMary = false;
	boolean faded = false;
	public void onTick(int dxt, int dyt) {
		if (scriptIndex == 0) {
			FifthBusiness.dunstan.speed = 15;
			FifthBusiness.dunstan.setTarget((3 << 4), (4 << 4) + 3);
			if (FifthBusiness.dunstan.y <= (4 << 4) + 5) {
				if (!confronted) {
					confronted = true;
					Timeline.advanceQuote(5);
					Timeline.waitForUser(false);
				}
			}
		} else if (scriptIndex == 1) {
			FifthBusiness.dunstan.speed = 7;
			FifthBusiness.dunstan.setTarget(-1, -1);
			advanceScript();
		} else if (scriptIndex == 2) {
			if (dxt > 7) {
				FifthBusiness.dunstan.setTarget((6 << 4) + 8, (9 << 4) + 8);
			} else if (dyt > 8) {
				if (!hasMary) {
					hasMary = true;
					FifthBusiness.mary.y = (9 << 4) + 12;
					FifthBusiness.mary.setTarget((3 << 4), (4 << 4) + 3);
					FifthBusiness.dunstan.speed = 10;
					FifthBusiness.dunstan.setTarget((4 << 4), (4 << 4) + 3);
				}
			} else if (FifthBusiness.mary.y <= (4 << 4) + 4) {
				if (!confronted2) {
					confronted2 = true;
					
					willie.lying = false;
					willie.dir = 0;
					Timeline.advanceQuote(5);
					Timeline.waitForUser(false);
				}
			} else if (FifthBusiness.mary.y <= (5 << 4)) {
				FifthBusiness.mary.showName = false;
			}
		} else if (scriptIndex == 3) {
			if (dxt > 8) {
				if (!faded) {
					FifthBusiness.dunstan.setTarget((15 << 4) + 8, (5 << 4) + 8);
	
					faded = true;
					FifthBusiness.screenBottom.fadeOut();
					FifthBusiness.screenTop.fadeOut();
				} else if (FifthBusiness.screenBottom.doneFadeOut) {
					Timeline.advanceScene();
				}
			} else {
				FifthBusiness.dunstan.speed = 15;
				FifthBusiness.dunstan.setTarget((15 << 4) + 8, (5 << 4) + 8);
			}
		}
	}
}
