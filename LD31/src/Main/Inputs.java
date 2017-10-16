package Main;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Inputs implements KeyListener,FocusListener,MouseMotionListener,MouseListener{
	
	public static volatile boolean left = false,right = false,up = false,down = false,focus = true,space = false,mousec,gainedF,big = false,small = false;
	
	private GamePanel gp;
	
	public static int mx,my;
	
	public static boolean[] keys = new boolean[26]; 
	public static boolean[] nums = new boolean[10]; 
	
	public Inputs(GamePanel gp){
		this.gp = gp;
	}
	
	public void focusGained(FocusEvent e) {
		if(!focus)gainedF = true;
		focus = true;
		left  = right = up = down = false;
	}

	public void focusLost(FocusEvent e) {
		focus = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_P && GamePanel.scale == 2){
			GamePanel.scale = 1;
			gp.setSize();
			small = true;
		}
		else if(key == KeyEvent.VK_P && GamePanel.scale == 1){
			GamePanel.scale = 2;
			gp.setSize();
			big = true;
		}
		
		if(key == KeyEvent.VK_W||key == KeyEvent.VK_UP)up = true;
		if(key == KeyEvent.VK_S||key == KeyEvent.VK_DOWN)down = true;
		if(key == KeyEvent.VK_D||key == KeyEvent.VK_RIGHT)right = true;
		if(key == KeyEvent.VK_A||key == KeyEvent.VK_LEFT)left = true;
		if(key == KeyEvent.VK_SPACE)space = true;
		
		if(key < 91 && key > 64)keys[key-65] = true;
		if(key < 58 && key > 47)nums[key-48] = true;
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W||key == KeyEvent.VK_UP)up = false;
		if(key == KeyEvent.VK_S||key == KeyEvent.VK_DOWN)down = false;
		if(key == KeyEvent.VK_D||key == KeyEvent.VK_RIGHT)right = false;
		if(key == KeyEvent.VK_A||key == KeyEvent.VK_LEFT)left = false;
		if(key == KeyEvent.VK_SPACE)space = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		mousec = true;
		
	}

	public void mouseReleased(MouseEvent arg0) {
		mousec = false;
		
	}

	public void mouseDragged(MouseEvent arg0) {
		
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

}
