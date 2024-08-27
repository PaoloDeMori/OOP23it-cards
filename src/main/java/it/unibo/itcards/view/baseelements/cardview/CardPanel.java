package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.*;
import it.unibo.itcards.model.baseelements.cards.Card;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class CardPanel extends JButton {
    private BufferedImage image;
    String path;
    Card card;
    int height;
    int width;
    Image resizedImg;

    public CardPanel(Card card, int height) {
        this.card = card;
        this.height = height;
        path = "/CardsImages/" + card.toString() + ".jpeg";
        try {
            image = ImageIO.read(getClass().getResource(path));
            width = (int) (height * (double) image.getWidth() / image.getHeight());
            resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImg);
            this.setIcon(icon);
            this.setMaximumSize(new Dimension(width, height));
        } catch (IOException e) {
            image = null;
            this.add(new JLabel(card.toString()));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
