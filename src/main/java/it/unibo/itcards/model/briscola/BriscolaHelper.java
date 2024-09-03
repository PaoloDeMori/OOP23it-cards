package it.unibo.itcards.model.briscola;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.cards.Cards;

public final class BriscolaHelper {

    private BriscolaHelper() {
    }

    /**
     * Determines the value of the specified card.
     * 
     * @param card
     * @return
     */
    public static int getCardValue(final Card card) {
        switch (card.getValue()) {
            case 1:
                return 11;
            case 3:
                return 10;
            case 8:
                return 2;
            case 9:
                return 3;
            case 10:
                return 4;
            default:
                return 0;
        }
    }

    /**
     * Determines if the specified card1 is the winner.
     * 
     * @param card1
     * @param card2
     * @param briscola
     */
    public static boolean isWinner(final Card card1, final Card card2, final Card briscola) {
        if (card1.getSuit().equals(briscola.getSuit()) && !(card2.getSuit().equals(briscola.getSuit()))) {
            return true;
        } else if (card2.getSuit().equals(briscola.getSuit()) && !(card1.getSuit().equals(briscola.getSuit()))) {
            return false;
        } else if (card1.getSuit().equals(card2.getSuit())) {
            return Cards.isGreater(card1, card2);
        }
        

        return true;

    }
}
