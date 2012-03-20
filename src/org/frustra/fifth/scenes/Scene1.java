package org.frustra.fifth.scenes;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;
import org.frustra.fifth.Timeline;
import org.frustra.fifth.entity.particle.SnowBallParticle;

public class Scene1 extends Scene {
	
	public Scene1() {
		super(1, "/res/1.map", 150);
	}
	
	public void onStart() {
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		level.add(FifthBusiness.mary);
		level.add(FifthBusiness.amasa);

		FifthBusiness.mary.x = (54 << 4) + 8;
		FifthBusiness.mary.y = (6 << 4) + 8;
		FifthBusiness.mary.dir = 3;
		FifthBusiness.amasa.x = (54 << 4) + 8;
		FifthBusiness.amasa.y = (5 << 4) + 14;
		FifthBusiness.amasa.dir = 3;
		
		FifthBusiness.boy.following = true;
		
		quotes = new QuoteLine[] {
				new QuoteLine("\"I had never heard an adult cry in pain before", false),
				new QuoteLine("and the sound was terrible to me.\" (p 5)", false),
				new QuoteLine("", false),
				new QuoteLine("", false),
				new QuoteLine("Press space to continue...", false),
		};
	}
	
	boolean snowballThrown = false;
	boolean faded = false;
	public void onTick(int dxt, int dyt) {
		if (scriptIndex == 0) {
			if (dxt == 52) {
				FifthBusiness.dunstan.setTarget((52 << 4) + 8, (5 << 4) + 8);
				
				if (!snowballThrown) {
					FifthBusiness.dunstan.speed = 10;
					snowballThrown = true;
					level.add(new SnowBallParticle(FifthBusiness.boy.x, FifthBusiness.boy.y, 1, 0, 3) {
						public void remove() {
							super.remove();
							FifthBusiness.mary.fallen = true;
							FifthBusiness.dunstan.dir = 0;
							FifthBusiness.amasa.dir = 0;
							Timeline.advanceQuote(5);
							Timeline.waitForUser(false);
						}
					});
				}
			} else if (dxt > 45) {
				FifthBusiness.boy.following = false;
				FifthBusiness.boy.setTarget(FifthBusiness.dunstan.x - 64, FifthBusiness.dunstan.y);
				FifthBusiness.dunstan.setTarget((52 << 4) + 8, (6 << 4) + 8);
			} else if (dxt > 43) {
				FifthBusiness.boy.speed = 20;
				FifthBusiness.dunstan.speed = 20;
				FifthBusiness.boy.following = false;
				FifthBusiness.boy.setTarget(FifthBusiness.dunstan.x - 64, FifthBusiness.dunstan.y);
				FifthBusiness.dunstan.setTarget((46 << 4) + 8, (6 << 4) + 8);
			}
		} else if (scriptIndex == 1) {
			if (dxt == 52) {
				FifthBusiness.dunstan.showName = false;
				FifthBusiness.mary.showName = false;
				FifthBusiness.amasa.showName = false;
				FifthBusiness.dunstan.speed = 20;
				FifthBusiness.dunstan.setTarget(FifthBusiness.mary.x, (7 << 4));
				FifthBusiness.boy.setTarget((52 << 4) + 8, (9 << 4) + 8);
			} else if (dxt == 54) {
				FifthBusiness.mary.fallen = false;
				if (FifthBusiness.dunstan.x >= FifthBusiness.mary.x - 1) {
					FifthBusiness.mary.speed = 20;
					FifthBusiness.amasa.speed = 20;
					FifthBusiness.amasa.setTarget((63 << 4) + 8, (5 << 4) + 14);
					FifthBusiness.mary.setTarget((63 << 4) + 8, (6 << 4) + 8);
					FifthBusiness.dunstan.setTarget((63 << 4) + 8, (7 << 4));
				}
			} else if (dxt == 57) {
				if (!faded) {
					faded = true;
					FifthBusiness.screenBottom.fadeOut();
					FifthBusiness.screenTop.fadeOut();
				}
			}
		}
	}
}
