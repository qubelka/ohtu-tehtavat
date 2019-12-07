package ohtu.kivipaperisakset.pelit;

import ohtu.kivipaperisakset.KonsoliIO;
import ohtu.kivipaperisakset.domain.Tuomari;
import ohtu.kivipaperisakset.domain.Tekoaly;

public class KPSTekoaly extends KPSPeli {
    private Tekoaly tekoaly;

    public KPSTekoaly(KonsoliIO io, Tuomari tuomari, Tekoaly tekoaly) {
        super(io, tuomari);
        this.tekoaly = tekoaly;
    }

    @Override
    public void annaTokanSiirto() {
        tokanSiirto = tekoaly.annaSiirto();
        io.println("Tietokone valitsi: " + tokanSiirto);
    }

    @Override
    public void asetaTokanSiirto() {
        annaTokanSiirto();
        tekoaly.asetaSiirto(ekanSiirto);
    }

}