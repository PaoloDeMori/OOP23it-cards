package it.unibo.itcards.model.baseelements.deck;
import it.unibo.itcards.model.baseelements.cards.Card;

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
     * @throws EmptyDeckException if the deck does not contain cards.
     * @return the card on top of the deck
     */
    Card drawCard() throws EmptyDeckException;

    /**
     * Checks if the deck ended.
     * 
     * @return true if there are no remaining cards in the deck
     */
    boolean isVoid();

    /**
     * A method that calculates the number of the remaining cards in the deck.
     * @return the number of the remaining cards in the deck.
     */
    int numberOfCards();

    @Override
    String toString();
}
