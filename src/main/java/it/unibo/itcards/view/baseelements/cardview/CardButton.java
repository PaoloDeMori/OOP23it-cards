package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 * this class represent a card like a button.
 */
public class CardButton extends JButton {
    private final int height;
    private int width;
    private Image resizedImg;

    /**
     * Constructor of the class.
     * set the size of the image and the name of the card
     * @param im the image of the card
     * @param height the height of the card
     * @param name the name of the card
     */
    public CardButton(final BufferedImage im, final int height, final String name) {
        this.height = height;
        if (im != null) {
            width = (int) (height * ImagesHelper.getImageRatio(im));
            resizedImg = ImagesHelper.resizeCardShapedImage(im, height);
            final ImageIcon icon = new ImageIcon(resizedImg);
            this.setIcon(icon);
            this.setMaximumSize(new Dimension(width, height));
        } else {
            this.add(new JLabel(name));
        }
    }

    /**
     * Returns the preferred size of the component.
     *
     * @return The preferred size of the component as a Dimension object.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
