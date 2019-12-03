package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
//        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );

        Matcher m1 = new And(
                new Not( new HasAtLeast(1, "goals") ),
                new PlaysIn("NYR")
        );

        Matcher m2 = new And(
                new HasFewerThan(1, "goals"),
                new PlaysIn("NYR")
        );

        Matcher m3 = new And (
                new Not(new PlaysIn("PHI"))
        );


        System.out.println("Players who play in PHI, have at least 5 goals and 5 assists: ");
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("");
        System.out.println("Players who play in NYR and have less than 1 goal: ");
        for (Player player : stats.matches(m1)) {
            System.out.println(player);
        }
        System.out.println("");
        System.out.println("Players who play in NYR and have less than 1 goal: ");
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }
        System.out.println("");
        System.out.println("Players who do not play in PHI: ");
        for (Player player : stats.matches(m3)) {
            System.out.println(player);
        }
    }
}
