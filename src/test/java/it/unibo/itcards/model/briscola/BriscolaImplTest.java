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
        briscolaGame = new BriscolaImpl();
        deck = ShuffledDeckFactoryImpl.buildDeck();
        player1 = new PlayerImpl("Player1", DEFAULT_MAX_NUMBER_OF_CARDS);
        player2 = new PlayerImpl("player2", DEFAULT_MAX_NUMBER_OF_CARDS);
        cards=new HashSet<>();
        cardFactory = new CardFactoryImpl();
        briscolaGame.start();
    }

    /**
     * Tests the correct setting of the briscola card.
     */
    @Test
    void testStart() {
        card1=deck.drawCard();
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
     * @throws InvalidOperationException
     */
    @Test
    void testGiveCards() throws InvalidOperationException {
        List<Player> players = briscolaGame.getPlayers();
        for(Player p: players) {
            Card card = p.getCard(0).get();
            p.playCard(card);
        }
        briscolaGame.giveCards();
        for(Player p: players) {
            assertEquals(3, p.getCards().size());
        }
    
    }
}
