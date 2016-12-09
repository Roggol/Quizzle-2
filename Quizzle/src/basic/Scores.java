package basic;

public class Scores {
    private int score;
    private String naam;

    public int getScore() {
        return score;
    }

    public String getNaam() {
        return naam;
    }

    public Scores(String naam, int score) {
        this.score = score;
        this.naam = naam;
    }
}
