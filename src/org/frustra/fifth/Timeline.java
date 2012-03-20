package org.frustra.fifth;

import org.frustra.fifth.scenes.*;

public class Timeline {
	public static Scene[] scenes;
	private static int sceneIndex = -1;
	private static boolean waiting = false;
	private static boolean afterWaitAdvanceScene = false;
	
	public static void initScenes() {
		sceneIndex = -1;
		waiting = false;
		afterWaitAdvanceScene = false;
		scenes = new Scene[] {
			new Scene1(),
			new Scene2(),
			new Scene3(),
			new Scene4(),
			new Scene5(),
			new Scene6(),
			new Scene7(),
			new Scene8(),
			new Scene9(),
			new Scene10(),
			new Scene11(),
			new Scene12(),
			new Scene13(),
			new Scene14(),
			new Scene15()
		};
		advanceScene();
	}
	
	public static Scene currentScene() {
		return scenes[sceneIndex];
	}
	
	public static void advanceScene() {
		if (sceneIndex + 1 >= scenes.length) throw new ArrayIndexOutOfBoundsException("No more scenes!");
		++sceneIndex;
		FifthBusiness.quotesCanvas.quoteLines.clear();
		FifthBusiness.screenBottom.reset();
		FifthBusiness.screenTop.reset();
		scenes[sceneIndex].startScene();
		FifthBusiness.level = scenes[sceneIndex].level;
	}
	
	public static void advanceQuote() {
		currentScene().advanceQuote();
	}
	
	public static void advanceQuote(int n) {
		for (int i = 0; i < n; ++i) advanceQuote();
	}
	
	public static void advanceScript() {
		currentScene().advanceScript();
	}
	
	public static void waitForUser(boolean afterWaitAdvanceScene) {
		waiting = true;
		Timeline.afterWaitAdvanceScene = afterWaitAdvanceScene;
	}
	
	public static void doneWait() {
		if (waiting) {
			waiting = false;
			FifthBusiness.quotesCanvas.quoteLines.clear();
			currentScene().advanceScript();
			if (afterWaitAdvanceScene) advanceScene();
		}
	}

}
