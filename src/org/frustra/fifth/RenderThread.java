package org.frustra.fifth;

public class RenderThread implements Runnable {
	private Thread t;
	private boolean running = false;
	public static int fps = 0;
	
	public void start() {
		if (t == null) t = new Thread(this);
		running = true;
		t.start();
	}
	
	public void stop() {
		running = false;
		t.interrupt();
	}
	
	public void run() {
		int frames = 0;
		long lastTimer1 = System.currentTimeMillis();
		
		while (running) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				break;
			}
			
			frames++;
			FifthBusiness.screenTop.render();
			FifthBusiness.quotesCanvas.render();
			FifthBusiness.screenBottom.render();
			
			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				fps = frames;
				frames = 0;
			}
		}
	}
}
