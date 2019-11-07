package ohtu.verkkokauppa;

public class VerkkokaupanViitegeneraattori implements Viitegeneraattori {    
    private int seuraava;
    
    public VerkkokaupanViitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
