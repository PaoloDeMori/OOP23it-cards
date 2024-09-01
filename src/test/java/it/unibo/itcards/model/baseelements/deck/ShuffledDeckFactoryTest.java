package it.unibo.itcards.model.baseelements.deck;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.itcards.commons.Card;

/**
 * Test the deck creation using the factory that shuffle its cards.
 */
class ShuffledDeckFactoryTest {
    /**
     * Test if the factory initialize a deck of 40 cards.
     */
    @Test
    void testInit() {
        assertDoesNotThrow(() -> {
            final Deck deck = ShuffledDeckFactoryImpl.buildDeck();
            assertEquals(Deck.MAX_NUMBER_OF_CARDS, deck.numberOfCards());
        });
    }

    /**
     * Test if the factory initialize a deck of the correct 40 cards.
     * Test the method drawCard
     */
    @Test
    void testDeck() {
        assertDoesNotThrow(() -> {
            final Deck deck = ShuffledDeckFactoryImpl.buildDeck();
            int bastoni = 0, coppe = 0, denari = 0, spade = 0;
            assertEquals(Deck.MAX_NUMBER_OF_CARDS, deck.numberOfCards());
            for (int i = 0; i < Deck.MAX_NUMBER_OF_CARDS; i++) {
                final Card card = deck.drawCard().get();
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
