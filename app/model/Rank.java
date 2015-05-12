package model;

import java.util.Arrays;
import java.util.List;

/**
* Created by marcin on 15/03/15.
*/
public enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    public boolean isGreater(Rank rank) {
        List<Rank> ranks = Arrays.asList(values());
        return ranks.indexOf(this)>ranks.indexOf(rank);
    }
}
