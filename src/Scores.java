import java.io.Serializable;

public class Scores implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String mode;
	private int score;
	
    public Scores(String n, String m, int s) {
        name = n;
        mode = m;
        score = s;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    
    public String getMode() {
        return mode;
    }

}