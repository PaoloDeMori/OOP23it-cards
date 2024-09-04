package it.unibo.itcards.controller;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.View;

import java.util.List;

/**
 * The controller of this application.
 */
public class ControllerImpl implements Controller {
    private Model model;
    private View view;

    /**
     * Sets the model and the view of the mvc pattern of this application.
     * 
     * @param model the game that the user decided to play
     * @param view  the graphical interface
     */
    @Override
    public void init(Model model, View view) {
        this.setModel(model);
        this.setView(view);
        this.model.addObserver(this.view);
    }

    /**
     * This method is called to start the game.
     */
    @Override
    public void start() {
        model.start();
        if (this.model.getCurrentPlayer().isAi()) {
            this.play(null);
        }
    }

    /**
     * This method is called by action listeners, it accepts a card as a parameter
     * that represents the card to play.
     * If the current player is not a user this called the aiPlay method, otherwise
     * it called the play method.
     * If the game ended this method will handle that situation by calling the end
     * method.
     * 
     * @param card the card to play, when this method is called by a user.
     */
    @Override
    public void playturn(Card card) {
        this.model.getCurrentPlayer().selectCard(card);
        do {
            if (this.model.isGameOver()) {
                this.model.notifyObserver();
                this.end();
            } else {
                this.play(card);
            }
        } while (this.model.getCurrentPlayer().isAi());
    }

    /**
     * Sets the game the users decided to play.
     * 
     * @param model the game choose by the user
     */
    private void setModel(Model model) {
        this.model = model;
    }

    /**
     * Sets the gui.
     * 
     * @param view the gui implemantation to run this application.
     */
    private void setView(View view) {
        this.view = view;
    }

    /**
     * Handles what happened when there is an error that stopped the correct
     * execution of this program.
     */
    private void error() {
        System.exit(0);
    }

    /**
     * Returns the hand of the human player of this application.
     * 
     * @return the hand of the human player of the game.
     */
    @Override
    public List<Card> getHand() {
        List<Player> players = this.model.getPlayers();
        int pos = 0;
        Player p = players.get(pos);
        while (p.isAi()) {
            pos++;
            p = players.get(pos);
        }
        if (!(p.isAi())) {
            return p.getCards();
        }
        throw new InGameException("The player required does not exists");
    }

    /**
     * Returns the hand of the AI player of this application.
     * 
     * @return the hand of the AI player of the game.
     */
    @Override
    public int getOpponentHand() {
        List<Player> players = this.model.getPlayers();
        int pos = 0;
        Player p = players.get(pos);
        while (!(p.isAi())) {
            pos++;
            p = players.get(pos);
        }
        return p.getCards().size();
    }

    /**
     * Return the cards on table of the game.
     * 
     * @return the cards on table of the model.
     */
    @Override
    public List<Card> getCardsOnTable() {
        return this.model.getCardsOnTable();
    }

    /**
     * Checks if the deck is empty.
     * 
     * @return true if the deck in the model is empty, otherwise false
     */
    @Override
    public boolean isDeckEmpty() {
        return this.model.getDeck().isVoid();
    }

    /**
     * Handles what happens when the game ends.
     */
    private void end() {
        this.view.stop();
    }

    /**
     * Handle what happens when a Player decided to play
     * When a player decided to play, this method is called, this calls the playturn
     * method of the model
     * using the playCard method of the Player interface, to play in the model the
     * card the player decided to play.
     */
    private void play(Card card) {
        try {
            model.playTurn(this.model.getCurrentPlayer().chooseCard(), this.model.getCurrentPlayer());
        } catch (Exception e) {
            this.error();
            return;
        }
    }

    @Override
    public List<Player> getPlayers() {
        return this.model.getPlayers();
    }
}
