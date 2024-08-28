package it.unibo.itcards.model.baseelements;

import java.util.List;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.player.Player;

public interface Model {

    void playTurn(Card card, Player player);

    List<Card> getCardsOnTable();

    void start();

    boolean isGameOver();

    boolean isDeckEnded();

    Player getCurrentPlayer();

    List<Player> getPlayers();

    boolean giveCards();

    Deck getDeck();

}