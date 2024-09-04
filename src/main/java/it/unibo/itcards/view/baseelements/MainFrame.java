package it.unibo.itcards.view.baseelements;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import it.unibo.itcards.commons.Card;
import it.unibo.itcards.controller.Controller;
import it.unibo.itcards.controller.ControllerImpl;
import it.unibo.itcards.model.Audio.Audio;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.briscola.BriscolaImpl;
import it.unibo.itcards.model.briscola.DifficultBriscolaAIPlayer;
import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.CardButtonFactory;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanelBuilder;
import it.unibo.itcards.view.baseelements.panels.CentralPanelImpl;
import it.unibo.itcards.view.baseelements.panels.HandPanelImpl;
import it.unibo.itcards.view.baseelements.panels.LeftPanelImpl;
import it.unibo.itcards.view.baseelements.panels.OpponentPanelImpl;
import it.unibo.itcards.view.baseelements.panels.RightPanelImpl;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainFrame extends JFrame implements View {

    private final MainPanel mainpanel;
    private final CardButtonFactory cardButtonFactory;
    private final Controller controller;

    public MainFrame(Dim d, Controller controller) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());

        this.cardButtonFactory = new CardButtonFactory();
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

    public void setMusic(){
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
            CardButton button = this.cardButtonFactory.build(card, al, this.mainpanel.getHandPanelDimension());
            buttons.add(button);
        }
        return buttons;
    }

    @Override
    public void setCardsOnTable(List<Card> cards) {
        this.mainpanel.setCardsOnTable(cards);
    }

    @Override
    public void setNumberOpponentCards(int n) {
        this.mainpanel.setOpponentCards(n);
    }

    @Override
    public boolean isDeckEnded() {
        return this.controller.isDeckEmpty();
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
                updateHand();
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
        Optional<Player> bot = controller.getPlayers().stream().filter(Player::isAi).findFirst();
        Optional<Player> player = controller.getPlayers().stream().filter(p -> !p.isAi()).findFirst();

        if (bot.isPresent() && player.isPresent()) {
            int botPoints = bot.get().getPoints();
            int playerPoints = player.get().getPoints();
            mainpanel.setPoints(botPoints, playerPoints);
        }
    }

    private void updateDeckStatus() {
        boolean isDeckEmpty = controller.isDeckEmpty();
        mainpanel.setDeck(!isDeckEmpty);
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
        String botName = this.controller.getPlayers().stream()
                .filter(Player::isAi)
                .map(Player::toString)
                .findFirst()
                .orElse("name not found");

        String playerName = this.controller.getPlayers().stream()
                .filter(player -> !player.isAi())
                .map(Player::toString)
                .findFirst()
                .orElse("name not found");

        this.mainpanel.setNames(botName, playerName);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Controller controller = new ControllerImpl();
        MainFrame mainframe = new MainFrame(Dim.MEDIUM, controller);
        BriscolaImpl briscola = new BriscolaImpl(new PlayerImpl("gino", 3), new DifficultBriscolaAIPlayer("bot", 3));
        controller.init(briscola, mainframe);
        briscola.start();
        mainframe.start();
    }
}