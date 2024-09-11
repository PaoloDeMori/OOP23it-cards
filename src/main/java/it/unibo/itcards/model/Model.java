package it.unibo.itcards.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.commons.Observable;
import it.unibo.itcards.commons.Observer;
import it.unibo.itcards.model.Audio.Audio;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.model.baseelements.player.Player;

/**
 * Represents the model of the game.
 */
public abstract class Model implements Observable {

    private Deck deck;
    private List<Player> players;
    private Player currentPlayer;
    private List<Observer> observers = new ArrayList<>();
    private Audio audio;

    /**
     * Constructs a new model with a shuffled deck.
     */
    public Model() {
        this.deck = ShuffledDeckFactoryImpl.buildDeck();
        this.players = new ArrayList<>();
        try {
            this.audio = new Audio();
            this.audio.start();
        } catch (Exception e) {
            audio = null;
            e.printStackTrace();
        }
    }

    /**
     * represent the turn og the game.
     * 
     * @param card   the card to play
     * @param player the player
     */
    public abstract void playTurn(Card card, Player player);

    /**
     * return the cards on the table in the turn which is called.
     * 
     * @return the cards on the table
     */
    public abstract List<Card> getCardsOnTable();

    /**
     * Start the game.
     */
    public abstract void start();

    /**
     * Checks if the game is over.
     * 
     * @return true if the game is over, false otherwise
     */
    public abstract boolean isGameOver();

    /**
     * Checks if the deck is ended.
     * 
     * @return true if the deck is empty, false if it is not
     */
    public boolean isDeckEnded() {
        return this.deck.isVoid();
    }

    /**
     * Returns the current player.
     * 
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Returns the list of players.
     * 
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gives the cards to the players.
     * 
     * @return true if cards were successfully dealt, false otherwise
     */
    public abstract boolean giveCards();

    /**
     * Returns the deck.
     * 
     * @return the deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Returns the points of the player.
     * 
     * @param player the player
     * @return the points of the player
     */
    public abstract int points(Player player);

    /**
     * Returns the winner of the game.
     * 
     * @param playedCards the cards played
     * @return the winner
     */
    public abstract Player winner(List<Card> playedCards);

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(final Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Removes a registered observer from the list of observers.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void deleteObserver(final Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifies all observers by calling their update method.
     */
    @Override
    public void notifyObserver() {
        for (final var observer : observers) {
            observer.update();
        }

    }

    /**
     * Sets the deck for the game.
     *
     * @param deck the deck to be set
     */
    public void setDeck(final Deck deck) {
        this.deck = deck;
    }

    /**
     * Sets the list of players in the game.
     *
     * @param players the list of players to be set
     */
    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    /**
     * Sets the current player in the game.
     *
     * @param currentPlayer the player to be set as the current player
     */
    public void setCurrentPlayer(final Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Returns a list of all registered observers.
     *
     * @return a list of observers
     */
    public List<Observer> getObservers() {
        return observers;
    }

    /**
     * Sets the list of observers for this object.
     *
     * @param observers the list of observers to be set
     */
    public void setObservers(final List<Observer> observers) {
        this.observers = observers;
    }

    /**
     * Starts the audio playback if it is available.
     */
    public void startAudio() {
        if (this.audio != null) {
            this.audio.start();
        }
    }

    /**
     * Stops the audio playback if it is currently playing.
     */
    public void stopAudio() {
        if (this.audio != null) {
            this.audio.stop();
        }
    }

    /**
     * returns the list of players points.
     * 
     * @return the list of players points
     */

    public abstract List<Integer> getPlayersPoints();

    /**
     * Returns the list of players names.
     * 
     * @return the list of players names
     */
    public abstract List<String> getPlayersNames();
}
