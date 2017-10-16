package Screen;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Images {
	
	public static BufferedImage player,block,spikes,s1,s2,star,corpse;
	
	public static void loadImages(){
		try{
			player = ImageIO.read(Images.class.getResourceAsStream("/Player.png"));
			block = ImageIO.read(Images.class.getResourceAsStream("/Block.png"));
			spikes = ImageIO.read(Images.class.getResourceAsStream("/Spikes.png"));
			s1 = ImageIO.read(Images.class.getResourceAsStream("/Soun.png"));
			s2 = ImageIO.read(Images.class.getResourceAsStream("/Soun2.png"));
			star = ImageIO.read(Images.class.getResourceAsStream("/Star.png"));
			corpse = ImageIO.read(Images.class.getResourceAsStream("/Corpse.png"));
		}catch(Exception e){
			System.err.println("could not load images.");
		}
		System.out.println("loaded");
	}
	
}
