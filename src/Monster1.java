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
 * A basic type of monster.
 */
public class Monster1 extends Monster {
    
    public static final String IMG_FILE = "files/monster1.png";
    public static final int SIZE = 150;
    public static final int INIT_POS_X = 500;
    public static final int INIT_POS_Y = 140;

    private static BufferedImage img;

    public Monster1(int courtWidth, int courtHeight) {
        super(78, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public void changeHealth(int damage){
        if (this.getHealth() > 0){
            this.minusHealth(damage);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
        g.setColor(Color.black);
        g.fillRect(545, 350, 80, 10);
        g.setColor(Color.red);
        g.fillRect(546, 351, this.getHealth(), 8);   
    }
}
