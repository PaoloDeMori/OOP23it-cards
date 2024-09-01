package it.unibo.itcards.view.baseelements;

import javax.swing.*;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.controller.Controller;
import it.unibo.itcards.controller.ControllerImpl;
import it.unibo.itcards.model.BriscolaImpl;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.briscola.EasyBriscolaAIPlayer;
import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.CardButtonFactory;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;
import it.unibo.itcards.view.baseelements.panels.HandPanelImpl;
import it.unibo.itcards.view.baseelements.panels.LeftPanelImpl;
import it.unibo.itcards.view.baseelements.panels.OpponentPanelImpl;
import it.unibo.itcards.view.baseelements.panels.RightPanelImpl;

import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame implements View {

    private MainPanel mainpanel;
    private CardButtonFactory cardButtonFactory;
    private Controller controller;

    public MainFrame(Dim d, Controller controller) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());
        this.cardButtonFactory = new CardButtonFactory();
        this.mainpanel = new MainPanelBuilder(d.getDimension())
                .addHandPanel(new HandPanelImpl())
                .addopponentPanel(new OpponentPanelImpl())
                .addLeftPanel(new LeftPanelImpl())
                .addRightPanel(new RightPanelImpl())
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
                controller.playturn(card);
                System.out.println(card.toString());
            };
            cp = this.cardButtonFactory.build(card, al, this.mainpanel.getHandPanelDimension());
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

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            this.setHand(this.controller.getHand());
            this.setOpponentCards(this.controller.getOpponentHand());
            this.mainpanel.revalidate();
            this.mainpanel.repaint();
        });
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

    private void setOpponentCards(int n) {
        this.mainpanel.setOpponentCards(n);
    }

    public void setNames() {
        String botName = this.controller.getPlayers().stream().filter((player) -> player instanceof AIPlayer)
                .map(Player::toString)
                .findFirst()
                .orElse("name not found");
        String playerName = this.controller.getPlayers().stream().filter((player) -> !(player instanceof AIPlayer))
        .map(Player::toString)
        .findFirst()
        .orElse("name not found");

        this.mainpanel.setNames(botName, playerName);
    }

    public void setPoints(final int botPoints, final int playerPoint) {
        this.mainpanel.setPoints(botPoints, playerPoint);
    }

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        MainFrame mainframe = new MainFrame(Dim.LARGE, controller);
        BriscolaImpl briscola = new BriscolaImpl(new PlayerImpl("gino", 3), new EasyBriscolaAIPlayer("bot", 3));
        controller.init(briscola, mainframe);
        briscola.start();
        mainframe.start();
    }
}