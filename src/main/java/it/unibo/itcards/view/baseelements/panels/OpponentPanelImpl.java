package it.unibo.itcards.view.baseelements.panels;

import java.awt.FlowLayout;

import it.unibo.itcards.view.baseelements.BriscolaView;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;
import java.awt.Dimension;

public class OpponentPanelImpl extends OpponentPanel {
    FlowLayout layout;
    public OpponentPanelImpl() {
        layout = new FlowLayout(FlowLayout.CENTER, 10, 5);
        this.setBackground(BriscolaView.invisibleColor);
    }

    @Override
    public void init(Dimension d){
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }

    @Override
    public void setOpponentCards(int n) {
        this.removeAll();
        for (int i = 0; i < n; i++) {
            this.add(StaticCardFactory.build("retro", getSize()));
            revalidate();
            repaint();
        }
    }
}
