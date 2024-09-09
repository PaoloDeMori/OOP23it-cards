package it.unibo.itcards.view.briscola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.panels.LateralPanel;

public class LeftPanelBriscola extends LateralPanel {

    public LeftPanelBriscola() {
        super();
    }
    @Override
    public void setCenter(JPanel panel) {
        this.add(panel, BorderLayout.CENTER);
    }
    @Override
    /**
     * unimplemented method
     * @param botPoints
     * @param playerPoint
     * @throws UnsupportedOperationException
     */
    public void setPoints(final int botPoints, final int playerPoint) {
        throw new UnsupportedOperationException("can't set points here");
    }
    @Override
    /**
     * set the player and bot names
     * @param botName
     * @param playerNameColor green = new Color(0xFF3E8E41);
     myPanel.setBackground(green);
     */
    public void setNames(String botName, String playerName) {
        JPanel botJPanel = new JPanel();
        botJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        botJPanel.setBackground(BriscolaView.invisibleColor);
        JLabel bot = new JLabel();
        bot.setFont(new Font("Arial", Font.BOLD, 40));
        bot.setBackground(BriscolaView.invisibleColor);
        bot.setForeground(BriscolaView.opponentColor);
        bot.setOpaque(false);
        bot.setText(botName);
        botJPanel.add(bot);
        this.add(botJPanel, BorderLayout.NORTH);

        JPanel playerJPanel = new JPanel();
        playerJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        playerJPanel.setBackground(BriscolaView.invisibleColor);
        JLabel player = new JLabel();
        player.setFont(new Font("Arial", Font.BOLD, 40));
        player.setOpaque(false);
        player.setText(playerName);
        player.setForeground(new Color(255,217,46));
        player.setBackground(BriscolaView.invisibleColor);
        playerJPanel.add(player);
        this.add(playerJPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

}
