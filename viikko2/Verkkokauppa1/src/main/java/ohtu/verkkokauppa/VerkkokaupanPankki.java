package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class VerkkokaupanPankki implements Pankki {
    private Kirjanpito kirjanpito;

    public VerkkokaupanPankki(Kirjanpito kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
