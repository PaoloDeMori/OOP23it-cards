package it.unibo.itcards.view;

import javax.swing.JPanel;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;

import java.util.List;


public interface View {

    void setHand(List<Card> hand);

    void setCardsOnTable(List<Card> cards);

    void setNumberOpponentCards(int n);

    boolean isDeckEnded();
}
