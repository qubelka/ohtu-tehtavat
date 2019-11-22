package ohtu;

public class Score {
    private static int advantageDifference = 1;
    private Player firstPlayer;
    private Player secondPlayer;

    public Score(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Player getLeadingPlayer() {
        if (firstPlayer.getTotalPoints() > secondPlayer.getTotalPoints()) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    public boolean checkForAdvantage() {
        if (Math.abs(firstPlayer.getTotalPoints()-secondPlayer.getTotalPoints()) == advantageDifference) {
            return true;
        }
        return false;
    }

    public String getScoreSituation() {
        if (firstPlayer.getTotalPoints() == secondPlayer.getTotalPoints()) {
            return "equals";
        } else if (firstPlayer.hasReceivedMinimumNumberOfPoints() || secondPlayer.hasReceivedMinimumNumberOfPoints()) {
            return "minimumPointsReceived";
        } else {
            return "differs";
        }
    }

    public boolean checkForWinner() {
        if (Math.abs(firstPlayer.getTotalPoints()-secondPlayer.getTotalPoints()) >= 2) {
            return true;
        }
        return false;
    }
}

