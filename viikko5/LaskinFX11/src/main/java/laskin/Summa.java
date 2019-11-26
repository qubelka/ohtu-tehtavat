package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    private int arvo;
    private int laskunTulos;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        arvo = 0;
    }

    @Override
    public void suorita() {
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        sovellus.plus(arvo);
        laskeJaNaytaTulos();
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.miinus(arvo);
        laskeJaNaytaTulos();
        undo.disableProperty().set(true);
    }

    public void laskeJaNaytaTulos() {
        laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }
}
