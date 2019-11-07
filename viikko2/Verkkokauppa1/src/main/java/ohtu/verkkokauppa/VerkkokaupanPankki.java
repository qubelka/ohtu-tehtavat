package ohtu.verkkokauppa;

public class VerkkokaupanPankki implements Pankki {

    private static VerkkokaupanPankki instanssi;

    public static VerkkokaupanPankki getInstance() {
        if (instanssi == null) {
            instanssi = new VerkkokaupanPankki();
        }

        return instanssi;
    }
    private Kirjanpito kirjanpito;

    public VerkkokaupanPankki() {
        kirjanpito = Kirjanpito.getInstance();
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
