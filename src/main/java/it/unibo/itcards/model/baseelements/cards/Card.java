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
     * Default constructor for card. 
     */
    public Card() { };

    /**
     * Return a string representation of an Italian playing card.
     * @return the string representation of the instance
     */
    public final String toString() {
        String value = Integer.toString(getValue());
        String suit = getSuit().toString();
        String toReturn = (value.concat("-")).concat(suit);
        return toReturn;
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
