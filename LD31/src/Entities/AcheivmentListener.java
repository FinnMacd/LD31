package Entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.Inputs;
import Main.PlayState;
import Screen.Button;

public class AcheivmentListener {
	
	private Player player;
	
	DisplayText text[] = new DisplayText[10];
	
	private Acheivment[] acheivments= {
			new Acheivment("First steps",0.01,"Travel 1m"),// 					0
			new Acheivment("Profesional walker",100,"Travel 100m"),//			1
			new Acheivment("vetran walker",300,"Travel 300m"),//				2
			new Acheivment("Up, up, and away",1,"Jump 1 time"),//				3
			new Acheivment("Super quads",10,"Jump 10 times"),//					4
			new Acheivment("Moon and back",100,"Jump 100 times"),//				5
			new Acheivment("Setback",1,"Die 1 time"),//							6
			new Acheivment("Get over it",10,"Die 10 times"),//					7
			new Acheivment("Mortician",30,"Die 30 times"),//					8
			new Acheivment("Glider",10,"Fall 10m"),//							9
			new Acheivment("Fallen one",100,"Fall 100m"),//						10
			new Acheivment("Strong kneecaps",1000,"Fall 1000m"),//				11
			new Acheivment("Unappreciative",false,"Mute Sound"),//				12
			new Acheivment("Much Better",true,"Turn on sound"),//				13
			new Acheivment("Nice start",9,"10% acheived"),//					14
			new Acheivment("Further",19,"20% acheived"),//						15
			new Acheivment("Still early on",29,"30% acheived"),//				16
			new Acheivment("Bored yet?",39,"40% acheived"),//					17
			new Acheivment("Halfway",49,"50% acheived"),//						18
			new Acheivment("You can do it",59,"60% acheived"),//				19
			new Acheivment("Tell your friends",69,"70% acheived"),//			20
			new Acheivment("Nearly there",79,"80% acheived"),//					21
			new Acheivment("So close",89,"90% acheived"),//						22
			new Acheivment("Too much time",97,"100% acheived"),//				23
			new Acheivment("Hesitant?",5,"Stay still for 5s"),//				24
			new Acheivment("Hesitant.",10,"Stay still for 10s"),//				25
			new Acheivment("PLAY THE GAME!",20,"Stay still for 20s"),//			26
			new Acheivment("WHEEEEEEE!",5,"Fall for 5s"),//						27
			new Acheivment("BELLY FLOP!!",10,"Fall for 10s"),//					28
			new Acheivment("PLAY THE GAME!",20,"Fall for 20s"),//				29
			new Acheivment("Losing focus",false,"Click away from screen"),//	30
			new Acheivment("Back again",true,"Click back on screen"),//			31
			new Acheivment("Terminal velocity",32,"Fall as fast as you can"),//	32
			new Acheivment("Vertical confusion",1f,true,"Press up and down"),//	33
			new Acheivment("Horizontal confusion",1f,true,"Press left and right"),//34
			new Acheivment("Total confusion",1f,true,"Press all direction keys"),//35
			new Acheivment("ABCD...",1,true,"Press all dem keys"),//			36
			new Acheivment("The bigger, the better",true,"Enlargen screen"),//	37
			new Acheivment("Back to normal",true,"Restore screen size"),//		38
			new Acheivment("1234...",1f,true,"Press all number keys"),//		39
			new Acheivment("What?",true,"Find hidden platform"),//				40
			new Acheivment("Secrets!",true,"Find a hidden object"),//			41
			new Acheivment("Happy star",true,"Find a hidden object"),//			42
			new Acheivment("Why?",true,"Find a hidden object"),//				43
			new Acheivment("Unsanitary",3,"Have 3 corpses on screen"),//		44
			new Acheivment("Graveyard",7,"Have 7 corpses on screen"),//			45
			new Acheivment("Party",12,"Have 12 corpses on screen"),//			46
			new Acheivment("Nice Star",true,"Find a hidden object"),//			47
			new Acheivment("Super Corpse",16,"Have a corpse fall fast"),//			48
	};
	
	public double tot = acheivments.length,now,perc = 1;
	
	public AcheivmentListener(Player p){
		player = p;
		for(int i = 0; i < 10; i++){
			text[i] = new DisplayText(10,450-65*i,0.5f);
		}
	}
	
	public void update(){
		
		acheivments[0].update(player.dist/16);
		acheivments[1].update(player.dist/16);
		acheivments[2].update(player.dist/16);
		acheivments[3].update(player.jumps);
		acheivments[4].update(player.jumps);
		acheivments[5].update(player.jumps);
		acheivments[6].update(player.died);
		acheivments[7].update(player.died);
		acheivments[8].update(player.died);
		acheivments[9].update(player.fallen/16);
		acheivments[10].update(player.fallen/16);
		acheivments[11].update(player.fallen/16);
		acheivments[12].update(Button.yes);
		acheivments[13].update(Button.good);
		acheivments[14].update(perc);
		acheivments[15].update(perc);
		acheivments[16].update(perc);
		acheivments[17].update(perc);
		acheivments[18].update(perc);
		acheivments[19].update(perc);
		acheivments[20].update(perc);
		acheivments[21].update(perc);
		acheivments[22].update(perc);
		acheivments[23].update(perc);
		acheivments[24].update(PlayState.player.stillTime/1000);
		acheivments[25].update(PlayState.player.stillTime/1000);
		acheivments[26].update(PlayState.player.stillTime/1000);
		acheivments[27].update(PlayState.player.fallTime/1000);
		acheivments[28].update(PlayState.player.fallTime/1000);
		acheivments[29].update(PlayState.player.fallTime/1000);
		acheivments[30].update(Inputs.focus);
		acheivments[31].update(Inputs.gainedF);
		acheivments[32].update(PlayState.player.dy);
		boolean[] b1 = {Inputs.up,Inputs.down};
		acheivments[33].update(b1);
		boolean[] b2 = {Inputs.left,Inputs.right};
		acheivments[34].update(b2);
		boolean[] b3 = {Inputs.left,Inputs.right,Inputs.up,Inputs.down};
		acheivments[35].update(b3);
		acheivments[36].update(Inputs.keys);
		acheivments[37].update(Inputs.big);
		acheivments[38].update(Inputs.small);
		acheivments[39].update(Inputs.nums);
		acheivments[40].update(PlayState.player.invis);
		acheivments[41].update(PlayState.star1.found);
		acheivments[42].update(PlayState.star2.found);
		acheivments[43].update(PlayState.star3.found);
		acheivments[44].update(PlayState.player.corpses.size());
		acheivments[45].update(PlayState.player.corpses.size());
		acheivments[46].update(PlayState.player.corpses.size());
		acheivments[47].update(PlayState.star4.found);
		acheivments[48].update(PlayState.player.corpseS);
		
		now = 0;
		for(int i = 0; i < tot;i++){
			if(acheivments[i].isDone())now++;
		}
		
		perc = (now/tot)*100;
		
		for(int i = 0; i < 10; i++){
			text[i].update();
		}
		
	}
	
	public void setText(String n,String d){
		String[] a = {n,d};
		boolean done = false;
		for(int i = 0; i < 10; i++){
			if(text[i].isDone()){
				text[i].setText(a, 4);
				done = true;
				System.out.println("draw something at "+i);
				break;
			}
		}
		if(!done){
			System.out.println("Hey Finn, you need to add more space for acheivments on screen");
			text[0].setText(a, 4);
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.setFont(new Font("TimesNewRoman",1,23));
		g.drawString((int)perc + "%", 570, 20);
		for(int i = 0; i < 10; i++){
			text[i].draw(g);
		}
	}
	
}
