package Screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Main.PlayState;

public class Map {
	
	public int width,height;
	private int[][] map;	
	private Tile[][] tiles;	
	
	public void loadMap(String path){
		Scanner scanner = null;
		try {

			scanner = new Scanner(getClass().getResourceAsStream(path));
			width = 40;
			height = 30;
			map = new int[width][height];
			for (int y = 0; y < height; y++) {
				String[] bl = scanner.nextLine().split(",");
				for (int x = 0; x < width; x++) {
					
					if(bl[x].equals("p")){
						bl[x] = "0";
						PlayState.player.setSpawn(x*16, y*16);
					}
					
					map[x][y] = Integer.parseInt(bl[x]);
				}
			}

		} catch(Exception e){
			System.out.println("could not load map");
			JOptionPane.showMessageDialog(null, "could not load map");
		}finally{
			scanner.close();
			
		}
		
		tiles = new Tile[width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(map[x][y] == Tile.blank.id())tiles[x][y] = Tile.blank;
				else if(map[x][y] == Tile.block.id())tiles[x][y] = Tile.block;
				else if(map[x][y] == Tile.invis.id())tiles[x][y] = Tile.invis;
				else if(map[x][y] == Tile.spikes.id())tiles[x][y] = Tile.spikes;
			}
		}
		
	}
	
	public boolean isSolid(int y, int x){
		if(x < 0)x = 0;
		if(x > width-1)x = width-1;
		if(y < 0)y=0;
		if(y > height-1)y = height-1;
		return tiles[x][y].issolid();
	}
	public boolean doesHurt(int y, int x){
		if(x < 0)x = 0;
		if(x > width-1)x = width-1;
		if(y < 0)y=0;
		if(y > height-1)y = height-1;
		return tiles[x][y].doesHurt();
	}
	
	public boolean isInvis(int y, int x){
		if(x < 0)x = 0;
		if(x > width-1)x = width-1;
		if(y < 0)y=0;
		if(y > height-1)y = height-1;
		return tiles[x][y].isInvis();
	}
	
	public Rectangle getRect(int x, int y){
		return new Rectangle(x*16,y*16,16,16);
	}
	
	public void draw(Graphics2D g) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y].id() >= 1) {
					g.drawImage(tiles[x][y].getImage(), (int) ((x * 16)), (int) ((y * 16)), null);
				}

			}
		}
	}
}
