package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    Matcher query;

    public QueryBuilder() {
        query = new All();
    }

    public Matcher build() {
        Matcher remember = query;
        query = new All();
        return remember;
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

    public QueryBuilder oneOf(Matcher... matchers) {
        query = new Or(matchers);
        return this;
    }
}
