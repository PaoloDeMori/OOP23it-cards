package it.unibo.itcards.view.briscola;

import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;
import java.awt.Dimension;

public final class MainPanelBriscolaFactory {
    private MainPanelBriscolaFactory() {
    }

    public static MainPanel build(Dimension d) {
        return new MainPanelBuilder(d)
                .addHandPanel(new HandPanelBriscola())
                .addOpponentPanel(new OpponentPanelBriscola())
                .addLeftPanel(new LeftPanelBriscola())
                .addRightPanel(new RightPanelBriscola())
                .addCentralPanel(new CentralPanelBriscola())
                .build();
    }

}
