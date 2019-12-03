package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    private Matcher category;

    public Not(Matcher category) {
        this.category = category;
    }

    @Override
    public boolean matches(Player p) {
        return !category.matches(p);
    }
}
