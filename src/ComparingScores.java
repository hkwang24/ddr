import java.util.Comparator;

public class ComparingScores implements Comparator<Scores>{
	
	public int compare(Scores s1, Scores s2) {

        int one = s1.getScore();
        int two = s2.getScore();

        if (one > two) {
            return -1;
        } else if (one < two) {
            return 1;
        } else {
            return 0;
        }
    }

}
