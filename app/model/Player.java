package model;

import java.util.List;

/**
* Created by marcin on 15/03/15.
*/
public class Player {
    private PlayerPosition position;
    private List<Player> players;
    private List<Card> hand;

    public Player(PlayerPosition position, List<Card> hand) {
        this.position = position;
        this.hand = hand;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean playCard(Card card, Suite currentSuite){
        if(!canPlay(card, currentSuite))
            return false;

        hand.remove(card);
        return true;
    }

    public boolean canPlay(Card card, Suite currentSuite) {
        return hand.contains(card) && (currentSuite==null || card.getSuite().equals(currentSuite) || !hasCardInSuite(currentSuite));
    }

    private boolean hasCardInSuite(Suite suite) {
        return hand.stream().anyMatch(card -> card.getSuite().equals(suite));
    }
}
