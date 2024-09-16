package it.unibo.itcards.view.baseelements.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * this class represent the opponent panel.
 */
public abstract class OpponentPanel extends JPanel {
    private static final long serialVersionUID = 3L;
    /**
     * initialize the panel.
     * 
     * @param d dimension
     */
    public abstract void init(Dimension d);

    /**
     * set the opponent cards.
     * 
     * @param n number of cards
     */
    public abstract void setOpponentCards(int n);

}
