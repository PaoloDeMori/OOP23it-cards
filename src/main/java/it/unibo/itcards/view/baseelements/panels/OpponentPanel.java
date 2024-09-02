package it.unibo.itcards.view.baseelements.panels;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class OpponentPanel extends JPanel{

   public abstract void init(Dimension d);

    public abstract void setOpponentCards(int n);

}