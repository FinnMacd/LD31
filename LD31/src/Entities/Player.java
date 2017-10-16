package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Audio.PlaySound;
import Main.GamePanel;
import Main.Inputs;
import Screen.Images;

public class Player extends Entity{
	
	private BufferedImage playerset;
	private BufferedImage[][] sets;
	private final int STILL = 0,WALKING = 1,JUMPING = 2;
	private int currentState;
	private Animation animation;
	private int[] an = {2,4,2};
	
	public int died;
	
	public long stillStart,fallStart,stillTime,fallTime;
	
	private PlaySound jump,die;
		
	public static AcheivmentListener ach;
	
	private int sx,sy;
	
	public static double corpseS;
	
	public ArrayList<Corpse> corpses = new ArrayList<Corpse>();
	
	public Player(){
		playerset = Images.player;
		sets = new BufferedImage[3][];
		animation = new Animation();
		ach = new AcheivmentListener(this);
		for(int y = 0; y < 3; y++){
			BufferedImage subset = playerset.getSubimage(0, y*16, an[y]*6, 16);
			sets[y] = new BufferedImage[an[y]];
			for(int x = 0; x < an[y]; x++){
				sets[y][x] = subset.getSubimage(x*6, 0, 6, 16);
			}
		}
		currentState = STILL;
		animation.setFrames(sets[STILL]);
		animation.setDelay(100);
		
		moveSpeed = 3;
		moveStart = 0.1;
		jumpStart = -6.72;
		fallSpeed = .23;
		maxFallSpeed = 32;
		stopSpeed = 0.09;
		slideSpeed = 0.05;
		
		width = 6;
		height = 14;
		
		jump = new PlaySound("/Sound/Jump.wav");
		die = new PlaySound("/Sound/Die.wav");
	}
	
	public void update(){
		
		ach.update();
		
		left = Inputs.left;
		right = Inputs.right;
		up = Inputs.up;
		down = Inputs.down;
		if(up && !falling)jumping = true;
		
		if(x<0)x=GamePanel.WIDTH-1;
		if(x>GamePanel.WIDTH)x=0;
		if(y<0)y=GamePanel.HEIGHT-1;
		if(y>GamePanel.HEIGHT)y=0;
		
		animation.update();
		
		movement();
		checkTileMapCollision();
		x = xtemp;
		y = ytemp;
		
		if (dy != 0) {
			if (jumping) {
				if ((currentState == JUMPING && animation.getFrame() != 0) || currentState != JUMPING) {
					currentState = JUMPING;
					animation.setFrames(sets[currentState]);
					animation.setDelay(-1);
					animation.setFrame(0);
				}
			} else if (falling && ((currentState == JUMPING && animation.getFrame() != 1) || currentState != JUMPING)) {
				currentState = JUMPING;
				animation.setFrames(sets[currentState]);
				animation.setDelay(-1);
				animation.setFrame(1);
			}
		} else if (dx != 0 && currentState != WALKING) {
			currentState = WALKING;
			animation.setFrames(sets[currentState]);
			animation.setDelay(200);
		} else if(dx == 0 && currentState != STILL){
			currentState = STILL;
			animation.setFrames(sets[currentState]);
			animation.setDelay(1000);
		}
		
		checkAlive(x,y);
		if(!alive){
			corpses.add(new Corpse(x,y,dx,dy));
			x = sx;
			y = sy;
			dx = dy = 0;
			died++;
			die.playSound();
		}
		
		checkInvis(x,y+2);
		
		for(int i = 0; i < corpses.size();i++){
			
			if(corpses.get(i).done){
				corpses.remove(i);
				i--;
				continue;
			}
			
			corpses.get(i).update();
		}
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
			jump.playSound();
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
		
		if(dx == 0 && dy == 0){
			if(stillStart == 0)stillStart = System.currentTimeMillis();
			stillTime = System.currentTimeMillis()-stillStart;
		}else stillStart = 0;
		
		if(dy > 0){
			if(fallStart == 0)fallStart = System.currentTimeMillis();
			fallTime = System.currentTimeMillis()-fallStart;
		}else fallStart = 0;
		
	}
	
	public void setSpawn(int x, int y){
		sy=y;
		sx=x;
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	public void draw(Graphics2D g){
		ach.draw(g);
		if (facingRight)g.drawImage(animation.getImage(), (int)x, (int)y, null);
		else g.drawImage(animation.getImage(), (int)x + width, (int)y,-width,16, null);
		for(int i = 0; i < corpses.size();i++){
			corpses.get(i).draw(g);
		}
	}
	
}
