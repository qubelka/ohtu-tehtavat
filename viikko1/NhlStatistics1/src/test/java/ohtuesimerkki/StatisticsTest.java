package ohtuesimerkki;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void konstruktoriToimii() {
        assertThat(stats, is(notNullValue()));        
    }

    @Test
    public void searchPalauttaaListallaOlevanPelaajan() {  
        Player Kurri = new Player("Kurri", "EDM", 37, 53);
        assertThat(stats.search("Kurri"), is(Kurri));
    }
    
    @Test
    public void searchPalauttaaNullJosPelaajaaEiLoytynyt() {
        assertThat(stats.search("Ovechkin"), nullValue());
    } 
    
    @Test 
    public void teamPalauttaaOikeanListan() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Kurri", "EDM", 37, 53));
        players.add(new Player("Gretzky", "EDM", 35, 89));

        assertThat(stats.team("EDM"), is(players));        
    }
    
    @Test
    public void teamPalauttaaTyhjanListanJosTiimiaEiLoytynyt() {
        assertThat(stats.team("F").size(), is(0));
    }
    
    @Test
    public void topScorersPalauttaaOikeanListan() {
        Player Gretzky = new Player("Gretzky", "EDM", 35, 89);
        assertThat(stats.topScorers(1).get(0), is(Gretzky));
    }
}
