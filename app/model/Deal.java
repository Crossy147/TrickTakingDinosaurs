package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by marcin on 15/03/15.
 */
public class Deal {
    private static final int TRICKS_TO_TAKE = 13;

    private List<Trick> tricks;
    private List<Player> players;
    private Trick currentTrick;

    public Deal(List<Player> players) {
        this.players = players;
        this.tricks = new ArrayList<>();
        this.currentTrick = new Trick(players);
    }

    public List<Trick> getTricks() {
        return tricks;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean inProgress() {
        return tricks.size() < TRICKS_TO_TAKE;
    }
}
