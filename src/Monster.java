/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */


import java.awt.Graphics;

/** 
 * An object in the game. 
 *
 * Game objects exist in the game court. They have a position, velocity, size and bounds. Their
 * velocity controls how they move; their position should always be within their bounds.
 */
public abstract class Monster {
	
	//whether monster is moving up or down
    private boolean goingUp = true;
    
    //original position
    private int originaly;
    
    //current position
    private int px; 
    private int py;

    //size
    private int width;
    private int height;
    
    //health
    private int health;

    public Monster(int h, int px, int py, int width, int height, int courtWidth, int courtHeight) {
        this.health = h;
        this.originaly = py;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;
    }

    //accessors
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getHealth() {
        return this.health;
    }

    //changers
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }
    
    public void minusHealth(int h) {
        this.health -= h;
    }
    
    public void setHealth(int h){
        this.health = h;
    }

    //moves monster up and down
    public void move() {
        if (goingUp){
            this.py += 1;
            if (this.py >= this.originaly + 30){
                goingUp = false;
            }
        } else {
            this.py -= 1;
            if (this.py <= this.originaly){
                goingUp = true;
            }
        } 
    }

    //draw method
    public abstract void draw(Graphics g);
}
