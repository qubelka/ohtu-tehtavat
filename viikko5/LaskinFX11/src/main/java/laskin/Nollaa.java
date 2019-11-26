package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private int arvo;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        arvo = 0;
    }

    @Override
    public void suorita() {
        try {
            arvo = Integer.parseInt(tuloskentta.getText());
        } catch (Exception e) {
        }
        sovellus.nollaa();
        syotekentta.setText("");
        tuloskentta.setText("" + 0);
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
        syotekentta.setText("");
        tuloskentta.setText("" + arvo);
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(true);
    }
}
