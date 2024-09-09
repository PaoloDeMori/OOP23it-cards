package it.unibo.itcards.view.briscola;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.view.View;
import it.unibo.itcards.view.baseelements.panels.LateralPanel;

public class RightPanelBriscola extends LateralPanel {
    private JLabel bot = new JLabel();
    private JLabel player = new JLabel();

    /**
     * RightPanelImpl.
     */
    public RightPanelBriscola() {
        super();
    }

    @Override
    /**
     * set the points of the player in the right panel and the bot.
     * 
     * @param Botpoints
     * @param Playerpoints
     */
    public void setPoints(final int botPoints, final int playerPoints) {
        JPanel botJPanel = new JPanel();
        botJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, View.STANDARD_HGAP, View.STANDARD_VGAP));
        botJPanel.setBackground(View.INVISIBLE_COLOR);
        bot.removeAll();
        bot.setText(Integer.toString(botPoints));
        bot.setFont(View.STANDARD_FONT);
        bot.setForeground(View.OPPONENT_COLOR);
        bot.setBackground(View.INVISIBLE_COLOR);
        bot.setOpaque(false);
        botJPanel.add(bot);
        this.add(botJPanel, BorderLayout.NORTH);
        JPanel playerJPanel = new JPanel();
        playerJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, View.STANDARD_HGAP, View.STANDARD_VGAP));
        playerJPanel.setBackground(View.INVISIBLE_COLOR);
        player.removeAll();
        player.setText(Integer.toString(playerPoints));
        player.setFont(View.STANDARD_FONT);
        player.setBackground(View.INVISIBLE_COLOR);
        player.setForeground(View.PLAYER_COLOR);
        player.setOpaque(false);
        playerJPanel.add(player);
        this.add(playerJPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
        return;
    }

    @Override
    public void setCenter(final JPanel panel) {

    }

    /**
     * unimplemented methods.
     * 
     * @param botName
     * @param playerName
     * @throws UnsupportedOperationException
     */
    @Override
    public void setNames(final String botName, final String playerName) {
        throw new UnsupportedOperationException(" you can't set names here");
    }

}
