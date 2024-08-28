package it.unibo.itcards.view.baseelements.cardview;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.*;

public class BackCardFactory {
    private Image resizedImg;
    private Dimension d;

    public BackCardFactory(Dimension d) {
        this.d = d;
        String path = "/CardsImages/retro.jpeg";
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            if (image == null) {
                throw new IOException("Immagine non trovata nel percorso: " + path);
            }
            int height = (int) d.getHeight();
            int width = (int) ((double) height * image.getWidth() / image.getHeight());
            resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            this.d = new Dimension(width, height);
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dell'immagine: " + e.getMessage());
            resizedImg = null;
        } catch (Exception e) {
            System.err.println("Errore sconosciuto: " + e.getMessage());
            resizedImg = null;
        }
    }

    public JPanel build() {
        if (resizedImg != null) {
            return new BackCardPanel(resizedImg, d);
        } else {
            JPanel p = new JPanel();
            p.add(new JLabel("Opponent Card"));
            return p;
        }
    }
}