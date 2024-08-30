package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.panels.CentralPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanelImpl;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

import javax.swing.*;
import java.util.*;

public class MainPanel extends JPanel{
    private final Dimension handPanelDimension;
    private final Dimension lateralPanelDimension;
    private final Dimension upPanelDimension;
    private HandPanel handPanel;
    private OpponentPanel opponentPanel;
    private CentralPanel centralPanel;
    private LateralPanel rightPanel;
    private LateralPanel leftPanel;
    private final static int HAND_PANEL_RATIO = 4;
    private final static int OPPONENT_PANEL_RATIO = 6;
    private final static int LATERAL_PANEL_RATIO = 13;

    protected MainPanel(Dimension d) {
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.handPanelDimension = new Dimension(this.getWidth(), this.getHeight() / HAND_PANEL_RATIO);
        this.lateralPanelDimension = new Dimension(this.getWidth() / LATERAL_PANEL_RATIO, this.getHeight());
        this.upPanelDimension = new Dimension(this.getWidth() , this.getHeight()/ OPPONENT_PANEL_RATIO);
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

    public void setCentralPanel(CentralPanel centralPanel){
        this.centralPanel = centralPanel;
        this.centralPanel.init();
        this.add(centralPanel,BorderLayout.CENTER);
    }

    public void setLateralPanel(LateralPanel lateralPanel , String borderLayoutPosition){
        if(borderLayoutPosition.equals(BorderLayout.WEST)){
            this.leftPanel = lateralPanel;
            this.leftPanel.init(lateralPanelDimension);
            this.add(leftPanel,BorderLayout.WEST);
        }else if(borderLayoutPosition.equals(BorderLayout.EAST)){
            this.rightPanel = lateralPanel;
            this.rightPanel.init(lateralPanelDimension);
            this.add(rightPanel,BorderLayout.EAST);
        }
        else{
            throw new IllegalArgumentException("The position must be 'left' or 'right'");
        }
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

    public void setNames(String botName, String playerName) {
       this.leftPanel.setNames(botName,playerName);
    }

    public void setPoints(int botPoints, int playerPoint) {
        this.rightPanel.setPoints(botPoints,playerPoint);
    }


}
