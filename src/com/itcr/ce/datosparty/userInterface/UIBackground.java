package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * this class renders an image and warps its size to fit our screen
 */
public class UIBackground extends UIObject{
    private final BufferedImage image;

    /**
     * main constructor for the class, only needs the buffered image and the string id
     * @param image buffered image that will be rendered
     * @param id String id for the image
     */
    public UIBackground(BufferedImage image, String id) {
        super(0, 0, 800*2, 608*2, id);
        this.image = image;
    }

    /**
     * unused method extended from parent
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
        g.drawImage(image, 0, 0, 800*2, 608*2, null);
    }

    /**
     * unused method extended from parent
     */
    @Override
    public void onClick() {
    }
}
