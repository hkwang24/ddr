/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main
 */
public class Game implements Runnable {
    public void run() {
        
        //variables for the menu bar
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuEasy;
        JMenuItem menuMedium;
        JMenuItem menuHard;
        
        //creates the menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(171, 198, 255));

        //creates the frame
        final JFrame frame = new JFrame("MONSTER HUNTER");
        frame.setLocation(0, 0);
        
        //creates status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Click the 'Start Game' button to start!");
        status_panel.add(status);
        
        //main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);
        
        //create the difficulty menu
        menu = new JMenu("Difficulty");
        menuBar.add(menu);
        
        //add the easy mode menu item
        menuEasy = new JMenuItem("Easy");
        menuEasy.setBackground(new Color(171, 198, 255));
        menu.add(menuEasy);
        
        //add the easy mode action listener
        menuEasy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.setMode("Easy");
                court.reset();
            }
        });
        
        //add menu separator
        menu.addSeparator();
        
        //add the medium mode menu item
        menuMedium = new JMenuItem("Medium");
        menuMedium.setBackground(new Color(171, 198, 255));
        menu.add(menuMedium);
        
        //add the medium mode action listener
        menuMedium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.setMode("Medium");
                court.reset();
            }
        });
        
        //add menu separator
        menu.addSeparator();

        //add hard mode menu item
        menuHard = new JMenuItem("Hard");
        menuHard.setBackground(new Color(171, 198, 255));
        menu.add(menuHard);
        
        //add the hard mode action listener
        menuHard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.setMode("Hard");
                court.reset();
            }
        });
        
        //set the menu bar in the frame
        frame.setJMenuBar(menuBar);

        //add reset button to the menu bar
        final JButton reset = new JButton("Reset");
        reset.setBackground(new Color(171, 198, 255));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        menuBar.add(reset);
        
        //add instructions button to the menu bar
        String inst = "Welcome to Monster Hunter! To play, simply press the corresponding arrow keys"
        		+ "\nas arrows scroll up the screen. You will deal more damage to monsters the more"
        		+ "\naccurately you press the keys. For example, hitting an up arrow dead on the static"
        		+ "\nup arrow will deal more damage than hitting it slightly off. You will also lose lives"
        		+ "\nif you accidentally hit a key when there is no arrow to destroy or if you miss an arrow."
        		+ "\nYou can change the difficulty of the game in the menu, but doing so will restart"
        		+ "\nthe game. You have 90 seconds to kill as many monsters as you can, unless you terminate"
        		+ "\nthe game early by losing all five lives. Try to beat the high scores!";
        final JButton instructions = new JButton("Instructions");
        instructions.setBackground(new Color(171, 198, 255));
        instructions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, inst);
        	}
        });
        menuBar.add(instructions);
        
        //add high scores button to the menu bar
        String scores = court.getScores();
        final JButton hScores = new JButton("Get High Scores");
        hScores.setBackground(new Color(171, 198, 255));
        hScores.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (scores.length() == 0) {
        			JOptionPane.showMessageDialog(null, "No high scores yet!");
        		} else {
        			JOptionPane.showMessageDialog(null, scores);
        		}
        	}
        });
        menuBar.add(hScores);
        
        //add start game button to the menu bar
        final JButton startGame = new JButton("Start Game");
        startGame.setBackground(new Color(171, 198, 255));
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.setPlayingTrue();
                court.reset();
            }
        });
        menuBar.add(startGame);
        
        //add stop game button to the menu bar
        final JButton stopGame = new JButton("Stop Game");
        stopGame.setBackground(new Color(171, 198, 255));
        stopGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.setPlayingFalse();
            }
        });
        menuBar.add(stopGame);

        //put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //start game
        court.reset();
    }

    //for starting and running game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}