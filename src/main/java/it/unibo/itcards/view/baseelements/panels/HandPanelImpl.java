package it.unibo.itcards.view.baseelements.panels;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.List;

import it.unibo.itcards.view.baseelements.BriscolaView;
import it.unibo.itcards.view.baseelements.cardview.CardButton;

public class HandPanelImpl extends HandPanel {
    FlowLayout layout;
    public HandPanelImpl() {
        layout = new FlowLayout(FlowLayout.CENTER,10,5);
        this.setBackground(BriscolaView.invisibleColor);
    }

    @Override
    public void init(Dimension d){
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }

    @Override
    public void setCards(List<CardButton> cards) {
        this.removeAll();
        this.cards = cards;
        for(CardButton cp : cards){
            this.add(cp);
        }
        revalidate();
        repaint();
    }

}
