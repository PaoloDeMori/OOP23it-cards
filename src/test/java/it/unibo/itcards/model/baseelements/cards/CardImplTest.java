package it.unibo.itcards.model.baseelements.cards;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;
/**
 * Test the correct implementation of the class CardImpl.
 */

public class CardImplTest {
    private Card card;
    private CardFactory factory;

    /**
     * Initializes the factory.
     */
    private void init() {
        this.factory = new CardFactoryImpl();
    }
    /**
     * Test the correct creation of the class.
     * It creates a card and after that it tests the getters.
     * It tries to create new Cards checking if an exception is thrown only if it tries to 
     * create a card with an invalid numerical value.
     */
    @Test
    public void testCreation() {
        init();
        card = factory.buildCard(Suit.Bastoni, 1);
        assertEquals(Suit.Bastoni, card.getSuit());
        assertEquals(1, card.getValue());
        assertDoesNotThrow(() -> card = factory.buildCard(Suit.Spade, 10));
        assertThrows(InvalidParameterException.class, () -> card = factory.buildCard(Suit.Spade, -1));
    }

    /**
     * Tests the method to string.
     */
    @Test
    public void testToString() {
        init();
        card = factory.buildCard(Suit.Bastoni, 8);
        String toTest = "8-Bastoni";
        assertEquals(toTest, card.toString());
    }


}
