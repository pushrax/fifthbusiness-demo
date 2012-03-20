package org.frustra.fifth;

public class EngineThread implements Runnable {
	private Thread t;
	private boolean running = false;
	
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
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60.0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();
		
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed--;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				break;
			}
			
			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + RenderThread.fps + " fps");
				ticks = 0;
			}
		}
	}
	
	public void tick() {
		if (!FifthBusiness.screenBottom.hasFocus() && !FifthBusiness.quotesCanvas.hasFocus() && !FifthBusiness.screenTop.hasFocus()) {
			FifthBusiness.input.resetAll();
		}
		FifthBusiness.level.tick();
	}
}
