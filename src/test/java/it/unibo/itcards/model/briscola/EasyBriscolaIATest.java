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
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
public class EasyBriscolaIATest {
    private Deck deck;
    private BriscolaImpl briscolaGame;
    private EasyBriscolaAIPlayer easyBriscolaAIPlayer;
    private Player player1;
    private Set<Card> cards;
    private CardFactory cardFactory;
    private static final int DEFAULT_MAX_NUMBER_OF_CARDS = 3;
    @BeforeEach
    private void init() {
        player1 = new PlayerImpl("Player1", DEFAULT_MAX_NUMBER_OF_CARDS);
        easyBriscolaAIPlayer = new EasyBriscolaAIPlayer("BotEasy" , DEFAULT_MAX_NUMBER_OF_CARDS);
        briscolaGame = new BriscolaImpl(player1 , easyBriscolaAIPlayer);
        deck = ShuffledDeckFactoryImpl.buildDeck();
        cards=new HashSet<>();
        cardFactory = new CardFactoryImpl();
    }

    /**
     * Test if the player choose the right card from his hand
     * 
     * @throws InvalidOperationException if the operation required wasn't possible
     */
    @Test
    void testChooseCard() throws InvalidOperationException{
        final Card card0 = cardFactory.buildCard(Suit.SPADE, 5);
        briscolaGame.setBriscola(card0);
        final Card card1 = cardFactory.buildCard(Suit.DENARI, 5);
        final Card card2 = cardFactory.buildCard(Suit.DENARI, 6);
        final Card card3 = cardFactory.buildCard(Suit.DENARI, 7);
        player1.drawCard(card1);
        player1.drawCard(card2);
        player1.drawCard(card3);
        final Card card4 = cardFactory.buildCard(Suit.COPPE, 8);
        final Card card5 = cardFactory.buildCard(Suit.SPADE, 9);
        final Card card6 = cardFactory.buildCard(Suit.DENARI, 10);
        easyBriscolaAIPlayer.drawCard(card4);
        easyBriscolaAIPlayer.drawCard(card5);
        easyBriscolaAIPlayer.drawCard(card6);
        easyBriscolaAIPlayer.setGame(briscolaGame);
        briscolaGame.setPlayedCards(List.of(card1));
        assertEquals(card6, easyBriscolaAIPlayer.chooseCard());


    }
    public static void main(String[] args) throws InvalidOperationException {
        EasyBriscolaIATest test = new EasyBriscolaIATest();
        test.init();
        test.testChooseCard();
    }
}
