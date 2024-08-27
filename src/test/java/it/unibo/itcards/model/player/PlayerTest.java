    package it.unibo.itcards.model.player;

    import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.junit.jupiter.api.Assertions.assertThrows;
    import static org.junit.jupiter.api.Assertions.assertTrue;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import it.unibo.itcards.model.baseelements.cards.Card;
    import it.unibo.itcards.model.baseelements.deck.Deck;
    import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
    import it.unibo.itcards.model.baseelements.player.InvalidOperationException;
    import it.unibo.itcards.model.baseelements.player.Player;
    import it.unibo.itcards.model.baseelements.player.PlayerImpl;
    import java.util.Optional;
    import java.util.Set;
    import java.util.HashSet;

    /**
     * Tests the correct implementation of the PlayerImpl class, that represents a
     * human player.
     */

    class PlayerTest {
        private Deck deck;
        private Player player;
        private static final String DEFAULT_NAME = "tizio";
        private static final int DEFAULT_MAX_NUMBER_OF_CARDS = 3;

        /**
         * Method called before each test to initialize the fields used to check the correct implementation.
         * Initializes a new deck and a new player.
         */
        @BeforeEach
        private void init() {
            deck = ShuffledDeckFactoryImpl.buildDeck();
            player = new PlayerImpl(DEFAULT_NAME, DEFAULT_MAX_NUMBER_OF_CARDS);
        }

        /**
         * Tests the correct creation of a Player, testing the getMaxNumberOfCards and getName methods.
         */
        @Test
        void testCreation() {
            assertEquals(DEFAULT_MAX_NUMBER_OF_CARDS, player.getMaxNumberOfCards());
            assertEquals(DEFAULT_NAME, player.getName());
        }

        /**
         * Tests the correct implementation of the set that contains the cards won by
         * the player during a match.
         * Checks if cards are correctly added and that duplicates are not allowed.
         */
        @Test
        void testWonCards() {
            final Set<Card> wonCards = new HashSet<>();
            for (int i = 0; i < DEFAULT_MAX_NUMBER_OF_CARDS; i++) {
                wonCards.add(deck.drawCard().get());
            }
            player.addWonCards(wonCards);
            assertEquals(wonCards.size(), player.getWonCards().size());
            player.addWonCards(wonCards);
            assertEquals(player.getWonCards().size(), wonCards.size());
        }

        /**
         * Tests the correct implementation of the method used to add a card to the hand
         * of the player.
         * Tests if a card can be added until when the maximum size is reached.
         */
        @Test
        void testDrawCard() {
            for (int i = 0; i < player.getMaxNumberOfCards(); i++) {
                assertTrue(player.drawCard(deck.drawCard().get()));
            }
            assertEquals(player.getMaxNumberOfCards(), player.getNumberOfCards());
            assertFalse(player.drawCard(deck.drawCard().get()));
        }

        /**
         * Tests the correct implementation of the method that removes a card from the
         * player's hand
         * Tests that an exception is thrown while trying to play an invalid card.
         */
        @Test
        void testPlayCard() {
            assertThrows(InvalidOperationException.class, () -> player.playCard(deck.drawCard().get()));
            final Deck deck = ShuffledDeckFactoryImpl.buildDeck();
            for (int i = 0; i < Deck.MAX_NUMBER_OF_CARDS - 1; i++) {
                final Card card = deck.drawCard().get();
                assertTrue(player.drawCard(card));
                assertDoesNotThrow(() -> assertEquals(card, player.playCard(card)));
            }
            assertThrows(InvalidOperationException.class, () -> player.playCard(deck.drawCard().get()));
        }

        /**
         * Tests the correct implementation of the method that returns a card from a
         * position of the player's hand if it exists.
         * Checks that the Optional is not void only if the card in the required
         * position exists.
         */
        @Test
        void testGetCard() {
            final Card card = deck.drawCard().get();
            player.drawCard(card);
            assertEquals(card, player.getCard(0).get());
            assertEquals(Optional.empty(), player.getCard(1));
        }

    }
