package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.HandPanel;
import it.unibo.itcards.view.baseelements.cardview.HandPanelImpl;
import it.unibo.itcards.view.baseelements.cardview.OpponentPanel;

import javax.swing.*;
import java.util.*;

public class MainPanel extends JPanel{
    private final Dimension handPanelDimension;
    private final Dimension lateralPanelDimension;
    private final Dimension upPanelDimension;
    private HandPanel handPanel;
    private OpponentPanel opponentPanel;

    protected MainPanel(Dimension d) {
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.handPanelDimension = new Dimension(this.getWidth(), this.getHeight() / 4);
        this.lateralPanelDimension = new Dimension(this.getWidth() / 13, this.getHeight());
        this.upPanelDimension = new Dimension(this.getWidth() , this.getHeight()/ 4);
    }

    public void setHandPanel(HandPanel handPanel){
        this.handPanel=handPanel;
        this.handPanel.init(handPanelDimension);
        this.add(handPanel,BorderLayout.SOUTH);
    }

    public void setOpponentPanel(OpponentPanel opponentPanel){
        this.opponentPanel=opponentPanel;
        this.opponentPanel.init(upPanelDimension);
        this.add(opponentPanel,BorderLayout.NORTH);
    }

    public Dimension getHandPanelDimension(){
        return this.handPanelDimension;
    }

    public void setHand(List<CardButton> cards){
        this.handPanel.setCards(cards);
    }

    public void setOpponentCards(int n){
        this.opponentPanel.setOpponentCards(n);
    }


}
