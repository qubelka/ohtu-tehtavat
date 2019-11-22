package ohtu;

import java.util.Objects;

public class Player {
    private Point totalPoints;
    private String name;

    public Player(String name) {
        this.name = name;
        this.totalPoints = new Point(0);
    }

    public void addPoints() {
        totalPoints.addPoints();
    }

    public int getTotalPoints() {
        return totalPoints.getPoints();
    }

    public String getNameOfPoints() {
        return totalPoints.pointsName();
    }

    public String getName() {
        return name;
    }

    public boolean hasReceivedMinimumNumberOfPoints() {
        return getTotalPoints() >= 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
