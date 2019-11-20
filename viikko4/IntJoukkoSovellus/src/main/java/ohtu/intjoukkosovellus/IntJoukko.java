
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,
            OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] lukujono;
    private int alkioidenLkm;
    private static int[] aTaulu;
    private static int[] bTaulu;
    private static IntJoukko joukkoOperaatio;

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetin täytyy olla positiivinen luku.");
        }
        if (kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kasvatuskoon täytyy olla positiivinen luku.");
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukujono.length == 0) {
                int[] taulukkoOld = lukujono;
                lukujono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, lukujono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int apu;
        int poistettavanLuvunIndeksi = etsiPoistettavaLuku(luku);

        if (poistettavanLuvunIndeksi != -1) {
            for (int i = poistettavanLuvunIndeksi; i < alkioidenLkm - 1; i++) {
                apu = lukujono[i];
                lukujono[i] = lukujono[i + 1];
                lukujono[i + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public int etsiPoistettavaLuku(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = 0;
                return i;
            }
        }
        return -1;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String pitempiMerkkijono = "{";
            for (int i = 0; i < alkioidenLkm-1; i++) {
                pitempiMerkkijono += lukujono[i] + ", ";
            }
            pitempiMerkkijono += lukujono[alkioidenLkm-1];
            pitempiMerkkijono += "}";
            return pitempiMerkkijono;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        luoTaulut(a,b);
        for (int i = 0; i < aTaulu.length; i++) {
            joukkoOperaatio.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            joukkoOperaatio.lisaa(bTaulu[i]);
        }
        return joukkoOperaatio;
    }

    private static void luoTaulut(IntJoukko a, IntJoukko b) {
        joukkoOperaatio = new IntJoukko();
        aTaulu = a.toIntArray();
        bTaulu = b.toIntArray();
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        luoTaulut(a,b);
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    joukkoOperaatio.lisaa(bTaulu[j]);
                }
            }
        }
        return joukkoOperaatio;
    }

    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        luoTaulut(a,b);
        for (int i = 0; i < aTaulu.length; i++) {
            joukkoOperaatio.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            joukkoOperaatio.poista(bTaulu[i]);
        }
        return joukkoOperaatio;
    }
}
