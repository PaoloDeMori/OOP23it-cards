package it.unibo.itcards.view.briscola;

import java.awt.FlowLayout;
import java.awt.Dimension;

import java.util.List;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.panels.HandPanel;

public class HandPanelBriscola extends HandPanel {
    private final FlowLayout layout;

    public HandPanelBriscola() {
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
    public void setCards(final List<CardButton> cards) {
        this.removeAll();
        for (CardButton cp : cards) {
            this.add(cp);
        }
        revalidate();
        repaint();
    }

}
