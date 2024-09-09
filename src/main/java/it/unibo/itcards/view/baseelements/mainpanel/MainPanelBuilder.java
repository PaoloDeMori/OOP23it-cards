package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import it.unibo.itcards.view.baseelements.panels.CentralPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanel;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

public class MainPanelBuilder {
    private Dimension dimension;
    private HandPanel handPanel;
    private OpponentPanel opponentPanel;
    private CentralPanel centralPanel;
    private LateralPanel lefLateralPanel;
    private LateralPanel rightLateralPanel;

    public MainPanelBuilder(final Dimension dimension) {
        this.dimension = dimension;
    }

    public MainPanelBuilder addHandPanel(final HandPanel handPanel) {
        this.handPanel = handPanel;
        return this;
    }

    public MainPanelBuilder addOpponentPanel(final OpponentPanel opponentPanel) {
        this.opponentPanel = opponentPanel;
        return this;
    }

    public MainPanelBuilder addLeftPanel(final LateralPanel lefLateralPanel) {
        this.lefLateralPanel = lefLateralPanel;
        return this;
    }

    public MainPanelBuilder addRightPanel(final LateralPanel rightLateralPanel) {
        this.rightLateralPanel = rightLateralPanel;
        return this;
    }

    public MainPanelBuilder addCentralPanel(final CentralPanel centralPanel) {
        this.centralPanel = centralPanel;
        return this;
    }

    public MainPanel build() {
        MainPanel mainPanel = new MainPanel(dimension);
        if (this.handPanel != null) {
            mainPanel.setHandPanel(handPanel);
        }
        if (this.opponentPanel != null) {
            mainPanel.setOpponentPanel(opponentPanel);
        }
        if (this.centralPanel != null) {
            mainPanel.setCentralPanel(centralPanel);
        }
        if (this.lefLateralPanel != null) {
            mainPanel.setLateralPanel(lefLateralPanel, BorderLayout.WEST);
        }
        if (this.rightLateralPanel != null) {
            mainPanel.setLateralPanel(rightLateralPanel, BorderLayout.EAST);
        }
        if (this.centralPanel != null) {
            mainPanel.setCentralPanel(centralPanel);
        }
        return mainPanel;
    }
}
