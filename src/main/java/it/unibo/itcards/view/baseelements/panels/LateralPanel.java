package it.unibo.itcards.view.baseelements.panels;

import javax.swing.JPanel;

import it.unibo.itcards.view.briscola.BriscolaView;

import java.awt.BorderLayout;
import java.awt.Dimension;

public abstract class LateralPanel extends JPanel {

    private BorderLayout layout;
    /**
     * Creates new form LateralPanel
     */
    public LateralPanel() {
        layout = new BorderLayout();
        this.setBackground(BriscolaView.invisibleColor);
    }
    /**
     * set the points of the player in the right panel and the bot
     * @param botPoints
     * @param playerPoint
     */
    public abstract void setPoints(final int botPoints , final int playerPoint);

    /**
     * set the center of the panel
     * @param panel
     */
    public abstract void setCenter(final JPanel panel);

    /**
     * set the names of the player and the bot
     * @param botName
     * @param playerName
     */
    public abstract void setNames(final String botName , final String playerName);

    /**
     * initialize the panel
     * @param d
     */
    public  void init(final Dimension d){
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }
}
