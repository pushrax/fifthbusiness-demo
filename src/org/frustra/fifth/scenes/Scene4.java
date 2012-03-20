package org.frustra.fifth.scenes;

import java.util.Random;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.Timeline;
import org.frustra.fifth.QuotesCanvas.QuoteLine;
import org.frustra.fifth.Scene;
import org.frustra.fifth.entity.Madonna;
import org.frustra.fifth.entity.particle.FlareParticle;

public class Scene4 extends Scene {
	
	public Scene4() {
		super(4, "/res/4.map", 150);
	}
	
	public void onStart() {
		Madonna madonna = new Madonna();
		madonna.x = 8 + 6 * 16;
		madonna.y = 12 + 7 * 16;
		level.add(madonna);
		
		level.add(FifthBusiness.dunstan);
		level.add(FifthBusiness.boy);
		
		FifthBusiness.boy.reset();
		FifthBusiness.dunstan.reset();
		FifthBusiness.dunstan.speed = 10;
		FifthBusiness.dunstan.showLight = false;
		FifthBusiness.boy.shown = false;
		FifthBusiness.screenTop.enabled = false;
		
		quotes = new QuoteLine[] {
			new QuoteLine("\"But what hit me worse", false),
			new QuoteLine("than the blow of the shrapnel,", false),
			new QuoteLine("was that the face was Mrs. Dempster's face.\"", false),
			new QuoteLine("", false),
			new QuoteLine("Press space to continue...", false),
		};
	}
	
	boolean confronted = false;
	boolean faded = false;
	Random r = new Random();
	public void onTick(int dxt, int dyt) {
		if (scriptIndex == 0) {
			if (dyt == 10) {
				FifthBusiness.dunstan.setTarget((7 << 4) + 8, (7 << 4) + 8);
				FifthBusiness.dunstan.speed = 20;
				advanceScript();
			}
		} else if (scriptIndex == 1) {
			if (dyt == 7) {
				FifthBusiness.dunstan.dir = 2;
				if (!confronted) {
					confronted = true;
					Timeline.advanceQuote(5);
					Timeline.waitForUser(false);
				}
			}
		} else if (scriptIndex == 2) {
			level.add(new FlareParticle(0, -100, FifthBusiness.dunstan.x, FifthBusiness.dunstan.y, 8));
			advanceScript();
		} else if (scriptIndex == 3) {
			// Waiting for snowball to hit
		} else if (scriptIndex == 4) {
			FifthBusiness.dunstan.movable = false;
			FifthBusiness.dunstan.fallen = true;
			advanceScript();
		} else if (scriptIndex == 5) {
			// Waiting for flare to fade to hit
		} else if (scriptIndex == 6) {
			if (!faded) {
				faded = true;
				FifthBusiness.screenBottom.fadeOut();
				FifthBusiness.screenTop.fadeOut();
			}
		} else if (scriptIndex > 130) {
			Timeline.advanceScene();
		}
	}
}
