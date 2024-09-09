package it.unibo.itcards.view.briscola;

import java.awt.FlowLayout;

import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

import java.awt.Dimension;

public class OpponentPanelBriscola extends OpponentPanel {
    FlowLayout layout;
    public OpponentPanelBriscola() {
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
