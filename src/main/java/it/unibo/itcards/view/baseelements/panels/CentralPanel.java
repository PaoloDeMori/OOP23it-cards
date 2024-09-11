package it.unibo.itcards.view.baseelements.panels;

import javax.swing.JPanel;

import it.unibo.itcards.model.baseelements.player.Player;

import java.awt.Dimension;
import java.util.List;

/**
 * this class represent the central panel.
 */
public abstract class CentralPanel extends JPanel {

    /**
     * set the cards on the table.
     *
     * @param cards the cards on the table
     */
    public abstract void setCardsOnTable(List<JPanel> cards);

    /**
     * set the deck for the central panel based on the given number of cards.
     * 
     * @param numberOfCards the number of cards to be set in the deck
     */
    public abstract void setDeck(int numberOfCards);

    /**
     * initialize the panel.
     * 
     * @param d dimension
     */
    public abstract void init(Dimension d);

    /**
     * update the current player.
     * 
     * @param player the current player to be updated
     */
    public abstract void updateCurrentPlayer(Player player);
}
