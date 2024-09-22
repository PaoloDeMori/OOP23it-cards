package it.unibo.itcards.model.briscola;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.baseelements.cards.CardFactory;
import it.unibo.itcards.model.baseelements.cards.CardFactoryImpl;

public class InsaneBriscolaAITest {

    private Deck deck;
    private BriscolaImpl briscolaGame;
    private InsaneBriscolaAIPlayer insaneBriscolaAIPlayer;
    private Player player1;
    private Set<Card> cards;
    private CardFactory cardFactory;
    private static final int DEFAULT_MAX_NUMBER_OF_CARDS = 3;

    @BeforeEach
    private void init() {
        player1 = new PlayerImpl("Player1", DEFAULT_MAX_NUMBER_OF_CARDS);
        insaneBriscolaAIPlayer = new InsaneBriscolaAIPlayer("BotInsane", DEFAULT_MAX_NUMBER_OF_CARDS);
        briscolaGame = new BriscolaImpl(player1, insaneBriscolaAIPlayer);
        deck = ShuffledDeckFactoryImpl.buildDeck();
        cards = new HashSet<>();
        cardFactory = new CardFactoryImpl();
    }

    @Test
    public void testPlayer1PlaysFirst() throws InvalidOperationException{

        final Card briscolaCard = cardFactory.buildCard(Suit.SPADE, 5);
        briscolaGame.setBriscola(briscolaCard);

        final Card card1 = cardFactory.buildCard(Suit.DENARI, 5);
        final Card card2 = cardFactory.buildCard(Suit.COPPE, 6);
        final Card card3 = cardFactory.buildCard(Suit.BASTONI, 7);
        player1.drawCard(card1);
        player1.drawCard(card2);
        player1.drawCard(card3);
        
        final Card card4 = cardFactory.buildCard(Suit.COPPE, 8);
        final Card card5 = cardFactory.buildCard(Suit.SPADE, 9);
        final Card card6 = cardFactory.buildCard(Suit.DENARI, 10);
        insaneBriscolaAIPlayer.drawCard(card4);
        insaneBriscolaAIPlayer.drawCard(card5);
        insaneBriscolaAIPlayer.drawCard(card6);

        insaneBriscolaAIPlayer.setGame(briscolaGame);

        Card player1Card = card3;
        briscolaGame.setPlayedCards(new ArrayList<>(List.of(player1Card)));

        insaneBriscolaAIPlayer.chooseCard();

        List<Card> playedCards = insaneBriscolaAIPlayer.getPlayedCards();
        assertEquals(1, playedCards.size());
        assertTrue(playedCards.contains(card5));

    }

    @Test
    public void testPlayer1PlaysWeakCard() throws InvalidOperationException {
        
        final Card briscolaCard = cardFactory.buildCard(Suit.SPADE, 5);
        briscolaGame.setBriscola(briscolaCard);

        final Card card1 = cardFactory.buildCard(Suit.DENARI, 5);
        final Card card2 = cardFactory.buildCard(Suit.COPPE, 6);
        final Card card3 = cardFactory.buildCard(Suit.BASTONI, 7);
        player1.drawCard(card1);
        player1.drawCard(card2);
        player1.drawCard(card3);

        final Card card4 = cardFactory.buildCard(Suit.COPPE, 8);
        final Card card5 = cardFactory.buildCard(Suit.SPADE, 9);
        final Card card6 = cardFactory.buildCard(Suit.DENARI, 10);
        insaneBriscolaAIPlayer.drawCard(card4);
        insaneBriscolaAIPlayer.drawCard(card5);
        insaneBriscolaAIPlayer.drawCard(card6);

        insaneBriscolaAIPlayer.setGame(briscolaGame);

        Card player1Card = card1;
        briscolaGame.setPlayedCards(new ArrayList<>(List.of(player1Card)));

        assertEquals(card6, insaneBriscolaAIPlayer.chooseCard());

        List<Card> playedCards = insaneBriscolaAIPlayer.getPlayedCards();
        assertEquals(1, playedCards.size());
        assertTrue(playedCards.contains(card6)); 

    }
    public static void main(String[] args) throws InvalidOperationException {
        InsaneBriscolaAITest test = new InsaneBriscolaAITest();
        test.init();
        test.testPlayer1PlaysFirst();
        test.testPlayer1PlaysWeakCard();

    }

}
