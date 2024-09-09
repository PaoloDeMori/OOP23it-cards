package it.unibo.itcards.view.baseelements;


import javax.swing.*;
import it.unibo.itcards.commons.Card;
import it.unibo.itcards.controller.Controller;


import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.CardViewFactory;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;
import it.unibo.itcards.view.baseelements.panels.CentralPanelImpl;
import it.unibo.itcards.view.baseelements.panels.HandPanelImpl;
import it.unibo.itcards.view.baseelements.panels.LeftPanelImpl;
import it.unibo.itcards.view.baseelements.panels.OpponentPanelImpl;
import it.unibo.itcards.view.baseelements.panels.RightPanelImpl;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BriscolaView extends JFrame implements View {

    private final MainPanel mainpanel;
    private final CardViewFactory cardViewFactory;
    private final Controller controller;

    public static final Color invisibleColor = new Color(0, 0, 0, 0);
    public static final Color opponentColor = new Color(255, 0, 0);
    public static final Color playerColor = new Color(255, 217, 46);

    private boolean canPlayerPlay = true;

    public BriscolaView(Dim d, Controller controller) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());

        this.cardViewFactory = new CardViewFactory();
        this.mainpanel = new MainPanelBuilder(d.getDimension())
                .addHandPanel(new HandPanelImpl())
                .addOpponentPanel(new OpponentPanelImpl())
                .addLeftPanel(new LeftPanelImpl())
                .addRightPanel(new RightPanelImpl())
                .addCentralPanel(new CentralPanelImpl())
                .build();

        this.setMusic();
        this.add(mainpanel);
        this.controller = controller;
    }

    @Override
    public void setHand(List<Card> cards) {
        List<CardButton> panels = createCardButtons(cards);
        mainpanel.setHand(panels);
    }

    public void setMusic() {
        JButton button = new JButton("Start/Stop music");
        button.addActionListener(e -> {
            this.controller.startAudio();
        });
        this.mainpanel.setMusicButtons(button);
    }

    private List<CardButton> createCardButtons(List<Card> cards) {
        List<CardButton> buttons = new ArrayList<>();
        for (Card card : cards) {
            ActionListener al = e -> {
                controller.playturn(card);
                System.out.println("Card played: " + card);
            };
            CardButton button = this.cardViewFactory.buildButton(card, al, this.mainpanel.getHandPanelDimension());
            if (!canPlayerPlay) {
                button.setEnabled(false);
                button.setOpaque(false);
            }

            buttons.add(button);
        }
        return buttons;
    }

    @Override
    public void setCardsOnTable(List<Card> cards) {
        this.mainpanel.setCardsOnTable(cards);
    }

    public void updateCurrentPlayer() {
        this.mainpanel.updateCurrentPlayer(this.controller.getCurrentPlayer());
    }

    @Override
    public void setNumberOpponentCards(int n) {
        this.mainpanel.setOpponentCards(n);
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            updateHand();
            updateCurrentPlayer();
            updateOpponentCards();
            updateCardsOnTable();
            updatePoints();
            updateDeckStatus();
            refreshUI();
        });
    }

    private void updateHand() {
        List<Card> hand = controller.getHand();
        if (hand != null) {
            setHand(hand);
        }
        this.refreshUI();
    }

    private void updateOpponentCards() {
        int opponentCardCount = controller.getOpponentHand();
        setNumberOpponentCards(opponentCardCount);
    }

    private void updateCardsOnTable() {
        List<Card> cardsOnTable = controller.getCardsOnTable();
        if (cardsOnTable != null) {
            setCardsOnTable(cardsOnTable);
        }
    }

    private void updatePoints() {
        List<Integer> points = this.controller.getPlayerPoints();

            mainpanel.setPoints(points.get(1), points.get(0));
    }

    private void updateDeckStatus() {
        int deckNumberOfCards = controller.deckNumberOfCards();
        mainpanel.setDeck(deckNumberOfCards);
    }

    private void refreshUI() {
        this.mainpanel.revalidate();
        this.mainpanel.repaint();
    }

    @Override
    public void stop() {
        System.out.println("Hai vinto");
    }

    @Override
    public void start() {
        this.update();
        this.setVisible(true);
        this.setNames();
    }

    private void setNames() {

        List<String> names = this.controller.getPlayerNames();
        String botName = names.get(1);
        String playerName = names.get(0);
        this.mainpanel.setNames(botName, playerName);
    }

    @Override
    public void aiCanPlay() {
        this.canPlayerPlay = false;
    }

    @Override
    public void playerCanPlay() {
        this.canPlayerPlay = true;
    }
}