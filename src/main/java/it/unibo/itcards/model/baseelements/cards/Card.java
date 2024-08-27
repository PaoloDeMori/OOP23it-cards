package it.unibo.itcards.model.baseelements.cards;

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
}
