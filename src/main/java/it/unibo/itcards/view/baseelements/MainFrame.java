package it.unibo.itcards.view.baseelements;

import javax.swing.*;

import it.unibo.itcards.controller.Controller;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.HandPanel;
import it.unibo.itcards.view.baseelements.cardview.HandPanelImpl;
import it.unibo.itcards.view.baseelements.cardview.ImagesHelper;
import it.unibo.itcards.view.baseelements.cardview.OpponentPanel;
import it.unibo.itcards.view.baseelements.cardview.OpponentPanelImpl;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame implements View {

    private MainPanel mainpanel;
    public Map<Card, BufferedImage> imagesCache;
    private Controller controller;

    public MainFrame(Dim d, Controller controller) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());
        this.imagesCache = new HashMap<>();
        this.mainpanel = new MainPanelBuilder(d.getDimension())
                         .addHandPanel(new HandPanelImpl())
                         .addopponentPanel(new OpponentPanelImpl())
                         .build();
        this.add(mainpanel);
        this.controller = controller;
    }

    @Override
    public void setHand(List<Card> cards) {
        CardButton cp;

        List<CardButton> panels = new ArrayList<>();
        for (Card card : cards) {
            ActionListener al = e -> {
                // controller.playturn(card);
                System.out.println(card.toString());
            };
            cp = this.createCardButton(card, al);
            panels.add(cp);
        }
        mainpanel.setHand(panels);
    }

    private void setOpponentCards(int n){
        this.mainpanel.setOpponentCards(n);
    }

    private CardButton createCardButton(Card card, ActionListener al) {
        BufferedImage image;
        CardButton cp;
        if (!imagesCache.keySet().contains(card)) {
            try {
                image = ImagesHelper.loadImage(card);
                imagesCache.put(card, image);
            } catch (IOException e) {
                image = null;
            }
            cp = new CardButton(image, (int) mainpanel.getHandPanelDimension().getHeight());
        } else {
            cp = new CardButton(imagesCache.get(card), (int) mainpanel.getHandPanelDimension().getHeight());
        }
        if (al != null) {
            cp.addActionListener(al);
        }
        return cp;
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

    public static void main(String[] args) {
        Deck deck = ShuffledDeckFactoryImpl.buildDeck();
        MainFrame mainframe = new MainFrame(Dim.LARGE, new Controller());
        mainframe.setNumberOpponentCards(3);
        mainframe.setVisible(true);
        List<Card> c = new ArrayList<Card>();
        c.add(deck.drawCard().get());
        c.add(deck.drawCard().get());
        Card l = deck.drawCard().get();
        c.add(l);
        mainframe.setHand(c);
        c.removeAll(c);
        mainframe.setHand(c);
        c.add(l);
        System.out.println(Integer.toString(mainframe.imagesCache.size()));
        mainframe.setHand(c);
    }

    @Override
    public void update() {
        this.setHand(this.controller.getHand());
        this.setOpponentCards(this.controller.getOpponentHand());
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }
}
