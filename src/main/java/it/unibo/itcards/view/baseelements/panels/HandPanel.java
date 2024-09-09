package it.unibo.itcards.view.baseelements.panels;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.cardview.CardButton;

public abstract class HandPanel extends JPanel {

    public abstract void init(Dimension d);

    public abstract void setCards(List<CardButton> cards);

}
