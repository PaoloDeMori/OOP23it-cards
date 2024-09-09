package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.*;


import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class CardButton extends JButton {
    String path;
    int height;
    int width;
    Image resizedImg;

    public CardButton(BufferedImage im, int height, String name) {
        this.height = height;
        if (im != null) {
            width = (int) (height * ImagesHelper.getImageRatio(im));
            resizedImg = ImagesHelper.resizeCardShapedImage(im, height);
            ImageIcon icon = new ImageIcon(resizedImg);
            this.setIcon(icon);
            this.setMaximumSize(new Dimension(width, height));
        } else {
            this.add(new JLabel(name));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
