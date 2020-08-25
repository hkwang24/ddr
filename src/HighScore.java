import java.util.*;
import java.io.*;

public class HighScore {
	
	//collection of scores
    private ArrayList<Scores> scores;

    //file to save to
    private static final String readFile = "highScores.dat";

    //making streams
    ObjectOutputStream output = null;
    ObjectInputStream input = null;

    public HighScore() {
        scores = new ArrayList<Scores>();
    }
    
    public ArrayList<Scores> getScores() {
        load();
        sort();
        return scores;
    }
    
    private void sort() {
        ComparingScores comparator = new ComparingScores();
        Collections.sort(scores, comparator);
    }
    
    public void addScore(String name, String mode, int score) {
        load();
        scores.add(new Scores(name, mode, score));
        update();
    }
    
    @SuppressWarnings("unchecked")
	public void load() {
        try {
            input = new ObjectInputStream(new FileInputStream(readFile));
            scores = (ArrayList<Scores>)input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("IO exception.");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception.");
            }
        }
    }
    
    public void update() {
        try {
            output = new ObjectOutputStream(new FileOutputStream(readFile));
            output.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Making a new file.");
        } catch (IOException e) {
            System.out.println("IO exception.");
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("IO exception.");
            }
        }
    }
    
    public String getHighScores() {
    	
        String result = "";
        ArrayList<Scores> scores = getScores();

        for (int i = 0; i < Math.min(5, scores.size()); i++) {
        	Scores x = scores.get(i);
            result += (i + 1) + ". " + x.getName() + ", " + x.getMode() + ", " + x.getScore() + "\n";
        }
        return result;
    }

}
