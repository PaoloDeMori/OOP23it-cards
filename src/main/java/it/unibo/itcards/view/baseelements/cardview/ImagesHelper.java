package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import it.unibo.itcards.commons.Card;

import java.io.IOException;

public final class ImagesHelper {

    private ImagesHelper() {
    }

    public static BufferedImage loadImage(final Card card) throws IOException {
        String path = "/CardsImages/" + card.toString() + ".jpeg";
        BufferedImage image = ImageIO.read(ImagesHelper.class.getResource(path));
        return image;
    }

    public static BufferedImage loadImage(final String string) throws IOException {
        String path = "/CardsImages/" + string + ".jpeg";
        BufferedImage image = ImageIO.read(ImagesHelper.class.getResource(path));
        return image;
    }

    public static double getImageRatio(final BufferedImage image) {
        double ratio = ((double) image.getWidth() / image.getHeight());
        return ratio;
    }

    public static Image resizeCardShapedImage(final BufferedImage image, final int height) {
        Image resizedImg;
        int width = (int) (height * (double) image.getWidth() / image.getHeight());
        resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImg;
    }
}
