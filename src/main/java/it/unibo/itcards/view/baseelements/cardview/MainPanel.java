package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.*;

public class MainPanel extends JPanel{
    private final Dimension handPanelDimension;
    private final Dimension lateralPanelDimension;
    private final Dimension upPanelDimension;
    private HandPanel handpanel;
    private OpponentPanel opponentPanel;
    public MainPanel(Dimension d) {
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.handPanelDimension = new Dimension(this.getWidth(), this.getHeight() / 4);
        this.lateralPanelDimension = new Dimension(this.getWidth() / 13, this.getHeight());
        this.upPanelDimension = new Dimension(this.getWidth() , this.getHeight()/ 4);
        this.handpanel=new HandPanel(handPanelDimension);
        this.add(handpanel,BorderLayout.SOUTH);
        this.opponentPanel=new OpponentPanel(upPanelDimension);
        this.add(opponentPanel,BorderLayout.NORTH);
    }

    public Dimension getHandPanelDimension(){
        return this.handPanelDimension;
    }

    public void setHand(List<CardPanel> cards){
        this.handpanel.setCards(cards);
    }

    public void setOpponentCards(int n){
        this.opponentPanel.setOpponentCards(n);
    }


}
