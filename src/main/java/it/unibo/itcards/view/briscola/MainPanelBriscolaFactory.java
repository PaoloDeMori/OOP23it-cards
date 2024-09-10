package it.unibo.itcards.view.briscola;

import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;
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
                .addHandPanel(new HandPanelBriscola())
                .addOpponentPanel(new OpponentPanelBriscola())
                .addLeftPanel(new LeftPanelBriscola())
                .addRightPanel(new RightPanelBriscola())
                .addCentralPanel(new CentralPanelBriscola())
                .build();
    }

}
