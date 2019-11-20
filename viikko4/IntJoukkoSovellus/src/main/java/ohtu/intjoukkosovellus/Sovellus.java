package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {
        public static void main(String[] args) {
            Scanner lukija = new Scanner(System.in);
            IntJoukko A, B, C;
            A = new IntJoukko();
            B = new IntJoukko();
            C = new IntJoukko();
            Tekstikayttoliittyma tekstikayttoliittyma = new Tekstikayttoliittyma(A, B, C, lukija);
            tekstikayttoliittyma.kaynnista();
        }
}

