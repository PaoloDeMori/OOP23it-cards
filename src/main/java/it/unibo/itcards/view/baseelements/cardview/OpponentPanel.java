package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;

public class OpponentPanel extends JPanel {
    CardFactory factory;

    public OpponentPanel(Dimension d) {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 10, 5);
        this.setBackground(Color.BLUE);
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
        factory = new CardFactory(this.getSize(),"retro");
    }

    public void setOpponentCards(int n) {
        for (int i = 0; i < n; i++) {
            this.add(factory.build());
            revalidate();
            repaint();
        }
    }
}
