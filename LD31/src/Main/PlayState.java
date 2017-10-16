package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Audio.PlaySound;
import Entities.Player;
import Entities.Star;
import Screen.Background;
import Screen.Button;
import Screen.Images;
import Screen.Map;

public class PlayState{
	
	public static Map map = new Map();
	Background bg;
	
	public static Player player;
	
	private static PlaySound music;
	
	public static Star star1,star2,star3,star4;
	
	private Button btn;
	
 	public PlayState() {
 		player = new Player();
		map.loadMap("/Map1");
		bg = new Background("/Blank.png");
		music = new PlaySound("/Sound/music.mid");
		btn =new Button(10,10);
		star1 = new Star(28*16,4*16,Images.star);
		star2 = new Star(13*16,23*16,Images.star);
		star3 = new Star(36*16,14*16,Images.star);
		star4 = new Star(6*16,24*16,Images.star);
	}

	public void update() {
		player.update();
		btn.update();
		if(star1.getRect().intersects(player.getRect()))star1.found = true;
		if(star2.getRect().intersects(player.getRect()))star2.found = true;
		if(star3.getRect().intersects(player.getRect()))star3.found = true;
		if(star4.getRect().intersects(player.getRect()))star4.found = true;
		//music.update();
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		map.draw(g);
		btn.draw(g);
		star1.draw(g);
		star2.draw(g);
		star3.draw(g);
		star4.draw(g);
		player.draw(g);
		
		if(Player.ach.perc == 100){
			
			g.setColor(Color.black);
			g.setFont(new Font("TimesNewRoman",1,112));
			g.drawString("You Win!!!", 50, 230);
			
		}
	}
	
}
