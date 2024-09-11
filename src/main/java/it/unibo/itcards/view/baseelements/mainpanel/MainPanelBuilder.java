package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import it.unibo.itcards.view.baseelements.panels.CentralPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanel;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

/**
 * this class builds the main panel.
 */
public class MainPanelBuilder {
    private Dimension dimension;
    private HandPanel handPanel;
    private OpponentPanel opponentPanel;
    private CentralPanel centralPanel;
    private LateralPanel lefLateralPanel;
    private LateralPanel rightLateralPanel;

    /**
     * @param dimension the dimension of the main panel
     */
    public MainPanelBuilder(final Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Adds a hand panel to the main panel builder.
     *
     * @param handPanel the hand panel to be added
     * @return the main panel builder instance
     */
    public MainPanelBuilder addHandPanel(final HandPanel handPanel) {
        this.handPanel = handPanel;
        return this;
    }

    /**
     * Adds an opponent panel to the main panel builder.
     *
     * @param opponentPanel the opponent panel to be added
     * @return the main panel builder instance
     */
    public MainPanelBuilder addOpponentPanel(final OpponentPanel opponentPanel) {
        this.opponentPanel = opponentPanel;
        return this;
    }

    /**
     * Adds a left lateral panel to the main panel builder.
     *
     * @param lefLateralPanel the left lateral panel to be added
     * @return the main panel builder instance
     */
    public MainPanelBuilder addLeftPanel(final LateralPanel lefLateralPanel) {
        this.lefLateralPanel = lefLateralPanel;
        return this;
    }

    /**
     * Adds a right lateral panel to the main panel builder.
     *
     * @param rightLateralPanel the right lateral panel to be added
     * @return the main panel builder instance
     */
    public MainPanelBuilder addRightPanel(final LateralPanel rightLateralPanel) {
        this.rightLateralPanel = rightLateralPanel;
        return this;
    }

    /**
     * Adds a central panel to the main panel builder.
     *
     * @param centralPanel the central panel to be added
     * @return the main panel builder instance
     */
    public MainPanelBuilder addCentralPanel(final CentralPanel centralPanel) {
        this.centralPanel = centralPanel;
        return this;
    }

    /**
     * Builds a MainPanel instance based on the provided components.
     *
     * @return the constructed MainPanel instance
     */
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
