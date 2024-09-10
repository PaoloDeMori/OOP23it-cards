package it.unibo.itcards.view.baseelements.panels;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.cardview.CardButton;

/**
 * this class represent the hand panel.
 */
public abstract class HandPanel extends JPanel {

    /**
     * initialize the panel.
     * 
     * @param d dimension
     */
    public abstract void init(Dimension d);

    /**
     * set the cards on the panel.
     * 
     * @param cards
     */
    public abstract void setCards(List<CardButton> cards);

}
