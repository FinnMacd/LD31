package Screen;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static int SOLID = 1,HURT = 2;
	
	private double health;
	private int state;
	private BufferedImage image;
	private int id;
	
	public static int numBlocks = 2;
	public static Tile block,blank,invis,spikes;
	
	public Tile(BufferedImage i, int id, int s){
		image = i;
		this.id = id;
		state = s;
	}
	
	public boolean issolid(){
		return state == SOLID;
	}
	
	public boolean isInvis(){return id == 3;}
	
	public boolean doesHurt(){
		return state == HURT;
	}
	
	public Image getImage(){
		return image;
	}
	
	public int id(){
		return id;
	}
	
	public static void loadTiles(){
		blank = new Tile(null, 0,0);
		invis = new Tile(null, 3,1);
		block = new Tile(Images.block, 1,1);
		spikes = new Tile(Images.spikes, 2, 2);
	}
	
}
