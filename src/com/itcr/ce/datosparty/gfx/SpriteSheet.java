package com.itcr.ce.datosparty.gfx;

import java.awt.image.BufferedImage;

/**
 * Provides the ability to crop an image and use only the cropped part
 */
public class SpriteSheet {

    private BufferedImage sheet;

    /**
     * Constructor for the SpriteSheet class
     * @param sheet Buffered image
     */
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /**
     * A method that will crop out a section of an image, and then return the section selected as a new buffered image
     * @param gridX int position of a subImage in a 16*16 pixel grid for its X value
     * @param gridY int position of a subImage in a 16*16 pixel grid for its Y value
     * @param width int number of 16*16 pixel squares for the desired sub-image for width
     * @param height int number of 16*16 pixel squares for the desired sub-image for height
     * @return a Buffered image of the desired parameters
     */
    public BufferedImage crop(int gridX, int gridY, int width, int height){
        return sheet.getSubimage(gridX*16, gridY*16, width*16, height*16);
    }
}
