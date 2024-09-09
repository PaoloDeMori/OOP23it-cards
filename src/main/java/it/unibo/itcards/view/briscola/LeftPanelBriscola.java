package it.unibo.itcards.view.briscola;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;

public class LeftPanelBriscola extends LateralPanel {

    public LeftPanelBriscola() {
        super();
    }

    @Override
    public void setCenter(final JPanel panel) {
        this.add(panel, BorderLayout.CENTER);
    }

    @Override
    /**
     * unimplemented method
     * 
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
     * 
     * @param botName the name of the opponent bot
     * @param playerName the name of the player
     */
    public void setNames(final String botName, final String playerName) {
        JPanel botJPanel = new JPanel();
        botJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, View.STANDARD_HGAP, View.STANDARD_VGAP));
        botJPanel.setBackground(View.INVISIBLE_COLOR);
        JLabel bot = new JLabel();
        bot.setFont(View.STANDARD_FONT);
        bot.setBackground(View.INVISIBLE_COLOR);
        bot.setForeground(View.OPPONENT_COLOR);
        bot.setOpaque(false);
        bot.setText(botName);
        botJPanel.add(bot);
        this.add(botJPanel, BorderLayout.NORTH);

        JPanel playerJPanel = new JPanel();
        playerJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, View.STANDARD_HGAP, View.STANDARD_VGAP));
        playerJPanel.setBackground(View.INVISIBLE_COLOR);
        JLabel player = new JLabel();
        player.setFont(View.STANDARD_FONT);
        player.setOpaque(false);
        player.setText(playerName);
        player.setForeground(View.PLAYER_COLOR);
        player.setBackground(View.INVISIBLE_COLOR);
        playerJPanel.add(player);
        this.add(playerJPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

}
