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
public class RightArrow extends Arrow {
    public static final String IMG_FILE = "files/right.png";
    public static final int SIZE = 70;
    public static final int INIT_POS_X = 380;
    public static final int INIT_POS_Y = 600;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 5;
    
    private static BufferedImage img;

    public RightArrow(int d, double x, double y) {
        super(d, (int)x, (int)y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE);

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public RightArrow(int x, int y){
        super(0, 0, 0, x, y, SIZE, SIZE);
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null); 
    }
}
