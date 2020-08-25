/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.Graphics;

/*
 * Arrow
 */
public abstract class Arrow {

    //current position
    private int px; 
    private int py;

    //size
    private int width;
    private int height;

    //velocity
    private int vx;
    private int vy;
    
    //when the object starts moving
    private int toDeploy;

    public Arrow(int d, int vx, int vy, int px, int py, int width, int height) {
        this.toDeploy = d;
        this.vx = vx;
        this.vy = vy;
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
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getDeploy(){
        return this.toDeploy;
    }

    //changers
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    //moves object by its velocity
    public void move() {
        this.py -= this.vy;
    }

    //determine intersection
    public boolean intersects(Arrow that) {
        return (this.px + this.width >= that.px
            && this.py + this.height >= that.py
            && that.px + that.width >= this.px 
            && that.py + that.height >= this.py);
    }

    //how accurate the intersection is
    public int kindOfIntersection(Arrow that){
        if (this.py + this.vy >= that.py - 5 && this.py + this.vy <= that.py + 10){
            return 20;
        } else {
            return 10;
        }
    }
    
    //sees if the object is in bounds
    public boolean isInBounds(){
        return (this.py > -80);
    }
    
    //sees whether two objects will intersect in the next time frame
    public boolean willIntersect(Arrow that) {
        int thisNextX = this.px + this.vx;
        int thisNextY = this.py + this.vy;
        int thatNextX = that.px + that.vx;
        int thatNextY = that.py + that.vy;
    
        return (thisNextX + this.width >= thatNextX
            && thisNextY + this.height >= thatNextY
            && thatNextX + that.width >= thisNextX 
            && thatNextY + that.height >= thisNextY);
    }

    //draw method
    public abstract void draw(Graphics g);
}
