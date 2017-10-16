package Entities;

import Main.PlayState;

public abstract class Entity {

	protected double x = 100, y = 100, dx, dy, xdest, ydest, xtemp, ytemp;

	protected double moveSpeed, jumpStart, fallSpeed, maxFallSpeed, stopSpeed, slideSpeed, moveStart;
	
	private boolean tr,tl,br,bl;
	
	protected int width,height;
	
	protected boolean facingRight = false,up,down,left,right,jumping,falling,alive = true,invis = false;
	
	protected double dist,jumps,fallen;
	
	public void checkCorners(double x, double y) {

		int leftcol = (int) (x) / 16;
		int rightcol = (int) (x + width) / 16;
		int toprow = (int) (y) / 16;
		int botrow = (int) (y + height) / 16;

		tl = PlayState.map.isSolid(toprow, leftcol);
		tr = PlayState.map.isSolid(toprow, rightcol);
		bl = PlayState.map.isSolid(botrow, leftcol);
		br = PlayState.map.isSolid(botrow, rightcol);

	}
	
	public void checkInvis(double x, double y) {

		int leftcol = (int) (x) / 16;
		int rightcol = (int) (x + width) / 16;
		int toprow = (int) (y) / 16;
		int botrow = (int) (y + height) / 16;

		invis = PlayState.map.isInvis(botrow, leftcol)||PlayState.map.isInvis(botrow, rightcol);
	}
	
	public void checkAlive(double x, double y) {

		int leftcol = (int) (x) / 16;
		int rightcol = (int) (x + width) / 16;
		int toprow = (int) (y) / 16;
		int botrow = (int) (y + height) / 16;

		alive = PlayState.map.doesHurt(toprow, leftcol)||PlayState.map.doesHurt(toprow, rightcol)|| 
				PlayState.map.doesHurt(botrow, leftcol)||PlayState.map.doesHurt(botrow, rightcol);
		alive = !alive;
	}
	
	public void checkTileMapCollision(){
		
		xdest = x+dx;
		ydest = y+dy;
		
		xtemp = x;
		ytemp = y;
		
		checkCorners(x,ydest);
		if(dy<0){
			if(tl||tr){
				dy = 0;
			}else{
				ytemp+=dy;
			}
		}else if(dy>0){
			if(bl||br){
				dy = 0;
				falling = false;
			}else{
				ytemp+=dy;
				fallen += dy;
			}
		}
		checkCorners(xdest,y);
		if(dx < 0){
			if(tl||bl){
				dx = 0;
			}else{
				xtemp+=dx;
				dist -= dx;
			}
		}else if(dx > 0){
			if(tr||br){
				dx = 0;
			}else{
				xtemp+=dx;
				dist+=dx;
			}
		}
		
		if(!falling){
			
			checkCorners(x,ydest+1);
			if(!br&&!bl){
				falling = true;
			}
			
		}
		
	}
	
}
