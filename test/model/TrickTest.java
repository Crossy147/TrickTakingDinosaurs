package model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TrickTest {

    @Test
    public void trickIsExactlyFourCards() throws Exception {
        Trick trick = new Trick(PlayerPosition.EAST);
        assertTrue(trick.inProgress());
        trick.playCard(new Card(Rank.ACE, Suite.SPADES));
        assertTrue(trick.inProgress());
        trick.playCard(new Card(Rank.ACE, Suite.DIAMONDS));
        assertTrue(trick.inProgress());
        trick.playCard(new Card(Rank.ACE, Suite.CLUBS));
        assertTrue(trick.inProgress());
        trick.playCard(new Card(Rank.ACE, Suite.HEARTS));
        assertFalse(trick.inProgress());
    }

    @Test
    public void trickGetsTheSuiteOfFirstCardPlayed() throws Exception {
        Trick trick = new Trick(PlayerPosition.EAST);
        assertEquals(null, trick.getSuite());

        trick.playCard(new Card(Rank.ACE, Suite.SPADES));

        assertEquals(Suite.SPADES, trick.getSuite());
    }

    @Test
    public void playCardIncrementsPlayer() throws Exception {
        PlayerPosition player = PlayerPosition.EAST;

        Trick trick = new Trick(player);
        trick.playCard(mock(Card.class));

        assertEquals(player.nextPlayer(), trick.getCurrentPlayer());
    }
}