package it.unibo.itcards.view;

import javax.swing.JPanel;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.observerpattern.Observer;

import java.util.List;


public interface View extends Observer{

    void setHand(List<Card> hand);

    void setCardsOnTable(List<Card> cards);

    void setNumberOpponentCards(int n);

    boolean isDeckEnded();
}
