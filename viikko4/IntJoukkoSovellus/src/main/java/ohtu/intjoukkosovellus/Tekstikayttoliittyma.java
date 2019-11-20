package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Tekstikayttoliittyma {

    private static IntJoukko A, B, C;
    private static Scanner lukija;

    public Tekstikayttoliittyma(IntJoukko A, IntJoukko B, IntJoukko C, Scanner lukija) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.lukija = lukija;
    }

    private static String lue() {
        return lukija.nextLine();
    }

    private static IntJoukko mikaJoukko() {
        while(true) {
            String luettu = lue();
            if (luettu.equalsIgnoreCase("a")) {
                return A;
            } else if (luettu.equalsIgnoreCase("b")) {
                return B;
            } else if (luettu.equalsIgnoreCase("c")) {
                return C;
            }
            System.out.println("Virheellinen joukko! " + luettu);
            System.out.print("Yritä uudelleen!");
        }
    }

    private static void lisaa() {
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        int lisattavaLuku = Integer.valueOf(lukija.nextLine());
        joukko.lisaa(lisattavaLuku);
    }

    private static void yhdiste() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko yhdiste = IntJoukko.yhdiste(aJoukko, bJoukko);
        System.out.println("A yhdiste B = " + yhdiste.toString());
    }

    private static void leikkaus() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko leikkaus = IntJoukko.leikkaus(aJoukko, bJoukko);
        System.out.println("A leikkaus B = " + leikkaus.toString());
    }

    private static void erotus() {
        IntJoukko aJoukko, bJoukko, erotus;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        erotus = IntJoukko.erotus(aJoukko, bJoukko);
        System.out.println("A erotus B = " + erotus.toString());
    }

    private static void poista() {
        System.out.print("Mistä joukosta? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku poistetaan? ");
        int poistettavaLuku = Integer.valueOf(lukija.nextLine());
        joukko.poista(poistettavaLuku);
    }

    private static void kuuluu() {
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku? ");
        int etsittavaLuku = Integer.valueOf(lukija.nextLine());
        boolean kuuluuko = joukko.kuuluu(etsittavaLuku);
        if (kuuluuko) {
            System.out.println(etsittavaLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(etsittavaLuku + " ei kuulu joukkoon ");
        }
    }

    public static void kaynnista() {
        String luettu;

        System.out.println("Tervetuloa joukkolaboratorioon!\n" +
                "Käytössäsi ovat joukot A, B ja C.\n" +
                "Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).\n" +
                "Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.\n");

        while (true) {
            luettu = lukija.nextLine();
            if (luettu.equals("lisää") || luettu.equals("li")) {
                lisaa();
            } else if (luettu.equalsIgnoreCase("poista") || luettu.equalsIgnoreCase("p")) {
                poista();
            } else if (luettu.equalsIgnoreCase("kuuluu") || luettu.equalsIgnoreCase("k")) {
                kuuluu();
            } else if (luettu.equalsIgnoreCase("yhdiste") || luettu.equalsIgnoreCase("y")) {
                yhdiste();
            } else if (luettu.equalsIgnoreCase("leikkaus") || luettu.equalsIgnoreCase("le")) {
                leikkaus();
            } else if (luettu.equalsIgnoreCase("erotus") || luettu.equalsIgnoreCase("e")) {
                erotus();
            } else if (luettu.equalsIgnoreCase("A")) {
                System.out.println(A);
            } else if (luettu.equalsIgnoreCase("B")) {
                System.out.println(B);
            } else if (luettu.equalsIgnoreCase("C")) {
                System.out.println(C);
            } else if (luettu.equalsIgnoreCase("lopeta") || luettu.equalsIgnoreCase("quit") || luettu.equalsIgnoreCase("q")) {
                System.out.println("Lopetetaan, moikka!");
                break;
            } else {
                System.out.println("Virheellinen komento! " + luettu);
            }

            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
        }
    }
}
