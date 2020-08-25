/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// /**
//  * Menu
//  * 
//  * The main menu screen.
//  * 
//  */
// //add implements KeyListener
// public class MonsterHunter extends JPanel implements KeyListener {

//     //changing these values will change the size of the game, while still remaining functional
//     //within the size limit specified.
//     static int windowWidth = 525;
//     static int windowHeight = 400;
//     JLabel label1 = new JLabel("Test");
//     int randNumb = 0;
//     int squareWidth = 60;
//     int squareHeight = 60;
//     double x = 0, y = 0, velX = 0, velY = 0;
//     int squareYLocation = -squareHeight;
//     private ImageIcon lpp;
//     private ImageIcon upp;
//     private ImageIcon dpp;
//     private ImageIcon rpp;
    

//     boolean numberCreated = false;
//     static boolean gameRunning = false;

//     //generates a random Y value inside the window for the square to spawn at
//     public void generateRandomNumber() {
//         lpp = new ImageIcon ("right.png");
//         upp = new ImageIcon ("up.png");
//         dpp = new ImageIcon ("down.png");
//         rpp = new ImageIcon ("left.png");
//         Random rand = new Random();
//         randNumb = rand.nextInt(5);
//         numberCreated = true;
//     }

//     //paints a black screen, then paints a rectangle on top of the black screen
//     public void paint(Graphics g) {
//         g.setColor(Color.white);
//         g.fillRect(0, 0, windowWidth, windowHeight);
//         g.setColor(Color.black);
//         JLabel label1 = new JLabel("Test");
        
//         g.drawImage(upp.getImage(), 112, 290, 60, 60, null);
//         g.drawImage(dpp.getImage(), 212, 290, 60, 60, null);
//         g.drawImage(lpp.getImage(), 312, 290, 60, 60, null);
//         g.drawImage(rpp.getImage(), 12, 290, 60, 60, null);
//         g.fillRect(425, 20, 75, 329);
        
//         if(randNumb == 1){
//         g.fillRect(12, squareYLocation, squareWidth, squareHeight);
//         }
//         if(randNumb == 2){
//         g.fillRect(112, squareYLocation, squareWidth, squareHeight);
//         }
//         if(randNumb == 3){
//         g.fillRect(212, squareYLocation, squareWidth, squareHeight);
//         }
//         if(randNumb == 4){
//         g.fillRect(312, squareYLocation, squareWidth, squareHeight);
//         }
//     }
//     public void up(){
//         System.out.println("hi");
        
//     }

//     public void down(){
//         System.out.println("hi");
        
//     }

//     public void left(){
//         System.out.println("hi");
        
//     }

//     public void right(){
//       System.out.println("hi");
        
       
//     }


//     public void update() {

//         //calls the generateRandomNumber() method which gives the square a random x value inside the screen
//         if (!numberCreated) {
//             generateRandomNumber();
//         }
//         //moves the squares y coordinate towards the bottom of the screen and stops once it hits the bottom
//         if (squareYLocation <= windowHeight) {
//             squareYLocation++;

//             //resets the x and y location to a new position
//         } else {
//             numberCreated = false;
//             squareYLocation = -squareHeight;
//         }
//     }

//     //sets the while loop to true to start the game
//     public void start() {
//         gameRunning = true;
//     }

//     public static void main(String[] args) throws InterruptedException {

//         MonsterHunter game = new MonsterHunter();
//         JFrame frame = new JFrame();
//         frame.add(game);
//         frame.setVisible(true);
//         frame.setSize(windowWidth, windowHeight);
//         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//         frame.setTitle("Raining Squares");
//         frame.setResizable(false);
//         frame.setLocationRelativeTo(null);
// //add this line
//         frame.addKeyListener(this);

//         game.start();

//         //updates square position, repaints square, and slows down update and paint speed.
//         while (gameRunning) {
//             game.update();
//             game.repaint();
//             Thread.sleep(1);
            
//         }
//     }
//     public void keyPressed(KeyEvent e){
//         int code = e.getKeyCode();

//         while(code == KeyEvent.VK_UP){
//             up();
//         }

//         if (code == KeyEvent.VK_DOWN){
//             down();
//         }

//         if (code == KeyEvent.VK_LEFT){
//             left();
//         }

//         if (code == KeyEvent.VK_RIGHT){
//             right();
//         }
//     }

//     public void keyTyped(KeyEvent e){}

//     public void keyReleased(KeyEvent e){

//       velX = 0;
//       velY = 0;
//         int code = e.getKeyCode();

//         if (code == KeyEvent.VK_UP){
//             velY = 0;
//         }
//         if (code == KeyEvent.VK_DOWN){
//             velY = 0;
//         }
//         if (code == KeyEvent.VK_LEFT){
//             velX = 0;
//         }
//         if (code == KeyEvent.VK_RIGHT){
//             velX = 0;
//         }
//     }
// }