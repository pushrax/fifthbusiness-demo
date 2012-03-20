package org.frustra.fifth.level;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.frustra.fifth.FifthBusiness;
import org.frustra.fifth.Timeline;
import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.entity.particle.SnowParticle;
import org.frustra.fifth.gfx.Screen;

public class Level {
	private static Random random = new Random();
	
	public int w, h, id;
	public int brightness;
	public int tickTime = 0;
	
	private int crumb = 1;
	public int[] crumbs;
	public byte[] backs;
	public byte[] tiles;
	public List<Entity>[] entitiesInTiles;
	
	public List<Entity> entities = new ArrayList<Entity>();
	private Comparator<Entity> spriteSorter = new Comparator<Entity>() {
		public int compare(Entity e0, Entity e1) {
			if (e0 == null || e1 == null) return 0;
			if (e1.y < e0.y) return +1;
			if (e1.y > e0.y) return -1;
			return 0;
		}
	};
	
	@SuppressWarnings("unchecked")
	public Level(int id, InputStream stream, int brightness) {
		this.id = id;
		this.brightness = brightness;
		
		Scanner scan = new Scanner(stream);
		int dunstanSpawnX = scan.nextInt();
		int dunstanSpawnY = scan.nextInt();
		int boySpawnX = scan.nextInt();
		int boySpawnY = scan.nextInt();
		FifthBusiness.dunstan.x = (dunstanSpawnX << 4) + 8;
		FifthBusiness.dunstan.y = (dunstanSpawnY << 4) + 8;
		FifthBusiness.boy.x = (boySpawnX << 4) + 8;
		FifthBusiness.boy.y = (boySpawnY << 4) + 8;
		this.w = scan.nextInt();
		this.h = scan.nextInt();
		this.crumbs = new int[w * h];
		this.backs = new byte[w * h];
		this.tiles = new byte[w * h];
		scan.nextLine();
		int i = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line == null || line.trim().length() <= 0) break;
			for (char ch : line.trim().toCharArray()) {
				switch (ch) {
					case 'g':
						backs[i++] = Tile.grass.id;
						break;
					case 's':
						backs[i++] = Tile.snow.id;
						break;
					case 'f':
						backs[i++] = Tile.floor.id;
						break;
					case 'd':
						backs[i++] = Tile.dirt.id;
						break;
				}
			}
		}
		i = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line == null || line.length() <= 0) break;
			for (char ch : line.toCharArray()) {
				switch (ch) {
					case 't':
						tiles[i++] = Tile.tree.id;
						break;
					case 'h':
						tiles[i++] = Tile.house.id;
						break;
					case 'H':
						tiles[i++] = Tile.squareHouse.id;
						break;
					case 'd':
						tiles[i++] = Tile.door.id;
						break;
					case 'q':
						tiles[i++] = Tile.squareDoor.id;
						break;
					case 'r':
						tiles[i++] = Tile.road.id;
						break;
					case 'D':
						tiles[i++] = Tile.dirt.id;
						break;
					case 'w':
						tiles[i++] = Tile.wall.id;
						break;
					case 'b':
						tiles[i++] = Tile.bed.id;
						break;
					default:
						tiles[i++] = Tile.nullTile.id;
						break;
				}
			}
		}
		
		entitiesInTiles = new ArrayList[w * h];
		for (int j = 0; j < w * h; j++) {
			entitiesInTiles[j] = new ArrayList<Entity>();
		}
	}
	
	public synchronized int getCrumb() {
		if (crumb > Integer.MAX_VALUE - 256) {
			for (int i = 0; i < crumbs.length; i++) {
				crumbs[i] = 0;
			}
			crumb = 1;
			return crumb++;
		} else return crumb++;
	}
	
	public boolean canMoveTo(int crumb, int fx, int fy, int dx, int dy, Entity e) {
		if (fx < 0 || fy < 0 || fx >= this.w || fy >= this.h) return false;
		crumbs[fx + fy * this.w] = crumb; 
		boolean a = getTile(fx, fy + dy).mayPass(this, fx, fy + dy, e) && (dy == 0 || crumbs[fx + (fy + dy) * this.w] != crumb);
		boolean b = getTile(fx + dx, fy).mayPass(this, fx + dx, fy, e) && (dx == 0 || crumbs[fx + dx + fy * this.w] != crumb);
		boolean c = getTile(fx + dx, fy + dy).mayPass(this, fx + dx, fy + dy, e) && crumbs[fx + dx + (fy + dy) * this.w] != crumb;
		return a && b && c;
	}
	
	public void renderLevel(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				getBack(x, y).render(screen, this, x, y);
				getTile(x, y).render(screen, this, x, y);
			}
		}
		screen.setOffset(0, 0);
	}
	
	private List<Entity> rowSprites = new ArrayList<Entity>();
	
	public void renderSprites(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		
		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				if (x < 0 || y < 0 || x >= this.w || y >= this.h) continue;
				rowSprites.addAll(entitiesInTiles[x + y * this.w]);
			}
			if (rowSprites.size() > 0) {
				sortAndRender(screen, rowSprites);
			}
			rowSprites.clear();
		}
		screen.setOffset(0, 0);
	}
	
	public void renderLight(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		
		screen.setOffset(xScroll, yScroll);
		int r = 4;
		for (int y = yo - r; y <= h + yo + r; y++) {
			for (int x = xo - r; x <= w + xo + r; x++) {
				if (x < 0 || y < 0 || x >= this.w || y >= this.h) continue;
				List<Entity> entities = entitiesInTiles[x + y * this.w];
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if (!e.shown) continue;
					int lr = e.getLightRadius();
					if (lr > 0) screen.renderLight((int) e.x - 1, (int) e.y - 4, lr);
				}
				int lr = getTile(x, y).getLightRadius(this, x, y);
				if (lr > 0) screen.renderLight(x * 16 + 8, y * 16 + 8, lr * 8);
			}
		}
		screen.setOffset(0, 0);
	}
	
	private void sortAndRender(Screen screen, List<Entity> list) {
		Collections.sort(list, spriteSorter);
		for (int i = 0; i < list.size(); i++) {
			Entity e = list.get(i);
			if (e != null) e.render(screen);
		}
	}
	
	public Tile getBack(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h) return Tile.rock;
		return Tile.backs[backs[x + y * w]];
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h) return Tile.rock;
		return Tile.tiles[tiles[x + y * w]];
	}
	
	public void add(Entity entity) {
		entity.removed = false;
		entities.add(entity);
		entity.init(this);
		
		insertEntity((int) entity.x >> 4, (int) entity.y >> 4, entity);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
		int xto = (int) e.x >> 4;
		int yto = (int) e.y >> 4;
		removeEntity(xto, yto, e);
	}
	
	private void insertEntity(int x, int y, Entity e) {
		if (x < 0 || y < 0 || x >= w || y >= h) return;
		entitiesInTiles[x + y * w].add(e);
	}
	
	private void removeEntity(int x, int y, Entity e) {
		if (x < 0 || y < 0 || x >= w || y >= h) return;
		entitiesInTiles[x + y * w].remove(e);
	}
	
	public void tick() {
		tickTime++;
		synchronized (FifthBusiness.level) {
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				int xto = (int) e.x >> 4;
				int yto = (int) e.y >> 4;
				
				e.tick();
				
				if (e.removed) {
					entities.remove(i--);
					removeEntity(xto, yto, e);
				} else {
					int xt = (int) e.x >> 4;
					int yt = (int) e.y >> 4;
					
					if (xto != xt || yto != yt) {
						removeEntity(xto, yto, e);
						insertEntity(xt, yt, e);
					}
				}
			}
		}
		Timeline.currentScene().tick();
		if (getBack((int) FifthBusiness.dunstan.x >> 4, (int) FifthBusiness.dunstan.y >> 4) instanceof SnowTile) {
			for (int i = 0; i < 2; i++) {
				SnowParticle snow = new SnowParticle(FifthBusiness.dunstan.x + random.nextInt(FifthBusiness.WIDTH) - (FifthBusiness.WIDTH / 2), FifthBusiness.dunstan.y + random.nextInt(FifthBusiness.HEIGHT / 2) - (FifthBusiness.HEIGHT / 4));
				add(snow);
			}
		}
		if (getBack((int) FifthBusiness.boy.x >> 4, (int) FifthBusiness.boy.y >> 4) instanceof SnowTile) {
			for (int i = 0; i < 2; i++) {
				SnowParticle snow = new SnowParticle(FifthBusiness.boy.x + random.nextInt(FifthBusiness.WIDTH) - (FifthBusiness.WIDTH / 2), FifthBusiness.boy.y + random.nextInt(FifthBusiness.HEIGHT / 2) - (FifthBusiness.HEIGHT / 4));
				add(snow);
			}
		}
	}
	
	public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();
		int xt0 = (x0 >> 4) - 1;
		int yt0 = (y0 >> 4) - 1;
		int xt1 = (x1 >> 4) + 1;
		int yt1 = (y1 >> 4) + 1;
		for (int y = yt0; y <= yt1; y++) {
			for (int x = xt0; x <= xt1; x++) {
				if (x < 0 || y < 0 || x >= w || y >= h) continue;
				List<Entity> entities = entitiesInTiles[x + y * this.w];
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if (e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		return result;
	}
}
