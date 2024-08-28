package it.unibo.itcards.controller;

import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;

public class Controller {
    private Model model;
    private Object view;

    public void init(Model model, Object view) {
        this.setModel(model);
        this.setView(view);
    }

    public void start(){
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
        updateView();
    }

    private void setModel(Model model) {
        this.model = model;
    }

    private void setView(Object view) {
        this.view = view;
    }

    private void error() {
        System.exit(0);
    }

    public void updateView(){

    }

    private void aiPlay(){
        try {
            model.playTurn(((AIPlayer)this.model.getCurrentPlayer()).chooseCard(), this.model.getCurrentPlayer());
        } catch (Exception e) {
            this.error();
            return;
        }
    }

    private void play(Card card){
        try {
            model.playTurn(this.model.getCurrentPlayer().playCard(card), this.model.getCurrentPlayer());
        } catch (Exception e) {
            this.error();
            return;
        }
    }
}
