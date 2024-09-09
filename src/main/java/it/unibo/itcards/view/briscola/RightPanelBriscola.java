package it.unibo.itcards.view.briscola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.view.baseelements.panels.LateralPanel;

public class RightPanelBriscola extends LateralPanel {
    JLabel bot = new JLabel();
    JLabel player = new JLabel();


    /**
     * RightPanelImpl
     */
    public RightPanelBriscola() {
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
        botJPanel.setBackground(BriscolaView.invisibleColor);
        bot.removeAll();
        bot.setText(Integer.toString(Botpoints));
        bot.setFont( new Font("Arial", Font.BOLD, 40) );
        bot.setForeground(new Color(255,0,0));
        bot.setBackground(BriscolaView.invisibleColor);
        bot.setOpaque(false);
        botJPanel.add(bot);
        this.add(botJPanel , BorderLayout.NORTH);
        JPanel playerJPanel = new JPanel();
        playerJPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        playerJPanel.setBackground(BriscolaView.invisibleColor);
        player.removeAll();
        player.setText(Integer.toString(Playerpoints));
        player.setFont( new Font("Arial", Font.BOLD, 40) );
        player.setBackground(BriscolaView.invisibleColor);
        player.setForeground(new Color(255,217,46));
        player.setOpaque(false);
        playerJPanel.add(player);
        this.add(playerJPanel , BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
        return;
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
