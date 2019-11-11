package ohtu;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd hh:mm:ss z yyyy");
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Europe/Helsinki");
        ZonedDateTime zdt = ZonedDateTime.of(now, zoneId);
        System.out.println("Players from FIN " + dtf.format(zdt));
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                System.out.println(player);
            }
        }
    }
}
