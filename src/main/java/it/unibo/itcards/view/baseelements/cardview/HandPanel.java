package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.JPanel;
import it.unibo.itcards.model.baseelements.cards.Card;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.util.List;

public class HandPanel extends JPanel {
    List<CardPanel> cards;

    public HandPanel(Dimension d) {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,10,5);
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
    }

    public void setCards(List<CardPanel> cards) {
        this.cards = cards;
        for(CardPanel cp : cards){
            this.add(cp);
        }
        revalidate();
        repaint();
    }

}
