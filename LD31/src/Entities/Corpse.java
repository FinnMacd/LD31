package Entities;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.GamePanel;
import Screen.Images;

public class Corpse extends Entity{
	
	private BufferedImage image;
	
	private long start;
	private float alpha = 1.0f;
	
	public boolean done = false;
	
	public Corpse(double x, double y,double dx,double dy){
		this.x = x;
		this.y = y;
		width = 16;
		height = 6;
		this.dx = dx;
		this.dy = dy;
		image = Images.corpse;
		
		
		moveSpeed = 3;
		moveStart = 0.1;
		jumpStart = -6.72;
		fallSpeed = .23;
		maxFallSpeed = 320;
		stopSpeed = 0.02;
		slideSpeed = 0.01;
		
		start = System.nanoTime();
	}
	
	public void movement(){
		
		if(left){
			if (dx < -moveSpeed) {
				dx = -moveSpeed;
			}else dx -= moveStart;
			facingRight = false;
		}else if(right){
			if (dx > moveSpeed) {
				dx = moveSpeed;
			}else dx += moveStart;
			facingRight = true;
		}else{
			if(dx > stopSpeed)dx -= stopSpeed;
			else if(dx < -stopSpeed)dx += stopSpeed;
			else dx = 0;
		}
		if(jumping && !falling){
			dy = jumpStart;
			falling = true;
			jumps++;
		}
		if(falling){
			dy+=fallSpeed;
			if(dy > 0){
				jumping =false;
			}
			if(dy >= maxFallSpeed){
				dy = maxFallSpeed;
			}
		}
		
	}
	
	public void update(){
		if(done)return;
		
		if(x<0)x=GamePanel.WIDTH-1;
		if(x>GamePanel.WIDTH)x=0;
		if(y<0)y=GamePanel.HEIGHT-1;
		if(y>GamePanel.HEIGHT)y=0;
		
		movement();
		checkTileMapCollision();
		x = xtemp;
		y = ytemp;
		
		if(dy > Player.corpseS)Player.corpseS = dy;
		
		long elapsed = System.nanoTime()-start;
		
		if(elapsed/1000000000 >= 14){
			
			alpha -= 0.009f;
			
		}
		
		if(alpha <= 0)done = true;
	}
	
	public void draw(Graphics2D g){
		if(done)return;
		
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	    g.setComposite(c);
		g.drawImage(image, (int)x, (int)y, null);
		
		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
	    g.setComposite(c);
	}
	
}
