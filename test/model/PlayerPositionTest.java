package model;

import org.junit.Test;
import model.PlayerPosition;

import static org.junit.Assert.*;

public class PlayerPositionTest {

    @Test
    public void testNextPlayer() throws Exception {
        assertEquals(PlayerPosition.NORTH, PlayerPosition.WEST.nextPlayer());
        assertEquals(PlayerPosition.EAST, PlayerPosition.NORTH.nextPlayer());
        assertEquals(PlayerPosition.SOUTH, PlayerPosition.EAST.nextPlayer());
        assertEquals(PlayerPosition.WEST, PlayerPosition.SOUTH.nextPlayer());
    }
}