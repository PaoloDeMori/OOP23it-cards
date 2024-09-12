package it.unibo.itcards.view.baseelements.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.view.View;

/**
 * A panel that represents the left side of the Briscola game view. 
 * It displays the player and bot names, and provides a placeholder for future functionalities.
 * This class extends the LateralPanel class and customizes it for the Briscola game.
 */
public class LeftPanelImpl extends LateralPanel {

    private static final long serialVersionUID = 3L;
    /**
     * Constructor that initializes the panel with default settings inherited from LateralPanel.
     */
    public LeftPanelImpl() {
        super();
    }

    /**
     * Sets the given panel to be displayed in the center of the layout. This method 
     * places the provided panel in the center region of the BorderLayout.
     * 
     * @param panel The JPanel to be added in the center of the layout.
     */
    @Override
    public void setCenter(final JPanel panel) {
        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * An unimplemented method meant to set the points for the bot and player. 
     * This method currently throws an  UnsupportedOperationExceptio as setting points is not supported in this panel.
     * 
     * @param botPoints The points of the bot.
     * @param playerPoint The points of the player.
     * @throws UnsupportedOperationException Always thrown as setting points is not allowed.
     */
    @Override
    public void setPoints(final int botPoints, final int playerPoint) {
        throw new UnsupportedOperationException("can't set points here");
    }

    /**
     * Sets the names of the player and the bot, displaying them in the panel.
     * The bot's name is displayed in the north region, and the player's name in the south region.
     * 
     * @param botName The name of the bot (opponent).
     * @param playerName The name of the player.
     */
    @Override
    public void setNames(final String botName, final String playerName) {
        // Bot name panel
        final JPanel botJPanel = new JPanel();
        botJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, View.STANDARD_HGAP, View.STANDARD_VGAP));
        botJPanel.setBackground(View.INVISIBLE_COLOR);
        final JLabel bot = new JLabel();
        bot.setFont(View.STANDARD_FONT);
        bot.setBackground(View.INVISIBLE_COLOR);
        bot.setForeground(View.OPPONENT_COLOR);
        bot.setOpaque(false);
        bot.setText(botName);
        botJPanel.add(bot);
        this.add(botJPanel, BorderLayout.NORTH);

        // Player name panel
        final JPanel playerJPanel = new JPanel();
        playerJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, View.STANDARD_HGAP, View.STANDARD_VGAP));
        playerJPanel.setBackground(View.INVISIBLE_COLOR);
        final JLabel player = new JLabel();
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
