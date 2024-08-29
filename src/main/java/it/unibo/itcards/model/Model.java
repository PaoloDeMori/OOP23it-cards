package it.unibo.itcards.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.model.baseelements.player.Player;

public abstract class Model {

    Deck deck;
    List<Player> players;
    Player currentPlayer;

    /**
     * Constructs a new model with a shuffled deck.
     */
    public Model() {
        this.deck = ShuffledDeckFactoryImpl.buildDeck();
        this.players = new ArrayList<>();
    }

    public abstract void playTurn(Card card, Player player);

    public abstract List<Card> getCardsOnTable();

    public abstract void start();

    public abstract boolean isGameOver();

    /**
     * Checks if the deck is ended
     * 
     * @return true if the deck is empty, false if it is not
     */
    public boolean isDeckEnded() {
        return this.deck.isVoid();
    }

    /**
     * Returns the current player
     * 
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Returns the list of players
     * 
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gives the cards to the players
     * 
     * @return true if cards were successfully dealt, false otherwise
     */
    public abstract boolean giveCards();

    /**
     * Returns the deck
     * 
     * @return the deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    public abstract int points(Player player);

    public abstract Player winner();

}