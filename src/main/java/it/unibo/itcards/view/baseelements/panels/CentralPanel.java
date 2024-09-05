package it.unibo.itcards.view.baseelements.panels;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.cardview.CardPanel;

import java.util.List;
import java.awt.Dimension;


public abstract class CentralPanel extends JPanel {

    public abstract void setCardsOnTable(List<CardPanel> cards);

    public abstract void setDeck(boolean hasCards);

    public abstract void init(Dimension d);
}
