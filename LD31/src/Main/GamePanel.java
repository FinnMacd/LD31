package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Screen.Images;
import Screen.Tile;

public class GamePanel extends Canvas implements Runnable{
	
	public static volatile int WIDTH = 640, HEIGHT = 480,scale = 1;
	
	private BufferedImage image;
	private Graphics2D g2D;
	
	public static Thread thread;
	
	PlayState game;
	
	private Inputs inputs;
	
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH*scale,HEIGHT*scale));
		requestFocus();
	}
	
	public void setSize(){
		setPreferredSize(new Dimension(WIDTH*scale,HEIGHT*scale));
		Game.frame.pack();
		Game.frame.setLocationRelativeTo(null);
	}
	
	public void start(){
		thread = new Thread(this,"GameLoop");
		thread.start();
	}
	
	public void init(){
		
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g2D = (Graphics2D)image.getGraphics();
		
		Images.loadImages();
		Tile.loadTiles();
		game = new PlayState();
		
		inputs = new Inputs(this);
		
		addFocusListener(inputs);
		addKeyListener(inputs);
		addMouseListener(inputs);
		addMouseMotionListener(inputs);
		setFocusable(true);
	}
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/60;
		double catchUp = 0;
		int frames = 0;
		int updates = 0;
		
		while(true){
			
			long now = System.nanoTime();
			catchUp += (now-lastTime)/ns;
			lastTime = now;
			
			while(catchUp >= 1){
				update();
				updates++;
				catchUp--;
			}
			
			draw();
			drawToScreen();
			frames++;
			
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis()-timer >= 1000){
				timer += 1000;
				updates = frames = 0;
			}
			
		}
	}
	
	public void update(){
		game.update();
	}
	
	public void draw(){
		game.draw(g2D);
	}
	
	public void drawToScreen(){
		
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*scale, HEIGHT*scale, null);
		g.dispose();
		
		bs.show();
		
	}
	
}
