package it.unibo.itcards.view.briscola;

import java.awt.FlowLayout;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

import java.awt.Dimension;

/**
 * A custom panel that represents the opponent's cards in the Briscola game.
 * This panel uses a FlowLayout to arrange the back of the opponent's cards in a visually centered manner.
 * It dynamically updates based on the number of cards the opponent has.
 */
public class OpponentPanelBriscola extends OpponentPanel {
    private FlowLayout layout;

    /**
     * Constructor that initializes the layout and appearance of the opponent's panel.
     * The panel uses a centered FlowLayout and has an invisible background.
     */
    public OpponentPanelBriscola() {
        layout = new FlowLayout(FlowLayout.CENTER, View.STANDARD_HGAP, View.STANDARD_VGAP);
        this.setBackground(View.INVISIBLE_COLOR);
    }

    /**
     * Initializes the panel's layout and dimensions. The panel is configured to use the FlowLayout 
     * defined in the constructor, and its size is set according to the provided dimension.
     * 
     * @param d The dimension to set for the panel.
     */
    @Override
    public void init(final Dimension d) {
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }

    /**
     * Updates the panel with a specified number of opponent's cards. For each card in the opponent's hand,
     * this method adds a card back image to the panel, simulating the visual display of the opponent's hand.
     * 
     * @param numberOpponentCards The number of cards the opponent currently has.
     */
    @Override
    public void setOpponentCards(final int numberOpponentCards) {
        this.removeAll();
        for (int i = 0; i < numberOpponentCards; i++) {
            this.add(StaticCardFactory.build("retro", getSize()));
            revalidate();
            repaint();
        }
    }
}
