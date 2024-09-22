package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

import it.unibo.itcards.commons.Card;

/**
 * this class allow to create a card view.
 */
public class CardViewFactory {

    private final Map<Card, BufferedImage> imagesCache;

    /**
     * this method create a card view like an hashmap.
     */
    public CardViewFactory() {
        this.imagesCache = new HashMap<>();
    }

    /**
     * Builds a CardButton instance for the given Card, ActionListener and Dimension.
     *
     * @param card  the Card to be associated with the button
     * @param al    the ActionListener to be added to the button
     * @param d     the Dimension of the button
     * @return      the CardButton instance
     */
    public CardButton buildButton(final Card card, final ActionListener al, final Dimension d) {
        BufferedImage image;
        CardButton cp;
        if (!imagesCache.keySet().contains(card)) {
            try {
                image = ImagesHelper.loadImage(card);
                imagesCache.put(card, image);
            } catch (IOException e) {
                image = null;
            }
            cp = new CardButton(image, (int) d.getHeight(), card.toString());
        } else {
            cp = new CardButton(imagesCache.get(card), (int) d.getHeight(), card.toString());
        }
        if (al != null) {
            cp.addActionListener(al);
        }
        return cp;
    }

    /**
     * Builds a JPanel representing a card view.
     *
     * @param card  the card to be represented in the panel
     * @param d     the dimension of the panel
     * @return      a JPanel representing the card view
     */
    public JPanel buildPanel(final Card card, final Dimension d) {
        BufferedImage image;
        JPanel cp;
        if (!imagesCache.keySet().contains(card)) {
            try {
                image = ImagesHelper.loadImage(card);
                imagesCache.put(card, image);
            } catch (IOException e) {
                image = null;
            }
            cp = new CardPanel(image, (int) d.getHeight(), card.toString());
        } else {
            cp = new CardPanel(imagesCache.get(card), (int) d.getHeight(), card.toString());
        }
        return cp;
    }

}
