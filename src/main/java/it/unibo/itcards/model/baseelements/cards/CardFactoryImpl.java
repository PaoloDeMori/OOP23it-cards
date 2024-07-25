package it.unibo.itcards.model.baseelements.cards;

import java.security.InvalidParameterException;

/**
 * An implementation of the CardFactory interface.
 * This class create a new instance of the CardImpl class.
 * It checks that the parameters are coherent with a traditional Italian playing
 * card.
 */
public class CardFactoryImpl implements CardFactory {

    /**
     * The default constructor.
     */
    public CardFactoryImpl() { };

    @Override
    public final Card buildCard(final Suit suit, final int value) throws InvalidParameterException {
        Card card;
        if (value <= Card.MAX_NUMERICAL_VALUE && value > 0) {
            card = new CardImpl(suit, value);
        } else {
            throw new InvalidParameterException("Invalid numerical value");
        }
        return card;
    }

}
