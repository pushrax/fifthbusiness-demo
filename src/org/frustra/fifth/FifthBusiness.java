package org.frustra.fifth;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.frustra.fifth.entity.Boy;
import org.frustra.fifth.entity.Character;
import org.frustra.fifth.entity.Dunstan;
import org.frustra.fifth.gfx.Screen;
import org.frustra.fifth.gfx.SpriteSheet;
import org.frustra.fifth.level.Level;

public class FifthBusiness extends Applet {
	private static final long serialVersionUID = 1L;
	
	public final static int WIDTH = 400;
	public final static int HEIGHT = 400;

	private static Panel container;
	public static Screen screenTop;
	public static Screen screenBottom;
	public static QuotesCanvas quotesCanvas;
	public static SpriteSheet entitySheet;
	public static SpriteSheet tileSheet;
	
	public static Level level = null;
	public static Dunstan dunstan;
	public static Boy boy;
	
	public static Character mary;
	public static Character amasa;

	public static EngineThread engine;
	public static RenderThread render;
	public static InputHandler input;
	
	public void init() {
		try {
			container = new Panel();
			entitySheet = new SpriteSheet(ImageIO.read(Screen.class.getResourceAsStream("/res/icons.png")));
			tileSheet = new SpriteSheet(ImageIO.read(Screen.class.getResourceAsStream("/res/tiles.png")));
			screenTop = new Screen(WIDTH / 2, HEIGHT / 4, true);
			screenBottom = new Screen(WIDTH / 2, HEIGHT / 4, false);
			quotesCanvas = new QuotesCanvas(WIDTH, 80);
			
			final int offsetX = 80 + 15;
			final int offsetY = 10;
			
			input = new InputHandler();
			screenTop.addKeyListener(input);
			quotesCanvas.addKeyListener(input);
			screenBottom.addKeyListener(input);

			dunstan = new Dunstan();
			boy = new Boy();
			
			mary = new Character("Mary", 0, 16, 0xFF271010, 0xFF52B3DB);
			amasa = new Character("Amasa", 0, 14, 0xFF271010, 0xFF222233);
			
			Timeline.initScenes();

			setSize(WIDTH + offsetY, HEIGHT + offsetX);
			setPreferredSize(new Dimension(WIDTH + offsetY, HEIGHT + offsetX));
			setLayout(new BorderLayout());
			container.setSize(WIDTH + offsetY, HEIGHT + offsetX);
			container.setPreferredSize(new Dimension(WIDTH + offsetY, HEIGHT + offsetX));
			container.setMaximumSize(new Dimension(WIDTH + offsetY, HEIGHT + offsetX));
			container.setMinimumSize(new Dimension(WIDTH + offsetY, HEIGHT + offsetX));
			container.setLayout(new BorderLayout());
			container.add(screenTop, BorderLayout.NORTH);
			container.add(quotesCanvas, BorderLayout.CENTER);
			container.add(screenBottom, BorderLayout.SOUTH);
			add(container, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		engine = new EngineThread();
		engine.start();
		render = new RenderThread();
		render.start();
	}
	
	public void stop() {
		engine.stop();
		render.stop();
	}
	
	public static void main(String[] args) {
		FifthBusiness game = new FifthBusiness();
		
		game.init();
		
		JFrame frame = new JFrame("Fifth Business");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}
}
