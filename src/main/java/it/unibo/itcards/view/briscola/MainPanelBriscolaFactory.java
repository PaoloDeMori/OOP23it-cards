package it.unibo.itcards.view.briscola;

import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;
import it.unibo.itcards.view.baseelements.panels.HandPanelImpl;
import it.unibo.itcards.view.baseelements.panels.LeftPanelImpl;
import it.unibo.itcards.view.baseelements.panels.OpponentPanelImpl;
import it.unibo.itcards.view.baseelements.panels.RightPanelImpl;

import java.awt.Dimension;

/**
 * this class builds the main panel.
 */
public final class MainPanelBriscolaFactory {
    /**
     * this class builds the main panel.
     */
    private MainPanelBriscolaFactory() {
    }

    /**
     * Builds and returns a new MainPanel instance with the specified dimension.
     *
     * @param  d  the dimension of the main panel
     * @return    a new MainPanel instance
     */
    public static MainPanel build(final Dimension d) {
        return new MainPanelBuilder(d)
                .addHandPanel(new HandPanelImpl())
                .addOpponentPanel(new OpponentPanelImpl())
                .addLeftPanel(new LeftPanelImpl())
                .addRightPanel(new RightPanelImpl())
                .addCentralPanel(new CentralPanelBriscola())
                .build();
    }

}
