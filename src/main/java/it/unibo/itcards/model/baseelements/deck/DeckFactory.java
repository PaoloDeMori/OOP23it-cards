package it.unibo.itcards.model.baseelements.deck;
/**
 * Interface for creating decks using the factory pattern.
 */
public interface DeckFactory {
    /**
     * Create a new Istance of the Deck class.
     * @return a new Istance of the Deck Class
     */
    Deck buildDeck();
}
