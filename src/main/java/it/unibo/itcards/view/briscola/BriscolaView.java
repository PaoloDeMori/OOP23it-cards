package it.unibo.itcards.view.briscola;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import it.unibo.itcards.commons.Card;
import it.unibo.itcards.controller.Controller;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.Dim;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.CardViewFactory;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.panels.EndPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represent the view of the game Briscola.
 */
public class BriscolaView extends JFrame implements View {

    private final MainPanel mainpanel;
    private final CardViewFactory cardViewFactory;
    private final Controller controller;

    private String botName;
    private String playerName;

    private int botPoints;
    private int playerPoints;

    private boolean canPlayerPlay = true;

    /**
     * this method build the view for the game Briscola.
     * 
     * @param d          dimension
     * @param controller the controller
     */
    public BriscolaView(final Dim d, final Controller controller) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());

        this.cardViewFactory = new CardViewFactory();
        this.mainpanel = MainPanelBriscolaFactory.build(d.getDimension());

        this.setMusic();
        this.add(mainpanel);
        this.controller = controller;
    }

    /**
     * Sets the hand of cards for the current player.
     *
     * @param cards a list of cards to be displayed in the hand
     */
    public void setHand(final List<Card> cards) {
        List<CardButton> panels = createCardButtons(cards);
        mainpanel.setHand(panels);
    }

    /**
     * Sets up the music functionality for the game.
     * 
     * Creates a JButton with the label "Start/Stop music" and adds an
     * ActionListener to it.
     * When the button is clicked, it calls the startAudio method of the controller.
     * The button is then added to the mainpanel.
     */
    public void setMusic() {
        JButton button = new JButton("Start/Stop music");
        button.addActionListener(e -> {
            this.controller.startAudio();
        });
        this.mainpanel.setMusicButtons(button);
    }

    /**
     * Creates a list of CardButton objects based on the given list of Card objects.
     *
     * @param cards the list of Card objects to create buttons for
     * @return a list of CardButton objects
     */
    private List<CardButton> createCardButtons(final List<Card> cards) {
        List<CardButton> buttons = new ArrayList<>();
        for (Card card : cards) {
            ActionListener al = e -> {
                controller.playturn(card);
                System.out.println("Card played: " + card);
            };
            CardButton button = this.cardViewFactory.buildButton(card, al, this.mainpanel.getHandPanelDimension());
            if (!canPlayerPlay) {
                button.setEnabled(false);
                button.setOpaque(false);
            }

            buttons.add(button);
        }
        return buttons;
    }

    /**
     * Sets the cards on the table for the current game.
     *
     * @param cards the list of cards to be displayed on the table
     */
    public void setCardsOnTable(final List<Card> cards) {
        this.mainpanel.setCardsOnTable(cards);
    }

    /**
     * Updates the current player in the game.
     */
    public void updateCurrentPlayer() {
        this.mainpanel.updateCurrentPlayer(this.controller.getCurrentPlayer());
    }

    /**
     * Sets the number of opponent cards in the game.
     *
     * @param setNumberOpponentCards the number of opponent cards to be set
     */
    public void setNumberOpponentCards(final int setNumberOpponentCards) {
        this.mainpanel.setOpponentCards(setNumberOpponentCards);
    }

    /**
     * Updates the game view by refreshing the hand, current player, opponent cards,
     * cards on the table, points, deck status, and the UI.
     */
    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            updateHand();
            updateCurrentPlayer();
            updateOpponentCards();
            updateCardsOnTable();
            updatePoints();
            updateDeckStatus();
            refreshUI();
        });
    }

    /**
     * Updates the current player's hand by retrieving the hand from the controller
     * and refreshing the UI.
     */
    private void updateHand() {
        List<Card> hand = controller.getHand();
        if (hand != null) {
            setHand(hand);
        }
        this.refreshUI();
    }

    /**
     * Updates the number of opponent cards in the game.
     */
    private void updateOpponentCards() {
        int opponentCardCount = controller.getOpponentHand();
        setNumberOpponentCards(opponentCardCount);
    }

    /**
     * Updates the cards currently on the table in the game view.
     */
    private void updateCardsOnTable() {
        List<Card> cardsOnTable = controller.getCardsOnTable();
        if (cardsOnTable != null) {
            setCardsOnTable(cardsOnTable);
        }
    }

    /**
     * Updates the points for the bot and the player in the game view.
     */
    private void updatePoints() {
        List<Integer> points = this.controller.getPlayerPoints();
        this.botPoints = points.get(1);
        this.playerPoints = points.get(0);

        mainpanel.setPoints(botPoints, playerPoints);
    }

    /**
     * Updates the deck status in the game view by retrieving the number of cards in
     * the deck from the controller and updating the main panel.
     */
    private void updateDeckStatus() {
        int deckNumberOfCards = controller.deckNumberOfCards();
        mainpanel.setDeck(deckNumberOfCards);
    }

    /**
     * Refreshes the user interface by revalidating and repainting the main panel.
     */
    private void refreshUI() {
        this.mainpanel.revalidate();
        this.mainpanel.repaint();
    }

    /**
     * Overrides the stop method to determine the winner of the game if the bot
     * points are higher than the player points the winner is bot else player.
     * if they have the same number of points the winner is a tie.
     */
    @Override
    public void stop() {
        String winner;
        if (botPoints > playerPoints) {
            winner = botName;
        } else if (botPoints < playerPoints) {
            winner = playerName;
        } else {
            winner = "pareggio";
        }
        EndPane endPane = new EndPane(controller, winner);
        endPane.showTwoCommandsDialog();
    }

    /**
     * Starts the game by updating the game state, making the game visible, and
     * setting the player names.
     */
    @Override
    public void start() {
        this.update();
        this.setVisible(true);
        this.setNames();
    }

    /**
     * Sets the names of the bot and the player.
     * 
     * Retrieves the names from the controller and updates the main panel with the
     * bot and player names.
     */
    private void setNames() {

        List<String> names = this.controller.getPlayerNames();
        botName = names.get(1);
        playerName = names.get(0);
        this.mainpanel.setNames(botName, playerName);
    }

    /**
     * Sets the flag indicating whether the player can play to false.
     * 
     * This method is used to signal that the AI is currently playing.
     */
    @Override
    public void aiCanPlay() {
        this.canPlayerPlay = false;
    }

    /**
     * Indicates that the player is allowed to play.
     * 
     * Sets the canPlayerPlay flag to true.
     */
    @Override
    public void playerCanPlay() {
        this.canPlayerPlay = true;
    }
}
