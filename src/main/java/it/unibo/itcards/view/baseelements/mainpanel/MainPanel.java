package it.unibo.itcards.view.baseelements.mainpanel;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.cardview.CardButton;
import it.unibo.itcards.view.baseelements.cardview.ImagesHelper;
import it.unibo.itcards.view.baseelements.cardview.StaticCardFactory;
import it.unibo.itcards.view.baseelements.panels.CentralPanel;
import it.unibo.itcards.view.baseelements.panels.HandPanel;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;
import it.unibo.itcards.view.baseelements.panels.OpponentPanel;
import it.unibo.itcards.view.briscola.BriscolaView;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class MainPanel extends JPanel {
    private final Dimension handPanelDimension;
    private final Dimension lateralPanelDimension;
    private final Dimension upPanelDimension;
    private final Dimension centralPanelDImension;

    private HandPanel handPanel;
    private OpponentPanel opponentPanel;
    private CentralPanel centralPanel;
    private LateralPanel rightPanel;
    private LateralPanel leftPanel;
    private static final int HAND_PANEL_RATIO = 4;
    private static final int OPPONENT_PANEL_RATIO = 6;
    private static final int LATERAL_PANEL_RATIO = 13;
    private static final int MUSIC_BUTTON_VGAP_RATIO = 6;
    private static final int CARDS_ON_TABLE_RATIO = 5;
    private BufferedImage backgroundImage;

    /**
     * initialize the Main panel.
     * 
     * @param d
     */
    protected MainPanel(final Dimension d) {
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.handPanelDimension = new Dimension(this.getWidth(), this.getHeight() / HAND_PANEL_RATIO);
        this.lateralPanelDimension = new Dimension(this.getWidth() / LATERAL_PANEL_RATIO, this.getHeight());
        this.upPanelDimension = new Dimension(this.getWidth(), this.getHeight() / OPPONENT_PANEL_RATIO);
        this.centralPanelDImension = new Dimension((int) (this.getWidth() - (lateralPanelDimension.getWidth() * 2)),
                (int) (this.getHeight() - (this.handPanelDimension.getHeight() + this.upPanelDimension.getHeight())));
        try {
            backgroundImage = ImagesHelper.loadImage("green");
        } catch (IOException e) {
        }
    }

    /**
     * set the hand panel.
     * 
     * @param handPanel
     */
    public void setHandPanel(final HandPanel handPanel) {
        this.handPanel = handPanel;
        this.handPanel.init(handPanelDimension);
        this.add(handPanel, BorderLayout.SOUTH);
    }

    public void setOpponentPanel(final OpponentPanel opponentPanel) {
        this.opponentPanel = opponentPanel;
        this.opponentPanel.init(upPanelDimension);
        this.add(opponentPanel, BorderLayout.NORTH);
    }

    public void setCentralPanel(final CentralPanel centralPanel) {
        this.centralPanel = centralPanel;
        this.centralPanel.init(centralPanelDImension);
        this.add(centralPanel, BorderLayout.CENTER);
    }

    public void setLateralPanel(final LateralPanel lateralPanel, final String borderLayoutPosition) {
        if (borderLayoutPosition.equals(BorderLayout.WEST)) {
            this.leftPanel = lateralPanel;
            this.leftPanel.init(lateralPanelDimension);
            this.add(leftPanel, BorderLayout.WEST);
        } else if (borderLayoutPosition.equals(BorderLayout.EAST)) {
            this.rightPanel = lateralPanel;
            this.rightPanel.init(lateralPanelDimension);
            this.add(rightPanel, BorderLayout.EAST);
        } else {
            throw new IllegalArgumentException("The position must be 'left' or 'right'");
        }
    }

    public void setMusicButtons(final JButton jbutton) {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,
                (int) this.lateralPanelDimension.getHeight() / MUSIC_BUTTON_VGAP_RATIO));
        jpanel.setBackground(View.INVISIBLE_COLOR);
        jpanel.add(jbutton);
        this.leftPanel.setCenter(jpanel);
    }

    public Dimension getHandPanelDimension() {
        return this.handPanelDimension;
    }

    public void setHand(final List<CardButton> cards) {
        this.handPanel.setCards(cards);
    }

    public void setOpponentCards(final int numberOfOpponentCards) {
        this.opponentPanel.setOpponentCards(numberOfOpponentCards);
    }

    public void setNames(final String botName, final String playerName) {
        this.leftPanel.setNames(botName, playerName);
    }

    public void setPoints(final int botPoints, final int playerPoint) {
        this.rightPanel.setPoints(botPoints, playerPoint);
    }

    public void setCardsOnTable(final List<Card> cards) {

        List<JPanel> panels = new ArrayList<>();
        panels.add(
                StaticCardFactory.build(cards.get(0),
                        new Dimension((int) (this.centralPanelDImension.getWidth() / CARDS_ON_TABLE_RATIO),
                                (int) (this.centralPanelDImension.getHeight() / CARDS_ON_TABLE_RATIO))));
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i) != null) {
                panels.add(StaticCardFactory.build(cards.get(i),
                        new Dimension((int) (this.centralPanelDImension.getWidth() / CARDS_ON_TABLE_RATIO),
                                (int) (this.centralPanelDImension.getHeight() / CARDS_ON_TABLE_RATIO))));
            }
        }
        this.centralPanel.setCardsOnTable(panels);
    }

    public void setDeck(final int numberOfCards) {
        this.centralPanel.setDeck(numberOfCards);
    }

    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public Dimension getCentralPanelDImension() {
        return centralPanelDImension;
    }

    public void updateCurrentPlayer(final Player player) {
        this.centralPanel.updateCurrentPlayer(player);
    }

}
