package it.unibo.itcards.view.briscola;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;
import it.unibo.itcards.view.baseelements.panels.CentralPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * A custom panel for the Briscola game that handles the display of cards, the
 * player's turn, and the deck status. This panel is divided into left and
 * right sections: the left for cards on the table, and the right for the deck
 * and the number of cards remaining. It also dynamically updates based on
 * player turns and game progress.
 */
public class CentralPanelBriscola extends CentralPanel {

    private static final long serialVersionUID = 3L;
    private final BorderLayout layout;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private JPanel deck;
    private JPanel briscola;
    private final JLabel turn = new JLabel("Il tuo turno");
    private JPanel cardsPanel;
    private JPanel playerTurnPanel;
    private JPanel deckPanel;
    private final JLabel numberOfCards;
    private static final int NUMBER_OF_CARDS_FONT_SIZE = 20;
    private static final int NOTIFY_TURN_FONT_SIZE = 15;
    private static final int RIGHT_PANEL_WIDTH_RATIO = 5;
    private static final double DECK_PANEL_HEIGHT_RATIO = 1.2;

    /**
     * Constructor that initializes the layout and basic components of the panel.
     * The panel includes a left section for cards and a right section for the deck
     * and card count.
     */
    public CentralPanelBriscola() {
        layout = new BorderLayout();
        this.setBackground(View.INVISIBLE_COLOR);
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        this.numberOfCards = new JLabel();
        this.numberOfCards.setText("0");
        numberOfCards.setFont(new Font("Arial", Font.BOLD, NUMBER_OF_CARDS_FONT_SIZE));
        numberOfCards.setForeground(View.PLAYER_COLOR);
    }

    /**
     * Updates the display of the cards currently on the table. This method removes
     * any previously displayed cards and adds the new set of cards provided in the
     * list. The first card in the list is treated as the Briscola card, which is
     * displayed separately in the deck panel.
     * 
     * @param cards A list of JPanels representing the cards to be displayed on the
     *              table.
     */
    @Override
    public void setCardsOnTable(final List<JPanel> cards) {
        cardsPanel.removeAll();
        if (briscola == null) {
            briscola = cards.get(0);
            this.deckPanel.add(briscola);
        }
        cards.remove(0);
        for (final var jPanel : cards) {
            this.cardsPanel.add(jPanel);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Updates the deck's display based on the number of cards remaining. If the
     * deck is empty, it removes the deck panel. Otherwise, it adds or maintains
     * the deck card's back image.
     * 
     * @param deckNumberOfCards The number of cards remaining in the deck.
     */
    @Override
    public void setDeck(final int deckNumberOfCards) {
        this.numberOfCards.setText("Carte rimanenti " + Integer.toString(deckNumberOfCards));
        if (deckNumberOfCards <= 0) {
            if (deck != null) {
                deckPanel.removeAll();
                deck = null;
            }
        } else {
            if (deck == null) {
                deck = StaticCardFactory.build("retro", this.deckPanel.getSize());
                this.deckPanel.add(deck);
            }

        }
    }

    /**
     * Initializes the entire panel by setting its size, layout, and organizing the
     * left and right sections. The left section displays the cards on the table,
     * while the right section handles the deck and card count.
     * 
     * @param d The dimension of the panel.
     */
    @Override
    public void init(final Dimension d) {
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setLeftPanel();
        this.setRightPanel(d);

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    /**
     * Configures the left panel, which is responsible for displaying the cards on
     * the table and notifying the user whose turn it is. The left panel contains
     * the card panel and the player's turn indicator.
     */
    private void setLeftPanel() {

        this.leftPanel.setLayout(new BorderLayout());
        this.leftPanel.setBackground(View.INVISIBLE_COLOR);
        cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardsPanel.setBackground(View.INVISIBLE_COLOR);
        leftPanel.add(cardsPanel, BorderLayout.CENTER);
        turn.setFont(new Font("Arial", Font.BOLD, NOTIFY_TURN_FONT_SIZE));
        turn.setForeground(View.PLAYER_COLOR);
        this.playerTurnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.playerTurnPanel.setBackground(View.INVISIBLE_COLOR);
        playerTurnPanel.add(turn);
        this.leftPanel.add(playerTurnPanel, BorderLayout.SOUTH);

    }

    /**
     * Configures the right panel, which displays the deck and the number of
     * remaining cards. This panel is resized dynamically based on the dimension
     * provided, and it contains the deck's visual representation and the card
     * counter.
     * 
     * @param d The dimension of the panel.
     */
    private void setRightPanel(final Dimension d) {
        this.deckPanel = new JPanel(new FlowLayout());
        this.deckPanel.setBackground(View.INVISIBLE_COLOR);
        this.numberOfCards.setBackground(View.INVISIBLE_COLOR);

        this.rightPanel.setBackground(View.INVISIBLE_COLOR);
        this.rightPanel
                .setPreferredSize(new Dimension((int) (d.getWidth() / RIGHT_PANEL_WIDTH_RATIO), (int) d.getHeight()));
        this.deckPanel.setPreferredSize(
                new Dimension((int) (this.rightPanel.getPreferredSize().getWidth() / RIGHT_PANEL_WIDTH_RATIO),
                        (int) (this.rightPanel.getPreferredSize().getHeight() / DECK_PANEL_HEIGHT_RATIO)));
        this.rightPanel.setLayout(new BorderLayout());
        this.rightPanel.add(deckPanel, BorderLayout.NORTH);
        this.rightPanel.add(numberOfCards, BorderLayout.SOUTH);

    }

    /**
     * Updates the panel to reflect which player's turn it currently is. If the
     * current player is an AI, the player's turn indicator is hidden; otherwise,
     * it is shown.
     * 
     * @param player The current player, used to determine if the player is an AI or
     *               a human.
     */
    @Override
    public void updateCurrentPlayer(final Player player) {
        if (player.isAi()) {
            this.playerTurnPanel.setVisible(false);
        } else {
            this.playerTurnPanel.setVisible(true);
        }
        this.revalidate();
        this.repaint();
    }
}
