package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.commons.Card;

public final class StaticCardFactory {

    private StaticCardFactory() {
    }

    public static JPanel build(Card card,Dimension dimension){
        BufferedImage image=null;
        Dimension d=null;
        try {
            image =ImagesHelper.loadImage(card.toString());
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            image = null;
        }
        if (image != null) {
            int height = (int) dimension.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            d = new Dimension(width, height);
            return new CardPanel(image, (int)d.getHeight(),card.toString());
        }
        JPanel p = new JPanel();
        p.add(new JLabel("Opponent Card"));
        return p;
    }

    public static JPanel build(String name,Dimension dimension){
        BufferedImage image=null;
        Dimension d=null;
        try {
            image =ImagesHelper.loadImage(name);
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            image = null;
        }
        if (image != null) {
            int height = (int) dimension.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            d = new Dimension(width, height);
            return new CardPanel(image, (int)d.getHeight(),name);
        }
        JPanel p = new JPanel();
        p.add(new JLabel("Opponent Card"));
        return p;

    }
}
