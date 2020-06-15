package com.itcr.ce.datosparty.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Loads images and provides the ability to display them onscreen
 */
public class ImageLoader {

    /**
     * This constructor will accept a String value of a file path, and return a Buffered image
     * @param path String value of files location
     * @return Buffered image
     */
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
