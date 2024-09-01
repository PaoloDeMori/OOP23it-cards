package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.itcards.model.baseelements.cards.Card;

public class CardButtonFactory {

    public Map<Card, BufferedImage> imagesCache;

    
    
    public CardButtonFactory() {
        this.imagesCache = new HashMap<>();
    }

    public CardButton build (Card card, ActionListener al,Dimension d) {
        BufferedImage image;
        CardButton cp;
        if (!imagesCache.keySet().contains(card)) {
            try {
                image = ImagesHelper.loadImage(card);
                imagesCache.put(card, image);
            } catch (IOException e) {
                image = null;
            }
            cp = new CardButton(image, (int)d.getHeight());
        } else {
            cp = new CardButton(imagesCache.get(card), (int)d.getHeight());
        }
        if (al != null) {
            cp.addActionListener(al);
        }
        return cp;
    }
    
}
