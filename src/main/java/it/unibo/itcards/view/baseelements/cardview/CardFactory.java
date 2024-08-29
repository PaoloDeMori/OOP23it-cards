package it.unibo.itcards.view.baseelements.cardview;

import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.*;

public class CardFactory {
    private Image resizedImg;
    private Dimension d;

    public CardFactory(Dimension d,String name) {
        this.d = d;
        try {
            BufferedImage image =ImagesHelper.loadImage(name);
            if (image == null) {
                throw new IOException("Image not found");
            }
            int height = (int) d.getHeight();
            int width = (int) (height * (double) image.getWidth() / image.getHeight());
            resizedImg = ImagesHelper.resizeCardShapedImage(image, height);
            this.d = new Dimension(width, height);
        } catch (IOException e) {
            System.err.println("Error while loading the image" + e.getMessage());
            resizedImg = null;
        } catch (Exception e) {
            System.err.println("Uknown error" + e.getMessage());
            resizedImg = null;
        }
    }

    public JPanel build() {
        if (resizedImg != null) {
            return new CardPanel(resizedImg, d);
        } else {
            JPanel p = new JPanel();
            p.add(new JLabel("Opponent Card"));
            return p;
        }
    }
}