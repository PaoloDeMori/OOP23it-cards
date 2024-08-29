package it.unibo.itcards.view.baseelements.cardview;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import it.unibo.itcards.model.baseelements.cards.Card;

import java.io.IOException;

public final class ImagesHelper {

    public static BufferedImage loadImage(Card card) throws IOException {
        String path = "/CardsImages/" + card.toString() + ".jpeg";
        BufferedImage image = ImageIO.read(ImagesHelper.class.getResource(path));
        return image;
    }

    public static BufferedImage loadImage(String string) throws IOException {
        String path = "/CardsImages/" + string + ".jpeg";
        BufferedImage image = ImageIO.read(ImagesHelper.class.getResource(path));
        return image;
    }

    public static double getImageRatio(BufferedImage image){
        double ratio =((double) image.getWidth() / image.getHeight());
        return ratio;
    }

    public static Image resizeCardShapedImage(BufferedImage image, int height){
        Image resizedImg;
        int width = (int) (height * (double) image.getWidth() / image.getHeight());
        resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImg;
    }
}
