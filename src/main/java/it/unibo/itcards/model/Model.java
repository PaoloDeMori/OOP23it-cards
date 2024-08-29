package it.unibo.itcards.model;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.OverrideMustInvoke;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.observerpattern.Observable;
import it.unibo.itcards.observerpattern.Observer;

public abstract class Model implements Observable{

    Deck deck;
    List<Player> players;
    Player currentPlayer;
    List<Observer> observers = new ArrayList<>();


    public Model() {
        this.deck = ShuffledDeckFactoryImpl.buildDeck();
        this.players = new ArrayList<>();
    }

    public abstract void playTurn(Card card, Player player);

    public abstract List<Card> getCardsOnTable();

    public abstract void start();

    public abstract boolean isGameOver();

    public  boolean isDeckEnded(){
        return this.deck.isVoid();
    }

    public  Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public  List<Player> getPlayers(){
        return this.players;
    }

    public abstract boolean giveCards();

    public  Deck getDeck(){
        return this.deck;
    }

    public abstract int points(Player player);

    public abstract Player winner();

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
        for (var observer : observers){
            observer.update();
        }
        
    }

}