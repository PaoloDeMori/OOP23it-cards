package it.unibo.itcards.view.baseelements.panels;
import javax.swing.JPanel;


import java.awt.Dimension;
import java.util.List;
import java.awt.Dimension;


public abstract class CentralPanel extends JPanel {

    public abstract void setCardsOnTable(List<JPanel> cards);

    public abstract void setDeck(boolean hasCards);

    public abstract void init(Dimension d);
}
