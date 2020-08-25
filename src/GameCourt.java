/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Collections;

/*
 * GameCourt
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	
	//mode
	String gameMode = "Easy";

    //monsters
    private Monster1 monster1;
    private Monster2 monster2;
    
    //the static arrows
    private UpArrow staticUp;
    private DownArrow staticDown;
    private LeftArrow staticLeft;
    private RightArrow staticRight;
    
    //score
    private int score = 0;
    
    //collection of arrows separated by direction of arrow
    private LinkedList<Arrow> leftArrowStream = new LinkedList<Arrow>();
    private LinkedList<Arrow> upArrowStream = new LinkedList<Arrow>();
    private LinkedList<Arrow> downArrowStream = new LinkedList<Arrow>();
    private LinkedList<Arrow> rightArrowStream = new LinkedList<Arrow>();
    
    //list of random integers
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private LinkedList<Integer> timeStream = new LinkedList<Integer>();
    
    //timer counter
    private double timerCount = 0.0;
 
    //which monster is currently appearing
    private boolean isMonster1 = true;
    
    //import high scores
    HighScore highScores = new HighScore();
    
    //number of lives left
    private int lives = 3;
    
    //mistake crosses, if any
    private LinkedList<Cross> mistakes = new LinkedList<Cross>();
    
    //clock
    private int clockCount = 0;

    //whether the game is running
    public boolean playing = false;
    private JLabel status;

    //game constants
    public static final int COURT_WIDTH = 800;
    public static final int COURT_HEIGHT = 600;  

    public GameCourt(JLabel status) {
        //border creation
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        //background color
        setBackground(new Color(197, 228, 236));

        //create timer
        Timer timer = new Timer(35, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick(); 
                timerCount += 0.1;
            }
        });
        //start the timer
        timer.start();
        
        //create clock timer
        Timer clock = new Timer(1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clockCount++;
        	}
        });
        clock.start();

        //focus keyboard on court
        setFocusable(true);

        //add key listeners
        addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
    
                //handles left arrow key press
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                	boolean didIntersect = false;
                    for (int i = 0; i < leftArrowStream.size(); i++){
                    	Arrow x = leftArrowStream.get(i);
                        if (x.intersects(staticLeft)) {
                            if (isMonster1){
                                monster1.changeHealth(x.kindOfIntersection(staticLeft));
                            } else {
                                monster2.changeHealth(x.kindOfIntersection(staticLeft));
                            } 
                            leftArrowStream.remove(x);
                            didIntersect = true;
                            break;
                        } 
                    } 
                    if (!didIntersect) {
                    	lives--;
                    	mistakes.add(new Cross(90, 50));
                    }
                    
                  //handles right arrow key press
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	boolean didIntersect = false;
                	for (int i = 0; i < rightArrowStream.size(); i++){
                    	Arrow x = rightArrowStream.get(i);
                        if (x.intersects(staticRight)) {
                            if (isMonster1){
                                monster1.changeHealth(x.kindOfIntersection(staticRight));
                            } else {
                                monster2.changeHealth(x.kindOfIntersection(staticRight));
                            }
                            rightArrowStream.remove(x);
                            didIntersect = true;
                            break;
                        }    
                    } 
                	if (!didIntersect) {
                    	lives--;
                    	mistakes.add(new Cross(390, 50));
                    }
                	
                  //handles down arrow key press
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                	boolean didIntersect = false;
                	for (int i = 0; i < downArrowStream.size(); i++){
                    	Arrow x = downArrowStream.get(i);
                        if (x.intersects(staticDown)) {
                            if (isMonster1){
                                monster1.changeHealth(x.kindOfIntersection(staticDown));
                            } else {
                                monster2.changeHealth(x.kindOfIntersection(staticDown));
                            }
                            downArrowStream.remove(x);
                            didIntersect = true;
                            break;
                        }
                    } 
                	if (!didIntersect) {
                    	lives--;
                    	mistakes.add(new Cross(290, 50));
                    }
                	
                  //handles up arrow key press
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	boolean didIntersect = false;
                	for (int i = 0; i < upArrowStream.size(); i++){
                    	Arrow x = upArrowStream.get(i);
                        if (x.intersects(staticUp)) {
                            if (isMonster1){
                                monster1.changeHealth(x.kindOfIntersection(staticUp));
                            } else {
                                monster2.changeHealth(x.kindOfIntersection(staticUp));
                            }
                            upArrowStream.remove(x);
                            didIntersect = true;
                            break;
                        }    
                    } 
                	if (!didIntersect) {
                    	lives--;
                    	mistakes.add(new Cross(190, 50));
                    }
                }
            }
        });

       this.status = status;
        
    }

    //reset game to initial state
    public void reset() {

        //reset lives
        lives = 3;
        
        //reset timer
        timerCount = 0.0;
    	
        //reset monsters
        monster1 = new Monster1(COURT_WIDTH, COURT_HEIGHT);
        monster2 = new Monster2(COURT_WIDTH, COURT_HEIGHT);
        monster1.setHealth(78);
        monster2.setHealth(78);
        
        //clear list of unique random integers
        list.clear();
        timeStream.clear();
        
        //clear list of mistakes
        mistakes.clear();
        
        //clear collection of arrows
        upArrowStream.clear();
        downArrowStream.clear();
        leftArrowStream.clear();
        rightArrowStream.clear();
        
        //reset static arrows
        staticLeft = new LeftArrow(80, 50);
        staticUp = new UpArrow(180, 50);
        staticDown = new DownArrow(280, 50);
        staticRight = new RightArrow(380, 50);
        
        //populate list of unique random integers
        for (int i = 0; i < 400; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < 400; i++) {
            timeStream.add(list.get(i));
        }
        
        //reset all arrows
        for (int x = 0; x < 50; x++){
            Arrow newArrow = new UpArrow(timeStream.get(x), 0.0, 5.0);
            if (gameMode.equals("Easy")) {
            	newArrow.setVy(10);
            } else if (gameMode.equals("Medium")) {
            	newArrow.setVy(14);
            } else if (gameMode.equals("Hard")) {
            	newArrow.setVy(20);
            }
            upArrowStream.add(newArrow);
        }        
        for (int x = 50; x < 100; x++){
            Arrow newArrow = new DownArrow(timeStream.get(x), 0.0, 5.0);
            if (gameMode.equals("Easy")) {
            	newArrow.setVy(10);
            } else if (gameMode.equals("Medium")) {
            	newArrow.setVy(14);
            } else if (gameMode.equals("Hard")) {
            	newArrow.setVy(20);
            }
            downArrowStream.add(newArrow);
        }           
        for (int x = 100; x < 150; x++){
            Arrow newArrow = new LeftArrow(timeStream.get(x), 0.0, 5.0);
            if (gameMode.equals("Easy")) {
            	newArrow.setVy(10);
            } else if (gameMode.equals("Medium")) {
            	newArrow.setVy(14);
            } else if (gameMode.equals("Hard")) {
            	newArrow.setVy(20);
            }
            leftArrowStream.add(newArrow);
        }        
        for (int x = 150; x < 300; x++){
            Arrow newArrow = new RightArrow(timeStream.get(x), 0.0, 5.0);
            if (gameMode.equals("Easy")) {
            	newArrow.setVy(10);
            } else if (gameMode.equals("Medium")) {
            	newArrow.setVy(14);
            } else if (gameMode.equals("Hard")) {
            	newArrow.setVy(20);
            }
            rightArrowStream.add(newArrow);
        }
        
        //set score to zero
        score = 0;
        
        //reset clock
        clockCount = 0;
        
        //reset monster to monster1
        isMonster1 = true;

        //focus keyboard on component
        requestFocusInWindow();
    }
    
    //start the game
    public void setPlayingTrue() {
    	playing = true;
    }
    
    //quick stop the game
    public void setPlayingFalse() {
    	playing = false;
    	status.setText("Game over!");
    	String n = JOptionPane.showInputDialog(null, "Please input your name. Restart the app to see if you made the high scores!");
        highScores.addScore(n, gameMode, score);
        reset();
    }
    
    //get high scores
    public String getScores() {
    	return highScores.getHighScores();
    }
    
    //lets user change mode
    public void setMode(String mode){
        if (mode.equals("Easy")){
        	gameMode = "Easy";
        } else if (mode.equals("Medium")){
        	gameMode = "Medium";
        } else if (mode.equals("Hard")){
        	gameMode = "Hard";
        }
    }

    //called every time the timer triggers
    void tick() {
        if (playing) {
        	
            //animate the monsters
            monster1.move();
            monster2.move();
            
            //animate a red cross for a short time if a mistake was made
            for (int i = 0; i < mistakes.size(); i++) {
            	Cross x = mistakes.get(i);
            	if (x.getLength() > 0) {
            		x.move();
            	} else {
            		mistakes.remove(x);
            		i--;
            	}
            }
            
            //move the arrows
            LinkedList<Arrow> toRemoveUp = new LinkedList<Arrow>();
            for (Arrow x : upArrowStream){
                if (x.isInBounds() && (int)timerCount > x.getDeploy()){
                    x.move();
                } else if (!x.isInBounds()) {
                	toRemoveUp.add(x);
                	lives--;
                	mistakes.add(new Cross(190, 50));
                }
            }
            upArrowStream.removeAll(toRemoveUp);
            
            LinkedList<Arrow> toRemoveDown = new LinkedList<Arrow>();
            for (Arrow x : downArrowStream){
                if (x.isInBounds() && (int)timerCount > x.getDeploy()){
                    x.move();
                } else if (!x.isInBounds()) {
                	toRemoveDown.add(x);
                	lives--;
                	mistakes.add(new Cross(290, 50));
                }
            }
            downArrowStream.removeAll(toRemoveDown);
            
            LinkedList<Arrow> toRemoveLeft = new LinkedList<Arrow>();
            for (Arrow x : leftArrowStream){
                if (x.isInBounds() && (int)timerCount > x.getDeploy()){
                    x.move();
                } else if (!x.isInBounds()) {
                	toRemoveLeft.add(x);
                	lives--;
                	mistakes.add(new Cross(90, 50));
                }
            }
            leftArrowStream.removeAll(toRemoveLeft);
            
            LinkedList<Arrow> toRemoveRight = new LinkedList<Arrow>();
            for (Arrow x : rightArrowStream){
                if (x.isInBounds() && (int)timerCount > x.getDeploy()){
                    x.move();
                } else if (!x.isInBounds()) {
                	toRemoveRight.add(x);
                	lives--;
                	mistakes.add(new Cross(390, 50));
                }
            }
            rightArrowStream.removeAll(toRemoveRight);
            
            //check to see whether the monster is dead
            if (isMonster1 && monster1.getHealth() <= 0){
                isMonster1 = !isMonster1;
                score++;
                monster1.setHealth(78);
            }
            if (!isMonster1 && monster2.getHealth() <= 0){
                isMonster1 = !isMonster1;
                score++;
                monster2.setHealth(78);
            }
            
            //reset status text
            int timeLeft = 60 - clockCount;
            status.setText("Game mode = " + gameMode + " | Lives = " + lives + " | Score = " + score + " | Time left = " + timeLeft);
            
            //end the game if number of lives is zero
            if (lives <= 0 || timeLeft <= 0) {
                playing = false;
                status.setText("Game over!");
                String n = JOptionPane.showInputDialog(null, "Please input your name. Restart the app to see if you made the high scores!");
                highScores.addScore(n, gameMode, score);
                reset();
            }
            
            repaint();
        }
    }
    
    public boolean isPlaying() {
    	return playing;
    }
    
    public void setLives(int x) {
    	lives = x;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw static arrows
        staticUp.draw(g);
        staticDown.draw(g);
        staticLeft.draw(g);
        staticRight.draw(g);
        
        //draw crosses, if any
        for (Cross x : mistakes) {
        	x.draw(g);
        }
        
        //draw arrows
        for (Arrow x : upArrowStream){
            x.draw(g);
        }
        for (Arrow x : downArrowStream){
            x.draw(g);
        }
        for (Arrow x : leftArrowStream){
            x.draw(g);
        }
        for (Arrow x : rightArrowStream){
            x.draw(g);
        }
        
        //draw either monster
        if (isMonster1){
            monster1.draw(g);
        } else {
            monster2.draw(g);
        }
    }
    
    

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}