package model;

import java.util.ArrayList;
import java.util.List;

/**
* Created by marcin on 15/03/15.
*/
public class Trick {
    private List<Card> cardsPlayed;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private Player currentPlayer;
    private List<Player> players;

    public Trick(List<Player> players) {
        this.players = players;
        this.currentPlayer = players.get(0);
        this.cardsPlayed = new ArrayList<>();
    }

    public boolean playCard(Card card) {
        if(!currentPlayer.playCard(card, this.getSuite())){
            return false;
        }
        cardsPlayed.add(card);
        currentPlayer = players.get((players.indexOf(currentPlayer)+1)%4);
        return true;
    }

    public Suite getSuite() {
        if (cardsPlayed.size() > 0) return cardsPlayed.get(0).getSuite();
        return null;
    }

    public boolean inProgress() {
        return cardsPlayed.size() < 4;
    }

    public Player getWinner() {
        Card winnerCard = cardsPlayed.get(0);
        Player winner = players.get(0);

        for(int i=1; i<cardsPlayed.size(); i++){
            Card card = cardsPlayed.get(i);
            if(card.isGreater(winnerCard)){
                winnerCard = card;
                winner = players.get(i);
            }
        }

        return winner;

    }
}
