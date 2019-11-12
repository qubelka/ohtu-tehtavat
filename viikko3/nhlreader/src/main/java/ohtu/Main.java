package ohtu;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd hh:mm:ss z yyyy");
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Europe/Helsinki");
        ZonedDateTime zdt = ZonedDateTime.of(now, zoneId);

        System.out.println("Players from FIN " + dtf.format(zdt));
        System.out.println();
        Arrays.stream(players)
                .filter(player -> player.getNationality().equals("FIN"))
                .sorted((player1, player2) -> {return player2.getPoints()-player1.getPoints();})
                .forEach(player -> System.out.println(player));
    }
}
