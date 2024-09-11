package it.unibo.itcards.view.baseelements.panels;

import java.awt.FlowLayout;
import java.awt.Dimension;

import java.util.List;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;

/**
 * A custom panel for displaying a player's hand in the Briscola game. 
 * This panel uses a FlowLayout to arrange the card buttons horizontally and can be dynamically updated
 * based on the cards in the player's hand.
 */
public class HandPanelImpl extends HandPanel {
    private final FlowLayout layout;

    /**
     * Constructor that initializes the panel with a FlowLayout aligned in the center.
     * The panel background is set to be invisible to match the rest of the UI.
     */
    public HandPanelImpl() {
        layout = new FlowLayout(FlowLayout.CENTER, View.STANDARD_HGAP, View.STANDARD_VGAP);
        this.setBackground(View.INVISIBLE_COLOR);
    }

    /**
     * Initializes the panel's layout and size based on the provided dimension.
     * This method sets the layout to a centered FlowLayout and applies the preferred dimensions.
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
     * Updates the panel by adding a new set of card buttons. This method removes any existing
     * cards in the panel before adding the new ones, ensuring the display is updated properly.
     * 
     * @param cards A list of CardButton objects representing the player's current hand.
     */
    @Override
    public void setCards(final List<CardButton> cards) {
        this.removeAll();
        for (CardButton cp : cards) {
            this.add(cp);
        }
        revalidate();
        repaint();
    }
}
