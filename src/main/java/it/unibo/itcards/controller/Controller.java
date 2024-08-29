package it.unibo.itcards.controller;

import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.View;

import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public void init(Model model, View view) {
        this.setModel(model);
        this.setView(view);
        this.model.addObserver(this.view);
    }

    public void start() {
        model.start();
        if (this.model.getCurrentPlayer() instanceof AIPlayer) {
            this.aiPlay();
        }
    }

    public void playturn(Card card) {
        if (this.model.getCurrentPlayer() instanceof AIPlayer) {
            this.aiPlay();
        }

        else {
            this.play(card);
        }
    }

    private void setModel(Model model) {
        this.model = model;
    }

    private void setView(View view) {
        this.view = view;
    }

    private void error() {
        System.exit(0);
    }

    public List<Card> getHand(){
        List<Player> players = this.model.getPlayers();
        int pos = 0;
        Player p = players.get(pos);
        while ( p instanceof AIPlayer) {
            pos++;
            p = players.get(pos);
        }
        if(!(p instanceof AIPlayer)){
                return p.getCards();
            }
            throw new InGameException("The player required does not exists");
        }

    public int getOpponentHand(){
            List<Player> players = this.model.getPlayers();
            int pos = 0;
            Player p = players.get(pos);
            while ( !(p instanceof AIPlayer)) {
                pos++;
                p = players.get(pos);
            }
            return p.getCards().size();
        }

    public List<Card> getCardsOnTable(){
        return this.model.getCardsOnTable();
    }

    public boolean isDeckEmpty(){
        return this.model.getDeck().isVoid();
    }

    private void aiPlay() {
        try {
            model.playTurn(((AIPlayer) this.model.getCurrentPlayer()).chooseCard(), this.model.getCurrentPlayer());
        } catch (Exception e) {
            this.error();
            return;
        }
    }

    private void play(Card card) {
        try {
            model.playTurn(this.model.getCurrentPlayer().playCard(card), this.model.getCurrentPlayer());
        } catch (Exception e) {
            this.error();
            return;
        }
    }
}
