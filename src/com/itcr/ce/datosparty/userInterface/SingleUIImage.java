package com.itcr.ce.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleUIImage extends UIObject{

    private BufferedImage[] image;
    private ClickListener clicker;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param image this uses an Array List from java, remember to change it for one of the data structures Gabo created
     */
    public SingleUIImage(float x, float y, int width, int height, BufferedImage[] image, String id) {
        super(x*16, y*16, width*16, height*16, id);
        this.image = image;
    }

    @Override
    public void tick() {


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image[0], (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
    }
}

