package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * this class is an buffered image that can be rendered and altered its size on screen
 */
public class UIImage extends UIObject{

    private final BufferedImage image;

    /**
     * this is the main constructor for the class
     * @param x int value for the x axis
     * @param y int value for the y axis
     * @param width int value for the width of the image in pixels
     * @param height int value for the height of the image in pixels
     * @param image Buffered image for the image we want to render
     */
    public UIImage(float x, float y, int width, int height, BufferedImage image, String id) {
        super(x*16, y*16, width*16, height*16, id);
        this.image = image;
    }

    /**
     * unused imported method form parent class
     */
    @Override
    public void tick() {
    }

    /**
     * this renders the image on screen
     * @param g java.awt graphics object
     */
    @Override
    public void render(Graphics g) {
            g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    /**
     * unused imported method from parent class
     */
    @Override
    public void onClick() {
    }
}
