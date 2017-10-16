package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Star {
	
	private int x,y;
	public boolean found;
	public BufferedImage image;
	
	public Star(int x, int y, BufferedImage img){
		this.x = x; 
		this.y = y;
		image = img;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,image.getWidth(),image.getHeight());
	}
	
	public void draw(Graphics2D g){
		if(!found )return;
		g.drawImage(image, x, y, null);
	}
	
}
