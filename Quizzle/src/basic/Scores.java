package basic;

public class Scores {
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