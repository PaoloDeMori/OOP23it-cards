package it.unibo.itcards.model.player;

import it.unibo.itcards.model.baseelements.cards.CardFactoryImpl;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.player.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.cards.CardFactory;

/**
 * Tests the correct implementation of the Hand class, that represents the
 * playable cards of a player.
 */

class HandTest {
    private final CardFactory cardFactory = new CardFactoryImpl();
    private final static int DEFAULT_SIZE = 3;

    /**
     * Test the class using 4 instance of Card.
        */
    @Test
    void testHand() {
        final Hand hand = new Hand(DEFAULT_SIZE);
        final Card card1 = cardFactory.buildCard(Suit.DENARI, 1);
        final Card card2 = cardFactory.buildCard(Suit.BASTONI, 1);
        final Card card3 = cardFactory.buildCard(Suit.BASTONI, 1);
        final Card card4 = cardFactory.buildCard(Suit.BASTONI, 1);
        assertEquals(DEFAULT_SIZE, hand.getMaxSize());
        assertTrue(hand.add(card1));
        assertEquals(hand.get(0), card1);
        assertTrue(hand.add(card2));
        assertFalse(hand.add(card2));
        assertEquals(hand.get(1), card2);
        assertTrue(hand.add(card3));
        assertEquals(DEFAULT_SIZE, hand.size());
        assertFalse(hand.add(card4));

    }

}
