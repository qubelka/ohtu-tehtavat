package ohtu.kivipaperisakset.pelit;

import ohtu.kivipaperisakset.KonsoliIO;
import ohtu.kivipaperisakset.domain.Tuomari;
import ohtu.kivipaperisakset.domain.Tekoaly;

public class PeliCreator extends KPSPeli {

    protected PeliCreator(KonsoliIO io, Tuomari tuomari) {
        super(io, tuomari);
    }
    public static KPSPeli luoKPSPelaajaVsPelaaja(KonsoliIO io, Tuomari tuomari) {
        return new PeliCreator(io, tuomari);
    }

    public static KPSPeli luoTekoalyPeli(KonsoliIO io, Tuomari tuomari, Tekoaly tekoaly) {
        return new KPSTekoaly(io, tuomari, tekoaly);
    }

    @Override
    public void annaTokanSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = io.read();
    }

    @Override
    public void asetaTokanSiirto() {
        annaTokanSiirto();
    }
}
