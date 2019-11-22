package ohtu;

public class Point {
    private int points;

    public Point(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints() {
        points += 1;
    }

    public String pointsName() {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }
}
