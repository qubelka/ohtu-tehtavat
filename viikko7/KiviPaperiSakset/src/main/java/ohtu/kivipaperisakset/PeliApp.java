package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.domain.TekoalyParannettu;
import ohtu.kivipaperisakset.domain.TekoalyPerus;
import ohtu.kivipaperisakset.domain.Tuomari;
import ohtu.kivipaperisakset.pelit.KPSPeli;
import ohtu.kivipaperisakset.pelit.PeliCreator;

public class PeliApp {
    private final String infoMessage = "peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s";

    public void kaynnista() {
        KonsoliIO io = new KonsoliIO();

        while (true) {
            Tuomari tuomari = new Tuomari();
            TekoalyPerus tekoalyPerus = new TekoalyPerus();
            TekoalyParannettu parannettuTekoaly = new TekoalyParannettu(20);

            io.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = io.read();
            if (vastaus.endsWith("a")) {
                io.println(infoMessage);
                KPSPeli kaksinpeli = PeliCreator.luoKPSPelaajaVsPelaaja(io, tuomari);
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                io.println(infoMessage);
                KPSPeli yksinpeli = PeliCreator.luoTekoalyPeli(io, tuomari, tekoalyPerus);
                yksinpeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                io.println(infoMessage);
                KPSPeli pahaYksinpeli = PeliCreator.luoTekoalyPeli(io, tuomari, parannettuTekoaly);
                pahaYksinpeli.pelaa();
            } else {
                break;
            }
        }
    }
}
