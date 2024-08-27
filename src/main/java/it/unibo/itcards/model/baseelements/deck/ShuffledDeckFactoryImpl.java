package it.unibo.itcards.model.baseelements.deck;

/**
 * A static factory for the creation of a shuffled initialized deck.
 */
public final class ShuffledDeckFactoryImpl {

    /**
     * Private constructor.
     */
    private ShuffledDeckFactoryImpl() { 
     }

    /**
     * Creates and returns a shuffled initialized deck.
     * 
     * @return New istance of the deck class, after the init and shuffle method are
     *         called.
     */
    public static Deck buildDeck() {
        final Deck deck = new DeckImpl();
        deck.init();
        deck.shuffle();
        return deck;
    }
}
