package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
        alusta();
    }

    private void alusta() {
        when(viite.uusi()).thenAnswer(new Answer() {
            private int viiteNumero = 42;

            public Object answer(InvocationOnMock invocation) {
                return viiteNumero++;
            }
        });
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(1);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Analyysia reaaliluvuilla", 30));

        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "Databases illuminated", 90));

        k.aloitaAsiointi();
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        k.lisaaKoriin(1);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(30));
    }

    @Test
    public void uusiViitenumeroJokaiselleMaksutapahtumalle() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("p", "00000");

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("z", "23232");

        verify(pankki).tilisiirto(anyString(), eq(44), anyString(), anyString(), anyInt());
    }

    @Test
    public void poistaKoristaPoistaaTuotteenOstoskorista() {
        Tuote t = varasto.haeTuote(1);
        k.lisaaKoriin(1);
        verify(varasto, times(1)).otaVarastosta(t);
        k.poistaKorista(1);
        verify(varasto, times(1)).palautaVarastoon(t);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void tilisiirtoaKutsutaanOikeillaParametreilla() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void tilisiirtoKunKorissaKaksiEriTuotetta() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(35));
    }

    @Test
    public void tilisiirtoKunKorissaKaksiSamaaTuotetta() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));
    }

    @Test
    public void tilisiirtoOnnistuuVaikkaKoriinOnYritettyLisataTuotteitaJoitaEiOleVarastossa() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }

}
