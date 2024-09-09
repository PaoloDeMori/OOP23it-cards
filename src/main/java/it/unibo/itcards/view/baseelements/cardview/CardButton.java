package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Image;

public class CardButton extends JButton {
    private int height;
    private int width;
    private Image resizedImg;

    public CardButton(final BufferedImage im, final int height, final String name) {
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
