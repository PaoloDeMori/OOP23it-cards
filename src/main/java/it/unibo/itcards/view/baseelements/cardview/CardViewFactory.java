package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

import it.unibo.itcards.commons.Card;

public class CardViewFactory {

    public Map<Card, BufferedImage> imagesCache;

    
    
    public CardViewFactory() {
        this.imagesCache = new HashMap<>();
    }

    public CardButton buildButton (Card card, ActionListener al,Dimension d) {
        BufferedImage image;
        CardButton cp;
        if (!imagesCache.keySet().contains(card)) {
            try {
                image = ImagesHelper.loadImage(card);
                imagesCache.put(card, image);
            } catch (IOException e) {
                image = null;
            }
            cp = new CardButton(image, (int)d.getHeight(),card.toString());
        } else {
            cp = new CardButton(imagesCache.get(card), (int)d.getHeight(),card.toString());
        }
        if (al != null) {
            cp.addActionListener(al);
        }
        return cp;
    }

    public JPanel buildPanel (Card card, Dimension d) {
        BufferedImage image;
        JPanel cp;
        if (!imagesCache.keySet().contains(card)) {
            try {
                image = ImagesHelper.loadImage(card);
                imagesCache.put(card, image);
            } catch (IOException e) {
                image = null;
            }
            cp = new CardPanel(image, (int)d.getHeight(),card.toString());
        } else {
            cp = new CardPanel(imagesCache.get(card), (int)d.getHeight(),card.toString());
        }
        return cp;
    }

}
