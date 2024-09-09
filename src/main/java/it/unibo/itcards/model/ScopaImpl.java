package it.unibo.itcards.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.player.Player;

public class ScopaImpl extends Model {
    private List<Card> playedCards;
    private Set<Card> scope;

    public ScopaImpl(){
        super();
        this.playedCards = new ArrayList<>();
    }

    @Override
    public void playTurn(Card card, Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playTurn'");
    }

    @Override
    public List<Card> getCardsOnTable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCardsOnTable'");
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

    @Override
    public boolean giveCards() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'giveCards'");
    }

    @Override
    public int points(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'points'");
    }

    public Player winner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'winner'");
    }

    @Override
    public Player winner(List<Card> playedCards) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'winner'");
    }

    @Override
    public List<Integer> getPlayersPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersPoints'");
    }

    @Override
    public List<String> getPlayersNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersNames'");
    }
    
}
