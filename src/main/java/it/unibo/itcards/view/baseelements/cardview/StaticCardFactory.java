package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.itcards.commons.Card;

public final class StaticCardFactory {

    private StaticCardFactory() {
    }

    public static JPanel build(Card card,Dimension dimension){
        Image resizedImg=null;
        Dimension d=null;
        try {
            BufferedImage image =ImagesHelper.loadImage(card.toString());
            if (image == null) {
                throw new IOException("Image not found");
            }
            int height = (int) dimension.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            resizedImg = ImagesHelper.resizeCardShapedImage(image, height);
            d = new Dimension(width, height);
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            resizedImg = null;
        } catch (Exception e) {
            System.err.println("Uknown error" + e.getMessage());
            resizedImg = null;
        }
        if (resizedImg != null) {
            return new CardPanel(resizedImg, d);
        }
        JPanel p = new JPanel();
        p.add(new JLabel("Opponent Card"));
        return p;
    }

    public static JPanel build(String name,Dimension dimension){
        Image resizedImg=null;
        Dimension d=null;
        try {
            BufferedImage image =ImagesHelper.loadImage(name);
            if (image == null) {
                throw new IOException("Image not found");
            }
            int height = (int) dimension.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            resizedImg = ImagesHelper.resizeCardShapedImage(image, height);
            d = new Dimension(width, height);
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            resizedImg = null;
        } catch (Exception e) {
            System.err.println("Uknown error" + e.getMessage());
            resizedImg = null;
        }
        if (resizedImg != null) {
            return new CardPanel(resizedImg, d);
        }
        JPanel p = new JPanel();
        p.add(new JLabel("Opponent Card"));
        return p;
    }

}
