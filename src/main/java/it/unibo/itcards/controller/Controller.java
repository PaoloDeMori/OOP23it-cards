package it.unibo.itcards.controller;

import java.util.List;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.View;

/**
 * The controller of this application.
 */
public interface Controller {

    /**
     * Sets the model and the view of the mvc pattern of this application.
     * 
     * @param model the game that the user decided to play
     * @param view  the graphical interface
     */
    void init(Model model, View view);

    /**
     * This method is called to start the game.
     */
    void start();

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
    void playturn(Card card);

    /**
     * Returns the hand of the human player of this application.
     * 
     * @return the hand of the human player of the game.
     */
    List<Card> getHand();

    /**
     * Returns the hand of the AI player of this application.
     * 
     * @return the hand of the AI player of the game.
     */
    int getOpponentHand();

    /**
     * Return the cards on table of the game.
     * 
     * @return the cards on table of the model.
     */
    List<Card> getCardsOnTable();

    /**
     * Return the number of cards in the deck.
     * 
     * @return the number of cards in the deck.
     */
    int deckNumberOfCards();

    /**
     * Return the players of the game.
     * 
     * @return the players of the game
     */
    List<Player> getPlayers();

    /**
     * Start the audio of the game.
     */
    void startAudio();

    /**
     * Stop the audio of the game.
     */
    void stopAudio();

    /**
     * Return the current player of the game.
     * 
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * Return the points of the players of the game.
     * 
     * @return the points of the players
     */
    List<Integer> getPlayerPoints();

    /**
     * Return the names of the players of the game.
     * 
     * @return the names of the players
     */
    List<String> getPlayerNames();
}
