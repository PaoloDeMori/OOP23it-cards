package it.unibo.itcards.model.briscola;

import it.unibo.itcards.model.BriscolaImpl;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.baseelements.cards.CardFactory;
import it.unibo.itcards.model.baseelements.cards.CardFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BriscolaImplTest {

    private Deck deck;
    private BriscolaImpl briscolaGame;
    private Player player1;
    private Player player2;
    private Optional<Card> card1;
    private Set<Card> cards;
    private CardFactory cardFactory;
    private static final int DEFAULT_MAX_NUMBER_OF_CARDS = 3;

    /**
     * Method called before each test to initialize the fields used to check the
     * correct implementation.
     * Initializes a new deck and a new player.
     */
    @BeforeEach
    private void init() {
        player1 = new PlayerImpl("Player1", DEFAULT_MAX_NUMBER_OF_CARDS);
        player2 = new PlayerImpl("player2", DEFAULT_MAX_NUMBER_OF_CARDS);
        briscolaGame = new BriscolaImpl(player1, player2);
        deck = ShuffledDeckFactoryImpl.buildDeck();
        cards = new HashSet<>();
        cardFactory = new CardFactoryImpl();
        briscolaGame.start();
    }

    /**
     * Tests the correct setting of the briscola card.
     */
    @Test
    void testStart() {
        card1 = deck.drawCard();
        briscolaGame.setBriscola(card1.get());
        assertEquals(card1.get(), briscolaGame.getBriscola());
    }

    /**
     * Tests the correct implementation of the method that returns the number of
     * points of a player.
     */
    @Test
    void testPoints() {
        final Card card1 = cardFactory.buildCard(Suit.DENARI, 10);
        final Card card2 = cardFactory.buildCard(Suit.BASTONI, 9);
        final Card card3 = cardFactory.buildCard(Suit.BASTONI, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        player1.addWonCards(cards);
        assertEquals(7, briscolaGame.points(player1));

    }

    /**
     * Tests the correct implementation of the method that gives the cards to
     * the players.
     * 
     * @throws InvalidOperationException
     */
    @Test
    void testGiveCards() throws InvalidOperationException {
        List<Player> players = briscolaGame.getPlayers();
        for (Player p : players) {
            Card card = p.getCard(0).get();
            p.playCard(card);
        }
        briscolaGame.giveCards();
        for (Player p : players) {
            assertEquals(3, p.getCards().size());
        }
    }

    @Test
    void testWinner() {
        final Card card1 = cardFactory.buildCard(Suit.DENARI, 10);
        final Card card2 = cardFactory.buildCard(Suit.DENARI, 9);
        List<Card> cards = List.of(card1, card2);
        briscolaGame.playTurn(card1, this.player1);
        briscolaGame.playTurn(card2, this.player2);
        assertEquals(this.player1, briscolaGame.winner(cards));
    }

    public static void main(String[] args) {
        BriscolaImplTest test = new BriscolaImplTest();
        test.init();
        test.testWinner();
    }

    /**
     * Tests the correct implementation of the method that checks if the game is
     * over.
     */
    @Test
    void testIsGameOver() {

        assertEquals(3, player1.getCards().size(), "P1 have 3 card");
        assertEquals(3, player2.getCards().size(), "P2 have 3 card");

        assertTrue(briscolaGame.isGameOver(), "The game should not be over when players still have cards.");

        player1.getCards().clear();
        player2.getCards().clear();

        assertFalse(briscolaGame.isGameOver(), "The game should be over when no player has cards.");
    }

    /**
     * Test the correct implementation of the playTurn method.
     */
    @Test
    void testPlayTurn() {

        final Card card1 = cardFactory.buildCard(Suit.DENARI, 2);
        final Card card2 = cardFactory.buildCard(Suit.SPADE, 3);

        player1.drawCard(card1);
        player2.drawCard(card2);

        briscolaGame.setCurrentPlayer(player1);
        briscolaGame.playTurn(card1, player1);

        assertEquals(1, briscolaGame.playedCards().size(), "There should be one played card");
        assertTrue(briscolaGame.playedCards().contains(card1), "Card 1 should be in the played cards.");

        assertEquals(player2, briscolaGame.getCurrentPlayer(), "It should be player two's turn now.");

        briscolaGame.playTurn(card2, player2);

        assertTrue(player1.getPlayedCards().contains(card1), "Player 1 should have played card1.");
        assertTrue(player2.getPlayedCards().contains(card2), "Player 2 should have played card2.");

        Player expectedWinner = briscolaGame.winner(new ArrayList<>(Set.of(card1, card2)));
        assertEquals(expectedWinner, briscolaGame.getCurrentPlayer(),
                "The winner of the round should be the current player");

        assertTrue(expectedWinner.getWonCards().containsAll(Set.of(card1, card2)),
                "The winner should have won the played cards.");

        assertEquals(3, player1.getCards().size(),
                "Player 1 should have 3 cards after new cards are dealt");
        assertEquals(3, player2.getCards().size(),
                "Player 2 should have 3 cards after new cards are dealt");

    }

    @Test
    void testPlayTurnWrongPlayer() {

        final Card card = cardFactory.buildCard(Suit.COPPE, 1);
        player2.drawCard(card);

        briscolaGame.setCurrentPlayer(player1);

        assertThrows(IllegalStateException.class, () -> briscolaGame.playTurn(card, player2),
                "An exception should be thrown if the wrong player tries to play.");
    }
}
