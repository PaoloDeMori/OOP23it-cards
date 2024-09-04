package it.unibo.itcards.view.baseelements.panels;

import java.util.List;

import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.cardview.CardFactory;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class CentralPanelImpl extends CentralPanel{

    BorderLayout layout;
    JPanel leftPanel;
    JPanel rightPanel;
    CardFactory factory;
    JPanel deck;
    JPanel briscola=null;

    public CentralPanelImpl(){
        layout = new BorderLayout();
        this.setBackground(new Color(0,0,0,0));
        this.leftPanel=new JPanel();
        this.rightPanel=new JPanel();
    }

    @Override
    public void setCardsOnTable(List<JPanel> cards) {
        leftPanel.removeAll();
        if(briscola==null){
            briscola = cards.get(0);
            this.rightPanel.add(briscola);
        }
        cards.remove(0);
        for (var jPanel : cards) {
            this.leftPanel.add(jPanel);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void setDeck(boolean hasCards) {
        if(!hasCards){
            rightPanel.remove(0);
        }
        else{
            if(deck==null){
            deck=StaticCardFactory.build("retro", this.rightPanel.getSize());
            this.rightPanel.add(deck);
            }
        }
    }

    @Override
    public void init(Dimension d) {
        this.setLayout(layout);
        this.setSize(d);
        this.setPreferredSize(d);
        this.leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.leftPanel.setBackground(new Color(0,0,0,0));
        this.rightPanel.setBackground(new Color(0,0,0,0));
        this.rightPanel.setPreferredSize(new Dimension((int)(d.getWidth()/5),(int)d.getHeight()));
        this.rightPanel.setLayout(new FlowLayout());
        this.add(rightPanel,BorderLayout.EAST);
        this.add(leftPanel,BorderLayout.CENTER);


        this.revalidate();
        this.repaint();
    }
    
}
