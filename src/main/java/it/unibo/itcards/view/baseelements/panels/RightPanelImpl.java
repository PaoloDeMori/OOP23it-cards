package it.unibo.itcards.view.baseelements.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanelImpl extends LateralPanel {

    /**
     * RightPanelImpl
     */
    public RightPanelImpl() {
        super();
    }

    @Override
    /**
     * set the points of the player in the right panel and the bot
     * @param Botpoints 
     * @param Playerpoints
     */
    public void setPoints( final int Botpoints , final int Playerpoints) {
        JPanel botJPanel = new JPanel();
        botJPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        botJPanel.setBackground(new Color(0,0,0,0));
        JLabel bot = new JLabel();
        bot.setText(Integer.toString(Botpoints));
        bot.setFont( new Font("Arial", Font.BOLD, 40) );
        bot.setBackground(new Color(0,0,0,0));
        bot.setOpaque(false);
        botJPanel.add(bot);
        this.add(botJPanel , BorderLayout.NORTH);
        JPanel playerJPanel = new JPanel();
        playerJPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        playerJPanel.setBackground(new Color(0,0,0,0));
        JLabel player = new JLabel();
        player.setText(Integer.toString(Playerpoints));
        player.setFont( new Font("Arial", Font.BOLD, 40) );
        player.setBackground(new Color(0,0,0,0));
        player.setOpaque(false);
        playerJPanel.add(player);
        this.add(playerJPanel , BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void setCenter(JPanel panel) {
        
    }

    /**
     * unimplemented methods
     * @param botName
     * @param playerName
     * @throws UnsupportedOperationException
     */
    @Override
    public void setNames(final String botName, final String playerName) {
        throw new UnsupportedOperationException(" you can't set names here");
    }

}
