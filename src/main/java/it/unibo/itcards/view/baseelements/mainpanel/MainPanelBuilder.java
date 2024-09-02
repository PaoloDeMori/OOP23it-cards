package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import it.unibo.itcards.view.baseelements.panels.CentralPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanel;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

public class MainPanelBuilder {
    Dimension dimension;
    HandPanel handPanel;
    OpponentPanel opponentPanel;
    CentralPanel centralPanel;
    LateralPanel lefLateralPanel;
    LateralPanel rightLateralPanel;
    public MainPanelBuilder(Dimension dimension){
        this.dimension=dimension;
    }

    public MainPanelBuilder addHandPanel(HandPanel handPanel){
        this.handPanel=handPanel;
        return this;
    }

    public MainPanelBuilder addopponentPanel(OpponentPanel opponentPanel){
        this.opponentPanel=opponentPanel;
        return this;
    }

    public MainPanelBuilder addLeftPanel(LateralPanel lefLateralPanel){
        this.lefLateralPanel=lefLateralPanel;
        return this;
    }

    public MainPanelBuilder addRightPanel(LateralPanel rightLateralPanel){
        this.rightLateralPanel=rightLateralPanel;
        return this;
    }

    public MainPanel build(){
        MainPanel mainPanel = new MainPanel(dimension);
        if(this.handPanel!=null){
            mainPanel.setHandPanel(handPanel);
        }
        if (this.opponentPanel!=null){
            mainPanel.setOpponentPanel(opponentPanel);
        }
        if (this.centralPanel!=null){
            mainPanel.setCentralPanel(centralPanel);
        }
        if (this.lefLateralPanel!=null){
            mainPanel.setLateralPanel(lefLateralPanel,BorderLayout.WEST);
        }
        if (this.rightLateralPanel!=null){
            mainPanel.setLateralPanel(rightLateralPanel, BorderLayout.EAST);
        }
        return mainPanel;
    }
}
