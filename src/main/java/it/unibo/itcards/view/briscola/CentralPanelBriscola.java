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

public class CentralPanelBriscola extends CentralPanel {

    private BorderLayout layout;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel deck;
    private JPanel briscola = null;
    private final JLabel turn = new JLabel("Il tuo turno");
    private JPanel cardsPanel = null;
    private JPanel playerTurnPanel;
    private JPanel deckPanel;
    private JLabel numberOfCards;
    private static final int NUMBER_OF_CARDS_FONT_SIZE = 20;
    private static final int NOTIFY_TURN_FONT_SIZE = 15;
    private static final int RIGHT_PANEL_WIDTH_RATIO = 5;
    private static final double DECK_PANEL_HEIGHT_RATIO = 1.2;

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

    @Override
    public void setCardsOnTable(final List<JPanel> cards) {
        cardsPanel.removeAll();
        if (briscola == null) {
            briscola = cards.get(0);
            this.deckPanel.add(briscola);
        }
        cards.remove(0);
        for (var jPanel : cards) {
            this.cardsPanel.add(jPanel);
        }
        this.revalidate();
        this.repaint();
    }

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
