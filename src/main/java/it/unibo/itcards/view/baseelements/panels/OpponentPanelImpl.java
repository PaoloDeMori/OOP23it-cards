package it.unibo.itcards.view.baseelements.panels;

import java.awt.FlowLayout;

import it.unibo.itcards.view.baseelements.cardview.CardFactory;

import java.awt.Dimension;
import java.awt.Color;

public class OpponentPanelImpl extends OpponentPanel {
    CardFactory factory;
    FlowLayout layout;
    public OpponentPanelImpl() {
        layout = new FlowLayout(FlowLayout.CENTER, 10, 5);
        this.setBackground(Color.BLUE);
    }

    @Override
    public void init(Dimension d){
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
        factory = new CardFactory(this.getSize(),"retro");
    }

    @Override
    public void setOpponentCards(int n) {
        for (int i = 0; i < n; i++) {
            this.add(factory.build());
            revalidate();
            repaint();
        }
    }
}
