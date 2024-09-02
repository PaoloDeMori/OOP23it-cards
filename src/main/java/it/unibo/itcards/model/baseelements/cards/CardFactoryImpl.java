package it.unibo.itcards.model.baseelements.cards;

import it.unibo.itcards.commons.Card;

/**
 * An implementation of the CardFactory interface.
 * This class creates a new instance of the CardImpl class.
 * It checks that the parameters are coherent with a traditional Italian playing
 * card.
 */
public class CardFactoryImpl implements CardFactory {
    @Override
    public final Card buildCard(final Suit suit, final int value) {
        Card card;
        if (value <= Card.MAX_NUMERICAL_VALUE && value > 0) {
            card = new CardImpl(suit, value);
        } else {
            throw new InvalidCardException("Invalid numerical value creating a card");
        }
        return card;
    }

}
