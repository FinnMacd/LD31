package Entities;

public class Acheivment {
	
	private boolean done = false,goal;
	private String name;
	private String disc;
	private double limit;
	private double perc;
	
	public Acheivment(String s,double l, String d){
		name = s;
		limit = l;
		disc = d;
	}
	
	public Acheivment(String s,boolean g,String d){
		name = s;
		goal = g;
		disc = d;
	}
	
	public Acheivment(String s, double f, boolean g, String d){
		name = s;
		perc = f;
		goal  = g;
		disc = d;
	}
	
	public void update(double i){
		if(done)return;
		if(i >= limit)done = true;
		
		if(done){
			Player.ach.setText(name, disc);
		}
	}
	
	public void update(boolean b){
		if(done)return;
		if(b == goal)done = true;
		
		if(done){
			Player.ach.setText(name, disc);
		}
	}
	
	public void update(boolean[] b){
		if(done)return;
		int tot = b.length;
		int num = 0;
		float comp;
		
		for(int i = 0; i < tot; i++){
			if(b[i] == goal)num++;
		}
		
		comp = num/tot;
		
		if(comp >= perc)done = true;
		
		if(done){
			Player.ach.setText(name, disc);
		}
		
	}
	
	public boolean isDone(){return done;}
	
}
