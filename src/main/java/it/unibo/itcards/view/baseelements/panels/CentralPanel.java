package it.unibo.itcards.view.baseelements.panels;
import javax.swing.JPanel;

import it.unibo.itcards.model.baseelements.player.Player;

import java.awt.Dimension;
import java.util.List;


public abstract class CentralPanel extends JPanel {

    public abstract void setCardsOnTable(List<JPanel> cards);

    public abstract void setDeck(int numberOfCards);

    public abstract void init(Dimension d);

    public abstract void updateCurrentPlayer(Player player);
}
