package controllers;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import play.mvc.Controller;
import play.mvc.Result;

import model.PlayerPosition;
import model.Player;
import model.Card;
import model.Rank;
import model.Suite;

import java.util.*;
import java.lang.*;

public class Game extends Controller {
    /* TODO: This is not the way to go (obviously). It should be done in some other class, 
       and also somehow correlated with Table. */
    private static List<Card> cards = Arrays.asList(new Card(Rank.ACE, Suite.HEARTS),
                                                    new Card(Rank.ACE, Suite.CLUBS),
                                                    new Card(Rank.KING, Suite.DIAMONDS),
                                                    new Card(Rank.JACK, Suite.DIAMONDS),
                                                    new Card(Rank.TWO, Suite.CLUBS));
    private static Player north = new Player(PlayerPosition.NORTH, cards);
    private static Player south = new Player(PlayerPosition.SOUTH, cards);
    private static Player east = new Player(PlayerPosition.EAST, cards);
    private static Player west = new Player(PlayerPosition.WEST, cards);

    public static Result deal() {
        JSONArray result = new JSONArray();
        for (Card card : cards) {
            try {
                result.put(card.toJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result.toString());
        return ok(result.toString());
    }
}

