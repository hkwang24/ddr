/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A basic game object starting in the center of the game court. It is displayed as a
 * type of floating monster.
 */
public class Cross {
	
    public static final String IMG_FILE = "files/x.png";
    private int px;
    private int py;
    private int originalpy;
    private int size = 50;
    private boolean goingUp = true;
    private int length = 25;

    private static BufferedImage img;

    public Cross(int x, int y) {
        px = x;
        py = y;
        originalpy = y;

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    //moves cross up and down
    public void move() {
        if (goingUp){
            this.py += 1;
            if (this.py >= this.originalpy + 10){
                goingUp = false;
            }
        } else {
            this.py -= 1;
            if (this.py <= this.originalpy){
                goingUp = true;
            }
        } 
        length--;
    }

    public void draw(Graphics g) {
        g.drawImage(img, px, py, size, size, null);
    }
    
    public int getLength() {
    	return length;
    }
}