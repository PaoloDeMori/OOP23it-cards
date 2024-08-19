package it.unibo.itcards.model.baseelements.deck;

/**
 * Implementation of the DeckFactory interface, that creates a shuffled
 * initialized deck.
 */
public class ShuffledDeckFactoryImpl implements DeckFactory {
    private final Deck deck;

    /**
     * Constructor.
     */
    public ShuffledDeckFactoryImpl() {
        deck = new DeckImpl();
    }

    /**
     * Creates and returns a shuffled initialized deck.
     * 
     * @return New istance of the deck class, after the init and shuffle method are
     *         called.
     */
    @Override
    public Deck buildDeck() {
        deck.init();
        deck.shuffle();
        return deck;
    }
}
