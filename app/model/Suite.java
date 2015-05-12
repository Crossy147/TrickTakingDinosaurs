package model;

import java.util.Arrays;
import java.util.List;

/**
* Created by marcin on 15/03/15.
*/
public enum Suite {
    CLUBS, DIAMONDS, HEARTS, SPADES;

    public boolean isGreater(Suite suite) {
        List<Suite> suites = Arrays.asList(values());
        return suites.indexOf(this)>suites.indexOf(suite);
    }
}
