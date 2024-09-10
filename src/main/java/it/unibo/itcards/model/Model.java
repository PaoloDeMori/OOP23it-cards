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

public abstract class Model implements Observable {

    protected Deck deck;
    protected List<Player> players;
    protected Player currentPlayer;
    protected List<Observer> observers = new ArrayList<>();
    private Audio audio;

    /**
     * Constructs a new model with a shuffled deck.
     */
    public Model() {
        this.deck = ShuffledDeckFactoryImpl.buildDeck();
        this.players = new ArrayList<>();
        try {
            audio = new Audio();
            audio.start();
        } catch (Exception e) {
            audio = null;
            e.printStackTrace();
        }
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


    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (var observer : observers) {
            observer.update();
        }

    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void startAudio() {
        if (audio != null) {
            audio.start();
        }
    }

    public void stopAudio() {
        if (audio != null) {
            audio.stop();
        }
    }

    public abstract List<Integer> getPlayersPoints();

    public abstract List<String> getPlayersNames();

}