package it.unibo.itcards.model.briscola;

import it.unibo.itcards.commons.Card;

/**Helper classe for BriscolaGame that calculates the values of the cards in the game and the winner between 2 cards. */
public final class BriscolaHelper {

    /** The value assigned to an ace card in Briscola. */
    public static final int ACE_VALUE = 11;

    /** The value assigned to a three card in Briscola. */
    public static final int THREE_VALUE = 10;

    /** The value assigned to a ten card in Briscola. */
    public static final int TEN_VALUE = 4;

    /** The value assigned to a nine card in Briscola. */
    public static final int NINE_VALUE = 3;

    /** The value assigned to an eight card in Briscola. */
    public static final int EIGHT_VALUE = 2;

    /** The value assigned to all other cards in Briscola. */
    public static final int OTHER_CARDS_VALUE = 0;

    /** Prevents instantiation of this helper class. */
    private BriscolaHelper() {
    }

    /**
     * Determines the value of the specified card.
     *
     * @param card the card for which to determine the value
     * @return the value of the specified card based on Briscola rules
     */
    public static int getCardValue(final Card card) {
        switch (card.getValue()) {
            case 1:
                return ACE_VALUE;
            case 3:
                return THREE_VALUE;
            case 8:
                return EIGHT_VALUE;
            case 9:
                return NINE_VALUE;
            case 10:
                return TEN_VALUE;
            default:
                return OTHER_CARDS_VALUE;
        }
    }

    /**
     * Determines if the first specified card is the winner over the second card,
     * considering the briscola suit.
     *
     * @param card1    the first card to compare
     * @param card2    the second card to compare
     * @param briscola the briscola used to determine priority in case of differing
     *                 suits
     * @return true if card1 is the winner, false if card2 is the winner
     */
    public static boolean isWinner(final Card card1, final Card card2, final Card briscola) {
        if (card1.getSuit().equals(briscola.getSuit()) && !(card2.getSuit().equals(briscola.getSuit()))) {
            return true;
        } else if (card2.getSuit().equals(briscola.getSuit()) && !(card1.getSuit().equals(briscola.getSuit()))) {
            return false;
        } else if (card1.getSuit().equals(card2.getSuit())) {
            return BriscolaHelper.getCardValue(card1) > BriscolaHelper.getCardValue(card2);
        }
        return true;
    }
}
