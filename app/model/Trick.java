package model;

import java.util.ArrayList;
import java.util.List;

/**
* Created by marcin on 15/03/15.
*/
public class Trick {
    private List<Card> cardsPlayed;

    public PlayerPosition getCurrentPlayer() {
        return currentPlayer;
    }

    private PlayerPosition currentPlayer;
    private PlayerPosition firstPlayer;

    public Trick(PlayerPosition firstPlayer) {
        this.firstPlayer = firstPlayer;
        this.currentPlayer = firstPlayer;
        this.cardsPlayed = new ArrayList<>();
    }

    public void playCard(Card card) {
        cardsPlayed.add(card);
        currentPlayer = currentPlayer.nextPlayer();
    }

    public Suite getSuite() {
        if (cardsPlayed.size() > 0) return cardsPlayed.get(0).getSuite();
        return null;
    }

    public boolean inProgress() {
        return cardsPlayed.size() < 4;
    }

    public PlayerPosition getWinner() { return PlayerPosition.EAST; }
}
