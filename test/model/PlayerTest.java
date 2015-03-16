package model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerTest {

    private Card aceOfSpades = new Card(Rank.ACE, Suite.SPADES);
    private Card aceOfClubs = new Card(Rank.ACE, Suite.CLUBS);
    private Card jackOfDiamonds = new Card(Rank.JACK, Suite.DIAMONDS);
    private Card queenOfHearts = new Card(Rank.QUEEN, Suite.HEARTS);

    @Test
    public void cannotPlayAbsentCard() throws Exception {
        Player player = new Player(PlayerPosition.EAST, Arrays.asList(aceOfSpades));
        assertFalse(player.canPlay(aceOfClubs, null));
        assertTrue(player.canPlay(aceOfSpades, null));
    }

    @Test
    public void cannotPlayCardOfWrongSuiteIfHasCorrect() throws Exception {
        Player player = new Player(PlayerPosition.EAST, Arrays.asList(aceOfClubs, aceOfSpades, jackOfDiamonds));
        assertTrue(player.canPlay(aceOfClubs, Suite.CLUBS));
        assertFalse(player.canPlay(aceOfClubs, Suite.DIAMONDS));
    }

    @Test
    public void canPlayAnySuiteWhenCurrentIsNull() throws Exception {
        Player player = new Player(PlayerPosition.EAST, Arrays.asList(aceOfClubs, aceOfSpades, jackOfDiamonds, queenOfHearts));
        player.getHand().stream().forEach(card -> assertTrue(player.canPlay(card, null)));
    }
}