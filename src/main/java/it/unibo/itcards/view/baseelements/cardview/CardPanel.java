package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private Image image;

    public CardPanel(Image image, Dimension d) {
        this.image = image;
        setPreferredSize(d);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
