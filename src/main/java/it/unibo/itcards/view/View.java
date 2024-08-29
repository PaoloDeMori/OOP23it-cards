package it.unibo.itcards.view;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.observerpattern.Observer;

import java.util.List;


public interface View extends Observer{

    void show();

    void stop();

    void setHand(List<Card> hand);

    void setCardsOnTable(List<Card> cards);

    void setNumberOpponentCards(int n);

    boolean isDeckEnded();
}
