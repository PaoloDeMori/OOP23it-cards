package it.unibo.itcards.view;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.commons.Observer;

import java.util.List;


public interface View extends Observer{

    void show();

    void start();

    void stop();

    void setHand(List<Card> hand);

    void setCardsOnTable(List<Card> cards);

    void setNumberOpponentCards(int n);

    boolean isDeckEnded();

    public void aiCanPlay();
    public void playerCanPlay();
}
