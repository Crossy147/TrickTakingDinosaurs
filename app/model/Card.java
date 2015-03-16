package model;

/**
* Created by marcin on 15/03/15.
*/
public class Card {
    Suite suite;
    Rank rank;

    public Suite getSuite() {
        return suite;
    }

    public Rank getRank() {
        return rank;
    }

    public Card(Rank rank, Suite suite) {
        this.suite = suite;
        this.rank = rank;
    }

    public boolean equals(Object that) {
        if (that == null) return this == null;
        if (!(that instanceof Card)) return false;
        Card t = (Card) that;
        return this.suite == t.suite && this.rank == t.rank;
    }
}
