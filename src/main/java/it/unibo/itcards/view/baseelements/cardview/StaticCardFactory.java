package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.commons.Card;

/**
 * this class allow to create a card view.
 */
public final class StaticCardFactory {

    /**
     * private constructor.
     */
    private StaticCardFactory() {
    }

    /**
     * Builds a JPanel representing a card view.
     *
     * @param card      the card to be represented
     * @param dimension the desired dimension of the card view
     * @return a JPanel representing the card view
     */
    public static JPanel build(final Card card, final Dimension dimension) {
        BufferedImage image = null;
        Dimension d = null;
        try {
            image = ImagesHelper.loadImage(card.toString());
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            image = null;
        }
        if (image != null) {
            int height = (int) dimension.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            d = new Dimension(width, height);
            return new CardPanel(image, (int) d.getHeight(), card.toString());
        }
        JPanel p = new JPanel();
        p.add(new JLabel("Opponent Card"));
        return p;
    }

    /**
     * Builds a JPanel representing a card with the specified name and dimension.
     *
     * @param name      the name of the card
     * @param dimension the desired dimension of the card
     * @return a JPanel representing the card
     */
    public static JPanel build(final String name, final Dimension dimension) {
        BufferedImage image = null;
        Dimension d = null;
        try {
            image = ImagesHelper.loadImage(name);
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            image = null;
        }
        if (image != null) {
            int height = (int) dimension.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            d = new Dimension(width, height);
            return new CardPanel(image, (int) d.getHeight(), name);
        }
        JPanel p = new JPanel();
        p.add(new JLabel("Opponent Card"));
        return p;

    }
}
