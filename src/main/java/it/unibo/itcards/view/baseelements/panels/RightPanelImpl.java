package it.unibo.itcards.view.baseelements.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.view.View;

/**
 * RightPanelImpl.
 */
public class RightPanelImpl extends LateralPanel {
    private static final long serialVersionUID = 3L;
    private final JLabel bot = new JLabel();
    private final JLabel player = new JLabel();

    /**
     * RightPanelImpl's constructor that initializes the panel.
     * it's the same of the LateralPanel
     */
    public RightPanelImpl() {
        super();
    }

    /**
     * set the points of the player in the right panel and the bot.
     */
    @Override
    public void setPoints(final int botPoints, final int playerPoints) {
        final JPanel botJPanel = new JPanel();
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
        final JPanel playerJPanel = new JPanel();
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
    }

    /**
     * Sets the given panel to be displayed in the center of the layout.
     * 
     * @param panel The JPanel to be added in the center of the layout.
     */
    @Override
    public void setCenter(final JPanel panel) {

    }

    /**
     * Unsupported method.
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
