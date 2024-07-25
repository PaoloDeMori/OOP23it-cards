package it.unibo.itcards.model.baseelements.cards;

import java.security.InvalidParameterException;
/**
 * Interface for creating traditional italian cards using the factory pattern.
 */
public interface CardFactory {
    /**
     * Creates an instance of the Card class.
     * @param suit is the suit of the card
     * @param value is the numerical value of the card
     * @return a new Card
     * @throws InvalidParameterException if the parameters werent coherent with the card class.
     */
    Card buildCard(Suit suit, int value) throws InvalidParameterException;
}
