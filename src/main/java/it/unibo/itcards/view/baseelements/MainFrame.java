package it.unibo.itcards.view.baseelements;

import javax.swing.*;

import it.unibo.itcards.controller.Controller;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardPanel;
import it.unibo.itcards.view.baseelements.cardview.MainPanel;

import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame implements View{

    private MainPanel mainpanel;
    private Controller controller;

    public MainFrame(Dim d) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());
        this.mainpanel = new MainPanel(d.getDimension());
        this.add(mainpanel);
        this.controller=new Controller();
    }

    @Override
    public void setHand(List<Card> cards) {
        List<CardPanel> panels = new ArrayList<>();
        CardPanel cp; 
        for(Card card:cards){
            cp =new CardPanel(card, (int) mainpanel.getHandPanelDimension().getHeight());
            ActionListener al = e ->{
                //controller.playturn(card);
                System.out.println(card.toString());
            };
            cp.addActionListener(al) ;
            panels.add(cp);
        }
        mainpanel.setHand(panels);
    }

    @Override
    public void setCardsOnTable(List<Card> cards) {
        
    }

    @Override
    public void setNumberOpponentCards(int n) {
        this.mainpanel.setOpponentCards(n);
        
    }

    @Override
    public boolean isDeckEnded() {
        return true;
    }

    public static void main(String[] args){
        Deck deck = ShuffledDeckFactoryImpl.buildDeck();
        MainFrame mainframe = new MainFrame(Dim.MEDIUM);
        mainframe.setVisible(true);
        List<Card> c = new ArrayList<Card>();
        c.add(deck.drawCard().get());
        c.add(deck.drawCard().get());
        c.add(deck.drawCard().get());
        mainframe.setHand(c);
        mainframe.setNumberOpponentCards(3);
    }
}
