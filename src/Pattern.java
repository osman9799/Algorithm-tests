public class Pattern {
    private char pattern;
    private int score;

    public Pattern(char pattern, int score) {
        this.pattern = pattern;
        this.score = score;
    }

    public void addScore(int add) {
        this.score += add;
    }
}
