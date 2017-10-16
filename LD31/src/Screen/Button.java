package Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.Inputs;

public class Button {

    private BufferedImage image1,image2;

	private int x, y, width, height;
    private int mx=0, my=0;

    private boolean hover = false, clicked = false;
    public static boolean yes = true,good = false;

    public Button(int x, int y) {
        
        this.x = x;
        this.y = y;
        image1 = Images.s1;
        image2 = Images.s2;
        width = image1.getWidth();
        height = image2.getHeight();
    }

    public void update() {
    	updateInputs();
        if(mx>x&&mx<x+width&&my>y&&my<y+height){
            hover=true;
        }else{
            hover = false;
        }
    }
    
    public void updateInputs(){
    	if(hover && Inputs.mousec){
        	Inputs.mousec = false;
        	yes = !yes;
        	if(yes)good = true;
        }
    	mx = Inputs.mx;
    	my = Inputs.my;
    }
    
    public void draw(Graphics2D g) {
        if(yes)g.drawImage(image1, x, x, null);
        else g.drawImage(image2, x, x, null);
    }
    
}
