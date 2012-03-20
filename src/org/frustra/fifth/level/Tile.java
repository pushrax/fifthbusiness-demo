package org.frustra.fifth.level;

import java.util.Random;

import org.frustra.fifth.entity.Entity;
import org.frustra.fifth.gfx.Screen;

public class Tile {
	protected Random random = new Random();

	public static Tile[] backs = new Tile[256];
	public static Tile[] tiles = new Tile[256];
	
	public static Tile grass = new GrassTile(0);
	public static Tile snow = new SnowTile(1);
	public static Tile floor = new FloorTile(2);
	public static Tile dirt = new DirtTile(3);
	
	public static Tile nullTile = new Tile(false, 0);
	public static Tile rock = new RockTile(1);
	public static Tile tree = new TreeTile(2);
	public static Tile house = new HouseTile(3);
	public static Tile door = new DoorTile(4);
	public static Tile road = new RoadTile(5);
	public static Tile wall = new WallTile(6);
	public static Tile squareDoor = new SquareDoorTile(7);
	public static Tile squareHouse = new SquareHouseTile(8);
	public static Tile bed = new BedTile(9);
	
	public final byte id;

	public boolean connectsToWater = false;
	public boolean connectsToHouse = false;
	
	public boolean advanceSceneTrigger = false;
	
	public Tile(boolean backTile, int id) {
		this.id = (byte) id;
		if (backTile) {
			if (backs[id] != null) throw new RuntimeException("Duplicate back ids!");
			backs[id] = this;
		} else {
			if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids!");
			tiles[id] = this;
		}
	}
	
	public void render(Screen screen, Level level, int x, int y) {
	}
	
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}
	
	public int getLightRadius(Level level, int x, int y) {
		return 0;
	}
	
	public void tick(Level level, int xt, int yt) {
	}
}
