package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;

/**
 * this class represent a card like a panel.
 */
public class CardPanel extends JPanel {
    private static final long serialVersionUID = 3L;
    private Image resizedImage;
    private int width;

    /**
     * constructor of the class.
     * resize the image if it's not null and set the height and the width of the
     * panel
     * 
     * @param image  the image of the card
     * @param height the height of the card
     * @param name   the name of the card
     */
    public CardPanel(final BufferedImage image, final int height, final String name) {
        if (image != null) {
            width = (int) (height * ImagesHelper.getImageRatio(image));
            resizedImage = ImagesHelper.resizeCardShapedImage(image, height);
            this.setPreferredSize(new Dimension(width, height));
        } else {
            this.add(new JLabel(name));
        }

    }

    /**
     * This method is responsible for painting the component.
     *
     * @param g the graphics context to use for painting
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (resizedImage != null) {
            g.drawImage(resizedImage, 0, 0, this);
        }
    }
}
