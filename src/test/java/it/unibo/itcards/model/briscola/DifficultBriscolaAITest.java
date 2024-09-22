package it.unibo.itcards.model.briscola;
import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.baseelements.cards.CardFactory;
import it.unibo.itcards.model.baseelements.cards.CardFactoryImpl;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
public class DifficultBriscolaAITest {
    private BriscolaImpl briscolaGame;
    private DifficultBriscolaAIPlayer difficultBriscolaAIPlayer;
    private Player player1;
    private CardFactory cardFactory;
    private static final int DEFAULT_MAX_NUMBER_OF_CARDS = 3;
    /**
     * Initializes the test setup.
     *
     * This method creates a new instance of the PlayerImpl class with the name "Player1"
     * and the default maximum number of cards. It also creates a new instance of the
     * DifficultBriscolaAIPlayer class with the name "BotDifficult" and the default maximum number
     * of cards. Then, it creates a new instance of the BriscolaImpl class with the
     * previously created PlayerImpl instance and the DifficultBriscolaAIPlayer instance.
     *
     * Additionally, it builds a new deck using the ShuffledDeckFactoryImpl class and
     * initializes the cards and cardFactory variables.
     *
     * This method is annotated with @BeforeEach, which means it will be executed before
     * each test case.
     */
    @BeforeEach
    private void init() {
        player1 = new PlayerImpl("Player1", DEFAULT_MAX_NUMBER_OF_CARDS);
        difficultBriscolaAIPlayer = new DifficultBriscolaAIPlayer("BotDifficult" , DEFAULT_MAX_NUMBER_OF_CARDS);
        briscolaGame = new BriscolaImpl(player1 , difficultBriscolaAIPlayer);
        cardFactory = new CardFactoryImpl();
    }

    /**
     * Tests the chooseCard method of the DifficultBriscolaAIPlayer class.
     *
     * This test case sets up a game scenario where the AI player has to choose a card
     * from its hand.
     *
     * @return  the result of the assertEquals method
     * @throws InvalidOperationException 
     */
    @Test
    public void testChooseCard() throws InvalidOperationException {
        final Card card0 = cardFactory.buildCard(Suit.SPADE, 5);
        briscolaGame.setBriscola(card0);
        final Card card1 = cardFactory.buildCard(Suit.DENARI, 3);
        final Card card2 = cardFactory.buildCard(Suit.COPPE, 6);
        final Card card3 = cardFactory.buildCard(Suit.BASTONI, 7);
        player1.drawCard(card1);
        player1.drawCard(card2);
        player1.drawCard(card3);
        final Card card4 = cardFactory.buildCard(Suit.COPPE, 8);
        final Card card5 = cardFactory.buildCard(Suit.SPADE, 9);
        final Card card6 = cardFactory.buildCard(Suit.DENARI, 10);
        difficultBriscolaAIPlayer.drawCard(card4);
        difficultBriscolaAIPlayer.drawCard(card5);
        difficultBriscolaAIPlayer.drawCard(card6);
        difficultBriscolaAIPlayer.setGame(briscolaGame);
        assertEquals(card4, difficultBriscolaAIPlayer.chooseCard());
        briscolaGame.setPlayedCards(new ArrayList<>(List.of(card1)));
        assertEquals(card5, difficultBriscolaAIPlayer.chooseCard());
        briscolaGame.clearPlayedCards();
  
}

public static void main(String[] args) throws InvalidOperationException {
    DifficultBriscolaAITest test = new DifficultBriscolaAITest();
    test.init();
    test.testChooseCard();
}
}
