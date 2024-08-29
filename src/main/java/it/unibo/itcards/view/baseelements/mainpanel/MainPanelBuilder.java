package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.Dimension;

import it.unibo.itcards.view.baseelements.panels.CentralPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanel;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

public class MainPanelBuilder {
    Dimension dimension;
    HandPanel handPanel;
    OpponentPanel opponentPanel;
    CentralPanel centralPanel;

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
        return mainPanel;
    }
}
