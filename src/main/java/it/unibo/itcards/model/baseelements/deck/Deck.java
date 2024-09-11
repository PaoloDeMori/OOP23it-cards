package it.unibo.itcards.model.baseelements.deck;

import java.util.Optional;

import it.unibo.itcards.commons.Card;

import java.util.List;

/**
 * Represents a deck of 40 traditional italian playing cards.
 */
public interface Deck {
    /**
     * The number of cards in a complete deck.
     */
    int MAX_NUMBER_OF_CARDS = 40;

    /**
     * Initializes the deck as an ordered full deck.
     */
    void init();

    /**
     * Shuffles the cards in the deck.
     */
    void shuffle();

    /**
     * Returns the card on top of the deck and removes it.
     * 
     * @return the card on top of the deck
     */
    Optional<Card> drawCard();

    /**
     * Checks if the deck ended.
     * 
     * @return true if there are no remaining cards in the deck
     */
    boolean isVoid();

    /**
     * A method that calculates the number of the remaining cards in the deck.
     * 
     * @return the number of the remaining cards in the deck.
     */
    int numberOfCards();

    /**
     * Return a string representation of the deck.
     * 
     * @return a string representation of the deck
     */
    @Override
    String toString();

    /**
     * Returns a list of the cards in the deck.
     * 
     * @return a list of the cards in the deck
     */
    List<Card> listOf();
}
