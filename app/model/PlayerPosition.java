package model;

import java.util.Arrays;
import java.util.List;

/**
* Created by marcin on 15/03/15.
*/
public enum PlayerPosition {
    WEST, NORTH, EAST, SOUTH;

    public PlayerPosition nextPlayer() {
        List<PlayerPosition> players = Arrays.asList(values());
        int index = players.indexOf(this);
        return players.get((index + 1) % 4);
    }
}
