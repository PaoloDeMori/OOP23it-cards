package it.unibo.itcards.model.baseelements.cards;

import it.unibo.itcards.commons.Card;

/**
 * Implementation of the abstract class Card.
 * 
 */
public class CardImpl extends Card {
    private final Suit suit;
    private final int value;

    /**
     * The constructor of the class.
     * 
     * @param suit  represent the suit of the card
     * @param value represent the numerical value of the card
     */
    public CardImpl(final Suit suit, final int value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Return the numerical value of the card.
     * 
     * @return the value of the card
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * Return the suit of the card.
     * 
     * @return the suit of the card
     */
    @Override
    public Suit getSuit() {
        return this.suit;
    }

}
