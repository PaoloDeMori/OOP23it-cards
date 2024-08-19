package it.unibo.itcards.model.baseelements.deck;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.itcards.model.baseelements.cards.Card;

/**
 * Test the deck creation using the factory that shuffle its cards.
 */
class ShuffledDeckFactoryTest {
    private DeckFactory factory;

    /**
     * Init the factory.
     */
    private void init() {
        factory = new ShuffledDeckFactoryImpl();
    }

    /**
     * Test if the factory initialize a deck of 40 cards.
     */
    @Test
    void testInit() {
        init();
        assertDoesNotThrow(() -> {
            final Deck deck = factory.buildDeck();
            assertEquals(Deck.MAX_NUMBER_OF_CARDS, deck.numberOfCards());
        });
    }

    /**
     * Test if the factory initialize a deck of the correct 40 cards.
     * Test the method drawCard
     */
    @Test
    void testDeck() throws EmptyDeckException {
        init();
        assertDoesNotThrow(() -> {
            final Deck deck = factory.buildDeck();
            int bastoni = 0, coppe = 0, denari = 0, spade = 0;
            assertEquals(Deck.MAX_NUMBER_OF_CARDS, deck.numberOfCards());
            for (int i = 0; i < Deck.MAX_NUMBER_OF_CARDS; i++) {
                final Card card = deck.drawCard();
                switch (card.getSuit()) {
                    case BASTONI:
                        bastoni++;
                        break;
                    case SPADE:
                        spade++;
                        break;
                    case DENARI:
                        denari++;
                        break;
                    case COPPE:
                        coppe++;
                        break;

                    default:
                        break;
                }
            }
            assertEquals(10, bastoni);
            assertEquals(10, coppe);
            assertEquals(10, spade);
            assertEquals(10, denari);
        });

    }
}
