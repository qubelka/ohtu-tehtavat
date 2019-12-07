package ohtu.kivipaperisakset.pelit;

import ohtu.kivipaperisakset.KonsoliIO;
import ohtu.kivipaperisakset.domain.Tuomari;

public abstract class KPSPeli {
    protected KonsoliIO io;
    protected Tuomari tuomari;
    protected String ekanSiirto;
    protected String tokanSiirto;

    public KPSPeli(KonsoliIO io, Tuomari tuomari) {
        this.io = io;
        this.tuomari = tuomari;
    }

    public void pelaa() {
        io.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = io.read();
        annaTokanSiirto();
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.println(tuomari.toString());
            io.println("");

            io.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = io.read();
            asetaTokanSiirto();

        }

        io.println("");
        io.println("Kiitos!");
        io.println(tuomari.toString());
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected abstract void annaTokanSiirto();
    protected abstract void asetaTokanSiirto();
}
