package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;

public abstract class HandPanel extends JPanel{
    List<CardButton> cards;
    public abstract void init(Dimension d);

    public abstract void setCards(List<CardButton> cards);

}