package ohtu;

public class TennisGame {
    private Player firstPlayer;
    private Player secondPlayer;
    private Score score;

    public TennisGame(String nameFirstPlayer, String nameSecondPlayer) {
        this.firstPlayer = new Player(nameFirstPlayer);
        this.secondPlayer = new Player(nameSecondPlayer);
        this.score = new Score(this.firstPlayer, this.secondPlayer);

    }

    public void wonPoint(String playerName) {
        if (firstPlayer.getName().equals(playerName)) {
            firstPlayer.addPoints();
        } else {
            secondPlayer.addPoints();
        }
    }

    public String getScore() {
        switch (score.getScoreSituation()) {
            case "equals":
                if (firstPlayer.getNameOfPoints().equals("Deuce")) {
                    return firstPlayer.getNameOfPoints();
                }
                return firstPlayer.getNameOfPoints() + "-All";
            case "minimumPointsReceived":
                if (score.checkForWinner()) {
                    return "Win for " + score.getLeadingPlayer().getName();
                }

                if (score.checkForAdvantage()) {
                    return "Advantage " + score.getLeadingPlayer().getName();
                }
            case "differs" :
                return firstPlayer.getNameOfPoints() + "-" + secondPlayer.getNameOfPoints();
            default :
                return "No score information was found.";
        }
    }
}