package it.unibo.itcards.commons;

import it.unibo.itcards.model.baseelements.cards.Suit;

/**
 * Represent traditional Italian playing cards.
 * Contains int value and Suit suit fields
 */
public abstract class Card {
    /**
     * The higher numerical value that a card can have.
     */
    public static final int MAX_NUMERICAL_VALUE = 10;

    /**
     * Return a string representation of an Italian playing card.
     * 
     * @return the string representation of the instance
     */
    @Override
    public final String toString() {
        final String value = Integer.toString(getValue());
        final String suit = getSuit().toString();
        return value.concat("-").concat(suit);
    }

    /**
     * Returns the numerical value of the card.
     * 
     * @return the int value of the card
     */
    public abstract int getValue();

    /**
     * Return the suit of the card, represented as an element of the enum Suit.
     * 
     * @return the Suit of the card.
     */
    public abstract Suit getSuit();

    @Override
    public boolean equals(Object obj){
        return obj instanceof Card 
                && ((Card) obj).getValue() == this.getValue()
                && ((Card) obj).getSuit() == this.getSuit();
    }
}
