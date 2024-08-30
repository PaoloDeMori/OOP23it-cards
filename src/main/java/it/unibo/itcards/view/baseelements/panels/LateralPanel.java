package it.unibo.itcards.view.baseelements.panels;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

public abstract class LateralPanel extends JPanel {

    private BorderLayout layout;

    public LateralPanel() {
        layout = new BorderLayout();
    }
    public abstract void setPoints(final int botPoints , final int playerPoint);

    public abstract void setCenter(final JPanel panel);

    public abstract void setNames(final String botName , final String playerName);

    public  void init(final Dimension d){
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }
}
