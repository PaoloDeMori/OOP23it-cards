package it.unibo.itcards.view.briscola;

import java.awt.FlowLayout;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;

import java.awt.Dimension;

public class OpponentPanelBriscola extends OpponentPanel {
    private FlowLayout layout;

    public OpponentPanelBriscola() {
        layout = new FlowLayout(FlowLayout.CENTER, View.STANDARD_HGAP, View.STANDARD_VGAP);
        this.setBackground(View.INVISIBLE_COLOR);
    }

    @Override
    public void init(final Dimension d) {
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }

    @Override
    public void setOpponentCards(final int numberOpponentCards) {
        this.removeAll();
        for (int i = 0; i < numberOpponentCards; i++) {
            this.add(StaticCardFactory.build("retro", getSize()));
            revalidate();
            repaint();
        }
    }
}
