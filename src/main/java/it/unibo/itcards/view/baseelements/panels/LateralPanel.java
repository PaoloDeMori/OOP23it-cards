package it.unibo.itcards.view.baseelements.panels;

import javax.swing.JPanel;

import it.unibo.itcards.view.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * this class represent the lateral panel.
 */
public abstract class LateralPanel extends JPanel {
    private static final long serialVersionUID = 3L;
    private final BorderLayout layout;

    /**
     * Creates new form LateralPanel.
     */
    public LateralPanel() {
        layout = new BorderLayout();
        this.setBackground(View.INVISIBLE_COLOR);
    }

    /**
     * set the points of the player in the right panel and the bot.
     * 
     * @param botPoints
     * @param playerPoint
     */
    public abstract void setPoints(int botPoints, int playerPoint);

    /**
     * set the center of the panel.
     * 
     * @param panel
     */
    public abstract void setCenter(JPanel panel);

    /**
     * set the names of the player and the bot.
     * 
     * @param botName
     * @param playerName
     */
    public abstract void setNames(String botName, String playerName);

    /**
     * initialize the panel.
     * 
     * @param d
     */
    public void init(final Dimension d) {
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }
}
