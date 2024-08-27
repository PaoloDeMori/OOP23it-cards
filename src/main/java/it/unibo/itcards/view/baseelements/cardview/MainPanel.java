package it.unibo.itcards.view.baseelements.cardview;
import javax.swing.*;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;

import java.awt.*;
import java.util.List;
import java.util.*;

public class MainPanel extends JPanel{
    List<Card> playerHand = new ArrayList<>();
    HandPanel panel = new HandPanel();
    public MainPanel(){
        this.setLayout(new BorderLayout());
        this.setSize(1920, 1080);
        JPanel op = new JPanel();
        op.setPreferredSize(new Dimension(1000,1000));
        op.setBackground(Color.GREEN);
        this.add(BorderLayout.CENTER,op);
        JPanel op1 = new JPanel();
        op1.setPreferredSize(new Dimension(100,1080));
        op1.setBackground(Color.BLUE);
        this.add(BorderLayout.EAST,op1);
        JPanel op2 = new JPanel();
        op2.setPreferredSize(new Dimension(100,1080));
        op2.setBackground(Color.RED);
        this.add(BorderLayout.WEST,op2);


        this.add(BorderLayout.SOUTH,panel);
    }

    public void setPlayerHand(List<Card> playerHand){
        panel.setCards(playerHand);
    }

    public static void main(String[] args) {
        MainPanel panel = new MainPanel();
        Deck deck = ShuffledDeckFactoryImpl.buildDeck();
        JFrame frame = new JFrame("Gioco di Carte");
        frame.setSize(new Dimension(1920, 1080));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < 3; i++) {
            Optional<Card> card = deck.drawCard();
            if (card.isPresent()) {
                panel.playerHand.add(card.get());
            }
        }
        panel.setPlayerHand(panel.playerHand);
        frame.add(panel);
        frame.setVisible(true);
    }

}
