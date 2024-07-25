package it.unibo.itcards.model.baseelements.cards;

/**
 * Helper class for the class Card.
 */
public final class Cards {

    /**
     * Private constructor because an helper class cannot have a public constructor.
     */
    private Cards() {
        throw new UnsupportedOperationException("This is an Helper class");
    }

    /**
     * Determines if a card has the same suit as another and if the first one has a
     * greater numerical value.
     * 
     * @param c1 the card to compare with another card
     * @param c2 the card to compare against
     * @return true if c1 has a greater numerical value and the same suit as c2
     */
    public static boolean isGreater(final Card c1, final Card c2) {
        if ((c1.getValue() > c2.getValue()) && (c1.getSuit().equals(c2.getSuit()))) {
            return true;
        }
        return false;
    }

    /**
     * Determines if a card has the same suit of another and if the first one has a
     * smaller numerical value.
     * 
     * @param c1 the card that i want to compare to another card
     * @param c2 the card with i want to compare my card
     * @return true if c1 has a smaller numerical value and the same suit of c2
     */
    public static boolean isSmaller(final Card c1, final Card c2) {
        if ((c1.getValue() < c2.getValue()) && (c1.getSuit().equals(c2.getSuit()))) {
            return true;
        }
        return false;
    }
}
