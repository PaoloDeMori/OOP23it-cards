package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.Dimension;

import it.unibo.itcards.view.baseelements.cardview.HandPanel;
import it.unibo.itcards.view.baseelements.cardview.OpponentPanel;

public class MainPanelBuilder {
    Dimension dimension;
    HandPanel handPanel;
    OpponentPanel opponentPanel;

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
        return mainPanel;
    }
}
