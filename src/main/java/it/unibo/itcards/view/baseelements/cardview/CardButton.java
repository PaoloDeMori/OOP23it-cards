package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.*;
import it.unibo.itcards.model.baseelements.cards.Card;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class CardButton extends JButton {
    String path;
    Card card;
    int height;
    int width;
    Image resizedImg;

    public CardButton(BufferedImage im, int height) {
        this.height = height;
        if (im != null) {
            width = (int) (height * ImagesHelper.getImageRatio(im));
            resizedImg = ImagesHelper.resizeCardShapedImage(im, height);
            ImageIcon icon = new ImageIcon(resizedImg);
            this.setIcon(icon);
            this.setMaximumSize(new Dimension(width, height));
        } else {
            this.add(new JLabel(card.toString()));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
