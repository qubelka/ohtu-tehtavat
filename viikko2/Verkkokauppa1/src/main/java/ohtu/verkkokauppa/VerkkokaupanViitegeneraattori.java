package ohtu.verkkokauppa;

public class VerkkokaupanViitegeneraattori implements Viitegeneraattori {

    private static VerkkokaupanViitegeneraattori instanssi;

    public static VerkkokaupanViitegeneraattori getInstance() {
        if (instanssi == null) {
            instanssi = new VerkkokaupanViitegeneraattori();
        }

        return instanssi;
    }
    
    private int seuraava;
    
    private VerkkokaupanViitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
