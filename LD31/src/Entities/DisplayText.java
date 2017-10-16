package Entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Main.GamePanel;

public class DisplayText{
	
	private String[] textA;
	private String text;
	
	private long start,elapsed;
	
	private Font font;
	
	private boolean done = true,ary,started;
	
	private float alpha,setalpha;
	
	private int delay;
	
	private int x,y;
	
	public DisplayText(int x, int y,float alpha){
		
		font = new Font("TimesNewRoman",Font.BOLD,18);
		ary = true;
		setalpha = alpha;
		this.x = x;
		this.y = y;
	
	}
	
	public void setText(String[] text,int delay){
		this.textA = text;
		done = false;
		started = true;
		ary = true;
		this.delay = delay;
		alpha = setalpha;
	}
	
	public void setText(String text){
		this.text = text;
		done = false;
		started = true;
		ary = false;
		delay = 6;
		alpha = setalpha;
	}
	
	public void update(){
		
		if(started){
			started = false;
			start = System.nanoTime();
		}
		
		if(done)return;
		
		elapsed = System.nanoTime()-start;
		
		if(elapsed/1000000000 >= delay){
			
			alpha -= 0.009f;
			
		}
		
		if(alpha <= 0)done = true;
		
	}
	
	public void draw(Graphics2D g){
		
		if(done)return;
		
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	    g.setComposite(c);
		
	    FontMetrics metrics = g.getFontMetrics(font);
	    
	    g.setFont(font);
	    
	    if(ary){
	    	
	    	int l = metrics.getHeight();
	    	/*
	    	int w = metrics.stringWidth(textA[0]);
	    	g.drawString(textA[0], 60-w/2, 40);
	    	
	    	w = metrics.stringWidth(textA[1]);
	    	g.drawString(textA[1], 60-x/2, 80);*/

	    	g.setColor(Color.green);
	    	g.fillRect(x-5, (int)(y-l*1.6),240, (int)(l*2.65));
	    	g.setColor(Color.black);
	    	
	    	g.drawString(textA[0], x, y-l/2);
	    	g.drawString(textA[1], x, y+l/2);
	    	
	    	
	    }else{
	    	
	    	/*int l= metrics.getHeight();
	    	
	    	int w = metrics.stringWidth(text);
	    	g.drawString(text, GamePanel.WIDTH/2-w/2, GamePanel.HEIGHT/2-y/2);
	    	*/
	    }
	    
	    c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
	    g.setComposite(c);
	    
	}
	
	public boolean isDone(){return done;}
	
}
