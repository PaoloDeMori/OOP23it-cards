package it.unibo.itcards.view.briscola;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import it.unibo.itcards.commons.Card;
import it.unibo.itcards.controller.Controller;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.Dim;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.CardViewFactory;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;
import it.unibo.itcards.view.baseelements.panels.EndPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BriscolaView extends JFrame implements View {

    private final MainPanel mainpanel;
    private final CardViewFactory cardViewFactory;
    private final Controller controller;

    private String botName;
    private String playerName;

    private int botPoints;
    private int playerPoints;

    private boolean canPlayerPlay = true;

    public BriscolaView(final Dim d, final Controller controller) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(d.getDimension());
        this.setSize(d.getDimension());

        this.cardViewFactory = new CardViewFactory();
        this.mainpanel = MainPanelBriscolaFactory.build(d.getDimension());

        this.setMusic();
        this.add(mainpanel);
        this.controller = controller;
    }

    public void setHand(final List<Card> cards) {
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

    private List<CardButton> createCardButtons(final List<Card> cards) {
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

    public void setCardsOnTable(final List<Card> cards) {
        this.mainpanel.setCardsOnTable(cards);
    }

    public void updateCurrentPlayer() {
        this.mainpanel.updateCurrentPlayer(this.controller.getCurrentPlayer());
    }

    public void setNumberOpponentCards(final int setNumberOpponentCards) {
        this.mainpanel.setOpponentCards(setNumberOpponentCards);
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
        this.botPoints = points.get(1);
        this.playerPoints = points.get(0);

        mainpanel.setPoints(botPoints, playerPoints);
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
        String winner;
        if (botPoints > playerPoints) {
            winner = botName;
        } else if (botPoints < playerPoints) {
            winner = playerName;
        } else {
            winner = "pareggio";
        }
        EndPane endPane = new EndPane(controller, winner);
        endPane.showTwoCommandsDialog();
    }

    @Override
    public void start() {
        this.update();
        this.setVisible(true);
        this.setNames();
    }

    private void setNames() {

        List<String> names = this.controller.getPlayerNames();
        botName = names.get(1);
        playerName = names.get(0);
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
