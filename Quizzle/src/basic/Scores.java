package basic;

public class Scores {//taken from http://forum.codecall.net/topic/50071-making-a-simple-high-score-system/
    private int score;
    private String user;

    public int getScore() {
        return score;
    }

    public String getUser() {
        return user;
    }

    public Scores(String user, int score) {
        this.score = score;
        this.user = user;
    }
}
//