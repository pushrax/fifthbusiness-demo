package org.frustra.fifth;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	
	public void resetAll() {
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_SPACE:
				Timeline.doneWait();
				break;
			case KeyEvent.VK_A:
				try {
					Timeline.advanceScene();
				} catch (ArrayIndexOutOfBoundsException e) {
					
				}
				break;
		}
	}

	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
		}
	}

	public void keyTyped(KeyEvent event) {}
}
