package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import it.unibo.itcards.commons.Card;

import java.io.IOException;

/**
 * Helper class for images.
 */
public final class ImagesHelper {

    /**
     * Private constructor.
     */
    private ImagesHelper() {
    }

    /**
     * Loads an image from the CardsImages directory based on the given card.
     *
     * @param card the card to load the image for
     * @return the loaded BufferedImage
     * @throws IOException if an I/O error occurs while reading the image
     */
    public static BufferedImage loadImage(final Card card) throws IOException {
        String path = "/CardsImages/" + card.toString() + ".jpeg";
        BufferedImage image = ImageIO.read(ImagesHelper.class.getResource(path));
        return image;
    }

    /**
     * Loads a card image from the resources.
     *
     * @param string the name of the card image to load
     * @return the loaded card image
     */
    public static BufferedImage loadImage(final String string) throws IOException {
        String path = "/CardsImages/" + string + ".jpeg";
        BufferedImage image = ImageIO.read(ImagesHelper.class.getResource(path));
        return image;
    }

    /**
     * Calculates the ratio of the width to the height of a BufferedImage.
     *
     * @param image the BufferedImage to calculate the ratio for
     * @return the ratio of the width to the height of the image
     */
    public static double getImageRatio(final BufferedImage image) {
        double ratio = ((double) image.getWidth() / image.getHeight());
        return ratio;
    }

    /**
     * Resizes a card-shaped image to a specified height.
     *
     * @param image  the BufferedImage to resize
     * @param height the desired height of the resized image
     * @return the resized Image
     */
    public static Image resizeCardShapedImage(final BufferedImage image, final int height) {
        Image resizedImg;
        int width = (int) (height * (double) image.getWidth() / image.getHeight());
        resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImg;
    }
}
