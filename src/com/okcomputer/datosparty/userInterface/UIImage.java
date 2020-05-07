package com.okcomputer.datosparty.userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject{

    private BufferedImage[] images;
    private ClickListener clicker;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param images this uses an Array List from java, remember to change it for one of the data structures Gabo created
     */
    public UIImage(float x, float y, int width, int height, BufferedImage[] images) {
        super(x, y, width*16, height*16);
        this.images = images;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering)
            g.drawImage(images[1], (int)x, (int)y, width, height, null);
        else
            g.drawImage(images[0], (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
    }
}
