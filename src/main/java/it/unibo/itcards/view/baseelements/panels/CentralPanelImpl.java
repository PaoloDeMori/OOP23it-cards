package it.unibo.itcards.view.baseelements.panels;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.baseelements.BriscolaView;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class CentralPanelImpl extends CentralPanel {

    BorderLayout layout;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel deck;
    JPanel briscola = null;
    final JLabel turn = new JLabel("Il tuo turno");
    JPanel cardsPanel = null;
    JPanel playerTurnPanel;
    JPanel deckPanel;
    JLabel numberOfCards;

    public CentralPanelImpl() {
        layout = new BorderLayout();
        this.setBackground(BriscolaView.invisibleColor);
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        this.numberOfCards = new JLabel();
        this.numberOfCards.setText("0");
        numberOfCards.setFont(new Font("Arial", Font.BOLD, 20));
        numberOfCards.setForeground(new Color(255, 217, 46));
    }

    @Override
    public void setCardsOnTable(List<JPanel> cards) {
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
    public void setDeck(int deckNumberOfCards) {
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
            this.numberOfCards.setText("Carte rimanenti " + Integer.toString(deckNumberOfCards));
        }
    }

    @Override
    public void init(Dimension d) {
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
        this.leftPanel.setBackground(BriscolaView.invisibleColor);
        cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardsPanel.setBackground(BriscolaView.invisibleColor);
        leftPanel.add(cardsPanel, BorderLayout.CENTER);
        turn.setFont(new Font("Arial", Font.BOLD, 15));
        turn.setForeground(new Color(255, 217, 46));
        this.playerTurnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.playerTurnPanel.setBackground(BriscolaView.invisibleColor);
        playerTurnPanel.add(turn);
        this.leftPanel.add(playerTurnPanel, BorderLayout.SOUTH);


    }

    private void setRightPanel(Dimension d) {
        this.deckPanel = new JPanel(new FlowLayout());
        this.deckPanel.setBackground(BriscolaView.invisibleColor);
        this.numberOfCards.setBackground(BriscolaView.invisibleColor);

        this.rightPanel.setBackground(BriscolaView.invisibleColor);
        this.rightPanel.setPreferredSize(new Dimension((int) (d.getWidth() / 5), (int) d.getHeight()));
        this.deckPanel.setPreferredSize(new Dimension((int) (this.rightPanel.getPreferredSize().getWidth() / 5),
                (int) (this.rightPanel.getPreferredSize().getWidth() / 1.2)));
        this.rightPanel.setLayout(new BorderLayout());
        this.rightPanel.add(deckPanel, BorderLayout.NORTH);
        this.rightPanel.add(numberOfCards, BorderLayout.SOUTH);

    }

    @Override
    public void updateCurrentPlayer(Player player) {
        if (player.isAi()) {
            this.playerTurnPanel.setVisible(false);
        } else {
            this.playerTurnPanel.setVisible(true);
        }
        this.revalidate();
        this.repaint();
    }
}