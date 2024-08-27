package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import it.unibo.itcards.model.baseelements.cards.Card;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;

import java.util.List;

public class HandPanel extends JPanel {
    List<Card> cards;

    public HandPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,10,5);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(1920,300));
        this.setSize(1920,300);
        this.addMouseListener(new MouseListener() {
            private boolean isUp;
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if(isUp){
                    HandPanel panel = (HandPanel)arg0.getSource();
                    panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()/2));
                    panel.revalidate();
                    isUp=false;
                    }
                else{
                    if(!isUp){
                        HandPanel panel = (HandPanel)arg0.getSource();
                        panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()*2));
                        panel.revalidate();
                        isUp=true;
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
            
        });
     }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        aggiungiCarte();
    }

    public void aggiungiCarte() {
        for (Card card : cards) {
            CardPanel panel = new CardPanel(card,this.getHeight());
            this.add(panel);
        }
        revalidate();
        repaint();
    }

}
