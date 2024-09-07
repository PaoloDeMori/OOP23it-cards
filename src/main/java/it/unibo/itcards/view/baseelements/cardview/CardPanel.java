package it.unibo.itcards.view.baseelements.cardview;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardPanel extends JPanel {
    private Image resizedImage;
    private int width;

    public CardPanel(BufferedImage image, int height,String name) {
        if (image != null) {
            width = (int) (height * ImagesHelper.getImageRatio(image));
            resizedImage = ImagesHelper.resizeCardShapedImage(image, height);
            this.setPreferredSize(new Dimension(width, height));
        } else {
            this.add(new JLabel(name));
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (resizedImage != null) {
            g.drawImage(resizedImage, 0, 0, this);
        }
    }
}
