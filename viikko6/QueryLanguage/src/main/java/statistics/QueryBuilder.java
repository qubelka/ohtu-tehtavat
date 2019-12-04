package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    Matcher query;

    public QueryBuilder() {
        query = new All();
    }
    
    public Matcher build() {
        return query;
    }

    public QueryBuilder playsIn(String team) {
        query = new And(query, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        query = new And(query, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        query = new And(query, new HasFewerThan(value, category));
        return this;
    }
}
